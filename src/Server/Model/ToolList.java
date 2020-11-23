package Server.Model;

import java.util.ArrayList;


public class ToolList {

    private ArrayList<Tool> ToolList;

    private Order order;

    public ToolList(Order order) {
        ToolList = new ArrayList<>();
        setOrder(order);
    }

    public ArrayList<Tool> getToolList() {
        return ToolList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addItem(Tool i) {
        ToolList.add(i);
    }

    public void decreaseQty(Tool tool, int amount) {
        tool.decreaseQty(tool, amount, order);
    }

}
