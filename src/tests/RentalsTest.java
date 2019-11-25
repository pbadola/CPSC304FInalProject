package tests;

import controller.RentalsController;
import model.Rental;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RentalsTest {
  public static void test() {
    Rental r1 =
        new Rental(
            14445434,
            "K4H5G2D",
            "0000111",
            Timestamp.valueOf("2017-06-11 16:10:00"),
            Timestamp.valueOf("2019-06-15 09:00:00"),
            78656,
            "Mastercard",
            "389275920888492",
            "08/22",
            7286515);
    System.out.println("Adding a new rental");
    RentalsController.addRental(r1);

    System.out.println("Getting the rental added to the database");
    Rental check = RentalsController.getRental(r1.getRid());
    System.out.println(check);
    ArrayList<Rental> rentals = RentalsController.getDailyRental("2019-11-20", null, null);
    if (rentals.isEmpty()) {
      System.out.println("list is empty");
    }
    System.out.println(rentals);
  }
}
