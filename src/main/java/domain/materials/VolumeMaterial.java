package domain.materials;

public class VolumeMaterial extends Material {
        int length;


    public VolumeMaterial(int id, String name, String details, int unitPrice, int length) {
        super(id, name, details, unitPrice);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
