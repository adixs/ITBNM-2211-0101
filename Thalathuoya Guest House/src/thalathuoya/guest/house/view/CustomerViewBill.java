package thalathuoya.guest.house.view;

import javax.swing.*;
import java.awt.*;

public class CustomerViewBill extends JFrame {
    public JTextField tfCustomerName;
    public JTextArea taResults;
    public JButton searchBtn;

    public CustomerViewBill() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(null);

        JLabel heading = new JLabel("View Bill");
        heading.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        heading.setForeground(new Color(255, 255, 255));
        heading.setBounds(150, 20, 200, 30);
        add(heading);

        JLabel lblCustomerName = new JLabel("Customer Name:");
        lblCustomerName.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblCustomerName.setForeground(new Color(255, 255, 255));
        lblCustomerName.setBounds(50, 80, 150, 30);
        add(lblCustomerName);

        tfCustomerName = new JTextField();
        tfCustomerName.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        tfCustomerName.setBounds(200, 80, 200, 30);
        add(tfCustomerName);

        searchBtn = createStyledButton("Search");
        searchBtn.setBounds(410, 80, 100, 30);
        add(searchBtn);

        taResults = new JTextArea();
        taResults.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        taResults.setBackground(new Color(240, 240, 240));
        taResults.setForeground(new Color(50, 50, 50));
        JScrollPane scrollPane = new JScrollPane(taResults);
        scrollPane.setBounds(50, 130, 500, 200);
        add(scrollPane);

        setBounds(350, 200, 600, 400);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.white);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        return button;
    }
}
