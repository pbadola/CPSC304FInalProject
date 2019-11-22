package controller;

import database.DatabaseConnectionHandler;
import model.VehicleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleTypesController {
    public static void addVehicleType(VehicleType vt) {
        Connection connection;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("INSERT INTO VehicleTypes VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, vt.getVtname());
            ps.setString(2, vt.getFeatures());
            ps.setFloat(3, vt.getWrate());
            ps.setFloat(4, vt.getDrate());
            ps.setFloat(5, vt.getHrate());
            ps.setFloat(6, vt.getWirate());
            ps.setFloat(7, vt.getDirate());
            ps.setFloat(8, vt.getHirate());
            ps.setFloat(9, vt.getKrate());


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

    public static void deleteVehicleType(String vtname) {
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("DELETE FROM VehicleTypes WHERE dlicense = ?");
            ps.setString(1, vtname);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(" ERROR: Vehicle type " + vtname + " does not exist");
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

    public static VehicleType getVehicleType(String vtname) {
        Connection connection;
        VehicleType retVehicleType = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return null;
            ps = connection.prepareStatement("SELECT * FROM VehicleTypes WHERE vtname=?");
            ps.setString(1, vtname);

            rs = ps.executeQuery();
            while (rs.next()) {
                retVehicleType = new VehicleType(rs.getString("vtname"), rs.getString("features"),
                        rs.getFloat("wrate"), rs.getFloat("drate"), rs.getFloat("hrate"),
                        rs.getFloat("wirate"), rs.getFloat("dirate"), rs.getFloat("hirate"),
                        rs.getFloat("krate"));
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

        return retVehicleType;
    }
}
