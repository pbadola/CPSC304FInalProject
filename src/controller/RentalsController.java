package controller;

import database.DatabaseConnectionHandler;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class RentalsController {
  private static int rentCount = 1000;
  private static Random rand;

  private static void addRental(Rental rental) {
    Connection connection;
    PreparedStatement ps = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("INSERT INTO Rentals VALUES (?,?,?,?,?,?,?,?,?,?)");

      ps.setInt(1, rental.getRid());
      ps.setString(2, rental.getVlicense());
      ps.setString(3, rental.getDlicense());
      ps.setTimestamp(4, rental.getFromDateTime());
      ps.setTimestamp(5, rental.getToDateTime());
      ps.setInt(6, rental.getOdometer());
      ps.setString(7, rental.getCardName());
      ps.setString(8, rental.getCardNo());
      ps.setString(9, rental.getExpDate());
      ps.setInt(10, rental.getConfNo());

      ps.executeUpdate();
      connection.commit();
      ps.close();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      DatabaseConnectionHandler.rollbackConnection();

    } finally {
      DatabaseConnectionHandler.close();
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static void deleteRental(int rid) {
    Connection connection;
    PreparedStatement ps = null;
    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("DELETE FROM Rentals WHERE rid = ?");
      ps.setInt(1, rid);

      int rowCount = ps.executeUpdate();
      if (rowCount == 0) {
        System.out.println(" ERROR: Rental with id " + rid + " does not exist");
      }

      connection.commit();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      DatabaseConnectionHandler.rollbackConnection();

    } finally {
      DatabaseConnectionHandler.close();
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static Rental getRental(int rid) {
    Connection connection;
    Rental retRental = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement("SELECT * FROM Rentals WHERE rid=?");
      ps.setInt(1, rid);

      rs = ps.executeQuery();
      while (rs.next()) {
        retRental =
            new Rental(
                rs.getInt("rid"),
                rs.getString("vlicense"),
                rs.getString("dlicense"),
                rs.getTimestamp("fromDateTime"),
                rs.getTimestamp("toDateTime"),
                rs.getInt("odometer"),
                rs.getString("cardName"),
                rs.getString("cardNo"),
                rs.getString("expDate"),
                rs.getInt("confNo"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      DatabaseConnectionHandler.rollbackConnection();
    } finally {
      DatabaseConnectionHandler.close();
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    return retRental;
  }

  public static ArrayList<RentalReportCount> getDailyRentalCount(
      String date, String location, String city) {
    Connection connection;
    ArrayList<RentalReportCount> counts = new ArrayList<RentalReportCount>();
    RentalReportCount count = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query =
        String.format(
            "SELECT V.city, V.location, V.vtname, COUNT(*) as count FROM Rentals R, Vehicles V "
                + "WHERE V.vlicense = R.vlicense AND TO_CHAR(TRUNC(R.fromDateTime), 'yyyy-mm-dd') = '%s'",
            date);

    if (location != null && city != null) {
      query += String.format(" AND V.city = '%s' AND V.location = '%s'", city, location);
    }

    query += " GROUP BY V.location, V.city, V.vtname ORDER BY V.location, V.city, V.vtname";

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement(query);

      rs = ps.executeQuery();
      System.out.println(rs);

      while (rs.next()) {
        count =
            new RentalReportCount(
                rs.getString("city"),
                rs.getString("location"),
                rs.getString("vtname"),
                rs.getInt("count"));
        counts.add(count);
      }
    } catch (SQLException e) {
      System.out.println("error");
      System.out.println(e.getMessage());
      DatabaseConnectionHandler.rollbackConnection();
    } finally {
      DatabaseConnectionHandler.close();
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        System.out.println("error");
        System.out.println(e.getMessage());
      }
    }

    return counts;
  }

  public static ArrayList<Vehicle> getDailyRentalInfo(String date, String location, String city) {
    Connection connection;
    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query =
        String.format(
            "SELECT V.* "
                + "FROM Rentals R, Vehicles V "
                + "WHERE V.vlicense = R.vlicense "
                + "AND TO_CHAR(TRUNC(R.fromDateTime), 'yyyy-mm-dd') = '%s'",
            date);

    if (location != null && city != null) {
      query += String.format(" AND V.city = '%s' AND V.location = '%s'", city, location);
    }

    query += " ORDER BY V.location, V.city, V.vtname";

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement(query);

      rs = ps.executeQuery();
      System.out.println(rs);

      while (rs.next()) {
        Vehicle v =
            new Vehicle(
                rs.getString("vlicense"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getInt("year"),
                rs.getString("color"),
                rs.getInt("odometer"),
                rs.getString("status"),
                rs.getString("vtname"),
                rs.getString("location"),
                rs.getString("city"));
        vehicles.add(v);
      }
    } catch (SQLException e) {
      System.out.println("error");
      System.out.println(e.getMessage());
      DatabaseConnectionHandler.rollbackConnection();
    } finally {
      DatabaseConnectionHandler.close();
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        System.out.println("error");
        System.out.println(e.getMessage());
      }
    }

    return vehicles;
  }

  public static RentalConfirmation rentVehicle(
      Reservation reservation, String cardName, String cardNo, String expDate) {
    RentalConfirmation confirmation = null;

    rand = new Random();
    String vtname, location, city;
    ArrayList<Vehicle> availableVehicles;
    Timestamp fromDateTime, toDateTime;
    Vehicle rentedVehicle = null;

    int confNo = reservation.getConfNo();
    vtname = reservation.getVtname();
    location = reservation.getLocation();
    city = reservation.getCity();
    fromDateTime = reservation.getFromDateTime();
    toDateTime = reservation.getToDateTime();

    availableVehicles =
        VehiclesController.getAvailableVehicles(vtname, location, city, fromDateTime, toDateTime);
    if (availableVehicles != null && !availableVehicles.isEmpty()) {
      rentedVehicle = availableVehicles.get(0);
      Rental rental =
          new Rental(
              rand.nextInt(9999999),
              rentedVehicle.getVlicense(),
              reservation.getDlicense(),
              fromDateTime,
              toDateTime,
              rentedVehicle.getOdometer(),
              cardName,
              cardNo,
              expDate,
              confNo);
      addRental(rental);

      // TODO: SHOULD THIS BE IN A TRY CATCH? HOW ARE WE HANDLING IF THIS IS AN ERROR?
      VehiclesController.changeStatus(rentedVehicle.getVlicense(), "Rented");

      Vehicle conf = VehiclesController.getVehicle(rentedVehicle.getVlicense());
      String status = conf.getStatus();
      if (status != "Rented") {
        // TODO: DO WE KEEP CALLING CHANGESTATUS? OR DO WE DELETE THE
        // ENTRY FROM RENTAL TABLE AND RETURN NULL SO THAT THEY START OVER THE PROCESS?

      } else {
        System.out.println("Vehicle has been rented");
      }

      confirmation = new RentalConfirmation(rental.getRid(), rentedVehicle);
    }

    return confirmation;
  }
}
