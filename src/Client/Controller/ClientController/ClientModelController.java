package Client.Controller.ClientController;

import Server.Model.*;

public class ClientModelController {

    private ToolList toolList;
    private SupplierList supplierList;
    private CustomerList customerList;

    public ClientModelController() {

    }

    public void printAllTools() {
        for (Tool t: toolList.getToolList()) {
            System.out.println(t);
        }
    }

    public void setToolList(Object toolList) {
        setToolList((ToolList) toolList);
        System.out.println("\t\tSet the received ToolList");
        printAllTools();
    }

    public ToolList getToolList() {
        return toolList;
    }

    public void setToolList(ToolList toolList) {
        this.toolList = toolList;
    }

    public SupplierList getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(SupplierList supplierList) {
        this.supplierList = supplierList;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
    }
}
