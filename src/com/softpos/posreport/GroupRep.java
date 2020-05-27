package com.softpos.posreport;

import java.awt.event.KeyEvent;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.softpos.main.program.PPrint;
import com.softpos.main.program.PUtility;
import com.softpos.main.program.PluRec;
import util.MSG;

public class GroupRep extends javax.swing.JDialog {

    PPrint prn = new PPrint();

    /** Creates new form GroupRep */
    public GroupRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtMacNo1.setText("");
        txtMacNo2.setText("ZZZ");
        txtCashNo1.setText("");
        txtCashNo2.setText("ZZZZZZ");
        txtGroup1.setText("");
        txtGroup2.setText("ZZZZ");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMacNo1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMacNo2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCashNo1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCashNo2 = new javax.swing.JTextField();
        txtGroup1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGroup2 = new javax.swing.JTextField();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการขายตามกลุ่มสินค้า (Group Sales Report)");
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("หมายเลขเครื่อง");

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMacNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMacNo1ActionPerformed(evt);
            }
        });
        txtMacNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo1KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ถึง");

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMacNo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMacNo2ActionPerformed(evt);
            }
        });
        txtMacNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo2KeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("รหัสพนักงานขาย");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("รหัสกลุ่มสินค้า");

        txtCashNo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCashNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCashNo1ActionPerformed(evt);
            }
        });
        txtCashNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashNo1KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("ถึง");

        txtCashNo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCashNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashNo2KeyPressed(evt);
            }
        });

        txtGroup1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtGroup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGroup1ActionPerformed(evt);
            }
        });
        txtGroup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroup1KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ถึง");

        txtGroup2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtGroup2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGroup2ActionPerformed(evt);
            }
        });
        txtGroup2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGroup2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCashNo1, 0, 1, Short.MAX_VALUE)
                    .addComponent(txtGroup1, 0, 1, Short.MAX_VALUE)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(txtCashNo2, 0, 0, Short.MAX_VALUE)
                    .addComponent(txtGroup2, 0, 0, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCashNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGroup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCashNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGroup1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap())
        );

        bntOK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntExit, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bntOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txtMacNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMacNo1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtMacNo1ActionPerformed

private void txtCashNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCashNo1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtCashNo1ActionPerformed

private void txtGroup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroup1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtGroup1ActionPerformed

private void txtMacNo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMacNo2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtMacNo2ActionPerformed

private void txtGroup2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroup2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtGroup2ActionPerformed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void txtMacNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMacNo1KeyPressed
    if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
    }
    if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
        bntExitClick() ;
    }
}//GEN-LAST:event_txtMacNo1KeyPressed

private void txtMacNo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMacNo2KeyPressed
    ProcessChkKey(evt);
}//GEN-LAST:event_txtMacNo2KeyPressed

private void txtCashNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashNo1KeyPressed
    ProcessChkKey(evt);
}//GEN-LAST:event_txtCashNo1KeyPressed

private void txtCashNo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashNo2KeyPressed
    ProcessChkKey(evt);
}//GEN-LAST:event_txtCashNo2KeyPressed

private void txtGroup1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroup1KeyPressed
    ProcessChkKey(evt);
}//GEN-LAST:event_txtGroup1KeyPressed

private void txtGroup2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGroup2KeyPressed
    ProcessChkKey(evt);
}//GEN-LAST:event_txtGroup2KeyPressed

    public void bntOKClick() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        String CashNo1 = txtCashNo1.getText();
        String CashNo2 = txtCashNo2.getText();
        String Group1 = txtGroup1.getText();
        String Group2 = txtGroup2.getText();
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
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select *from t_sale where (macno>='" + MacNo1 + "') and (macno<='" + MacNo2 + "') " +
                    "and (cashier>='" + CashNo1 + "') and (cashier<='" + CashNo2 + "') " +
                    "and (r_group>='" + Group1 + "') and (r_group<='" + Group2 + "') " +
                    "and (r_void<>'V') and (r_refund<>'V') Order by r_group";
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
                TempGroup = rec.getString("r_group");
                do {
                    if (!TempGroup.equals(rec.getString("r_group"))) {
                        PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = CashNo1;
                        GroupRec.Cashier2 = CashNo1;
                        GroupRec.Group1 = Group1;
                        GroupRec.Group2 = Group1;
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
                            ArraySize = GArray.length ;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                        TempGroup = rec.getString("r_group");
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
                    if (rec.getString("r_etd").equals("E")) {
                        SumEQty = SumEQty + rec.getDouble("r_quan");
                        SumEAmt = SumEAmt + rec.getDouble("r_total");
                    }
                    if (rec.getString("r_etd").equals("T")) {
                        SumTQty = SumTQty + rec.getDouble("r_quan");
                        SumTAmt = SumTAmt + rec.getDouble("r_total");
                    }
                    if (rec.getString("r_etd").equals("D")) {
                        SumDQty = SumDQty + rec.getDouble("r_quan");
                        SumDAmt = SumDAmt + rec.getDouble("r_total");
                    }
                    if (rec.getString("r_etd").equals("P")) {
                        SumPQty = SumPQty + rec.getDouble("r_quan");
                        SumPAmt = SumPAmt + rec.getDouble("r_total");
                    }
                    if (rec.getString("r_etd").equals("W")) {
                        SumWQty = SumWQty + rec.getDouble("r_quan");
                        SumWAmt = SumWAmt + rec.getDouble("r_total");
                    }
                    SumSQty = SumSQty + rec.getDouble("r_quan");
                    SumSAmt = SumSAmt + rec.getDouble("r_total");
                } while (rec.next());
            }

            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }
        
        prn.PrintGroup(GArray);
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
        if (txtCashNo1.hasFocus()) {
            String tempstr = "";
            tempstr = txtCashNo1.getText();
            tempstr = tempstr + str;
            txtCashNo1.setText(tempstr);
        }
        if (txtCashNo2.hasFocus()) {
            String tempstr = "";
            tempstr = txtCashNo2.getText();
            tempstr = tempstr + str;
            txtCashNo2.setText(tempstr);
        }
        if (txtGroup1.hasFocus()) {
            String tempstr = "";
            tempstr = txtGroup1.getText();
            tempstr = tempstr + str;
            txtGroup1.setText(tempstr);
        }
        if (txtGroup2.hasFocus()) {
            String tempstr = "";
            tempstr = txtGroup2.getText();
            tempstr = tempstr + str;
            txtGroup2.setText(tempstr);
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
                txtCashNo1.requestFocus();
            }
            if (txtCashNo1.hasFocus()) {
                txtCashNo2.requestFocus();
            }
            if (txtCashNo2.hasFocus()) {
                txtGroup1.requestFocus();
            }
            if (txtGroup1.hasFocus()) {
                txtGroup2.requestFocus();
            }
            if (txtGroup2.hasFocus()) {
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
                GroupRep dialog = new GroupRep(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCashNo1;
    private javax.swing.JTextField txtCashNo2;
    private javax.swing.JTextField txtGroup1;
    private javax.swing.JTextField txtGroup2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables
}
