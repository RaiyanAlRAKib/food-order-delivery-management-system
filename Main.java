import java.sql.*;
import java.lang.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/food_order_and_delivery_management_system";
        String user = "root";
        String db_pass = "";
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, db_pass);
            while (true) {
                System.out.println("\n1. Add Customer");
                System.out.println("2. View Customers");
                System.out.println("3. Add Food Item");
                System.out.println("4. View Food Items");
                System.out.println("5. Place Order");
                System.out.println("6. View Orders");
                System.out.println("7. Update Order Status");
                System.out.println("8. Delete Customer");
                System.out.println("9. Delete Food Item");
                System.out.println("10. Delete Order");
                System.out.println("11. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = sc.nextLine();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        String sql = "INSERT INTO Customers(name, phone, address) VALUES (?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2, phone);
                        ps.setString(3, address);
                        ps.executeUpdate();
                        System.out.println("Customer added!");
                        break;

                    case 2:
                        String showCustSql = "SELECT * FROM Customers";
                        PreparedStatement ps2 = con.prepareStatement(showCustSql);
                        ResultSet rs = ps2.executeQuery();
                        System.out.println("\n--- Customer List ---");
                        while (rs.next()) {
                            System.out.println(rs.getInt("customer_id") + " | " + rs.getString("name") + " | "
                                    + rs.getString("phone") + " | " + rs.getString("address"));
                        }
                        break;

                    case 3:
                        System.out.print("Enter Food Name: ");
                        String foodName = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Category: ");
                        String category = sc.nextLine();

                        String foodSql = "INSERT INTO Food_Items(name, price, category) VALUES (?, ?, ?)";
                        PreparedStatement ps3 = con.prepareStatement(foodSql);
                        ps3.setString(1, foodName);
                        ps3.setDouble(2, price);
                        ps3.setString(3, category);
                        ps3.executeUpdate();
                        System.out.println("Food item added!");
                        break;

                    case 4:
                        String showFoodSql = "SELECT * FROM Food_Items";
                        PreparedStatement ps4 = con.prepareStatement(showFoodSql);
                        ResultSet rs2 = ps4.executeQuery();
                        System.out.println("\n--- Food Item List ---");
                        while (rs2.next()) {
                            System.out.println(rs2.getInt("food_id") + " | " + rs2.getString("name") + " | "
                                    + rs2.getDouble("price") + " | " + rs2.getString("category"));
                        }
                        break;

                    case 5:
                        System.out.print("Enter Customer ID: ");
                        int custId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Total Amount: ");
                        double totalAmount = sc.nextDouble();
                        sc.nextLine();

                        String orderSql = "INSERT INTO Orders(customer_id, total_amount) VALUES (?, ?)";
                        PreparedStatement ps5 = con.prepareStatement(orderSql);
                        ps5.setInt(1, custId);
                        ps5.setDouble(2, totalAmount);
                        ps5.executeUpdate();
                        System.out.println("Order placed!");
                        break;

                    case 6:
                        String showOrderSql = "SELECT * FROM Orders";
                        PreparedStatement ps6 = con.prepareStatement(showOrderSql);
                        ResultSet rs3 = ps6.executeQuery();
                        System.out.println("\n--- Order List ---");
                        while (rs3.next()) {
                            System.out.println(rs3.getInt("order_id") + " | " + rs3.getInt("customer_id") + " | "
                                    + rs3.getTimestamp("order_date") + " | " + rs3.getDouble("total_amount") + " | "
                                    + rs3.getString("status"));
                        }
                        break;

                    case 7:
                        System.out.print("Enter Order ID: ");
                        int orderId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Status: ");
                        String status = sc.nextLine();

                        String updateSql = "UPDATE Orders SET status = ? WHERE order_id = ?";
                        PreparedStatement ps7 = con.prepareStatement(updateSql);
                        ps7.setString(1, status);
                        ps7.setInt(2, orderId);
                        ps7.executeUpdate();
                        System.out.println("Order status updated!");
                        break;

                    case 8:
                        System.out.print("Enter Customer ID to delete: ");
                        int delCustId = sc.nextInt();
                        sc.nextLine();

                        String delCustSql = "DELETE FROM Customers WHERE customer_id = ?";
                        PreparedStatement ps8 = con.prepareStatement(delCustSql);
                        ps8.setInt(1, delCustId);
                        ps8.executeUpdate();
                        System.out.println("Customer deleted!");
                        break;

                    case 9:
                        System.out.print("Enter Food ID to delete: ");
                        int delFoodId = sc.nextInt();
                        sc.nextLine();

                        String delFoodSql = "DELETE FROM Food_Items WHERE food_id = ?";
                        PreparedStatement ps9 = con.prepareStatement(delFoodSql);
                        ps9.setInt(1, delFoodId);
                        ps9.executeUpdate();
                        System.out.println("Food item deleted!");
                        break;

                    case 10:
                        System.out.print("Enter Order ID: ");
                        int delOrderId = sc.nextInt();
                        sc.nextLine();

                        String delOrderSql = "DELETE FROM Orders WHERE order_id = ?";
                        PreparedStatement ps10 = con.prepareStatement(delOrderSql);
                        ps10.setInt(1, delOrderId);
                        ps10.executeUpdate();
                        System.out.println("Order deleted!");
                        break;

                    case 11:
                        System.out.println("Program exited.");
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice, try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}