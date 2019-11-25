import database.DatabaseConnectionHandler;
import tests.RentalsTest;
import ui.MainWindow;
import ui.AvailableVehicles;
import ui.RentVehicleUI;
import ui.ReservationUI;

public class Main extends MainWindow{
  private static DatabaseConnectionHandler db = null;

  public static void main(String[] args) {
    DatabaseConnectionHandler.login();

    // VehiclesTest.test();
    // CustomersTest.test();
    // ReservationsTest.test();
    // VehicleTypesTest.test();
    // VehicleTypesTest.test();
    //RentalsTest.test();
    System.out.println("before mw");
    MainWindow mw = new MainWindow();
    System.out.println("after mw");
    mw.showFrame();
    System.out.println("after showframe");
  }
}
