package Server.Controller.DBController;

import CommonModel.Model.CustomerList;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DBController implements DBCredentials{

    private Connection conn;
    private DBToolController dbTool;
    private DBOrderController dbOrder;
    private DBCustomerController dbCustomer;

    public DBController(DBToolController dbTool, DBCustomerController dbCustomer, DBOrderController dbOrder) {
        initializeConnection();
        setDbTool(dbTool);
        setDbCustomer(dbCustomer);
        setDbOrder(dbOrder);
        //setting connection for the controllers
        dbTool.setConn(conn);
        dbCustomer.setConn(conn);
        dbOrder.setConn(conn);
    }

    public void initializeConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Problem");
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

    public ResultSet searchToolById(int id) {
        return dbTool.searchToolById(id);
    }

    public ResultSet searchToolByName(String name) {
        return dbTool.searchToolByName(name);
    }

    public ResultSet selectAllTools() {
        return dbTool.selectAllTools();
    }

    public void sellTool(int toolId) {
        dbTool.sellTool(toolId);
    }

    public void sellTool(String toolName) {
        dbTool.sellTool(toolName);
    }

    public ResultSet searchCustomerByLName(String lName) {
        return dbCustomer.searchCustomerByLName(lName);
    }

    public ResultSet searchCustomerByID(int id) {
        return dbCustomer.searchCustomerByID(id);
    }

    public ResultSet searchByCustomerType(String type) {
        return dbCustomer.searchByCustomerType(type);
    }

    public void updateCustomer(CustomerList customerList) {
        dbCustomer.updateUser(customerList);
    }

    public void deleteUser(int customerId) {
        dbCustomer.deleteUser(customerId);
    }

    public void addOrder(int orderId, LocalDate date) {
        dbOrder.addOrder(orderId, date);
    }

    public void addOrderLine(int orderId, int toolId, int supplierId, int quantity) {
        dbOrder.addOrderLine(orderId, toolId, supplierId, quantity);
    }

    public void setDbTool(DBToolController dbTool) {
        this.dbTool = dbTool;
    }

    public void setDbOrder(DBOrderController dbOrder) {
        this.dbOrder = dbOrder;
    }

    public void setDbCustomer(DBCustomerController dbCustomer) {
        this.dbCustomer = dbCustomer;
    }
}