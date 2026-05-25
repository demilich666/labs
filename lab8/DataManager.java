package org.example;

import org.example.DataProcessor;
import org.example.DatabaseConfig;
import org.example.DataRecord;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class DataManager {
    private final DatabaseConfig databaseConfig;
    private final List<ProcessorStep> processorSteps = new ArrayList<>();
    private List<DataRecord> loadedData = new ArrayList<>();
    private List<DataRecord> processedData = new ArrayList<>();

    public DataManager(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public void registerDataProcessor(Object processor) {
        Objects.requireNonNull(processor, "processor must not be null");

        for (Method method : processor.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(DataProcessor.class)) {
                continue;
            }

            if (method.getParameterCount() != 1) {
                throw new IllegalArgumentException(
                        "Method " + method.getName() + " must have exactly one parameter");
            }

            if (!Stream.class.isAssignableFrom(method.getParameterTypes()[0])) {
                throw new IllegalArgumentException(
                        "Method " + method.getName() + " must accept Stream as a parameter");
            }

            if (!Stream.class.isAssignableFrom(method.getReturnType())) {
                throw new IllegalArgumentException(
                        "Method " + method.getName() + " must return Stream");
            }

            method.setAccessible(true);
            int order = method.getAnnotation(DataProcessor.class).order();
            processorSteps.add(new ProcessorStep(processor, method, order));
        }

        processorSteps.sort(Comparator.comparingInt(ProcessorStep::order));
    }

    public void loadData(String source) {
        String sql = "SELECT id, value FROM " + source + " ORDER BY id";

        try (Connection connection = databaseConfig.openConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<DataRecord> records = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String value = resultSet.getString("value");
                records.add(new DataRecord(id, value));
            }

            loadedData = records;
            processedData = new ArrayList<>(records);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to load data from " + source, e);
        }
    }

    public void processData() {
        if (loadedData.isEmpty()) {
            processedData = new ArrayList<>();
            return;
        }

        int threads = Math.min(Runtime.getRuntime().availableProcessors(), loadedData.size());
        threads = Math.max(1, threads);

        List<List<DataRecord>> chunks = splitIntoChunks(loadedData, threads);
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        try {
            List<CompletableFuture<List<DataRecord>>> futures = chunks.stream()
                    .map(chunk -> CompletableFuture.supplyAsync(() -> processChunk(chunk), executorService))
                    .toList();

            processedData = futures.stream()
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .sorted(Comparator.comparing(DataRecord::getId, Comparator.nullsLast(Long::compareTo)))
                    .collect(Collectors.toList());
        } finally {
            executorService.shutdown();
        }
    }

    public void saveData(String destination) {
        String deleteSql = "DELETE FROM " + destination;
        String insertSql = "INSERT INTO " + destination + " (source_id, value) VALUES (?, ?)";

        try (Connection connection = databaseConfig.openConnection()) {
            connection.setAutoCommit(false);

            try (Statement deleteStatement = connection.createStatement()) {
                deleteStatement.executeUpdate(deleteSql);
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                for (DataRecord record : processedData) {
                    if (record.getValue() == null) {
                        continue;
                    }
                    if (record.getId() == null) {
                        preparedStatement.setObject(1, null);
                    } else {
                        preparedStatement.setLong(1, record.getId());
                    }
                    preparedStatement.setString(2, record.getValue());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to save data to " + destination, e);
        }
    }

    public List<DataRecord> getProcessedData() {
        return List.copyOf(processedData);
    }

    private List<DataRecord> processChunk(List<DataRecord> chunk) {
        Stream<DataRecord> stream = chunk.stream();

        for (ProcessorStep step : processorSteps) {
            stream = invokeProcessor(step, stream);
        }

        return stream.collect(Collectors.toList());
    }

    private Stream<DataRecord> invokeProcessor(ProcessorStep step, Stream<DataRecord> input) {
        try {
            return (Stream<DataRecord>) step.method().invoke(step.target(), input);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to invoke processor method " + step.method().getName(), e);
        }
    }

    private List<List<DataRecord>> splitIntoChunks(List<DataRecord> data, int chunksCount) {
        int chunkSize = Math.max(1, (data.size() + chunksCount - 1) / chunksCount);
        List<List<DataRecord>> chunks = new ArrayList<>();

        for (int i = 0; i < data.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, data.size());
            chunks.add(new ArrayList<>(data.subList(i, end)));
        }

        return chunks;
    }
}
