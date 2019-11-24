import database.DatabaseConnectionHandler;
import model.VehicleType;
import tests.*;

public class Main {
  private static DatabaseConnectionHandler db = null;

  public static void main(String[] args) {
    DatabaseConnectionHandler.login();

//    VehiclesTest.test();
//    CustomersTest.test();
//    ReservationsTest.test();
   // VehicleTypesTest.test();
    RentalsTest.test();
  }
}
