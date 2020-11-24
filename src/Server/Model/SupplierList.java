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

    public ArrayList<Supplier> getSupplierList() {
        return supplierList;
    }
}
