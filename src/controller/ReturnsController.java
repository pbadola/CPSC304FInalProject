package controller;

import database.DatabaseConnectionHandler;
import model.Return;
import model.ReturnReportCount;
import model.Vehicle;

import java.sql.*;
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
      ps.setDouble(5, vReturn.getValue());

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
                rs.getDouble("value"));
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

  public static ArrayList<ReturnReportCount> getDailyReturnCount(
      String date, String location, String city) {
    Connection connection;
    ArrayList<ReturnReportCount> counts = new ArrayList<ReturnReportCount>();
    ReturnReportCount count = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query =
        String.format(
            "SELECT V.city, V.location, V.vtname, COUNT(*) as count, SUM(Re.value) as revenue FROM Returns Re, Vehicles V, Rentals R "
                + "WHERE V.vlicense = R.vlicense AND R.rid = Re.rid AND TO_CHAR(TRUNC(R.fromDateTime), 'yyyy-mm-dd') = '%s'",
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
            new ReturnReportCount(
                rs.getString("city"),
                rs.getString("location"),
                rs.getString("vtname"),
                rs.getInt("count"),
                rs.getDouble("revenue"));
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
                + "FROM Returns Re, Vehicles V, Rentals R"
                + "WHERE V.vlicense = R.vlicense AND R.rid = Re.rid"
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

  public static int calculateValue(Timestamp fromDateTime, Timestamp toDateTime, String vtname) {
    //    long millis = toDateTime.getTime() - fromDateTime.getTime();
    //
    //    Calendar cal = Calendar.getInstance();
    //    cal.setTimeInMillis(millis);
    //
    //    int weeks = cal.get(Calendar.WE) - calFrom.get(Calendar.YEAR);
    //    int days = cal.get(Calendar.YEAR) - calFrom.get(Calendar.YEAR);
    //    int hours = cal.get(Calendar.YEAR) - calFrom.get(Calendar.YEAR);
    //
    //    VehicleType vt = VehicleTypesController.getVehicleType(vtname);
    //    double weeklyRate = vt.getWrate();
    //    double dailyRate = vt.getDrate();
    //    double hourlyRate = vt.getHrate();
    //    double weeklyIns = vt.getWirate();
    //    double dailyIns = vt.getDirate();
    //    double hourlyIns = vt.getHirate();
    //
    //    // TODO: find out how Date and time are dealt with
    //    // int totalTime = toDate.getTime() - fromDate.getTime();
    //    // int weeks =
    //    // int days =
    //    // int hours =
    //    // totalCost = (weeks * (weeklyRate + weeklyIns)) + (days * (dailyRate + dailyIns)) +
    // (hours *
    //    // (hourlyRate + hourlyIns));
    return 0;
  }
}
