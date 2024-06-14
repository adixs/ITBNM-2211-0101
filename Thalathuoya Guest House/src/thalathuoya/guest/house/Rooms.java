package thalathuoya.guest.house;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Rooms extends JFrame implements ActionListener {
    JTextField tfroom, tfprice;
    JComboBox<String> availableCombo, cleanCombo, typeCombo;
    JButton add, update, delete, clear;
    JTable table;
    DefaultTableModel model;

    public Rooms() {
       
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

       
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);

       
        JLabel heading = new JLabel("Manage Rooms");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(Color.WHITE);
        heading.setBounds(150, 20, 200, 30);
        add(heading);

       
        JLabel lblroomno = new JLabel("Room Number");
        lblroomno.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblroomno.setBounds(60, 80, 120, 30);
        add(lblroomno);

        tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        tfroom.setToolTipText("Enter the room number");
        add(tfroom);

        
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblavailable.setBounds(60, 130, 120, 30);
        add(lblavailable);

        String[] availableOptions = {"Available", "Occupied"};
        availableCombo = new JComboBox<>(availableOptions);
        availableCombo.setBounds(200, 130, 150, 30);
        availableCombo.setBackground(Color.white);
        availableCombo.setToolTipText("Select availability status");
        add(availableCombo);

        
        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblclean.setBounds(60, 180, 150, 30);
        add(lblclean);

        String[] cleanOptions = {"Cleaned", "Dirty"};
        cleanCombo = new JComboBox<>(cleanOptions);
        cleanCombo.setBounds(200, 180, 150, 30);
        cleanCombo.setBackground(Color.white);
        cleanCombo.setToolTipText("Select cleaning status");
        add(cleanCombo);

        
        JLabel lblprice = new JLabel("Price");
        lblprice.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblprice.setBounds(60, 230, 120, 30);
        add(lblprice);

        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        tfprice.setToolTipText("Enter the room price");
        add(tfprice);

        
        JLabel lbltype = new JLabel("Bed Type");
        lbltype.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lbltype.setBounds(60, 280, 120, 30);
        add(lbltype);

        String[] typeOptions = {"Single Bed", "Double Bed"};
        typeCombo = new JComboBox<>(typeOptions);
        typeCombo.setBounds(200, 280, 150, 30);
        typeCombo.setBackground(Color.white);
        typeCombo.setToolTipText("Select bed type");
        add(typeCombo);

        
        add = new JButton("Add");
        styleButton(add);
        add.setBounds(60, 430, 100, 30);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        styleButton(update);
        update.setBounds(170, 430, 100, 30);
        update.addActionListener(this);
        add(update);

        delete = new JButton("Delete");
        styleButton(delete);
        delete.setBounds(280, 430, 100, 30);
        delete.addActionListener(this);
        add(delete);

        clear = new JButton("Clear");
        styleButton(clear);
        clear.setBounds(390, 430, 100, 30);
        clear.addActionListener(this);
        add(clear);

       
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 100, 500, 300);
        add(image);

        
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Room Number", "Available", "Cleaning Status", "Price", "Bed Type"});
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 480, 800, 150);
        add(scrollPane);

        setBounds(330, 200, 940, 700);
        setVisible(true);

        fetchAndShowData();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String roomNumber = tfroom.getText();
        String availability = (String) availableCombo.getSelectedItem();
        String cleaningStatus = (String) cleanCombo.getSelectedItem();
        String price = tfprice.getText();
        String bedType = (String) typeCombo.getSelectedItem();

        if (ae.getSource() != clear) {
            if (roomNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Room Number should not be empty");
                return;
            }

            if (price.equals("") || !price.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Price should not be empty and must be a number");
                return;
            }
        }

        try (Connection conn = Conn.getConnection()) {
            String query = "";
            if (ae.getSource() == add) {
                query = "INSERT INTO room (room_number, available, cleaning_status, price, bed_type) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, roomNumber);
                    pst.setString(2, availability);
                    pst.setString(3, cleaningStatus);
                    pst.setString(4, price);
                    pst.setString(5, bedType);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Room added successfully");
            } else if (ae.getSource() == update) {
                query = "UPDATE room SET available = ?, cleaning_status = ?, price = ?, bed_type = ? WHERE room_number = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, availability);
                    pst.setString(2, cleaningStatus);
                    pst.setString(3, price);
                    pst.setString(4, bedType);
                    pst.setString(5, roomNumber);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Room updated successfully");
            } else if (ae.getSource() == delete) {
                query = "DELETE FROM room WHERE room_number = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, roomNumber);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Room deleted successfully");
            } else if (ae.getSource() == clear) {
                clearFields();
            }
            fetchAndShowData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database operation failed: " + e.getMessage());
        }
    }

    private void fetchAndShowData() {
        try (Connection conn = Conn.getConnection()) {
            String query = "SELECT * FROM room";
            ResultSet rs = conn.createStatement().executeQuery(query);

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("room_number"), rs.getString("available"), rs.getString("cleaning_status"), rs.getString("price"), rs.getString("bed_type")});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch data: " + e.getMessage());
        }
    }

    private void clearFields() {
        tfroom.setText("");
        availableCombo.setSelectedIndex(0);
        cleanCombo.setSelectedIndex(0);
        tfprice.setText("");
        typeCombo.setSelectedIndex(0);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(new Color(34, 45, 65));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        new Rooms();
    }
}
