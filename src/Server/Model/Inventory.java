package Server.Model;

import java.util.ArrayList;

/**
 * Provides the data fields and methods to create a data type representing the inventory in a tool shop.
 * Responsible for maintaining the list of all Items (tools) in the shop.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class Inventory {
    /**
     * ArrayList of Items representing the tools in the shop
     */
    private final ArrayList<Item> itemList;
    /**
     * The daily Order for the shop
     */
    private Order order;

    /**
     * Constructs the Inventory object and assigns the daily Order to its member variable.
     * @param order the daily Order for the Shop
     */
    public Inventory(Order order) {
        itemList = new ArrayList<>();
        setOrder(order);
    }

    /**
     * Returns the ArrayList of Items representing the inventory of the shop
     * @return ArrayList of Items
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * Returns the Order object representing the daily order from the shop
     * @return Order object
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the Order
     * @param order Order object
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Adds the Item passed to this method to the ArrayList instance variable
     * @param i Item to be added
     */
    public void addItem(Item i) {
        itemList.add(i);

    }

    /**
     * Searches the inventory and returns the tool matching the user supplied name. If no match is found,
     * return null.
     * @param name the name of the tool to search for
     * @return the matching Item if found, null if it does not exist
     */
    public Item findItemByName(String name) {
        for (Item i : itemList) {
            if (i.getName().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    /**
     * Searches the inventory and returns the tool matching the user tool ID. If no match is found,
     * return null.
     * @param id the ID of the tool to search for
     * @return the matching Item if found, null if it does not exist
     */
    public Item findItemByID(int id) {
        for (Item i : itemList) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Simulates the sale of a Item by decreasing the current quantity by a specific amount. Values
     * of data fields are supplied by the given parameters.
     * @param item the Item that is being sold
     * @param amount the number of Items being sold
     */
    public void decreaseQty(Item item, int amount) {
        item.decreaseQty(item, amount, order);
    }

    /**
     * Displays all current Items in the Inventory to the console
     */
    public void displayInventory() {
        System.out.printf("%s %18s %17s %9s  %15s\n", "ID", "Name", "Price", "Qty", "Supplier ID");
        System.out.println("-----------------------------------------------------------------");
        for (Item item : itemList){
            System.out.print(item);
        }
    }
}
