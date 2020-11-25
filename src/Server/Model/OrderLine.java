package Server.Model;

import java.io.Serializable;


public class OrderLine {

    private Tool toolToOrder;
    private int orderQty;

    public OrderLine(Tool tool, int orderQty) {
        setItemToOrder(tool);
        setOrderQty(orderQty);
    }

    public Tool getItemToOrder() {
        return toolToOrder;
    }

    public void setItemToOrder(Tool toolToOrder) {
        this.toolToOrder = toolToOrder;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    //TODO we may actually need the association between tool and supplier for orderlines... for now i am ignoring
    @Override
    public String toString() {
        return String.format("%-25s %-10s", "Item description:", toolToOrder.getName()) + "\n" +
                String.format("%-25s %-10s", "Amount ordered:", getOrderQty()) + "\n" +
                String.format("%-25s %-10s", "Supplier:", toolToOrder.getSupplierID()) + "\n\n";
    }
}
