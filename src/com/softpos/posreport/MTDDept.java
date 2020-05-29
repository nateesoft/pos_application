package com.softpos.posreport;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import database.MySQLConnect;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.softpos.main.program.Jdi_MTDdepReport;
import com.softpos.main.model.POSHWSetup;
import com.softpos.main.program.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PluRec;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.Value;
import java.util.ArrayList;
import printReport.PrintDriver;
import soft.virtual.KeyBoardDialog;
import util.DateChooseDialog;
import util.MSG;

public class MTDDept extends javax.swing.JDialog {

    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    Date TDate1 = new Date();
    Date TDate2 = new Date();
    PPrint prn = new PPrint();
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    private POSHWSetup POSHW;
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;

    /**
     * Creates new form MTDDept
     */
    public MTDDept(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate1.setText(DatefmtShow.format(date));
        txtDate2.setText(DatefmtShow.format(date));
        txtMacNo1.setText("0000");
        txtMacNo2.setText("9999");
        InitScreen();

        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    public void InitScreen() {

        txtDate1.requestFocus();
    }

    public void inputfrombnt(String str) {
        if (txtMacNo1.hasFocus()) {
            String tempstr = "";
            tempstr = txtMacNo1.getText();
            tempstr = tempstr + str;
            txtMacNo1.setText(tempstr);
        }
        if (txtMacNo2.hasFocus()) {
            String tempstr = "";
            tempstr = txtMacNo2.getText();
            tempstr = tempstr + str;
            txtMacNo2.setText(tempstr);
        }
        if (txtDate1.hasFocus()) {
            String tempstr = "";
            tempstr = txtDate1.getText();
            tempstr = tempstr + str;
            txtDate1.setText(tempstr);
        }
        if (txtDate2.hasFocus()) {
            String tempstr = "";
            tempstr = txtDate2.getText();
            tempstr = tempstr + str;
            txtDate2.setText(tempstr);
        }

    }

    public void bntExitClick() {
        this.dispose();
    }

    public void Action() {
        String TDate = txtDate1.getText();

        try {
            TDate1 = DatefmtShow.parse(txtDate1.getText());
            TDate2 = DatefmtShow.parse(txtDate2.getText());
            if (!PUtility.ChkValidDate(TDate1)) {
                txtDate1.requestFocus();
            }
            if (!PUtility.ChkValidDate(TDate2)) {
                txtDate2.requestFocus();
            }
            ProcessProc();
//            InitScreen();
        } catch (ParseException ex) {
            MSG.ERR(this, "กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
        }
    }

    public void bntOKClick() {
        Action();
    }

    public void ProcessProc() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        String TempGroup = "";
        int ArraySize = 0;
        Boolean Found = false;
        PluRec[] GArray;
        GArray = new PluRec[1];
        ArraySize = 0;

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select s_date,s_dept,sum(e_qty),sum(e_amt),sum(t_qty),sum(t_amt),sum(d_qty),sum(d_amt),sum(p_qty),sum(p_amt),sum(w_qty),sum(w_amt),sum(s_qty),sum(s_amt) from s_sale "
                    + "where (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') and (s_dept>='" + txtMacNo1.getText() + "') and (s_dept<='" + txtMacNo2.getText() + "') group by s_dept order by s_dept";

            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            TempGroup = "";
            Double SumEQty = 0.0;
            Double SumEAmt = 0.0;
            Double SumTQty = 0.0;
            Double SumTAmt = 0.0;
            Double SumDQty = 0.0;
            Double SumDAmt = 0.0;
            Double SumPQty = 0.0;
            Double SumPAmt = 0.0;
            Double SumWQty = 0.0;
            Double SumWAmt = 0.0;
            Double SumSQty = 0.0;
            Double SumSAmt = 0.0;
            if (rec.getRow() == 0) {
            } else {
                TempGroup = rec.getString("s_dept");
                do {
                    Found = true;
                    if (!TempGroup.equals(rec.getString("s_dept"))) {
                        PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = "";
                        GroupRec.Cashier2 = "";
                        GroupRec.Group1 = txtMacNo1.getText();
                        GroupRec.Group2 = txtMacNo2.getText();
                        GroupRec.Plu1 = "";
                        GroupRec.Plu2 = "";
                        GroupRec.GroupCode = TempGroup;
                        GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                        GroupRec.PCode = "";
                        GroupRec.PName = "";

                        GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                        GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;

                        GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                        GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;

                        GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                        GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;

                        GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                        GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;

                        GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                        GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                        GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                        GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                        if (ArraySize == 0) {
                            GArray[ArraySize] = GroupRec;
                            ArraySize = GArray.length;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                        TempGroup = rec.getString("s_dept");
                        SumEQty = 0.0;
                        SumEAmt = 0.0;
                        SumTQty = 0.0;
                        SumTAmt = 0.0;
                        SumDQty = 0.0;
                        SumDAmt = 0.0;
                        SumPQty = 0.0;
                        SumPAmt = 0.0;
                        SumWQty = 0.0;
                        SumWAmt = 0.0;
                        SumSQty = 0.0;
                        SumSAmt = 0.0;
                    }

                    SumEQty = SumEQty + rec.getDouble("sum(e_qty)");
                    SumEAmt = SumEAmt + rec.getDouble("sum(e_amt)");

                    SumTQty = SumTQty + rec.getDouble("sum(t_qty)");
                    SumTAmt = SumTAmt + rec.getDouble("sum(t_amt)");

                    SumDQty = SumDQty + rec.getDouble("sum(d_qty)");
                    SumDAmt = SumDAmt + rec.getDouble("sum(d_amt)");

                    SumPQty = SumPQty + rec.getDouble("sum(p_qty)");
                    SumPAmt = SumPAmt + rec.getDouble("sum(p_amt)");

                    SumWQty = SumWQty + rec.getDouble("sum(w_qty)");
                    SumWAmt = SumWAmt + rec.getDouble("sum(w_amt)");

                    SumSQty = SumSQty + rec.getDouble("sum(s_qty)");
                    SumSAmt = SumSAmt + rec.getDouble("sum(s_amt)");
                } while (rec.next());
                if (SumSQty > 0) {
                    PluRec GroupRec = new PluRec();
                    GroupRec.MacNo1 = MacNo1;
                    GroupRec.MacNo2 = MacNo2;
                    GroupRec.Cashier1 = "";
                    GroupRec.Cashier2 = "";
                    GroupRec.Group1 = txtMacNo1.getText();
                    GroupRec.Group2 = txtMacNo2.getText();
                    GroupRec.Plu1 = "";
                    GroupRec.Plu2 = "";
                    GroupRec.GroupCode = TempGroup;
                    GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                    GroupRec.PCode = "";
                    GroupRec.PName = "";

                    GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                    GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;

                    GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                    GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;

                    GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                    GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;

                    GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                    GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;

                    GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                    GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                    GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                    GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                    if (ArraySize == 0) {
                        GArray[ArraySize] = GroupRec;
                        ArraySize = GArray.length;
                    } else {
                        GArray = PUtility.addPluArray(GArray);
                        ArraySize = GArray.length;
                        GArray[ArraySize - 1] = GroupRec;
                    }

                }
            }

            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        } finally {
            mysql.close();
        }

        PrintGroup(GArray, Found);
//        InitScreen();
    }

    public void PrintGroup(PluRec[] GArray, Boolean Found) {
        Double SumEQty = 0.0;
        Double SumEAmt = 0.0;
        Double SumTQty = 0.0;
        Double SumTAmt = 0.0;
        Double SumDQty = 0.0;
        Double SumDAmt = 0.0;
        Double SumPQty = 0.0;
        Double SumPAmt = 0.0;
        Double SumWQty = 0.0;
        Double SumWAmt = 0.0;
        Double SumSQty = 0.0;
        Double SumSAmt = 0.0;
        int ArraySize = GArray.length;
        if (Value.printdriver) {
            PrintGroupDriver(GArray, Found);
        } else {
            if (!Value.getComPort().equals("NONE")) {
                if (prn.OpenPrint(Value.getComPort())) {
                    prn.InitPrinter();
                    prn.print(POSHW.getHeading1());
                    prn.print(POSHW.getHeading2());
                    prn.print(POSHW.getHeading3());
                    prn.print(POSHW.getHeading4());
                    prn.print("REG ID :" + Value.MACNO);
                    prn.print("         รายงานการขายตามกลุ่มสินค้า");
                    prn.print("        (MTD Department Report)");
                    prn.print("ช่วงวันที่ : " + DatefmtShow.format(TDate1) + "..." + DatefmtShow.format(TDate2));
                    prn.print("รหัสกลุ่มสินค้า (Dept/Group) " + txtMacNo1.getText() + "..." + txtMacNo2.getText());
                    prn.print(" ");
                    Date dateP = new Date();
                    prn.print(DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    prn.print("----------------------------------------");
                    prn.print("รายละเอียด");
                    prn.print("    .....EAT IN.....   ...TAKE AWAY.....");
                    prn.print("    ....DELIVERY....   .....PINTO.......");
                    prn.print("    ...WHOLE SALE...   .....TOTAL.......");
                    prn.print("----------------------------------------");
                    if (Found) {
                        for (int i = 0; i < ArraySize; i++) {

                            prn.print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                            prn.print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 13) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 13));
                            prn.print(PUtility.DataFull(IntFmt.format(GArray[i].D_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].D_Amt), 13) + PUtility.DataFull(IntFmt.format(GArray[i].P_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].P_Amt), 13));
                            prn.print(PUtility.DataFull(IntFmt.format(GArray[i].W_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].W_Amt), 13) + PUtility.DataFull(IntFmt.format(GArray[i].S_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].S_Amt), 13));

                            SumEQty = SumEQty + GArray[i].E_Qty;
                            SumEAmt = SumEAmt + GArray[i].E_Amt;
                            SumTQty = SumTQty + GArray[i].T_Qty;
                            SumTAmt = SumTAmt + GArray[i].T_Amt;
                            SumDQty = SumDQty + GArray[i].D_Qty;
                            SumDAmt = SumDAmt + GArray[i].D_Amt;
                            SumPQty = SumPQty + GArray[i].P_Qty;
                            SumPAmt = SumPAmt + GArray[i].P_Amt;
                            SumWQty = SumWQty + GArray[i].W_Qty;
                            SumWAmt = SumWAmt + GArray[i].W_Amt;
                            SumSQty = SumSQty + GArray[i].S_Qty;
                            SumSAmt = SumSAmt + GArray[i].S_Amt;
                        }
                    }

                    prn.print("----------------------------------------");
                    prn.print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 13) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 13));
                    prn.print(PUtility.DataFull(IntFmt.format(SumDQty), 6) + PUtility.DataFull(DecFmt.format(SumDAmt), 13) + PUtility.DataFull(IntFmt.format(SumPQty), 6) + PUtility.DataFull(DecFmt.format(SumPAmt), 13));
                    prn.print(PUtility.DataFull(IntFmt.format(SumWQty), 6) + PUtility.DataFull(DecFmt.format(SumWAmt), 13) + PUtility.DataFull(IntFmt.format(SumSQty), 6) + PUtility.DataFull(DecFmt.format(SumSAmt), 13));

                    prn.print("----------------------------------------");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");

                    prn.CutPaper();
                    prn.closePrint();
                }
            }
        }
    }

    public void PrintGroupDriver(PluRec[] GArray, Boolean Found) {
        Double SumEQty = 0.0;
        Double SumEAmt = 0.0;
        Double SumTQty = 0.0;
        Double SumTAmt = 0.0;
        Double SumDQty = 0.0;
        Double SumDAmt = 0.0;
        Double SumPQty = 0.0;
        Double SumPAmt = 0.0;
        Double SumWQty = 0.0;
        Double SumWAmt = 0.0;
        Double SumSQty = 0.0;
        Double SumSAmt = 0.0;
        int ArraySize = GArray.length;
        String t = "";
        if (POSHW.getHeading1().trim().length() >= 18) {
            String[] strs = POSHW.getHeading1().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading1().trim().replace(" ", "&nbsp; ") + "_";
        }
        if (POSHW.getHeading2().trim().length() >= 18) {
            String[] strs = POSHW.getHeading2().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
        }
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "REG.ID :" + POSHW.getTerminal() + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("รายงานการขายตามกลุ่มสินค้า") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("(MTD Department Report)") + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + ("ช่วงวันที่ : " + DatefmtShow.format(TDate1) + "..." + DatefmtShow.format(TDate2)) + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + ("รหัสกลุ่มสินค้า (Dept/Group) " + txtMacNo1.getText() + "..." + txtMacNo2.getText()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "_";
        Date dateP = new Date();
        t += "colspan=3 align=left><font face=Angsana New size=1>" + (DatefmtThai.format(dateP) + Space + "Cashier:" + PublicVar._User + Space + "Mac:" + Space + Value.MACNO) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("รายละเอียด") + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + (TAB + ".....EAT IN....." + TAB + TAB + TAB + "...TAKE AWAY.....") + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + (TAB + "....DELIVERY...." + TAB + Space + Space + TAB + ".....PINTO.......") + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + (TAB + "...WHOLE SALE..." + TAB + TAB + ".....TOTAL.......") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (TAB + "-----------------------------------------------------") + "_";
        if (Found) {
            for (int i = 0; i < ArraySize; i++) {

                t += "align=left><font face=Angsana New size=1>" + (PUtility.DataFullR(GArray[i].GroupCode, 4) + Space + Space + GArray[i].GroupName + "_");
                t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + (PUtility.DataFullSpace(IntFmt.format(GArray[i].E_Qty), 3) + PUtility.DataFullSpace(DecFmt.format(GArray[i].E_Amt), 10) + "</td><td align=left><font face=Angsana New size=1>" + PUtility.DataFullSpace(IntFmt.format(GArray[i].T_Qty), 3) + PUtility.DataFullSpace(DecFmt.format(GArray[i].T_Amt), 10) + "_");
                t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + (PUtility.DataFullSpace(IntFmt.format(GArray[i].D_Qty), 3) + PUtility.DataFullSpace(DecFmt.format(GArray[i].D_Amt), 10) + "</td><td align=left><font face=Angsana New size=1>" + PUtility.DataFullSpace(IntFmt.format(GArray[i].P_Qty), 3) + PUtility.DataFullSpace(DecFmt.format(GArray[i].P_Amt), 10) + "_");
                t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + (PUtility.DataFullSpace(IntFmt.format(GArray[i].W_Qty), 3) + PUtility.DataFullSpace(DecFmt.format(GArray[i].W_Amt), 10) + "</td><td align=left><font face=Angsana New size=1>" + PUtility.DataFullSpace(IntFmt.format(GArray[i].S_Qty), 3) + PUtility.DataFullSpace(DecFmt.format(GArray[i].S_Amt), 10) + "_");

//                t += "align=right><font face=Angsana New size=1>" + (PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 3) + TAB + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 10) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 3) + TAB + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 10)) + "_";
//                t += "align=right><font face=Angsana New size=1>" + (PUtility.DataFull(IntFmt.format(GArray[i].D_Qty), 3) + TAB + PUtility.DataFull(DecFmt.format(GArray[i].D_Amt), 10) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(GArray[i].P_Qty), 3) + TAB + PUtility.DataFull(DecFmt.format(GArray[i].P_Amt), 10)) + "_";
//                t += "align=right><font face=Angsana New size=1>" + (PUtility.DataFull(IntFmt.format(GArray[i].W_Qty), 3) + TAB + PUtility.DataFull(DecFmt.format(GArray[i].W_Amt), 10) + "</td><td align=right><font face=Angsana New size=1>" + PUtility.DataFull(IntFmt.format(GArray[i].S_Qty), 3) + TAB + PUtility.DataFull(DecFmt.format(GArray[i].S_Amt), 10)) + "_";
                SumEQty = SumEQty + GArray[i].E_Qty;
                SumEAmt = SumEAmt + GArray[i].E_Amt;
                SumTQty = SumTQty + GArray[i].T_Qty;
                SumTAmt = SumTAmt + GArray[i].T_Amt;
                SumDQty = SumDQty + GArray[i].D_Qty;
                SumDAmt = SumDAmt + GArray[i].D_Amt;
                SumPQty = SumPQty + GArray[i].P_Qty;
                SumPAmt = SumPAmt + GArray[i].P_Amt;
                SumWQty = SumWQty + GArray[i].W_Qty;
                SumWAmt = SumWAmt + GArray[i].W_Amt;
                SumSQty = SumSQty + GArray[i].S_Qty;
                SumSAmt = SumSAmt + GArray[i].S_Amt;
            }
        }

        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------") + "_";
        t += "align=left><font face=Angsana New size=1>" + (PUtility.DataFullSpace(IntFmt.format(SumEQty), 6) + PUtility.DataFullSpace(DecFmt.format(SumEAmt), 10) + "</td><td colspan=2 align=left><font face=Angsana New size=1>" + PUtility.DataFullSpace(IntFmt.format(SumTQty), 6) + PUtility.DataFullSpace(DecFmt.format(SumTAmt), 10) + "_");
        t += "align=left><font face=Angsana New size=1>" + (PUtility.DataFullSpace(IntFmt.format(SumDQty), 6) + PUtility.DataFullSpace(DecFmt.format(SumDAmt), 10) + "</td><td colspan=2 align=left><font face=Angsana New size=1>" + PUtility.DataFullSpace(IntFmt.format(SumPQty), 6) + PUtility.DataFullSpace(DecFmt.format(SumPAmt), 10) + "_");
        t += "align=left><font face=Angsana New size=1>" + (PUtility.DataFullSpace(IntFmt.format(SumWQty), 6) + PUtility.DataFullSpace(DecFmt.format(SumWAmt), 10) + "</td><td colspan=2 align=left><font face=Angsana New size=1>" + PUtility.DataFullSpace(IntFmt.format(SumSQty), 6) + PUtility.DataFullSpace(DecFmt.format(SumSAmt), 10) + "_");
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------") + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + ("_");
        ArrayList<Object[]> list1 = DocAnalyse(Datefmt.format(dateP) + "", Datefmt.format(dateP) + "");
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
            totalE = Double.parseDouble(list1.get(0)[4].toString());
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
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "Analysts" + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>_");
        t += ("colspan=3 align=right><font face=Angsana New size=1>" + "DineIn" + TAB + TAB + "TakeAway" + TAB + TAB + "Delivery" + "_");
//            t += ("colspan=3 align=center><font face=Angsana New size=1>_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Gross Sales" + "_");
        t += ("align=right><font face=Angsana New size=1>" + DecFmt.format(totalE) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(totalT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(totalD) + "_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Net Sales" + "_");
        t += ("align=right><font face=Angsana New size=1>" + DecFmt.format(nettotalE) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(nettotalT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(nettotalD) + "_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Docket" + "_");
        t += ("align=right><font face=Angsana New size=1>" + IntFmt.format(countBillE) + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(countBillT) + "</td><td align=right><font face=Angsana New size=1>" + IntFmt.format(countBillD) + "_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "Customer" + "_");
        t += ("align=right><font face=Angsana New size=1>" + DecFmt.format(countCCE) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(countCCT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(countCCD) + "_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "AVG/Dock" + "_");
        t += ("align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_DockE) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_DockT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_DockD) + "_");
        t += ("colspan=3 align=left><font face=Angsana New size=1>" + "AVG/Head" + "_");
        t += ("align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_CCE) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_CCT) + "</td><td align=right><font face=Angsana New size=1>" + DecFmt.format(AVG_CCD) + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>" + "-------------------------------------------------" + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>_");

        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");

        for (String data1 : strs) {
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        pd.printHTML();

    }

    public void bntViewClick() {
        String TDate = txtDate1.getText();

        try {
            TDate1 = DatefmtShow.parse(txtDate1.getText());
            TDate2 = DatefmtShow.parse(txtDate2.getText());
            if (!PUtility.ChkValidDate(TDate1)) {
                txtDate1.requestFocus();
            }
            if (!PUtility.ChkValidDate(TDate2)) {
                txtDate2.requestFocus();
            }
            ProcessProc2();
            InitScreen();
        } catch (ParseException ex) {
            MSG.ERR(this, "กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
            Logger.getLogger(MTDTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ProcessProc2() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        String TempGroup = "";
        int ArraySize = 0;
        Boolean Found = false;
        PluRec[] GArray;
        GArray = new PluRec[1];
        ArraySize = 0;

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select s_date,s_dept,sum(e_qty),sum(e_amt),sum(t_qty),sum(t_amt),sum(d_qty),sum(d_amt),sum(p_qty),sum(p_amt),sum(w_qty),sum(w_amt),sum(s_qty),sum(s_amt) from s_sale "
                    + "where (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') and (s_dept>='" + txtMacNo1.getText() + "') and (s_dept<='" + txtMacNo2.getText() + "') group by s_dept order by s_dept";

            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            TempGroup = "";
            Double SumEQty = 0.0;
            Double SumEAmt = 0.0;
            Double SumTQty = 0.0;
            Double SumTAmt = 0.0;
            Double SumDQty = 0.0;
            Double SumDAmt = 0.0;
            Double SumPQty = 0.0;
            Double SumPAmt = 0.0;
            Double SumWQty = 0.0;
            Double SumWAmt = 0.0;
            Double SumSQty = 0.0;
            Double SumSAmt = 0.0;
            if (rec.getRow() == 0) {
            } else {
                TempGroup = rec.getString("s_dept");
                do {
                    Found = true;
                    if (!TempGroup.equals(rec.getString("s_dept"))) {
                        PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = "";
                        GroupRec.Cashier2 = "";
                        GroupRec.Group1 = txtMacNo1.getText();
                        GroupRec.Group2 = txtMacNo2.getText();
                        GroupRec.Plu1 = "";
                        GroupRec.Plu2 = "";
                        GroupRec.GroupCode = TempGroup;
                        GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                        GroupRec.PCode = "";
                        GroupRec.PName = "";

                        GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                        GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;

                        GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                        GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;

                        GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                        GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;

                        GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                        GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;

                        GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                        GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                        GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                        GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                        if (ArraySize == 0) {
                            GArray[ArraySize] = GroupRec;
                            ArraySize = GArray.length;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                        TempGroup = rec.getString("s_dept");
                        SumEQty = 0.0;
                        SumEAmt = 0.0;
                        SumTQty = 0.0;
                        SumTAmt = 0.0;
                        SumDQty = 0.0;
                        SumDAmt = 0.0;
                        SumPQty = 0.0;
                        SumPAmt = 0.0;
                        SumWQty = 0.0;
                        SumWAmt = 0.0;
                        SumSQty = 0.0;
                        SumSAmt = 0.0;
                    }

                    SumEQty = SumEQty + rec.getDouble("sum(e_qty)");
                    SumEAmt = SumEAmt + rec.getDouble("sum(e_amt)");

                    SumTQty = SumTQty + rec.getDouble("sum(t_qty)");
                    SumTAmt = SumTAmt + rec.getDouble("sum(t_amt)");

                    SumDQty = SumDQty + rec.getDouble("sum(d_qty)");
                    SumDAmt = SumDAmt + rec.getDouble("sum(d_amt)");

                    SumPQty = SumPQty + rec.getDouble("sum(p_qty)");
                    SumPAmt = SumPAmt + rec.getDouble("sum(p_amt)");

                    SumWQty = SumWQty + rec.getDouble("sum(w_qty)");
                    SumWAmt = SumWAmt + rec.getDouble("sum(w_amt)");

                    SumSQty = SumSQty + rec.getDouble("sum(s_qty)");
                    SumSAmt = SumSAmt + rec.getDouble("sum(s_amt)");
                } while (rec.next());
                if (SumSQty > 0) {
                    PluRec GroupRec = new PluRec();
                    GroupRec.MacNo1 = MacNo1;
                    GroupRec.MacNo2 = MacNo2;
                    GroupRec.Cashier1 = "";
                    GroupRec.Cashier2 = "";
                    GroupRec.Group1 = txtMacNo1.getText();
                    GroupRec.Group2 = txtMacNo2.getText();
                    GroupRec.Plu1 = "";
                    GroupRec.Plu2 = "";
                    GroupRec.GroupCode = TempGroup;
                    GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                    GroupRec.PCode = "";
                    GroupRec.PName = "";

                    GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                    GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;

                    GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                    GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;

                    GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                    GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;

                    GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                    GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;

                    GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                    GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                    GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                    GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                    if (ArraySize == 0) {
                        GArray[ArraySize] = GroupRec;
                        ArraySize = GArray.length;
                    } else {
                        GArray = PUtility.addPluArray(GArray);
                        ArraySize = GArray.length;
                        GArray[ArraySize - 1] = GroupRec;
                    }

                }
            }

            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        } finally {
            mysql.close();
        }

        if (Found) {
            Jdi_MTDdepReport dept = new Jdi_MTDdepReport(new Frame(), true);
            dept.setData(GArray);
            dept.setHeaderPage(txtDate1.getText(), txtDate2.getText(), MacNo1, MacNo2);
            dept.setVisible(true);
        } else {
            MSG.ERR(this, "ไม่พบข้อมูลการขายตามช่วงที่ต้องการ...");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        bntOK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JFormattedTextField();
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        bntF1 = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการขายตามกลุ่มสินค้า (MTD Department Report)");
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงวันที่ๆต้องการ (Date)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txtDate1.setEditable(false);
        txtDate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate1MouseClicked(evt);
            }
        });
        txtDate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate1KeyPressed(evt);
            }
        });

        txtDate2.setEditable(false);
        txtDate2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate2MouseClicked(evt);
            }
        });
        txtDate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate2KeyPressed(evt);
            }
        });

        cmdDateChoose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose1.setFocusable(false);
        cmdDateChoose1.setRequestFocusEnabled(false);
        cmdDateChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ถึง");

        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.setRequestFocusEnabled(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "รหัสแผนกสินค้า (Department)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMacNo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMacNo1.setText("0000");
        txtMacNo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMacNo1MouseClicked(evt);
            }
        });
        txtMacNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo1KeyPressed(evt);
            }
        });

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMacNo2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMacNo2.setText("9999");
        txtMacNo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMacNo2MouseClicked(evt);
            }
        });
        txtMacNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntF1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntF1.setText("F1- จอภาพ");
        bntF1.setFocusable(false);
        bntF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntF1ActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(bntF1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntF1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txtMacNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMacNo1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo2.requestFocus();
    }
}//GEN-LAST:event_txtMacNo1KeyPressed

