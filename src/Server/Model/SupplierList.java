package Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class SupplierList implements Serializable {

    private final ArrayList<Supplier> supplierList;

    public SupplierList() {
        supplierList = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        supplierList.add(supplier);
    }

    public void displaySuppliers() {
        for (Supplier s : supplierList){
            System.out.println(s);
        }
    }

    public Supplier findSupplier (int supplierID) {
        for (Supplier s : supplierList) {
            if (s.getSupplierID() == supplierID) {
                return s;
            }
        }
        return null;
    }
}
