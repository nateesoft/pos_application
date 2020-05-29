package com.softpos.main.controller;

import database.MySQLConnect;
import java.sql.ResultSet;
import com.softpos.main.model.BalanceBean;
import com.softpos.main.model.POSConfigSetup;
import java.sql.SQLException;
import java.sql.Statement;
import util.DateConvert;
import util.MSG;
import com.softpos.main.model.MemberBean;

public class MemberControl {

    MemberBean MemberBean = new MemberBean();
    
    public MemberControl() {

    }

    public void updateMemberDiscount(String table, MemberBean memberBean) {
        String strDisc = "";
        if (memberBean != null) {
            if (!memberBean.getMember_DiscountRate().equals("")) {
                strDisc = memberBean.getMember_DiscountRate();
            }
        }
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select sum(R_PrSubAmt) as MemDiscount "
                    + "from balance where r_table='" + table + "' "
                    + "order by R_Index;";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String upd = "update tablefile set "
                        + "MemDiscAmt='" + rs.getDouble("MemDiscount") + "',"
                        + "MemDisc='" + strDisc + "' "
                        + "where tcode='" + table + "'";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(upd);
                stmt1.close();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
    }
//    public void updateMemberDiscount(String table, MemberBean memberBean) {
//        String strDisc = "";
//        String memCode = "";
//        String memName = "";
//        if (memberBean != null) {
//            if (!memberBean.getMember_DiscountRate().equals("")) {
//                strDisc = memberBean.getMember_DiscountRate();
//            }
//            memCode = memberBean.getMember_Code();
//            memName = memberBean.getMember_NameThai();
//        }
//        /**
//         * * OPEN CONNECTION **
//         */
//        MySQLConnect mysql = new MySQLConnect();
//        mysql.open();
//        try {
//            String sql = "select sum(R_PrSubAmt) as MemDiscount "
//                    + "from balance where r_table='" + table + "' "
//                    + "order by R_Index;";
//            try (Statement stmt = mysql.getConnection().createStatement()) {
//                ResultSet rs = stmt.executeQuery(sql);
//                if (rs.next()) {
//                    String upd = "update tablefile set "
//                            + "MemDiscAmt='" + rs.getDouble("MemDiscount") + "',"
//                            + "MemDisc='" + strDisc + "', "
//                            + "MemCode='" + memCode + "', "
//                            + "MemName='" + memName + "' "
//                            + "where tcode='" + table + "'";
//                    try (Statement stmt1 = mysql.getConnection().createStatement()) {
//                        stmt1.executeUpdate(upd);
//                    }
//                }
//
//                rs.close();
//            }
//        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
//        }
//    }

    public void updateMemberAllBalance(String table, MemberBean memberBean) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            /*
             R_PrSubType = -M
             R_PrSubCode = MEM
             R_PrSubQuan = 1
             R_PrSubDisc = 5 (เปอร์เซ็นต์การลด)
             R_PrSubBath = 0
             R_PrSubAmt = 4.75 (5% ของราคาสินค้า)
             R_QuanCanDisc = 0
             */
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "and R_Discount='Y' "
                    + "and R_Void<>'V' "
                    + "order by R_Index;";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balance = new BalanceBean();

                // คำนวณหาว่าลดเท่าไหร่
                if (memberBean != null) {
                    String[] subPercent = memberBean.getMember_DiscountRate().split("/");
                    int Percent = 0;

                    if (subPercent.length == 3) {
                        String R_Normal = rs.getString("R_Normal");
                        if (R_Normal == null) {
                            R_Normal = "";
                        }
                        if (R_Normal.equals("N")) {
                            Percent = Integer.parseInt(subPercent[0].replace("  ", "00").trim());
                        } else if (R_Normal.equals("C")) {
                            Percent = Integer.parseInt(subPercent[1].replace("  ", "00").trim());
                        } else if (R_Normal.equals("S")) {
                            Percent = Integer.parseInt(subPercent[2].replace("  ", "00").trim());
                        }
                    }

                    balance.setR_PrSubAmt((rs.getDouble("R_Total") * Percent) / 100);
                    balance.setR_QuanCanDisc(0);// if member default 0

                    String sqlUpd = "update balance set "
                            + "R_PrSubType='-M',"
                            + "R_PrSubCode='MEM',"
                            + "R_PrSubQuan='" + rs.getDouble("R_Quan") + "',"
                            + "R_PrSubDisc='" + Percent + "',"
                            + "R_PrSubBath='0',"
                            + "R_PrSubAmt='" + ((rs.getDouble("R_Total") * Percent) / 100) + "',"
                            + "R_QuanCanDisc='0' "
                            + "where R_Index='" + rs.getString("R_Index") + "' "
                            + "and R_Table='" + rs.getString("R_Table") + "'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    stmt1.executeUpdate(sqlUpd);
                    stmt1.close();
                }
            }

            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
    }

