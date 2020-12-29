package domain.materials;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "UnitMaterial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitType='" + unitType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitMaterial)) return false;
        UnitMaterial that = (UnitMaterial) o;
        return Objects.equals(unitType, that.unitType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitType);
    }
}