private void txtMacNo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMacNo2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate1.requestFocus();
    }
}//GEN-LAST:event_txtMacNo2KeyPressed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntF1ActionPerformed
// TODO add your handling code here:
    bntViewClick();
}//GEN-LAST:event_bntF1ActionPerformed

private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo1.requestFocus();
    }
}//GEN-LAST:event_txtDate2KeyPressed

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, cmdDateChoose1.getLocationOnScreen());
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, cmdDateChoose1.getLocationOnScreen());
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate2.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

    private void txtDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bntExitClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            bntOKClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            bntViewClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDate2.requestFocus();
        }
    }//GEN-LAST:event_txtDate1KeyPressed

    private void txtDate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate1MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtDate1);
        }
    }//GEN-LAST:event_txtDate1MouseClicked

    private void txtDate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtDate2);
        }
    }//GEN-LAST:event_txtDate2MouseClicked

    private void txtMacNo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo1MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtMacNo1);
        }
    }//GEN-LAST:event_txtMacNo1MouseClicked
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
        /**
         * * OPEN CONNECTION **
         */
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
            rs2.close();
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
            MSG.ERR(e.getMessage());
            
        }

        mysql.close();

        return listObj;
    }

    private void txtMacNo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtMacNo2);
        }
    }//GEN-LAST:event_txtMacNo2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MTDDept dialog = new MTDDept(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntF1;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables
}
