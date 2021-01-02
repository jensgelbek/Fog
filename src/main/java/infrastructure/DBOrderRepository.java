package infrastructure;

import api.Utils;
import domain.items.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBOrderRepository implements OrderRepository {
    private final Database db;

    public DBOrderRepository(Database db) {
        this.db = db;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> orderlist = new ArrayList<>();
        Connection con = db.getConnection();
        String SQL = "SELECT * FROM ordre";
        PreparedStatement ps = con.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            LocalDate tilbudsDato = Utils.timestampToLocalDate(rs.getTimestamp("tilbudsDato"));
            LocalDate ordreDato = Utils.timestampToLocalDate(rs.getTimestamp("ordreDato"));
            LocalDate leveringsDato = Utils.timestampToLocalDate(rs.getTimestamp("leveringsDato"));
            String eamil = rs.getString("kundeEmail");
            int carportId = rs.getInt("carportid");
            int pris = rs.getInt("pris");
            String status = rs.getString("status");
            Order order = new Order(tilbudsDato, ordreDato, leveringsDato, eamil, carportId, pris, status);
            order.setOrderID(id);
            orderlist.add(order);
        }
        return orderlist;
    }

    @Override
    public List<Order> findAllWithEmail(String email) throws SQLException {
        List<Order> orderlist = new ArrayList<>();
        Connection con = db.getConnection();
        String SQL = "SELECT * FROM ordre WHERE kundeEmail=(?)";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            LocalDate tilbudsDato = Utils.timestampToLocalDate(rs.getTimestamp("tilbudsDato"));
            LocalDate ordreDato = Utils.timestampToLocalDate(rs.getTimestamp("ordreDato"));
            LocalDate leveringsDato = Utils.timestampToLocalDate(rs.getTimestamp("leveringsDato"));
            String eamil = rs.getString("kundeEmail");
            int carportId = rs.getInt("carportid");
            int pris = rs.getInt("pris");
            String status = rs.getString("status");
            Order order = new Order(tilbudsDato, ordreDato, leveringsDato, eamil, carportId, pris, status);
            order.setOrderID(id);
            orderlist.add(order);
        }
        return orderlist;
    }

    @Override
    public Order find(int id) throws DBException {

        try {
            Connection con = db.getConnection();
            String SQL = "SELECT * FROM ordre WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate tilbudsDato = Utils.timestampToLocalDate(rs.getTimestamp("tilbudsDato"));
                LocalDate ordreDato = Utils.timestampToLocalDate(rs.getTimestamp("ordreDato"));
                LocalDate leveringsDato = Utils.timestampToLocalDate(rs.getTimestamp("leveringsDato"));
                String eamil = rs.getString("kundeEmail");
                int carportId = rs.getInt("carportid");
                int pris = rs.getInt("pris");
                String status = rs.getString("status");
                Order order = new Order(tilbudsDato, ordreDato, leveringsDato, eamil, carportId, pris, status);
                order.setOrderID(id);
                return order;
            }
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return null;
    }

    @Override
    public void updateStatus(int id, String status) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE ordre  SET status=(?)WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DBException("order do not exist");
        }

    }

    @Override
    public void updatePrice(int id, int newPrice) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE ordre  SET pris=(?)WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DBException("order do not exist");
        }
    }


    @Override
    public int commit(Order order) {
        int id = 0;
        try {
            Connection con = db.getConnection();
            String SQL = "INSERT INTO ordre (tilbudsDato,ordreDato,leveringsDato,kundeEmail,carportId,pris,status) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(order.getTilbudsdato().atStartOfDay()));
            if (order.getOrdredato() != null) {
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(order.getOrdredato().atStartOfDay()));
            } else {
                ps.setTimestamp(2, null);
            }

            if (order.getLeveringsDato() != null) {
                ps.setTimestamp(3, java.sql.Timestamp.valueOf(order.getLeveringsDato().atStartOfDay()));
            } else {
                ps.setTimestamp(3, null);
            }
            ps.setString(4, order.getKundeEmail());
            ps.setInt(5, order.getCarportId());
            ps.setInt(6, order.getPrice());
            ps.setString(7, order.getStatus());

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
    public void setOrdreDato(int id, LocalDate ordreDato) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE ordre SET ordreDato=(?) WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            System.out.println(ordreDato+" dato fra DB + orderid:"+ id);
            if (ordreDato != null) {
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(ordreDato.atStartOfDay()));
            } else {
                throw new DBException("no date");
            }
            ps.setInt(2, id);
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DBException("order do not exist");
        }

    }

    @Override
    public void setLeveringsDato(int id, LocalDate leveringsDato) throws DBException {
        try {
            Connection con = db.getConnection();
            String SQL = "UPDATE ordre  SET leveringsDato=(?)WHERE id=(?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            if (leveringsDato != null) {
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(leveringsDato.atStartOfDay()));
            } else {
                throw new DBException("no date");
            }
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DBException("order do not exist");
        }

    }
}
