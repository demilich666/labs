package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DatabaseConfig fromSystemProperties() {
        String url = System.getProperty("db.url", "jdbc:postgresql://localhost:5432/demo_db");
        String user = System.getProperty("db.user", "postgres");
        String password = System.getProperty("db.password", "postgres");
        return new DatabaseConfig(url, user, password);
    }

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

