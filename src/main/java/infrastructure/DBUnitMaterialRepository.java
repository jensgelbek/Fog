package infrastructure;

import domain.items.DBException;
import domain.materials.UnitMaterial;
import domain.materials.UnitMaterialRepository;
import domain.materials.VolumeMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUnitMaterialRepository implements UnitMaterialRepository {
    private final Database db;

    public DBUnitMaterialRepository(Database db) {
        this.db = db;
    }


    @Override
    public List<UnitMaterial> findAll() throws DBException {
        List<UnitMaterial> unitMaterials=new ArrayList<>();
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN unitmaterialer u ON m.id=u.unitMaterialeId INNER JOIN materialetype t on m.name=t.name;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String navn = rs.getString("name");
                int id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                String unitType=rs.getString("unitType");
                UnitMaterial unitMaterial=new UnitMaterial(id,navn,details,unitPrice,unitType);
                unitMaterials.add(unitMaterial);
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }

        return unitMaterials;
    }

    @Override
    public UnitMaterial find(int id) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN unitmaterialer u ON m.id=u.unitMaterialeId INNER JOIN materialetype t on m.name=t.name Where m.id=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String navn = rs.getString("name");
                id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                String unitType=rs.getString("unitType");
                UnitMaterial unitMaterial=new UnitMaterial(id,navn,details,unitPrice,unitType);
                return unitMaterial;
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }

    @Override
    public UnitMaterial findName(String name) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM materialer m INNER JOIN unitmaterialer u ON m.id=u.unitMaterialeId INNER JOIN materialetype t on m.name=t.name Where m.name=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String navn = rs.getString("name");
                int id = rs.getInt("id");
                String details=rs.getString("details");
                int unitPrice=rs.getInt("pris");
                String unitType=rs.getString("unitType");
                UnitMaterial unitMaterial=new UnitMaterial(id,navn,details,unitPrice,unitType);
                return unitMaterial;
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
    public void updatePrice(int id, int newPrice) throws DBException {
        try {
            Connection con = db.getConnection();
            UnitMaterial unitMaterial=find(id);
            String SQL = "UPDATE materialetype SET pris=(?) WHERE name=(?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,newPrice);
            ps.setString(2, unitMaterial.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }
}
