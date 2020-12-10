package domain.bom;

import domain.items.Carport;
import domain.materials.MaterialRepository;
import domain.materials.MaterialType;

public class BomService {
    MaterialRepository materials;

    public Bom calculateBom(Carport carport) {
        Bom bom = new Bom();

        bom.add(materials.find(MaterialType.RIM_WOOD, 200, 30), 2,  carport.getLenght(), "side af rim");

        return bom;
    }

}
