package CommonModel.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToolInventory {

    private ArrayList<Tool> toolList;
    private Order order;

    public ToolInventory(Order order) {
        toolList = new ArrayList<>();
        setOrder(order);
    }

    public void buildTool(ResultSet rs) {
        try {
            while (rs.next()) {
                if (rs.getString("tType").equals("electrical")) {
                    addTool(new Electrical(
                            rs.getInt("toolId"),
                            rs.getString("tName"),
                            rs.getString("tType"),
                            rs.getInt("quantity"),
                            rs.getFloat("price"),
                            rs.getInt("supplierId"),
                            rs.getString("sName"),
                            rs.getString("powerType")));
                } else {
                    addTool(new NonElectrical(
                            rs.getInt("toolId"),
                            rs.getString("tName"),
                            rs.getString("tType"),
                            rs.getInt("quantity"),
                            rs.getFloat("price"),
                            rs.getInt("supplierId"),
                            rs.getString("sName")));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean sellItem(int id) {
        for (Tool tool : getToolList())
            if (tool.getId() == id)
                return decreaseQty(tool);
        return false;
    }

    public boolean sellItem(String name) {
        for (Tool tool : getToolList())
            if (tool.getName().equals(name))
                return decreaseQty(tool);
        return false;
    }

    public void clearList() {
        toolList.clear();
    }

    private boolean decreaseQty(Tool tool) {
        return tool.decreaseQty(order);
    }

    public ArrayList<Tool> getToolList() {
        return toolList;
    }

    public Order getOrder() {
        if (order == null)
            order = new Order();
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addTool(Tool i) {
        toolList.add(i);
    }
}
