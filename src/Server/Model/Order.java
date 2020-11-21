package Server.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Provides the data fields and methods to create a data type representing a daily order of tools for a tool shop.
 * OrderLines are automatically generated and added to this object when a tool goes below a quantity of 40.
 *
 * @author Davis Allan, 10016543
 * @version 1.0
 * @since 2020-10-10
 */
public class Order {
    /**
     * ArrayList of OrderLines which represent the information for ordering additional stock
     */
    private final ArrayList<OrderLine> orderLines;
    /**
     * Randomly generated 5 digit integer ID
     */
    private final int orderNum;

    /**
     * Constructs the Order object and assigns a randomized 5 digit integer ID to orderNum
     * random 5 digit order number code adapted from https://programming.guide/java/genercate-random-number-of-given-length.html
     */
    public Order() {
        orderLines = new ArrayList<>();
        orderNum = 10000 + new Random().nextInt(90000);
    }

    /**
     * Returns the order number
     * @return order number
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * Adds the OrderLine object to the Order's ArrayList instance variable.
     * @param line the OrderLine to be added
     */
    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
        System.out.println("\033[0;32m" + "\t*OrderLine created for Order " + getOrderNum() + "\033[0m");
    }

    /**
     * Returns the String representation of an Order with all of its OrderLines. If there are no OrderLines,
     * it will return with a message that the "Order is currently blank". Otherwise, it will output all
     * orderLines with their accompanying information.
     * @return String representation of an Order object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (orderLines.size() < 1) {
            sb.append("\n***************************************************************\n");
            sb.append("Order is currently blank\n\n");
            sb.append("\n***************************************************************\n");
            return sb.toString();
        }
        else {
            sb.append("\n***************************************************************\n");
            sb.append(String.format("%-25s %-20s", "ORDER ID:", getOrderNum())).append("\n");
            sb.append(String.format("%-25s %-20s", "Date Ordered:",java.time.LocalDate.now())).append("\n\n");
            for (OrderLine ol: orderLines) {
                sb.append(ol);
            }
            sb.append("\n***************************************************************\n");
            return sb.toString();
        }
    }
}
