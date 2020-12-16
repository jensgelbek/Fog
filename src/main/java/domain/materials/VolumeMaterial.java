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

    @Override
    public String toString() {
        return "VolumeMaterial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", unitPrice=" + unitPrice +
                ", length=" + length +
                '}';
    }

    @Override
    public int getprice() {
        return this.unitPrice*this.length/1000;
    }
}
