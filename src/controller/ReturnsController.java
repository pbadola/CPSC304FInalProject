package controller;
import database.DatabaseConnectionHandler;
import model.Return;
//import javafx.scene.control.CustomMenuItem;
import java.sql.*;
import java.util.ArrayList;

public class ReturnsController {
    public static void addReturn(Return vReturn) {
        Connection connection;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("INSERT INTO Returns VALUES (?,?,?,?,?,?)");
            ps.setInt(1, vReturn.getRid());
            ps.setString(2, vReturn.getRdate());
            ps.setString(3, vReturn.getRtime());
            ps.setInt(4, vReturn.getOdometer());
            ps.setInt(5, vReturn.isFulltank());
            ps.setFloat(6, vReturn.getRvalue());



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
            } catch (SQLException e) { System.out.println(e.getMessage()); }
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
                retReturn = new Return(rs.getInt("rid"), rs.getString("rdate"),
                        rs.getString("rtime"), rs.getInt("odometer"),
                        rs.getInt("fulltank"), rs.getFloat("rvalue"));
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

        return retReturn;
    }

    public static ArrayList<Return> getDailyReturn(String date, String location, String city)  {
        Connection connection;
        Return rReturn = null;
        ArrayList<Return> retReturn = new ArrayList<Return>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //TODO: This query is incorrect. It does not do what is expected
        String query = "SELECT * ";
        if (location != null && city != null){
            query = query + "FROM Returns R, Rentals Re, Vehicles V "+
                    "WHERE R.fromDate =  " + date + " AND R.vlicense = V.vlicence AND V.city = " + city +
                    " AND R.rid = Re.rid AND V.location = " + location;
        }
        else {
            query = query + "FROM Returns WHERE rdate=" + date;
        }

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return null;
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                rReturn = new Return(rs.getInt("rid"), rs.getString("rdate"),
                        rs.getString("rtime"), rs.getInt("odometer"),
                        rs.getInt("fulltank"), rs.getFloat("rvalue"));
                retReturn.add(rReturn);
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

        return retReturn;
    }

    public int calculateValue(){
        return 0;
    }
}
