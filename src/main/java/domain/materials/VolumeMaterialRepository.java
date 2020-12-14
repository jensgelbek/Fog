package domain.materials;

import domain.items.DBException;

import java.util.List;

public interface VolumeMaterialRepository {
    List<VolumeMaterial> findAll() throws DBException;
    List<VolumeMaterial> findAllName(String name) throws DBException;

    VolumeMaterial find(int parseInt) throws DBException;
    VolumeMaterial find(String name, int lenght) throws DBException;

    int commit(UnitMaterial unitMaterial);

    void updateNameWithPrice(String name,int newPrice);
}

