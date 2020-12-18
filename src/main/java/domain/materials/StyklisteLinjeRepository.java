package domain.materials;

public interface StyklisteLinjeRepository {
    StykListeLinje find(int styklisteLinjeId);
    int commit(StykListeLinje stykListeLinje,int orderId);
    void updateAntal(int id, int antal);
}
