package Server.Model;

import java.io.Serializable;

public abstract class Customer implements Serializable {

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s", getCustomerId(), getLastName(), getFirstName(), getAddress(), getPostalCode(), getPhoneNum(), getType());
    }
}
