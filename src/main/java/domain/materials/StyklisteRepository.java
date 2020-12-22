package domain.materials;

import domain.items.DBException;

public interface StyklisteRepository {
    Stykliste findStykliste(int orderId) throws DBException;
    void commitStykliste(Stykliste stykliste,int orderId);
    void deleteStykliste(int orderId);
}
