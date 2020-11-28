package CommonModel.Model;

public class OrderLine {

    private Tool tool;
    private int orderQty;
    private int supplierId;
    private int orderId;

    public OrderLine(Tool tool, int orderQty, Order order) {
        setToolToOrder(tool);
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

    public Tool getToolToOrder() {
        return tool;
    }

    public void setToolToOrder(Tool toolToOrder) {
        this.tool = toolToOrder;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return String.format("%-25s %-10s", "Item description:", tool.getName()) + "\n" +
                String.format("%-25s %-10s", "Amount ordered:", getOrderQty()) + "\n" +
                String.format("%-25s %-10s", "Supplier:", tool.getSupplierName()) + "\n";
    }
}
