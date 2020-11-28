package Server.Controller.DBController;

import java.sql.*;
import java.time.LocalDate;

public class DBOrderController {

    private Connection conn;
    private PreparedStatement stmt;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void addOrder(int orderId, LocalDate date) {
        try {
            String query = "INSERT INTO toolorder (orderId, orderDate) \n" +
                    "SELECT ?, ?\n" +
                    "WHERE NOT EXISTS \n" +
                    "(SELECT orderId FROM toolorder WHERE orderId = ?);";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setInt(3, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderLine(int orderId, int toolId, int supplierId, int quantity) {
        try {
            String query = "INSERT INTO orderline VALUES (?,?,?,?);";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);
            stmt.setInt(2, toolId);
            stmt.setInt(3, supplierId);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
