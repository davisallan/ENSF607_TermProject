package PreProjectExercise;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryManager implements IDBCredentials {

    // Attributes
    private Connection conn;//Object of type connection from the JDBC class that deals with connecting to
    //the database
    private Statement stmt; //object of type statement from JDBC class that enables the creation "Query
    //statements"
    private ResultSet rs;//object of type ResultSet from the JDBC class that stores the result of the query

    public void initializeConnection() {
        try {
            //Register JDBC driver
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to jdbc:mysql://localhost/inventorydb");
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
        }
    }

    public void createTable() {
        String dropTable = "DROP TABLE IF EXISTS ToolTable";
        String sql = "CREATE TABLE ToolTable " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
                + " quantity INTEGER, " + " price float, " + " supplierId INTEGER, " + " PRIMARY KEY ( id ))";
        try {
            stmt = conn.createStatement();
            stmt.execute(dropTable);
            stmt.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Created table ToolTable in given database...");
        loadTools();
    }

    public void searchAllTools() {
        try {
            String query = "SELECT * FROM ToolTable";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("quantity") + " "
                + rs.getString("price") + " " + rs.getString("supplierId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TASK 1
    public void searchTool() {
        try {
            System.out.println("\nSearching table for tool 1002: should return 'Grommets'");
            String query = "SELECT * FROM ToolTable WHERE id = 1002";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print("Search Result: ");
                System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("quantity") + " "
                        + rs.getString("price") + " " + rs.getString("supplierId"));
            }

            System.out.println("\nSearching table for tool 9000: should fail to find a tool");
            query = "SELECT * FROM ToolTable WHERE id = 9000";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print("Search Result: ");
                System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("quantity") + " "
                        + rs.getString("price") + " " + rs.getString("supplierId"));
            }
             if (!rs.next()) {
                System.out.println("Search Failed to find a tool matching ID: 9000");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        System.out.println("\nTrying to remove the table");
        String query = "DROP TABLE ToolTable";
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Removed Table ToolTable");
    }

    private void addTool(Tool t) {
        try {
            String insert = "INSERT INTO toolTable (id, name, quantity, price, supplierId) values (?,?,?,?,?)";

            PreparedStatement pStat = conn.prepareStatement(insert);
            pStat.setInt(1, t.getId());
            pStat.setString(2, t.getName());
            pStat.setInt(3, t.getQuantity());
            pStat.setDouble(4,t.getPrice());
            pStat.setInt(5,t.getSupplierId());
            pStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTools() {
        System.out.println("Filling the table with Tools...");
        Inventory inventory = FileMgr.loadInventory("itemsNew.txt");
        for (Tool t: inventory.getToolList()) {
            addTool(t);
        }
    }

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.initializeConnection();
        manager.createTable();
        manager.searchAllTools();
        manager.searchTool();
        manager.dropTable();
        System.out.println("\nThe program is finished running");
    }
}
