package com.softpos.posreport;

import com.softpos.main.model.POSHWSetup;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.Value;
import database.MySQLConnect;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import printReport.PrintDriver;
import com.softpos.main.utils.KeyBoardDialog;
import util.DateChooseDialog;
import util.DateConvert;
import util.MSG;

public class MTDCoupon extends javax.swing.JDialog {

    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy(HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    Date TDate1 = new Date();
    Date TDate2 = new Date();
    PPrint prn = new PPrint();
    DateConvert dc = new DateConvert();
    private POSHWSetup POSHW;
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;

    public MTDCoupon(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate1.setText(DatefmtShow.format(date));
        txtDate2.setText(DatefmtShow.format(date));
        txtMacNo1.setText("001");
        txtMacNo2.setText("999");

        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        bntExit = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JFormattedTextField();
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 3));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("หมายเลขเครื่อง");

        txtMacNo1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txtMacNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo1KeyPressed(evt);
            }
        });

        txtMacNo2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txtMacNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
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
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงวันที่ๆต้องการ (Date)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ถึง");

        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.setRequestFocusEnabled(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        bntExitClick();
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        bntOKClick();
    }//GEN-LAST:event_bntOKActionPerformed

    private void txtDate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate1MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtDate1);
        }
    }//GEN-LAST:event_txtDate1MouseClicked

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

    private void txtDate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtDate2);
        }
    }//GEN-LAST:event_txtDate2MouseClicked

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
            txtMacNo1.requestFocus();
        }
    }//GEN-LAST:event_txtMacNo2KeyPressed
    public void bntOKClick() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        Double SumQty = 0.0;
        Double SumAmt = 0.0;
        if (Value.printdriver) {
            PrintSpecialCuponDriver(MacNo1, MacNo2);
        } else {
            if (!Value.getComPort().equals("NONE")) {
                if (prn.OpenPrint(Value.getComPort())) {
                    prn.InitPrinter();
                    prn.print(PublicVar.HWrec_Heading1);
                    prn.print(PublicVar.HWrec_Heading2);
                    prn.print(PublicVar.HWrec_Heading3);
                    prn.print(PublicVar.HWrec_Heading4);
                    prn.print("REG ID :" + Value.MACNO);
                    prn.print("      รายงานส่วนลดบัตรคูปองพิเศษ");
                    prn.print("         (Coupon Report)");
                    prn.print("หมายเลขเครื่อง :" + MacNo1 + " ..." + MacNo2);
                    prn.print(" ");
                    prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    prn.print("----------------------------------------");
                    prn.print("บัตรคูปอง                 จำนวน      ส่วนลด ");
                    prn.print("----------------------------------------");
                    /**
                     * * OPEN CONNECTION **
                     */
                    MySQLConnect mysql = new MySQLConnect();
                    mysql.open();
                    try {
                        String sql = "select s_cupon.cucode,sum(cuquan),sum(cuamt),cupon.cuname from s_cupon left join cupon on s_cupon.cucode=cupon.cucode "
                                + "where (macno>='" + MacNo1 + "') and (macno<='" + MacNo2 + "')"
                                + "and s_date between'" + dc.dateDatabase(txtDate1.getText()) + "' and '" + dc.dateDatabase(txtDate2.getText()) + "'"
                                + " group by s_cupon.cucode order by s_cupon.cucode";
                        Statement stmt = mysql.getConnection().createStatement();
                        ResultSet rec = stmt.executeQuery(sql);
                        rec.first();
                        if (rec.getRow() == 0) {
                        } else {
                            do {
                                prn.print(PUtility.DataFullR(rec.getString("cucode"), 3) + "  " + PUtility.DataFullR(rec.getString("cuname"), 30));
                                prn.print("                    " + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(cuquan)")), 8) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(cuamt)")), 11));
                                SumQty = SumQty + rec.getDouble("sum(cuquan)");
                                SumAmt = SumAmt + rec.getDouble("sum(cuamt)");
                            } while (rec.next());
                        }
                        rec.close();
                        stmt.close();
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                        
                    } finally {
                        mysql.close();
                    }

                    prn.print("----------------------------------------");
                    prn.print("                    " + PUtility.DataFull(IntFmt.format(SumQty), 8) + PUtility.DataFull(DecFmt.format(SumAmt), 11));
                    prn.print("----------------------------------------");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.CutPaper();
                    prn.closePrint();
                }
            }
        }
        txtMacNo1.requestFocus();

    }

    public void PrintSpecialCuponDriver(String MacNo1, String MacNo2) {
        Double SumQty = 0.0;
        Double SumAmt = 0.0;
        String t = "";
        if (POSHW.getHeading1().length() >= 18) {
            String[] strs = POSHW.getHeading1().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading1().trim().replace(" ", "&nbsp; ") + "_";
        }
        if (POSHW.getHeading2().trim().length() >= 18) {
            String[] strs = POSHW.getHeading2().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=sAngsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
        }
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3()).trim() + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4()).trim() + "_";
        t += ("colspan=3 align=center><font face=Angsana New size=1>_");
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("รายงานส่วนลดบัตรคูปองพิเศษ" + "_");
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("(MTD..Coupon Report)" + "_");
        t += ("colspan=3 align=center><font face=Angsana New size=1>_");
        t += "align=left><font face=Angsana New size=1>" + ("หมายเลขเครื่อง :" + "</td><td colspan=2 align=left><font face=Angsana New size=1>" + MacNo1 + " ..." + MacNo2 + "_");
        t += "colspan=3 align=left><font face=Angsana New size=1>" + ("_");
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "Print Date" + Space + (DatefmtThai.format(date) + Space + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("บัตรคูปอง" + Space + "จำนวน" + Space + "ส่วนลด ") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select s_cupon.cucode,sum(cuquan),sum(cuamt),cupon.cuname from s_cupon left join cupon on s_cupon.cucode=cupon.cucode "
                    + "where (macno>='" + MacNo1 + "') and (macno<='" + MacNo2 + "')"
                    + "and s_date between'" + dc.dateDatabase(txtDate1.getText()) + "' and '" + dc.dateDatabase(txtDate2.getText()) + "' "
                    + "and refund<>'V' "
                    + " group by s_cupon.cucode order by s_cupon.cucode";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rec = stmt.executeQuery(sql);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    t += "colspan=3 align=left><font face=Angsana New size=1>" + TAB + (PUtility.DataFullR(rec.getString("cucode"), 3) + Space + PUtility.DataFullR(rec.getString("cuname"), 30)) + "_";
                    t += "align=right><font face=Angsana New size=1>" + (TAB + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(cuquan)")), 8) + "</td><td colspan=2 align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(cuamt)")), 11)) + "_";
                    SumQty = SumQty + rec.getDouble("sum(cuquan)");
                    SumAmt = SumAmt + rec.getDouble("sum(cuamt)");
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------") + "_";
        t += "align=right><font face=Angsana New size=1>" + "TOTAL" + (Space + PUtility.DataFull(IntFmt.format(SumQty), 8) + "</td><td colspan=2 align=right><font face=Angsana New size=1>" + PUtility.DataFull(DecFmt.format(SumAmt), 11)) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "_";
        txtMacNo1.requestFocus();

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
            java.util.logging.Logger.getLogger(MTDCoupon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MTDCoupon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MTDCoupon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MTDCoupon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MTDCoupon dialog = new MTDCoupon(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables
}
