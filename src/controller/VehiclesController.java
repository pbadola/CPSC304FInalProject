package controller;

import database.DatabaseConnectionHandler;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
      ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?)");
      ps.setString(1, vehicle.getVlicense());
      ps.setString(2, vehicle.getMake());
      ps.setString(3, vehicle.getModel());
      ps.setInt(4, vehicle.getYear());
      ps.setString(5, vehicle.getColor());
      ps.setInt(6, vehicle.getOdometer());
      ps.setString(7, vehicle.getStatus());
      ps.setString(8, vehicle.getVtname());
      ps.setString(9, vehicle.getLocation());
      ps.setString(10, vehicle.getLocation());

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

  // TODO: what about city?
  // TODO: time intervals????????????
  public static ArrayList<Vehicle> getAvailableVehicles(String vtname, String location) {
    Connection connection;
    ArrayList<Vehicle> retVehicles = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query = "SELECT * FROM Vehicles WHERE status = 'Available'";
    if (vtname != null) {
      query += " AND vtname = " + vtname;
    }
    if (location != null) {
      query += " AND location = " + location;
    }
    // TODO: what should we sort by?
    query += " ORDER BY year desc";

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
