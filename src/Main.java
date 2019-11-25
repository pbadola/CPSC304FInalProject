import database.DatabaseConnectionHandler;
import tests.RentalsTest;

public class Main {
  private static DatabaseConnectionHandler db = null;

  public static void main(String[] args) {
    DatabaseConnectionHandler.login();

    // VehiclesTest.test();
    // CustomersTest.test();
    // ReservationsTest.test();
    // VehicleTypesTest.test();
    // VehicleTypesTest.test();
    RentalsTest.test();
  }
}
