package tests;

import controller.VehiclesController;
import model.Vehicle;

import java.sql.Timestamp;

public class VehiclesTest {
  public static void test() {
    Vehicle v1 =
        new Vehicle(
            "license",
            "fake make",
            "fake model",
            1111,
            "fake color",
            12233,
            "Available",
            "Compact",
            "800 Robson St",
            "Vancouver");

    VehiclesController.addVehicle(v1);

    VehiclesController.getVehicle(v1.getVlicense());

    System.out.println("All Available Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles(null, null, null, null, null));

    System.out.println("Available Standard Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles("Standard", null, null, null, null));

    System.out.println("Available Economy Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles("Economy", null, null, null, null));

    System.out.println("Available Compact Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles("Compact", null, null, null, null));

    System.out.println("Available Standard Vehicles in 4700 Kingsway, Burnaby");
    System.out.println(
        VehiclesController.getAvailableVehicles(
            "Standard", "4700 Kingsway", "Burnaby", null, null));

    System.out.println("Available Standard Vehicles in 800 Robson St, Vancouver");
    System.out.println(
        VehiclesController.getAvailableVehicles(
            "Standard", "800 Robson St", "Vancouver", null, null));

    System.out.println("Available Vehicles in 4700 Kingsway, Burnaby");
    System.out.println(
        VehiclesController.getAvailableVehicles(null, "4700 Kingsway", "Burnaby", null, null));

    System.out.println("Available Vehicles in 800 Robson St, Vancouver");
    System.out.println(
        VehiclesController.getAvailableVehicles(null, "800 Robson St", "Vancouver", null, null));

    VehiclesController.deleteVehicle(v1.getVlicense());

    System.out.println(VehiclesController.getVehicle(v1.getVlicense()));

    System.out.println("All Available Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles(null, null, null, null, null));

    Vehicle v2 =
        new Vehicle(
            "lfd9322",
            "lakdjfaomake",
            "fake model",
            9999,
            "fake color",
            12233,
            "Available",
            "Economy",
            "800 Robson St",
            "Vancouver");

    VehiclesController.addVehicle(v2);

    System.out.println("All Available Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles(null, null, null, null, null));

    VehiclesController.changeStatus(v2.getVlicense(), "Rented");

    System.out.println("All Available Vehicles");
    System.out.println(VehiclesController.getAvailableVehicles(null, null, null, null, null));

    VehiclesController.deleteVehicle(v2.getVlicense());

    System.out.println(VehiclesController.getVehicle(v2.getVlicense()));

    System.out.println(
        "=============================== DATE & TIME TESTS ==================================");

    System.out.println(Timestamp.valueOf("2018-06-18 13:30:00"));

    System.out.println(
        VehiclesController.getAvailableVehicles(
            null,
            null,
            null,
            Timestamp.valueOf("2018-06-18 13:30:00"),
            Timestamp.valueOf("2018-06-20 13:30:00")));

    System.out.println(
        VehiclesController.getAvailableVehicles(
            null,
            null,
            null,
            Timestamp.valueOf("2018-05-21 13:30:00"),
            Timestamp.valueOf("2018-05-25 13:30:00")));

    System.out.println(
        VehiclesController.getAvailableVehicles(
            null,
            null,
            null,
            Timestamp.valueOf("2018-05-21 13:30:00"),
            Timestamp.valueOf("2018-05-23 14:30:00")));

    System.out.println(
        VehiclesController.getAvailableVehicles(
            null,
            null,
            null,
            Timestamp.valueOf("2018-05-23 13:30:00"),
            Timestamp.valueOf("2018-05-23 14:30:00")));

    System.out.println(
        VehiclesController.getAvailableVehicles(
            null,
            null,
            null,
            Timestamp.valueOf("2018-05-23 13:30:00"),
            Timestamp.valueOf("2018-05-25 14:30:00")));
  }
}
