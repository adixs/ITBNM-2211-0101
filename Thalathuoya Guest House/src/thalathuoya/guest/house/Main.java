package thalathuoya.guest.house;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your role (customer/admin/employee): ");
        String role = scanner.nextLine().trim().toLowerCase();

        switch (role) {
            case "customer":
                new CustomerDashboard();
                break;
            case "admin":
                new Dashboard();
                break;
            case "employee":
                new EmployeeDashboard("employee");
                break;
            default:
                System.out.println("Invalid role");
        }
        
        scanner.close();
    }
}
