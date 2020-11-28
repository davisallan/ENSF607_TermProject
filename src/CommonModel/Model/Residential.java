package CommonModel.Model;

public class Residential extends Customer {

    public Residential (int customerId, String lastName, String firstName, char type,
                        String phoneNum, String address, String postalCode){
        super(customerId,lastName,firstName,type,phoneNum,address,postalCode);
    }
}
