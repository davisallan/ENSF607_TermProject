package Server.Model;

/**
 *
 * Provides the data fields and methods to create a data type representing a tool that is sold in the tool shop.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class Tool {
    /**
     * The unique tool ID
     */
    private int id;

    /**
     * The name or description of the tool
     */
    private String name;

    /**
     * The current quantity of the tool in stock
     */
    private int quantity;

    /**
     * The current price of the tool
     */
    private double price;
    /**
     * The supplier associated with the tool
     */
    private Supplier supplier;

    /**
     * Constructs a Supplier object with the specified values for id, name, quantity, and price.
     * The values are supplied by the given parameters.
     * @param id the unique tool ID
     * @param name the name or description of the tool
     * @param quantity the current quantity of the tool in stock
     * @param price the current price of the tool
     */
    public Tool(int id, String name, int quantity, double price) {
        setId(id);
        setName(name);
        setQuantity(quantity);
        setPrice(price);
    }

    /**
     * Returns the unique Item id
     * @return id of the Item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique Item id
     * @param id id of the Item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the Item name
     * @return name of Item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Item
     * @param name Name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current quantity of the Item
     * @return quantity of the Item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity instance variable and ensures that it will always be 0 or greater.
     * @param quantity the current amount in stock
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        }
        this.quantity = quantity;
    }

    /**
     * Returns the price of the Item
     * @return price of the Item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the Item
     * @param price price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the Supplier object that supplies this Item
     * @return Supplier that supplies the Item
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the Supplier of the Item
     * @param supplier Supplier that supplies the Item
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Simulates the sale of a Item by decreasing the current quantity by a specific amount. Ensures if
     * quantity is decreased below 40, it will trigger the creation of an OrderLine with a default order
     * size of 50 - current quantity. Values of data fields are supplied by the given parameters.
     * @param tool the Item that is being sold
     * @param amount the number of Items being sold
     * @param order the current day's Order
     */
    public void decreaseQty(Tool tool, int amount, Order order) {
        int qty = getQuantity() - amount;
        //ensures qty is always > 0
        if (qty < 0) {
            qty = 0;
        }
        setQuantity(qty); //update current stock with amount sold reduced
        System.out.println("\033[0;32m" + "Quantity reduced to " + tool.getQuantity() + "\033[0m");
        //create an OrderLine if current stock is < 40
        if (getQuantity() < 40) {
            OrderLine OL = new OrderLine(tool, 50 - getQuantity());
            order.addOrderLine(OL);
            setQuantity(50 - getQuantity());
        }
    }

    /**
     * Returns the String representation of the Item object
     * @return String representation of the Item object
     */
    @Override
    public String toString() {
        return String.format("%s %18s %14s %10s %15s\n", getId(), getName(), getPrice(), getQuantity(), getSupplier().getSupplierID());
    }
}
