package domain.items;

public interface CustomerRepository {
    Iterable<Customer> findAll() throws DBException;

    Customer findCustomer(String email) throws  DBException;

    Customer commitCustomer(Customer customer) throws DBException;
}
