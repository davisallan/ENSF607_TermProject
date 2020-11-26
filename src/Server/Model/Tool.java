package Server.Model;

import java.io.Serializable;

public abstract class Tool implements Serializable {

    private int id;
    private String name;
    private String type;
    private int quantity;
    private double price;
    private int supplierId;

    public Tool(int id, String name, String type, int quantity, double price, int supplierID) {
        setId(id);
        setName(name);
        setType(type);
        setQuantity(quantity);
        setPrice(price);
        setSupplierID(supplierID);
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
        return String.format("%-15d%-50s%-25s%-9.2f%-8d%-8s", getId(), getName(), getType(), getPrice(), getQuantity(), getSupplierID());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
