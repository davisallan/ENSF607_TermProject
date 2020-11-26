package Server.Model;

import java.io.*;
import java.time.LocalDate;
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
    private LocalDate date;
    FileWriter fileWriter;

    public Order() {
        orderLines = new ArrayList<>();
//        orderNum = 10000 + new Random().nextInt(90000);
        orderNum = 10000;
        date = java.time.LocalDate.now();
        writeToFile();
    }

    public void writeToFile() {
        try {
            fileWriter = new FileWriter("order.txt");
            fileWriter.write(this.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
        writeToFile();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n***************************************************************\n");
        sb.append(String.format("%-25s %-20s", "ORDER ID:", getOrderNum())).append("\n");
        sb.append(String.format("%-25s %-20s", "Date Ordered:", getDate())).append("\n");
        for (OrderLine ol: orderLines) {
            sb.append("\n").append(ol);
        }
        sb.append("\n***************************************************************\n");
        return sb.toString();
    }
}
