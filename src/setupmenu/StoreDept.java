package setupmenu;

import database.MySQLConnect;
import java.sql.SQLException;
import java.sql.Statement;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class StoreDept {

    public boolean store(DeptButtonBean bean) {
        String sql = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            sql = "INSERT INTO menusetup (code_id,code_type,pcode,shortname,ppathname,pcolor)"
                    + " VALUES ('" + bean.getButtonName() + "','" + bean.getButtonType().toCharArray()[0] + "',"
                    + "'" + ThaiUtil.Unicode2ASCII(bean.getShortDesc()) + "','','','"+bean.getPcolor()+"')";
            Statement stmt = mysql.getConnection().createStatement();
            int i = stmt.executeUpdate(sql);
            stmt.close();
            return i > 0;
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage() + "\n" + sql);
            e.printStackTrace();
            return false;
        } finally{
            mysql.close();
        }
    }

    public boolean storeUpdate(DeptButtonBean bean) {
        String sql = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            sql = "UPDATE menusetup SET "
                    + "code_type = '" + bean.getButtonType().toCharArray()[0] + "',"
                    + "pcode = '" + bean.getGroupcode() + "',"
                    + "shortname = '" + ThaiUtil.Unicode2ASCII(bean.getShortDesc()) + "',"
                    + "ppathname = '',"
                    + "pcolor='"+bean.getPcolor()+"' "
                    + "WHERE code_id = '" + bean.getButtonName() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            int i = stmt.executeUpdate(sql);
            stmt.close();
            return i > 0;
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage() + "\n" + sql);
            e.printStackTrace();
            return false;
        }
    }
}
