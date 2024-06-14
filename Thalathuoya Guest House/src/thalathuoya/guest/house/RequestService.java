package thalathuoya.guest.house;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestService extends JFrame implements ActionListener {
    JTextField serviceField;

    public RequestService() {
        setTitle("Request Service");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter your service request:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(label, BorderLayout.NORTH);

        serviceField = new JTextField();
        serviceField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(serviceField, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String request = serviceField.getText();
        if (request.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a service request.");
        } else {
            
            JOptionPane.showMessageDialog(this, "Service request submitted: " + request);
        }
    }

    public static void main(String[] args) {
        new RequestService();
    }
}