//    public void updateMemVIPAllBalance(String table, MemberBean memberBean) {
//        /**
//         * * OPEN CONNECTION **
//         */
//        MySQLConnect mysql = new MySQLConnect();
//        mysql.open();
//        try {
//            /*
//             R_PrSubType = -M
//             R_PrSubCode = MEM
//             R_PrSubQuan = 1
//             R_PrSubDisc = 5 (เปอร์เซ็นต์การลด)
//             R_PrSubBath = 0
//             R_PrSubAmt = 4.75 (5% ของราคาสินค้า)
//             R_QuanCanDisc = 0
//             */
//            String sql = "select * from balance "
//                    + "where R_Table='" + table + "' "
//                    + "and R_Discount='Y' "
//                    + "order by R_Index;";
//            Statement stmt = mysql.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                BalanceBean balance = new BalanceBean();
//
//                // คำนวณหาว่าลดเท่าไหร่
//                String[] subPercent = memberBean.getMember_DiscountRate().split("/");
//                int Percent = 0;
//
//                if (subPercent.length == 3) {
//                    String R_Normal = rs.getString("R_Normal");
//                    if (R_Normal == null) {
//                        R_Normal = "";
//                    }
//                    if (R_Normal.equals("N")) {
//                        Percent = Integer.parseInt(subPercent[0].trim());
//                    } else if (R_Normal.equals("C")) {
//                        Percent = Integer.parseInt(subPercent[1].trim());
//                    } else if (R_Normal.equals("S")) {
//                        Percent = Integer.parseInt(subPercent[2].trim());
//                    }
//                }
//                if (subPercent.length == 3) {
//                    String R_Normal = rs.getString("R_Normal");
//                    if (R_Normal == null) {
//                        R_Normal = "";
//                    }
//                    if (R_Normal.equals("N")) {
//                        Percent = Integer.parseInt(subPercent[0].trim());
//                    } else if (R_Normal.equals("C")) {
//                        Percent = Integer.parseInt(subPercent[1].trim());
//                    } else if (R_Normal.equals("S")) {
//                        Percent = Integer.parseInt(subPercent[2].trim());
//                    }
//                }
//
//                balance.setR_PrSubAmt((rs.getDouble("R_Total") * Percent) / 100);
//                balance.setR_QuanCanDisc(0);// if member default 0
//
//                String sqlUpd = "update balance set "
//                        + "R_PrSubType='-M',"
//                        + "R_PrSubCode='MEM',"
//                        + "R_PrSubQuan='" + rs.getDouble("R_Quan") + "',"
//                        + "R_PrSubDisc='" + Percent + "',"
//                        + "R_PrSubBath='0',"
//                        + "R_PrSubAmt='" + ((rs.getDouble("R_Total") * Percent) / 100) + "',"
//                        + "R_QuanCanDisc='0' "
//                        + "where R_Index='" + rs.getString("R_Index") + "' "
//                        + "and R_Table='" + rs.getString("R_Table") + "'";
//                Statement stmt1 = mysql.getConnection().createStatement();
//                stmt1.executeUpdate(sqlUpd);
//                stmt1.close();
//            }
//
//            rs.close();
//        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
//            
//        }
//    }
    public void updateMemVIPAllBalance(String table, String discountRate) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            /*
             R_PrSubType = -M
             R_PrSubCode = MEM
             R_PrSubQuan = 1
             R_PrSubDisc = 5 (เปอร์เซ็นต์การลด)
             R_PrSubBath = 0
             R_PrSubAmt = 4.75 (5% ของราคาสินค้า)
             R_QuanCanDisc = 0
             */
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "and R_Discount='Y' "
                    + "order by R_Index;";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balance = new BalanceBean();

                // คำนวณหาว่าลดเท่าไหร่
                String[] subPercent = discountRate.split("/");
                int Percent = 0;

                if (subPercent.length == 3) {
                    String R_Normal = rs.getString("R_Normal");
                    if (R_Normal == null) {
                        R_Normal = "";
                    }
                    if (R_Normal.equals("N")) {
                        Percent = Integer.parseInt(subPercent[0].trim());
                    } else if (R_Normal.equals("C")) {
                        Percent = Integer.parseInt(subPercent[1].trim());
                    } else if (R_Normal.equals("S")) {
                        Percent = Integer.parseInt(subPercent[2].trim());
                    }
                }
                if (subPercent.length == 3) {
                    String R_Normal = rs.getString("R_Normal");
                    if (R_Normal == null) {
                        R_Normal = "";
                    }
                    if (R_Normal.equals("N")) {
                        Percent = Integer.parseInt(subPercent[0].trim());
                    } else if (R_Normal.equals("C")) {
                        Percent = Integer.parseInt(subPercent[1].trim());
                    } else if (R_Normal.equals("S")) {
                        Percent = Integer.parseInt(subPercent[2].trim());
                    }
                }

                balance.setR_PrSubAmt((rs.getDouble("R_Total") * Percent) / 100);
                balance.setR_QuanCanDisc(0);// if member default 0

                String sqlUpd = "update balance set "
                        + "R_PrSubType='-M',"
                        + "R_PrSubCode='MEM',"
                        + "R_PrSubQuan='" + rs.getDouble("R_Quan") + "',"
                        + "R_PrSubDisc='" + Percent + "',"
                        + "R_PrSubBath='0',"
                        + "R_PrSubAmt='" + ((rs.getDouble("R_Total") * Percent) / 100) + "',"
                        + "R_QuanCanDisc='0' "
                        + "where R_Index='" + rs.getString("R_Index") + "' "
                        + "and R_Table='" + rs.getString("R_Table") + "'";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sqlUpd);
                stmt1.close();
            }

            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
    }

    public void getScoreMemberToCheckBill(String table, MemberBean memberBean) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            DateConvert dc = new DateConvert();
            if (!memberBean.getMember_Code().equals("")) {
                String expireDate = memberBean.getMember_ExpiredDate().toString();
                String date = dc.GetCurrentDate();
                int compare = Integer.parseInt(expireDate) - Integer.parseInt(date);
                if (compare > 0) {

                }
            }
            mysql.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }
    }

    public void setPoint(MemberBean memberBean) {
        try {
            DateConvert dc =new DateConvert();
            POSConfigSetup posConfig;
            PosControl posControl = new PosControl();
            posConfig = posControl.getData();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }
    }

}
