package com.softpos.main.program;

import database.MySQLConnect;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import printReport.PrintDriver;
import util.MSG;

public class CashBackDialog extends javax.swing.JDialog {

    double cash = 0.00;
    private POSHWSetup POSHW;
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;
    Date date = new Date();
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy(HH:mm)", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");

    public CashBackDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        POSHW = POSHWSetup.Bean(Value.getMacno());
        txtCash.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCash = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("จ่ายเงินมัดจำเป็นเงินสด");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Net-Total:");

        lbTotal.setFont(new java.awt.Font("Tahoma", 3, 50)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbTotal.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotal))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("จำนวนเงินคืนเงินมัดจำเป็นเงินสด........................");

        txtCash.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int icon = JOptionPane.showConfirmDialog(this, "ยืนยันการคืนเงินมัดจำเป็นเงินสด (Yes/No) ?");
            if (icon == JOptionPane.YES_OPTION) {
                saveCashBack();
            }
        }
    }//GEN-LAST:event_txtCashKeyPressed

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
            java.util.logging.Logger.getLogger(CashBackDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashBackDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashBackDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashBackDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CashBackDialog dialog = new CashBackDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTextField txtCash;
    // End of variables declaration//GEN-END:variables

    private void saveCashBack() {

        if (!txtCash.getText().trim().equals("")) {
            try {
                cash = Double.parseDouble(txtCash.getText());
            } catch (NumberFormatException e) {
                MSG.WAR(this, "ท่านระบบจำนวนเงินไม่ถูกต้อง กรุณาตรวจสอบ \n" + e.getMessage());
                txtCash.setText("");
                txtCash.requestFocus();

                return;
            }
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            //select returnbillno from branch
            //0000002
            String sql = "select returnbillno from branch";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int refNo = 0;
            String refStr;
            if (rs.next()) {
                String ref = rs.getString(1);
                if (ref != null) {
                    refNo = Integer.parseInt(ref);
                }
            }

            rs.close();
            stmt.close();

            if (refNo < 10) {
                refStr = "000000" + refNo;
            } else if (refNo < 100) {
                refStr = "00000" + refNo;
            } else if (refNo < 1000) {
                refStr = "0000" + refNo;
            } else if (refNo < 10000) {
                refStr = "000" + refNo;
            } else if (refNo < 100000) {
                refStr = "00" + refNo;
            } else if (refNo < 1000000) {
                refStr = "0" + refNo;
            } else {
                refStr = "" + refNo;
            }

            String sql1 = "insert into billret(Ref_No,OnDate,Stotal,Cash,Cupon,Credit,Terminal,Cashier,Fat,UserVoid) values "
                    + "('" + refStr + "',curdate(),'" + cash + "','" + cash + "','0','0','" + Value.MACNO + "','" + Value.CASHIER + "','N','')";
            Statement stmt1 = mysql.getConnection().createStatement();
            int i = stmt1.executeUpdate(sql1);
            if (i > 0) {
                //update returnbillno
                stmt1.executeUpdate("update branch set returnbillno=returnbillno+1");

                //print output to printer
                stmt1.close();
                dispose();
            }
            PrintReturnMoney(refStr);
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            e.printStackTrace();
        }
    }

    public void PrintReturnMoney(String Refno) {
        for (int i = 0; i < 3; i++) {
            String t = "";
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
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3().trim()) + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4()).trim() + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + "REG.ID :" + Space + (POSHW.getTerminal()) + "_";
            t += "colspan=3 align=left><font face=Angsana New size=1>" + ("_");
            t += "colspan=3 align=left><font face=Angsana New size=1>" + ("รายการคืนเงินมัดจำ..ไม่ใช่ใบกำกับภาษี") + "_";
            if (i == 1) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + ("*** สำเนาใบที่ " + i + "***") + "_";
            }
            if (i == 2) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + ("*** สำเนาใบที่ " + i + "***") + "_";
            }
            t += "colspan=3 align=left><font face=Angsana New size=1>" + "Print Date" + Space + (DatefmtThai.format(date)) + "_";
            t += "colspan=3 align=left><font face=Angsana New size=1>" + "Cashier:" + PublicVar._UserName + TAB + " Mac:" + Value.MACNO + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
            t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + ("คืนเงินมัดจำเเป็นเงินสด") + "</td></Font><td align=right><font face=Angsana New size=1>" + DecFmt.format(cash) + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
            PrintDriver pd = new PrintDriver();
            t += "colspan=2 align=left><font face=Angsana New size=1>" + ("Net-Total...") + "</td></Font><td align=right><font face=Angsana New size=2>" + DecFmt.format(cash) + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
            t += "colspan=3 align=right><font face=Angsana New size=1>" + ("RET.-NO.: ") + Refno + TAB + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getFootting1().trim()) + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getFootting2().trim()) + "_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getFootting3().trim()) + "_";
            String[] strs = t.split("_");

            for (String data : strs) {
                pd.addTextIFont(data);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
            pd.printHTML();
        }

    }
}
