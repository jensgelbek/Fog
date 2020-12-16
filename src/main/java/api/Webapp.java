package api;

import domain.items.*;
import domain.materials.*;
import infrastructure.DBStyklisteLinjeRepository;


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
    private static VolumeMaterialRepository volumeMaterials;
    private final UnitMaterialRepository unitMaterials;
    private static StyklisteLinjeRepository styklisteLinjer;
    private static StyklisteRepository styklister;

    public Webapp(OrderRepository orders, CustomerRepository customers, CarportRepository carports, SellerRepository sellers, VolumeMaterialRepository volumeMaterials, UnitMaterialRepository unitMaterials,StyklisteLinjeRepository styklisteLinjer,StyklisteRepository styklister) {
        this.orders = orders;
        this.customers = customers;
        this.carports = carports;
        this.sellers = sellers;
        this.volumeMaterials=volumeMaterials;
        this.unitMaterials=unitMaterials;
        this.styklisteLinjer=styklisteLinjer;
        this.styklister=styklister;

    }

    public static int getVersion() {
        return VERSION;
    }

    public static String getTitle() {
        return TITLE;
    }

    public List<Order> findAllOrders() throws SQLException {
        return orders.findAll();
    }


    public List<Order> findAllOrdersWithEmail(String email) throws SQLException {
        return orders.findAllWithEmail(email);
    }

    public void updateOrderstatus(int id, String status) {
        try {
            orders.updateStatus(id, status);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void setOdreDato(int id, LocalDate ordreDato) {
        try {
            orders.setOrdreDato(id, ordreDato);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void setLeveringsDato(int id, LocalDate leveringsdato)  {

        try {
            orders.setLeveringsDato(id, leveringsdato);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    public Carport findCarport(int Id) throws DBException {
        return carports.find(Id);
    }

    public List<Carport> findAlleCarports() throws DBException {
        return carports.findAll();
    }

    ;

    public Customer findCustomer(String email) throws DBException, CustomerNotFound {
        return customers.findCustomer(email);
    }

    public List<Customer> findAllCustomers() throws DBException, CustomerNotFound {
        return (List<Customer>) customers.findAll();
    }


    public int commitOrder(Order order) {
        return orders.commit(order);
    }

    ;

    public Order findOrder(int id) throws DBException {
        return orders.find(id);
    }


    public Customer commitCustomer(Customer customer) throws DBException {
        return customers.commitCustomer(customer);
    }

    public int commitCarport(Carport carport) throws SQLException {
        return carports.commit(carport);
    }

    public int commitSeller(Seller seller) {
        return sellers.commit(seller);
    }

    public Seller findSeller(String userName) throws DBException {
        return sellers.find(userName);
    }

    ;

    public List<Seller> findAllSellers() throws SQLException, DBException {
        return sellers.findAll();
    }

    public static void main(String[] args) {
        System.out.println(findVolumeMaterial(2));
    }

    public static VolumeMaterial findVolumeMaterial(int id){
        try {
            return volumeMaterials.find(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public VolumeMaterial findVolumeMaterialNameLenght(String name, int lenght){
        try {
            return volumeMaterials.find(name,lenght);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<VolumeMaterial> findAllVolumeMaterials(){
        try {
            return volumeMaterials.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<VolumeMaterial> findAllVolumeMaterialsName(String name){
        try {
            return volumeMaterials.findAllName(name);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public UnitMaterial findUnitMaterial(int id){
        try {
            return unitMaterials.find(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public UnitMaterial findUnitMaterial(String name){
        try {
            return unitMaterials.findName(name);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<UnitMaterial> findAllUnitMaterilas(){
        try {
            return unitMaterials.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }return null;
    }
    public void updateUnitMaterislPrice(String name,int price){
        try {
            unitMaterials.updatePrice(name, price);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    public void commitStyklisteLinie(StykListeLinje stykListeLinje, int ordreId){
        // styklisteLinjer.commit(stykListeLinje,ordreId);
        // DBStyklisteLinjeRepository.commit(stykListeLinje,ordreId);
    }
    public Stykliste findStykliste(int ordreId){
        try {

            return styklister.findStykliste(ordreId);

        } catch (DBException e) {
            e.printStackTrace();
        }
        return new Stykliste();
    }

    public void commitStykliste(Stykliste stykliste, int orderId){
        styklister.commitStykliste(stykliste,orderId);
    }

    public void updateSellerPassword(String name,String oldPassword, String newPassword){
        sellers.updatePassword(name,oldPassword,newPassword);

    }
}
