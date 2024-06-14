package thalathuoya.guest.house;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField password;
    JButton login, cancel, forgotPassword, createAccount;

    public Login() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(41, 36, 118));
        setLayout(null);

        JLabel user = new JLabel("Username:");
        user.setBounds(40, 20, 100, 30);
        user.setForeground(Color.WHITE);
        add(user);

        JLabel pass = new JLabel("Password:");
        pass.setBounds(40, 70, 100, 30);
        pass.setForeground(Color.WHITE);
        add(pass);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setFont(new Font("Serif", Font.BOLD, 15));
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.setFont(new Font("Serif", Font.BOLD, 15));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        forgotPassword = new JButton("Forgot Password");
        forgotPassword.setBounds(40, 180, 260, 30);
        forgotPassword.setFont(new Font("Serif", Font.BOLD, 15));
        forgotPassword.setBackground(Color.BLACK);
        forgotPassword.setForeground(Color.WHITE);
        forgotPassword.addActionListener(this);
        add(forgotPassword);

        createAccount = new JButton("Create New Account");
        createAccount.setBounds(40, 220, 260, 30);
        createAccount.setFont(new Font("Serif", Font.BOLD, 15));
        createAccount.setBackground(Color.BLACK);
        createAccount.setForeground(Color.WHITE);
        createAccount.addActionListener(this);
        add(createAccount);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(320, 10, 150, 150);
        add(image);

        setTitle("Login");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            login();
        } else if (ae.getSource() == cancel) {
            System.exit(0);
        } else if (ae.getSource() == forgotPassword) {
            forgotPassword();
        } else if (ae.getSource() == createAccount) {
            createAccount();
        }
    }

    private void login() {
        String user = username.getText();
        String pass = new String(password.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and password cannot be empty");
            return;
        }

        try (Connection conn = Conn.getConnection()) {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                JOptionPane.showMessageDialog(null, "Login Successful");
                this.setVisible(false); // Hide the login window
                if ("employee".equalsIgnoreCase(role)) {
                    new EmployeeDashboard(role).setVisible(true);
                } else if ("customer".equalsIgnoreCase(role)) {
                    new CustomerDashboard().setVisible(true);
                } else if ("admin".equalsIgnoreCase(role)) {
                    new Dashboard().setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void forgotPassword() {
        String user = JOptionPane.showInputDialog(this, "Enter your username:");
        if (user != null) {
            try (Connection conn = Conn.getConnection()) {
                String query = "SELECT * FROM users WHERE username=?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    JPasswordField newPasswordField = new JPasswordField();
                    Object[] message = {
                        "New Password:", newPasswordField
                    };
                    int option = JOptionPane.showConfirmDialog(this, message, "Reset Password", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String newPass = new String(newPasswordField.getPassword());
                        if (newPass.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Password cannot be empty");
                            return;
                        }
                        String updateQuery = "UPDATE users SET password=? WHERE username=?";
                        PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
                        updatePstmt.setString(1, newPass);
                        updatePstmt.setString(2, user);
                        updatePstmt.executeUpdate();
                        updatePstmt.close();
                        JOptionPane.showMessageDialog(null, "Password reset successfully");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found");
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private void createAccount() {
        JTextField newUserField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        String[] roles = {"Customer", "Employee", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        
        Object[] message = {
            "Username:", newUserField,
            "Password:", newPasswordField,
            "Role:", roleComboBox
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Create New Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String newUser = newUserField.getText();
            String newPass = new String(newPasswordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            if (newUser.isEmpty() || newPass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username and password cannot be empty");
                return;
            }

            try (Connection conn = Conn.getConnection()) {
                String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, newUser);
                pstmt.setString(2, newPass);
                pstmt.setString(3, role.toLowerCase());
                pstmt.executeUpdate();

                pstmt.close();
                JOptionPane.showMessageDialog(null, "Account created successfully");

                this.setVisible(false); 
                if ("employee".equalsIgnoreCase(role)) {
                    new EmployeeDashboard(role).setVisible(true);
                } else if ("customer".equalsIgnoreCase(role)) {
                    new CustomerDashboard().setVisible(true);
                } else if ("admin".equalsIgnoreCase(role)) {
                    new Dashboard().setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
