/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softpos.main.program;

import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.utils.ThaiUtil;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.model.BalanceBean;
import com.softpos.main.model.MemberBean;
import database.MySQLConnect;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import soft.virtual.KeyBoardDialog;

/**
 *
 * @author Dell-Softpos
 */
public class ItemEditQty extends javax.swing.JDialog {

    private double XTotal;
    private double XQty;
    private double XNewQty;
    private double XPrice;
    private int IndexRow;
    private double XPDisc;
    private double XPAmount;
    private double XNewAmount;
    private double TempValue;
    private POSConfigSetup CONFIG;
    private String tableNo;
    private BalanceBean balanceBean;
    private String RIndex;
    private DecimalFormat dec1 = new DecimalFormat("#0.00");
    private MemberBean memberBean;
    private String NewDesc;

    /**
     * Creates new form ItemEditQty
     */
    public ItemEditQty(java.awt.Dialog parent, boolean modal, String tableNo, String RIndex, MemberBean memberBean) {
        super(parent, modal);
        initComponents();
        this.memberBean = memberBean;
        this.tableNo = tableNo;
        this.RIndex = RIndex;

        txtPCode.setText("");
        txtPDesc.setText("");
        txtPQuan.setText("");
        txtPrice.setText("");
        txtPAmount.setText("");
        txtPQuanEdit.setText("");
        txtPAmountEdit.setText("");
        txtPQuanEdit.requestFocus();
        txtNewPDesc.setText("");
        BalanceControl bCon = new BalanceControl();
        balanceBean = bCon.getBalanceIndex(tableNo, RIndex);

        if (balanceBean != null) {
            txtPCode.setText(balanceBean.getR_PluCode());
            txtPDesc.setText(sun.natee.project.util.ThaiUtil.ASCII2Unicode(balanceBean.getR_PName()));
            txtPQuan.setText("" + dec1.format(balanceBean.getR_Quan()));
            txtPrice.setText(dec1.format(balanceBean.getR_Price()));
            txtPAmount.setText(dec1.format(balanceBean.getR_Total()));
            XQty = balanceBean.getR_Quan();
            XPrice = balanceBean.getR_Price();
            this.XPrice = XPrice;
            XTotal = balanceBean.getR_Total();
            XPDisc = balanceBean.getR_PrDisc();
            XPAmount = balanceBean.getR_PrAmt();
        }
        TempValue = 0.0;
        CONFIG = POSConfigSetup.Bean();
    }

    public void ProcessUpdateBalance() {
        if (XNewQty > 0.0000001) {
            BalanceControl bCon = new BalanceControl();
            balanceBean = bCon.getBalanceIndex(tableNo, RIndex);
            String newdesc = ThaiUtil.Unicode2ASCII(txtNewPDesc.getText().trim());
            double newAmount = Double.parseDouble(txtPAmountEdit.getText().replace(",", ""));
            String sqlUpdateBalance = "";
            SimpleDateFormat Datefmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
            Date dateP = new Date();
            String sqlKeepTempEdit = "INSERT INTO tempeditqty "
                    + "(OnDate, Time, Emp, "
                    + "Pcode, Pdesc, OldQty, "
                    + "OldPrice, NewQty, NewPrice) "
                    + "VALUES ("
                    + "'" + balanceBean.getR_Date() + "', '" + Datefmt.format(dateP) + "', '" + balanceBean.getR_Emp() + "/" + balanceBean.getR_Table() + "',"
                    + " '" + balanceBean.getR_PluCode() + "', '" + ThaiUtil.Unicode2ASCII(balanceBean.getR_ETD() + "-" + balanceBean.getR_PName() + "-" + balanceBean.getR_Opt1()) + "','" + XQty + "',"
                    + " '" + XPrice + "','" + XNewQty + "','" + newAmount + "');";

            if (balanceBean != null) {
                try {
                    MySQLConnect c = new MySQLConnect();
                    c.open();
                    balanceBean.setR_Quan(XNewQty);
                    balanceBean.setR_Total(XNewAmount);
                    NewDesc = ThaiUtil.Unicode2ASCII(txtNewPDesc.getText().trim().replace(" ", "-"));
                    if (!txtNewPDesc.getText().replace("null", "").equals("")) {
                        System.out.println(true);
                        this.XNewAmount = XNewQty * newAmount;
                        balanceBean.setR_Total(XNewAmount);
                        if (balanceBean.getR_Price() != 0) {
                            newAmount = balanceBean.getR_Price();
                        }
                        sqlUpdateBalance = "update balance set "
                                + "r_total='" + balanceBean.getR_Total() + "'"
                                + ",r_quan='" + balanceBean.getR_Quan() + "' "
                                + ThaiUtil.Unicode2ASCII(",r_pname='" + NewDesc + "' ")
                                //                                + ThaiUtil.Unicode2ASCII(",r_opt1='" + NewDesc + "' ")
                                + ",r_price='" + newAmount + "' "
                                + "where r_index='" + RIndex + "' "
                                + "and r_plucode='" + txtPCode.getText() + "';";
                        balanceBean.setR_PName(NewDesc);
                        try {
                            c.getConnection().createStatement().executeUpdate(sqlKeepTempEdit);
                        } catch (Exception e) {
                        }
                    } else {
                        if (balanceBean.getR_Price() != 0) {
                            newAmount = balanceBean.getR_Price();
                        }
                        System.out.println(false);
                        this.XNewAmount = XNewQty * newAmount;
                        balanceBean.setR_Total(XNewAmount);
                        sqlUpdateBalance = "update balance set "
                                + "r_total='" + balanceBean.getR_Total() + "'"
                                + ",r_quan='" + balanceBean.getR_Quan() + "' "
                                + ",r_price='" + newAmount + "' "
                                + "where r_index='" + RIndex + "' "
                                + "and r_plucode='" + txtPCode.getText() + "';";
                    }

                    String sqlUpdateTableFile = "";
                    sqlUpdateTableFile = "update tablefile set "
                            + "TAmount= TAmount+" + (XNewAmount) + " "
                            + "where tcode='" + tableNo + "';";

                    c.getConnection().createStatement().executeUpdate(sqlUpdateBalance);
                    c.getConnection().createStatement().executeUpdate(sqlUpdateTableFile);
                    try {
                        c.getConnection().createStatement().executeUpdate(sqlKeepTempEdit);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } catch (Exception e) {

                }
            }
            BalanceControl.updateProSerTable(tableNo, memberBean);
            this.dispose();
        } else {
            txtPQuanEdit.selectAll();
            txtPQuanEdit.requestFocus();
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

        jPanel1 = new javax.swing.JPanel();
        txtPCode = new javax.swing.JTextField();
        txtPDesc = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtPQuan = new javax.swing.JTextField();
        txtPAmount = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtPQuanEdit = new javax.swing.JTextField();
        txtPAmountEdit = new javax.swing.JTextField();
        OK = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        txtNewPDesc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtPCode.setEditable(false);
        txtPCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPCode.setText("jTextField1");

        txtPDesc.setEditable(false);
        txtPDesc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPDesc.setText("jTextField1");

        txtPrice.setEditable(false);
        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrice.setText("jTextField1");

        txtPQuan.setEditable(false);
        txtPQuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPQuan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPQuan.setText("jTextField1");

        txtPAmount.setEditable(false);
        txtPAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPAmount.setText("jTextField1");

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "แก้ไขจำนวนเป็น", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtPQuanEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPQuanEdit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPQuanEdit.setText("jTextField1");
        txtPQuanEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPQuanEditActionPerformed(evt);
            }
        });

        txtPAmountEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPAmountEdit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPAmountEdit.setText("jTextField1");
        txtPAmountEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPAmountEditMouseClicked(evt);
            }
        });
        txtPAmountEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPAmountEditActionPerformed(evt);
            }
        });

        OK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OK.setText("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Cancel.setText("CANCEL");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        txtNewPDesc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNewPDesc.setText("jTextField1");
        txtNewPDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNewPDescMouseClicked(evt);
            }
        });
        txtNewPDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPDescActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("เปลี่ยนชื่อสินค้าเป็น");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X คูณ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("จำนวน");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("/kg.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPQuanEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPAmountEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtNewPDesc))
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPQuanEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPAmountEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("แก้ไขจำนวนสินค้า");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPQuan, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtPCode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPAmount))
                            .addComponent(txtPDesc)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelActionPerformed

    private void txtPQuanEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPQuanEditActionPerformed
        double newQty = Double.parseDouble(txtPQuanEdit.getText().trim().replace(",", ""));
        txtPAmountEdit.setText(dec1.format(newQty * XPrice));
        this.XNewQty = newQty;
        this.XNewAmount = newQty * XPrice;
        txtPAmountEdit.requestFocus();
        txtPAmountEdit.selectAll();

//        OKActionPerformed(null);
    }//GEN-LAST:event_txtPQuanEditActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        ProcessUpdateBalance();
    }//GEN-LAST:event_OKActionPerformed

    private void txtPAmountEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPAmountEditActionPerformed
        if (!txtPAmountEdit.getText().equals("")) {
            txtNewPDesc.requestFocus();
            txtNewPDesc.selectAll();
        }
    }//GEN-LAST:event_txtPAmountEditActionPerformed

    private void txtNewPDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPDescActionPerformed
        OKActionPerformed(null);
    }//GEN-LAST:event_txtNewPDescActionPerformed

    private void txtPAmountEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPAmountEditMouseClicked
        txtPQuanEditActionPerformed(null);
    }//GEN-LAST:event_txtPAmountEditMouseClicked

    private void txtNewPDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNewPDescMouseClicked
        txtPAmountEditActionPerformed(null);
    }//GEN-LAST:event_txtNewPDescMouseClicked

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
            java.util.logging.Logger.getLogger(ItemEditQty.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemEditQty.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemEditQty.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemEditQty.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ItemEditQty dialog = new ItemEditQty(new javax.swing.JDialog(), true, "1", "1/002", null);
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
    private javax.swing.JButton Cancel;
    private javax.swing.JButton OK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtNewPDesc;
    private javax.swing.JTextField txtPAmount;
    private javax.swing.JTextField txtPAmountEdit;
    private javax.swing.JTextField txtPCode;
    private javax.swing.JTextField txtPDesc;
    private javax.swing.JTextField txtPQuan;
    private javax.swing.JTextField txtPQuanEdit;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
