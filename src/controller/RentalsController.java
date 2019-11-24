package controller;

import database.DatabaseConnectionHandler;
import model.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalsController {

  public static void addRental(Rental rental) {
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
      ps.setInt(8, rental.getCardNo());
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
                rs.getInt("cardNo"),
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

  public static ArrayList<Rental> getDailyRental(String date, String location, String city) {
    Connection connection;
    ArrayList<Rental> retRental = new ArrayList<Rental>();
    Rental rental = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // TODO: This query is incorrect. It does not do what is expected
    String query = "SELECT * ";
    if (location != null && city != null) {
      query =
          query
              + "FROM Rentals R, Vehicles V "
              + "WHERE R.fromDateTime =  "
              + date
              + " AND R.vlicense = V.vlicense AND V.city = "
              + city
              + " AND V.location = "
              + location;
    } else {
      query = query + "FROM Rentals WHERE fromDateTime=" + date;
    }

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement(query);

      rs = ps.executeQuery();
      while (rs.next()) {
        rental =
            new Rental(
                rs.getInt("rid"),
                rs.getString("vlicense"),
                rs.getString("dlicense"),
                rs.getTimestamp("fromDateTime"),
                rs.getTimestamp("toDateTime"),
                rs.getInt("odometer"),
                rs.getString("cardName"),
                rs.getInt("cardNo"),
                rs.getString("expDate"),
                rs.getInt("confNo"));
        retRental.add(rental);
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
}
