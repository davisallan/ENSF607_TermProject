package Server.Model;

import java.io.*;
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

    private final ArrayList<OrderLine> orderLines;
    private final int orderNum;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public Order() {
        orderLines = new ArrayList<>();
        orderNum = 10000 + new Random().nextInt(90000);
        try {
            fileWriter = new FileWriter("order.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createOrderFile();
    }

    private void createOrderFile() {
//        try {
////            bufferedWriter.write("Order created\n");
////            bufferedWriter.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
        System.out.println("Adding order line");
        try {
            bufferedWriter.write("OrderLine added");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
