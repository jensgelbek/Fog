package infrastructure;

import api.Utils;
import api.Fog;
import domain.items.*;
import domain.materials.*;
import infrastructure.*;
import integration.UserStories;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static entries.Migrate.runMigrations;
import static org.junit.Assert.*;

public class ApiDBTest {
    Fog api;
    /**
     * Before you run this script create a user 'fogtest' and grant access to the database:
     *
     * <pre>
     * DROP USER IF EXISTS fogtest@localhost;
     * CREATE USER fogtest@localhost IDENTIFIED BY 'fog';;
     * GRANT ALL PRIVILEGES ON fogtest.* TO fogtest@localhost;
     * </pre>
     */
    Order order;
    Order order2;
    Order order3;
    Customer customer;
    Customer customer2;
    Seller seller;
    Carport carport;
    Carport carport2;
    Carport carport3;
    Stykliste stykliste;
    Material unitMaterial;
    StykListeLinje unitstykListeLinje;
    Material volumenMaterial;
    StykListeLinje volumestykListeLinje;

    static void resetTestDatabase() {
        String URL = "jdbc:mysql://localhost:3306/?serverTimezone=CET";
        String USER = "fogtest";

        InputStream stream = UserStories.class.getClassLoader().getResourceAsStream("resettest.sql");
        if (stream == null) throw new RuntimeException("resettest.sql");
        try (Connection conn = DriverManager.getConnection(URL, USER, "fog")) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Done running migration");
    }

    @Before
    public void setup() throws DBException, SQLException {
        //lav ny test tom DB
        resetTestDatabase();
        String url = "jdbc:mysql://localhost:3306/fogtest?serverTimezone=CET";
        String user = "fogtest";

        //kør migration script på test DB
        Database db = new Database(url, user);
        try {
            runMigrations(db);
        } catch (IOException e) {
            System.out.println("iofejl");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("sqlfejl");
            throwables.printStackTrace();
        }
        //instantier alle repos med DB repos med test DB
        api = new Fog(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteRepository(db),new DBStyklisteRepository(db),new DBMaterialRepository(db) );
        byte[] salt= Utils.generateSalt();
        byte[] secret=Utils.calculateSecret(salt,"password");
        customer=new Customer("customer","et sted","customer@jorden.dk",123,false,salt,secret);
        customer= api.commitCustomer(customer);
        byte[] salt2=Utils.generateSalt();
        byte[] secret2=Utils.calculateSecret(salt2,"password2");
        customer2=new Customer("customer2","et sted","customer2@jorden.dk",456,false,salt2,secret2);
        customer2= api.commitCustomer(customer2);
        seller=new Seller("seller","Morten Olsen",salt,secret);
        seller.setSellerID(api.commitSeller(seller));
        carport=new Carport(3600,6300,false,"trapez",2100,2100);
        carport.setCarportID(api.commitCarport(carport));
        carport2=new Carport(3000,6000,false,"trapez",2400,2100);
        carport2.setCarportID(api.commitCarport(carport2));
        carport3=new Carport(5400,6300,false,"trapez",0,0);
        carport3.setCarportID(api.commitCarport(carport3));
        order=new Order(LocalDate.now(),null,null,"customer@jorden.dk",carport.getCarportID(),100000,"tilbud");
        order.setOrderID(api.commitOrder(order));
        order2=new Order(LocalDate.now().minusDays(2),LocalDate.now(),null,"customer2@jorden.dk",carport2.getCarportID(),5000,"ordre");
        order2.setOrderID(api.commitOrder(order2));
        order3=new Order(LocalDate.now().minusDays(2),LocalDate.now(),LocalDate.now().plusDays(10),"customer2@jorden.dk",carport3.getCarportID(),5000,"ordre");
        order3.setOrderID(api.commitOrder(order3));

        stykliste=new Stykliste();
        unitMaterial=api.findUnitMaterial(201);
        unitstykListeLinje=new StykListeLinje(unitMaterial,10,"unit materiale");
        stykliste.getUnitListe().add(unitstykListeLinje);
        volumenMaterial=api.findVolumeMaterialNameLenght("stolpe",3000);
        volumestykListeLinje=new StykListeLinje(volumenMaterial,25,"stolper");
        stykliste.getVolumenListe().add(volumestykListeLinje);
        api.commitStykliste(stykliste,order.getOrderID());
        stykliste= api.findStykliste(order.getOrderID());
        unitstykListeLinje=stykliste.unitListe.get(0);
        volumestykListeLinje=stykliste.volumenListe.get(0);
    }



   @Test
    public void updateCarport() throws SQLException, DBException {
        Carport carport=new Carport(4200,4200,false,"trapez",1800,1800);
        carport.setCarportID(api.commitCarport(carport));
        Carport oprindeligCarport=api.findCarport(carport.getCarportID());
        carport.setLenght(5100);
        carport.setShedWidth(4700);
        api.updateCarport(carport);
        Carport nyCarport=api.findCarport(carport.getCarportID());
        //er det ændret...
        assertEquals(carport,nyCarport);
        // er det forskelligt far den oprindelige
       assertTrue(nyCarport!=oprindeligCarport);
    }
    @Test
    public void findAllMaterailTypes(){
        List<Material> materialList=api.findAllMaterailTypes();
        assertEquals(21,materialList.size());
        assertNotNull(materialList.get(0));
        assertNotNull(materialList.get(20));
    }

    @Test
    public void updateOrderStatus() throws DBException {
        Order denneOrder=api.findOrder(order.getOrderID());
        api.updateOrderstatus(denneOrder.getOrderID(),"ny status");
        Order opdateretOrder=api.findOrder(order.getOrderID());
        assertEquals("ny status",opdateretOrder.getStatus());
    }
    @Test
    public void updateOrderPrice() throws DBException {
        Order denneOrder=api.findOrder(order.getOrderID());
        api.updateOrderPrice(denneOrder.getOrderID(),-500);
        Order opdateretOrder=api.findOrder(order.getOrderID());
        assertEquals(-500,opdateretOrder.getPrice());
    }

    @Test
    public void setOdreDato() throws DBException {
        Order denneOrder=api.findOrder(order.getOrderID());
        LocalDate juleaften=LocalDate.of(2010,12,24);
        api.setOdreDato(denneOrder.getOrderID(),juleaften);
        Order opdateretOrder=api.findOrder(denneOrder.getOrderID());
        assertEquals(juleaften,opdateretOrder.getOrdredato());
    }

    @Test
    public void setLeveringsDato() throws DBException {
        Order denneOrder=api.findOrder(order.getOrderID());
        LocalDate andenjueldag=LocalDate.of(2010,12,26);
        api.setLeveringsDato(denneOrder.getOrderID(),andenjueldag);
        Order opdateretOrder=api.findOrder(denneOrder.getOrderID());
        assertEquals(andenjueldag,opdateretOrder.getLeveringsDato());
    }

}