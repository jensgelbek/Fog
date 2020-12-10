package infrastructure;

import domain.items.DBException;
import domain.items.Seller;
import domain.materials.UnitMaterial;
import domain.materials.VolumeMaterial;
import domain.materials.VolumeMaterialRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBVolumeMateialRepository implements VolumeMaterialRepository {
    private final Database db;

    public DBVolumeMateialRepository(Database db) {
        this.db = db;
    }

    @Override
    public List<VolumeMaterial> findAll() throws DBException {
        List<VolumeMaterial> volumeMaterialList=new ArrayList<>();
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN volumematerialer v ON m.id=v.volumeMaterialeId;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String navn = rs.getString("name");
                int id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                int length=rs.getInt("length");
                VolumeMaterial volumeMaterial=new VolumeMaterial(id,navn,details,unitPrice,length);
                volumeMaterialList.add(volumeMaterial);
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return volumeMaterialList;

    }

    @Override
    public List<VolumeMaterial> findAllName(String name) throws DBException {
        List<VolumeMaterial> volumeMaterialList=new ArrayList<>();
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN volumematerialer v ON m.id=v.volumeMaterialeId WHERE m.name=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String navn = rs.getString("name");
                int id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                int length=rs.getInt("length");
                VolumeMaterial volumeMaterial=new VolumeMaterial(id,navn,details,unitPrice,length);
                volumeMaterialList.add(volumeMaterial);
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return volumeMaterialList;

    }

    @Override
    public VolumeMaterial find(int id) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN volumematerialer v ON m.id=v.volumeMaterialeId Where m.id=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String navn = rs.getString("name");
                id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                int length=rs.getInt("length");
                VolumeMaterial volumeMaterial=new VolumeMaterial(id,navn,details,unitPrice,length);
                return volumeMaterial;
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;

    }

    @Override
    public VolumeMaterial find(String name, int lenght) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN volumematerialer v ON m.id=v.volumeMaterialeId Where m.name=(?) AND v.length=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setInt(2,lenght);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String navn = rs.getString("name");
                int id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                int length=rs.getInt("length");
                VolumeMaterial volumeMaterial=new VolumeMaterial(id,navn,details,unitPrice,length);
                return volumeMaterial;
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }

    @Override
    public int commit(UnitMaterial unitMaterial) {
        return 0;
    }

    @Override
    public void updateNameWithPrice(String name, int newPrice) {

    }
}
