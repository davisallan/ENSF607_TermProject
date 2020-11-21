package Server.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Provides the data fields and methods to create a data type representing the tool shop. Contains a list of
 * all Items (tools) and Suppliers for the given shop
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class Shop {
    /**
     * Inventory object that contains all Item information
     */
    private Inventory inventory;

    /**
     * SupplierList object that contains all supplier information
     */
    private SupplierList supplierList;

    /**
     * Constructs the Shop object and depends on FileMgr to initialize the instance variables of inventory
     * and supplierList
     */
    public Shop() {
//        supplierList = FileMgr.loadSupplierList("suppliers.txt");
//        inventory = FileMgr.loadInventory("items.txt", supplierList);
    }

    /**
     * Returns the Inventory object containing all Items in the store
     * @return Inventory object
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns the SupplierList object containing all Suppliers for the store
     * @return SupplierList object
     */
    public SupplierList getSupplierList() {
        return supplierList;
    }

    /**
     * Simulates the sale of a Item by decreasing the current quantity by a specific amount. Values
     * of data fields are supplied by the given parameters.
     * @param reader BufferedReader used to take in user data
     * @throws IOException if any file I/O errors occur
     */
    public void decreaseQty(BufferedReader reader) throws IOException {
        int amount;
        //find the item to decrease
        Item search = searchByIDorName(reader);
        if (search == null) {
            System.out.println("\033[0;31m" + "\tSorry! Item does not exist - returning to main menu" + "\033[0m");
            return;
        }
        //Ensures valid user input for the amount to decrease the given item by
        while (true) {
            try {
                System.out.print("Enter the amount to decrease " + search.getName() +" by: ");
                amount = Integer.parseInt(reader.readLine());
                if (amount < 0) {
                    System.out.println("\033[0;31m" + "Invalid input, please a positive Integer" + "\033[0m");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\033[0;31m" + "Error, please enter a valid integer" + "\033[0m");
            }
        }
        getInventory().decreaseQty(search, amount);
    }

    /**
     * Outputs the current quantity to the console for the given item
     * @param reader BufferedReader used to take in user data
     * @throws IOException if any I/O error occurs
     */
    public void checkQty (BufferedReader reader) throws IOException {
        Item search = searchByIDorName(reader);
        if (search == null) {
            System.out.println("\033[0;31m" + "\tSorry! Item does not exist - returning to main menu" + "\033[0m");
        } else {
            System.out.println("\033[0;32m" + "\nThe quantity of " + search.getName() + " is = " +
                    search.getQuantity() + "\033[0m");
        }
    }

    /**
     * Searches and returns the Item by either searching by tool ID or Name. If the Item is not found,
     * return null
     * @param reader BufferedReader used to take in user data
     * @return the matching Item if found, null if it does not exist
     * @throws IOException if any I/O error occurs
     */
    public Item searchByIDorName(BufferedReader reader) throws IOException {
        System.out.println("\033[0;32m" + "1. Search by tool Name\n2. Search by tool ID" + "\033[0m");
        int selection;
        while (true) {
            System.out.print("\nSelection: ");
            try {
                selection = Integer.parseInt(reader.readLine());
                if (selection > 2 || selection < 0 ) {
                    System.out.println("\033[0;31m" + "Invalid input, please enter 1 or 2" + "\033[0m");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\033[0;31m" + "Error, please enter a valid integer" + "\033[0m");
            }
        }
        Item search = null;
        if (selection == 1) {
            search = searchByName(reader);
        } else if (selection == 2) {
            search = searchByID(reader);
        }
        return search;
    }

    /**
     * Searches and returns the Item by searching for a matching name. If the Item is not found,
     * return null
     * @param reader BufferedReader used to take in user data
     * @return the matching Item if found, null if it does not exist
     * @throws IOException if any I/O error occurs
     */
    public Item searchByName (BufferedReader reader) throws IOException {
        System.out.print("\033[0;32m" + "Please enter the tool name to search for: " + "\033[0m");
        String name = reader.readLine();
        return getInventory().findItemByName(name);
    }

    /**
     * Searches and returns the Item by searching for a tool ID. If the Item is not found,
     * return null
     * @param reader BufferedReader used to take in user data
     * @return the matching Item if found, null if it does not exist
     * @throws IOException if any I/O error occurs
     */
    public Item searchByID (BufferedReader reader) throws IOException {
        System.out.print("\033[0;32m" + "Please enter the tool ID to search for: " + "\033[0m");
        int id;
        while (true) {
            try {
                id = Integer.parseInt(reader.readLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("\033[0;31m" + "Please enter a valid Integer id" + "\033[0m");
            }
        }
        return getInventory().findItemByID(id);
    }

    /**
     * Displays the String representation of a given Item to the console. If the item is null, output
     * a message stating it does not exist.
     * @param item the Item to be displayed
     */
    public void displayItem(Item item) {
        if (item == null) {
            System.out.println("\033[0;31m" + "\tSorry! Item does not exist - returning to main menu" + "\033[0m");
        } else {
            System.out.println("Item info:");
            System.out.printf("%s %18s %17s %9s  %15s\n", "ID", "Name", "Price", "Qty", "Supplier ID");
            System.out.println(item);
        }
    }
}
