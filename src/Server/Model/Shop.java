package Server.Model;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Shop {

    private ToolList toolList;

    private SupplierList supplierList;

    private CustomerList customerList;

    public Shop() {
        toolList = new ToolList(new Order());
        supplierList = new SupplierList();
        customerList = new CustomerList();
    }

    public SupplierList getSupplierList() {
        return supplierList;
    }

    public ToolList getToolList() {
        return toolList;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void decreaseQty() {
        //Will need at some point
    }

    public void addTool(ResultSet rs) {
        try {
            while (rs.next()) {
                toolList.addItem(new Tool(rs.getInt("toolId"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getFloat("price"),
                        rs.getInt("supplierId")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
