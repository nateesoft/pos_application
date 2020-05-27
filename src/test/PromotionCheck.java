/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import util.DateConvert;

/**
 *
 * @author Dell-Softpos
 */
public class PromotionCheck {

    public String promotionCheck(String pcode) {
        String procode = "";
        String prodesc = "";
        try {
            MySQLConnect c = new MySQLConnect();
            String sql = "select "
                    + "p.pcode, "
                    + "p.pdesc, "
                    + "p.pprice11, "
                    + "p.pprice12, "
                    + "p.pprice13, "
                    + "p.pprice14, "
                    + "p.pprice15, "
                    + "p.ppromotion1, "
                    + "pt.* "
                    + "from product p "
                    + "left join protab pt "
                    + "on p.ppromotion1 = pt.procode "
                    + "where pcode='" + pcode + "'";
            c.open();
            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                DateConvert dc = new DateConvert();
                String startDate = (rs.getString("pdate1")).replace("-", "");
                String expireDate = (rs.getString("pdate2")).replace("-", "");
                String date = (dc.GetCurrentDate()).replace("-", "");
                int ptime1s = Integer.parseInt(rs.getString("ptime1s").replace(":", ""));
                int ptime1e = Integer.parseInt(rs.getString("ptime1e").replace(":", ""));
                int expDate = Integer.parseInt(expireDate);
                int curDate = Integer.parseInt(date);
                procode = rs.getString("procode");
                prodesc = rs.getString("prodesc");
                if (expDate < curDate) {
                    System.out.println("Promotion Expire!");
                    System.out.println("start " + startDate);
                    System.out.println("expire " + expireDate);
                    System.out.println("current " + date);
                } else {
                    System.out.println("Excute Promotion!");
                    System.out.println("start " + startDate);
                    System.out.println("expire " + expireDate);
                    System.out.println("current " + date);
                    int time = Integer.parseInt(dc.GetCurrentDateFM("HH:mm").replace(":", ""));
                    System.out.println("TIME " + time);
                    if (time > ptime1s && time < ptime1e) {
                        System.out.println("Promotion is in Active time" + ptime1s + " and " + ptime1e + " and " + time);
                    }
                }
            }
        } catch (Exception e) {
        }
        return procode + ":" + prodesc;
    }

    public static void main(String[] args) {
        MySQLConnect c = new MySQLConnect();
        c.open();
        PromotionCheck pc = new PromotionCheck();
//        pc.promotionCheck("CF001");
        String promotion = pc.promotionCheck("CF001");
        System.out.println(promotion);
        String code = promotion.substring(0,3);
        String name = promotion.substring(4);
        System.out.println(code);
        System.out.println(name);
    }
}
