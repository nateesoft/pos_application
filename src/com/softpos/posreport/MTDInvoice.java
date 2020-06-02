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
import com.softpos.main.utils.DateChooseDialog;
import com.softpos.database.util.MySQLConnect;
import java.sql.Statement;
import com.softpos.main.model.POSHWSetup;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.Value;
import com.softpos.main.utils.KeyBoardDialog;
import com.softpos.main.utils.MSG;

public class MTDInvoice extends javax.swing.JDialog {

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

    public MTDInvoice(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate1.setText(DatefmtShow.format(date));
        txtDate2.setText(DatefmtShow.format(date));
        txtMacNo1.setText("");
        txtMacNo2.setText("");
        InitScreen();

        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JFormattedTextField();
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการให้ส่วนลดแยกตามประเภทการขายและส่วนลด");
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "หมายเลขเครื่อง (Mac No)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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
                .addGap(63, 63, 63)
                .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงวันที่ๆต้องการ (Date)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N

        txtDate1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        txtDate2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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
                .addContainerGap()
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate1.requestFocus();
    }
}//GEN-LAST:event_txtMacNo2KeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo1.requestFocus();
    }
}//GEN-LAST:event_txtDate2KeyPressed

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
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
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate2.requestFocus();
    }
    }//GEN-LAST:event_txtDate1KeyPressed

    private void txtDate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate1MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtDate1);
        }
    }//GEN-LAST:event_txtDate1MouseClicked

    private void txtDate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate2MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtDate2);
        }
    }//GEN-LAST:event_txtDate2MouseClicked

    private void txtMacNo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo1MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtMacNo1);
        }
    }//GEN-LAST:event_txtMacNo1MouseClicked

    private void txtMacNo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo2MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtMacNo2);
        }
    }//GEN-LAST:event_txtMacNo2MouseClicked

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

    public void bntOKClick() {
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
            InitScreen();
        } catch (ParseException ex) {
            MSG.ERR(this, "กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
        }
    }

    public void ProcessProc() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();

        if (!Value.getComPort().equals("NONE")) {
            if (prn.OpenPrint(Value.getComPort())) {
                prn.InitPrinter();
                prn.print(POSHW.getHeading1());
                prn.print(POSHW.getHeading2());
                prn.print(POSHW.getHeading3());
                prn.print(POSHW.getHeading4());
                prn.print("REG ID :" + Value.MACNO);
                prn.print(" รายงานส่วนลดแยกตามประเภทการขายและส่วนลด");
                prn.print("          (MTD Receipt Report)");
                prn.print("ช่วงวันที่  :" + DatefmtShow.format(TDate1) + " ..." + DatefmtShow.format(TDate2));
                prn.print("สำหรับเครื่อง  " + MacNo1 + " ถึง " + MacNo2);
                Date dateP = new Date();
                prn.print(DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                prn.print("----------------------------------------");
                prn.print("ประเภทการขาย               จำนวนบิล/ลูกค้า  ");
                prn.print("      ประเภทส่วนลด        จำนวนเงิน/ส่วนลด  ");
                prn.print("----------------------------------------");
                String TempDate = "";
                String TempMacNo = "";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String SqlQuery = "select b_etd,count(*) as billcnt,sum(b_cust),Sum(b_empdiscAmt),Sum(b_memdiscamt),sum(b_cupondiscamt),sum(b_prodiscamt),sum(b_nettotal) from s_invoice where (b_macno>='" + MacNo1 + "') and (b_macno<='" + MacNo2 + "') and (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') and (b_void<>'V') Group by b_etd order by b_etd ";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        Double Sum0 = 0.0;
                        Double Sum1 = 0.0;
                        Double Sum2 = 0.0;
                        Double Sum3 = 0.0;
                        Double Sum4 = 0.0;
                        Double Sum5 = 0.0;
                        Double Sum6 = 0.0;
                        do {
                            TempMacNo = rec.getString("b_etd");
                            String EtdName = "";
                            if (TempMacNo.equals("E")) {
                                EtdName = "EAT IN";
                            }
                            if (TempMacNo.equals("T")) {
                                EtdName = "TAKE AWAY";
                            }
                            if (TempMacNo.equals("D")) {
                                EtdName = "DELIVERY";
                            }
                            if (TempMacNo.equals("P")) {
                                EtdName = "PINTO";
                            }
                            if (TempMacNo.equals("W")) {
                                EtdName = "WHOLD SALE";
                            }
                            prn.print("*** : " + PUtility.DataFullR(EtdName, 10) + "  " + PUtility.DataFull(IntFmt.format(rec.getDouble("billcnt")), 8) + " / " + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(b_cust)")), 8));
                            prn.print("    " + PUtility.DataFullR("ยอดขาย.................", 20) + " " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_nettotal)")), 12));
                            prn.print("    " + PUtility.DataFullR("ส่วนลดพนักงาน............", 20) + " " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_empdiscamt)")), 12));
                            prn.print("    " + PUtility.DataFullR("ส่วนลดสมาชิก.............", 20) + " " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_memdiscamt)")), 12));
                            prn.print("    " + PUtility.DataFullR("ส่วนลดโปรโมชั่น...........", 20) + " " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_prodiscamt)")), 12));
                            prn.print("    " + PUtility.DataFullR("ส่วนลดบัตรคูปอง...........", 20) + " " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_cupondiscamt)")), 12));

                            //prn.Print_Str(PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_empdiscamt)")),9)+" "+PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_memdiscamt)")),9)+" "+PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_Prodiscamt)")),9)+" "+PUtility.DataFull(DecFmt.format(rec.getDouble("sum(b_cupondiscamt)")),9));
                            prn.print(" ");
                            Sum0 = Sum0 + rec.getDouble("sum(b_nettotal)");
                            Sum1 = Sum1 + rec.getDouble("billcnt");
                            Sum2 = Sum2 + rec.getDouble("sum(b_cust)");
                            Sum3 = Sum3 + rec.getDouble("sum(b_empdiscamt)");
                            Sum4 = Sum4 + rec.getDouble("sum(b_memdiscamt)");
                            Sum5 = Sum5 + rec.getDouble("sum(b_prodiscamt)");
                            Sum6 = Sum6 + rec.getDouble("sum(b_cupondiscamt)");
                        } while (rec.next());
                        prn.print("----------------------------------------");
                        prn.print("*** : " + PUtility.DataFullR("TOTAL.........", 10) + "  " + PUtility.DataFull(IntFmt.format(Sum1), 8) + " / " + PUtility.DataFull(IntFmt.format(Sum2), 8));
                        prn.print("    " + PUtility.DataFullR("ยอดขาย.................", 20) + " " + PUtility.DataFull(DecFmt.format(Sum0), 12));
                        prn.print("    " + PUtility.DataFullR("ส่วนลดพนักงาน...........", 20) + " " + PUtility.DataFull(DecFmt.format(Sum3), 12));
                        prn.print("    " + PUtility.DataFullR("ส่วนลดสมาชิก............", 20) + " " + PUtility.DataFull(DecFmt.format(Sum4), 12));
                        prn.print("    " + PUtility.DataFullR("ส่วนลดโปรโมชั่น..........", 20) + " " + PUtility.DataFull(DecFmt.format(Sum5), 12));
                        prn.print("    " + PUtility.DataFullR("ส่วนลดบัตรคูปอง..........", 20) + " " + PUtility.DataFull(DecFmt.format(Sum6), 12));

                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                } finally{
                    mysql.close();
                }
                prn.print("----------------------------------------");
                prn.print(" ");
                prn.print(" ");
                prn.print(" ");
                
                prn.CutPaper();
                prn.closePrint();
            } else {
//                MSG.ERR("เครื่องพิมพ์ใบกำกับภาษีไม่สามารถพิมพ์ได้ ...");
            }
        }
        InitScreen();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables

}
