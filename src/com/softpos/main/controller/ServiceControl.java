package com.softpos.main.controller;

import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.model.TableFileBean;
import com.softpos.main.model.BalanceBean;
import com.softpos.database.util.MySQLConnect;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.softpos.main.utils.DateConvert;
import com.softpos.main.utils.MSG;

public class ServiceControl {

    private POSConfigSetup CONFIG;
    private final PosControl posControl;
    private SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm:ss");
    DecimalFormat df = new DecimalFormat("##.00");

    public ServiceControl() {
        posControl = new PosControl();
        CONFIG = POSConfigSetup.Bean();
    }

    public void updateService(String table) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        DateConvert dc = new DateConvert();
        try {
            BalanceControl balanceControl = new BalanceControl();
//            ArrayList<BalanceBean> dataBalance = balanceControl.getAllBalance(table);
            ArrayList<BalanceBean> dataBalance = balanceControl.getAllBalanceSum(table);
            double ServiceTotal = 0.00;
            double VatTotal = 0.00;
            double ServicePercent = POSConfigSetup.Bean().getP_Service();
            double ServiceAmt = 0.00;
            double vatEXCTypeServiceN = 0.00;
            String serviceYorN = "";
            double tAmount = 0;
            double totalDiscount = 0.00;
            double NetTotal = 0;
            for (int i = 0; i < dataBalance.size(); i++) {
                double ServiceLine = 0.00;
                BalanceBean balance = (BalanceBean) dataBalance.get(i);

                //ตรวจสอบสินค้าที่ Void ไปแล้ว
                if (balance.getR_Void().equals("V")) {
                    continue;
                }
//                if (POSConfigSetup.Bean().getP_Service().equals("0")) {
//                            continue;
//                        }

                totalDiscount += balance.getR_PrAmt()
                        + balance.getR_PrSubAmt()
                        + balance.getR_PrSubBath()
                        + balance.getR_PrCuAmt()
                        + balance.getR_DiscBath();
//                
                totalDiscount = (totalDiscount);
                if (posControl.getETDPW_Active(balance.getR_ETD(), POSConfigSetup.Bean().getP_SerChkBySaleType())) {

                    //ตรวจสอบการให้ส่วนลด
                    if (balance.getR_Service().equals("Y")) {
                        String a = POSConfigSetup.Bean().getP_SerChkBySaleType();
//                        System.out.println("Processing Cal Discount type = " + a);
                        //คิดค่าบริการแบบยอด Net
                        if (POSConfigSetup.Bean().getP_ServiceType().equals("N")) {
                            String r_index = balance.getR_Index();
//                        System.out.println("r_index= " + r_index + "R_ETD = " + balance.getR_ETD());
                            String[] strs = POSConfigSetup.Bean().getP_SerChkBySaleType().split("/");
                            String serviceSaleTypeE = "";
                            String serviceSaleTypeT = "";
                            String serviceSaleTypeD = "";
                            String serviceSaleTypeP = "";
                            String serviceSaleTypeW = "";
                            for (String data : strs) {
                                serviceSaleTypeE = strs[0];
                                serviceSaleTypeT = strs[1];
                                serviceSaleTypeD = strs[2];
                                serviceSaleTypeP = strs[3];
                                serviceSaleTypeW = strs[4];
                            }
                            if (balance.getR_ETD().equals("E") && serviceSaleTypeE.equals("Y")) {
                                ServiceLine = balance.getR_Total();
                                serviceYorN = "Y";
                            } else if (balance.getR_ETD().equals("T") && serviceSaleTypeT.equals("Y")) {
                                serviceYorN = "Y";
                                ServiceLine = balance.getR_Total();
                            } else if (balance.getR_ETD().equals("D") && serviceSaleTypeD.equals("Y")) {
                                serviceYorN = "Y";
                                ServiceLine = balance.getR_Total();
                            } else if (balance.getR_ETD().equals("P") && serviceSaleTypeP.equals("Y")) {
                                serviceYorN = "Y";
                                ServiceLine = balance.getR_Total();
                            } else if (balance.getR_ETD().equals("W") && serviceSaleTypeW.equals("Y")) {
                                serviceYorN = "Y";
                                ServiceLine = balance.getR_Total();
                            } else if (balance.getR_ETD().equals("E") && serviceSaleTypeE.equals("N")) {
                                serviceYorN = "N";
                                ServiceLine = 0;
                            } else if (balance.getR_ETD().equals("T") && serviceSaleTypeT.equals("N")) {
                                serviceYorN = "N";
                                ServiceLine = 0;
                            } else if (balance.getR_ETD().equals("D") && serviceSaleTypeD.equals("N")) {
                                serviceYorN = "N";
                                ServiceLine = 0;
                            } else if (balance.getR_ETD().equals("P") && serviceSaleTypeP.equals("N")) {
                                serviceYorN = "N";
                                ServiceLine = 0;
                            } else if (balance.getR_ETD().equals("W") && serviceSaleTypeW.equals("N")) {
                                serviceYorN = "N";
                                ServiceLine = 0;
                            }
                            ServiceTotal += ServiceLine;
//                            ServiceLine = balance.getR_Total() - balance.getR_PrAmt();
//                            ServiceTotal += ServiceLine;
                        }
//  
//                        คิดค่าบริการแบบยอด Gross
                        if (POSConfigSetup.Bean().getP_ServiceType().equals("G")) {
                            ServiceLine = balance.getR_Total();
                        }
                    }
                    if (ServiceTotal > 0 && POSConfigSetup.Bean().getP_ServiceType().equals("N")) {
                        ServiceAmt = (ServiceTotal - totalDiscount) * ServicePercent / 100;
//                        ServiceAmt = getDouble(ServiceAmt);
                        ServiceAmt = ServiceAmt;
                    }
//                    คิดภาษีหรือไม่ ?
                    if (balance.getR_Vat().equals("V")) {
                        if (POSConfigSetup.Bean().getP_VatType().equals("I")) {
                            //คิดภาษีแบบ Include Vat
                            VatTotal += balance.getR_Total();
                        }
                        if (POSConfigSetup.Bean().getP_VatType().equals("E")) {
                            //คิดภาษีแบบ Exclude Vat
                            VatTotal += balance.getR_Total();
                        }

                    }

                }
                if (balance.getR_ETD().equals("T")) {
                    VatTotal += balance.getR_Total();
                }
                tAmount += balance.getR_Total();
            }

            //คำนวณค่า Service ตรวจสอบต่อว่าจะให้ปัดขึ้นหรือลง
//            double ServiceAmt = 0;
//            if (ServiceTotal > 0) {
//                ServiceAmt = ServiceTotal * ServicePercent / 100;
//                //ปัดเศษทศนิยม
//                ServiceAmt = getDouble(ServiceAmt);
//            }
//            double Total_Vat_Amt = 0;
//            double TAmount = 0.00;
//            String sqlTotal = "select sum(R_Price*R_Quan) TAmount "
//                    + "from balance "
//                    + "where r_void<>'V' "
//                    + "and R_Table='" + table + "';";
//            Statement stmt1 = mysql.getConnection().createStatement();
//            ResultSet rsTotal = stmt1.executeQuery(sqlTotal);
//            if (rsTotal.next()) {
//                TAmount = rsTotal.getDouble("TAmount");
//            }
//
//            rsTotal.close();
//            stmt1.close();
//            String sql = "select * from tablefile where Tcode='" + table + "'";
//            Statement stmt2 = mysql.getConnection().createStatement();
//            ResultSet rs = stmt2.executeQuery(sql);
//            if (rs.next()) {
//                double ProDiscAmt = rs.getDouble("ProDiscAmt") + rs.getDouble("ItemDiscAmt");
//                if (VatTotal == 0) {
////                    Total_Vat_Amt = (TAmount - ProDiscAmt) + ServiceAmt;
//                    Total_Vat_Amt = (TAmount - ProDiscAmt) + ServiceAmt;
//                } else {
//                    Total_Vat_Amt = (TAmount) + ServiceAmt;
//                }
//            }
//            rs.close();
//            stmt2.close();
            TableFileControl tfc = new TableFileControl();
            TableFileBean tBean = tfc.getData(table);
//            double totalDiscount = tBean.getEmpDiscAmt() + tBean.getFastDiscAmt() + tBean.getTrainDiscAmt()
//                    + tBean.getMemDiscAmt() + tBean.getSubDiscAmt() + tBean.getDiscBath()
//                    + tBean.getSpaDiscAmt() + tBean.getCuponDiscAmt() + tBean.getItemDiscAmt();
            String sqlUpd = "";

            ServiceAmt = getDouble(ServiceAmt, "SERVICE");
            totalDiscount = getDouble(totalDiscount, "DISCOUNT");
            NetTotal = tAmount - totalDiscount + ServiceAmt ;
            sqlUpd = "update tablefile "
                    + "set ServiceAmt = '" + ServiceAmt + "',"
                    + "TAmount='" + tAmount + "',"
                    + "NetTotal = " + NetTotal + " "
                    + "where Tcode = '" + table + "'";

            Statement stmt3 = mysql.getConnection().createStatement();
            stmt3.executeUpdate(sqlUpd);
            stmt3.close();
            // update all discount

//            if (!CONFIG.getP_DiscRound().equals("O")) {
////                totalDiscount = NumberControl.UP_DOWN_NATURAL_BAHT(totalDiscount);
//                totalDiscount = totalDiscount;
//            }
//            if (POSConfigSetup.Bean().getP_ServiceType().equals("G") && serviceYorN.equals("Y")) {
//                ServiceAmt = (((tBean.getTAmount() - totalDiscount)) * ServicePercent / 100);
//                if (ServiceAmt < 0) {
//                    ServiceAmt = 0;
//                }
//                Total_Vat_Amt = (tBean.getTAmount() - totalDiscount) + ServiceAmt;
//            }
//            if (POSConfigSetup.Bean().getP_ServiceType().equals("N") && serviceYorN.equals("Y")) {
////                ServiceAmt = getDouble(((tBean.getTAmount())) * ServicePercent / 100);
//                ServiceAmt = ((ServiceTotal - totalDiscount) * ServicePercent / 100);
//                if (ServiceAmt < 0) {
//                    ServiceAmt = 0;
//                }
//                Total_Vat_Amt = (tBean.getTAmount() - totalDiscount) + ServiceAmt;
//            }
//            if (POSConfigSetup.Bean().getP_VatType().equals("I")) {
//                vatEXCTypeServiceN = Total_Vat_Amt;
//            }
//            if (POSConfigSetup.Bean().getP_VatType().equals("E")) {
//                vatEXCTypeServiceN = Total_Vat_Amt + (Total_Vat_Amt * POSConfigSetup.Bean().getP_Vat() / 100);
//            }
            try {
//                sqlUpd = "update tablefile "
//                        + "set ServiceAmt = '" + ServiceAmt + "',"
////                        + "TAmount='" + TAmount + "',"
//                        + "NetTotal = " + Total_Vat_Amt + " "
//                        + "where Tcode = '" + table + "'";
//                sqlUpd = "update tablefile "
//                        + "set ServiceAmt = '" + ServiceAmt + "',"
//                        + "TAmount='" + TAmount + "',"
//                        + "NetTotal = " + vatEXCTypeServiceN + " "
//                        + "where Tcode = '" + table + "'";
//                Statement stmt4 = mysql.getConnection().createStatement();
//                stmt4.executeUpdate(sqlUpd);
//                stmt4.close();
                if (tAmount > 0 && tBean.getTLoginTime().equals("00:00:00")) {
                    String sqlUpdateDate = "update tablefile set "
                            + "tlogindate='" + dc.GetCurrentDate() + "', "
                            + "tlogintime='" + Timefmt.format(new Date()) + "' "
                            + "where tcode='" + table + "'";
                    Statement stmt5 = mysql.getConnection().createStatement();
                    stmt5.executeUpdate(sqlUpdateDate);

                }
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            }
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

        mysql.close();
    }
//    public void updateService(String table) {
//        MySQLConnect mysql = new MySQLConnect();
//        mysql.open();
//        try {
//            BalanceControl balanceControl = new BalanceControl();
//            ArrayList<BalanceBean> dataBalance = balanceControl.getAllBalance(table);
//            double ServiceTotal = 0;
//            double VatTotal = 0;
//            double ServicePercent = POSConfigSetup.Bean().getP_Service();
//
//            for (int i = 0; i < dataBalance.size(); i++) {
//                double ServiceLine;
//                BalanceBean balance = (BalanceBean) dataBalance.get(i);
//
//                //ตรวจสอบสินค้าที่ Void ไปแล้ว
//                if (balance.getR_Void().equals("V")) {
//                    continue;
//                }
////                if (POSConfigSetup.Bean().getP_Service().equals("0")) {
////                            continue;
////                        }
//
//                //ตรวจสอบ Type ของรายการสินค้าว่าสามารถให้ส่วนลดได้หรือไม่ ?
//                if (posControl.getETDPW_Active(balance.getR_ETD(), POSConfigSetup.Bean().getP_SerChkBySaleType())) {
//
//                    //ตรวจสอบการให้ส่วนลด
//                    if (balance.getR_Service().equals("Y")) {
//
//                        //คิดค่าบริการแบบยอด Net
//                        if (POSConfigSetup.Bean().getP_ServiceType().equals("N")) {
//                            ServiceLine = balance.getR_Total() - balance.getR_PrAmt();
//                            ServiceTotal += ServiceLine;
//                        }
//
//                        //คิดค่าบริการแบบยอด Gross
//                        if (POSConfigSetup.Bean().getP_ServiceType().equals("G")) {
//                            ServiceLine = balance.getR_Total();
//                            ServiceTotal += ServiceLine;
//                        }
//
//                    }
//
//                    //คิดภาษีหรือไม่ ?
//                    if (balance.getR_Vat().equals("V")) {
//                        //คิดภาษีแบบ Include Vat
//                        if (POSConfigSetup.Bean().getP_VatType().equals("I")) {
//                            //NetTotal += (balance.getR_Total() - balance.getR_PrAmt())+ServiceLine;
////                            VatTotal += 0;
//                        } //คิดภาษีแบบ Exclude Vat
//                        else if (POSConfigSetup.Bean().getP_VatType().equals("E")) {
//                            VatTotal += balance.getR_Total();
//                        }
//                    }
//                }
//            }
//
//            //คำนวณค่า Service ตรวจสอบต่อว่าจะให้ปัดขึ้นหรือลง
//            double ServiceAmt = 0;
//            if (ServiceTotal > 0) {
//                ServiceAmt = ServiceTotal * ServicePercent / 100;
//
//                //ปัดเศษทศนิยม
//                ServiceAmt = getDouble(ServiceAmt);
//            }
//
//            double Total_Vat_Amt = 0;
//            double TAmount = 0.00;
//            String sqlTotal = "select sum(R_Price*R_Quan) TAmount "
//                    + "from balance "
//                    + "where r_void<>'V' "
//                    + "and R_Table='" + table + "';";
//            Statement stmt1 = mysql.getConnection().createStatement();
//            ResultSet rsTotal = stmt1.executeQuery(sqlTotal);
//            if (rsTotal.next()) {
//                TAmount = rsTotal.getDouble("TAmount");
//            }
//
//            rsTotal.close();
//            stmt1.close();
//
//            String sql = "select * from tablefile where Tcode='" + table + "'";
//            Statement stmt2 = mysql.getConnection().createStatement();
//            ResultSet rs = stmt2.executeQuery(sql);
//            if (rs.next()) {
//                double ProDiscAmt = rs.getDouble("ProDiscAmt") + rs.getDouble("ItemDiscAmt");
//
//                //ไม่มีสินค้ารายการใดคิด Vat
//                if (VatTotal == 0) {
//                    Total_Vat_Amt = TAmount - ProDiscAmt + ServiceAmt;
//                } else {
//                    double vat = (VatTotal - ProDiscAmt + ServiceAmt) * POSConfigSetup.Bean().getP_Vat() / 100;
//                    Total_Vat_Amt = (TAmount - ProDiscAmt + ServiceAmt) + vat;
//                }
//            }
//            rs.close();
//            stmt2.close();
//
//            String sqlUpd = "update tablefile "
//                    + "set ServiceAmt = '" + ServiceAmt + "',"
//                    + "TAmount='" + TAmount + "',"
//                    + "NetTotal = " + Total_Vat_Amt + " "
//                    + "where Tcode = '" + table + "'";
//            Statement stmt3 = mysql.getConnection().createStatement();
//            stmt3.executeUpdate(sqlUpd);
//            stmt3.close();
//
//            // update all discount
//            TableFileControl tfc = new TableFileControl();
//            TableFileBean tBean = tfc.getData(table);
//            double totalDiscount = tBean.getEmpDiscAmt() + tBean.getFastDiscAmt() + tBean.getTrainDiscAmt()
//                    + tBean.getMemDiscAmt() + tBean.getSubDiscAmt() + tBean.getDiscBath()
//                    + tBean.getSpaDiscAmt() + tBean.getCuponDiscAmt() + tBean.getItemDiscAmt()
//                    + tBean.getProDiscAmt();
//            ServiceAmt = ((tBean.getTAmount() - totalDiscount) * ServicePercent) / 100;
//            Total_Vat_Amt = (tBean.getTAmount() - totalDiscount) + ServiceAmt;
//            try {
//                sqlUpd = "update tablefile "
//                        + "set ServiceAmt = '" + ServiceAmt + "',"
//                        + "TAmount='" + TAmount + "',"
//                        + "NetTotal = " + Total_Vat_Amt + " "
//                        + "where Tcode = '" + table + "'";
//                Statement stmt4 = mysql.getConnection().createStatement();
//                stmt4.executeUpdate(sqlUpd);
//                stmt4.close();
//            } catch (SQLException e) {
//                MSG.ERR(e.getMessage());
//            }
//        } catch (Exception e) {
//            MSG.ERR(null, e.getMessage());
//        }
//        
//        mysql.close();
//    }
    
