package Server.Controller.DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBToolController {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public ResultSet searchToolById(int id) {
        try {
            String query = "SELECT T.toolId, T.tName, T.tType, T.quantity, T.price, T.supplierId, T.sName, E.powerType " +
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
            String query = "SELECT T.toolId, T.tName, T.tType, T.quantity, T.price, T.supplierId, T.sName, E.powerType " +
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
            String query = "SELECT T.toolId, T.tName, T.tType, T.quantity, T.price, T.supplierId, T.sName, E.powerType " +
                    "FROM (tool AS T LEFT OUTER JOIN electrical AS E ON T.toolId = E.toolId)";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void sellTool(int toolId) {
        try {
            String query = "UPDATE tool SET quantity = quantity - 1 WHERE toolId=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, toolId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sellTool(String toolName) {
        try {
            String query = "UPDATE tool SET quantity = quantity - 1 WHERE tName=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, toolName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
