package Server.Model;

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

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ID: " + supplierID + "\n" +
                "Name: " + companyName + "\n" +
                "Address: " + address + "\n" +
                "Contact: " + contact + "\n";
    }
}
