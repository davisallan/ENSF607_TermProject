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
//        System.out.println("\t\t\tReturning the results of query");
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
//        System.out.println("\t\t\tReturning the results of query");
        return rs;
    }

    public ResultSet selectAll(String tableName) {
        try {
            String query = "SELECT * FROM " + tableName;
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public void close() {
        try {
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //testing functions
        DBController controller = new DBController();
        controller.initializeConnection();
        ResultSet resultSet = controller.searchToolById(1001);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("toolId") + " " +
                        resultSet.getString("name") + " " +
                        resultSet.getString("type") + " " +
                        resultSet.getInt("quantity") + " " +
                        resultSet.getFloat("price") + " " +
                        resultSet.getInt("supplierId") + " " +
                        resultSet.getString("powerType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        controller.close();
    }
}

