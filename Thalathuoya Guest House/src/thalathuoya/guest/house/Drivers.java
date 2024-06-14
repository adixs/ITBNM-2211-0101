package thalathuoya.guest.house;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Drivers extends JFrame implements ActionListener {
    JTextField tfname, tfage, tfcompany, tfmodel, tflocation;
    JComboBox<String> availableCombo, genderCombo;
    JButton addBtn, updateBtn, deleteBtn, clearBtn;
    JTable table;
    DefaultTableModel model;
    Conn conn;

    public Drivers() {
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        getContentPane().setBackground(Color.gray);
        setLayout(null);

       
        JLabel heading = new JLabel("Manage Drivers");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(34, 45, 65));
        heading.setBounds(150, 20, 200, 30);
        add(heading);

        
        conn = new Conn();

        
        JLabel lblDriverNo = new JLabel("Driver Number");
        lblDriverNo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDriverNo.setBounds(60, 80, 120, 30);
        add(lblDriverNo);

        tfname = new JTextField();
        tfname.setBounds(200, 80, 150, 30);
        tfname.setToolTipText("Enter the driver's number");
        add(tfname);

        
        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAge.setBounds(60, 130, 120, 30);
        add(lblAge);

        tfage = new JTextField();
        tfage.setBounds(200, 130, 150, 30);
        tfage.setToolTipText("Enter the driver's age");
        add(tfage);

        
        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblGender.setBounds(60, 180, 150, 30);
        add(lblGender);

        String[] genderOptions = {"Male", "Female"};
        genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(200, 180, 150, 30);
        genderCombo.setBackground(Color.white);
        genderCombo.setToolTipText("Select the driver's gender");
        add(genderCombo);

        
        JLabel lblCompany = new JLabel("Car Company");
        lblCompany.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCompany.setBounds(60, 230, 120, 30);
        add(lblCompany);

        tfcompany = new JTextField();
        tfcompany.setBounds(200, 230, 150, 30);
        tfcompany.setToolTipText("Enter the car company");
        add(tfcompany);

        
        JLabel lblModel = new JLabel("Car Model");
        lblModel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblModel.setBounds(60, 280, 120, 30);
        add(lblModel);

        tfmodel = new JTextField();
        tfmodel.setBounds(200, 280, 150, 30);
        tfmodel.setToolTipText("Enter the car model");
        add(tfmodel);

        
        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAvailable.setBounds(60, 330, 120, 30);
        add(lblAvailable);

        String[] driverOptions = {"Available", "Busy"};
        availableCombo = new JComboBox<>(driverOptions);
        availableCombo.setBounds(200, 330, 150, 30);
        availableCombo.setBackground(Color.white);
        availableCombo.setToolTipText("Select the driver's availability");
        add(availableCombo);

       
        JLabel lblLocation = new JLabel("Location");
        lblLocation.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLocation.setBounds(60, 380, 120, 30);
        add(lblLocation);

        tflocation = new JTextField();
        tflocation.setBounds(200, 380, 150, 30);
        tflocation.setToolTipText("Enter the driver's location");
        add(tflocation);

        
        addBtn = new JButton("Add");
        styleButton(addBtn);
        addBtn.setBounds(60, 430, 100, 30);
        addBtn.addActionListener(this);
        add(addBtn);

        updateBtn = new JButton("Update");
        styleButton(updateBtn);
        updateBtn.setBounds(170, 430, 100, 30);
        updateBtn.addActionListener(this);
        add(updateBtn);

        deleteBtn = new JButton("Delete");
        styleButton(deleteBtn);
        deleteBtn.setBounds(280, 430, 100, 30);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        clearBtn = new JButton("Clear");
        styleButton(clearBtn);
        clearBtn.setBounds(390, 430, 100, 30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image image2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(image2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 100, 500, 300);
        add(image);

      
        String[] columns = {"Driver Number", "Age", "Gender", "Car Company", "Car Model", "Available", "Location"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 480, 885, 150);
        add(scrollPane);

        populateTable(); 

        setBounds(350, 200, 940, 680);
        setVisible(true);
    }

    private void populateTable() {
        try {
           
            model.setRowCount(0);

            
            String query = "SELECT * FROM driver";
            ResultSet rs = conn.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("name"), rs.getString("age"), rs.getString("gender"),
                        rs.getString("company"), rs.getString("brand"), rs.getString("available"), rs.getString("location")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String driverNumber = tfname.getText();
        String age = tfage.getText();
        String gender = (String) genderCombo.getSelectedItem();
        String company = tfcompany.getText();
        String modelText = tfmodel.getText();
        String availability = (String) availableCombo.getSelectedItem();
        String location = tflocation.getText();

        if (ae.getSource() != clearBtn) {
            if (driverNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Driver Number should not be empty");
                return;
            }

            if (age.equals("") || !age.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Age should not be empty and must be a number");
                return;
            }

            if (company.equals("")) {
                JOptionPane.showMessageDialog(null, "Car Company should not be empty");
                return;
            }

            if (modelText.equals("")) {
                JOptionPane.showMessageDialog(null, "Car Model should not be empty");
                return;
            }

            if (location.equals("")) {
                JOptionPane.showMessageDialog(null, "Location should not be empty");
                return;
            }
        }

        try {
            String query = "";

            if (ae.getSource() == addBtn) {
                query = "INSERT INTO driver (name, age, gender, company, brand, available, location) VALUES ('" + driverNumber + "', '" + age + "', '" + gender + "', '" + company + "', '" + modelText + "', '" + availability + "', '" + location + "')";
                conn.getConnection().createStatement().executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Driver added successfully");
            } else if (ae.getSource() == updateBtn) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String originalDriverNumber = (String) model.getValueAt(selectedRow, 0);
                    query = "UPDATE driver SET name='" + driverNumber + "', age='" + age + "', gender='" + gender + "', company='" + company + "', brand='" + modelText + "', available='" + availability + "', location='" + location + "' WHERE name='" + originalDriverNumber + "'";
                    conn.getConnection().createStatement().executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Driver updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a driver to update");
                    return;
                }
            } else if (ae.getSource() == deleteBtn) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String originalDriverNumber = (String) model.getValueAt(selectedRow, 0);
                    query = "DELETE FROM driver WHERE name='" + originalDriverNumber + "'";
                    conn.getConnection().createStatement().executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Driver deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a driver to delete");
                    return;
                }
            } else if (ae.getSource() == clearBtn) {
                clearFields();
            }

            populateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tfname.setText("");
        tfage.setText("");
        tfcompany.setText("");
        tfmodel.setText("");
        tflocation.setText("");
        genderCombo.setSelectedIndex(0);
        availableCombo.setSelectedIndex(0);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(34, 45, 65));
        button.setForeground(Color.white);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    public static void main(String[] args) {
        new Drivers();
    }
}
