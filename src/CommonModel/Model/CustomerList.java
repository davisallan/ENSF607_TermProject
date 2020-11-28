package CommonModel.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerList {

    private ArrayList<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }

    public void buildCustomer(ResultSet rs) {
        try {
            while (rs.next()) {
                if (rs.getString("ctype").equals("R")) {
                    addCustomer(new Residential(
                            rs.getInt("customerId"),
                            rs.getString("lName"),
                            rs.getString("fName"),
                            rs.getString("cType").charAt(0),
                            rs.getString("phoneNum"),
                            rs.getString("address"),
                            rs.getString("postalCode")));
                } else {
                    addCustomer(new Commercial(
                            rs.getInt("customerId"),
                            rs.getString("lName"),
                            rs.getString("fName"),
                            rs.getString("cType").charAt(0),
                            rs.getString("phoneNum"),
                            rs.getString("address"),
                            rs.getString("postalCode")));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        customers.add(c);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
