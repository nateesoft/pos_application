package com.softpos.main.program;

import com.softpos.main.utils.SendEmail;
import com.softpos.main.model.POSHWSetup;
import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.Value;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.CreditRec;
import com.softpos.main.model.FinalcialRec;
import com.softpos.database.util.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import com.softpos.main.utils.DateConvert;
import com.softpos.main.utils.MSG;
import com.softpos.database.util.ConfigFile;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import com.softpos.main.controller.PrintDriver;

public class SendTerminalReportAuto {
    
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    DateConvert dc = new DateConvert();
    private POSConfigSetup CONFIG;
    private POSHWSetup POSHW;
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    public String filePath = "";
    public String fileName = "";
    MySQLConnect c = new MySQLConnect();
    
    public void ProcessProc() throws FileNotFoundException, UnsupportedEncodingException {
        POSHW = POSHWSetup.Bean("001");
        FinalcialRec frec = new FinalcialRec();
        CreditRec[] CrArray;
        
        CrArray = null;
        int ArraySize = 0;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from s_invoice "
                    + "where (s_date>='" + dc.GetCurrentDate() + "') "
                    + "and (s_date<='" + dc.GetCurrentDate() + "') "
                    + "and (b_macno>='001') "
                    + "and (b_macno<='999')";
//                    + "and (b_macno<='" + txtMacNo2.getText() + "') and b_void<>'V'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            String sqlGetEntertainPay = "select sum(B_Entertain) EntertainAMT,sum(B_NetDiff) B_NetDiff from s_invoice where b_void<>'V'  "
                    + "and s_date between '" + dc.GetCurrentDate() + "' and '" + dc.GetCurrentDate() + "';";
            ResultSet rsGetEntertain = mysql.getConnection().createStatement().executeQuery(sqlGetEntertainPay);
            String sqlSumBillno = "select count(B_Refno) b_refno from s_invoice where b_entertain<>'0' and b_void<>'V' "
                    + "and s_date between '" + dc.GetCurrentDate() + "' and '" + dc.GetCurrentDate() + "';";
            ResultSet rsGetSumBillno = mysql.getConnection().createStatement().executeQuery(sqlSumBillno);
            if (rsGetEntertain.next()) {
                frec.Entertain = rsGetEntertain.getDouble("EntertainAMT");
                frec.B_NetDiff = rsGetEntertain.getDouble("B_NetDiff");
            }
            if (rsGetSumBillno.next()) {
                frec.BillEntertain = rsGetSumBillno.getDouble("b_refno");
            }
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                frec.StBill = rec.getString("b_refno");
                do {
                    frec.SpBill = rec.getString("b_refno");
                    if (!rec.getString("b_void").equals("V")) {
                        frec.Dept_Sum = frec.Dept_Sum + rec.getDouble("b_total");
                        if (rec.getDouble("b_serviceamt") != 0.0) {
                            frec.Service = frec.Service + rec.getDouble("b_serviceamt");
                            frec.ServiceCnt++;
                        }
                        if (rec.getDouble("b_crchargeamt1") != 0.0) {
                            frec.Charge = frec.Charge + rec.getDouble("b_crchargeamt1");
                            frec.ChargeCnt++;
                        }
                        if (rec.getDouble("b_memdiscamt") != 0.0) {
                            frec.Vip_Disc = frec.Vip_Disc + rec.getDouble("b_memdiscamt");
                            frec.Vip_DiscCnt++;
                        }
                        if (rec.getDouble("b_empdiscamt") != 0.0) {
                            frec.Emp_Disc = frec.Emp_Disc + rec.getDouble("b_empdiscamt");
                            frec.Emp_DiscCnt++;
                        }
                        if (rec.getDouble("b_fastdiscamt") != 0.0) {
                            frec.Fast_Disc = frec.Fast_Disc + rec.getDouble("b_fastdiscamt");
                            frec.Fast_DiscCnt++;
                        }
                        if (rec.getDouble("b_Traindiscamt") != 0.0) {
                            frec.Train_Disc = frec.Train_Disc + rec.getDouble("b_traindiscamt");
                            frec.Train_DiscCnt++;
                        }
                        if (rec.getDouble("b_subdiscamt") != 0.0) {
                            frec.Sub_Disc = frec.Sub_Disc + rec.getDouble("b_subdiscamt");
                            frec.Sub_DiscCnt++;
                        }
                        if (rec.getDouble("b_subdiscbath") != 0.0) {
                            frec.Gen_Refund = frec.Gen_Refund + rec.getDouble("b_subdiscbath");
                            frec.Gen_RefundCnt++;
                        }
                        if (rec.getDouble("b_cupondiscamt") != 0.0) {
                            frec.Cupon_Disc = frec.Cupon_Disc + rec.getDouble("b_cupondiscamt");
                            frec.Cupon_DiscCnt++;
                        }
                        if (rec.getDouble("b_prodiscamt") != 0.0) {
                            frec.Promotion = frec.Promotion + rec.getDouble("b_prodiscamt");
                            frec.PromotionCnt++;
                        }
                        if (rec.getDouble("b_spadiscamt") != 0.0) {
                            frec.Spacial = frec.Spacial + rec.getDouble("b_spadiscamt");
                            frec.SpacialCnt++;
                        }
                        if (rec.getDouble("b_itemdiscamt") != 0.0) {
                            frec.Item_Disc = frec.Item_Disc + rec.getDouble("b_itemdiscamt");
                            frec.Item_DiscCnt++;
                        }
                        frec.Net_Sale = frec.Net_Sale + (rec.getDouble("b_nettotal") + rec.getDouble("b_crchargeamt1"));
                        if (rec.getDouble("b_cash") != 0.0) {
                            frec.Cash = frec.Cash + rec.getDouble("b_cash");
                            frec.CashCnt++;
                        }
                        if (rec.getDouble("b_giftvoucher") != 0.0) {
                            frec.Gift = frec.Gift + rec.getDouble("b_giftvoucher");
                            frec.GiftCnt++;
                        }
                        if (rec.getDouble("b_earnest") != 0.0) {
                            frec.Earnest = frec.Earnest + rec.getDouble("b_earnest");
                            frec.EarnestCnt++;
                        }
                        if (rec.getDouble("b_accramt") != 0.0) {
                            frec.ArPayment = frec.ArPayment + rec.getDouble("b_accramt");
                            frec.ArPaymentCnt++;
                        }
                        if (rec.getDouble("b_cramt1") != 0.0) {
                            frec.Credit_Card = frec.Credit_Card + rec.getDouble("b_cramt1");
                            frec.Credit_CardCnt++;
                        }
                        frec.SaleVat = frec.SaleVat + rec.getDouble("b_netvat");
                        frec.SaleNonVat = frec.SaleNonVat + rec.getDouble("b_netnonvat");
                        frec.VatAmt = frec.VatAmt + rec.getDouble("b_vat");
                        frec.CntBill++;
                        if (rec.getDouble("b_food") != 0.0) {
                            frec.Food = frec.Food + rec.getDouble("b_food");
                        }
                        if (rec.getDouble("b_drink") != 0.0) {
                            frec.Drink = frec.Drink + rec.getDouble("b_drink");
                        }
                        if (rec.getDouble("b_product") != 0.0) {
                            frec.Product = frec.Product + rec.getDouble("b_product");
                        }
                        if (rec.getInt("b_cust") != 0) {
                            frec.Customer = frec.Customer + rec.getInt("b_cust");
                        }
                        if (rec.getString("b_etd").equals("E")) {
                            frec.Eat_In_Cnt++;
                            frec.Eat_In_Amt = frec.Eat_In_Amt + rec.getDouble("b_total");
                            frec.Eat_In_Cust = frec.Eat_In_Cust + rec.getInt("b_cust");
                            frec.Eat_In_Net = frec.Eat_In_Net + rec.getDouble("b_nettotal");
                        }
                        if (rec.getString("b_etd").equals("T")) {
                            frec.Take_AwayCnt++;
                            frec.Take_AwayAmt = frec.Take_AwayAmt + rec.getDouble("b_total");
                            frec.Take_AwayCust = frec.Take_AwayCust + rec.getInt("b_cust");
                            frec.Take_AwayNet = frec.Take_AwayNet + rec.getDouble("b_nettotal");
                        }
                        if (rec.getString("b_etd").equals("D")) {
                            frec.DeliveryCnt++;
                            frec.DeliveryAmt = frec.DeliveryAmt + rec.getDouble("b_total");
                            frec.DeliveryCust = frec.DeliveryCust + rec.getInt("b_cust");
                            frec.DeliveryNet = frec.DeliveryNet + rec.getDouble("b_nettotal");
                            String amt = frec.DeliveryAmt + "";
                            String cust = frec.DeliveryCust + "";
                            String net = frec.DeliveryNet + "";
                            System.out.println(amt + ":" + cust + ":" + net);
                        }
                        if (rec.getString("b_etd").equals("P")) {
                            frec.PintoCnt++;
                            frec.PintoAmt = frec.PintoAmt + rec.getDouble("b_total");
                            frec.PintoCust = frec.PintoCust + rec.getInt("b_cust");
                            frec.PintoNet = frec.PintoNet + rec.getDouble("b_nettotal");
                        }
                        if (rec.getString("b_etd").equals("W")) {
                            frec.WholeCnt++;
                            frec.WholeAmt = frec.WholeAmt + rec.getDouble("b_total");
                            frec.WholeCust = frec.WholeCust + rec.getInt("b_cust");
                            frec.WholeNet = frec.WholeNet + rec.getDouble("b_nettotal");
                        }
                    } else {
                        frec.AmtBillVoid = frec.AmtBillVoid + (rec.getDouble("b_nettotal") + rec.getDouble("b_crchargeamt1"));
                        frec.CntBillVoid++;
                        frec.CntBill++;
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
            rsGetSumBillno.close();
            rsGetEntertain.close();
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        }
        
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from s_paidio where (flage='I') and (terminal>='001') and (terminal<='999') and (s_date>='" + dc.GetCurrentDate() + "') and (s_date<='" + dc.GetCurrentDate() + "')";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    frec.Paid_InCnt++;
                    frec.Paid_In = frec.Paid_In + rec.getDouble("paidamt");
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from s_paidio where (flage='O') and (terminal>='001') and (terminal<='999') and (s_date>='" + dc.GetCurrentDate() + "') and (s_date<='" + dc.GetCurrentDate() + "')";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    frec.Paid_OutCnt++;
                    frec.Paid_Out = frec.Paid_Out + rec.getDouble("paidamt");
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from s_void where (macno>='001') and (macno<='999') and (s_date>='" + dc.GetCurrentDate() + "') and (s_date<='" + dc.GetCurrentDate() + "')";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    frec.CntVoid++;
                    frec.VoidValue = frec.VoidValue + rec.getDouble("amt");
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from s_invoice where (b_macno>='001') and (b_macno<='999') and (s_date>='" + dc.GetCurrentDate() + "') and (s_date<='" + dc.GetCurrentDate() + "') and (b_void<>'V') and (b_cramt1<>0)";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            Boolean XFound;
            Boolean First = true;
            if (rec.getRow() == 0) {
            } else {
                CrArray = new CreditRec[1];
                do {
                    XFound = false;
                    ArraySize = CrArray.length;
                    if (First) {
                        CreditRec CrRec = new CreditRec();
                        CrRec.CrCode = rec.getString("b_crcode1");
                        CrRec.CrCnt++;
                        CrRec.CrAmt = rec.getDouble("b_cramt1");
                        CrRec.CrName = PUtility.SeekCreditName(rec.getString("b_crcode1"));
                        First = false;
                        CrArray[ArraySize - 1] = CrRec;
                        
                    } else {
                        for (int i = 0; i < ArraySize; i++) {
                            if (rec.getString("b_crcode1").equals(CrArray[i].CrCode)) {
                                CrArray[i].CrCnt++;
                                CrArray[i].CrAmt = CrArray[i].CrAmt + rec.getDouble("b_cramt1");
                                XFound = true;
                            }
                        }
                        if (!XFound) {
                            CrArray = PUtility.addCrArray(CrArray);
                            
                            CreditRec CrRec = new CreditRec();
                            ArraySize = CrArray.length;
                            CrRec.CrCode = rec.getString("b_crcode1");
                            CrRec.CrCnt++;
                            CrRec.CrAmt = rec.getDouble("b_cramt1");
                            CrRec.CrName = PUtility.SeekCreditName(rec.getString("b_crcode1"));
                            CrArray[ArraySize - 1] = CrRec;
                        }
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        }
        PrintTerminalEngFormDriver(frec, CrArray, "001", "999");
    }
    
    public void PrintTerminalEngFormDriver(FinalcialRec frec, CreditRec[] CrArray, String macNo1, String macNo2) throws FileNotFoundException, UnsupportedEncodingException {
        String t = "";
        double sumCuponAmt = 0.00;
        CONFIG = POSConfigSetup.Bean();
        ArrayList<Object[]> list1 = DocAnalyse(dc.GetCurrentDate() + "", dc.GetCurrentDate() + "");
        String countE = "", countT = "", countD = "", etdE = "", etdT = "", etdD = "";
        double totalE = 0.00, totalT = 0.00, totalD = 0.00, nettotalE = 0.00, nettotalT = 0.00, nettotalD = 0.00;
        double countCCE = 0.00, countCCT = 0.00, countCCD = 0.00, countBillE = 0.00, countBillT = 0.00, countBillD = 0.00;
        double AVG_DockE = 0.00;
        double AVG_DockT = 0.00;
        double AVG_DockD = 0.00;
        double AVG_CCE = 0.00;
        double AVG_CCT = 0.00;
        double AVG_CCD = 0.00;
        if (list1 != null && list1.size() > 0) {
            countE = list1.get(0)[0].toString();
            etdE = list1.get(0)[1].toString();
            countCCE = Double.parseDouble(list1.get(0)[2].toString());
            totalE = Double.parseDouble(list1.get(0)[4].toString()) - frec.Service - frec.VatAmt;
            nettotalE = Double.parseDouble(list1.get(0)[5].toString());
            
            countT = list1.get(1)[0].toString();
            etdT = list1.get(1)[1].toString();
            countCCT = Double.parseDouble(list1.get(1)[2].toString());
            nettotalT = Double.parseDouble(list1.get(1)[4].toString());
            totalT = Double.parseDouble(list1.get(1)[5].toString());
            
            countD = list1.get(2)[0].toString();
            etdD = list1.get(2)[1].toString();
            countCCD = Double.parseDouble(list1.get(2)[2].toString());
            nettotalD = Double.parseDouble(list1.get(2)[4].toString());
            totalD = Double.parseDouble(list1.get(2)[5].toString());
            
            countBillE = Double.parseDouble(list1.get(0)[0].toString());
            countBillT = Double.parseDouble(list1.get(1)[0].toString());
            countBillD = Double.parseDouble(list1.get(2)[0].toString());
            
            AVG_DockE = nettotalE / countBillE;
            AVG_DockT = nettotalT / countBillT;
            AVG_DockD = nettotalD / countBillD;
            AVG_CCE = nettotalE / countCCE;
            AVG_CCT = nettotalT / countCCT;
            AVG_CCD = nettotalD / countCCD;
            
            if (nettotalE == 0.00 && countBillE == 0.00) {
                AVG_DockE = 0.00;
            }
            if (nettotalT == 0.00 && countBillT == 0.00) {
                AVG_DockT = 0.00;
            }
            if (nettotalD == 0.00 && countBillD == 0.00) {
                AVG_DockD = 0.00;
            }
            if (nettotalE == 0.00 && countCCE == 0) {
                AVG_CCE = 0.00;
            }
            if (nettotalT == 0.00 & countCCT == 0) {
                AVG_CCT = 0.00;
            }
            if (nettotalD == 0.00 & countCCD == 0) {
                AVG_CCD = 0.00;
            }
        } else {
            
        }
        double totalDiscount = 0.00;
        totalDiscount = frec.Vip_Disc + frec.Fast_Disc + frec.Emp_Disc
                + frec.Train_Disc + frec.Sub_Disc + frec.Gen_Refund + frec.Promotion
                + frec.Spacial + frec.Item_Disc + frec.Cupon_Disc;
        if (POSHW.getHeading1().trim().length() >= 18) {
            String[] strs = POSHW.getHeading1().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading1().trim().replace(" ", "&nbsp; ") + "_";
        }
        if (POSHW.getHeading2().length() >= 18) {
            String[] strs = POSHW.getHeading2().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
        }
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3()).trim() + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4()).trim() + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "REG.ID :" + Space + (POSHW.getTerminal()) + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "*** MTD Terminal Report ***") + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Date From :" + dc.dateGetToShow(dc.GetCurrentDate())) + " To." + Space + dc.dateGetToShow(dc.GetCurrentDate()) + "_";
//        t += "colspan=3 align=left><font face=Angsana New size=1>" + "Date To..." + DatefmtShow.format(TDate2) + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Terminal : " + Space + "001" + Space + " To Terminal : " + "999") + "_";
        Date dateP = new Date();
        t += "colspan=3 align=left><font face=Angsana New size=1>" + "Print Time :" + dc.dateGetToShow(dc.GetCurrentDate()) + Space + PublicVar._User + " Mac:" + Value.MACNO + "_";
        
        double NetSale_VatExclude = frec.Net_Sale * CONFIG.getP_Vat() / (100 + CONFIG.getP_Vat());
        double NetSale = frec.Net_Sale - NetSale_VatExclude;
        
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "FOOD" + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.Food)) + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "BEVERAGE" + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.Drink)) + "_";
        if (frec.Product > 0) {
            t += ("colspan=2 align=left><font face=Angsana New size=1>" + "PRODUCT" + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.Product)) + "_";
        }
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "TOTAL-SALES" + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.Dept_Sum)) + "_";
        //prn.print(PUtility.DataFullR("ค่าบริการ Service       ", 20) + PUtility.DataFull(IntFmt.format(frec.ServiceCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Service), 13));
        if (frec.ChargeCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Charge  Credit", 20) + PUtility.DataFull(IntFmt.format(frec.ChargeCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Charge), 13)) + "_";
        }
        if (frec.Vip_DiscCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Member", 20) + PUtility.DataFull(IntFmt.format(frec.Vip_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Vip_Disc), 13)) + "_";
        }
        if (frec.Fast_DiscCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Festival", 20) + PUtility.DataFull(IntFmt.format(frec.Fast_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Fast_Disc), 13)) + "_";
        }
        if (frec.Emp_DiscCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Employ", 20) + PUtility.DataFull(IntFmt.format(frec.Emp_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Emp_Disc), 13)) + "_";
        }
        if (frec.Train_DiscCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Staff Disc", 20) + PUtility.DataFull(IntFmt.format(frec.Train_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Train_Disc), 13)) + "_";
        }
        if (frec.Sub_DiscCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Cupon", 20) + PUtility.DataFull(IntFmt.format(frec.Sub_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Sub_Disc), 13)) + "_";
        }
        if (frec.Gen_RefundCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Bath.", 20) + PUtility.DataFull(IntFmt.format(frec.Gen_RefundCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Gen_Refund), 13)) + "_";
        }
        if (frec.PromotionCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Promotion", 20) + PUtility.DataFull(IntFmt.format(frec.PromotionCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Promotion), 13)) + "_";
        }
        if (frec.SpacialCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Special", 20) + PUtility.DataFull(IntFmt.format(frec.SpacialCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Spacial), 13)) + "_";
        }
        if (frec.Item_DiscCnt > 0) {
            t += ("colspan=3 align=center><font face=Angsana New size=1>" + PUtility.DataFullR("Discount Item", 20) + PUtility.DataFull(IntFmt.format(frec.Item_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Item_Disc), 13)) + "_";
        }
        if (frec.Cupon_DiscCnt > 0) {
//            MySQLConnect c = new MySQLConnect();
            c.open();
            try {
                String sql = "select sum(cuquan) cuquan ,sum(cuamt) cuamt "
                        + "from s_cupon "
                        + "where s_date between'" + dc.GetCurrentDate() + "' "
                        + "and '" + dc.GetCurrentDate() + "' "
                        + "and cuquan<>'0' and cuamt<>'0' and refund<>'V'";
                ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
                while (rs.next()) {
                    double cuamt = rs.getDouble("cuamt");
                    double quan = rs.getDouble("cuquan");
                    t += ("align=left><font face=Angsana New size=1>" + "Special Coupon" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(quan), 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(cuamt), 13)) + "_";
                }
                rs.close();
            } catch (Exception e) {
                c.close();
            }
        }
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "=====================================") + "_";
//        t += ("colspan=2 align=left><font face=Angsana New size=1>" + PUtility.DataFullR("Gross-Sales", 26) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Net_Sale), 13)) + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Gross-Sales" + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.Dept_Sum - totalDiscount) + "_");
        
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "=====================================") + "_";
        if (frec.Gift > 0) {
            t += ("align=left><font face=Angsana New size=1>" + PUtility.DataFullR("Gift Voucher", 20) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(frec.GiftCnt), 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Gift), 13)) + "_";
        }
        if (frec.Entertain > 0) {
            t += ("align=lfet><font face=Angsana New size=1>" + PUtility.DataFullR("Entertain", 20) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(frec.BillEntertain), 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Entertain), 13) + "_");
        }
        t += ("align=left><font face=Angsana New size=1>" + "Cash" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(frec.CashCnt), 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Cash), 13)) + "_";
        String[] credit = credit(macNo1, macNo2);
        if (!credit.equals("")) {
            String cd = credit[0];
            String am = credit[1];
            if (am == null) {
                am = "0";
            }
            double am1 = Double.parseDouble(am);
            t += ("align=left><font face=Angsana New size=1>" + "CRADIT" + "</td><td align=right><font face=Angsana New size=1>" + cd + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(am1)) + "_";
        }
        ArrayList<String[]> list = CreName(macNo1, macNo2);
        for (int i = 0; i < list.size(); i++) {
            String[] CreName = (String[]) list.get(i);
            
            String name = CreName[0];
            String num = CreName[1];
            String amt = CreName[2];
            double amt1 = Double.parseDouble(amt);
            t += ("align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFull(name, 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(("" + num), 8) + "</td><td  align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(amt1), 13)) + "_";
        }
        t += ("align=left><font face=Angsana New size=1>" + "FLOAT-IN" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(frec.Paid_InCnt), 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Paid_In), 13)) + "_";
        t += ("align=left><font face=Angsana New size=1>" + "FLOAT-OUT" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(frec.Paid_OutCnt), 6) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Paid_Out), 13)) + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t += ("align=left><font face=Angsana New size=1>" + "Bank In" + "</td><td colspan=2 align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Cash + frec.Paid_In - frec.Paid_Out), 13)) + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Service Charege" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Service), 13)) + "_";
        if (CONFIG.getP_VatType().contains("I")) {
            t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Net-Sales" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Net_Sale - NetSale_VatExclude), 19) + "_");
        }
        if (CONFIG.getP_VatType().contains("E")) {
            t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Net-Sales" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Net_Sale), 19) + "_");
//            totalE = totalE - frec.Service;
        }
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Round Total" + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.B_NetDiff) + "_");
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Vat" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.VatAmt), 13)) + "_";

