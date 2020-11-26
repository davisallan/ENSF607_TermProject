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

    //TODO move the addCustomers/addTools methods to their respective classes
    public void addTools(ResultSet rs) {
        try {
            while (rs.next()) {
                if (rs.getString("tType").equals("electrical")) {
                    toolList.addTool(new Electrical(
                            rs.getInt("toolId"),
                            rs.getString("tName"),
                            rs.getString("tType"),
                            rs.getInt("quantity"),
                            rs.getFloat("price"),
                            rs.getInt("supplierId"),
                            rs.getString("sName"),
                            rs.getString("powerType")));
                } else {
                    toolList.addTool(new NonElectrical(
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

    public boolean decreaseQty(Tool tool) {
        return toolList.decreaseQty(tool);
    }

    public boolean sellItem(int id) {
        for (Tool tool : toolList.getToolList())
            if (tool.getId() == id)
                return decreaseQty(tool);
        return false;
    }

    public boolean sellItem(String name) {
        for (Tool tool : toolList.getToolList())
            if (tool.getName().equals(name))
                return decreaseQty(tool);
        return false;
    }

    public void addCustomers(ResultSet rs) {
        try {
            while (rs.next()) {
                if (rs.getString("ctype").equals("R")) {
                    customerList.addCustomer(new Residential(
                            rs.getInt("customerId"),
                            rs.getString("lName"),
                            rs.getString("fName"),
                            rs.getString("cType").charAt(0),
                            rs.getString("phoneNum"),
                            rs.getString("address"),
                            rs.getString("postalCode")));
                } else {
                    customerList.addCustomer(new Commercial(
                            rs.getInt("customerId"),
                            rs.getString("lName"),
                            rs.getString("fName"),
                            rs.getString("cType").charAt(0),
                            rs.getString("phoneNum"),
                            rs.getString("address"),
                            rs.getString("postalCode")));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAllLists() {
        toolList.getToolList().clear();
        supplierList.getSupplierList().clear();
        customerList.getCustomerList().clear();
    }

    public void setToolList(ToolList toolList) {
        this.toolList = toolList;
    }

    public void setSupplierList(SupplierList supplierList) {
        this.supplierList = supplierList;
    }

    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
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
}
