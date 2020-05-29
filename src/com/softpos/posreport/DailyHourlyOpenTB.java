/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softpos.posreport;

import com.softpos.main.model.POSHWSetup;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.Value;
import java.util.Date;
import com.softpos.main.model.PublicVar;
import printReport.PrintDriver;
import util.MSG;

/**
 *
 * @author ASUS
 */
public class DailyHourlyOpenTB extends javax.swing.JDialog {

    /**
     * Creates new form CustPerHour
     */
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    PPrint prn = new PPrint();
    Date date = new Date();
    Date TDate1 = new Date();
    Date TDate2 = new Date();
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;
    private POSHWSetup POSHW;
    private String Regid;

    public DailyHourlyOpenTB(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    public ArrayList<Object[]> LoadData() {
        ArrayList<Object[]> ListObj = new ArrayList<>();
        MySQLConnect c = new MySQLConnect();
        c.open();
        try {
            DecimalFormat df = new DecimalFormat("00.00");
            DecimalFormat df1 = new DecimalFormat("00.00");
            DecimalFormat df2 = new DecimalFormat("#,##0");
            DefaultTableModel model = (DefaultTableModel) tblSalePerHour.getModel();
            int size1 = model.getRowCount();
            int sumCustE = 0;
            int sumCustT = 0;
            int TotalCustE = 0;
            int TotalCustT = 0;
            int totalCC = 0;
            double sumNet = 0.00;
            double sumBill = 0.00;
            int TTLCC = 0;
            for (int i = 0; i < size1; i++) {
                model.removeRow(0);//ลบข้อมูลบนตารางจนเหลือ 0 ก่อนที่จะโหลดขึ้นมาใหม่
            }
            int t1 = 05;
            int t2 = 06;
            for (int i = 0; i < 24; i++) {
                if (t2 == 23) {
                    t2 = -1;
                }
                if (t1 == 23) {
                    t1 = -1;
                }
                t1++;
                t2++;
                String time1 = df.format(t1).replace(".", ":");
                String time2 = df1.format(t2).replace(".", ":");
                time1 += ":01";
                time2 += ":00";
                int sumB_custE = 0;
                int sumB_custT = 0;
                double sumNettotalE = 0.00;
                double sumNettotalT = 0.00;
                int sumBillE = 0;
                int sumBillT = 0;

                //หาจำนวนบิลที่ชำระเงินในช่วงเวลา E ราคารวม E
                String sqlE = "select count(b_refno) sumBillE,"
                        + "sum(b_cust) sumBcustE,"
                        + "sum(b_nettotal) sumNettotalE "
                        + "from billno  "
                        + "where b_logintime between'" + time1 + "' and '" + time2 + "' and b_void<>'V' and b_etd='E';";
                //หาจำนวนบิลที่ชำระเงินในช่วงเวลา T ราคารวม T
                String sqlT = "select count(b_refno) sumBillT,"
                        + "sum(b_cust) sumBcustT,"
                        + "sum(b_nettotal) sumNettotalT "
                        + "from billno  "
                        + "where b_logintime between'" + time1 + "' and '" + time2 + "' and b_void<>'V' and b_etd='T';";
                //หาจำนวนลูกค้า E
                String sqlE1 = "select sum(b_cust) sumB_custE1 "
                        + "from billno  "
                        + "where b_void<>'V' and b_etd='E';";
                //หาจำนวนลูกค้า T
                String sqlT1 = "select sum(b_cust) sumB_custT1 "
                        + "from billno  "
                        + "where b_void<>'V' and b_etd='T';";
                //หาจำนวนลูกค้า,ราคารวม,ตามช่วงเวลาจาก Tablefile
                String sqlTableFile = "select count(tcode) bill,"
                        + "sum(tcustomer) cc,"
                        + "sum(tamount) amount "
                        + "from tablefile "
                        + "where tcustomer<>'0' "
                        + "and tamount<>'0' "
                        + "and tlogintime between '" + time1 + "' and '" + time2 + "'";

                ResultSet rsTypeE = c.getConnection().createStatement().executeQuery(sqlE);
                if (rsTypeE.next()) {
                    ResultSet rs = c.getConnection().createStatement().executeQuery(sqlTableFile);
                    double bill = 0;
                    double cc = 0;
                    double amount = 0;
                    if (rs.next()) {
                        bill = rs.getDouble("bill");
                        cc = rs.getDouble("cc");
                        amount = rs.getDouble("amount");
                    }
                    sumB_custE = rsTypeE.getInt("sumBcustE");//จำนวนลูกค้า
                    sumB_custE += cc;
                    sumBillE = rsTypeE.getInt("sumBillE");//จำนวนบิล
                    sumBillE += bill;
                    sumNettotalE = rsTypeE.getDouble("sumNettotalE");//มูลค่า
                    sumNettotalE += amount;
                    sumCustE += sumB_custE;
                    sumNet += sumNettotalE;
                    sumBill += sumBillE;
                }
                ResultSet rsTypeT = c.getConnection().createStatement().executeQuery(sqlT);
                if (rsTypeT.next()) {
                    sumB_custT = rsTypeT.getInt("sumBcustT");
                    sumBillT = rsTypeT.getInt("sumBillT");
                    sumNettotalT = rsTypeT.getDouble("sumNettotalT");
                    sumCustT += sumB_custT;
                    sumNet += sumNettotalT;
                    sumBill += sumBillT;
                }
                ResultSet rs1 = c.getConnection().createStatement().executeQuery(sqlE1);
                if (rs1.next()) {
                    TotalCustE = rs1.getInt("sumB_custE1");
                }
                ResultSet rs2 = c.getConnection().createStatement().executeQuery(sqlT1);
                if (rs2.next()) {
                    TotalCustT = rs2.getInt("sumB_custT1");
                }
                TTLCC = sumB_custE + sumB_custT;
                totalCC += TTLCC;
                model.addRow(new Object[]{
                    df.format(t1) + " - " + df1.format(t2),//เวลา
                    IntFmt.format(sumB_custE + sumB_custT),//จำนวนลูกค้า
                    IntFmt.format(sumBillE + sumBillT),//จำนวนบิล
                    IntFmt.format(sumNettotalE + sumNettotalT)//มูลค่า
                });
                ListObj.add(new Object[]{df.format(t1).replace(".", ":") + " - " + df1.format(t2).replace(".", ":"),
                    IntFmt.format(sumB_custE + sumB_custT),
                    IntFmt.format(sumBillE + sumBillT),
                    IntFmt.format(sumNettotalE + sumNettotalT)
                });
            }
            ListObj.add(new Object[]{
                IntFmt.format(totalCC),
                IntFmt.format(sumBill),
                IntFmt.format(sumNet)
            });

            model.addRow(new Object[]{
                "TOTAL : >>>",
                IntFmt.format(sumCustE + sumCustT),
                IntFmt.format(sumBill),
                IntFmt.format(sumNet)
            });
            c.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }
        return ListObj;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblSalePerHour = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        bntOK1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        tblSalePerHour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "เวลา", "จำนวนลูกค้า", "จำนวนบิล", "จำนวนเงิน"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSalePerHour);
        if (tblSalePerHour.getColumnModel().getColumnCount() > 0) {
            tblSalePerHour.getColumnModel().getColumn(0).setMinWidth(100);
            tblSalePerHour.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("ประมวลผล");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
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

        bntOK1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK1.setText("พิมพ์ (F5)");
        bntOK1.setFocusable(false);
        bntOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Customer Count");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Time");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TTL CC");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bntOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOK1ActionPerformed
        LoadData();
        ProcessProc();
    }//GEN-LAST:event_bntOK1ActionPerformed

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        btnExitClick();
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        LoadData();
    }//GEN-LAST:event_bntOKActionPerformed
    public void btnOKClick() {

    }

    public void ProcessProc() {
        PrintHourlyByCust();
    }

    public void btnExitClick() {
        this.dispose();
    }

    public void PrintHourlyByCust() {
        ArrayList<Object[]> ListObj = LoadData();
        if (Value.printdriver) {
            PrintHourlyByCustDriver();
        } else {
            if (!Value.getComPort().equals("NONE")) {
                if (prn.OpenPrint(Value.getComPort())) {
                    prn.InitPrinter();
                    prn.print(POSHW.getHeading1());
                    prn.print(POSHW.getHeading2());
                    prn.print(POSHW.getHeading3());
                    prn.print(POSHW.getHeading4());
                    prn.print("REG ID :" + Value.MACNO);
                    prn.print("     (Daily Customer count per Hours)");
                    prn.print("ช่วงวันที่  :" + DatefmtShow.format(TDate1) + " ..." + DatefmtShow.format(TDate2));
                    prn.print(" ");
                    Date dateP = new Date();
                    prn.print(DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    prn.print("----------------------------------------");
                    prn.print("    Time       CC       Bill      Amount");
                    prn.print("----------------------------------------");
                    if (ListObj != null && ListObj.size() > 0) {
                        String time = "-";
                        String DineIn = "0";
                        String TakeAway = "0";
                        String TTLCustomer = "0";
//                    String amount = "0";
                        for (int i = 0; i < 24; i++) {
                            time = (ListObj.get(i)[0].toString().replace(" ", ""));
                            DineIn = (ListObj.get(i)[1].toString());
                            TakeAway = (ListObj.get(i)[2].toString());
                            TTLCustomer = (ListObj.get(i)[3].toString());
                            prn.print(time + " " + PUtility.DataFull((DineIn), 5) + PUtility.DataFull((TakeAway), 10) + PUtility.DataFull((TTLCustomer), 11));
                        }
                        prn.print(" ");
                        prn.print("TTL CC >>> " + ListObj.get(24)[0]);
                        prn.print("TTL Bill >>> " + ListObj.get(24)[1]);
                        prn.print("TTL Amount >>> " + ListObj.get(24)[2]);
                    }
                    prn.print("----------------------------------------");
                    prn.print("----------------------------------------");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");

                    prn.CutPaper();
                    prn.closePrint();
                } else {
//                PUtility.showError("เครื่องพิมพ์ใบกำกับภาษีไม่สามารถพิมพ์ได้ ...");
                }
            }
        }
    }

    public void PrintHourlyByCustDriver() {
        ArrayList<Object[]> ListObj = LoadData();
        String t = "";
        POSHW = POSHWSetup.Bean(Value.getMacno());
        if (POSHW.getHeading1().trim().length() >= 18) {
            String[] strs = POSHW.getHeading1().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=4 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=4 align=center><font face=Angsana New size=1>" + POSHW.getHeading1().trim().replace(" ", "&nbsp; ") + "_";
        }
        if (POSHW.getHeading2().length() >= 18) {
            String[] strs = POSHW.getHeading2().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=4 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=4 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
        }
        t += "colspan=4 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3().trim()) + "_";
        t += "colspan=4 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4().trim()) + "_";
        t += "colspan=4 align=center><font face=Angsana New size=1>" + "REG ID :" + POSHW.getTerminal() + "_";
        t += ("colspan=4 align=center><font face=Angsana New size=1>_");
        t += "colspan=4 align=center><font face=Angsana New size=1>" + ("(Daily Customer per Hours)" + "_");
        t += "colspan=4 align=center><font face=Angsana New size=1>_";
        Date dateP = new Date();
        t += "colspan=4 align=center><font face=Angsana New size=1>" + "Print Date" + (DatefmtThai.format(dateP)) + "_";
        t += "colspan=4 align=center><font face=Angsana New size=1>" + "Cashier:" + PublicVar._UserName + " Mac:" + Value.MACNO + "_";
        t += "colspan=4 align=center><font face=Angsana New size=1>" + ("----------------------------------------" + "_");
        t += "colspan=1 align=center><font face=Angsana New size=1>" + "Time" + "</td></font><td align=center><font face=Angsana New size=1>" + "CC" + "</td></font><td align=center><font face=Angsana New size=1>" + "Bill" + "</td></font><td align=center><font face=Angsana New size=1>" + "Amount" + "_";
        t += "colspan=4 align=center><font face=Angsana New size=1>" + ("----------------------------------------" + "_");
        if (ListObj != null && ListObj.size() > 0) {
            String time = "-";
            String DineIn = "0";
            String TakeAway = "0";
            String TTLCustomer = "0";
//                    String amount = "0";
            for (int i = 0; i < 24; i++) {
                time = (ListObj.get(i)[0].toString().replace(" ", ""));
                DineIn = (ListObj.get(i)[1].toString());
                TakeAway = (ListObj.get(i)[2].toString());
                TTLCustomer = (ListObj.get(i)[3].toString());
                t += "colspan=1 align=left><font face=Angsana New size=1>" + time + "</td></font><td align=right><font face=Angsana New size=1>" + (DineIn) + "</td></font><td align=right><font face=Angsana New size=1>" + (TakeAway) + "</td></font><td align=right><font face=Angsana New size=1>" + (TTLCustomer) + "_";
            }
            t += "colspan=4 align=center><font face=Angsana New size=1>" + ("----------------------------------------") + "_";
            t += "colspan=3 align=left><font face=Angsana New size=1>" + ("TTL CC >>> " + "</td></font><td align=right><font face=Angsana New size=1>" + ListObj.get(24)[0]) + "_";
            t += "colspan=3 align=left><font face=Angsana New size=1>" + ("TTL Bill >>> " + "</td></font><td align=right><font face=Angsana New size=1>" + ListObj.get(24)[1]) + "_";
            t += "colspan=3 align=left><font face=Angsana New size=1>" + ("TTL Amount >>> " + "</td></font><td align=right><font face=Angsana New size=1>" + ListObj.get(24)[2]) + "_";
        }
        t += "colspan=4 align=center><font face=Angsana New size=1>" + ("----------------------------------------") + "_";

        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        pd.setResolution(100, 75);
        for (String data1 : strs) {
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        pd.printHTML();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DailyHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DailyHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DailyHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DailyHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DailyHourlyOpenTB dialog = new DailyHourlyOpenTB(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
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
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntOK1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblSalePerHour;
    // End of variables declaration//GEN-END:variables
}
