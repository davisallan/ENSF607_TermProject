package Server.Model;

import java.util.ArrayList;

/**
 *
 * Provides the data fields and methods to create a Java data type representing a tool supplier for the tool shop.
 * Ensures that all tools that are supplied by the given supplier are tracked.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class Supplier {
    /**
     * ID of the Supplier
     */
    private int supplierID;
    /**
     * Company name of Supplier
     */
    private String companyName;
    /**
     * The address of the Supplier
     */
    private String address;
    /**
     * The contact at the supplier for the Shop
     */
    private String contact;
    /**
     * ArrayList of Item objects that the Supplier supplies to the Shop
     */
    private final ArrayList<Tool> suppliedTools;

    /**
     * Constructs a Supplier object with the specified values for supplierID, companyName,
     * address, and contact. The values are supplied by the given parameters.
     * @param supplierID the unique identification number for the supplier
     * @param companyName the name of the supplier
     * @param address the address of the supplier
     * @param contact the name of the contact for the shop at the supplier
     */
    public Supplier(int supplierID, String companyName, String address, String contact) {
        setSupplierID(supplierID);
        setCompanyName(companyName);
        setAddress(address);
        setContact(contact);
        suppliedTools = new ArrayList<>(); //association between Item and Supplier
    }

    /**
     * Returns the supplier id
     * @return supplier id
     */
    public int getSupplierID() {
        return supplierID;
    }

    /**
     * Sets the supplier id
     * @param supplierID supplier id
     */
    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * Returns the company name of the supplier
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name of the supplier
     * @param companyName company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns the address of the supplier
     * @return company address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the company address of the supplier
     * @param address company address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the name of the contact at the supplier
     * @return contact name
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact at the supplier
     * @param contact contact name
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Adds the Item passed to the method to the suppliedItems ArrayList
     * @param tool Item to be added
     */
    public void addItemSupplied(Tool tool) {
        suppliedTools.add(tool);
    }

    /**
     * Returns the ArrayList containing all supplied Items from the supplier
     * @return ArrayList with all Items that the supplier supplies
     */
    public ArrayList<Tool> getSuppliedItems() {
        return suppliedTools;
    }

    /**
     * Returns the String representation of the Supplier object with all relevant information
     * shown.
     * @return String representation of the Supplier object
     */
    @Override
    public String toString() {
        return "ID: " + supplierID + "\n" +
                "Name: " + companyName + "\n" +
                "Address: " + address + "\n" +
                "Contact: " + contact + "\n";
    }
}
