package Integration;

import api.Utils;
import api.Webapp;
import domain.items.Customer;
import domain.items.CustomerNotFound;
import domain.items.DBException;
import domain.items.Seller;
import infrastructure.*;
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

import static entries.Migrate.runMigrations;
import static org.junit.Assert.*;

public class UserStories {
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

    static void resetTestDatabase() {
        String URL = "jdbc:mysql://localhost:3306/?serverTimezone=CET";
        String USER = "fogtest";

        InputStream stream = UserStory1Test.class.getClassLoader().getResourceAsStream("resettest.sql");
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
    public void setup() {
        resetTestDatabase();

        String url = "jdbc:mysql://localhost:3306/fogtest?serverTimezone=CET";
        String user = "fogtest";

        Database db = new Database(url, user);
        try {
            System.out.println("runs migrations");
            runMigrations(db);
        } catch (IOException e) {
            System.out.println("iofejl");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("sqlfejl");
            throwables.printStackTrace();
        }


        api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteLinjeRepository(db),new DBStyklisteRepository(db),new DBMaterialRepository(db) );

    }
    @Test
    public void opretBruger() throws DBException {
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

        //tjek om den hentede er ens med den gemte
        assertEquals(customer,DBCustomer);

        //tjek om der en customer mere i DB
        int nytAntal=api.findAllCustomers().size();
        assertEquals(nytAntal,antal+1);


        //tjek om customer kan logge ind med password.
        assertTrue(Utils.checkPassword(password,DBCustomer.getSecret(),DBCustomer.getSalt()));

    }
}