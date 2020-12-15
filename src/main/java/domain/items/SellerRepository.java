package domain.items;

import java.sql.SQLException;
import java.util.List;

public interface SellerRepository {
    List<Seller> findAll() throws SQLException, DBException;

    Seller find(String userName) throws DBException;

    int commit(Seller seller);
    void updatePassword(String name, String password);
}
