package Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList implements Serializable {

    private ArrayList<Customer> customerList;

    public CustomerList() {
        customerList = new ArrayList<>();
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
