package thalathuoya.guest.house;

import thalathuoya.guest.house.view.CustomerViewBill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CustomerDashboard extends JFrame implements ActionListener {

    public CustomerDashboard() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setTitle("Customer Dashboard");

        JLabel heading = new JLabel("Customer Dashboard");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(Color.WHITE);
        heading.setBounds(170, 20, 300, 30);
        add(heading);

        addButton("View Bookings", 60, 80);
        addButton("Request Service", 60, 130);
        addButton("View Food Menu", 60, 180);
        addButton("View Bill", 60, 230);
        addButton("Logout", 60, 280);

        
        URL imgURL = ClassLoader.getSystemResource("icons/tenth.jpg");
        if (imgURL != null) {
            ImageIcon imageIcon = new ImageIcon(imgURL);
            Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setBounds(300, 80, 250, 250); 
            add(imageLabel);
        } else {
            System.err.println("Image file not found: icons/tenth.jpg");
        }

        setBounds(350, 200, 600, 400);
        setVisible(true);
    }

    private void addButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 30);
        button.setBackground(new Color(34, 45, 65));
        button.setForeground(Color.white);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("View Bookings")) {
            new ViewBookings().setVisible(true);
        } else if (command.equals("Request Service")) {
            new RequestService().setVisible(true);
        } else if (command.equals("View Food Menu")) {
            new CustomerViewFoodMenu().setVisible(true);
        } else if (command.equals("View Bill")) {
            new CustomerViewBill().setVisible(true);
        } else if (command.equals("Logout")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new CustomerDashboard();
    }
}
