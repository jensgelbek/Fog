package infrastructure;

import domain.items.DBException;
import domain.materials.Material;
import domain.materials.MaterialRepository;
import domain.materials.UnitMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBMaterialRepository implements MaterialRepository {
    private final Database db;

    public DBMaterialRepository(Database db) {
        this.db = db;
    }
    @Override
    public void updatePrice(String name, int newPrice) throws DBException {
            try {
                Connection con = db.getConnection();
                String SQL = "UPDATE materialetype SET pris=(?) WHERE name=(?);";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setInt(1,newPrice);
                ps.setString(2, name);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DBException(ex.getMessage());
            }
        }

    @Override
    public List<Material> getAllTypes() throws DBException {
        List<Material> materials=new ArrayList<>();

            try {
                Connection con = db.getConnection();
                String SQL = "SELECT * FROM materialetype;";
                PreparedStatement ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String navn = rs.getString("name");
                    String details=rs.getString("details");
                    int unitPrice=rs.getInt("pris");
                    Material material=new Material(0,navn,details,unitPrice);
                    materials.add(material);
                }
            } catch (SQLException ex) {
                throw new DBException(ex.getMessage());
            }

            return materials;
        }



    }


