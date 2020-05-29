package com.softpos.main.controller;

import com.softpos.main.model.BranchBean;
import com.softpos.database.util.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class BranchControl {

    private BranchBean bean;

    public BranchControl() {
        bean = getData();
    }

    public BranchBean getBranch() {
        return bean;
    }

    public static void updateKicItemNo() {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();

        try {
            String sql = "update branch set KicItemNo=KicItemNo+1";
//            Statement stmt = mysql.getConnection().createStatement();
            mysql.getConnection().createStatement().executeUpdate(sql);
//            stmt.executeUpdate(sql);
//            stmt.close();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    public static BranchBean getData() {
        BranchBean bean = new BranchBean();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from branch";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                bean.setCode(rs.getString("Code"));
                bean.setName(ThaiUtil.ASCII2Unicode(rs.getString("Name")));
                bean.setAddressNo(rs.getString("AddressNo"));
                bean.setLocality(rs.getString("Locality"));
                bean.setSubProvince(rs.getString("SubProvince"));
                bean.setProvince(rs.getString("Province"));
                bean.setPost(rs.getString("Post"));
                bean.setTel_No(rs.getString("Tel_No"));
                bean.setFax_No(rs.getString("Fax_No"));
                bean.setE_Mail(rs.getString("E_Mail"));
                bean.setManager(rs.getString("Manager"));
                bean.setLocation_Area(rs.getString("Location_Area"));
                bean.setSer_Area(rs.getFloat("Ser_Area"));
                bean.setCou_Area(rs.getFloat("Cou_Area"));
                bean.setKic_Area(rs.getFloat("Kic_Area"));
                bean.setTot_Area(rs.getFloat("Tot_Area"));
                bean.setCost(rs.getFloat("Cost"));
                bean.setCharge(rs.getFloat("Charge"));
                bean.setFlageCost(rs.getString("FlageCost"));
                bean.setGp(rs.getFloat("Gp"));
                bean.setFlageGp(rs.getString("FlageGp"));
                bean.setRemark(rs.getString("Remark"));
                bean.setArBillNo(rs.getFloat("ArBillNo"));
                bean.setEarneatBillNo(rs.getFloat("EarneatBillNo"));
                bean.setReturnBillNo(rs.getFloat("ReturnBillNo"));
                try {
                    bean.setPrintAutoSumDate(rs.getDate("PrintAutoSumDate"));
                } catch (SQLException e) {
                    System.out.println("Error Date: " + e.getMessage());
                }
                bean.setSaveOrder(rs.getString("SaveOrder"));
                bean.setSaveOrderCopy(rs.getString("SaveOrderCopy"));
                bean.setSaveOrderChk(rs.getString("SaveOrderChk"));
                bean.setKIC1(rs.getString("KIC1"));
                bean.setKIC2(rs.getString("KIC2"));
                bean.setKIC3(rs.getString("KIC3"));
                bean.setKIC4(rs.getString("KIC4"));
                bean.setKIC5(rs.getString("KIC5"));
                bean.setKIC6(rs.getString("KIC6"));
                bean.setKIC7(rs.getString("KIC7"));
                bean.setKIC8(rs.getString("KIC8"));
                bean.setKIC9(rs.getString("KIC9"));
                bean.setSmartCard(rs.getString("SmartCard"));
                bean.setGetFile(rs.getString("GetFile"));
                bean.setRetFile(rs.getString("RetFile"));
                bean.setPointFile(rs.getString("PointFile"));
                bean.setCntLoop(rs.getInt("CntLoop"));
                bean.setInvNo(rs.getFloat("InvNo"));
                bean.setInvCashNo(rs.getFloat("InvCashNo"));
                bean.setInvCash(rs.getFloat("InvCash"));
                bean.setInvActive(rs.getString("InvActive"));
                bean.setCreditAct(rs.getString("CreditAct"));
                bean.setPromotionGP(rs.getString("PromotionGP"));
                bean.setLockTime(rs.getInt("LockTime"));
                bean.setKicItemNo(rs.getInt("KicItemNo"));
                bean.setPT1(rs.getString("PT1"));
                bean.setPT2(rs.getString("PT2"));
                bean.setPT3(rs.getString("PT3"));
                bean.setPT4(rs.getString("PT4"));
                bean.setPT5(rs.getString("PT5"));
                bean.setPONO(rs.getInt("PONO"));
                bean.setPrintKicForm(rs.getString("PrintKicForm"));
                bean.setPrintInvForm(rs.getString("PrintInvForm"));
                bean.setPSelectStk(rs.getString("PSelectStk"));
                bean.setPStkChk(rs.getString("PStkChk"));
                bean.setPMinStkChk(rs.getString("PMinStkChk"));
                bean.setRoundUpTime(rs.getFloat("RoundUpTime"));
                bean.setGiftStatusChk(rs.getString("GiftStatusChk"));
                bean.setKICCopy1(rs.getString("KICCopy1"));
                bean.setKICCopy2(rs.getString("KICCopy2"));
                bean.setKICCopy3(rs.getString("KICCopy3"));
                bean.setKICCopy4(rs.getString("KICCopy4"));
                bean.setKICCopy5(rs.getString("KICCopy5"));
                bean.setKICCopy6(rs.getString("KICCopy6"));
                bean.setKICCopy7(rs.getString("KICCopy7"));
                bean.setKICCopy8(rs.getString("KICCopy8"));
                bean.setKICCopy9(rs.getString("KICCopy9"));
                bean.setKICChk1(rs.getString("KICChk1"));
                bean.setKICChk2(rs.getString("KICChk2"));
                bean.setKICChk3(rs.getString("KICChk3"));
                bean.setKICChk4(rs.getString("KICChk4"));
                bean.setKICChk5(rs.getString("KICChk5"));
                bean.setKICChk6(rs.getString("KICChk6"));
                bean.setKICChk7(rs.getString("KICChk7"));
                bean.setKICChk8(rs.getString("KICChk8"));
                bean.setKICChk9(rs.getString("KICChk9"));
                bean.setUpdateBranchPoint(rs.getString("UpdateBranchPoint"));
                bean.setKicName1(rs.getString("KicName1"));
                bean.setKicName2(rs.getString("KicName2"));
                bean.setKicName3(rs.getString("KicName3"));
                bean.setKicName4(rs.getString("KicName4"));
                bean.setKicName5(rs.getString("KicName5"));
                bean.setKicName6(rs.getString("KicName6"));
                bean.setKicName7(rs.getString("KicName7"));
                bean.setKicName8(rs.getString("KicName8"));
                bean.setKicName9(rs.getString("KicName9"));
                bean.setKicPrintOnReceipt1(rs.getString("KicPrintOnReceipt1"));
                bean.setKicPrintOnReceipt2(rs.getString("KicPrintOnReceipt2"));
                bean.setKicPrintOnReceipt3(rs.getString("KicPrintOnReceipt3"));
                bean.setKicPrintOnReceipt4(rs.getString("KicPrintOnReceipt4"));
                bean.setKicPrintOnReceipt5(rs.getString("KicPrintOnReceipt5"));
                bean.setKicPrintOnReceipt6(rs.getString("KicPrintOnReceipt6"));
                bean.setKicPrintOnReceipt7(rs.getString("KicPrintOnReceipt7"));
                bean.setKicPrintOnReceipt8(rs.getString("KicPrintOnReceipt8"));
                bean.setKicPrintOnReceipt9(rs.getString("KicPrintOnReceipt9"));
                bean.setKicQue(rs.getInt("KicQue"));
                bean.setKic10(rs.getString("Kic10"));
                bean.setKic11(rs.getString("Kic11"));
                bean.setKic12(rs.getString("Kic12"));
                bean.setKic13(rs.getString("Kic13"));
                bean.setKic14(rs.getString("Kic14"));
                bean.setKic15(rs.getString("Kic15"));
                bean.setKic16(rs.getString("Kic16"));
                bean.setKic17(rs.getString("Kic17"));
                bean.setKic18(rs.getString("Kic18"));
                bean.setKic19(rs.getString("Kic19"));
                bean.setKic20(rs.getString("Kic20"));
                bean.setKicCopy10(rs.getString("KicCopy10"));
                bean.setKicCopy11(rs.getString("KicCopy11"));
                bean.setKicCopy12(rs.getString("KicCopy12"));
                bean.setKicCopy13(rs.getString("KicCopy13"));
                bean.setKicCopy14(rs.getString("KicCopy14"));
                bean.setKicCopy15(rs.getString("KicCopy15"));
                bean.setKicCopy16(rs.getString("KicCopy16"));
                bean.setKicCopy17(rs.getString("KicCopy17"));
                bean.setKicCopy18(rs.getString("KicCopy18"));
                bean.setKicCopy19(rs.getString("KicCopy19"));
                bean.setKicCopy20(rs.getString("KicCopy20"));
                bean.setKicChk10(rs.getString("KicChk10"));
                bean.setKicChk11(rs.getString("KicChk11"));
                bean.setKicChk12(rs.getString("KicChk12"));
                bean.setKicChk13(rs.getString("KicChk13"));
                bean.setKicChk14(rs.getString("KicChk14"));
                bean.setKicChk15(rs.getString("KicChk15"));
                bean.setKicChk16(rs.getString("KicChk16"));
                bean.setKicChk17(rs.getString("KicChk17"));
                bean.setKicChk18(rs.getString("KicChk18"));
                bean.setKicChk19(rs.getString("KicChk19"));
                bean.setKicChk20(rs.getString("KicChk20"));
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return bean;
    }

    public static String[] getKicData20() {
        BranchBean bb = BranchControl.getData();
        String[] kic;

        kic = new String[]{bb.getKIC1(), bb.getKIC2(), bb.getKIC3(), bb.getKIC4(), bb.getKIC5(), bb.getKIC6(), bb.getKIC7(), bb.getKIC8(), bb.getKIC9(), bb.getKic10(), bb.getKic11(), bb.getKic12(), bb.getKic13(), bb.getKic14(), bb.getKic15(), bb.getKic16(), bb.getKic17(), bb.getKic18(), bb.getKic19(), bb.getKic20()};

        return kic;
    }

    public static String getForm(String kicNo) {
        String form = "1";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select KICCopy" + kicNo + " from branch";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                form = rs.getString(1);
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return form;
    }

}
