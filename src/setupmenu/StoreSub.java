package setupmenu;

import database.MySQLConnect;
import java.sql.Statement;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class StoreSub {

    public boolean store(SubButtonBean bean) {
        String sql = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            sql = "INSERT INTO menusetup (code_id,code_type,pcode,shortname,ppathname,pcolor)"
                + " VALUES ('"+bean.getButtonName()+"','"+bean.getButtonType().toCharArray()[0]+"',"
                    + "'"+ThaiUtil.Unicode2ASCII(bean.getShortDesc())+"','','','"+bean.getPcolor()+"')";
            Statement stmt = mysql.getConnection().createStatement();
            int i = stmt.executeUpdate(sql);
            stmt.close();
            return i > 0;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage()+"\n"+sql);
            return false;
        } finally {
            mysql.close();
        }
    }

    public boolean storeUpdate(SubButtonBean bean) {
        String sql = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            sql = "UPDATE menusetup SET "
                    + "code_type = '"+bean.getButtonType().toCharArray()[0]+"',"
                    + "pcode = '',"
                    + "shortname = '"+ThaiUtil.Unicode2ASCII(bean.getShortDesc())+"',"
                    + "ppathname = '"+bean.getPicture()+"',"
                    + "pcolor='"+bean.getPcolor()+"' "
                    + "WHERE code_id = '"+bean.getButtonName()+"'";
            Statement stmt = mysql.getConnection().createStatement();
            int i = stmt.executeUpdate(sql);
            stmt.close();
            return i > 0;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage()+"\n"+sql);
            return false;
        } finally {
            mysql.close();
        }
    }
}
