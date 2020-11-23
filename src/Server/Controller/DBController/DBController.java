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

    public void close() {
        try {
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchByID(int id, String tableName, String idType) {
        try {
            String query = "SELECT * FROM ? WHERE ? = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, tableName);
            stmt.setString(2, idType);
            stmt.setInt(3, id);
            rs = stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        //testing functions
        DBController controller = new DBController();
        controller.initializeConnection();
        ResultSet test = controller.searchByID(1000, "tool", "toolId");

        controller.close();

    }

}
