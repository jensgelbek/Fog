package domain.materials;

public class StykListeLinje {

    private Material materiale;
    private int quantity;
    private String description;
    private int id;


    public StykListeLinje(Material materiale, int quantity, String description) {
        this.materiale = materiale;
        this.quantity = quantity;
        this.description = description;
    }

    public StykListeLinje(Material materiale, int quantity, String description, int id) {
        this.materiale = materiale;
        this.quantity = quantity;
        this.description = description;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Material getMateriale() {
        return materiale;
    }

    public void setMateriale(VolumeMaterial materiale) {
        this.materiale = materiale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMateriale(Material materiale) {
        this.materiale = materiale;
    }

    @Override
    public String toString() {
        return "StykListeLinje{" +
                "materiale=" + materiale +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
