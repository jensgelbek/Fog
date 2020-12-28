package Integration;

import api.Webapp;
import domain.items.DBException;
import domain.items.Seller;
import infrastructure.*;
import junit.framework.TestCase;
import org.apache.ibatis.jdbc.ScriptRunner;
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
import static org.junit.Assert.assertEquals;

public class UserStory1Test{

    Webapp api;
    /**
     * Before you run this script create a user 'fogtest' and grant access to the database:
     *
     * <pre>
     * DROP USER IF EXISTS fogtest@localhost;
     * CREATE USER fogtest@localhost;
     * GRANT ALL PRIVILEGES ON fogtest.* TO fogtest@localhost;
     * </pre>
     */

    static void resetTestDatabase() {
        String URL = "jdbc:mysql://localhost:3306/?serverTimezone=CET";
        String USER = "fogtest";

        InputStream stream = UserStory1Test.class.getClassLoader().getResourceAsStream("reset.sql");
        if (stream == null) throw new RuntimeException("reset.sql");
        try (Connection conn = DriverManager.getConnection(URL, USER, null)) {
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

    @BeforeEach
    void setupAPI() {
        System.out.println("beforeeach");
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
    public void testFindAllOrders() throws DBException {
        Seller admin=api.findSeller("admin");
        assertEquals("Admin",admin.getUsername());
    }
}