package controller;

import database.DatabaseConnectionHandler;
import model.Vehicle;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// TODO: Validation and error handling
// TODO: should probably throw exception when connection == null

public class VehiclesController {

  public static void addVehicle(Vehicle vehicle) {
    Connection connection;
    PreparedStatement ps = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("INSERT INTO Vehicles VALUES (?,?,?,?,?,?,?,?,?,?)");
      ps.setString(1, vehicle.getVlicense());
      ps.setString(2, vehicle.getMake());
      ps.setString(3, vehicle.getModel());
      ps.setInt(4, vehicle.getYear());
      ps.setString(5, vehicle.getColor());
      ps.setInt(6, vehicle.getOdometer());
      ps.setString(7, vehicle.getStatus());
      ps.setString(8, vehicle.getVtname());
      ps.setString(9, vehicle.getLocation());
      ps.setString(10, vehicle.getCity());

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

  public static void deleteVehicle(String vlicense) {
    Connection connection;
    PreparedStatement ps = null;
    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("DELETE FROM Vehicles WHERE vlicense = ?");
      ps.setString(1, vlicense);

      int rowCount = ps.executeUpdate();
      if (rowCount == 0) {
        System.out.println(" ERROR: Vehicle with license " + vlicense + " does not exist");
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

  public static Vehicle getVehicle(String vlicense) {
    Connection connection;
    Vehicle retVehicle = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement("SELECT * FROM Vehicles WHERE vlicense=?");
      ps.setString(1, vlicense);

      rs = ps.executeQuery();
      while (rs.next()) {
        retVehicle =
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

    return retVehicle;
  }

  public static ArrayList<Vehicle> getAvailableVehicles(
      String vtname, String location, String city, Timestamp fromDateTime, Timestamp toDateTime) {
    Connection connection;
    ArrayList<Vehicle> retVehicles = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String query = "SELECT * FROM Vehicles V";

    if (fromDateTime != null && toDateTime != null) {
      query +=
          String.format(
              " WHERE NOT EXISTS "
                  + "(SELECT 1 FROM Rentals R "
                  + "WHERE V.vlicense = R.vlicense "
                  + "AND (TO_DATE('%s', 'yyyy-mm-dd hh24:mi:ss') BETWEEN R.fromDateTime AND R.toDateTime "
                  + "OR TO_DATE('%s', 'yyyy-mm-dd hh24:mi:ss') BETWEEN R.fromDateTime AND R.toDateTime OR "
                  + "(TO_DATE('%s', 'yyyy-mm-dd hh24:mi:ss') <= R.fromDateTime AND TO_DATE('%s', 'yyyy-mm-dd hh24:mi:ss') >= R.toDateTime)))",
              formatter.format(fromDateTime),
              formatter.format(toDateTime),
              formatter.format(fromDateTime),
              formatter.format(toDateTime));
    } else {
      // show currently available vehicles if time period is not provided
      query += " WHERE V.status = 'Available'";
    }

    if (location != null && city != null) {
      query += String.format(" AND V.location = '%s' AND V.city = '%s'", location, city);
    }
    // Actually don't know if I should do this
    //    else {
    //      // default branch
    //      query += " AND V.location = '800 Robson St' AND V.city = 'Vancouver'";
    //    }

    if (vtname != null) {
      query += String.format(" AND V.vtname = '%s'", vtname);
    }

    // TODO: todo add make and model?
    query += " ORDER BY V.year desc";

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return null;
      ps = connection.prepareStatement(query);

      rs = ps.executeQuery();
      while (rs.next()) {
        retVehicles.add(
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
                rs.getString("city")));
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

    return retVehicles;
  }

  public static void changeStatus(String vlicense, String newStatus) {
    Connection connection;
    PreparedStatement ps = null;

    try {
      connection = DatabaseConnectionHandler.getConnection();
      if (connection == null) return;
      ps = connection.prepareStatement("UPDATE Vehicles SET status = ? WHERE vlicense = ?");
      ps.setString(1, newStatus);
      ps.setString(2, vlicense);

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
}
