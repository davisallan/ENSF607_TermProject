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

    private void addTool(Tool t) {
        try {
            String insert = "INSERT INTO toolTable (id, name, quantity, price, supplierId) values (?,?,?,?,?)";

            PreparedStatement pStat = conn.prepareStatement(insert);
            pStat.setInt(1, t.getId());
            pStat.setString(2, t.getName());
            pStat.setInt(3, t.getQuantity());
            pStat.setDouble(4,t.getPrice());
            pStat.setInt(5,t.getSupplierId());

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
    }
}
