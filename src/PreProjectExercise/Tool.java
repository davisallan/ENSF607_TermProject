package PreProjectExercise;

/**
 * Class representing a Tool
 */
public class Tool {

    private int id;
    private String name;
    private int quantity;
    private double price;
    private int supplierId;

    /**
     * Constructs a Supplier object with the specified values for id, name, quantity, and price.
     * The values are supplied by the given parameters.
     *
     * @param id         the unique tool ID
     * @param name       the name or description of the tool
     * @param quantity   the current quantity of the tool in stock
     * @param price      the current price of the tool
     * @param supplierId the supplier id
     */
    public Tool(int id, String name, int quantity, double price, int supplierId) {
        setId(id);
        setName(name);
        setQuantity(quantity);
        setPrice(price);
        setSupplierId(supplierId);
    }

    /**
     * Sets supplier id.
     *
     * @param supplierId the supplier id
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * Gets supplier id.
     *
     * @return the supplier id
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * Returns the unique Item id
     *
     * @return id of the Item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique Item id
     *
     * @param id id of the Item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the Item name
     *
     * @return name of Item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Item
     *
     * @param name Name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current quantity of the Item
     *
     * @return quantity of the Item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity instance variable and ensures that it will always be 0 or greater.
     *
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
     *
     * @return price of the Item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the Item
     *
     * @param price price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the String representation of the Item object
     * @return String representation of the Item object
     */
    @Override
    public String toString() {
        return String.format("%s %18s %14s %10s %15s\n", getId(), getName(), getPrice(), getQuantity(), getSupplierId());
    }
}