    public static double getDouble(double db) {
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("U")) {
            db = NumberControl.UP_BAHT(db);
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("D")) {
            db = NumberControl.DOWN_BAHT(db);
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("O")) {
            return db;
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("N")) {
            db = NumberControl.UP_DOWN_NATURAL_BAHT(db);
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("F")) {
            db = NumberControl.UP_DOWN_25(db);
        } else {
            return db;
        }

        return db;
    }

    public static double getDouble(double db, String type) {
        if (db > 0) {
            switch (type) {
                case "SERVICE":
                    if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("U")) {
                        db = NumberControl.UP_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("D")) {
                        db = NumberControl.DOWN_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("O")) {
                        return db;
                    }
                    if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("N")) {
                        db = NumberControl.UP_DOWN_NATURAL_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("F")) {
                        db = NumberControl.UP_DOWN_25NewTotal(db);
                    } else {
                        return db;
                    }
                    break;
                case "DISCOUNT":
                    if (POSConfigSetup.Bean().getP_DiscRound().equalsIgnoreCase("U")) {
                        db = NumberControl.UP_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_DiscRound().equalsIgnoreCase("D")) {
                        db = NumberControl.DOWN_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_DiscRound().equalsIgnoreCase("O")) {
                        return db;
                    }
                    if (POSConfigSetup.Bean().getP_DiscRound().equalsIgnoreCase("N")) {
                        db = NumberControl.UP_DOWN_NATURAL_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_DiscRound().equalsIgnoreCase("F")) {
                        db = NumberControl.UP_DOWN_25(db);
                    } else {
                        return db;
                    }
                    break;
                case "PAYMENT":
                    if (POSConfigSetup.Bean().getP_PayBahtRound().equalsIgnoreCase("U")) {
                        db = NumberControl.UP_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_PayBahtRound().equalsIgnoreCase("D")) {
                        db = NumberControl.DOWN_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_PayBahtRound().equalsIgnoreCase("O")) {
                        return db;
                    }
                    if (POSConfigSetup.Bean().getP_PayBahtRound().equalsIgnoreCase("N")) {
                        db = NumberControl.UP_DOWN_NATURAL_BAHT(db);
                    }
                    if (POSConfigSetup.Bean().getP_PayBahtRound().equalsIgnoreCase("F")) {
                        db = NumberControl.UP_DOWN_25(db);
                    } else {
                        return db;
                    }
                    break;
            }
        }

        return db;
    }

}
