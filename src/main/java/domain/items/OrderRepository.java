package domain.items;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {
    List<Order> findAll() throws SQLException;
    List<Order> findAllWithEmail(String email) throws SQLException;
    Order find(int id) throws DBException;
    void updateStatus(int id,String status) throws DBException;
    int commit(Order order);

}