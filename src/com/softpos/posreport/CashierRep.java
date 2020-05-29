package com.softpos.posreport;

import java.awt.event.KeyEvent;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import com.softpos.main.model.CreditRec;
import com.softpos.main.model.FinalcialRec;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PublicVar;
import com.softpos.main.utils.MSG;


public class CashierRep extends javax.swing.JDialog {

    PPrint prn = new PPrint();
    static SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss");
    static DecimalFormat IntFmt = new DecimalFormat("##,###,##0");

    /** Creates new form CashierRep */
    public CashierRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        txtCashNo.setText(PublicVar._RealUser);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bntOK = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCashNo = new javax.swing.JTextField();
        bntExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("รายงานยอดการเงินพนักงานขาย Cashier Report");
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์รายงาน");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 2, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("รหัสพนักงานขาย");

        txtCashNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCashNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCashNo.setText("001");
        txtCashNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashNoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCashNo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCashNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
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

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void txtCashNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashNoKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    
}//GEN-LAST:event_txtCashNoKeyPressed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        bntOKClick();
    }//GEN-LAST:event_bntOKActionPerformed

    public void inputfrombnt(String str) {
        String tempstr = "";
        tempstr = txtCashNo.getText();
        tempstr = tempstr + str;
        txtCashNo.setText(tempstr);
    }

    public void bntExitClick() {
        this.dispose();
    }

    public void bntOKClick() {
        boolean datafound = false;
        String TempCashNo = txtCashNo.getText();
        FinalcialRec frec = new FinalcialRec();
        CreditRec[] CrArray;

        CrArray = null;

        int ArraySize = 0;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select * from billno "
                    + "where b_cashier='" + TempCashNo + "' "
                    + "and b_ondate=curdate() "
                    + "order by b_refno";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                PUtility.ShowMsg("ไม่พบยอดขายของพนักงานรหัสนี้...");
                datafound = false;
            } else {
                datafound = true;
                frec.StBill = rec.getString("b_refno");
                frec.Cashier1 = TempCashNo;
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
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
        
        if (datafound) {
            try {
                Statement stmt =  mysql.getConnection().createStatement();
                String SqlQuery = "select * from paidiofile "
                        + "where cashier='" + TempCashNo + "' "
                        + "and flage='I' "
                        + "and date=curdate() ";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        frec.Paid_InCnt++;
                        frec.Paid_In = frec.Paid_In + rec.getDouble("paidinamt");
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            try {
                Statement stmt =  mysql.getConnection().createStatement();
                String SqlQuery = "select * from paidiofile "
                        + "where cashier='" + TempCashNo + "' "
                        + "and flage='O' "
                        + "and date=curdate()";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        frec.Paid_OutCnt++;
                        frec.Paid_Out = frec.Paid_Out + rec.getDouble("paidoutamt");
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            try {
                Statement stmt =  mysql.getConnection().createStatement();
                String SqlQuery = "select * from t_sale "
                        + "where r_void='V' "
                        + "and cashier='" + TempCashNo + "' "
                        + "and r_date=curdate()";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        frec.CntVoid++;
                        frec.VoidValue = frec.VoidValue + rec.getDouble("r_total");
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            
            mysql.close();
            prn.PrintCashier(frec, CrArray);
        }
        txtCashNo.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                CashierRep dialog = new CashierRep(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField txtCashNo;
    // End of variables declaration//GEN-END:variables
}
