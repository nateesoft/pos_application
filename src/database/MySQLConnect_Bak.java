package database;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.softpos.main.program.Value;
import javax.swing.JOptionPane;
import util.MSG;

public class MySQLConnect_Bak {

    public static Connection con;
    public static String HostName;
    public static String DbName;
    public static String UserName;
    public static String Password;
    public static String PortNumber;
    public static String Char_Set;
    public static String msgError = "พบการเชื่อมต่อมีปัญหา ไม่สามารถดำเนินการต่อได้\n"
            + "ท่านต้องการปิดโปรแกรมอัตโนมัติหรือไม่ ?";

    public static void close() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database Closed.");
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnect_Bak.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void showError(Exception e) {
        String strErr = e.getMessage();
        if (strErr.indexOf("No operations") != -1||
                strErr.indexOf("Communications link failure")!=-1) {
            int confirm = JOptionPane.showConfirmDialog(null, msgError);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public MySQLConnect_Bak() {
        dbconnect();
    }

    private void dbconnect() {
        try {
            getDbVar();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + HostName + ":" + PortNumber + "/" + DbName + "?characterEncoding=utf-8", UserName, Password);
            System.out.println("Database Connected.");
        } catch (ClassNotFoundException e) {
            MSG.ERR("Database Connection Error !!!\n" + e.getMessage());
        } catch (SQLException e) {
            MSG.ERR("Database Connection Error !!!\n" + e.getMessage());
        }
    }

    public void getDbVar() {
        try {
            FileInputStream fs = new FileInputStream(Value.FILE_CONFIG);
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));
            String tmp;
            while ((tmp = br.readLine()) != null) {
                String[] data = tmp.split(",", tmp.length());
                if (data[0].equalsIgnoreCase("server")) {
                    HostName = data[1];
                } else if (data[0].equalsIgnoreCase("database")) {
                    DbName = data[1];
                    Value.DATABASE = data[1];
                } else if (data[0].equalsIgnoreCase("user")) {
                    UserName = data[1];
                } else if (data[0].equalsIgnoreCase("pass")) {
                    Password = data[1];
                } else if (data[0].equalsIgnoreCase("port")) {
                    PortNumber = data[1];
                } else if (data[0].equalsIgnoreCase("charset")) {
                    Char_Set = data[1];
                } else if (data[0].equalsIgnoreCase("macno")) {
                    Value.MACNO = data[1];
                } else if (data[0].equalsIgnoreCase("language")) {
                    Value.LANG = data[1];
                } else if (data[0].equalsIgnoreCase("db_member")) {
                    Value.db_member = data[1];
                } else if (data[0].equalsIgnoreCase("useprint")) {
                    try {
                        Value.useprint = Boolean.parseBoolean(data[1]);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        Value.useprint = false;
                    }
                } else if (data[0].equalsIgnoreCase("printkic")) {
                    try {
                        Value.printkic = Boolean.parseBoolean(data[1]);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        Value.printkic = false;
                    }
                } else if (data[0].equalsIgnoreCase("autoqty")) {
                    try {
                        Value.autoqty = Boolean.parseBoolean(data[1]);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        Value.autoqty = false;
                    }
                } else if (data[0].equalsIgnoreCase("printdriver")) {
                    try {
                        Value.printdriver = Boolean.parseBoolean(data[1]);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        Value.printdriver = false;
                    }
                } else if (data[0].equalsIgnoreCase("printerName")) {
                    Value.printerDriverName = data[1];
                }
            }
            br.close();
            ds.close();
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
            MSG.ERR(e.getMessage());
        }
    }

    public static ResultSet getResultSet(String sql) throws Exception {
        if (con == null) {
            return null;
        } else {
            try {
                return getStatement().executeQuery(sql);
            } catch (Exception e) {
                e.printStackTrace();
                showError(e);
                return null;
            }
        }
    }

    public static int exeUpdate(String sql) throws Exception {
        if (con == null) {
            return -1;
        } else {
            try {
                return con.createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                showError(e);
                return -1;
            }
        }
    }

    public static Statement getStatement() throws Exception {
        if (con == null) {
            return null;
        } else {
            try {
                return con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
                showError(e);
                return null;
            }

        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws Exception {
        if (con == null) {
            return null;
        } else {
            try {
                return con.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                showError(e);
                return null;
            }

        }
    }

}
