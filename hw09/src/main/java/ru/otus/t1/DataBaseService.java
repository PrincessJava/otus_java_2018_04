package ru.otus.t1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseService {
    private static String DB_PATH = "jdbc:postgresql://localhost:5432/hw09";
    private static String DB_USERNAME = "postgres";
    private static String DB_PASSWORD = "postgres";

    private static Connection connection;

    private DataBaseService() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
