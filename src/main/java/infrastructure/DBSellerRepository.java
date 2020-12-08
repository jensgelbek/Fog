package infrastructure;

import api.Utils;
import domain.items.DBException;
import domain.items.Order;
import domain.items.SellerRepository;
import domain.items.Seller;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DBSellerRepository implements SellerRepository{
    private final Database db;
    public DBSellerRepository(Database db) {
        this.db = db;
    }
    @Override
    public List<Seller> findAll() throws SQLException, DBException {
        List<Seller> sellers=new ArrayList<>();
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM sælger";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String navn=rs.getString("name");
                String userName=rs.getString("userName");
                byte[] secret = rs.getBytes("secret");
                byte[] salt = rs.getBytes("salt");
                int id=rs.getInt("id");

                Seller seller=new Seller("",navn,"".getBytes(StandardCharsets.UTF_8),"".getBytes(StandardCharsets.UTF_8));

                seller.setSellerID(id);
                sellers.add(seller);
            }
        } catch ( SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return sellers;
    }

    @Override
    public Seller find(int id) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM sælger WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String navn=rs.getString("name");

                Seller seller=new Seller("",navn,"".getBytes(StandardCharsets.UTF_8),"".getBytes(StandardCharsets.UTF_8));

                seller.setSellerID(id);
                return seller;
            }
        } catch ( SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }

    @Override
    public int commit(Seller seller) {
        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO sælger (name) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, seller.getName());
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
