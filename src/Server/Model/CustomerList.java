package Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList implements Serializable {

    private ArrayList<Customer> customerList;

    public CustomerList() {
        customerList = new ArrayList<>();
    }

    public Residential buildResidential(String [] customerInfo) {
        return new Residential(Integer.parseInt(customerInfo[0]),
                customerInfo[2], customerInfo[1], customerInfo[6].charAt(0), customerInfo[5],
                customerInfo[3], customerInfo[4]);
    }

    public Commercial buildCommercial(String [] customerInfo) {
        return new Commercial(Integer.parseInt(customerInfo[0]),
                customerInfo[2], customerInfo[1], customerInfo[6].charAt(0), customerInfo[5],
                customerInfo[3], customerInfo[4]);
    }

    public void addCustomer(Customer c) {
        customerList.add(c);
    }

    public void display() {
        for (Customer c: customerList) {
            System.out.println(c);
        }
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
}
