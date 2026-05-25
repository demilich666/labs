package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private final DatabaseConfig databaseConfig;

    public DatabaseInitializer(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public void init() {
        try (Connection connection = databaseConfig.openConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS source_data (
                        id BIGSERIAL PRIMARY KEY,
                        value TEXT NOT NULL
                    )
                    """);

            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS processed_data (
                        id BIGSERIAL PRIMARY KEY,
                        source_id BIGINT NULL,
                        value TEXT NOT NULL
                    )
                    """);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to initialize database", e);
        }
    }

    public void seedSourceDataIfEmpty() {
        String countSql = "SELECT COUNT(*) FROM source_data";
        String insertSql = "INSERT INTO source_data(value) VALUES (?)";

        try (Connection connection = databaseConfig.openConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countSql)) {

            resultSet.next();
            long count = resultSet.getLong(1);

            if (count > 0) {
                return;
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                String[] sample = {
                        "  Java  ",
                        "stream api",
                        "",
                        "PostgreSQL",
                        "  concurrent processing  ",
                        "jdbc"
                };

                for (String value : sample) {
                    preparedStatement.setString(1, value);
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to seed source data", e);
        }
    }
}
