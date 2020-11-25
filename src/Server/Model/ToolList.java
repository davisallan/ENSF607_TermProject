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

    public void display() {
        for (Tool t: toolList) {
            System.out.println(t);
        }
    }

    public void clearList() {
        toolList.clear();
    }

    public ArrayList<Tool> getToolList() {
        return toolList;
    }

    public Order getOrder() {
        if (order == null)
            order = new Order();
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addTool(Tool i) {
        toolList.add(i);
    }

    public void decreaseQty(Tool tool) {
        tool.decreaseQty(order);
    }

}
