package domain.items;

public interface CustomerRepository {
    Iterable<Customer> findAll() throws CustomerNotFound, DBException;

    Customer findCustomer(String email) throws CustomerNotFound, DBException;

    Customer commitCustomer(Customer customer) throws DBException;
}
