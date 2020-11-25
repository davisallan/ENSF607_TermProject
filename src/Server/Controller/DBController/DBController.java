package Server.Controller.DBController;

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
            String query = "SELECT T.toolId, T.name, T.type, T.quantity, T.price, T.supplierId, E.powerType " +
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
            String query = "SELECT T.toolId, T.name, T.type, T.quantity, T.price, T.supplierId, E.powerType " +
                    "FROM (tool AS T LEFT OUTER JOIN electrical AS E ON T.toolId = E.toolId) WHERE T.name= ?";
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
            String query = "SELECT T.toolId, T.name, T.type, T.quantity, T.price, T.supplierId, E.powerType " +
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

    public void updateUser(int customerId, String lName, String fName, String cType,
                           String phoneNum, String address, String postalCode) {
        try {
            String query = "UPDATE customer SET lName = ?, fName = ?, cType = ?, phoneNum = ?, address = ?, postalCode = ? WHERE customerId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, lName);
            stmt.setString(2, fName);
            stmt.setString(3, cType);
            stmt.setString(4, phoneNum);
            stmt.setString(5, address);
            stmt.setString(6, postalCode);
            stmt.setInt(7, customerId);
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
        controller.updateUser(100,"POWERS","AUSTIN", "R",
                                "403-123-1343","yeah baby", "t23-123");
        controller.deleteUser(100);
        controller.close();
    }
}