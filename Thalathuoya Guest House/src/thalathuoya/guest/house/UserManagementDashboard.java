package thalathuoya.guest.house;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.*;

public class UserManagementDashboard extends JFrame {
    private JTable userTable;
    private JTable loginHistoryTable;
    private DefaultTableModel userModel;
    private DefaultTableModel loginHistoryModel;

    private UserManagement userManagement;

    public UserManagementDashboard() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        userManagement = new UserManagement();

        getContentPane().setBackground(new Color(45, 45, 45));
        setTitle("User Management Dashboard");
        setBounds(100, 100, 800, 600);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        JPanel userPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Users", userPanel);

        userModel = new DefaultTableModel(new String[]{"Username", "Password", "Role"}, 0);
        userTable = new JTable(userModel);
        userTable.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        userTable.setRowHeight(30);
        userTable.setBackground(new Color(70, 130, 180));
        userTable.setForeground(Color.black);
        userTable.setGridColor(new Color(128, 128, 128));
        userTable.setSelectionBackground(new Color(100, 149, 237));
        userTable.setSelectionForeground(Color.WHITE);

        JTableHeader userTableHeader = userTable.getTableHeader();
        userTableHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        userTableHeader.setBackground(new Color(50, 50, 50));
        userTableHeader.setForeground(Color.BLACK);

        JScrollPane userScrollPane = new JScrollPane(userTable);
        userPanel.add(userScrollPane, BorderLayout.CENTER);

        JPanel userButtonPanel = new JPanel();
        userButtonPanel.setBackground(new Color(45, 45, 45));
        userPanel.add(userButtonPanel, BorderLayout.SOUTH);

        JButton addUserButton = new JButton("Add User");
        JButton updateUserButton = new JButton("Update User");
        JButton deleteUserButton = new JButton("Delete User");

        configureButton(addUserButton);
        configureButton(updateUserButton);
        configureButton(deleteUserButton);

        userButtonPanel.add(addUserButton);
        userButtonPanel.add(updateUserButton);
        userButtonPanel.add(deleteUserButton);

        addUserButton.addActionListener(e -> addUser());
        updateUserButton.addActionListener(e -> updateUser());
        deleteUserButton.addActionListener(e -> deleteUser());

        loadUsers();

        JPanel loginHistoryPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Login History", loginHistoryPanel);

        loginHistoryModel = new DefaultTableModel(new String[]{"Username", "Login Time"}, 0);
        loginHistoryTable = new JTable(loginHistoryModel);
        loginHistoryTable.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        loginHistoryTable.setRowHeight(30);
        loginHistoryTable.setBackground(new Color(70, 130, 180));
        loginHistoryTable.setForeground(Color.black);
        loginHistoryTable.setGridColor(new Color(128, 128, 128));
        loginHistoryTable.setSelectionBackground(new Color(100, 149, 237));
        loginHistoryTable.setSelectionForeground(Color.WHITE);

        JTableHeader loginHistoryTableHeader = loginHistoryTable.getTableHeader();
        loginHistoryTableHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        loginHistoryTableHeader.setBackground(new Color(50, 50, 50));
        loginHistoryTableHeader.setForeground(Color.BLACK);

        JScrollPane loginHistoryScrollPane = new JScrollPane(loginHistoryTable);
        loginHistoryPanel.add(loginHistoryScrollPane, BorderLayout.CENTER);

        loadLoginHistory();

        setVisible(true);
    }

    private void configureButton(JButton button) {
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.BLACK);
    }

    private void loadUsers() {
        try {
            ResultSet rs = userManagement.getAllUsers();
            userModel.setRowCount(0);
            while (rs.next()) {
                userModel.addRow(new Object[]{rs.getString("username"), rs.getString("password"), rs.getString("role")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadLoginHistory() {
        try {
            ResultSet rs = userManagement.getUserLoginHistory();
            loginHistoryModel.setRowCount(0);
            while (rs.next()) {
                loginHistoryModel.addRow(new Object[]{rs.getString("username"), rs.getString("login_time")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUser() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        String[] roles = {"customer", "employee", "admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField,
            "Role:", roleComboBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                userManagement.addUser(usernameField.getText(), new String(passwordField.getPassword()), (String) roleComboBox.getSelectedItem());
                loadUsers();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
            }
        }
    }

    private void updateUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) userModel.getValueAt(selectedRow, 0);

            JPasswordField passwordField = new JPasswordField();
            String[] roles = {"customer", "employee", "admin"};
            JComboBox<String> roleComboBox = new JComboBox<>(roles);

            Object[] message = {
                "New Password:", passwordField,
                "New Role:", roleComboBox
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Update User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    if (!new String(passwordField.getPassword()).isEmpty()) {
                        userManagement.updateUserPassword(username, new String(passwordField.getPassword()));
                    }
                    userManagement.updateUserRole(username, (String) roleComboBox.getSelectedItem());
                    loadUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to update");
        }
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) userModel.getValueAt(selectedRow, 0);

            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    userManagement.deleteUser(username);
                    userModel.removeRow(selectedRow);  // Remove the row from the table model
                    loadUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a user to delete");
        }
    }

    public static void main(String[] args) {
        new UserManagementDashboard();
    }
}
