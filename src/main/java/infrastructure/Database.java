package infrastructure;

import java.sql.*;
import java.util.TimeZone;

public class Database {

    // Database configuration
    private final String DB_SERVER = "localhost";
    private final String DB_USER;
    private final String DB_PASS = "fog";
    private final String SCHEMA_NAME = "fog";


    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL;
    // Database version

    private static final int version = 12;

    public Database(String DB_URL, String DB_USER) {
        this.DB_URL = DB_URL == null ? "jdbc:mysql://" + DB_SERVER + "/" + SCHEMA_NAME + "?serverTimezone=" + TimeZone.getDefault().getID():DB_URL;
        this.DB_USER = DB_USER == null ? "fog" : DB_USER;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public Database() {
        this(null, null);
    }

    public  int getCurrentVersion() {
        try (Connection conn = getConnection()) {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'version';");
            if (rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            } else {
                System.err.println("No version in properties.");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    // Connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static int getVersion() {
        return version;
    }
}
