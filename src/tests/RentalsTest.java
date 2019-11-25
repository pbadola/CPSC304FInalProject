package tests;

import controller.RentalsController;
import controller.ReservationsController;
import model.Rental;
import model.RentalConfirmation;
import model.Reservation;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RentalsTest {
  public static void test() {
    //    Rental r1 =
    //        new Rental(
    //            -1,
    //            "K4H5G2D",
    //            "0000111",
    //            Timestamp.valueOf("2017-06-11 16:10:00"),
    //            Timestamp.valueOf("2019-06-15 09:00:00"),
    //            78656,
    //            "Mastercard",
    //            "389275920888492",
    //            "08/22",
    //            7286515);
    //    System.out.println("Adding a new rental");

    Reservation res1 =
        new Reservation(
            -1,
            "Standard",
            "0000111",
            Timestamp.valueOf("2017-06-11 16:10:00"),
            Timestamp.valueOf("2019-06-15 09:00:00"),
            "525 W Broadway",
            "Vancouver");
    ReservationsController.makeReservation(res1);

    RentalConfirmation rc1 =
        RentalsController.rentVehicle(res1, "Mastercard", "389275920888492", "08/22");

    System.out.println("Getting the rental added to the database");
    Rental check = RentalsController.getRental(rc1.getRid());
    System.out.println(check);
    ArrayList<Rental> rentals = RentalsController.getDailyRental("2019-11-20", null, null);
    if (rentals != null && rentals.isEmpty()) {
      System.out.println("list is empty");
    }
    System.out.println(rentals);

    RentalsController.deleteRental(rc1.getRid());
  }
}
