package com.softpos.main.program;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.MSG;

public class DiscountControl {

    public static double getDouble(double db) {
        if (POSConfigSetup.Bean().getP_DiscRound().equals("F")) {
            return NumberControl.UP_DOWN_25(db);
        } else {
            return db;
        }
    }

    public void updateDiscount(String tableNo) {
        //หามูลค่าส่วนลดรายการ
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select sum(R_PrAmt) SUM_R_PrAmt "
                    + "from balance "
                    + "where R_table = '" + tableNo + "' "
                    + "and R_Void<>'V' "
                    + "and R_Discount='Y' "
                    + "and R_PrType = '-I'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next() && !rs.wasNull()) {
                String sqlUpd = "update tablefile set "
                        + "ItemDiscAmt='" + rs.getDouble("SUM_R_PrAmt") + "' "
                        + "where Tcode = '" + tableNo + "'";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sqlUpd);
                stmt1.close();
            }

            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        mysql.open();
        //หามูลค่าบัตรลดคูปอง
        try {
            String sql = "select sum(R_PrCuAmt) SUM_R_PrCuAmt "
                    + "from balance "
                    + "where R_table = '" + tableNo + "' "
                    + "and R_Void<>'V' "
                    + "and R_Discount='Y' "
                    + "and R_PrCuType = '-C'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() && !rs.wasNull()) {
                String sqlUpd = "";
                if (!POSConfigSetup.Bean().getP_DiscRound().equals("F")) {
                    sqlUpd = "update tablefile set "
                            + "CuponDiscAmt='" + NumberControl.UP_DOWN_NATURAL_BAHT(rs.getDouble("SUM_R_PrCuAmt")) + "' "
                            + "where Tcode = '" + tableNo + "'";
                } else {
                    sqlUpd = "update tablefile set "
                            + "CuponDiscAmt='" + (rs.getDouble("SUM_R_PrCuAmt")) + "' "
                            + "where Tcode = '" + tableNo + "'";
                }

//                Statement stmt1 = mysql.getConnection().createStatement();
                mysql.getConnection().createStatement().executeUpdate(sqlUpd);
//                stmt1.close();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }
}
