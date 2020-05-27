package com.softpos.posreport;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import database.MySQLConnect;
import java.sql.Statement;
import com.softpos.main.program.POSHWSetup;
import com.softpos.main.program.PPrint;
import com.softpos.main.program.PUtility;
import com.softpos.main.program.PublicVar;
import com.softpos.main.program.Value;
import printReport.PrintDriver;
import soft.virtual.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class PromotionRep extends javax.swing.JDialog {

    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy(HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    PPrint prn = new PPrint();
    private POSHWSetup POSHW;
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;

    /**
     * Creates new form PromotionRep
     */
    public PromotionRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtMacNo1.setText("001");
        txtMacNo2.setText("001");
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bntExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        bntOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานส่วนลดโปรโมชั่น (Promotion Report)");
        setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("หมายเลขเครื่อง");

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtMacNo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo1MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtMacNo1);
        }
    }//GEN-LAST:event_txtMacNo1MouseClicked

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        dispose();
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        bntOKClick();
    }//GEN-LAST:event_bntOKActionPerformed

    private void txtMacNo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtMacNo2);
        }
    }//GEN-LAST:event_txtMacNo2MouseClicked

    public void bntOKClick() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        Double SumQty = 0.0;
        Double SumAmt = 0.0;
        if (!Value.getComPort().equals("NONE")) {
            if (Value.printdriver) {
                PrintPromotionDriver(MacNo1, MacNo2);
            } else {
                if (prn.OpenPrint(Value.getComPort())) {
                    prn.InitPrinter();
                    prn.print(POSHW.getHeading1());
                    prn.print(POSHW.getHeading2());
                    prn.print(POSHW.getHeading3());
                    prn.print(POSHW.getHeading4());
                    prn.print("REG ID :" + Value.MACNO);
                    prn.print("         รายงานส่วนลดโปรโมชั่น");
                    prn.print("         (Promotion Report)");
                    prn.print("หมายเลขเครื่อง :" + MacNo1 + " ..." + MacNo2);
                    prn.print(" ");
                    prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    prn.print("----------------------------------------");
                    prn.print("โปรโมชั่น                จำนวน      ส่วนลด ");
                    prn.print("----------------------------------------");

                    /**
                     * * OPEN CONNECTION **
                     */
                    MySQLConnect mysql = new MySQLConnect();
                    mysql.open();
                    try {
                        Statement stmt = mysql.getConnection().createStatement();
                        String SqlQuery = "select prtype,prcode,sum(pqty),sum(pramt),protab.prodesc from t_promotion left join protab on procode=prcode "
                                + "where (terminal>='" + MacNo1 + "') and (terminal<='" + MacNo2 + "') group by prcode order by prcode";
                        ResultSet rec = stmt.executeQuery(SqlQuery);
                        rec.first();
                        if (rec.getRow() == 0) {
                        } else {
                            do {
                                prn.print(PUtility.DataFullR(rec.getString("prcode"), 3) + "  " + PUtility.DataFullR(ThaiUtil.ASCII2Unicode(rec.getString("prodesc")), 15)
                                        + "" + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(pqty)")), 8) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(pramt)")), 11));
                                SumQty = SumQty + rec.getDouble("sum(pqty)");
                                SumAmt = SumAmt + rec.getDouble("sum(pramt)");
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

    public void PrintPromotionDriver(String MacNo1, String MacNo2) {
        String t = "";
        try {
            MySQLConnect c = new MySQLConnect();
            c.open();
            String sql = "select "
                    + "tp.PrCode PrCode,pt.ProDesc ProDesc,sum(tp.PQty) PQty, sum(tp.PrAmt) PrAmt "
                    + "from t_promotion tp "
                    + "left join protab pt on tp.prcode = pt.ProCode "
                    + "where tp.terminal between'" + MacNo1 + "' and'" + MacNo2 + "' "
                    + "group by tp.prcode";
            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
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
                    t += "colspan=3 align=center><font face=sAngsana New size=1>" + data + "_";
                }
            } else {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
            }
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3()).trim() + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4()).trim() + "_";
            t += ("colspan=3 align=center><font face=Angsana New size=1>_");
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("รายงานส่วนโปรโมชั่น" + "_");
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("(Daily..Promotion Report)" + "_");
            t += ("colspan=3 align=center><font face=Angsana New size=1>_");
            t += "align=left><font face=Angsana New size=1>" + ("หมายเลขเครื่อง :" + "</td><td colspan=2 align=left><font face=Angsana New size=1>" + MacNo1 + " ..." + MacNo2 + "_");
            t += "colspan=3 align=left><font face=Angsana New size=1>" + ("_");
            t += "colspan=3 align=left><font face=Angsana New size=1>" + "Print Date" + Space + (DatefmtThai.format(date)) + "_";
            t += "colspan=3 align=left><font face=Angsana New size=1>" + "Cashier:" + PublicVar._UserName + TAB + " Mac:" + Value.MACNO + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("โปรโมชั่น" + TAB + "จำนวน" + TAB + "ส่วนลด ") + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
            if (rs.next() && !rs.wasNull()) {
                t += "colspan=3 align=left><font face=Angsana New size=1>" + rs.getString("PrCode") + Space + rs.getString("ProDesc") + "_";
                t += "colspan=1 align=right><font face=Angsana New size=1>" + Space + rs.getString("PQty") + "</td></font><td colspan=2 align=right><font face=Angsana New size=1>" + rs.getString("PrAmt") + "_";
                t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
            }

            rs.close();
            c.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PromotionRep dialog = new PromotionRep(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables

}
