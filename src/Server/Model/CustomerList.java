package Server.Model;

import java.util.ArrayList;

public class CustomerList {

    private ArrayList<Customer> customerList;

    public CustomerList() {
        customerList = new ArrayList<>();
    }

    public void addCustomer(Customer c) {
        customerList.add(c);
    }
}
