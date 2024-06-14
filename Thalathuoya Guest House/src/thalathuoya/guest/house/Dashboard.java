package thalathuoya.guest.house;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    Dashboard() {
        setBounds(0, 0, 1090, 566);
        setLayout(null);
        setTitle("Thalathuoya Guest House Dashboard");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1080, 566, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1080, 566);
        add(image);

        JLabel text = new JLabel("Thalathuoya Guest House Welcomes You");
        text.setBounds(100, 80, 1000, 50);
        text.setFont(new Font("Tahoma", Font.BOLD, 40));
        text.setForeground(Color.WHITE);
        image.add(text);

        JLabel description = new JLabel("<html><center>Experience the comfort and hospitality at Thalathuoya Guest House. Enjoy a serene environment with top-notch services, luxurious rooms, and delicious food.</center></html>", SwingConstants.CENTER);
        description.setBounds(190, 150, 700, 50);
        description.setFont(new Font("Tahoma", Font.PLAIN, 16));
        description.setForeground(Color.WHITE);
        image.add(description);
        
        
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1080, 30);
        mb.setBackground(new Color(51, 153, 255));
        mb.setOpaque(true);
        mb.setBorderPainted(false);
        image.add(mb);

        JMenu admin = new JMenu("Admin");
        admin.setForeground(Color.BLACK);
        admin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mb.add(admin);

        JMenuItem addemployee = new JMenuItem("Add Employee");
        JMenuItem addrooms = new JMenuItem("Add Rooms");
        JMenuItem adddrivers = new JMenuItem("Add Drivers");
        JMenuItem addfoodmenu = new JMenuItem("Add Food Menu");
        JMenuItem manageUsers = new JMenuItem("Manage Users");
        JMenuItem manageBills = new JMenuItem("Manage Bills"); 

        addemployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addrooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
        adddrivers.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addfoodmenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        manageUsers.setFont(new Font("Tahoma", Font.PLAIN, 14));
        manageBills.setFont(new Font("Tahoma", Font.PLAIN, 14)); 

        admin.add(addemployee);
        admin.add(addrooms);
        admin.add(adddrivers);
        admin.add(addfoodmenu);
        admin.add(manageUsers);
        admin.add(manageBills); 

        addemployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeDashboard("admin");
            }
        });

        addrooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rooms();
            }
        });

        adddrivers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Drivers();
            }
        });

        addfoodmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FoodMenu();
            }
        });

        manageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserManagementDashboard();
            }
        });

        manageBills.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageBills();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
