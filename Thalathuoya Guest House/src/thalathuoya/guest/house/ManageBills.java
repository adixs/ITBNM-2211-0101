package thalathuoya.guest.house;

import thalathuoya.guest.house.controller.CustomerBillController;
import thalathuoya.guest.house.model.Billconnector;
import thalathuoya.guest.house.view.CreateViewBill;
import thalathuoya.guest.house.view.CustomerViewBill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageBills extends JFrame implements ActionListener {
    private Billconnector model;

    public ManageBills() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.model = new Billconnector();
        setTitle("Manage Bills");
        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(null);

        JLabel heading = new JLabel("Manage Bills");
        heading.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        heading.setForeground(new Color(255, 255, 255));
        heading.setBounds(150, 20, 200, 30);
        add(heading);

        JButton createBillBtn = createStyledButton("Create Bill");
        createBillBtn.setBounds(50, 80, 150, 30);
        createBillBtn.addActionListener(this);
        add(createBillBtn);

        JButton viewBillBtn = createStyledButton("View Bill");
        viewBillBtn.setBounds(50, 130, 150, 30);
        viewBillBtn.addActionListener(this);
        add(viewBillBtn);

        setBounds(350, 200, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Create Bill")) {
            CreateViewBill createView = new CreateViewBill();
            new CustomerBillController(model, createView);
        } else if (command.equals("View Bill")) {
            CustomerViewBill customerView = new CustomerViewBill();
            new CustomerBillController(model, customerView);
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

    public static void main(String[] args) {
        new ManageBills();
    }
}
