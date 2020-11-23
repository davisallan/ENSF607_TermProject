package Server.Model;


/**
 * Provides the data fields and methods to create a data type representing the tool shop. Contains a list of
 * all Items (tools) and Suppliers for the given shop
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
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

    public void decreaseQty()  {

    }
}
