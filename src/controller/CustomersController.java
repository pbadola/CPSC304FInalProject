package controller;

import database.DatabaseConnectionHandler;
//import javafx.scene.control.CustomMenuItem;
import model.Customer;
import java.sql.*;

public class CustomersController {

    public static void addCustomer(Customer customer) {
        Connection connection;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("INSERT INTO Customers VALUES (?,?,?,?)");
            ps.setString(1, customer.getDlicense());
            ps.setInt(2, customer.getCellphone());
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getAddress());


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

    public static void deleteCustomer(String dlicense) {
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return;
            ps = connection.prepareStatement("DELETE FROM Customers WHERE dlicense = ?");
            ps.setString(1, dlicense);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(" ERROR: Customer with license " + dlicense + " does not exist");
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

    public static Customer getCustomer(String dlicense) {
        Connection connection;
        Customer retCustomer = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnectionHandler.getConnection();
            if (connection == null) return null;
            ps = connection.prepareStatement("SELECT * FROM Customers WHERE dlicense=?");
            ps.setString(1, dlicense);

            rs = ps.executeQuery();
            while (rs.next()) {
                retCustomer = new Customer(rs.getInt("cellphone"), rs.getString("name"),
                        rs.getString("address"), rs.getString("dlicense"));
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

        return retCustomer;
    }

}
