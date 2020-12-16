package infrastructure;

import domain.materials.StykListeLinje;
import domain.materials.StyklisteLinjeRepository;

import java.sql.*;

public class DBStyklisteLinjeRepository implements StyklisteLinjeRepository {
    public Database db;
    public DBStyklisteLinjeRepository(Database db) {
        this.db = db;
    }

    @Override
    public StykListeLinje find(int styklisteLinjeId) {
        return null;
    }



    @Override
    public int commit(StykListeLinje stykListeLinje, int ordreId) {

        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO styklistelinje (ordreid,materialid,antal) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordreId);
            ps.setInt(2, stykListeLinje.getMateriale().getId());
            ps.setInt(3, stykListeLinje.getQuantity());
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
