package test;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TestCupnDetailuse {

    public String getSpecialCupon(String tableNo) {
        ArrayList<String[]> list = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String sql = "select sum(cuamt) amt,t_cupon.cuquan quan,"
                    + " t_cupon.cucode code,cupon.cuname name"
                    + "from t_cupon "
                    + "inner join cupon "
                    + "on t_cupon.cucode = cupon.cucode "
                    + "where t_cupon.cuquan<>'0' "
                    + "and t_cupon.refund<>'V' "
                    + "group by t_cupon.cucode";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String quan = rs.getString("cuquan");
                String amt = rs.getString("amt");
                String[] Cupon = new String[]{"", "", "", ""};

                Cupon[0] = code;
                Cupon[1] = name;
                Cupon[2] = quan;
                Cupon[3] = amt;

                list.add(Cupon);
            }
        } catch (Exception e) {
            mysql.close();
        }
        return ("");
    }

    public static void main(String[] args) {
        MySQLConnect c = new MySQLConnect();
    }
}
