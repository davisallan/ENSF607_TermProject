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
}
