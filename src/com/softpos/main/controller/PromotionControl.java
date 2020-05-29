package com.softpos.main.controller;

import com.softpos.main.model.TPromotionBean;
import com.softpos.main.model.TPromotion2;
import com.softpos.main.model.TPromotion5;
import com.softpos.main.model.TPromotion4;
import com.softpos.main.model.Promotion;
import com.softpos.main.model.TPromotion3;
import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.model.TempPromotion;
import com.softpos.main.utils.ThaiUtil;
import com.softpos.main.model.ProductBean;
import com.softpos.main.model.ProtabBean;
import com.softpos.main.model.BalanceBean;
import com.softpos.database.util.MySQLConnect;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.softpos.main.utils.DateConvert;
import com.softpos.main.utils.MSG;

public class PromotionControl {

    private final PosControl posControl;

    public PromotionControl() {
        posControl = new PosControl();
    }

    public void saveTempPromotion(TempPromotion bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into temppromotion (TableNo,PrCode,PrType,PCode,PQty,PrTotalAmt,PrAmt) "
                    + "values('" + bean.getTableNo() + "','" + bean.getPrCode() + "','" + bean.getPrType() + "','"
                    + bean.getPCode() + "','" + bean.getPQty() + "','"
                    + bean.getPrTotalAmt() + "','" + bean.getPrAmt() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    public void savePromotionItem(String PCode, String Table, String Index, double prDisc, double prBaht) {
        BalanceControl bCon = new BalanceControl();
        BalanceBean bean = bCon.getProduct(PCode, Index);

        double prAmt = 0, R_Total = 0, addServiceAmt = 0, Service = 0;
        ProductControl pCon = new ProductControl();
        ProductBean product = pCon.getData(PCode);

        double priceMain = StockControl.PRODUCT_PRICE(PCode, bean.getR_ETD());

        /* สำหรับการคำนวณยอด GROSS */
        if (POSConfigSetup.Bean().getP_ServiceType().equalsIgnoreCase("G")) {//ยอด Gross
            if (prDisc > 0) {
                //คำนวณแบบ %
                prAmt = prDisc / 100 * priceMain;
                R_Total = priceMain - prAmt;

                bean.setR_PrDisc(prDisc);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prAmt);
            } else {
                //คำนวณแบบเงินบาท
                R_Total = priceMain - prBaht;

                bean.setR_PrDisc(0);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prBaht);
            }

            //ให้คิดค่าบริการด้วย
            if (product.getPService().equalsIgnoreCase("Y")) {
                Service = POSConfigSetup.Bean().getP_Service();
                addServiceAmt = Service / 100 * priceMain;

                //คำนวณหาค่าปัดเศษทศนิยม
                addServiceAmt = ServiceControl.getDouble(addServiceAmt, "SERVICE");

                R_Total = priceMain + addServiceAmt;
            }
        } /* สำหรับการคำนวณยอด NET */ else if (POSConfigSetup.Bean().getP_ServiceType().equalsIgnoreCase("N")) {//ยอด Net
            if (prDisc > 0) {
                //คำนวณแบบ %
                //คำนวณแบบ %
                prAmt = prDisc / 100 * priceMain;
                R_Total = priceMain - prAmt;

                bean.setR_PrDisc(prDisc);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prAmt);

            } else {
                //คำนวณแบบเงินบาท
                R_Total = priceMain - prBaht;

                bean.setR_PrDisc(0);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prBaht);
            }

            //ให้คิดค่าบริการด้วย
            if (product.getPService().equalsIgnoreCase("Y")) {
                Service = POSConfigSetup.Bean().getP_Service();
                addServiceAmt = Service / 100 * R_Total;

                //คำนวณหาค่าปัดเศษทศนิยม
                addServiceAmt = ServiceControl.getDouble(addServiceAmt, "SERVICE");

                R_Total = priceMain + addServiceAmt;
            }
        }

        bean.setR_PrType("-P");
        /* สำหรับ PrCode ต้องคำนวณหาโปรโมชันตามช่วงเวลา */
        bean.setR_PrCode("XXXXXXX");
        bean.setR_QuanCanDisc(0);
        bean.setR_Total(R_Total);

        /* SAve data to database */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "UPDATE balance set "
                    + "R_Total = '" + bean.getR_Total() + "',"
                    + "R_PrType = '" + bean.getR_PrType() + "',"
                    + "R_PrCode='" + bean.getR_PrCode() + "',"
                    + "R_PrDisc='" + bean.getR_PrDisc() + "',"
                    + "R_PrBath='" + bean.getR_PrBath() + "',"
                    + "R_PrAmt='" + bean.getR_PrAmt() + "',"
                    + "R_QuanCanDisc='" + bean.getR_QuanCanDisc() + "' "
                    + "WHERE R_Index='" + bean.getR_Index() + "' "
                    + "and R_Table='" + bean.getR_Table() + "' "
                    + "and R_PluCode='" + bean.getR_PluCode() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public ProtabBean getData(String proCode) {
        ProtabBean proTab = new ProtabBean();

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from protab where ProCode='" + proCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                proTab.setProCode(rs.getString("ProCode"));
                proTab.setProdesc(rs.getString("Prodesc"));
                try {
                    proTab.setPDate1(rs.getDate("PDate1"));
                    proTab.setPDate2(rs.getDate("PDate2"));
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }

                proTab.setPStrDay(rs.getString("PStrDay"));
                proTab.setPTime1S(rs.getString("PTime1S"));
                proTab.setPTime1E(rs.getString("PTime1E"));
                proTab.setPDisc1(rs.getInt("PDisc1"));
                proTab.setPSPDisc1(rs.getFloat("PSPDisc1"));
                proTab.setPTS1(rs.getString("PTS1"));
                proTab.setPTime2S(rs.getString("PTime2S"));
                proTab.setPTime2E(rs.getString("PTime2E"));
                proTab.setPDisc2(rs.getInt("PDisc2"));
                proTab.setPSPDisc2(rs.getFloat("PSPDisc2"));
                proTab.setPTS2(rs.getString("PTS2"));
                proTab.setPTime3S(rs.getString("PTime3S"));
                proTab.setPTime3E(rs.getString("PTime3E"));
                proTab.setPDisc3(rs.getInt("PDisc3"));
                proTab.setPSPDisc3(rs.getFloat("PSPDisc3"));
                proTab.setPTS3(rs.getString("PTS3"));
                proTab.setPType(rs.getString("PType"));
                proTab.setPSale1(rs.getInt("PSale1"));
                proTab.setPFree1(rs.getInt("PFree1"));
                proTab.setPSum1(rs.getInt("PSum1"));
                proTab.setPDiscFree1(rs.getFloat("PDiscFree1"));
                proTab.setPSale2(rs.getInt("PSale2"));
                proTab.setPFree2(rs.getInt("PFree2"));
                proTab.setPSum2(rs.getInt("PSum2"));
                proTab.setPDiscFree2(rs.getFloat("PDiscFree2"));
                proTab.setPSale3(rs.getInt("PSale3"));
                proTab.setPFree3(rs.getInt("PFree3"));
                proTab.setPSum3(rs.getInt("PSum3"));
                proTab.setPDiscFree3(rs.getFloat("PDiscFree3"));
                proTab.setPSale41(rs.getInt("PSale41"));
                proTab.setPFree41(rs.getInt("PFree41"));
                proTab.setPSale42(rs.getInt("PSale42"));
                proTab.setPFree42(rs.getInt("PFree42"));
                proTab.setPSale43(rs.getInt("PSale43"));
                proTab.setPFree43(rs.getInt("PFree43"));
                proTab.setPQty11(rs.getFloat("PQty11"));
                proTab.setPQty12(rs.getFloat("PQty12"));
                proTab.setPQty13(rs.getFloat("PQty13"));
                proTab.setPQty14(rs.getFloat("PQty14"));
                proTab.setPQDisc11(rs.getFloat("PQDisc11"));
                proTab.setPQDisc12(rs.getFloat("PQDisc12"));
                proTab.setPQDisc13(rs.getFloat("PQDisc13"));
                proTab.setPQDisc14(rs.getFloat("PQDisc14"));
                proTab.setPQBath11(rs.getFloat("PQBath11"));
                proTab.setPQBath12(rs.getFloat("PQBath12"));
                proTab.setPQBath13(rs.getFloat("PQBath13"));
                proTab.setPQBath14(rs.getFloat("PQBath14"));
                proTab.setPQty21(rs.getFloat("PQty21"));
                proTab.setPQty22(rs.getFloat("PQty22"));
                proTab.setPQty23(rs.getFloat("PQty23"));
                proTab.setPQty24(rs.getFloat("PQty24"));
                proTab.setPQDisc21(rs.getFloat("PQDisc21"));
                proTab.setPQDisc22(rs.getFloat("PQDisc22"));
                proTab.setPQDisc23(rs.getFloat("PQDisc23"));
                proTab.setPQDisc24(rs.getFloat("PQDisc24"));
                proTab.setPQBath21(rs.getFloat("PQBath21"));
                proTab.setPQBath22(rs.getFloat("PQBath22"));
                proTab.setPQBath23(rs.getFloat("PQBath23"));
                proTab.setPQBath24(rs.getFloat("PQBath24"));
                proTab.setPQty31(rs.getFloat("PQty31"));
                proTab.setPQty32(rs.getFloat("PQty32"));
                proTab.setPQty33(rs.getFloat("PQty33"));
                proTab.setPQty34(rs.getFloat("PQty34"));
                proTab.setPQDisc31(rs.getFloat("PQDisc31"));
                proTab.setPQDisc32(rs.getFloat("PQDisc32"));
                proTab.setPQDisc33(rs.getFloat("PQDisc33"));
                proTab.setPQDisc34(rs.getFloat("PQDisc34"));
                proTab.setPQBath31(rs.getFloat("PQBath31"));
                proTab.setPQBath32(rs.getFloat("PQBath32"));
                proTab.setPQBath33(rs.getFloat("PQBath33"));
                proTab.setPQBath34(rs.getFloat("PQBath34"));
            }
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return proTab;
    }

    private ProtabBean getDataSql(String sql) {
        ProtabBean proTab = new ProtabBean();

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                proTab.setProCode(rs.getString("ProCode"));
                proTab.setProdesc(ThaiUtil.ASCII2Unicode(rs.getString("Prodesc")));
                try {
                    proTab.setPDate1(rs.getDate("PDate1"));
                    proTab.setPDate2(rs.getDate("PDate2"));
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }

                proTab.setPStrDay(rs.getString("PStrDay"));
                proTab.setPTime1S(rs.getString("PTime1S"));
                proTab.setPTime1E(rs.getString("PTime1E"));
                proTab.setPDisc1(rs.getInt("PDisc1"));
                proTab.setPSPDisc1(rs.getFloat("PSPDisc1"));
                proTab.setPTS1(rs.getString("PTS1"));
                proTab.setPTime2S(rs.getString("PTime2S"));
                proTab.setPTime2E(rs.getString("PTime2E"));
                proTab.setPDisc2(rs.getInt("PDisc2"));
                proTab.setPSPDisc2(rs.getFloat("PSPDisc2"));
                proTab.setPTS2(rs.getString("PTS2"));
                proTab.setPTime3S(rs.getString("PTime3S"));
                proTab.setPTime3E(rs.getString("PTime3E"));
                proTab.setPDisc3(rs.getInt("PDisc3"));
                proTab.setPSPDisc3(rs.getFloat("PSPDisc3"));
                proTab.setPTS3(rs.getString("PTS3"));
                proTab.setPType(rs.getString("PType"));
                proTab.setPSale1(rs.getInt("PSale1"));
                proTab.setPFree1(rs.getInt("PFree1"));
                proTab.setPSum1(rs.getInt("PSum1"));
                proTab.setPDiscFree1(rs.getFloat("PDiscFree1"));
                proTab.setPSale2(rs.getInt("PSale2"));
                proTab.setPFree2(rs.getInt("PFree2"));
                proTab.setPSum2(rs.getInt("PSum2"));
                proTab.setPDiscFree2(rs.getFloat("PDiscFree2"));
                proTab.setPSale3(rs.getInt("PSale3"));
                proTab.setPFree3(rs.getInt("PFree3"));
                proTab.setPSum3(rs.getInt("PSum3"));
                proTab.setPDiscFree3(rs.getFloat("PDiscFree3"));
                proTab.setPSale41(rs.getInt("PSale41"));
                proTab.setPFree41(rs.getInt("PFree41"));
                proTab.setPSale42(rs.getInt("PSale42"));
                proTab.setPFree42(rs.getInt("PFree42"));
                proTab.setPSale43(rs.getInt("PSale43"));
                proTab.setPFree43(rs.getInt("PFree43"));
                proTab.setPQty11(rs.getFloat("PQty11"));
                proTab.setPQty12(rs.getFloat("PQty12"));
                proTab.setPQty13(rs.getFloat("PQty13"));
                proTab.setPQty14(rs.getFloat("PQty14"));
                proTab.setPQDisc11(rs.getFloat("PQDisc11"));
                proTab.setPQDisc12(rs.getFloat("PQDisc12"));
                proTab.setPQDisc13(rs.getFloat("PQDisc13"));
                proTab.setPQDisc14(rs.getFloat("PQDisc14"));
                proTab.setPQBath11(rs.getFloat("PQBath11"));
                proTab.setPQBath12(rs.getFloat("PQBath12"));
                proTab.setPQBath13(rs.getFloat("PQBath13"));
                proTab.setPQBath14(rs.getFloat("PQBath14"));
                proTab.setPQty21(rs.getFloat("PQty21"));
                proTab.setPQty22(rs.getFloat("PQty22"));
                proTab.setPQty23(rs.getFloat("PQty23"));
                proTab.setPQty24(rs.getFloat("PQty24"));
                proTab.setPQDisc21(rs.getFloat("PQDisc21"));
                proTab.setPQDisc22(rs.getFloat("PQDisc22"));
                proTab.setPQDisc23(rs.getFloat("PQDisc23"));
                proTab.setPQDisc24(rs.getFloat("PQDisc24"));
                proTab.setPQBath21(rs.getFloat("PQBath21"));
                proTab.setPQBath22(rs.getFloat("PQBath22"));
                proTab.setPQBath23(rs.getFloat("PQBath23"));
                proTab.setPQBath24(rs.getFloat("PQBath24"));
                proTab.setPQty31(rs.getFloat("PQty31"));
                proTab.setPQty32(rs.getFloat("PQty32"));
                proTab.setPQty33(rs.getFloat("PQty33"));
                proTab.setPQty34(rs.getFloat("PQty34"));
                proTab.setPQDisc31(rs.getFloat("PQDisc31"));
                proTab.setPQDisc32(rs.getFloat("PQDisc32"));
                proTab.setPQDisc33(rs.getFloat("PQDisc33"));
                proTab.setPQDisc34(rs.getFloat("PQDisc34"));
                proTab.setPQBath31(rs.getFloat("PQBath31"));
                proTab.setPQBath32(rs.getFloat("PQBath32"));
                proTab.setPQBath33(rs.getFloat("PQBath33"));
                proTab.setPQBath34(rs.getFloat("PQBath34"));
            } else {
                proTab = null;
            }
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return proTab;
    }

    public void updatePromotion(String table) {
        clearPromotion(table);
        double R_PrAmt = 0;
        double TAmount = 0.00;
        double ProDiscAmt = 0.00;
        double NetTotal;

        ProductControl proControl = new ProductControl();
        ProductBean product;

        BalanceControl balanceControl = new BalanceControl();
        ArrayList<BalanceBean> dataBean = balanceControl.getAllBalancePromotion(table);//Filter by Promotion and Discount = 'Y'
        String PCode;

        double R_Quan = 0.00;

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();

        for (int i = 0; i < dataBean.size(); i++) {
            BalanceBean balance = (BalanceBean) dataBean.get(i);

            //ตรวจสอบสินค้าที่ Void ไปแล้ว
            if (balance.getR_Void().equals("V")) {
                continue;
            }

            PCode = balance.getR_PluCode();
            product = proControl.getData(PCode);

            String temp_R_ETD = balance.getR_ETD();
            String temp_P_SerChkBySaleType = POSConfigSetup.Bean().getP_SerChkBySaleType();

            if (posControl.getETDPW_Active(temp_R_ETD, temp_P_SerChkBySaleType)) {
                String E_Format_Date = DateControl.GET_CURRENT_NAME_DAY();
                String sql = "select * from protab "
                        + "where ProCode='" + product.getPPromotion1() + "' "
                        + "and (PDate1<=curdate() and PDate2>=curdate()) "
                        + "and (PTime1S<=curtime() and PTime1E>=curtime()) "
                        + "and PStrDay like '%" + E_Format_Date + "%' "
                        + "and PTS1 like '%" + balance.getR_ETD() + "%' ";

                ProtabBean protab1 = getDataSql(sql);
                if (protab1 != null) {
                    try {
                        /*ให้อัพเดตข้อมูลใน balance เป็นเครื่องหมาย -P ก่อน หมายถึงเข้าข่ายโปรโมชัน */
                        String sqlUpdatePro = "update balance set "
                                + "R_PrType='-P',"
                                + "R_PrCode='" + protab1.getProCode() + "' "
                                + "where R_Table='" + table + "' "
                                + "and R_PluCode='" + balance.getR_PluCode() + "' "
                                + "and R_Void <> 'V' and r_quancandisc>'0'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        int iUpdate = stmt1.executeUpdate(sqlUpdatePro);
                        System.out.println(iUpdate + ":Update -P");

                        //Sale Promotion Type 1
                        if (protab1.getPType().equals(Promotion.PROMOTION_TYPE_1)) {

                            TAmount += balance.getR_Total();

                            //จำนวน % มากกว่า 0 % ให้คิดแบบเปอร์เซ็นต์
                            if (protab1.getPDisc1() > 0) {
                                R_PrAmt = balance.getR_Total() * protab1.getPDisc1() / 100;
                                protab1.setPSPDisc1(0);
                            } //ถ้าจำนวน % เป็น 0 ให้คิดแบบจำนวนบาท
                            else if (protab1.getPSPDisc1() > 0) {
                                R_PrAmt = protab1.getPSPDisc1() * balance.getR_Quan();
                                //protab1.setPDisc1(0);
                                protab1.setPDisc1((int) (R_PrAmt / balance.getR_Total() * 100));
                            }

                            /*
                             ให้อัพเดตข้อมูลใน balance ที่คอลัมน์ R_PrCode, R_PrDisc, R_PrBath, R_PrChkType, R_QuanCanDisc, R_PrAmt
                             */
                            sqlUpdatePro = "update balance set "
                                    + "R_PrCode='" + protab1.getProCode() + "',"
                                    + "R_PrDisc='" + protab1.getPDisc1() + "',"
                                    + "R_PrBath='0',"
                                    + "R_PrChkType='" + protab1.getPType() + "',"
                                    + "R_PrQuan='" + balance.getR_Quan() + "',"
                                    + "R_QuanCanDisc='0',"
                                    + "R_PrAmt='" + R_PrAmt + "' "
                                    + "where R_Table='" + balance.getR_Table() + "' "
                                    //                                    + "and R_index='" + balance.getR_Index() + "' "
                                    + "and R_PluCode='" + balance.getR_PluCode() + "' "
                                    + "and R_PrType='-P' "
                                    + "and R_Void <> 'V' ";
                            iUpdate = stmt1.executeUpdate(sqlUpdatePro);
                            stmt1.close();
//                            ProDiscAmt += R_PrAmt;
                            ProDiscAmt = Double.parseDouble(BalanceControl.GetDiscount(table).replace(",", ""));

                            if (iUpdate > 0) {
                                //Update temp promotion
                                TempPromotion temp = new TempPromotion();
                                temp.setPCode(PCode);
                                temp.setPQty(R_Quan);
                                temp.setPrAmt(R_PrAmt);
                                temp.setPrCode(balance.getR_PrCode());
                                temp.setPrTotalAmt(ProDiscAmt);
                                temp.setPrType(balance.getR_PrType());
                                temp.setTableNo(table);

                                saveTempPromotion(temp);
                            }

                        } //Sale Promotion Type 2
                        else if (protab1.getPType().equals(Promotion.PROMOTION_TYPE_2)) {
                            // GET FROM Database
                            String sqlCheck = "select sum(R_Quan) R_Quan "
                                    + "from balance "
                                    + "where R_PluCode='" + balance.getR_PluCode() + "' "
                                    + "and R_Table='" + balance.getR_Table() + "' "
                                    + "and R_Void <> 'V' ";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sqlCheck);
                            double Total_Qty = 0;

                            if (rs.next()) {
                                Total_Qty = rs.getDouble("R_Quan");
                            }

                            rs.close();
                            stmt.close();

                            // COMPUTER Function
                            int Total_Pro_Free = (protab1.getPSale1() + protab1.getPFree1());
                            double Item_Free = Total_Qty / Total_Pro_Free;
                            if (Item_Free <= 0) {
                                Item_Free = 1;
                            }
                            double Total_Rate = Total_Pro_Free * Item_Free;

                            if (Total_Qty >= Total_Rate) {
                                double Pr_Quan;

                                //คำนวณหาถัวเฉลี่ย
                                BigDecimal a100 = new BigDecimal(100);
                                BigDecimal aPro = new BigDecimal(Total_Pro_Free);

                                BigDecimal R_PrDisc = a100.divide(aPro, 6, 6);//ทศนิยม 6 ตำแหน่ง
                                double R_PrDisc_Total = R_PrDisc.doubleValue();

                                // Get data from database again
                                sqlCheck = "select * from balance "
                                        + "where R_PluCode='" + balance.getR_PluCode() + "' "
                                        + "and R_Table='" + balance.getR_Table() + "' "
                                        + "and R_Void <> 'V' "
                                        + "order by R_Index";
                                Statement stmt2 = mysql.getConnection().createStatement();
                                rs = stmt2.executeQuery(sqlCheck);

                                double Customer_Buy_Quantity = 0;

                                while (rs.next()) {
                                    double QuanCanDisc = rs.getInt("R_Quan");
                                    double Temp_QuanCanDisc = QuanCanDisc;
                                    Customer_Buy_Quantity += rs.getDouble("R_Quan");

                                    if (Customer_Buy_Quantity > Total_Rate) {
                                        Pr_Quan = (QuanCanDisc - (Customer_Buy_Quantity - Total_Rate));
                                        QuanCanDisc = QuanCanDisc - Pr_Quan;
                                    } else {
                                        Pr_Quan = QuanCanDisc;
                                        QuanCanDisc = 0;
                                    }

                                    R_PrAmt = (balance.getR_Price() / Total_Pro_Free) * Pr_Quan;
                                    if (R_PrAmt <= 0) {
                                        R_PrDisc_Total = 0;
                                        R_PrAmt = 0;
                                        Pr_Quan = 0;
                                        QuanCanDisc = Temp_QuanCanDisc;
                                    }

                                    ProDiscAmt += R_PrAmt;
                                    TAmount += rs.getDouble("R_Total");

                                    sqlUpdatePro = "update balance set "
                                            + "R_PrDisc='" + R_PrDisc_Total + "',"
                                            + "R_PrQuan='" + Pr_Quan + "',"
                                            + "R_PrBath='0',"
                                            + "R_PrChkType='" + protab1.getPType() + "',"
                                            + "R_QuanCanDisc='" + QuanCanDisc + "',"
                                            + "R_PrAmt='" + R_PrAmt + "' "
                                            + "where R_Table='" + rs.getString("R_Table") + "' "
                                            + "and R_PluCode='" + rs.getString("R_PluCode") + "' "
                                            + "and R_Index='" + rs.getString("R_Index") + "' "
                                            + "and R_PrType='-P' "
                                            + "and R_Void <> 'V' ";
                                    Statement stmt3 = mysql.getConnection().createStatement();
                                    stmt3.executeUpdate(sqlUpdatePro);
                                    stmt3.close();
                                }

                                TPromotion2 bean = new TPromotion2();
                                bean.setTCode(balance.getR_Table());
                                bean.setPCode(PCode);
                                bean.setProCode(protab1.getProCode());
                                bean.setPQuan(Total_Qty);
                                bean.setPPrice(balance.getR_Price());
                                bean.setMacNo(balance.getMacno());

                                saveTPromotion2(bean);

                                rs.close();
                                stmt2.close();
                            }

                        }//END TYPE 2
                        /*
                         สำหรับ Type 3-5 ให้คละรายการสินค้าได้หมดแล้ว
                        
                         */ //Sale Promotion Type 3
                        else if (protab1.getPType().equals(Promotion.PROMOTION_TYPE_3)) {
                            // GET FROM Database
                            String sqlCheck = "select sum(R_Quan) R_Quan, sum(R_Total) R_Total "
                                    + "from balance "
                                    + "where R_Table='" + balance.getR_Table() + "' "
                                    + "and R_PrCode='" + protab1.getProCode() + "' "
                                    + "and R_QuanCandisc>'0' "
                                    + "and R_PRType='-P'"
                                    + "and R_Void <> 'V' ";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sqlCheck);
                            double Sum_Total_Qty = 0;
                            double Sum_Total_Price = 0;
                            int round = 0;
                            if (rs.next()) {
                                Sum_Total_Qty = rs.getDouble("R_Quan");
                                Sum_Total_Price = rs.getInt("R_Total");
                                round = rs.getInt("R_Quan");
                                TAmount = Sum_Total_Price;

                            }

                            rs.close();
                            stmt.close();

                            // COMPUTER Function
                            int Pro_Disc = protab1.getPSum1();//ซื้อครบจำนวนนี้สามารถให้ส่วนลดได้ ( เช่น ซื้อ 3 รายการลด 20 บาท เป็นต้น)

                            double disc_amt = protab1.getPDiscFree1();//จำนวนเงินที่ลด เช่น 20.00 บาท
                            if (Pro_Disc == 0) {
                                Pro_Disc = 1;
                            }
                            double Item_disc = Sum_Total_Qty / Pro_Disc;//จำนวนที่ให้ส่วนลด เช่น ยอดซื้อทั้งหมด 6 รายการ แต่ซื้อ 3 ลด 20 บาท = 6
                            if (Item_disc <= 0) {
                                Item_disc = 1;
                            }
                            double Total_Disc_Amt = disc_amt * Item_disc;//สรุปยอดทั้งหมดที่ลด เช่น ซื้อ 6 รายการ ยอดที่ลดคือ (20*(6/3)) = 40 บาท

                            if (round >= Pro_Disc) {
                                round = round / Pro_Disc;
                            } else {
                                round = 0;
                            }
                            if (Sum_Total_Qty >= Pro_Disc) {

                                ProDiscAmt = Total_Disc_Amt;

                                double Pr_Quan;

                                //คำนวณหาถัวเฉลี่ย
                                BigDecimal aSum_Total_Price = new BigDecimal(Sum_Total_Price);
                                BigDecimal aTotal_Disc_Amt = new BigDecimal(Total_Disc_Amt * 100);

                                //R_PrDisc = ส่วนลด/ยอดเงินที่ซื้อทุกรายการ*100
                                BigDecimal R_PrDisc = aTotal_Disc_Amt.divide(aSum_Total_Price, 6, 6);//ทศนิยม 6 ตำแหน่ง
                                double R_PrDisc_Total = R_PrDisc.doubleValue();

                                for (int j = round; j > 0; j--) {
                                    // Get data from database again
                                    sqlCheck = "select * from balance "
                                            + "where R_Table='" + balance.getR_Table() + "' "
                                            + "and R_PrCode='" + protab1.getProCode() + "' "
                                            + "and R_Void <> 'V' "
                                            + "and R_QuanCanDisc>'0' "
                                            + "order by R_Index desc LIMIT 4";//เรียงลำดับแบบย้อนหลัง 6,5,4,3,2,1 (ปกติจะเป็น 1,2,3,4,5,6)
                                    Statement stmt2 = mysql.getConnection().createStatement();
                                    rs = stmt2.executeQuery(sqlCheck);

                                    int Customer_Buy_Quantity = 0;
                                    int countLoop = 0;
                                    while (rs.next()) {
                                        countLoop++;
                                        double QuanCanDisc = rs.getDouble("R_Quan");
                                        double Temp_QuanCanDisc = QuanCanDisc;
                                        double R_Total = rs.getDouble("R_Total");
                                        Customer_Buy_Quantity += rs.getDouble("R_Quan");

                                        if (Customer_Buy_Quantity > Pro_Disc) {
                                            Pr_Quan = (QuanCanDisc - (Customer_Buy_Quantity - Pro_Disc));
                                            QuanCanDisc = QuanCanDisc - Pr_Quan;
                                        } else {
                                            Pr_Quan = QuanCanDisc;
                                            QuanCanDisc = 0;
                                        }

                                        //R_PrAmt = (R_PrDisc*R_Total)/100
                                        R_PrAmt = R_PrDisc_Total * R_Total / 100;
                                        if (R_PrAmt <= 0) {
                                            R_PrDisc_Total = 0;
                                            R_PrAmt = 0;
                                            Pr_Quan = 0;
                                            QuanCanDisc = Temp_QuanCanDisc;
                                        }

                                        String R_Index = rs.getString("R_Index");
                                        String R_PluCode = rs.getString("R_PluCode");
                                        String R_Table = rs.getString("R_Table");
                                        double R_Price = Double.parseDouble(rs.getString("R_Price"));
                                        String R_PrCode = rs.getString("R_PrCode");
                                        String MacNo = rs.getString("Macno");
                                        sqlUpdatePro = "update balance set "
                                                + "R_PrDisc='" + R_PrDisc_Total + "',"
                                                + "R_PrQuan='" + Pr_Quan + "',"
                                                + "R_PrBath='0',"
                                                + "R_PrChkType='" + protab1.getPType() + "',"
                                                + "R_QuanCanDisc='" + 0 + "',"
                                                + "R_PrAmt='" + R_PrAmt + "' "
                                                + "where R_Table='" + R_Table + "' "
                                                + "and R_Index='" + R_Index + "' "
                                                + "and R_PrType='-P' "
                                                + "and R_PrCode='" + rs.getString("R_PrCode") + "' "
                                                + "and R_Void <> 'V' ";
                                        Statement stmt3 = mysql.getConnection().createStatement();
                                        stmt3.executeUpdate(sqlUpdatePro);
                                        stmt3.close();

                                        TPromotion3 bean = new TPromotion3();
                                        bean.setR_Index(R_Index);
                                        bean.setTCode(R_Table);
                                        bean.setPCode(R_PluCode);
                                        bean.setProCode(R_PrCode);
                                        bean.setPQuan(Pr_Quan);
                                        bean.setPPrice(R_Price);
                                        bean.setMacNo(MacNo);

                                        saveTPromotion3(bean);
                                    }
                                    rs.close();
                                    stmt2.close();
                                }

                            }

                        }//END TYPE 3
                        //Sale Promotion Type 4
                        else if (protab1.getPType().equals(Promotion.PROMOTION_TYPE_4)) {
                            // GET FROM Database
                            String sqlCheck = "select sum(R_Quan) R_Quan, min(R_Price) Min_Price, sum(R_Total) R_Total "
                                    + "from balance "
                                    + "where R_Table='" + balance.getR_Table() + "' "
                                    + "and R_Void <> 'V' ";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sqlCheck);
                            int Total_Qty = 0;
                            double Min_Price = 0;
                            double R_Total;

                            if (rs.next()) {
                                Total_Qty = rs.getInt("R_Quan");
                                Min_Price = rs.getDouble("Min_Price");
                                R_Total = rs.getDouble("R_Total");

                                TAmount = R_Total;
                            }

                            rs.close();
                            stmt.close();

                            // COMPUTER Function
                            int Total_Free_Gift = (protab1.getPSale41() + protab1.getPFree41());
                            if (Total_Free_Gift == 0) {
                                Total_Free_Gift = 1;
                            }
                            int Item_Free = Total_Qty / Total_Free_Gift;
                            if (Item_Free <= 0) {
                                Item_Free = 1;
                            }
                            int Total_Rate = Total_Free_Gift * Item_Free;

                            if (Total_Qty >= Total_Rate) {
                                double Pr_Quan = 0;

                                //คำนวณหาถัวเฉลี่ย
                                BigDecimal a100 = new BigDecimal(100);
                                BigDecimal aPro = new BigDecimal(Total_Free_Gift);

                                BigDecimal R_PrDisc = a100.divide(aPro, 6, 6);//ทศนิยม 6 ตำแหน่ง
                                double R_PrDisc_Total = R_PrDisc.doubleValue();

                                // Get data from database again
                                sqlCheck = "select * from balance "
                                        + "where R_PrCode='" + protab1.getProCode() + "' "
                                        + "and R_Table='" + balance.getR_Table() + "' "
                                        + "and R_QuanCanDisc>'0' "
                                        + "and R_Void <> 'V' "
                                        + "order by R_PluCode";
                                Statement stmt2 = mysql.getConnection().createStatement();
                                rs = stmt2.executeQuery(sqlCheck);

                                int Customer_Buy_Quantity = 0;

                                while (rs.next()) {
                                    double QuanCanDisc = rs.getInt("R_Quan");
                                    double Temp_QuanCanDisc = QuanCanDisc;
                                    Customer_Buy_Quantity += QuanCanDisc;

                                    if (Customer_Buy_Quantity > Total_Rate) {
                                        Pr_Quan = (QuanCanDisc - (Customer_Buy_Quantity - Total_Rate));
                                        QuanCanDisc = QuanCanDisc - Pr_Quan;
                                    } else {
                                        Pr_Quan = QuanCanDisc;
                                        QuanCanDisc = 0;
                                    }

                                    R_PrAmt = (Min_Price / Total_Free_Gift) * Pr_Quan;
                                    if (R_PrAmt <= 0) {
                                        R_PrDisc_Total = 0;
                                        R_PrAmt = 0;
                                        Pr_Quan = 0;
                                        QuanCanDisc = Temp_QuanCanDisc;
                                    }

                                    ProDiscAmt = Min_Price;

                                    sqlUpdatePro = "update balance set "
                                            + "R_PrDisc='" + R_PrDisc_Total + "',"
                                            + "R_PrQuan='" + Pr_Quan + "',"
                                            + "R_PrBath='0',"
                                            + "R_PrChkType='" + protab1.getPType() + "',"
                                            + "R_QuanCanDisc='" + QuanCanDisc + "',"
                                            + "R_PrAmt='" + R_PrAmt + "' "
                                            + "where R_Table='" + rs.getString("R_Table") + "' "
                                            + "and R_PluCode='" + rs.getString("R_PluCode") + "' "
                                            + "and R_Index='" + rs.getString("R_Index") + "' "
                                            + "and R_Void <> 'V' "
                                            + "and R_PrType='-P' ";
                                    Statement stmt3 = mysql.getConnection().createStatement();
                                    stmt3.executeUpdate(sqlUpdatePro);
                                    stmt3.close();
                                }
                                sql = "select * from balance "
                                        + "where R_Table='" + table + "' "
                                        + "and R_PrCode='" + protab1.getProCode() + "' "
                                        + "and R_Void <> 'V' ";
                                Statement stmt4 = mysql.getConnection().createStatement();
                                ResultSet rss = stmt4.executeQuery(sql);
                                clearTPromotion4(table);
                                while (rss.next()) {
                                    TPromotion4 bean = new TPromotion4();
                                    bean.setR_Index(rss.getString("R_Index"));
                                    bean.setTCode(table);
                                    bean.setPCode(rss.getString("R_PluCode"));
                                    bean.setProCode(protab1.getProCode());
                                    bean.setPQuan(rss.getDouble("R_PrQuan"));
                                    bean.setPPrice(rss.getDouble("R_Price"));
                                    bean.setMacNo(balance.getMacno());

                                    saveTPromotion4(bean);

                                }

                                rss.close();
                                stmt4.close();

                                rs.close();
                                stmt2.close();
                            }

                        }//END TYPE 4
                        //Sale Promotion Type 5
                        else if (protab1.getPType().equals(Promotion.PROMOTION_TYPE_5)) {
                            // GET FROM Database
                            String sqlCheck = "select sum(R_Quan) R_Quan, sum(R_Total) R_Total "
                                    + "from balance "
                                    + "where R_Table='" + balance.getR_Table() + "' "
                                    + "and R_PrCode='" + protab1.getProCode() + "' "
                                    + "and R_Void <> 'V' ";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sqlCheck);
                            double Sum_Total_Qty = 0;
                            double Sum_Total_Price = 0;

                            if (rs.next()) {
                                Sum_Total_Qty = rs.getDouble("R_Quan");
                                Sum_Total_Price = rs.getInt("R_Total");

                                TAmount = Sum_Total_Price;
                            }

                            rs.close();
                            stmt.close();

                            // COMPUTER Function
                            double[] Pro_Qty = {protab1.getPQty14(), protab1.getPQty13(), protab1.getPQty12(), protab1.getPQty11()};//จำนวนรายการที่ซื้อครบตามโปรโมชัน เช่น ครบ 5 รายการ
                            double[] Pro_Disc = {protab1.getPQDisc14(), protab1.getPQDisc13(), protab1.getPQDisc12(), protab1.getPQDisc11()};//จำนวนเปอร์เซ็นต์การลด เช่น 1%, 5%, 10%
                            double[] Pro_Baht = {protab1.getPQBath14(), protab1.getPQBath13(), protab1.getPQBath12(), protab1.getPQBath11()};//จำนวนเงินที่ลด เช่น 20.00 บาท                            
                            double Total_Disc_Amt = 0;

                            for (int id = 0; id < Pro_Qty.length; id++) {
                                if (Pro_Disc[id] > 0) {
                                    Total_Disc_Amt = Sum_Total_Price * Pro_Disc[id] / 100;

                                } else if (Pro_Disc[id] == 0 && Pro_Baht[id] > 0) {
                                    Total_Disc_Amt = Pro_Baht[id];
                                }

                                //ตรวจสอบการปัดขึ้นลงทศนิยม
                                Total_Disc_Amt = DiscountControl.getDouble(Total_Disc_Amt);

                                if (Sum_Total_Qty >= Pro_Qty[id]) {

                                    ProDiscAmt = Total_Disc_Amt;

                                    double Pr_Quan;

                                    //คำนวณหาถัวเฉลี่ย
                                    BigDecimal aSum_Total_Price = new BigDecimal(Sum_Total_Price);
                                    BigDecimal aTotal_Disc_Amt = new BigDecimal(Total_Disc_Amt * 100);

                                    //R_PrDisc = ส่วนลด/ยอดเงินที่ซื้อทุกรายการ*100
                                    BigDecimal R_PrDisc6 = aTotal_Disc_Amt.divide(aSum_Total_Price, 6, 6);//ทศนิยม 6 ตำแหน่ง
                                    double R_PrDisc = R_PrDisc6.doubleValue();

                                    // Get data from database again
                                    sqlCheck = "select * from balance "
                                            + "where R_Table='" + balance.getR_Table() + "' "
                                            + "and R_PrCode='" + protab1.getProCode() + "' "
                                            + "and R_Void <> 'V' "
                                            + "order by R_Index";//เรียงลำดับแบบย้อนหลัง 6,5,4,3,2,1 (ปกติจะเป็น 1,2,3,4,5,6)
                                    Statement stmt2 = mysql.getConnection().createStatement();
                                    rs = stmt2.executeQuery(sqlCheck);

                                    double Customer_Buy_Quantity = 0;

                                    while (rs.next()) {
                                        double QuanCanDisc = rs.getDouble("R_Quan");
                                        double Temp_QuanCanDisc = QuanCanDisc;
                                        double R_Total = rs.getDouble("R_Total");
                                        Customer_Buy_Quantity += rs.getDouble("R_Quan");

                                        if (Customer_Buy_Quantity > Pro_Qty[id]) {
                                            Pr_Quan = (QuanCanDisc - (Customer_Buy_Quantity - Pro_Qty[id]));
                                            QuanCanDisc = QuanCanDisc - Pr_Quan;
                                        } else {
                                            Pr_Quan = QuanCanDisc;
                                            QuanCanDisc = 0;
                                        }

                                        //R_PrAmt = (R_PrDisc*R_Total)/100
                                        R_PrAmt = R_PrDisc * R_Total / 100;
                                        if (R_PrAmt <= 0) {
                                            R_PrDisc = 0;
                                            R_PrAmt = 0;
                                            Pr_Quan = 0;
                                            QuanCanDisc = Temp_QuanCanDisc;
                                        }

                                        String R_Index = rs.getString("R_Index");
                                        String R_Table = rs.getString("R_Table");

                                        sqlUpdatePro = "update balance set "
                                                + "R_PrDisc='" + R_PrDisc + "',"
                                                + "R_PrQuan='" + Pr_Quan + "',"
                                                + "R_PrBath='0',"
                                                + "R_PrChkType='" + protab1.getPType() + "',"
                                                + "R_QuanCanDisc='" + QuanCanDisc + "',"
                                                + "R_PrAmt='" + R_PrAmt + "' "
                                                + "where R_Table='" + R_Table + "' "
                                                + "and R_Index='" + R_Index + "' "
                                                + "and R_PrType='-P' "
                                                + "and R_PrCode='" + rs.getString("R_PrCode") + "' "
                                                + "and R_Void <> 'V' ";
                                        Statement stmt3 = mysql.getConnection().createStatement();
                                        stmt3.executeUpdate(sqlUpdatePro);
                                        stmt3.close();
                                    }
                                    TPromotion5 bean = new TPromotion5();
                                    bean.setTCode(balance.getR_Table());
                                    bean.setPCode(balance.getR_PluCode());
                                    bean.setProCode(balance.getR_PrCode());
                                    bean.setPQuan(balance.getR_Quan());
                                    bean.setPPrice(balance.getR_Price());
                                    bean.setMacNo(balance.getMacno());

                                    saveTPromotion5(bean);

                                    rs.close();
                                    stmt2.close();

                                    break;
                                }

                            }

                        }//END TYPE 5

                    } catch (Exception e) {
                        MSG.ERR(e.getMessage());
                    }
                }
            }

        }// END LOOP ALL BALANCE

        try {
            String sqlTotal = "select sum(R_Price*R_Quan) TAmount,sum(R_PRAmt) R_PRAmt from balance where r_void<>'V';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rsTotal = stmt.executeQuery(sqlTotal);
            if (rsTotal.next()) {
                TAmount = rsTotal.getDouble("TAmount");
                ProDiscAmt = rsTotal.getDouble("R_PRAmt");
            }

            rsTotal.close();
            stmt.close();

            NetTotal = TAmount - ProDiscAmt;

            //update tablefile
            String updateTableFile = "update tablefile set "
                    + "TAmount = '" + TAmount + "',"
                    + "ProDiscAmt = '" + ProDiscAmt + "',"
                    + "NetTotal = '" + NetTotal + "' "
                    + "where TCode='" + table + "'";
            Statement stmt1 = mysql.getConnection().createStatement();
            int update = stmt1.executeUpdate(updateTableFile);
            stmt1.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }

        mysql.close();
    }

    public void saveTPromotion(TPromotionBean bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sqlGetProtype = "select ptype from protab where procode='" + bean.getPrCode() + "';";
            Statement stmtGetPro = mysql.getConnection().createStatement();
            ResultSet rs = stmtGetPro.executeQuery(sqlGetProtype);
            if (rs.next()) {
                bean.setPrType(rs.getString("ptype"));
            }
            rs.close();
            stmtGetPro.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }
        try {
            String sql = "insert into t_promotion "
                    + "(R_Index,R_RefNo,Terminal,Cashier,PrCode,PrType,PCode,PDisc,PDiscBath,PPrice,PQty,PrTotalAmt,PrAmt,Flage) "
                    + "values('" + bean.getR_Index() + "','" + bean.getR_RefNo() + "','" + bean.getTerminal() + "','" + bean.getCashier()
                    + "','" + bean.getPrCode() + "','" + bean.getPrType() + "','" + bean.getPCode() + "','" + bean.getPDisc() + "','" + bean.getPDiscBath()
                    + "','" + bean.getPPrice() + "','" + bean.getPQty() + "','" + bean.getPrTotalAmt() + "','" + bean.getPrAmt() + "','" + bean.getFlage() + "')";
//            String sql = "insert into t_promotion "
//                    + "(R_Index,R_RefNo,Terminal,Cashier,PrCode,PrType,PCode,PDisc,PDiscBath,PPrice,PQty,PrTotalAmt,PrAmt,Flage) "
//                    + "values('" + bean.getR_Index() + "','" + bean.getR_RefNo() + "','" + bean.getTerminal() + "','" + bean.getCashier()
//                    + "','" + bean.getPrCode() + "','" + bean.getPrType() + "','" + bean.getPCode() + "','" + bean.getPDisc() + "','" + bean.getPDiscBath()
//                    + "','" + bean.getPPrice() + "','" + bean.getPQty() + "','" + bean.getPrTotalAmt() + "','" + bean.getPrAmt() + "','" + bean.getFlage() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            int update = stmt.executeUpdate(sql);
            if (update > 0) {

            }
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void saveTPromotion2(TPromotion2 bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into tpromotion2 "
                    + "(TCode,PCode,ProCode,PQuan,PPrice,MacNo) "
                    + "values('" + bean.getTCode() + "','" + bean.getPCode() + "','" + bean.getProCode() + "','"
                    + bean.getPQuan() + "','" + bean.getPPrice() + "','" + bean.getMacNo() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void clearTPromotion2(String table) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tpromotion2 where TCode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void saveTPromotion3(TPromotion3 bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into tpromotion3 "
                    + "(R_Index,TCode,PCode,ProCode,PQuan,PPrice,MacNo) "
                    + "values('" + bean.getR_Index() + "','" + bean.getTCode() + "','" + bean.getPCode() + "','"
                    + bean.getProCode() + "','" + bean.getPQuan() + "','" + bean.getPPrice() + "','" + bean.getMacNo() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void clearTPromotion3(String table) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tpromotion3 where TCode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void saveTPromotion4(TPromotion4 bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into tpromotion4 "
                    + "(R_Index,TCode,PCode,ProCode,PQuan,PPrice,MacNo) "
                    + "values('" + bean.getR_Index() + "','" + bean.getTCode() + "','" + bean.getPCode() + "','"
                    + bean.getProCode() + "','" + bean.getPQuan() + "','" + bean.getPPrice() + "','" + bean.getMacNo() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    public void clearTPromotion4(String table) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tpromotion4 where TCode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private void clearPromotion(String table) {
        clearTPromotion2(table);
        clearTPromotion3(table);
        clearTPromotion4(table);
    }

    public void saveTPromotion5(TPromotion5 bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into tpromotion5 (TCode,PCode,ProCode,PQuan,PPrice,MacNo) "
                    + "values('" + bean.getTCode() + "','" + bean.getPCode() + "','"
                    + bean.getProCode() + "','" + bean.getPQuan() + "','"
                    + bean.getPPrice() + "','" + bean.getMacNo() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    public void clearTPromotion5(String table) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tpromotion5 where TCode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

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
}
