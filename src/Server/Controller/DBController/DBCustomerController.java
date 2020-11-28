package Server.Controller.DBController;

import CommonModel.Model.CustomerList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomerController {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public ResultSet searchCustomerByLName(String lName) {
        try {
            String query = "SELECT * FROM customer WHERE lName = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,lName);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchCustomerByID(int id) {
        try {
            String query = "SELECT * FROM customer WHERE customerId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchByCustomerType(String type) {
        try {
            String query = "SELECT * FROM customer WHERE cType = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,type);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void updateUser(CustomerList customerList) {
        try {
            String query = "UPDATE customer SET lName = ?, fName = ?, cType = ?, phoneNum = ?, address = ?, postalCode = ? WHERE customerId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, customerList.getCustomers().get(0).getLastName());
            stmt.setString(2, customerList.getCustomers().get(0).getFirstName());
            stmt.setString(3, String.valueOf(customerList.getCustomers().get(0).getType()));
            stmt.setString(4, customerList.getCustomers().get(0).getPhoneNum());
            stmt.setString(5, customerList.getCustomers().get(0).getAddress());
            stmt.setString(6, customerList.getCustomers().get(0).getPostalCode());
            stmt.setInt(7, customerList.getCustomers().get(0).getCustomerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int customerId) {
        try {
            String query = "DELETE FROM customer WHERE customerId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
