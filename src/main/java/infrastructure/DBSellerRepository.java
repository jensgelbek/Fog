package infrastructure;

import api.Utils;
import domain.items.DBException;
import domain.items.SellerRepository;
import domain.items.Seller;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBSellerRepository implements SellerRepository {
    private final Database db;

    public DBSellerRepository(Database db) {
        this.db = db;
        try {
            if(find("admin")==null){
                byte[]salt= Utils.generateSalt();
                byte[]secret=Utils.calculateSecret(salt,"1234");
                Seller seller=new Seller("admin","admin", salt,secret);
                commit(seller);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Seller> findAll() throws SQLException, DBException {
        List<Seller> sellers = new ArrayList<>();
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM sælger";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String navn = rs.getString("name");
                String userName = rs.getString("userName");
                byte[] secret = rs.getBytes("secret");
                byte[] salt = rs.getBytes("salt");
                int id = rs.getInt("id");

                Seller seller = new Seller("", navn, "".getBytes(StandardCharsets.UTF_8), "".getBytes(StandardCharsets.UTF_8));

                seller.setSellerID(id);
                sellers.add(seller);
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return sellers;
    }

    @Override
    public Seller find(String userName) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM sælger WHERE userName=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String navn = rs.getString("name");
                int id = rs.getInt("id");
                byte[] salt = rs.getBytes("salt");
                byte[] secret = rs.getBytes("secret");
                Seller seller = new Seller(userName, navn, salt, secret);

                seller.setSellerID(id);
                return seller;
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }

    @Override
    public int commit(Seller seller) {
        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO sælger (name,userName,salt,secret) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, seller.getName());
            ps.setString(2, seller.getUsername());
            ps.setBytes(3, seller.getSalt());
            ps.setBytes(4, seller.getSecret());
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
    public void updatePassword(String name,String oldPassword, String newPassword) {

        try {
            Connection con = db.getConnection();
            Seller seller=find(name);
            byte[]secret=Utils.calculateSecret(seller.getSalt(),newPassword);
            if(Utils.checkPassword(oldPassword,seller.getSecret(),seller.getSalt())) {
                String SQL = "UPDATE sælger  SET secret=(?)WHERE name=(?)";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setBytes(1, secret);
                ps.setString(2, name);
                ps.executeUpdate();
            }else{
                System.out.println("wrong password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
