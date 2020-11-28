package CommonModel.Model;

import java.sql.ResultSet;

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

    public void buildCustomers(ResultSet rs) {
        customerList.buildCustomer(rs);
    }

    public boolean sellTool(int id) {
        return toolInventory.sellItem(id);
    }

    public boolean sellTool(String name) {
        return toolInventory.sellItem(name);
    }

    public void clearAllLists() {
        toolInventory.clearList();
        supplierList.clearList();
        customerList.clearList();
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

    public ToolInventory getToolList() {
        return toolInventory;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }
}
