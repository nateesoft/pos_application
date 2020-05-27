package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtility {

    protected Connection connect;
    private boolean enable;
    private String mainSQL;
    private PreparedStatement mainPstmt;

    public DatabaseUtility(String driver, String url, String user, String password) {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            MSG.ERR(null, e.getMessage());
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public DatabaseUtility(Connection connect) {
        this.connect = connect;
    }

    public DatabaseUtility() {
    }

    public void close() {
        try {
            if (getConnect() != null) {
                getConnect().close();
            }
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    /**
     * เพิ่มข้อมูลลงตารางทีละ 1 แถว (จำกัดที่ Pk ต้องเป็น Pk จริงๆ
     * ถูกสร้างกำหนดลงในฐานข้อมูล)
     *
     * @param sql - sql command ที่จะใช้ อยู่ในรูปแบบของ PreparedStatement
     * @param args - กลุ่มของข้อมูลที่จะเพิ่ม สัมพันธ์กับ sql command รูปแบบเช่น
     * (sql,"id01","name")
     * @return - คืนค่าเป็น index ของบรรทัดที่เพิ่มเข้าไปในตาราง
     */
    public int insert(String sql, Object... args) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {

            pstmt = getConnect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }

            pstmt.executeUpdate();

            //Find Auto Increment Id
            rs = pstmt.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);

        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
            return -1;
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
            }
        }
    }

    /**
     * ประมวลผลคำสั่ง sql command เช่น คำสั่ง insert, update, delete, alter,
     * create table etc.
     *
     * @param sql - sql command ที่จะใช้ อยู่ในรูปแบบของ PreparedStatement
     * @param args - กลุ่มของข้อมูลที่จะเพิ่ม สัมพันธ์กับ sql command รูปแบบเช่น
     * (sql,"id01","name")
     * @return - คืนค่าเป็นจำนวนแถวที่มีการเปลี่ยนแปลง
     */
    public int executeUpdate(String sql, Object... args) {
        PreparedStatement pstmt = null;
        try {

            if (enable) {
                pstmt = this.mainPstmt;
            } else {
                pstmt = getConnect().prepareStatement(sql);
            }

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            return -1;
        } finally {
            try {
                if (!enable) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (SQLException ex) {
            }
        }
    }

    public Map<String, Object> querySingle(String sql, Object... args) {
        Map<String, Object> map = new HashMap<String, Object>();

        ResultSet rs = null;
        PreparedStatement pstmt;
        try {
            pstmt = getConnect().prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();

            if (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    Object obj = null;
                    try {
                        obj = rs.getObject(i);

                    } catch (SQLException ex) {
                        if (ex.getMessage().indexOf("'0000-00-00'") != -1) {
                            obj = null;
                        } else {
                            throw new RuntimeException(ex);
                        }
                    }
                    map.put(md.getColumnLabel(i).toLowerCase(), obj);
                }
            }
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } catch (RuntimeException e) {
            MSG.ERR(null, e.getMessage());
        }

        return map;
    }

    public List<Map<String, Object>> queryList(String sql, Object... args) {
        List<Map<String, Object>> list;
        list = new ArrayList<Map<String, Object>>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {

            pstmt = getConnect().prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }

            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();

            while (rs.next()) {
                Map<String, Object> map;
                map = new HashMap<String, Object>();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    Object obj = null;
                    try {
                        obj = rs.getObject(i);
                    } catch (SQLException ex) {
                        if (ex.getMessage().indexOf("'0000-00-00'") != -1) {
                            obj = null;
                        } else {
                            throw new RuntimeException(ex);
                        }
                    }
                    map.put(md.getColumnLabel(i).toLowerCase(), obj);
                }

                list.add(map);
            }
            
            rs.close();

        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } catch (RuntimeException e) {
            MSG.ERR(null, e.getMessage());
        }

        return list;
    }

    /**
     * เปิดการใช้ Transaction mode
     */
    public void enableTransactionMode() {
        try {
            getConnect().setAutoCommit(false);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    /**
     * ปิดการใช้ Transaction mode
     */
    public void disableTransactionMode() {
        try {
            getConnect().setAutoCommit(true);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    /**
     * ยืนยันการทำรายการ commit
     */
    public void commit() {
        try {
            getConnect().commit();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    /**
     * ย้อนกลับไปที่จุด save point
     */
    public void rollback() {
        try {
            getConnect().rollback();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    /**
     * get java Connection
     *
     * @return - Connection
     */
    public Connection getConnect() {
        return connect;
    }

    /**
     * set java Connection
     */
    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    /**
     * เปิดการใช้งานคำสั่ง executeUpdate(...) แบบ Multi command
     * ซึ่งใช้คู่กับคำสั่ง executeUpdate(...) โดยจะทำให้คำสั่งนี้ไม่อ่าน sql
     * ที่เพิ่มเข้าไป แต่จะไปใช้คำสั่ง sql ที่ใส่เข้าไปใน
     * enableExceuteSQLSuiteMode(...) แทน
     *
     * @param sql - sql command ที่จะใช้ อยู่ในรูปแบบของ PreparedStatement
     */
    public void enableExceuteSQLSuiteMode(String mainSQL) {
        try {
            this.enable = true;
            this.mainSQL = mainSQL;
            this.mainPstmt = getConnect().prepareStatement(this.mainSQL);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    /**
     * ปิดการใช้งานคำสั่ง executeUpdate(...) แบบ Multi command
     */
    public void disableExceuteSQLSuiteMode() {
        try {
            this.enable = false;
            this.mainSQL = null;
            mainPstmt.close();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }
}
