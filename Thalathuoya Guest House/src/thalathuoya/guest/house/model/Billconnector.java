package thalathuoya.guest.house.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Billconnector {
    private Connection conn;

    public Billconnector() {
        try {
            
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tdb", "root", "cisco");
        } catch (ClassNotFoundException e) {
            System.err.println("MariaDB JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
    }

    public void createBill(String customerName, String description, double amount) {
        try {
            String query = "INSERT INTO bills (customer_name, description, amount) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, customerName);
            pstmt.setString(2, description);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getBillsByCustomerName(String customerName) {
        try {
            String query = "SELECT * FROM bills WHERE customer_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, customerName);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
