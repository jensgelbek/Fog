package domain.items;

import java.sql.SQLException;
import java.util.List;

public interface SellerRepository {
    List<Seller> findAll() throws SQLException, DBException;
    Seller find(int id) throws DBException;
    int commit(Seller seller);

}
