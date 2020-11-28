package CommonModel.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shop {

    private ToolInventory toolInventory;
    private SupplierList supplierList;
    private CustomerList customerList;

    public Shop(ToolInventory toolInventory, SupplierList supplierList, CustomerList customerList) {
        setToolList(toolInventory);
        setSupplierList(supplierList);
        setCustomerList(customerList);
    }

    public void buildTool(ResultSet rs) {
        toolInventory.buildTool(rs);
    }

    public boolean decreaseQty(Tool tool) {
        return toolInventory.decreaseQty(tool);
    }

    public boolean sellItem(int id) {
        for (Tool tool : toolInventory.getToolList())
            if (tool.getId() == id)
                return decreaseQty(tool);
        return false;
    }

    public boolean sellItem(String name) {
        for (Tool tool : toolInventory.getToolList())
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
        toolInventory.clearList();
        supplierList.getSupplierList().clear();
        customerList.getCustomerList().clear();
    }

    public void setToolList(ToolInventory toolInventory) {
        this.toolInventory = toolInventory;
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

    public ToolInventory getToolList() {
        return toolInventory;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }


}
