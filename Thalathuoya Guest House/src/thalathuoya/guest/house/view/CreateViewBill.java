package thalathuoya.guest.house.view;

import javax.swing.*;
import java.awt.*;

public class CreateViewBill extends JFrame {
    public JTextField tfCustomerName;
    public JTextField tfDescription;
    public JTextField tfAmount;
    public JButton saveBtn;

    public CreateViewBill() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(null);

        JLabel heading = new JLabel("Create Bill");
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

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblDescription.setForeground(new Color(255, 255, 255));
        lblDescription.setBounds(50, 130, 150, 30);
        add(lblDescription);

        tfDescription = new JTextField();
        tfDescription.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        tfDescription.setBounds(200, 130, 200, 30);
        add(tfDescription);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblAmount.setForeground(new Color(255, 255, 255));
        lblAmount.setBounds(50, 180, 150, 30);
        add(lblAmount);

        tfAmount = new JTextField();
        tfAmount.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        tfAmount.setBounds(200, 180, 200, 30);
        add(tfAmount);

        saveBtn = createStyledButton("Save");
        saveBtn.setBounds(150, 230, 100, 30);
        add(saveBtn);

        setBounds(350, 200, 500, 350);
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
