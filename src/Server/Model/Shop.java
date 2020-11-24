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

    public void addTools(ResultSet rs) {
        try {
//            System.out.println("\tAdding tools");
            do {
                if (!rs.next()) {
                    System.out.println("SET IS EMPTY!!!");
                } else{
//                    System.out.println(rs.getString("toolId"));
                    if (rs.getString("type").equals("electrical")) {
                        toolList.addItem(new Electrical(rs.getInt("toolId"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getInt("quantity"),
                                rs.getFloat("price"),
                                rs.getInt("supplierId"),
                                rs.getString("powerType")));
                    } else {
                        toolList.addItem(new NonElectrical(rs.getInt("toolId"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getInt("quantity"),
                                rs.getFloat("price"),
                                rs.getInt("supplierId")));

                    }
                }
            } while (rs.next());
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
