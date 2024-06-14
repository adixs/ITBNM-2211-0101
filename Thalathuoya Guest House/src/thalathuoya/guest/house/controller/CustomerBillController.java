package thalathuoya.guest.house.controller;

import thalathuoya.guest.house.model.Billconnector;
import thalathuoya.guest.house.view.CreateViewBill;
import thalathuoya.guest.house.view.CustomerViewBill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBillController {
    private Billconnector model;
    private CreateViewBill createView;
    private CustomerViewBill customerView;

    public CustomerBillController(Billconnector model, CreateViewBill createView) {
        this.model = model;
        this.createView = createView;

        this.createView.saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createBill();
            }
        });
    }

    public CustomerBillController(Billconnector model, CustomerViewBill customerView) {
        this.model = model;
        this.customerView = customerView;

        this.customerView.searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBills();
            }
        });
    }

    private void createBill() {
        String customerName = createView.tfCustomerName.getText();
        String description = createView.tfDescription.getText();
        double amount;

        try {
            amount = Double.parseDouble(createView.tfAmount.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(createView, "Amount must be a valid number");
            return;
        }

        model.createBill(customerName, description, amount);
        JOptionPane.showMessageDialog(createView, "Bill created successfully");
    }

    private void searchBills() {
        String customerName = customerView.tfCustomerName.getText();
        ResultSet rs = model.getBillsByCustomerName(customerName);

        try {
            customerView.taResults.setText("");
            while (rs.next()) {
                String result = String.format("ID: %d\nCustomer Name: %s\nDescription: %s\nAmount: %.2f\n\n",
                        rs.getInt("id"), rs.getString("customer_name"), rs.getString("description"), rs.getDouble("amount"));
                customerView.taResults.append(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
