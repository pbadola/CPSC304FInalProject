package controller;

import database.DatabaseConnectionHandler;
import model.Return;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// import javafx.scene.control.CustomMenuItem;

public class ReturnsController {
  public static void addReturn(Return vReturn) {
    Connection connection;
    PreparedStatement ps = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("INSERT INTO Returns VALUES (?,?,?,?,?)");
      ps.setInt(1, vReturn.getRid());
      ps.setTimestamp(2, vReturn.getDateTime());
      ps.setInt(3, vReturn.getOdometer());
      ps.setInt(4, vReturn.isFulltank());
      ps.setFloat(5, vReturn.getValue());

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

  public static void deleteReturn(int rid) {
    Connection connection;
    PreparedStatement ps = null;
    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("DELETE FROM Returns WHERE rid = ?");
      ps.setInt(1, rid);

      int rowCount = ps.executeUpdate();
      if (rowCount == 0) {
        System.out.println(" ERROR: Return with with return id " + rid + " does not exist");
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

  public static Return getReturn(int rid) {
    Connection connection;
    Return retReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement("SELECT * FROM Returns WHERE rid=?");
      ps.setInt(1, rid);

      rs = ps.executeQuery();
      while (rs.next()) {
        retReturn =
            new Return(
                rs.getInt("rid"),
                rs.getTimestamp("dateTime"),
                rs.getInt("odometer"),
                rs.getInt("fulltank"),
                rs.getFloat("value"));
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

    return retReturn;
  }

  public static ArrayList<Return> getDailyReturn(String date, String location, String city) {
    Connection connection;
    Return rReturn = null;
    ArrayList<Return> retReturn = new ArrayList<Return>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    // TODO: This query is incorrect. It does not do what is expected
    String query = "SELECT * ";
    if (location != null && city != null) {
      query =
          query
              + "FROM Returns R, Rentals Re, Vehicles V "
              + "WHERE TRUNC(R.dateTime) = "
              + date
              + " AND Re.vlicense = V.vlicense AND V.city = "
              + city
              + " AND R.rid = Re.rid AND V.location = "
              + location;
    } else {
      query = query + "FROM Returns WHERE dateTime =" + date;
    }

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement(query);

      rs = ps.executeQuery();
      while (rs.next()) {
        rReturn =
            new Return(
                rs.getInt("rid"),
                rs.getTimestamp("dateTime"),
                rs.getInt("odometer"),
                rs.getInt("fulltank"),
                rs.getFloat("value"));
        retReturn.add(rReturn);
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

    return retReturn;
  }

  public int calculateValue() {
    return 0;
  }
}
