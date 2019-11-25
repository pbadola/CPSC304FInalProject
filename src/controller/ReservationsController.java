package controller;

import database.DatabaseConnectionHandler;
import model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ReservationsController {

  public static int makeReservation(Reservation reservation) {
    Connection connection;
    PreparedStatement ps = null;
    Random rand = new Random();
    reservation.setConfNo(rand.nextInt(9999999));

    try {
      System.out.println("before get connection");
      connection = DatabaseConnectionHandler.getConnection();
      System.out.println("after get connection");
      if (connection == null) return 0;
      ps = connection.prepareStatement("INSERT INTO Reservations VALUES (?,?,?,?,?,?,?)");
      System.out.println("after sql statement");
      System.out.println(reservation.getConfNo());
      ps.setInt(1, reservation.getConfNo());
      ps.setString(2, reservation.getVtname());
      ps.setString(3, reservation.getDlicense());
      ps.setTimestamp(4, reservation.getFromDateTime());
      ps.setTimestamp(5, reservation.getToDateTime());
      ps.setString(6, reservation.getLocation());
      ps.setString(7, reservation.getCity());
      System.out.println("after all values added");

      ps.executeUpdate();
      System.out.println("after executeupdate");
      connection.commit();
      System.out.println("after commit");
      ps.close();
      System.out.println("after close");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      DatabaseConnectionHandler.rollbackConnection();
      System.out.println("in catch");

    } finally {
      DatabaseConnectionHandler.close();
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      System.out.println("in finally");
    }
    return reservation.getConfNo();
  }

  public static void deleteReservation(int confNo) {
    Connection connection;
    PreparedStatement ps = null;
    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("DELETE FROM Reservations WHERE confNo = ?");
      ps.setInt(1, confNo);

      int rowCount = ps.executeUpdate();
      if (rowCount == 0) {
        System.out.println(
            " ERROR: Reservation with confirmation number " + confNo + " does not exist");
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

  public static Reservation getReservation(int confNo) {
    Connection connection;
    Reservation retReservation = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement("SELECT * FROM Reservations WHERE CONFNO=?");
      ps.setInt(1, confNo);

      rs = ps.executeQuery();
      while (rs.next()) {
        retReservation =
            new Reservation(
                rs.getInt("confNo"),
                rs.getString("vtname"),
                rs.getString("dlicense"),
                rs.getTimestamp("fromDateTime"),
                rs.getTimestamp("toDateTime"),
                rs.getString("location"),
                rs.getString("city"));
      }
      //   System.out.println(retReservation.getConfNo());
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

    return retReservation;
  }
}
