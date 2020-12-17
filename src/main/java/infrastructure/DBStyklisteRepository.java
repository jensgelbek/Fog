package infrastructure;

import api.Utils;
import domain.items.DBException;
import domain.items.Seller;
import domain.materials.*;

import java.sql.*;

public class DBStyklisteRepository implements StyklisteRepository,StyklisteLinjeRepository {
    private final Database db;
    public DBStyklisteRepository(Database db) {
        this.db = db;
    }


    @Override
    public Stykliste findStykliste(int orderId) throws DBException {
        Stykliste stykliste=new Stykliste();
        DBVolumeMateialRepository dbVolumeMateialRepository=new DBVolumeMateialRepository(db);
        DBUnitMaterialRepository dbUnitMaterialRepository=new DBUnitMaterialRepository(db);
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM styklistelinje Where ordreid=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                orderId=rs.getInt("ordreid");
                int materialeId=rs.getInt("materialid");
                int antal=rs.getInt("antal");
                String description=rs.getString("description");
                VolumeMaterial volumeMaterial=dbVolumeMateialRepository.find(materialeId);
                if (volumeMaterial!=null){
                StykListeLinje stykListeLinje=new StykListeLinje(volumeMaterial,antal,description,id);
                stykliste.volumenListe.add(stykListeLinje);}
                UnitMaterial unitMaterial=dbUnitMaterialRepository.find(materialeId);
                if(unitMaterial!=null){
                    StykListeLinje stykListeLinje=new StykListeLinje(unitMaterial,antal,description,id);
                    stykliste.unitListe.add(stykListeLinje);}


            }
        } catch (SQLException | DBException ex) {
            System.out.println("ikke fundet");
            throw new DBException(ex.getMessage());
        }

        return stykliste;
    }

    @Override
    public void commitStykliste(Stykliste stykliste, int orderId) {
        for (StykListeLinje styklistelinje:stykliste.volumenListe) {
            System.out.println("commitStykliste");
            commit(styklistelinje,orderId);
        }
        for (StykListeLinje styklistelinje:stykliste.unitListe) {
            commit(styklistelinje,orderId);
        }

    }

    @Override
    public StykListeLinje find(int styklisteLinjeId) {
        return null;
    }

    @Override
    public int commit(StykListeLinje stykListeLinje, int orderId) {
        System.out.println("Så er vi her");
        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO styklistelinje (ordreid,materialid,antal,description) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,orderId);
            System.out.println("commitStykListe fra DB: 1");
            System.out.println(stykListeLinje.getMateriale());
            ps.setInt(2, stykListeLinje.getMateriale().getId());
            System.out.println("commitStykListe fra DB: 2");
            ps.setInt(3,stykListeLinje.getQuantity());

            ps.setString(4,stykListeLinje.getDescription());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("commitStykListe fra DB: 4");
                id = rs.getInt(1);
            } else {
                System.out.println("else");
                throw new RuntimeException("Unexpected error");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("StyklisteID fra DB: " + id);
        return id;
    }

    @Override
    public void updateAntal(int id, int antal) {
        try {
            Connection con = db.getConnection();

                String SQL = "UPDATE styklistelinje antal=(?) WHERE id=(?);";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setInt(1, antal);
                ps.setInt(2, id);
                ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
