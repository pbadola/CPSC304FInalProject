package tests;

import controller.ReservationsController;
import model.Reservation;

import java.sql.Timestamp;

public class ReservationsTest {
  public static void test() {
    Reservation r1 =
        new Reservation(
            -1,
            "Economy",
            "1982451",
            Timestamp.valueOf("2019-01-18 13:30:00"),
            Timestamp.valueOf("2019-01-28 13:30:00"),
            "la",
            "lo");

    int i = ReservationsController.makeReservation(r1);
    Reservation rtest = ReservationsController.getReservation(i);
    System.out.println(rtest);
    ReservationsController.deleteReservation(i);
    Reservation rtest2 = ReservationsController.getReservation(i);
    System.out.println(rtest2);
  }
}
