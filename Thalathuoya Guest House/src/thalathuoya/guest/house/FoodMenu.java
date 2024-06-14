package thalathuoya.guest.house;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class FoodMenu extends JFrame implements ActionListener {
    JTextField tfDish, tfPrice;
    JButton addBtn, updateBtn, deleteBtn, clearBtn;
    JTable table;
    DefaultTableModel model;
    Conn conn;

    public FoodMenu() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn = new Conn();

        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(null);

        JLabel heading = new JLabel("Food Menu");
        heading.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        heading.setForeground(new Color(255, 255, 255));
        heading.setBounds(150, 20, 200, 30);
        add(heading);



        JLabel lblDish = new JLabel("Dish Name");
        lblDish.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblDish.setForeground(new Color(255, 255, 255));
        lblDish.setBounds(50, 80, 100, 30);
        add(lblDish);

        tfDish = new JTextField();
        tfDish.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        tfDish.setBounds(150, 80, 200, 30);
        add(tfDish);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblPrice.setForeground(new Color(255, 255, 255));
        lblPrice.setBounds(50, 130, 100, 30);
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        tfPrice.setBounds(150, 130, 200, 30);
        add(tfPrice);

        addBtn = createStyledButton("Add");
        addBtn.setBounds(370, 80, 100, 30);
        addBtn.addActionListener(this);
        add(addBtn);

        updateBtn = createStyledButton("Update");
        updateBtn.setBounds(370, 130, 100, 30);
        updateBtn.addActionListener(this);
        add(updateBtn);

        deleteBtn = createStyledButton("Delete");
        deleteBtn.setBounds(480, 80, 100, 30);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        clearBtn = createStyledButton("Clear");
        clearBtn.setBounds(480, 130, 100, 30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        String[] columns = {"Dish Name", "Price"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 230, 600, 200);
        add(scrollPane);

        setBounds(350, 200, 700, 500);
        setVisible(true);

        populateTable();
    }

    private void populateTable() {
        try {
            model.setRowCount(0);

            String query = "SELECT * FROM food_menu";
            ResultSet rs = conn.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("dish_name"), rs.getString("price")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String dish = tfDish.getText();
        String price = tfPrice.getText();

        if (ae.getSource() != clearBtn) {
            if (dish.equals("")) {
                JOptionPane.showMessageDialog(null, "Dish Name should not be empty");
                return;
            }

            if (price.equals("") || !price.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Price should not be empty and must be a number");
                return;
            }
        }

        try {
            if (ae.getSource() == addBtn) {
                String query = "INSERT INTO food_menu (dish_name, price) VALUES ('" + dish + "', '" + price + "')";
                conn.getConnection().createStatement().executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Dish added successfully");
                populateTable();
            } else if (ae.getSource() == updateBtn) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String originalDish = (String) model.getValueAt(selectedRow, 0);
                    String query = "UPDATE food_menu SET dish_name='" + dish + "', price='" + price + "' WHERE dish_name='" + originalDish + "'";
                    conn.getConnection().createStatement().executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Dish updated successfully");
                    populateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a dish to update");
                }
            } else if (ae.getSource() == deleteBtn) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String originalDish = (String) model.getValueAt(selectedRow, 0);
                    String query = "DELETE FROM food_menu WHERE dish_name='" + originalDish + "'";
                    conn.getConnection().createStatement().executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Dish deleted successfully");
                    populateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a dish to delete");
                }
            } else if (ae.getSource() == clearBtn) {
                tfDish.setText("");
                tfPrice.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.white);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        return button;
    }

    public static void main(String[] args) {
        new FoodMenu();
    }
}
