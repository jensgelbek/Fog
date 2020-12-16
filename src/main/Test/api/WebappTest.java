package api;

import domain.items.DBException;
import domain.materials.Material;
import domain.materials.StykListeLinje;
import domain.materials.Stykliste;
import infrastructure.*;
import junit.framework.TestCase;

import java.sql.SQLException;

public class WebappTest extends TestCase {

    public void testCommitOrder() throws DBException, SQLException {
        Database db = new Database();
        Webapp api = new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteLinjeRepository(db), new DBStyklisteRepository(db),new DBMaterialRepository(db));
        //  int id=api.commitOrder(order);
        //order=api.findOrder(id);
        //api.findAllOrders();
        //Seller seller=new Seller("morten");
        // api.commitSeller(seller);
        //  assertEquals("morten",api.findSeller(2).getName());
        // System.out.println(api.findSeller(1).getName());
        //List<Seller> sellers = api.findAllSellers();
        //for (Seller sellr : sellers) {
        //    System.out.println(sellr.getName());
        //}
        /*List<Carport> carports = api.findAlleCarports();
        for (Carport carport : carports) {
            System.out.println(carport);*/
      Stykliste stykliste=new Stykliste();
        StykListeLinje stykListeLinje1=new StykListeLinje(api.findVolumeMaterialNameLenght("stolpe",3000),6,"stolper");
        StykListeLinje stykListeLinje2=new StykListeLinje(api.findVolumeMaterialNameLenght("spær/rem",3000),2,"remme");
        StykListeLinje stykListeLinje3=new StykListeLinje(api.findVolumeMaterialNameLenght("understern",2400),4,"understern i enderne");
        stykliste.volumenListe.add(stykListeLinje1);
        stykliste.volumenListe.add(stykListeLinje2);
        stykliste.volumenListe.add(stykListeLinje3);
       api.commitStykliste(stykliste,1);
        System.out.println(api.findStykliste(1));

    }
}
