package Server.Model;

import java.io.Serializable;
import java.util.ArrayList;


public class ToolList implements Serializable {

    private ArrayList<Tool> toolList;

    private Order order;

    public ToolList(Order order) {
        toolList = new ArrayList<>();
        setOrder(order);
    }

    public ArrayList<Tool> getToolList() {
        return toolList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addItem(Tool i) {
        toolList.add(i);
    }

    public void decreaseQty(Tool tool, int amount) {
        tool.decreaseQty(tool, amount, order);
    }

}
