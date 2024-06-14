package thalathuoya.guest.house;

import javax.swing.*;
import java.awt.*;

public class ViewBookings extends JFrame {

    public ViewBookings() {
        setTitle("View Bookings");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea bookingsArea = new JTextArea();
        bookingsArea.setEditable(false);
        bookingsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        
        bookingsArea.setText("Booking 1: Room 101\nBooking 2: Room 102\nBooking 3: Room 103");

        JScrollPane scrollPane = new JScrollPane(bookingsArea);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewBookings();
    }
}
