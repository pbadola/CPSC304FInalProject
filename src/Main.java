import database.DatabaseConnectionHandler;
import tests.CustomersTest;
import tests.ReservationsTest;
import tests.VehiclesTest;
import ui.MainWindow;

public class Main {
  private static DatabaseConnectionHandler db = null;

  public static void main(String[] args) {
    DatabaseConnectionHandler.login();

//    VehiclesTest.test();
//    CustomersTest.test();
//    ReservationsTest.test();

    MainWindow mw = new MainWindow();
    mw.showFrame();
  }
}
