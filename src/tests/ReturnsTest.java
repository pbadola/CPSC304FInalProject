package tests;

import controller.RentalsController;
import controller.ReturnsController;
import model.Rental;
import model.Return;

public class ReturnsTest {
  public static void test() {
    Return r1 = new Return(14445434, "2019-08-27", "09:00", 73464, 1, 0);

    Rental rt = RentalsController.getRental(r1.getRid());
    String fromDate = rt.getFromDate();

    // TODO: When you figure out the date stuff, enter this
    System.out.println("Calculating the cost");
    // double cost =  ReturnsController.calculateValue(null, null, null);
    System.out.println("Setting the value");
    // r1.setValue(cost);

    System.out.println("Adding a new return");
    ReturnsController.addReturn(r1);

    System.out.println("Getting the return added to the database");
    Rental check = RentalsController.getRental(r1.getRid());
    System.out.println(check);

    //       // ArrayList<Rental> rentals = RentalsController.getDailyRental("2019-11-20", null,
    // null);
    //        if (rentals.isEmpty()){
    //            System.out.println("list is empty");
    //        }
    //        System.out.println(rentals);
  }
}
