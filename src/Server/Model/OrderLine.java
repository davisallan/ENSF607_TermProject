package Server.Model;

/**
 * Provides the data fields and methods to create a data type representing an order line for a specific
 * item in an Order.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class OrderLine {
    /**
     * The Item that is being ordered
     */
    private Tool toolToOrder;

    /**
     * The amount to order
     */
    private int orderQty;

    /**
     * Constructs the OrderLine object with the specified values for item and orderQty. Values
     * of the data fields are supplied by the given parameters.
     * @param tool the Item to be ordered
     * @param orderQty the quantity to order
     */
    public OrderLine(Tool tool, int orderQty) {
        setItemToOrder(tool);
        setOrderQty(orderQty);
    }

    /**
     * Returns the Item object that is being ordered
     * @return Item being ordered
     */
    public Tool getItemToOrder() {
        return toolToOrder;
    }

    /**
     * Sets the Item to be ordered
     * @param toolToOrder Item that is being ordered
     */
    public void setItemToOrder(Tool toolToOrder) {
        this.toolToOrder = toolToOrder;
    }

    /**
     * Returns the quantity of the Item being ordered
     * @return quantity of the Item being ordered
     */
    public int getOrderQty() {
        return orderQty;
    }

    /**
     * Sets the order quantity for the Item being ordered
     * @param orderQty quantity to be ordered
     */
    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * Returns the String representation of the OrderLine object with the required information shown.
     * @return String representation of the OrderLine object
     */
    @Override
    public String toString() {
        return String.format("%-25s %-10s", "Item description:", toolToOrder.getName()) + "\n" +
                String.format("%-25s %-10s", "Amount ordered:", getOrderQty()) + "\n" +
                String.format("%-25s %-10s", "Supplier:", toolToOrder.getSupplier().getCompanyName()) + "\n\n";
    }
}
