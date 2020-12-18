package infrastructure;

import domain.items.DBException;
import domain.materials.StykListeLinje;
import domain.materials.StyklisteLinjeRepository;
import domain.materials.UnitMaterial;
import domain.materials.VolumeMaterial;

import java.sql.*;

public class DBStyklisteLinjeRepository implements StyklisteLinjeRepository {
    public Database db;
    public DBStyklisteLinjeRepository(Database db) {
        this.db = db;
    }

    @Override
    public StykListeLinje find(int styklisteLinjeId) {
        DBVolumeMateialRepository dbVolumeMateialRepository=new DBVolumeMateialRepository(db);
        DBUnitMaterialRepository dbUnitMaterialRepository=new DBUnitMaterialRepository(db);
        StykListeLinje stykListeLinje=null;
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM styklistelinje Where id=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, styklisteLinjeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("id="+id);
                int orderId=rs.getInt("ordreid");
                int materialeId=rs.getInt("materialid");
                int antal=rs.getInt("antal");
                String description=rs.getString("description");
                VolumeMaterial volumeMaterial=dbVolumeMateialRepository.find(materialeId);
                if (volumeMaterial!=null) {
                    stykListeLinje = new StykListeLinje(volumeMaterial, antal, description, id);
                }
                UnitMaterial unitMaterial=dbUnitMaterialRepository.find(materialeId);
                if(unitMaterial!=null){
                    stykListeLinje=new StykListeLinje(unitMaterial,antal,description,id);
                }


            }
        } catch (SQLException | DBException ex) {
            System.out.println("ikke fundet");
        }
        return stykListeLinje;
    }



    @Override
    public int commit(StykListeLinje stykListeLinje, int ordreId) {

        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO styklistelinje (ordreid,materialid,antal,description) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordreId);
            ps.setInt(2, stykListeLinje.getMateriale().getId());
            ps.setInt(3, stykListeLinje.getQuantity());
            ps.setString(4,stykListeLinje.getDescription());
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

    @Override
    public void updateAntal(int id, int antal) {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE styklistelinje SET antal=(?) WHERE id=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, antal);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void updateMaterial(int id, int materialId) {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE styklistelinje SET materialid=(?) WHERE id=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, materialId);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
