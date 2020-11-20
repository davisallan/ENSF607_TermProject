package PreProjectExercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * Manages the file input in order to construct the Shops database from two csv files. Responsible for reading the
 * items.txt and suppliers.txt from the current working directory and transforming the information into the correct
 * objects and data types.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class FileMgr {

    /**
     * Default constructor for FileMgr, class is used for its static methods only
     */
    public FileMgr() {

    }

    /**
     * Reads in items.txt to create the Item objects and sets association relationship between Item and Supplier.
     *
     * @param fileName name of the ".txt" file that contains the item information
     * @return Inventory object containing the inventory database
     */
    public static Inventory loadInventory(String fileName) {
        Inventory inventory = new Inventory();
        //handling IOException from reading in the text file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            do {
                String[] itemInfo = line.split(";");
                Tool tool = new Tool(Integer.parseInt(itemInfo[0]), itemInfo[1],
                        Integer.parseInt(itemInfo[2]), Double.parseDouble(itemInfo[3]), Integer.parseInt(itemInfo[4]));

                //adding item to the inventory
                inventory.addItem(tool);

                line = reader.readLine();
            } while (line != null);

        } catch (IOException e) {
            System.out.println("Error occurred, ensure 'items.txt' is in working directory");
        }
        return inventory;
    }

}