import java.sql.*;
import database.DatabaseConnectionHandler;
import controller.CustomersController;
import model.Customer;

public class Main {
    private static DatabaseConnectionHandler db = null;

    public static void main(String[] args) {
        DatabaseConnectionHandler.login();

        CustomersController.deleteCustomer("1234567");
        CustomersController.deleteCustomer("1234568");
        CustomersController.deleteCustomer("5634563");

        Customer c1 = new Customer(356345634, "Patrishka Badola", "Dumpster", "1234567");
        CustomersController.addCustomer(c1);

        Customer c2 = new Customer(356234234, "Ruperto Torres", "Hell", "1234568");
        CustomersController.addCustomer(c2);

        Customer test = CustomersController.getCustomer("1234568");
        System.out.println(test);
        Customer test2 = CustomersController.getCustomer("1234567");
        System.out.println(test2);

        CustomersController.deleteCustomer("1234568");
        Customer test3 = CustomersController.getCustomer("1234568");
        System.out.println(test3);

    }
}
