package infrastructure;

import domain.items.Customer;
import domain.items.CustomerNotFound;
import domain.items.CustomerRepository;
import domain.items.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCustomerRepository implements CustomerRepository {
    private final Database db;

    public DBCustomerRepository(Database db) {
        this.db = db;
    }


    @Override
    public List<Customer> findAll() throws DBException {
        List<Customer> customerList = new ArrayList<>();
        try {

            Connection con = db.getConnection();
            String SQL = "SELECT * FROM kunde";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("name");
                String addresse = rs.getString("addresse");
                int tlfNummer = rs.getInt("tlfNummer");
                byte[] secret = rs.getBytes("secret");
                byte[] salt = rs.getBytes("salt");

                Customer customer = new Customer(name, addresse, email, tlfNummer, false, salt, secret);

                customerList.add(customer);

            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return customerList;
    }

    @Override
    public Customer findCustomer(String email) throws  DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM kunde WHERE email=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
                String name = rs.getString("name");
                String addresse = rs.getString("addresse");
                int tlfNummer = rs.getInt("tlfNummer");
                byte[] secret = rs.getBytes("secret");
                byte[] salt = rs.getBytes("salt");

                Customer customer = new Customer(name, addresse, email, tlfNummer, false, salt, secret);
                return customer;

            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }


    public Customer commitCustomer(Customer customer) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO kunde (email,name,addresse,tlfNummer,salt,secret) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setInt(4, customer.getPhoneNumber());
            ps.setBytes(5, customer.getSalt());
            ps.setBytes(6, customer.getSecret());
            ps.executeUpdate();
            return customer;

        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }


    }
}