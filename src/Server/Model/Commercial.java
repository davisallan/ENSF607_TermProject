package Server.Model;

public class Commercial extends Customer {

    public Commercial (int customerId, String lastName, String firstName, char type,
                       String phoneNum, String address, String postalCode)
    {
        super(customerId,lastName,firstName,type,phoneNum,address,postalCode);
    }

}
