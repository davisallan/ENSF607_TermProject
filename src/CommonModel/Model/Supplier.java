package CommonModel.Model;

import java.io.Serializable;

public abstract class Supplier implements Serializable {

    private int supplierID;
    private String companyName;
    private String address;
    private String contact;
    private String type;

    public Supplier(int supplierID, String companyName, String type, String address, String contact) {
        setSupplierID(supplierID);
        setCompanyName(companyName);
        setType(type);
        setAddress(address);
        setContact(contact);
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
