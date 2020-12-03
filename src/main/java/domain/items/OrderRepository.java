package domain.items;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {
    List<Order> findAll() throws SQLException;
    Order find(int id) throws DBException;
    int commit(Order order);

}