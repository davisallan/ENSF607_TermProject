package CommonModel.Model;

public class International extends Supplier {

    private double importTax;

    public International (int supplierID, String companyName, String type,
                          String address, String contact, double importTax) {
        super(supplierID,companyName,type,address,contact);
        setImportTax(importTax);
    }

    public void setImportTax(double importTax) {
        this.importTax = importTax;
    }
}
