package PreProjectExercise;

import java.util.ArrayList;

/**
 * Provides the data fields and methods to create a data type representing the inventory in a tool shop.
 * Responsible for maintaining the list of all Items (tools) in the shop.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class Inventory {
    /**
     * ArrayList of Items representing the tools in the shop
     */
    private final ArrayList<Tool> toolList;

    public Inventory() {
        toolList = new ArrayList<>();
    }

    /**
     * Returns the ArrayList of Items representing the inventory of the shop
     * @return ArrayList of Items
     */
    public ArrayList<Tool> getToolList() {
        return toolList;
    }

    public void addItem(Tool tool) {
        toolList.add(tool);

    }
}
