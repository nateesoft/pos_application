package com.softpos.main.controller;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.softpos.main.model.BalanceBean;
import com.softpos.main.program.NumberControl;
import com.softpos.main.program.POSConfigSetup;
import java.sql.SQLException;
import java.sql.Statement;
import util.MSG;

public class ServiceControl {

    private final PosControl posControl;

    public ServiceControl() {
        posControl = new PosControl();
    }

    public void updateService(String table) {
        try {
            BalanceControl balanceControl = new BalanceControl();
            ArrayList<BalanceBean> dataBalance = balanceControl.getAllBalance(table);
            double ServiceTotal = 0;
            double VatTotal = 0;
            double ServicePercent = POSConfigSetup.Bean().getP_Service();

            for (int i = 0; i < dataBalance.size(); i++) {
                double ServiceLine = 0;
                BalanceBean balance = (BalanceBean) dataBalance.get(i);

                //ตรวจสอบสินค้าที่ Void ไปแล้ว
                if (balance.getR_Void().equals("V")) {
                    continue;
                }

                //ตรวจสอบ Type ของรายการสินค้าว่าสามารถให้ส่วนลดได้หรือไม่ ?
                if (posControl.getETDPW_Active(balance.getR_ETD(), POSConfigSetup.Bean().getP_SerChkBySaleType())) {

                    //ตรวจสอบการให้ส่วนลด
                    if (balance.getR_Service().equals("Y")) {

                        //คิดค่าบริการแบบยอด Net
                        if (POSConfigSetup.Bean().getP_ServiceType().equals("N")) {
                            ServiceLine = balance.getR_Total() - balance.getR_PrAmt();
                            ServiceTotal += ServiceLine;
                        }

                        //คิดค่าบริการแบบยอด Gross
                        if (POSConfigSetup.Bean().getP_ServiceType().equals("G")) {
                            ServiceLine = balance.getR_Total();
                            ServiceTotal += ServiceLine;
                        }
                    }

                    //คิดภาษีหรือไม่ ?
                    if (balance.getR_Vat().equals("V")) {
                        //คิดภาษีแบบ Include Vat
                        if (POSConfigSetup.Bean().getP_VatType().equals("I")) {
                            //NetTotal += (balance.getR_Total() - balance.getR_PrAmt())+ServiceLine;
//                            VatTotal += 0;
                        } //คิดภาษีแบบ Exclude Vat
                        else if (POSConfigSetup.Bean().getP_VatType().equals("E")) {
                            VatTotal += balance.getR_Total();
                        }
                    }
                }
            }

            //คำนวณค่า Service ตรวจสอบต่อว่าจะให้ปัดขึ้นหรือลง
            double ServiceAmt = 0;
            if (ServiceTotal > 0) {
                ServiceAmt = ServiceTotal * ServicePercent / 100;

                //ปัดเศษทศนิยม
                ServiceAmt = getDouble(ServiceAmt);
            }

            double Total_Vat_Amt = 0;

            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            String sql = "select * from tablefile where Tcode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                double Product_Price_Total = rs.getDouble("TAmount");
                double ProDiscAmt = rs.getDouble("ProDiscAmt") + rs.getDouble("ItemDiscAmt");

                //ไม่มีสินค้ารายการใดคิด Vat
                if (VatTotal == 0) {
                    Total_Vat_Amt = Product_Price_Total - ProDiscAmt + ServiceAmt;
                } else {
                    double vat = (VatTotal - ProDiscAmt + ServiceAmt) * POSConfigSetup.Bean().getP_Vat() / 100;
                    Total_Vat_Amt = (Product_Price_Total - ProDiscAmt + ServiceAmt) + vat;
                }
            }
            rs.close();
            stmt.close();
            
            String sqlUpd = "update tablefile "
                    + "set ServiceAmt = '" + ServiceAmt + "',"
                    + "NetTotal = " + Total_Vat_Amt + " "
                    + "where Tcode = '" + table + "'";
            Statement stmt2 = mysql.getConnection().createStatement();
            int iUpd = stmt2.executeUpdate(sqlUpd);
            mysql.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            
        }
    }

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

}
