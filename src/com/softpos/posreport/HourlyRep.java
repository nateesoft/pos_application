package com.softpos.posreport;

import java.awt.event.KeyEvent;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.softpos.main.model.HourlyRec;
import com.softpos.main.program.Jdi_report_HourlyReport;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.utils.KeyBoardDialog;
import com.softpos.main.utils.MSG;

public class HourlyRep extends javax.swing.JDialog {

    PPrint prn = new PPrint();
    private String CashNo1;

    /** Creates new form HourlyRep */
    public HourlyRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtMacNo1.setText("001");
        txtMacNo2.setText("001");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        bntOK2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการขายตามช่วงเวลา (Hourly Report)");
        setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("หมายเลขเครื่อง");

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntOK2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK2.setText("F1- จอภาพ");
        bntOK2.setFocusable(false);
        bntOK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOK2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bntOK2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(bntOK2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo1.requestFocus();
    }
}//GEN-LAST:event_txtMacNo2KeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void bntOK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOK2ActionPerformed
    bntViewClick() ;
}//GEN-LAST:event_bntOK2ActionPerformed

    private void txtMacNo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo1MouseClicked
        if(evt.getClickCount()==2){
            new KeyBoardDialog(null, true, 4).get(txtMacNo1, 4);
        }
    }//GEN-LAST:event_txtMacNo1MouseClicked

    private void txtMacNo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo2MouseClicked
        if(evt.getClickCount()==2){
            new KeyBoardDialog(null, true, 4).get(txtMacNo2, 4);
        }
    }//GEN-LAST:event_txtMacNo2MouseClicked
    public void bntViewClick() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        int C0 = 0;
        int C1 = 0;
        int C2 = 0;
        int C3 = 0;
        int C4 = 0;
        int C5 = 0;
        int C6 = 0;
        int C7 = 0;
        int C8 = 0;
        int C9 = 0;
        int C10 = 0;
        int C11 = 0;
        int C12 = 0;
        int C13 = 0;
        int C14 = 0;
        int C15 = 0;
        int C16 = 0;
        int C17 = 0;
        int C18 = 0;
        int C19 = 0;
        int C20 = 0;
        int C21 = 0;
        int C22 = 0;
        int C23 = 0;
        int SumC = 0;

        int B0 = 0;
        int B1 = 0;
        int B2 = 0;
        int B3 = 0;
        int B4 = 0;
        int B5 = 0;
        int B6 = 0;
        int B7 = 0;
        int B8 = 0;
        int B9 = 0;
        int B10 = 0;
        int B11 = 0;
        int B12 = 0;
        int B13 = 0;
        int B14 = 0;
        int B15 = 0;
        int B16 = 0;
        int B17 = 0;
        int B18 = 0;
        int B19 = 0;
        int B20 = 0;
        int B21 = 0;
        int B22 = 0;
        int B23 = 0;
        int SumB = 0;

        Double S0 = 0.0;
        Double S1 = 0.0;
        Double S2 = 0.0;
        Double S3 = 0.0;
        Double S4 = 0.0;
        Double S5 = 0.0;
        Double S6 = 0.0;
        Double S7 = 0.0;
        Double S8 = 0.0;
        Double S9 = 0.0;
        Double S10 = 0.0;
        Double S11 = 0.0;
        Double S12 = 0.0;
        Double S13 = 0.0;
        Double S14 = 0.0;
        Double S15 = 0.0;
        Double S16 = 0.0;
        Double S17 = 0.0;
        Double S18 = 0.0;
        Double S19 = 0.0;
        Double S20 = 0.0;
        Double S21 = 0.0;
        Double S22 = 0.0;
        Double S23 = 0.0;
        Double SumS = 0.0;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select *from billno where (b_macno>='" + MacNo1 + "') and (b_macno<='" + MacNo2 + "') and (b_void<>'V')";
