package Server.Controller.DBController;

import Server.Model.CustomerList;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class DBController implements DBCredentials{

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public DBController() {
        initializeConnection();
    }

    public void initializeConnection() {
        try {
            //Register JDBC driver
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
        }
    }

    public ResultSet searchToolById(int id) {
        try {
            String query = "SELECT T.toolId, T.tName, T.tType, T.quantity, T.price, T.supplierId, E.powerType " +
                           "FROM (tool AS T LEFT OUTER JOIN electrical AS E ON T.toolId = E.toolId) WHERE T.toolId= ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchToolByName(String name) {
        try {
            String query = "SELECT T.toolId, T.tName, T.tType, T.quantity, T.price, T.supplierId, E.powerType " +
                    "FROM (tool AS T LEFT OUTER JOIN electrical AS E ON T.toolId = E.toolId) WHERE T.tName= ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet selectAllTools() {
        try {
            String query = "SELECT T.toolId, T.tName, T.tType, T.quantity, T.price, T.supplierId, E.powerType " +
                    "FROM (tool AS T LEFT OUTER JOIN electrical AS E ON T.toolId = E.toolId)";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
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
            stmt.setString(1, customerList.getCustomerList().get(0).getLastName());
            stmt.setString(2, customerList.getCustomerList().get(0).getFirstName());
            stmt.setString(3, String.valueOf(customerList.getCustomerList().get(0).getType()));
            stmt.setString(4, customerList.getCustomerList().get(0).getPhoneNum());
            stmt.setString(5, customerList.getCustomerList().get(0).getAddress());
            stmt.setString(6, customerList.getCustomerList().get(0).getPostalCode());
            stmt.setInt(7, customerList.getCustomerList().get(0).getCustomerId());
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

    public void sellItem(int toolId) {
        try {
            String query = "UPDATE tool SET quantity = quantity - 1 WHERE toolId=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, toolId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sellItem(String toolName) {
        try {
            String query = "UPDATE tool SET quantity = quantity - 1 WHERE name=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, toolName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //testing functions
        DBController controller = new DBController();
        controller.initializeConnection();
        controller.deleteUser(100);
        controller.close();
    }
}