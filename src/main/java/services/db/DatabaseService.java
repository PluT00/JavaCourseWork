package services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private static final String URL = "jdbc:postgresql://217.107.219.154:49307/bonch_2105600";
    private static final String USER = "bonch_2105600";
    private static final String PASSWORD = "qm1dtlfPBro=";

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}

