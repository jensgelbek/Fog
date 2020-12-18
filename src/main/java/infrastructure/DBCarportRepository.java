package infrastructure;

import domain.items.Carport;
import domain.items.CarportRepository;

import domain.items.DBException;
import domain.items.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCarportRepository implements CarportRepository {

    private final Database db;

    public DBCarportRepository(Database db) {
        this.db = db;
    }


    @Override
    public List<Carport> findAll() throws DBException {
        List<Carport> carports = new ArrayList<>();
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM carport";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int bredde = rs.getInt("bredde");
                int laengde = rs.getInt("længde");
                boolean rejsnin = rs.getBoolean("rejsning");
                String tag = rs.getString("tag");
                int skurbredde = rs.getInt("skurBredde");
                int skurlaengde = rs.getInt("skurlængde");
                Carport carport = new Carport(bredde, laengde, rejsnin, tag, skurbredde, skurlaengde);
                carport.setCarportID(id);
                carports.add(carport);
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return carports;
    }

    @Override
    public Carport find(int id) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM carport WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
                int bredde = rs.getInt("bredde");
                int laengde = rs.getInt("længde");
                boolean rejsnin = rs.getBoolean("rejsning");
                String tag = rs.getString("tag");
                int skurbredde = rs.getInt("skurBredde");
                int skurlaengde = rs.getInt("skurlængde");
                Carport carport = new Carport(bredde, laengde, rejsnin, tag, skurbredde, skurlaengde);
                carport.setCarportID(id);
                return carport;
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }

    @Override
    public int commit(Carport carport) {
        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO carport (bredde, længde, rejsning, tag, skurBredde, skurLængde) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, carport.getWidth());
            ps.setInt(2, carport.getLenght());
            ps.setBoolean(3, carport.getRejsning());
            ps.setString(4, carport.getTag());
            ps.setInt(5, carport.getShedWidth());
            ps.setInt(6, carport.getShedLength());
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
    public void update(Carport carport) {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE carport SET bredde=(?),længde=(?),rejsning=(?),tag=(?),skurBredde=(?),skurLængde=(?) WHERE id=(?);";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, carport.getWidth());
            ps.setInt(2, carport.getLenght());
            ps.setBoolean(3, carport.getRejsning());
            ps.setString(4, carport.getTag());
            ps.setInt(5, carport.getShedWidth());
            ps.setInt(6, carport.getShedLength());
            ps.setInt(7,carport.getCarportID());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}