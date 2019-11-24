package controller;

import model.Rental;
import database.DatabaseConnectionHandler;
import model.Reservation;
import model.Vehicle;
import java.util.Random;

import java.sql.*;
import java.util.ArrayList;

public class RentalsController {
    private static int rentCount = 1000;
    private static Random rand;

    public static void addRental(Rental rental) {
        Connection connection;
        PreparedStatement ps = null;
        rand = new Random();
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
            ps = connection.prepareStatement("SELECT * FROM Rentals WHERE rid=?");
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

    public static ArrayList<Rental> getDailyRental(String date, String location, String city) {
        Connection connection;
        ArrayList<Rental> retRental = new ArrayList<Rental>();
        Rental rental = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //TODO: This query is incorrect. It does not do what is expected
        String query = "SELECT * ";
        if (location == null && city == null){
            query = query +
                    "FROM Rentals R, Vehicles V "+
                    "WHERE R.fromDate =  '" + date + "' AND V.status = 'Rented' AND R.vlicense = V.vlicense AND V.city = '" + city +
                    "' AND V.location = '" + location +
//                    " AND (SELECT * WHERE (SELECT * GROUP BY V.vtname))" +
                    "' GROUP BY V.city, V.location, V.vtname";

        }
        else {
            query = query + "FROM Rentals  R, Vehicles V WHERE fromDate='" + date + "' AND V.status = 'Rented' AND " +
                    "V.vlicense = R.vlicense GROUP BY V.vtname";
        }

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return null;
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                rental = new Rental(rs.getInt("rid"), rs.getString("vlicense"),
                        rs.getString("dlicense"), rs.getString("fromDate"), rs.getString("fromTime"),
                        rs.getString("toDate"), rs.getString("toTime"), rs.getInt("odometer"),
                        rs.getString("cardName"), rs.getInt("cardNo"), rs.getString("expDate"),
                        rs.getInt("confNo"));
                retRental.add(rental);
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


    public static Vehicle rentVehicle(int confNo, String cardName, int cardNo, String expDate){
        Reservation reservation = ReservationsController.getReservation(confNo);
        String vtname, location, city;
        ArrayList<Vehicle> availableVehicles;
        String fromDate, fromTime, toDate, toTime;
        Vehicle rentedVehicle = null;

        vtname = reservation.getVtname();
        location = reservation.getLocation();
        city = reservation.getCity();
        fromDate = reservation.getPickUpDate();
        fromTime = reservation.getPickUpTime();
        toDate = reservation.getDropOffDate();
        toTime = reservation.getDropOffTime();

        availableVehicles = VehiclesController.getAvailableVehicles(vtname, location, city);
        if (availableVehicles != null){
            rentedVehicle = availableVehicles.get(0);
            VehiclesController.changeStatus(rentedVehicle.getVlicense(), "Rented");
            Rental rental = new Rental(
                    rand.nextInt(9999999),
                    rentedVehicle.getVlicense(),
                    reservation.getDlicense(),
                    fromDate,
                    fromTime,
                    toDate,
                    toTime,
                    rentedVehicle.getOdometer(),
                    cardName,
                    cardNo,
                    expDate,
                    confNo
            );
        }

        return rentedVehicle;
    }

    

}
