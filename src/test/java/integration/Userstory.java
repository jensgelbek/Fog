package java.integration;

import api.Utils;
import api.Webapp;
import domain.items.*;
import domain.materials.*;
import infrastructure.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Test;

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
import static org.junit.Assert.*;

public class Userstory {
    Webapp api;
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
        api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteLinjeRepository(db),new DBStyklisteRepository(db),new DBMaterialRepository(db) );
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
    public void userstory17og23() throws DBException, SQLException {
        //denne test dækker user stories:
        //#17 Som kunde vil jeg gerne kunne oprette en carport med fladt tag
        //#23 som kunde vil jeg gerne kunne se den carport jeg har bestilt

        // find antal carporte inden den nye bliver gemt
        int antal=api.findAlleCarports().size();

        // Lav en carport og gem den
        Carport carport=new Carport(3600,6300,false,"trapez",0,0);
        int carportId= api.commitCarport(carport);
        carport.setCarportID(carportId);

        //hent carporten igen og tjek om det er den samme: #17 + #23
        Carport DBCarport=api.findCarport(carportId);
        assertEquals(carport,DBCarport);

        // find antal carporte efetr den nye er tilføjet og tjek om det er en højere
        int nytAntal=api.findAlleCarports().size();
        assertEquals(antal+1,nytAntal);

    }
    @Test
    public void userstory25og26() throws DBException {
        //denne test dækker user stories:
        //#25 Som bruger vil jeg gerne kunne oprette mig i systemet
        //#26 som kunde vil jeg gerne kunne logge ind i systemet

        //Lav den customer der skal oprettes i DB
        String password="123456";
        String email="jens@hansen.dk";
        byte[] salt= Utils.generateSalt();
        byte[] secret=Utils.calculateSecret(salt,password);
        Customer customer=new Customer("Jens Hansen","en vej ",email,45455656,false,salt,secret);

        //Customer findes ikke allerede
        assertEquals(null,api.findCustomer(email));

        //find nuværende antal customers
        int antal=api.findAllCustomers().size();

        //Gem customer i DB
        api.commitCustomer(customer);

        //hent i DB igen
        Customer DBCustomer= api.findCustomer(email);

        //tjek om den hentede er ens med den gemte  #25
        assertEquals(customer,DBCustomer);

        //tjek om der en customer mere i DB  #25
        int nytAntal=api.findAllCustomers().size();
        assertEquals(nytAntal,antal+1);

        //tjek om customer kan logge ind med ønskede password.  #26
        assertTrue(Utils.checkPassword(password,DBCustomer.getSecret(),DBCustomer.getSalt()));

        //tjek at customer ikke kan logge ind med forkert password   #26
        assertTrue(!Utils.checkPassword("wrongpassword",DBCustomer.getSecret(),DBCustomer.getSalt()));

    }
    @Test
    public void userstory27og28() throws DBException, SQLException {
        //denne test dækker user story:
        //#30 Som sælger vil jeg gerne kunne se alle ordrer)
        Order newOrder=new Order(LocalDate.now(),null,null,"customer@jorden.dk",carport.getCarportID(),80000,"tilbud");
        newOrder.setOrderID(api.commitOrder(newOrder));
        List<Order> orderlist=api.findAllOrders();
        //er der 2 ordrer i listen...
        assertEquals(4,orderlist.size());

        //er den  ordre i listen der lige er lagt der..
        assertTrue(orderlist.indexOf(newOrder)>-1);

        //kan jeg hente den lige lagte ordre i DB
        Order hentetOrder=api.findOrder(newOrder.getOrderID());
        assertEquals(newOrder,hentetOrder);
    }

    @Test
    public void userstory30() throws DBException, SQLException {
        //denne test dækker user stories:
        //#30 Som sælger vil jeg gerne kunne se og redigere stykliste..(kan kun redigere antal)
        int unitAntal=unitstykListeLinje.getQuantity();
        int volumenAntal=volumestykListeLinje.getQuantity();
        System.out.println(unitstykListeLinje.getId());
        api.updateStykListeLinjeAntal(unitstykListeLinje.getId(),unitAntal+10);
        api.updateStykListeLinjeAntal(volumestykListeLinje.getId(),volumenAntal+20);
        Stykliste updatedStykliste=api.findStykliste(order.getOrderID());
        assertEquals(unitAntal+10,updatedStykliste.unitListe.get(0).getQuantity());
        assertEquals(volumenAntal+20,updatedStykliste.volumenListe.get(0).getQuantity());
    }

    @Test
    public void userstory31() throws DBException, SQLException {
        //denne test dækker user stories:
        //#31 Som kunde vil jeg gerne kunne se stykliste efter betaling
        Stykliste DBStykliste=api.findStykliste(order.getOrderID());
        //er det den rigtige stykliste der er hentet ?
        //er der kun en unit og en volumen linje ?
        assertEquals(1,DBStykliste.unitListe.size());
        assertEquals(1,DBStykliste.volumenListe.size());
        // er det de rigitge materialer der er...
        assertEquals(unitMaterial,DBStykliste.unitListe.get(0).getMateriale());
        assertEquals(volumenMaterial,DBStykliste.volumenListe.get(0).getMateriale());


    }
    @Test
    public void userstory32() throws DBException, SQLException {
        //denne test dækker user stories:
        //#32 som sælger vil jeg gerne kunne logge ind
        // oprettelse af sælger
        // ændring af password (hvis 1234)

        String password="1234";
        byte[] salt=Utils.generateSalt();
        byte[] secret=Utils.calculateSecret(salt,password);
        Seller seller=new Seller("hh","Helge Hansen",salt,secret);

        //find antal sellers i DB nu.
        int antal=api.findAllSellers().size();

        //opret seller i DB...med givent password
        seller.setSellerID(api.commitSeller(seller));

        //find gemt seller i DB
        Seller DBSeller=api.findSeller("hh");

        //tjek om den fundne seller er lig den gemte
        assertEquals(seller,DBSeller);

        // tjek om der er en mere i DB nu..
        int nytAntal=api.findAllSellers().size();
        assertEquals(antal+1,nytAntal);

        // tjek om password stemmer (der kan logges ind): #32
        assertTrue(Utils.checkPassword(password,seller.getSecret(),seller.getSalt()));



        //tjek om password kan ændres
        String newPassword="nytpassword";
        api.updateSellerPassword("hh",password,newPassword);
        seller=api.findSeller("hh");
        assertTrue(Utils.checkPassword(newPassword,seller.getSecret(),seller.getSalt()));

    }
    @Test
    public void userstory33() throws DBException, SQLException {
        //denne test dækker user stories:
        //#33 som sælger vil jeg gerne kunne ændre prisen på materialer

        //kan vi finde en liste over alle materialer både unit og volumen...
        List<UnitMaterial> unitMaterialList=api.findAllUnitMaterilas();
        assertEquals(13,unitMaterialList.size());
        assertTrue(unitMaterialList.contains(unitMaterial));
        List<VolumeMaterial> volumeMaterialList=api.findAllVolumeMaterials();
        assertEquals(103,volumeMaterialList.size());
        assertTrue(volumeMaterialList.contains(volumenMaterial));
        String name="vinkelbeslag";
        Material material= api.findUnitMaterial(name);
        int price=material.getprice();

        api.updateMaterialPrice(name,price+100);
        material= api.findUnitMaterial(name);
        //tjek om prisen er ændret til det ønskede
        assertEquals(price+100,material.getprice());

    }

    @Test
    public void userstory44() throws DBException, SQLException {
        //denne test dækker user stories:
        //#44 som kunde vil jeg gerne kunne se mine ordrer

        //kan vi finde en liste over alle ordrfer kun fra denne kunde.
        List<Order> orderList=api.findAllOrdersWithEmail("customer2@jorden.dk");
        for (Order o : orderList) {
            assertEquals("customer2@jorden.dk",o.getKundeEmail());
        }
        int antalMedEmail=orderList.size();

        //find alle ordrer.
        int  antalAlle=api.findAllOrders().size();

        //vi forventer at antal ,ed rette email er mindre end anatal af alle
        assertTrue(antalAlle>antalMedEmail);


    }




}