package Server.Model;

public abstract class Customer {

    private int customerId;
    private String lastName;
    private String firstName;
    private char type;
    private String phoneNum;
    private String address;
    private String postalCode;

    public Customer(int customerId, String lastName, String firstName, char type, String phoneNum, String address, String postalCode) {
        setCustomerId(customerId);
        setLastName(lastName);
        setFirstName(firstName);
        setType(type);
        setPhoneNum(phoneNum);
        setAddress(address);
        setPostalCode(postalCode);
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
