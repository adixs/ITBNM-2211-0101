package thalathuoya.guest.house;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/tdb";
    private static final String USER = "root";
    private static final String PASSWORD = "cisco";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: Failed to connect to the database");
            return null;
        }
    }
}
