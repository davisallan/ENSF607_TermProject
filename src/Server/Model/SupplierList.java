package Server.Model;

import java.util.ArrayList;

/**
 * Provides the data fields and methods to create a data type representing the list of Suppliers for a tool shop.
 * Responsible for maintaining the list of all Suppliers for the shop.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class SupplierList {
    /**
     * ArrayList of Supplier objects representing individual suppliers
     */
    private final ArrayList<Supplier> supplierList;

    /**
     * Constructs the SupplierList object and instantiates the ArrayList of Suppliers.
     */
    public SupplierList() {
        supplierList = new ArrayList<>();
    }

    /**
     * Adds a Supplier to the ArrayList of Suppliers
     * @param supplier Supplier to be added
     */
    public void addSupplier(Supplier supplier) {
        supplierList.add(supplier);
    }

    /**
     * Displays the String representation of all suppliers currently stored in the ArrayList of Suppliers
     */
    public void displaySuppliers() {
        for (Supplier s : supplierList){
            System.out.println(s);
        }
    }

    /**
     * Searches the ArrayList of Suppliers and returns the the Supplier matching the user supplied supplierID.
     * If no match is found, return null.
     * @param supplierID the Supplier ID to search for
     * @return the matching Supplier if found, null if it does not exist
     */
    public Supplier findSupplier (int supplierID) {
        for (Supplier s : supplierList) {
            if (s.getSupplierID() == supplierID) {
                return s;
            }
        }
        return null;
    }
}
