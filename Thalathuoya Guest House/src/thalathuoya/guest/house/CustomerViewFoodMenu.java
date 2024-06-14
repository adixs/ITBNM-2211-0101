package thalathuoya.guest.house;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.*;

class CustomerViewFoodMenu extends JFrame {
    JTable table;
    DefaultTableModel model;
    Conn conn;

    public CustomerViewFoodMenu() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn = new Conn();

        getContentPane().setBackground(new Color(45, 45, 45));
        setLayout(null);

        JLabel heading = new JLabel("Food Menu");
        heading.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        heading.setForeground(new Color(255, 255, 255));
        heading.setBounds(20, 20, 200, 30);
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(340, 380, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(480, 80, 540, 380);
        add(imageLabel);

        String[] columns = {"Dish Name", "Price"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        table = new JTable(model);
        table.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setBackground(new Color(70, 130, 180));
        table.setForeground(Color.black);
        table.setGridColor(new Color(128, 128, 128));
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        tableHeader.setBackground(new Color(50, 50, 50));
        tableHeader.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 540, 380);
        add(scrollPane);

        setBounds(350, 200, 955, 520);
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

    public static void main(String[] args) {
        new CustomerViewFoodMenu();
    }
}
