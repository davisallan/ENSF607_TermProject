package Server.Model;

public class Electrical extends Tool {

    private String powerType;

    public Electrical(int id, String name, String type, int quantity, double price, int supplierID, String supplierName, String powerType) {
        super(id, name, type, quantity, price, supplierID, supplierName);
        setPowerType(powerType);
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    @Override
    public String toString() {
        return String.format("%-12d %-16s %-20s %-10.2f %-8d %-12s %10s", getId(), getName(), getType(), getPrice(), getQuantity(), getSupplierID(), getPowerType());
    }
}
