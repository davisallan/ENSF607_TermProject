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

    public void decreaseQty() {
        //Will need at some point
    }

    //TODO move the addCustomers/addTools methods to their respective classes
    public void addTools(ResultSet rs) {
        try {
            while (rs.next()) {
                if (rs.getString("type").equals("electrical")) {
                    toolList.addTool(new Electrical(
                            rs.getInt("toolId"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("quantity"),
                            rs.getFloat("price"),
                            rs.getInt("supplierId"),
                            rs.getString("powerType")));
                } else {
                    toolList.addTool(new NonElectrical(
                            rs.getInt("toolId"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("quantity"),
                            rs.getFloat("price"),
                            rs.getInt("supplierId")));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
