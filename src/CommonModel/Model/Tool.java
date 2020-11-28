package CommonModel.Model;

import java.io.Serializable;

public abstract class Tool implements Serializable {

    private int id;
    private String name;
    private String type;
    private int quantity;
    private double price;
    private int supplierId;
    private String supplierName;

    public Tool(int id, String name, String type, int quantity, double price, int supplierID, String supplierName) {
        setId(id);
        setName(name);
        setType(type);
        setQuantity(quantity);
        setPrice(price);
        setSupplierID(supplierID);
        setSupplierName(supplierName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSupplierID() {
        return supplierId;
    }

    public void setSupplierID(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    //    /**
//     * Simulates the sale of a Item by decreasing the current quantity by a specific amount. Ensures if
//     * quantity is decreased below 40, it will trigger the creation of an OrderLine with a default order
//     * size of 50 - current quantity. Values of data fields are supplied by the given parameters.
//     * @param tool the Item that is being sold
//     * @param amount the number of Items being sold
//     * @param order the current day's Order
//     */
//    public void decreaseQty(Tool tool, int amount, Order order) {
//        int qty = getQuantity() - amount;
//        //ensures qty is always > 0
//        if (qty < 0) {
//            qty = 0;
//        }
//        setQuantity(qty); //update current stock with amount sold reduced
//        System.out.println("\033[0;32m" + "Quantity reduced to " + tool.getQuantity() + "\033[0m");
//        //create an OrderLine if current stock is < 40
//        if (getQuantity() < 40) {
//            OrderLine OL = new OrderLine(tool, 50 - getQuantity());
//            order.addOrderLine(OL);
//            setQuantity(50 - getQuantity());
//        }
//    }

    public boolean decreaseQty(Order order) {
        setQuantity(getQuantity() - 1);

        if (getQuantity() < 40) {
            OrderLine orderLine = new OrderLine(this, 50 - getQuantity(), order);
            order.addOrderLine(orderLine);
            return true;
        }
        return false;
    }

    /**
     * Returns the String representation of the Item object
     * @return String representation of the Item object
     */
    @Override
    public String toString() {
        return String.format("%-12d %-16s %-20s %-10.2f %-8d %-12s", getId(), getName(), getType(), getPrice(), getQuantity(), getSupplierID());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}