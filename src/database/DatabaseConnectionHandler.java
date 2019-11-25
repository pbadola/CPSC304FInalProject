package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionHandler {
  private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
  private static final String EXCEPTION_TAG = "[EXCEPTION]";
  private static final String WARNING_TAG = "[WARNING]";

  private static Connection connection = null;

  public static Connection getConnection() {
    try {
      connection = DriverManager.getConnection(ORACLE_URL, "ora_ruptor", "a57017162");
      connection.setAutoCommit(false);
      return connection;
    } catch (SQLException e) {
      System.out.println("Error getting the connection");
      return null;
    }
  }

  public static void close() {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      System.out.println(EXCEPTION_TAG + " " + e.getMessage());
    }
  }

  public static boolean login() {
    try {
      if (connection != null) {
        connection.close();
      }
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      return true;
    } catch (SQLException e) {
      System.out.println(EXCEPTION_TAG + " " + e.getMessage());
      return false;
    }
  }

  public static void rollbackConnection() {
    try {
      if (connection != null) {
        connection.rollback();
      }
    } catch (SQLException e) {
      System.out.println(EXCEPTION_TAG + " " + e.getMessage());
    }
  }
}
