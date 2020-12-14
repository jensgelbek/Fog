package infrastructure;

import domain.items.DBException;
import domain.materials.StykListeLinje;
import domain.materials.Stykliste;
import domain.materials.StyklisteRepository;
import domain.materials.VolumeMaterial;

import java.sql.*;

public class DBStyklisteRepository implements StyklisteRepository {
    private final Database db;
    public DBStyklisteRepository(Database db) {
        this.db = db;
    }


    @Override
    public Stykliste findStykliste(int orderId) throws DBException {
        Stykliste stykliste=new Stykliste();
        System.out.println("orderid="+orderId);

        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM styklistelinje Where id=(1);";
            PreparedStatement ps = con.prepareStatement(SQL);
           // ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                orderId=rs.getInt("ordreid");
                int materialeId=rs.getInt("materialid");
                int antal=rs.getInt("antal");
                VolumeMaterial volumeMaterial=new DBVolumeMateialRepository(db).find(materialeId);
                System.out.println("fsggdfg");
                StykListeLinje stykListeLinje=new StykListeLinje(volumeMaterial,antal,"");
                System.out.println(stykListeLinje);
                stykliste.volumenListe.add(stykListeLinje);


            }
        } catch (SQLException | DBException ex) {
            throw new DBException(ex.getMessage());
        }

        return stykliste;
    }

    @Override
    public void commitStykliste(Stykliste stykliste, int orderId) {

    }
}
