package thalathuoya.guest.house;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class EmployeeDashboard extends JFrame implements ActionListener {
    private String role;
    JTextField tfname, tfage, tfemail, tfphone, tfsalary;
    JRadioButton malebtn, femalebtn;
    JButton submit, update, delete, clear, manageBillsBtn;
    JComboBox<String> cbjob;
    JTable table;
    DefaultTableModel model;
    Conn conn;

    public EmployeeDashboard(String role) {
        this.role = role;

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(Color.gray);
        setLayout(null);
        setTitle("Employee Management Dashboard");

        JLabel heading = new JLabel("Manage Employees");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(34, 45, 65));
        heading.setBounds(150, 20, 300, 30);
        add(heading);

        addLabelAndField("Name", 80, tfname = new JTextField());
        addLabelAndField("Age", 130, tfage = new JTextField());
        addLabelAndField("Gender", 180);
        addLabelAndField("Job", 230, cbjob = new JComboBox<>(new String[]{"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Waiter/Waitress", "Manager", "Accountant", "Chef"}));
        addLabelAndField("Salary", 280, tfsalary = new JTextField());
        addLabelAndField("Phone", 330, tfphone = new JTextField());
        addLabelAndField("Email", 380, tfemail = new JTextField());

        malebtn = new JRadioButton("Male");
        femalebtn = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(malebtn);
        genderGroup.add(femalebtn);
        malebtn.setBounds(200, 180, 70, 30);
        femalebtn.setBounds(280, 180, 70, 30);
        add(malebtn);
        add(femalebtn);

        addButton("Submit", 60, 430, submit = new JButton());
        addButton("Update", 170, 430, update = new JButton());
        addButton("Delete", 280, 430, delete = new JButton());
        addButton("Clear", 390, 430, clear = new JButton());

       
        manageBillsBtn = new JButton("Manage Bills");
        manageBillsBtn.setBounds(500, 430, 150, 30);
        manageBillsBtn.setBackground(new Color(34, 45, 65));
        manageBillsBtn.setForeground(Color.white);
        manageBillsBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        manageBillsBtn.setFocusPainted(false);
        manageBillsBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        manageBillsBtn.addActionListener(this);
        add(manageBillsBtn);

        JLabel image = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg")).getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT)));
        image.setBounds(370, 70, 450, 350);
        add(image);

        conn = new Conn();

        String[] columns = {"Name", "Age", "Gender", "Job", "Salary", "Phone", "Email"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 480, 800, 150);
        add(scrollPane);

        setBounds(350, 200, 850, 680);
        setVisible(true);

        populateTable();
    }

    private void addLabelAndField(String text, int y, JTextField field) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setBounds(60, y, 120, 30);
        add(label);
        field.setBounds(200, y, 150, 30);
        add(field);
    }

    private void addLabelAndField(String text, int y, JComboBox<String> comboBox) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setBounds(60, y, 120, 30);
        add(label);
        comboBox.setBounds(200, y, 150, 30);
        add(comboBox);
    }

    private void addLabelAndField(String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setBounds(60, y, 120, 30);
        add(label);
    }

    private void addButton(String text, int x, int y, JButton button) {
        button.setText(text);
        button.setBounds(x, y, 100, 30);
        button.setBackground(new Color(34, 45, 65));
        button.setForeground(Color.white);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.addActionListener(this);
        add(button);
    }

    private void populateTable() {
        try {
            model.setRowCount(0); 
            String query = "SELECT * FROM employee";
            ResultSet rs = conn.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("name"), rs.getString("age"), rs.getString("gender"), rs.getString("job"), rs.getString("salary"), rs.getString("phone"), rs.getString("email")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String age = tfage.getText();
        String gender = malebtn.isSelected() ? "Male" : "Female";
        String job = (String) cbjob.getSelectedItem();
        String salary = tfsalary.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();

        if (ae.getSource() == submit) {
            try {
                String query = "INSERT INTO employee VALUES('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "')";
                conn.getConnection().createStatement().executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                clearFields();
                populateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            try {
                String query = "UPDATE employee SET age='" + age + "', gender='" + gender + "', job='" + job + "', salary='" + salary + "', phone='" + phone + "', email='" + email + "' WHERE name='" + name + "'";
                conn.getConnection().createStatement().executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                clearFields();
                populateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == delete) {
            try {
                String query = "DELETE FROM employee WHERE name='" + name + "'";
                conn.getConnection().createStatement().executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details deleted successfully");
                clearFields();
                populateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == clear) {
            clearFields();
        } else if (ae.getSource() == manageBillsBtn) {
            new ManageBills();
        }
    }

    private void clearFields() {
        tfname.setText("");
        tfage.setText("");
        tfemail.setText("");
        tfphone.setText("");
        tfsalary.setText("");
        malebtn.setSelected(false);
        femalebtn.setSelected(false);
        cbjob.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new EmployeeDashboard("");
    }
}
