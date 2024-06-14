package thalathuoya.guest.house;

import java.sql.*;

public class UserManagement {

    public ResultSet getAllUsers() throws SQLException {
        Connection conn = Conn.getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM users");
    }

    public ResultSet getUserLoginHistory() throws SQLException {
        Connection conn = Conn.getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM login_history");
    }

    public void addUser(String username, String password, String role) throws SQLException {
        Connection conn = Conn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, role);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateUserPassword(String username, String newPassword) throws SQLException {
        Connection conn = Conn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
        pstmt.setString(1, newPassword);
        pstmt.setString(2, username);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateUserRole(String username, String newRole) throws SQLException {
        Connection conn = Conn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET role = ? WHERE username = ?");
        pstmt.setString(1, newRole);
        pstmt.setString(2, username);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteUser(String username) throws SQLException {
        Connection conn = Conn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE username = ?");
        pstmt.setString(1, username);
        pstmt.executeUpdate();
        pstmt.close();
    }
}
