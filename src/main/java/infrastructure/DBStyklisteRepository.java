package infrastructure;

import domain.items.DBException;
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
                VolumeMaterial volumeMaterial=dbVolumeMateialRepository.find(materialeId);
                if (volumeMaterial!=null){
                StykListeLinje stykListeLinje=new StykListeLinje(volumeMaterial,antal,"");
                stykliste.volumenListe.add(stykListeLinje);}
                UnitMaterial unitMaterial=dbUnitMaterialRepository.find(materialeId);
                if(unitMaterial!=null){
                    StykListeLinje stykListeLinje=new StykListeLinje(unitMaterial,antal,"");
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
        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO styklistelinje (ordreid,materialid,antal) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,orderId);
            ps.setInt(2,stykListeLinje.getMateriale().getId());
            ps.setInt(3,stykListeLinje.getQuantity());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
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
        return id;
    }
}