package database;

import prefs.Prefs;

import java.sql.*;


public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;
    private PreparedStatement createSt;
    private Database() {

        try {

            connection = DriverManager.getConnection(new Prefs().getString(Prefs.DB_URL));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
