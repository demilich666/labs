package org.example;

import org.example.DataProcessor;
import org.example.DataRecord;

import java.util.List;
import java.util.stream.Stream;

public class StatisticsProcessor {
    @DataProcessor(order = 4)
    public Stream<DataRecord> printStatistics(Stream<DataRecord> stream) {
        List<DataRecord> records = stream.toList();

        long count = records.size();
        long totalLength = records.stream()
                .mapToLong(record -> record.getValue().length())
                .sum();
        long averageLength = count == 0 ? 0 : totalLength / count;

        System.out.println("Статистика по текущему потоку:");
        System.out.println("Количество записей: " + count);
        System.out.println("Средняя длина значения: " + averageLength);

        return records.stream();
    }
}
