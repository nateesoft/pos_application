package com.softpos.main.controller;

import com.softpos.main.model.CompanyBean;
import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.model.POSHWSetup;
import com.softpos.database.util.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class PosControl {

    private CompanyBean comBean;

    public PosControl() {
        comBean = getDataCompany();
    }

    public CompanyBean getCompany() {
        return comBean;
    }

    private CompanyBean getDataCompany() {
        CompanyBean bean = new CompanyBean();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from company";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setCode(rs.getString("Code"));
                bean.setName(ThaiUtil.ASCII2Unicode(rs.getString("Name")));
                bean.setAddress(ThaiUtil.ASCII2Unicode(rs.getString("Address")));
                bean.setSubprovince(ThaiUtil.ASCII2Unicode(rs.getString("Subprovince")));
                bean.setProvince(ThaiUtil.ASCII2Unicode(rs.getString("Province")));
                bean.setCity(ThaiUtil.ASCII2Unicode(rs.getString("City")));
                bean.setPOST(rs.getString("POST"));
                bean.setTel(rs.getString("Tel"));
                bean.setFax(rs.getString("Fax"));
                bean.setEmailaddress(rs.getString("emailaddress"));
                bean.setTax(rs.getString("Tax"));
                bean.setAccterm(rs.getDate("Accterm"));
                bean.setPosStock(rs.getString("PosStock"));
                bean.setRecCost(rs.getString("RecCost"));
                bean.setTriCost(rs.getString("TriCost"));
                bean.setTroCost(rs.getString("TroCost"));
                bean.setLosCost(rs.getString("LosCost"));
                bean.setFreCost(rs.getString("FreCost"));
                bean.setTri_Cost(rs.getString("Tri_Cost"));
                bean.setAdjCost(rs.getString("AdjCost"));
                bean.setRecAvgCost(rs.getString("RecAvgCost"));
                bean.setTriAvgCost(rs.getString("TriAvgCost"));
                bean.setTroAvgCost(rs.getString("TroAvgCost"));
                bean.setLosAvgCost(rs.getString("LosAvgCost"));
                bean.setFreAvgCost(rs.getString("FreAvgCost"));
                bean.setTri_AvgCost(rs.getString("Tri_AvgCost"));
                bean.setAdjAvgCost(rs.getString("AdjAvgCost"));
                bean.setUsePSetCost(rs.getString("UsePSetCost"));
                bean.setUsePIngredentCost(rs.getString("UsePIngredentCost"));
                bean.setHead1(rs.getString("Head1"));
                bean.setHead2(rs.getString("Head2"));
                bean.setHead3(rs.getString("Head3"));
                bean.setHead4(rs.getString("Head4"));
                bean.setPdahead1(rs.getString("pdahead1"));
                bean.setPdahead2(rs.getString("pdahead2"));
                bean.setDisplayTextInfo(rs.getString("DisplayTextInfo"));
                bean.setFloorTab1(rs.getString("FloorTab1"));
                bean.setFloorTab2(rs.getString("FloorTab2"));
                bean.setFloorTab3(rs.getString("FloorTab3"));
                bean.setFloorTab4(rs.getString("FloorTab4"));
                bean.setFloorTab5(rs.getString("FloorTab5"));
                bean.setFloorTab6(rs.getString("FloorTab6"));
                bean.setPdahead3(rs.getString("pdahead3"));
                bean.setPdahead4(rs.getString("pdahead4"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return bean;
    }

    public POSConfigSetup getData() {
        POSConfigSetup bean = new POSConfigSetup();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from posconfigsetup";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setP_Terminal(rs.getString("P_Terminal"));
                bean.setP_Vat(rs.getFloat("P_Vat"));
                bean.setP_Service(rs.getFloat("P_Service"));
                bean.setP_ServiceType(rs.getString("P_ServiceType"));

                bean.setP_VatPrn(rs.getString("P_VatPrn"));
                bean.setP_VatType(rs.getString("P_VatType"));
                bean.setP_BillCopy(rs.getInt("P_BillCopy"));
                bean.setP_BillCopyOne(rs.getString("P_BillCopyOne"));
                bean.setP_DefaultSaleType(rs.getString("P_DefaultSaleType"));
                bean.setP_EmpUse(rs.getString("P_EmpUse"));
                bean.setP_CodePrn(rs.getString("P_CodePrn"));
                bean.setP_CheckBillBeforCash(rs.getString("P_CheckBillBeforCash"));
                bean.setP_PrintDetailOnRecp(rs.getString("P_PrintDetailOnRecp"));
                bean.setP_PrintSum(rs.getString("P_PrintSum"));
                bean.setP_PrintRecpMessage(ThaiUtil.ASCII2Unicode(rs.getString("P_PrintRecpMessage")));
                bean.setP_MemDisc(rs.getString("P_MemDisc"));
                bean.setP_MemDiscChk(rs.getString("P_MemDiscChk"));
                bean.setP_MemDiscGet(rs.getString("P_MemDiscGet"));
                bean.setP_MemDiscMax(rs.getString("P_MemDiscMax"));
                bean.setP_FastDisc(rs.getString("P_FastDisc"));
                bean.setP_FastDiscChk(rs.getString("P_FastDiscChk"));
                bean.setP_FastDiscGet(rs.getString("P_FastDiscGet"));
                bean.setP_FastDiscMax(rs.getString("P_FastDiscMax"));
                bean.setP_EmpDisc(rs.getString("P_EmpDisc"));
                bean.setP_EmpDiscChk(rs.getString("P_EmpDiscChk"));
                bean.setP_EmpDiscGet(rs.getString("P_EmpDiscGet"));
                bean.setP_EmpDiscMax(rs.getString("P_EmpDiscMax"));
                bean.setP_TrainDisc(rs.getString("P_TrainDisc"));
                bean.setP_TrainDiscChk(rs.getString("P_TrainDiscChk"));
                bean.setP_TrainDiscGet(rs.getString("P_TrainDiscGet"));
                bean.setP_TrainDiscMax(rs.getString("P_TrainDiscMax"));
                bean.setP_SubDisc(rs.getString("P_SubDisc"));
                bean.setP_SubDiscChk(rs.getString("P_SubDiscChk"));
                bean.setP_SubDiscGet(rs.getString("P_SubDiscGet"));
                bean.setP_SubDiscMax(rs.getString("P_SubDiscMax"));
                bean.setP_DiscBathChk(rs.getString("P_DiscBathChk"));
                bean.setP_DiscBathMax(rs.getInt("P_DiscBathMax"));
                bean.setP_PromotionChk(rs.getString("P_PromotionChk"));
                bean.setP_SpacialChk(rs.getString("P_SpacialChk"));
                bean.setP_DiscRound(rs.getString("P_DiscRound"));
                bean.setP_ServiceRound(rs.getString("P_ServiceRound"));
                bean.setP_SerChkBySaleType(rs.getString("P_SerChkBySaleType"));
                bean.setP_DiscChkBySaleType(rs.getString("P_DiscChkBySaleType"));
                bean.setP_MemberSystem(rs.getString("P_MemberSystem"));
                bean.setKicSum(rs.getString("KicSum"));
                bean.setKicCopy(rs.getString("KicCopy"));
                bean.setP_PrintByItemType(rs.getString("P_PrintByItemType"));
                bean.setP_PrintTotalSumItemType(rs.getString("P_PrintTotalSumItemType"));
                bean.setP_PrintTotalSumNormalType(rs.getString("P_PrintTotalSumNormalType"));
                bean.setP_PrintTotalSumGroup(rs.getString("P_PrintTotalSumGroup"));
                bean.setWTime(rs.getString("WTime"));
                bean.setLTime(rs.getString("LTime"));
                bean.setP_PrintProductValue(rs.getString("P_PrintProductValue"));
                bean.setP_LimitTime(rs.getInt("P_LimitTime"));
                bean.setP_RefreshTime(rs.getInt("P_RefreshTime"));
                bean.setP_SaleDecimal(rs.getString("P_SaleDecimal"));
                bean.setP_PayBahtRound(rs.getString("P_PayBahtRound"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return bean;
    }

    public POSHWSetup getData(String macno) {
        POSHWSetup poshwsetup = new POSHWSetup();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from poshwsetup where terminal='" + macno + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                poshwsetup.setTerminal(rs.getString("macno"));
                poshwsetup.setOnAct(rs.getString("OnAct"));
                poshwsetup.setMacNo(macno);
                poshwsetup.setReceNo1(rs.getDouble("ReceNo1"));
                poshwsetup.setSaleType(rs.getString("SaleType"));
                poshwsetup.setTStock(rs.getString("TStock"));
                poshwsetup.setTSone(rs.getString("TSone"));
                poshwsetup.setHeading1(ThaiUtil.ASCII2Unicode(rs.getString("Heading1")));
                poshwsetup.setHeading2(ThaiUtil.ASCII2Unicode(rs.getString("Heading2")));
                poshwsetup.setHeading3(ThaiUtil.ASCII2Unicode(rs.getString("Heading3")));
                poshwsetup.setHeading4(ThaiUtil.ASCII2Unicode(rs.getString("Heading4")));
                poshwsetup.setFootting1(ThaiUtil.ASCII2Unicode(rs.getString("Footting1")));
                poshwsetup.setFootting2(ThaiUtil.ASCII2Unicode(rs.getString("Footting2")));
                poshwsetup.setFootting3(ThaiUtil.ASCII2Unicode(rs.getString("Footting3")));
                poshwsetup.setDRPort(rs.getString("DRPort"));
                poshwsetup.setDRType(rs.getString("DRType"));
                poshwsetup.setDRCOM(rs.getString("DRCOM"));
                poshwsetup.setDISPort(rs.getString("DISPort"));
                poshwsetup.setDISType(rs.getString("DISType"));
                poshwsetup.setDISCOM(rs.getString("DISCOM"));
                poshwsetup.setPRNPort(rs.getString("PRNPort"));
                poshwsetup.setPRNTYPE(rs.getString("PRNTYPE"));
                poshwsetup.setPRNCOM(rs.getString("PRNCOM"));
                poshwsetup.setPRNThaiLevel(rs.getString("PRNThaiLevel"));
                poshwsetup.setKIC1Port(rs.getString("KIC1Port"));
                poshwsetup.setKIC1Type(rs.getString("KIC1Type"));
                poshwsetup.setKIC1Com(rs.getString("KIC1Com"));
                poshwsetup.setKIC1ThaiLevel(rs.getString("KIC1ThaiLevel"));
                poshwsetup.setKIC2Port(rs.getString("KIC2Port"));
                poshwsetup.setKIC2Type(rs.getString("KIC2Type"));
                poshwsetup.setKIC2Com(rs.getString("KIC2Com"));
                poshwsetup.setKIC2ThaiLevel(rs.getString("KIC2ThaiLevel"));
                poshwsetup.setKIC3Port(rs.getString("KIC3Port"));
                poshwsetup.setKIC3Type(rs.getString("KIC3Type"));
                poshwsetup.setKIC3Com(rs.getString("KIC3Com"));
                poshwsetup.setKIC3ThaiLevel(rs.getString("KIC3ThaiLevel"));
                poshwsetup.setKIC4Port(rs.getString("KIC4Port"));
                poshwsetup.setKIC4Type(rs.getString("KIC4Type"));
                poshwsetup.setKIC4Com(rs.getString("KIC4Com"));
                poshwsetup.setKIC4ThaiLevel(rs.getString("KIC4ThaiLevel"));
                poshwsetup.setEJounal(rs.getString("EJounal"));
                poshwsetup.setEJDailyPath(rs.getString("EJDailyPath"));
                poshwsetup.setEJBackupPath(rs.getString("EJBackupPath"));
                poshwsetup.setPrnRate(rs.getInt("PrnRate"));
                poshwsetup.setDrRate(rs.getInt("DrRate"));
                poshwsetup.setDisRate(rs.getInt("DisRate"));
                poshwsetup.setEDCPort(rs.getString("EDCPort"));
                poshwsetup.setSMPBank(rs.getString("SMPBank"));
                poshwsetup.setMenuItemList(rs.getString("MenuItemList"));
                poshwsetup.setUseFloorPlan(rs.getString("UseFloorPlan"));
                poshwsetup.setTakeOrderChk(rs.getString("TakeOrderChk"));
               
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return poshwsetup;
    }

    private String[] getETDPW(String data) {
        String[] d = data.split("/");
        String str = "";
        if (d[0].equals("Y")) {
            str += "E,";
        }
        if (d[1].equals("Y")) {
            str += "T,";
        }
        if (d[2].equals("Y")) {
            str += "D,";
        }

        if (d[3].equals("Y")) {
            str += "P,";
        }

        if (d[4].equals("Y")) {
            str += "W,";
        }

        return str.split(",");
    }

    public boolean getETDPW_Active(String ETD, String data) {
        if (ETD == null || data == null) {
            return false;
        }
        String[] d = getETDPW(data);
        for (String dd : d) {
            if (ETD.equals(dd)) {
                return true;
            }
        }

        return false;
    }

    public static int getRefreshTime() {
        int refrechTime = 1;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select P_RefreshTime from posconfigsetup";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                refrechTime = rs.getInt("P_RefreshTime");
                if (refrechTime <= 0) {
                    refrechTime = 1;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }

        return refrechTime;
    }

}
