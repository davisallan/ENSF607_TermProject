package CommonModel.Model;

import java.util.ArrayList;

public class SupplierList {

    private ArrayList<Supplier> suppliers;

    public SupplierList() {
        suppliers = new ArrayList<>();
    }

    public void clearList() {
        suppliers.clear();
    }
}
