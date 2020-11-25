package Server.Model;

public class Electrical extends Tool {

    private String powerType;

    public Electrical(int id, String name, String type, int quantity, double price, int supplierID, String powerType) {
        super(id, name, type, quantity, price, supplierID);
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
        return String.format("%s %18s %14.2f %10s %15s %10s", getId(), getName(), getPrice(), getQuantity(), getSupplierID(), getPowerType());
    }
}
