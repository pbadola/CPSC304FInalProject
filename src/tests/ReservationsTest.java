package tests;

import controller.ReservationsController;
import model.Reservation;

public class ReservationsTest {
  public static void test() {
    Reservation r1 = new Reservation(-1, "Economy", "1234567", "1", "M", "1", "f", "la","lo");

    int i = ReservationsController.makeReservation(r1);
    Reservation rtest = ReservationsController.getReservation(i);
    System.out.println(rtest);
    ReservationsController.deleteReservation(i);
    Reservation rtest2 = ReservationsController.getReservation(i);
    System.out.println(rtest2);
  }
}
