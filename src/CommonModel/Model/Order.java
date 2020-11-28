package CommonModel.Model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Order {

    private ArrayList<OrderLine> orderLines;
    private int orderNum;
    private LocalDate date;
    FileWriter fileWriter;

    public Order() {
        orderLines = new ArrayList<>();
        orderNum = 10000 + new Random().nextInt(90000);
        date = java.time.LocalDate.now();
        writeToFile();
    }

    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
        writeToFile();
    }

    private void writeToFile() {
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
