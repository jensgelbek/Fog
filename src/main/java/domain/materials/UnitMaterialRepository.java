package domain.materials;

import domain.items.Carport;
import domain.items.DBException;

import java.util.List;

public interface UnitMaterialRepository {
    List<UnitMaterial> findAll() throws DBException;

    UnitMaterial find(int parseInt) throws DBException;
    UnitMaterial findName(String name) throws DBException;

    int commit(UnitMaterial unitMaterial);

 //   void updatePrice(String name,int newPrice) throws DBException;
}
