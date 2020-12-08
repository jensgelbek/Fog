package api;

import domain.items.*;


import java.sql.SQLException;

import java.time.LocalDate;
import java.util.List;

public class Webapp {
    private final static int VERSION = 1;
    private final static String TITLE = "Fog";

    private final OrderRepository orders;
    private final CustomerRepository customers;
    private final CarportRepository carports;
    private final SellerRepository sellers;

    public Webapp(OrderRepository orders, CustomerRepository customers,CarportRepository carports,SellerRepository sellers) {
        this.orders =orders;
        this.customers=customers;
        this.carports=carports;
        this.sellers=sellers;

    }
    
    public static int getVersion() {
        return VERSION;
    }
    public static String getTitle() {
        return TITLE;
    }

    public  List<Order> findAllOrders() throws SQLException {
       return orders.findAll();
    }


    public  List<Order> findAllOrdersWithEmail(String email) throws SQLException {
        return orders.findAllWithEmail(email);
    }
    public void updateOrderstatus(int id, String status){
        try {
            orders.updateStatus(id,status);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    public void setOdreDato(int id, LocalDate ordreDato){
        try {
            orders.setOrdreDato(id,ordreDato);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    public void setLeveringsDato(int id, LocalDate leveringsdato){
        try {
            orders.setLeveringsDato(id,leveringsdato);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    public Carport findCarport(int Id) throws DBException {return carports.find(Id);}
    public List<Carport> findAlleCarports()throws DBException {return carports.findAll();};

    public Customer findCustomer(String email) throws DBException, CustomerNotFound {
        return customers.findCustomer(email);
    }
    public List<Customer> findAllCustomers() throws DBException, CustomerNotFound {
        return (List<Customer>) customers.findAll();
    }

  

    public int commitOrder(Order order){return orders.commit(order);};
    public Order findOrder(int id) throws DBException {return orders.find(id);}


    public Customer commitCustomer(Customer customer) throws DBException {
        return customers.commitCustomer(customer);
    }
    public int commitCarport(Carport carport) throws SQLException {
       return carports.commit(carport);
    }
    public int commitSeller(Seller seller){return sellers.commit(seller);}
    public Seller findSeller(String userName) throws DBException {return sellers.find(userName);};
    public List<Seller> findAllSellers () throws SQLException, DBException {return sellers.findAll();};
}
