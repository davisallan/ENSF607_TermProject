package CommonModel.Model;

public class OrderLine {

    private Tool toolToOrder;
    private int toolId;
    private int orderQty;
    private int supplierId;
    private int orderId;

    public OrderLine(Tool tool, int orderQty, Order order) {
        setItemToOrder(tool);

        setOrderQty(orderQty);
        setOrderId(order.getOrderNum());
        setSupplierId(tool.getSupplierID());
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
                String.format("%-25s %-10s", "Supplier:", toolToOrder.getSupplierName()) + "\n";
    }
}
