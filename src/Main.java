import java.sql.*;
import database.DatabaseConnectionHandler;
import controller.CustomersController;
import model.Customer;
import model.Reservation;
import controller.ReservationsController;

public class Main {
    private static DatabaseConnectionHandler db = null;

    public static void main(String[] args) {
        DatabaseConnectionHandler.login();

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

        Reservation r1 = new Reservation(-1, "Economy", "1234567", "1",
                "M", "1", "f");

        int i = ReservationsController.makeReservation(r1);
        Reservation rtest = ReservationsController.getReservation(i);
        System.out.println(rtest);
        ReservationsController.deleteReservation(i);
        Reservation rtest2 = ReservationsController.getReservation(i);
        System.out.println(rtest2);


    }
}