//            System.out.println(SqlQuery);
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            String XTime = "";
            String TempTime = "";
            if (rec.getRow() == 0) {
            } else {
                do {
                    TempTime = rec.getString("b_ontime");
                    XTime = rec.getString("b_ontime").substring(0, 2);
                    if (XTime.equals("00")) {
                        B0++;
                        C0 = C0 + rec.getInt("b_cust");
                        S0 = S0 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("01")) {
                        B1++;
                        C1 = C1 + rec.getInt("b_cust");
                        S1 = S1 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("02")) {
                        B2++;
                        C2 = C2 + rec.getInt("b_cust");
                        S2 = S2 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("03")) {
                        B3++;
                        C3 = C0 + rec.getInt("b_cust");
                        S3 = S3 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("04")) {
                        B4++;
                        C4 = C4 + rec.getInt("b_cust");
                        S4 = S4 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("05")) {
                        B5++;
                        C5 = C5 + rec.getInt("b_cust");
                        S5 = S5 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("06")) {
                        B6++;
                        C6 = C6 + rec.getInt("b_cust");
                        S6 = S6 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("07")) {
                        B7++;
                        C7 = C7 + rec.getInt("b_cust");
                        S7 = S7 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("08")) {
                        B8++;
                        C8 = C8 + rec.getInt("b_cust");
                        S8 = S8 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("09")) {
                        B9++;
                        C9 = C9 + rec.getInt("b_cust");
                        S9 = S9 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("10")) {
                        B10++;
                        C10 = C10 + rec.getInt("b_cust");
                        S10 = S10 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("11")) {
                        B11++;
                        C11 = C11 + rec.getInt("b_cust");
                        S11 = S11 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("12")) {
                        B12++;
                        C12 = C12 + rec.getInt("b_cust");
                        S12 = S12 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("13")) {
                        B13++;
                        C13 = C13 + rec.getInt("b_cust");
                        S13 = S13 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("14")) {
                        B14++;
                        C14 = C14 + rec.getInt("b_cust");
                        S14 = S14 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("15")) {
                        B15++;
                        C15 = C15 + rec.getInt("b_cust");
                        S15 = S15 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("16")) {
                        B16++;
                        C16 = C16 + rec.getInt("b_cust");
                        S16 = S16 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("17")) {
                        B17++;
                        C17 = C17 + rec.getInt("b_cust");
                        S17 = S17 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("18")) {
                        B18++;
                        C18 = C18 + rec.getInt("b_cust");
                        S18 = S18 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("19")) {
                        B19++;
                        C19 = C19 + rec.getInt("b_cust");
                        S19 = S19 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("20")) {
                        B20++;
                        C20 = C20 + rec.getInt("b_cust");
                        S20 = S20 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("21")) {
                        B21++;
                        C21 = C21 + rec.getInt("b_cust");
                        S21 = S21 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("22")) {
                        B22++;
                        C22 = C22 + rec.getInt("b_cust");
                        S22 = S22 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("23")) {
                        B23++;
                        C23 = C23 + rec.getInt("b_cust");
                        S23 = S23 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    SumB++;
                    SumC = SumC + rec.getInt("b_cust");
                    SumS = SumS + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));

                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }
        
        int ArraySize = 0;
        HourlyRec HRec = new HourlyRec();
        HourlyRec[] HArray;
        HArray = new HourlyRec[1];
        if (ArraySize == 0) {
            HRec = new HourlyRec();
            HRec.TTime = "00:00";
            HRec.TBill = B0;
            HRec.TCust = C0;
            HRec.TAmount = S0;
            ArraySize = HArray.length;
            HArray[ArraySize - 1] = HRec;
        }
        HourlyRec HRec1 = new HourlyRec();
        HRec1.TTime = "01:00"; HRec1.TBill = B1; HRec1.TCust = C1;HRec1.TAmount = S1;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec1;
        HourlyRec HRec2 = new HourlyRec();
        HRec2.TTime = "02:00"; HRec2.TBill = B2; HRec2.TCust = C2;HRec2.TAmount = S2;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec1;
        HourlyRec HRec3 = new HourlyRec();
        HRec3.TTime = "03:00"; HRec3.TBill = B3; HRec3.TCust = C3;HRec3.TAmount = S3;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec3;
        HourlyRec HRec4 = new HourlyRec();
        HRec4.TTime = "04:00"; HRec4.TBill = B4; HRec4.TCust = C4;HRec4.TAmount = S4;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec4;
        HourlyRec HRec5 = new HourlyRec();
        HRec5.TTime = "05:00"; HRec5.TBill = B5; HRec5.TCust = C5;HRec5.TAmount = S5;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec5;
        HourlyRec HRec6 = new HourlyRec();
        HRec6.TTime = "06:00"; HRec6.TBill = B6; HRec6.TCust = C6;HRec6.TAmount = S6;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec6;
        HourlyRec HRec7 = new HourlyRec();
        HRec7.TTime = "07:00"; HRec7.TBill = B7; HRec7.TCust = C7;HRec7.TAmount = S7;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec7;
        HourlyRec HRec8 = new HourlyRec();
        HRec8.TTime = "08:00"; HRec8.TBill = B8; HRec8.TCust = C8;HRec8.TAmount = S8;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec8;
        HourlyRec HRec9 = new HourlyRec();
        HRec9.TTime = "09:00"; HRec9.TBill = B9; HRec9.TCust = C9;HRec9.TAmount = S9;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec9;
        HourlyRec HRec10 = new HourlyRec();
        HRec10.TTime = "10:00"; HRec10.TBill = B10; HRec10.TCust = C10;HRec10.TAmount = S10;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec10;
        HourlyRec HRec11 = new HourlyRec();
        HRec11.TTime = "11:00"; HRec11.TBill = B11; HRec11.TCust = C11;HRec11.TAmount = S11;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec11;
        HourlyRec HRec12 = new HourlyRec();
        HRec12.TTime = "12:00"; HRec12.TBill = B12; HRec12.TCust = C12;HRec12.TAmount = S12;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec12;
        HourlyRec HRec13 = new HourlyRec();
        HRec13.TTime = "13:00"; HRec13.TBill = B13; HRec13.TCust = C13;HRec13.TAmount = S13;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec13;
        HourlyRec HRec14 = new HourlyRec();
        HRec14.TTime = "14:00"; HRec14.TBill = B14; HRec14.TCust = C14;HRec14.TAmount = S14;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec14;
        HourlyRec HRec15 = new HourlyRec();
        HRec15.TTime = "15:00"; HRec15.TBill = B15; HRec15.TCust = C15;HRec15.TAmount = S15;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec15;
        HourlyRec HRec16 = new HourlyRec();
        HRec16.TTime = "16:00"; HRec16.TBill = B16; HRec16.TCust = C16;HRec16.TAmount = S16;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec16;
        HourlyRec HRec17 = new HourlyRec();
        HRec17.TTime = "17:00"; HRec17.TBill = B17; HRec17.TCust = C17;HRec17.TAmount = S17;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec17;
        HourlyRec HRec18 = new HourlyRec();
        HRec18.TTime = "18:00"; HRec18.TBill = B18; HRec18.TCust = C18;HRec18.TAmount = S18;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec18;
        HourlyRec HRec19 = new HourlyRec();
        HRec19.TTime = "19:00"; HRec19.TBill = B19; HRec19.TCust = C19;HRec19.TAmount = S19;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec19;
        HourlyRec HRec20 = new HourlyRec();
        HRec20.TTime = "20:00"; HRec20.TBill = B20; HRec20.TCust = C20;HRec20.TAmount = S20;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec20;
        HourlyRec HRec21 = new HourlyRec();
        HRec21.TTime = "21:00"; HRec21.TBill = B21; HRec21.TCust = C21;HRec21.TAmount = S21;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec21;
        HourlyRec HRec22 = new HourlyRec();
        HRec22.TTime = "22:00"; HRec22.TBill = B22; HRec22.TCust = C22;HRec22.TAmount = S22;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec22;
        HourlyRec HRec23 = new HourlyRec();
        HRec23.TTime = "23:00"; HRec23.TBill = B23; HRec23.TCust = C23;HRec23.TAmount = S23;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec23;
    
        Jdi_report_HourlyReport view = new Jdi_report_HourlyReport(null,true); 
  //      JOptionPane.showMessageDialog(null, HArray.length);
        view.setData(HArray,MacNo1,MacNo2);
        view.setVisible(true);
        
        txtMacNo1.requestFocus();
    }
    public void bntOKClick() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        int C0 = 0;
        int C1 = 0;
        int C2 = 0;
        int C3 = 0;
        int C4 = 0;
        int C5 = 0;
        int C6 = 0;
        int C7 = 0;
        int C8 = 0;
        int C9 = 0;
        int C10 = 0;
        int C11 = 0;
        int C12 = 0;
        int C13 = 0;
        int C14 = 0;
        int C15 = 0;
        int C16 = 0;
        int C17 = 0;
        int C18 = 0;
        int C19 = 0;
        int C20 = 0;
        int C21 = 0;
        int C22 = 0;
        int C23 = 0;
        int SumC = 0;

        int B0 = 0;
        int B1 = 0;
        int B2 = 0;
        int B3 = 0;
        int B4 = 0;
        int B5 = 0;
        int B6 = 0;
        int B7 = 0;
        int B8 = 0;
        int B9 = 0;
        int B10 = 0;
        int B11 = 0;
        int B12 = 0;
        int B13 = 0;
        int B14 = 0;
        int B15 = 0;
        int B16 = 0;
        int B17 = 0;
        int B18 = 0;
        int B19 = 0;
        int B20 = 0;
        int B21 = 0;
        int B22 = 0;
        int B23 = 0;
        int SumB = 0;

        Double S0 = 0.0;
        Double S1 = 0.0;
        Double S2 = 0.0;
        Double S3 = 0.0;
        Double S4 = 0.0;
        Double S5 = 0.0;
        Double S6 = 0.0;
        Double S7 = 0.0;
        Double S8 = 0.0;
        Double S9 = 0.0;
        Double S10 = 0.0;
        Double S11 = 0.0;
        Double S12 = 0.0;
        Double S13 = 0.0;
        Double S14 = 0.0;
        Double S15 = 0.0;
        Double S16 = 0.0;
        Double S17 = 0.0;
        Double S18 = 0.0;
        Double S19 = 0.0;
        Double S20 = 0.0;
        Double S21 = 0.0;
        Double S22 = 0.0;
        Double S23 = 0.0;
        Double SumS = 0.0;
        
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select * from billno "
                    + "where (b_macno>='" + MacNo1 + "') "
                    + "and (b_macno<='" + MacNo2 + "') "
                    + "and (b_void<>'V') "
                    + "and b_ondate=curdate()";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            String XTime = "";
            String TempTime = "";
            if (rec.getRow() == 0) {
            } else {
                do {
                    TempTime = rec.getString("b_ontime");
                    XTime = rec.getString("b_ontime").substring(0, 2);
                    if (XTime.equals("00")) {
                        B0++;
                        C0 = C0 + rec.getInt("b_cust");
                        S0 = S0 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("01")) {
                        B1++;
                        C1 = C1 + rec.getInt("b_cust");
                        S1 = S1 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("02")) {
                        B2++;
                        C2 = C2 + rec.getInt("b_cust");
                        S2 = S2 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("03")) {
                        B3++;
                        C3 = C0 + rec.getInt("b_cust");
                        S3 = S3 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("04")) {
                        B4++;
                        C4 = C4 + rec.getInt("b_cust");
                        S4 = S4 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("05")) {
                        B5++;
                        C5 = C5 + rec.getInt("b_cust");
                        S5 = S5 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("06")) {
                        B6++;
                        C6 = C6 + rec.getInt("b_cust");
                        S6 = S6 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("07")) {
                        B7++;
                        C7 = C7 + rec.getInt("b_cust");
                        S7 = S7 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("08")) {
                        B8++;
                        C8 = C8 + rec.getInt("b_cust");
                        S8 = S8 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("09")) {
                        B9++;
                        C9 = C9 + rec.getInt("b_cust");
                        S9 = S9 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("10")) {
                        B10++;
                        C10 = C10 + rec.getInt("b_cust");
                        S10 = S10 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("11")) {
                        B11++;
                        C11 = C11 + rec.getInt("b_cust");
                        S11 = S11 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("12")) {
                        B12++;
                        C12 = C12 + rec.getInt("b_cust");
                        S12 = S12 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("13")) {
                        B13++;
                        C13 = C13 + rec.getInt("b_cust");
                        S13 = S13 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("14")) {
                        B14++;
                        C14 = C14 + rec.getInt("b_cust");
                        S14 = S14 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("15")) {
                        B15++;
                        C15 = C15 + rec.getInt("b_cust");
                        S15 = S15 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("16")) {
                        B16++;
                        C16 = C16 + rec.getInt("b_cust");
                        S16 = S16 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("17")) {
                        B17++;
                        C17 = C17 + rec.getInt("b_cust");
                        S17 = S17 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("18")) {
                        B18++;
                        C18 = C18 + rec.getInt("b_cust");
                        S18 = S18 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("19")) {
                        B19++;
                        C19 = C19 + rec.getInt("b_cust");
                        S19 = S19 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("20")) {
                        B20++;
                        C20 = C20 + rec.getInt("b_cust");
                        S20 = S20 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("21")) {
                        B21++;
                        C21 = C21 + rec.getInt("b_cust");
                        S21 = S21 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("22")) {
                        B22++;
                        C22 = C22 + rec.getInt("b_cust");
                        S22 = S22 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    if (XTime.equals("23")) {
                        B23++;
                        C23 = C23 + rec.getInt("b_cust");
                        S23 = S23 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                    }
                    SumB++;
                    SumC = SumC + rec.getInt("b_cust");
                    SumS = SumS + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));

                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }
        
        int ArraySize = 0;
        HourlyRec HRec = new HourlyRec();
        HourlyRec[] HArray;
        HArray = new HourlyRec[1];
        if (ArraySize == 0) {
            HRec = new HourlyRec();
            HRec.TTime = "00:00";
            HRec.TBill = B0;
            HRec.TCust = C0;
            HRec.TAmount = S0;
            ArraySize = HArray.length;
            HArray[ArraySize - 1] = HRec;
        }
        HourlyRec HRec1 = new HourlyRec();
        HRec1.TTime = "01:00"; HRec1.TBill = B1; HRec1.TCust = C1;HRec1.TAmount = S1;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec1;
        HourlyRec HRec2 = new HourlyRec();
        HRec2.TTime = "02:00"; HRec2.TBill = B2; HRec2.TCust = C2;HRec2.TAmount = S2;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec1;
        HourlyRec HRec3 = new HourlyRec();
        HRec3.TTime = "03:00"; HRec3.TBill = B3; HRec3.TCust = C3;HRec3.TAmount = S3;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec3;
        HourlyRec HRec4 = new HourlyRec();
        HRec4.TTime = "04:00"; HRec4.TBill = B4; HRec4.TCust = C4;HRec4.TAmount = S4;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec4;
        HourlyRec HRec5 = new HourlyRec();
        HRec5.TTime = "05:00"; HRec5.TBill = B5; HRec5.TCust = C5;HRec5.TAmount = S5;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec5;
        HourlyRec HRec6 = new HourlyRec();
        HRec6.TTime = "06:00"; HRec6.TBill = B6; HRec6.TCust = C6;HRec6.TAmount = S6;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec6;
        HourlyRec HRec7 = new HourlyRec();
        HRec7.TTime = "07:00"; HRec7.TBill = B7; HRec7.TCust = C7;HRec7.TAmount = S7;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec7;
        HourlyRec HRec8 = new HourlyRec();
        HRec8.TTime = "08:00"; HRec8.TBill = B8; HRec8.TCust = C8;HRec8.TAmount = S8;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec8;
        HourlyRec HRec9 = new HourlyRec();
        HRec9.TTime = "09:00"; HRec9.TBill = B9; HRec9.TCust = C9;HRec9.TAmount = S9;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec9;
        HourlyRec HRec10 = new HourlyRec();
        HRec10.TTime = "10:00"; HRec10.TBill = B10; HRec10.TCust = C10;HRec10.TAmount = S10;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec10;
        HourlyRec HRec11 = new HourlyRec();
        HRec11.TTime = "11:00"; HRec11.TBill = B11; HRec11.TCust = C11;HRec11.TAmount = S11;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec11;
        HourlyRec HRec12 = new HourlyRec();
        HRec12.TTime = "12:00"; HRec12.TBill = B12; HRec12.TCust = C12;HRec12.TAmount = S12;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec12;
        HourlyRec HRec13 = new HourlyRec();
        HRec13.TTime = "13:00"; HRec13.TBill = B13; HRec13.TCust = C13;HRec13.TAmount = S13;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec13;
        HourlyRec HRec14 = new HourlyRec();
        HRec14.TTime = "14:00"; HRec14.TBill = B14; HRec14.TCust = C14;HRec14.TAmount = S14;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec14;
        HourlyRec HRec15 = new HourlyRec();
        HRec15.TTime = "15:00"; HRec15.TBill = B15; HRec15.TCust = C15;HRec15.TAmount = S15;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec15;
        HourlyRec HRec16 = new HourlyRec();
        HRec16.TTime = "16:00"; HRec16.TBill = B16; HRec16.TCust = C16;HRec16.TAmount = S16;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec16;
        HourlyRec HRec17 = new HourlyRec();
        HRec17.TTime = "17:00"; HRec17.TBill = B17; HRec17.TCust = C17;HRec17.TAmount = S17;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec17;
        HourlyRec HRec18 = new HourlyRec();
        HRec18.TTime = "18:00"; HRec18.TBill = B18; HRec18.TCust = C18;HRec18.TAmount = S18;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec18;
        HourlyRec HRec19 = new HourlyRec();
        HRec19.TTime = "19:00"; HRec19.TBill = B19; HRec19.TCust = C19;HRec19.TAmount = S19;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec19;
        HourlyRec HRec20 = new HourlyRec();
        HRec20.TTime = "20:00"; HRec20.TBill = B20; HRec20.TCust = C20;HRec20.TAmount = S20;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec20;
        HourlyRec HRec21 = new HourlyRec();
        HRec21.TTime = "21:00"; HRec21.TBill = B21; HRec21.TCust = C21;HRec21.TAmount = S21;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec21;
        HourlyRec HRec22 = new HourlyRec();
        HRec22.TTime = "22:00"; HRec22.TBill = B22; HRec22.TCust = C22;HRec22.TAmount = S22;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec22;
        HourlyRec HRec23 = new HourlyRec();
        HRec23.TTime = "23:00"; HRec23.TBill = B23; HRec23.TCust = C23;HRec23.TAmount = S23;
        HArray = PUtility.addHourlyArray(HArray);
        ArraySize = HArray.length;
        HArray[ArraySize - 1] = HRec23;

        prn.PrintHourly(HArray,MacNo1,MacNo2);
        txtMacNo1.requestFocus();
    }

    public void bntExitClick() {
        this.dispose();
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

    }

    public void ProcessChkKey(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bntExitClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            bntOKClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtMacNo1.hasFocus()) {
                txtMacNo2.requestFocus();
            }
            if (txtMacNo2.hasFocus()) {
                txtMacNo1.requestFocus();
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                HourlyRep dialog = new HourlyRep(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntOK2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables
}
