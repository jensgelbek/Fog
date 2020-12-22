package domain.items;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    List<Order> findAll() throws SQLException;

    List<Order> findAllWithEmail(String email) throws SQLException;

    Order find(int id) throws DBException;

    void updateStatus(int id, String status) throws DBException;

    void updatePrice(int id,int newPrice)throws DBException;

    int commit(Order order);

    void setOrdreDato(int id, LocalDate ordreDato) throws DBException;

    void setLeveringsDato(int id, LocalDate leveringsDato) throws DBException;
}