package org.example.javamessenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    private static String port = "3306";
    private static String username;
    private static String password;

    public static Connection connection;

    public static void initialize(MySQLConfig config) throws SQLException {
        setPort(config.getPort());
        setUsername(config.getUsername());
        setPassword(config.getPassword());

        ConnectToDatabase();
        InitDB();
    }

    public static void ConnectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:" + port + "/sys";
        connection = DriverManager.getConnection(url, username, password);
    }

    public static void InitDB() throws SQLException {
        Statement statement = connection.createStatement();

        statement.addBatch("CREATE DATABASE IF NOT EXISTS java_messenger " +
                "CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;");
        statement.addBatch("USE java_messenger;");
        statement.addBatch("CREATE TABLE IF NOT EXISTS users (" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(50) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL," +
                "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ");");

        statement.executeBatch();
        statement.close();
    }

    public static void closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed()) connection.close();
    }

    public static void setPort(String port) {
        MySQL.port = port == null || port.isEmpty() ? "3306" : port;
    }

    public static void setUsername(String username) {
        MySQL.username = username;
    }

    public static void setPassword(String password) {
        MySQL.password = password;
    }
}
