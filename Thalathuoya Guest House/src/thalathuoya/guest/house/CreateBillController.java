package thalathuoya.guest.house;

import thalathuoya.guest.house.model.Bill;
import thalathuoya.guest.house.model.Billconnector;
import thalathuoya.guest.house.view.CreateViewBill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBillController {
    private Billconnector model;
    private CreateViewBill view;

    public CreateBillController(Billconnector model, CreateViewBill view) {
        this.model = model;
        this.view = view;

        this.view.saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBill();
            }
        });
    }

    private void createBill() {
        try {
            String customerName = view.tfCustomerName.getText();
            String description = view.tfDescription.getText();
            double amount = Double.parseDouble(view.tfAmount.getText());

            Bill bill = new Bill(0, customerName, description, amount);
            model.createBill(customerName, description, amount);
            JOptionPane.showMessageDialog(null, "Bill created successfully");

            view.tfCustomerName.setText("");
            view.tfDescription.setText("");
            view.tfAmount.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to create bill");
        }
    }
}
