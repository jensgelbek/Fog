package api;

import domain.items.Carport;
import domain.items.DBException;
import domain.items.Order;
import domain.items.Seller;
import infrastructure.*;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WebappTest extends TestCase {

    public void testCommitOrder() throws DBException, SQLException {
        Order order=new Order(LocalDate.now(),null,null,"jens@gamil.com",1,1,100000,"bestilling");
        Database db=new Database();
        Webapp api=new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db),new DBSellerRepository(db));
      //  int id=api.commitOrder(order);
        //order=api.findOrder(id);
        api.findAllOrders();
        //Seller seller=new Seller("morten");
       // api.commitSeller(seller);
      //  assertEquals("morten",api.findSeller(2).getName());
       // System.out.println(api.findSeller(1).getName());
        List<Seller> sellers=api.findAllSellers();
        for (Seller sellr:sellers ) {
            System.out.println(sellr.getName());
        }
        List<Carport> carports=api.findAlleCarports();
        for (Carport carport:carports){
            System.out.println(carport);
        }
    }
}