//        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Net-Sales" + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Net_Sale - NetSale_VatExclude), 19)) + "_";
        //prn.print(PUtility.DataFullR("Net-Sales                   ", 26) + PUtility.DataFull(DecFmt.format(frec.Dept_Sum - frec.VatAmt), 13));
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Customer" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.Customer)) + "_";
//        t += ("align=left><font face=Angsana New size=1>" + "MGR Refund" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.CntBillVoid) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.AmtBillVoid)) + "_";
//        t += ("align=left><font face=Angsana New size=1>" + "MGR Void" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.CntVoid) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(frec.VoidValue)) + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "MGR Refund" + Space + PUtility.DataFull(IntFmt.format(frec.CntBillVoid), 6) + Space + "Doc." + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.AmtBillVoid), 13) + "_");
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "MGR Void" + Space + TAB + PUtility.DataFull(IntFmt.format(frec.CntVoid), 6) + Space + "Items." + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.VoidValue), 13) + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t += ("colspan=2 align=left><font face=Angsana New size=1>" + "Docket" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.CntBill) + " ") + "_";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select b_macno,min(b_refno),max(b_refno) from s_invoice "
                    + "where (s_date>='" + dc.GetCurrentDate() + "') and (s_date<='" + dc.GetCurrentDate() + "') "
                    + "and (b_macno>='" + "001" + "') "
                    + "and (b_macno<='" + "999" + "') "
                    + " group by b_macno order by b_macno";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    t += ("colspan=3 align=left><font face=Angsana New size=1>" + " Start Docket" + Space + rec.getString("min(b_refno)") + TAB + " To" + Space + rec.getString("max(b_refno)")) + "_";
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
        
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "=====================================") + "_";
        t += ("align=left><font face=Angsana New size=1>" + "SaleType" + "</td><td align=left><font face=Angsana New size=1>" + "Docket" + TAB + "CC" + "</td><td align=center><font face=Angsana New size=1>" + "Amount" + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "=====================================") + "_";
        t += ("align=left><font face=Angsana New size=1>" + "Dine - In" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.Eat_In_Cnt) + TAB + PUtility.DataFullSpace(IntFmt.format(frec.Eat_In_Cust), 4) + TAB + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Eat_In_Amt), 8) + "_");
        t += ("align=left><font face=Angsana New size=1>" + "Take Away" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.Take_AwayCnt) + TAB + PUtility.DataFullSpace(IntFmt.format(frec.Take_AwayCust), 4) + TAB + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.Take_AwayAmt), 8) + "_");
        t += ("align=left><font face=Angsana New size=1>" + "Delivery" + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(frec.DeliveryCnt) + TAB + PUtility.DataFullSpace(IntFmt.format(frec.DeliveryCust), 4) + TAB + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(frec.DeliveryAmt), 8) + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "=====================================") + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "Analysts") + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "_");
        t += ("align=right><font face=Angsana New size=1>" + "DineIn" + TAB + "</td><td align=center><font face=Angsana New size=1>" + "TakeAway" + "</td><td align=right><font face=Angsana New size=1>" + "Delivery" + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Gross Sales") + "_";
        t += ("width=10 align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFullSpace(DecFmt.format(frec.Dept_Sum - totalDiscount), 13) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(totalT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(totalD)) + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Net Sales") + "_";
        t += ("align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFullSpace(DecFmt.format(nettotalE), 13) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(nettotalT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(nettotalD)) + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Docket") + "_";
        t += ("align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFullSpace(IntFmt.format(countBillE), 13) + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(countBillT) + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(countBillD)) + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Customer") + "_";
        t += ("align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFullSpace(DecFmt.format(countCCE), 13) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(countCCT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(countCCD)) + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "AVG/Dock") + "_";
        t += ("align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFullSpace(DecFmt.format(AVG_DockE), 13) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_DockT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_DockD)) + "_";
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "AVG/Head") + "_";
        t += ("align=left><font face=Angsana New size=1>" + TAB + PUtility.DataFullSpace(DecFmt.format(AVG_CCE), 13) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_CCT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_CCD)) + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "------------------------------------------------------------------") + "_";
        t = changeReportLanguage(t);
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        
        for (String data1 : strs) {
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        pd.printHTMLIntoFile();
    }
    
    private ArrayList<Object[]> DocAnalyse(String date1, String date2) {
        ArrayList<Object[]> listObj = new ArrayList<>();
        String sqlSelectDocTypeE = "select count(b_refno)b_refno,"
                + "b_ondate, "
                + "b_macno, "
                + "b_etd,sum(b_cust) b_cust, "
                + "sum(b_nettotal) b_nettotal,"
                + " sum(b_vat) b_vat "
                + "from s_invoice "
                + "where s_date between '" + date1 + "' and '" + date2 + "' "
                + "and b_void<>'V'"
                + "and b_etd='E' "
                + "group by b_etd";
        String sqlSelectDocTypeT = "select count(b_refno)b_refno,"
                + "b_ondate, "
                + "b_macno, "
                + "b_etd,sum(b_cust) b_cust, "
                + "sum(b_nettotal) b_nettotal,"
                + " sum(b_vat) b_vat "
                + "from s_invoice "
                + "where s_date between '" + date1 + "' and '" + date2 + "' "
                + "and b_void<>'V'"
                + "and b_etd='T' "
                + "group by b_etd";
        String sqlSelectDocTypeD = "select count(b_refno)b_refno,"
                + "b_ondate, "
                + "b_macno, "
                + "b_etd,sum(b_cust) b_cust, "
                + "sum(b_nettotal) b_nettotal,"
                + " sum(b_vat) b_vat "
                + "from s_invoice "
                + "where s_date between '" + date1 + "' and '" + date2 + "' "
                + "and b_void<>'V'"
                + "and b_etd='D' "
                + "group by b_etd";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelectDocTypeE);
            int countb_refnoE = 0;
            int countb_refnoT = 0;
            int countb_refnoD = 0;
            String b_etd = "";
            String b_cust = "";
            if (rs.next()) {
                int countb_refno = rs.getInt("b_refno");
                b_etd = rs.getString("b_etd");
                b_cust = rs.getString("b_cust");
                double b_nettotal = rs.getDouble("b_nettotal");
                double b_vat = rs.getDouble("b_vat");
                double nettotal = b_nettotal - b_vat;
                System.out.println(b_etd + " " + b_cust + " " + b_nettotal + " " + b_vat);
                listObj.add(new Object[]{countb_refno, b_etd, b_cust, b_vat, nettotal, b_nettotal,});
            } else {
                listObj.add(new Object[]{"0", "E", "0", 0.00, 0.00, 0.00});
            }
            rs.close();
            stmt.close();
            
            stmt = mysql.getConnection().createStatement();
            ResultSet rs4 = stmt.executeQuery(sqlSelectDocTypeT);
            if (rs4.next()) {
                int countb_refno = rs4.getInt("b_refno");
                b_etd = rs4.getString("b_etd");
                b_cust = rs4.getString("b_cust");
                double b_nettotal = rs4.getDouble("b_nettotal");
                double b_vat = rs4.getDouble("b_vat");
                double nettotal = b_nettotal - b_vat;
                System.out.println(b_etd + " " + b_cust + " " + b_nettotal + " " + b_vat);
                listObj.add(new Object[]{countb_refno, b_etd, b_cust, b_vat, nettotal, b_nettotal,});
            } else {
                listObj.add(new Object[]{"0", "T", "0", 0.00, 0.00, 0.00});
            }
            rs4.close();
            stmt.close();
            ResultSet rsD = mysql.getConnection().createStatement().executeQuery(sqlSelectDocTypeD);
            if (rsD.next()) {
                int countb_refno = rsD.getInt("b_refno");
                b_etd = rsD.getString("b_etd");
                b_cust = rsD.getString("b_cust");
                double b_nettotal = rsD.getDouble("b_nettotal");
                double b_vat = rsD.getDouble("b_vat");
                double nettotal = b_nettotal - b_vat;
                System.out.println(b_etd + " " + b_cust + " " + b_nettotal + " " + b_vat);
                listObj.add(new Object[]{countb_refno, b_etd, b_cust, b_vat, nettotal, b_nettotal,});
            } else {
                listObj.add(new Object[]{"0", "D", "0", 0.00, 0.00, 0.00});
            }
            rsD.close();
            
            String sqlCountBillnoE = "select count(b_refno) cb_refnoE "
                    + "from s_invoice "
                    + "where s_date between '" + date1 + "' and '" + date2 + "' "
                    + "and b_etd='E' and b_void<>'V'";
            stmt = mysql.getConnection().createStatement();
            ResultSet rs1 = stmt.executeQuery(sqlCountBillnoE);
            if (rs1.next()) {
                countb_refnoE = rs1.getInt("cb_refnoE");
            } else {
                countb_refnoE = 0;
            }
            rs1.close();
            stmt.close();
            
            listObj.add(new Object[]{countb_refnoE});
            String sqlCountBillnoT = "select count(b_refno) cb_refnoT "
                    + "from s_invoice "
                    + "where s_date between '" + date1 + "' and '" + date2 + "' "
                    + "and b_etd='T' and b_void<>'V'";
            stmt = mysql.getConnection().createStatement();
            ResultSet rs2 = stmt.executeQuery(sqlCountBillnoT);
            if (rs2.next()) {
                countb_refnoT = rs2.getInt("cb_refnoT");
            } else {
                countb_refnoT = 0;
            }
            rs2.close();
            stmt.close();
            listObj.add(new Object[]{countb_refnoT});
            String sqlCountBillnoD = "select count(b_refno) cb_refnoD "
                    + "from s_invoice "
                    + "where s_date between '" + date1 + "' and '" + date2 + "' "
                    + "and b_etd='D' and b_void<>'V'";
            stmt = mysql.getConnection().createStatement();
            ResultSet rs6 = stmt.executeQuery(sqlCountBillnoD);
            if (rs6.next()) {
                countb_refnoD = rs6.getInt("cb_refnoD");
            } else {
                countb_refnoD = 0;
            }
            rs6.close();
            stmt.close();
            listObj.add(new Object[]{countb_refnoD});
        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
            
        }
        
        mysql.close();
        
        return listObj;
    }
    
    private ArrayList<String[]> CreName(String macNo1, String macNo2) {
        ArrayList<String[]> list = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select b_macno, B_CrCode1,B_cardNo1, sum(B_CrAmt1) B_CrAmt1 "
                    + "from s_invoice "
                    + "where b_macno='" + macNo1 + "' and '" + macNo2 + "' "
                    + "and s_date between '" + dc.GetCurrentDate() + "' and '" + dc.GetCurrentDate() + "' "
                    + "and b_crcode1<>'' "
                    + "and b_cramt1 <>'0' "
                    + "and b_void<>'V' "
                    + "group by b_crcode1 order by b_crcode1";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            while (rec.next()) {
                String[] CreName = new String[]{"", "", ""};
                String name = rec.getString("B_CrCode1");
                String code = rec.getString("B_CardNo1");
                String amount = rec.getString("B_CrAmt1");
                CreName[0] = name;
                CreName[1] = "";
//                CreName[1] = code;
                CreName[2] = amount;
                list.add(CreName);
            }
            
            rec.close();
            stmt.close();
        } catch (SQLException ex) {
//            MSG.ERR(ex.getMessage());
            
        } finally {
            mysql.close();
        }
        
        return list;
    }
    
    private String[] credit(String macNo1, String macNo2) {
        String[] credit = new String[]{"", ""};
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select count(b_crcode1) b_crcode1,b_crcode1,sum(B_CrAmt1) "
                    + "from S_invoice "
                    + "where b_macno between'" + macNo1 + "' and '" + macNo2 + "' "
                    + "and s_date between'" + dc.GetCurrentDate() + "' and '" + dc.GetCurrentDate() + "' "
                    + "and B_CardNo1 <>'' "
                    + "and b_void<>'V' group by b_crcode1 order by b_crcode1";
//            String SqlQuery = "select COUNT(B_CardNo1),sum(B_CrAmt1) "
//                    + "from S_invoice "
//                    + "where b_macno between'" + macNo1 + "' and '" + macNo2 + "' "
//                    + "and s_date between'" + Datefmt.format(TDate1) + "' and '" + Datefmt.format(TDate2) + "' "
//                    + "and B_CardNo1 <>'' "
//                    + "and b_void<>'V' order by b_refno";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            String CardNo = "";
            String Amt = "";
            int dCardNo = 0;
            double dAmt = 0.00;
            while (rec.next()) {
                CardNo = rec.getString("b_crcode1");
                Amt = rec.getString("sum(B_CrAmt1)");
                dCardNo += Integer.parseInt(CardNo);
                dAmt += Double.parseDouble(Amt);
            }
            credit[0] = dCardNo + "";
            credit[1] = dAmt + "";
            rec.close();
            stmt.close();
        } catch (SQLException ex) {
//            MSG.ERR(ex.getMessage());
            
        } finally {
            mysql.close();
        }
        
        return credit;
    }
    
    private String[] specialCupon(String macNo1, String macNo2) {
        String[] cupon = new String[]{"", ""};
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select sum(cuquan) cuquan, "
                    + "sum(cuamt) cuamt "
                    + "from s_cupon "
                    + "where cuquan<>'0' "
                    + "and cuant<>'0' "
                    + "and macno between'" + macNo1 + "' and '" + macNo2 + "';";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            if (rec.next()) {
                cupon[0] = rec.getString("cuquan");
                cupon[1] = rec.getString("cuamt");
            }
            rec.close();
            stmt.close();
        } catch (SQLException ex) {
//            MSG.ERR(ex.getMessage());
            
        } finally {
            mysql.close();
        }
        
        return cupon;
    }
    
    public String changeReportLanguage(String text) {
        String t = text;
        String billLanguage = ConfigFile.getProperties("languagebillSubtotal");
        if (billLanguage.equals("th")) {
            t = t.replace("CASH IN DRAWER", "เงินสดในลิ้นชัก");
            t = t.replace("FOOD", "อาหาร");
            t = t.replace("BEVERAGE", "เครื่องดื่ม");
            t = t.replace("PRODUCT", "สินค้า");
            t = t.replace("TOTAL-SALES", "รวมยอดขายตามป้าย");
            t = t.replace("Discount Bath", "ส่วนลด บาท");
            t = t.replace("Discount Item", "ส่วนลด รายการ");
            t = t.replace("Gross-Sales", "ยอดขายหลังหักส่วนลด");
            t = t.replace("CASH", "เงินสด");
            t = t.replace("CRADIT", "เครดิต");
            t = t.replace("Bank In", "ยอดเงินสดนำส่ง");
            t = t.replace("Service Charge", "ค่าบริการ");
            t = t.replace("Net-Sale", "ยอดขายรวมทั้งสิ้น");
            t = t.replace("Round Total", "ยอดปัดเศษ");
            t = t.replace("Vat", "ภาษีมูลค่าเพิ่ม");
            t = t.replace("Customer", "จำนวนลูกค้า");
            t = t.replace("MGR Refund", "ยกเลิกใบเสร็จ").replace("Doc.", "ใบ");
            t = t.replace("MGR Void", "ยกเลิกรายการ").replace("Items.", "รายการ");
            t = t.replace("Total Docket", "จำนวนใบเสร็จ");
            t = t.replace("Start Docket", "ใบเสร็จเริ่มต้น");
            t = t.replace("To..", "ถึง..");
            t = t.replace("SaleType", "ประเภทขาย");
            t = t.replace("Docket", "ใบเสร็จ");
            t = t.replace("CC", "ลูกค้า");
            t = t.replace("Amount", "มูลค่า");
            t = t.replace("DineIn", "ทานในร้าน");
            t = t.replace("Dine-In", "ทานในร้าน");
            t = t.replace("Dine - In", "ทานในร้าน");
            t = t.replace("TakeAway", "ห่อกลับ");
            t = t.replace("Take Away", "ห่อกลับ");
            t = t.replace("Delivery", "ส่ง");
            t = t.replace("Analysts", "วิเคราะห์");
            t = t.replace("Gross Sales", "ยอดขายหลังหักส่วนลด");
            t = t.replace("Net Sales", "ยอดขายรวมภาษีมูลค่าเพิ่ม");
            t = t.replace("Customer", "จำนวนลูกค้า");
            t = t.replace("AVG/Dock", "ยอเเฉลี่ยต่อใบเสร็จ");
            t = t.replace("AVG/Head", "ยอเเฉลี่ยต่อคน");
            t = t.replace("Cash", "เงินสด");
            t = t.replace("FLOAT IN", "เงินสำรองทอนเข้า");
            t = t.replace("FLOAT OUT", "เงินสำรองทอนออก");
            t = t.replace("FLOAT-IN", "เงินสำรองทอนเข้า");
            t = t.replace("FLOAT-OUT", "เงินสำรองทอนออก");
            t = t.replace("Service Charege", "ค่าบริการ");
            
        }
        return t;
    }
    
    public void beforeProcess() {
        try {
            DateConvert dc1 = new DateConvert();
            String timeNow = dc1.GetCurrentTime();
//            MySQLConnect c = new MySQLConnect();
            c.open();
            String sqlGetEmailFromTranconfig = "select TranEmailAuto,TimeSend1,EmailAddress from tranconfig; ";
            ResultSet rsConfig = c.getConnection().createStatement().executeQuery(sqlGetEmailFromTranconfig);
            if (rsConfig.next() && !rsConfig.wasNull()) {
                String TranEmailAuto = rsConfig.getString("TranEmailAuto");
                String TimeSend = rsConfig.getString("TimeSend1");
//                if (TranEmailAuto.equals("Y") && timeNow.equals(TimeSend)) {
                    Thread.sleep(3600 * 3);
                    try {
                        c.open();
                        String sqlAddress = "select address from company";
                        ResultSet rsAddress = c.getConnection().createStatement().executeQuery(sqlAddress);
                        if (rsAddress.next() && !rsAddress.wasNull()) {
                            String data[] = rsAddress.getString("Address").split("/");
                            String username = data[0];
                            String password = data[1];
                            String to[] = rsConfig.getString("EmailAddress").split(",");
                            ProcessProc();
                            Thread.sleep(3600);
                            
                            String host = "smtp.gmail.com";
                            String port = "587";

                            // message info
                            String mailTo = to[0];
                            String subject = "MTD_REPORT";
                            String message = "MTD_REPORT_AutoSend";

                            // attachments
                            String[] attachFiles = new String[1];
                            
                            attachFiles[0] = PublicVar.filePath;
                            System.out.println(PublicVar.filePath);
                            SendEmail.sendEmailWithAttachments(host, port, username, password, mailTo, subject, message, attachFiles);
                            Thread.sleep(3600 * 5);
                            System.exit(0);
                        }
                        rsAddress.close();
                    } catch (Exception e) {
                        MSG.ERR(e.toString());
                    }
                    
//                } else {
//                    System.out.println("Holder Time : Next Round = :" + TimeSend);
//                }
            }
            rsConfig.close();
            c.close();
        } catch (Exception e) {
            MSG.ERR(e.toString());
        }
    }

}
