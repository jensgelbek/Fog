package domain.items;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll() ;
    Customer find(int parseInt) ;
}