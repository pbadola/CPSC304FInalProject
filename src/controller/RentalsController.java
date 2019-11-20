package controller;

import model.Rental;
import model.TimeInterval;
import database.DatabaseConnectionHandler;
//import javafx.scene.control.CustomMenuItem;
import model.Customer;
import java.sql.*;
public class RentalsController {

    public static void addRental(Rental rental) {
        Connection connection;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("INSERT INTO Rentals VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, rental.getRid());
            ps.setString(2, rental.getVlicense());
            ps.setString(3, rental.getDlicense());
            ps.setString(4, rental.getFromDate());
            ps.setString(5, rental.getFromTime());
            ps.setString(6, rental.getToDate());
            ps.setString(7, rental.getToTime());
            ps.setInt(8, rental.getOdometer());
            ps.setString(9, rental.getCardName());
            ps.setInt(10, rental.getCardNo());
            ps.setString(11, rental.getExpDate());
            ps.setInt(12, rental.getConfNo());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            DatabaseConnectionHandler.rollbackConnection();

        } finally {
            DatabaseConnectionHandler.close();
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {System.out.println(e.getMessage()); }
        }
    }

    public static void deleteRental(int rid) {
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("DELETE FROM Customer WHERE rid = ?");
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
            } catch (SQLException e) { System.out.println(e.getMessage()); }
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
            ps = connection.prepareStatement("SELECT * FROM Customer WHERE rid=?");
            ps.setInt(1, rid);

            rs = ps.executeQuery();
            while (rs.next()) {
                retRental = new Rental(rs.getInt("rid"), rs.getString("vlicense"),
                        rs.getString("dlicense"), rs.getString("fromDate"), rs.getString("fromTime"),
                        rs.getString("toDate"), rs.getString("toTime"), rs.getInt("odometer"),
                        rs.getString("cardName"), rs.getInt("cardNo"), rs.getString("expDate"),
                        rs.getInt("confNo"));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            DatabaseConnectionHandler.rollbackConnection();
        }
        finally {
            DatabaseConnectionHandler.close();
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }

        return retRental;
    }


}
