package thalathuoya.guest.house.model;

public class Bill {
    private int id;
    private String customerName;
    private String description;
    private double amount;

    public Bill(int id, String customerName, String description, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.description = description;
        this.amount = amount;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
