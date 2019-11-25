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
    MainWindow mw = new MainWindow();
    mw.showFrame();
  }
}
