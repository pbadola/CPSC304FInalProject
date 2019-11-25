import database.DatabaseConnectionHandler;
import model.VehicleType;
<<<<<<< HEAD
import tests.CustomersTest;
import tests.ReservationsTest;
import tests.VehicleTypesTest;
import tests.VehiclesTest;
=======
import tests.*;
>>>>>>> 333a18a4401d19d35f528b91366991d3bd3df620

public class Main {
  private static DatabaseConnectionHandler db = null;

  public static void main(String[] args) {
    DatabaseConnectionHandler.login();

//    VehiclesTest.test();
//    CustomersTest.test();
//    ReservationsTest.test();
<<<<<<< HEAD
    VehicleTypesTest.test();
=======
   // VehicleTypesTest.test();
    RentalsTest.test();
>>>>>>> 333a18a4401d19d35f528b91366991d3bd3df620
  }
}
