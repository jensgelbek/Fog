package domain.materials;

public class UnitMaterial extends Material{
    String unitType;

    public UnitMaterial(int id, String name, String details, int unitPrice, String unitType) {
        super(id, name, details, unitPrice);
        this.unitType = unitType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
