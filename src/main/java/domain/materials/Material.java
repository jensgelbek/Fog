package domain.materials;

public abstract class Material {
    int id;
    String name;
    String details;
    int unitPrice;

    public Material(int id, String name, String details, int unitPrice) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    public abstract int getprice ();
}