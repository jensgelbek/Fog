package domain.materials;

import domain.items.Carport;
import domain.items.DBException;

import java.util.List;

public interface MaterialRepository {

    void updatePrice(String name,int newPrice) throws DBException;
    List<Material> getAllTypes() throws DBException;
}
