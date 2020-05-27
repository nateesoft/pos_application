package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.ConfigFile;

public class SQLServerConnect {

    private final String CLASS_NAME;
    public static String SERVER;
    private final String USER;
    private final String PASS;
    public static String DATABASE;
    public static Connection conn;
    private String PORT = "3307";
    private final String CHARSET="tis-620";

    public SQLServerConnect() throws ClassNotFoundException, SQLException {
        //CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        CLASS_NAME = "com.mysql.jdbc.Driver";
        SERVER = ConfigFile.getProperties("sql_server_host");
        USER = ConfigFile.getProperties("sql_server_user");
        PASS = ConfigFile.getProperties("sql_server_pass");
        DATABASE = ConfigFile.getProperties("sql_server_database");
        connect();
    }

    public SQLServerConnect(String CLASS_NAME, String SERVER, String USER, String PASS, String DATABASE) throws ClassNotFoundException, SQLException {
        this.CLASS_NAME = CLASS_NAME;
        SQLServerConnect.SERVER = SERVER;
        this.USER = USER;
        this.PASS = PASS;
        SQLServerConnect.DATABASE = DATABASE;

        connect();
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(CLASS_NAME);
        System.out.println("Driver Loaded.");

        //String jdbcUrl = "jdbc:sqlserver://" + SERVER + ";database=" + DATABASE + ";user=" + USER + ";password=" + PASS;
        String jdbcUrl = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE + "?charset=" + CHARSET;
        conn = DriverManager.getConnection(jdbcUrl,USER,PASS);
        System.out.println("Connected.");

        return conn;
    }

    public ResultSet getResultSet(String sql) throws Exception {
        if (conn == null) {
            System.out.println("Not connect database !");
            return null;
        }
        ResultSet rs = null;

        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        return rs;
    }

    public int getUpdate(String sql) throws SQLException {
        if (conn == null) {
            System.out.println("Not connect database !");
            return -1;
        }
        int iUpdate = -1;
        Statement stmt = conn.createStatement();
        iUpdate = stmt.executeUpdate(sql);
        stmt.close();

        return iUpdate;
    }

    public void close() throws Exception {

        if (conn != null) {
            conn.close();
            System.out.println("Connection Closed.");
        }

    }
}
