package com.softpos.main.program;

import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.utils.PUtility;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.model.BalanceBean;
import com.softpos.main.model.MemberBean;
import com.softpos.main.controller.ItemDisControl;
import java.awt.event.KeyEvent;
import com.softpos.database.util.MySQLConnect;
import java.text.DecimalFormat;
import com.softpos.main.utils.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class ItemDiscount extends javax.swing.JDialog {

    private double XTotal;
    private double XQty;
    private double XPrice;
    private int IndexRow;
    private double XPDisc;
    private double XPAmount;
    private double TempValue;
    private POSConfigSetup CONFIG;
    private String tableNo;
    private BalanceBean balanceBean;
    private String RIndex;
    private DecimalFormat dec1 = new DecimalFormat("#0.00");
    private MemberBean memberBean;

    /**
     * Creates new form ItemDiscount
     */
    public ItemDiscount(java.awt.Frame parent, boolean modal, String tableNo, String RIndex, MemberBean memberBean) {
        super(parent, modal);
        initComponents();

        this.memberBean = memberBean;
        this.tableNo = tableNo;
        this.RIndex = RIndex;

        BalanceControl bCon = new BalanceControl();
        balanceBean = bCon.getBalanceIndex(tableNo, RIndex);

        if (balanceBean != null) {
            txtPCode.setText(balanceBean.getR_PluCode());
            txtPDesc.setText(ThaiUtil.ASCII2Unicode(balanceBean.getR_PName()));
            txtPQuan.setText("" + balanceBean.getR_Quan());
            txtPrice.setText(dec1.format(balanceBean.getR_Price()));
            txtPAmount.setText(dec1.format(balanceBean.getR_Total()));
            txtDiscPercent.setText("" + balanceBean.getR_PrDisc());
            txtDiscBaht.setText("" + balanceBean.getR_PrAmt());
            XQty = balanceBean.getR_Quan();
            XPrice = balanceBean.getR_Price();
            XTotal = balanceBean.getR_Total();
            XPDisc = balanceBean.getR_PrDisc();
            XPAmount = balanceBean.getR_PrAmt();
        }
        TempValue = 0.0;
        CONFIG = POSConfigSetup.Bean();
        txtDiscPercent.requestFocus();
        txtDiscPercent.selectAll();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDiscPercent = new javax.swing.JTextField();
        txtDiscBaht = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPCode = new javax.swing.JTextField();
        txtPDesc = new javax.swing.JTextField();
        txtPQuan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPAmount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Item Discount (ส่วนลดตามรายการสินค้า)");
        setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("ส่วนลด %");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("จำนวนเงินส่วนลด");

        txtDiscPercent.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDiscPercent.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscPercent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDiscPercent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiscPercentMouseClicked(evt);
            }
        });
        txtDiscPercent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiscPercentFocusGained(evt);
            }
        });
        txtDiscPercent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiscPercentKeyPressed(evt);
            }
        });

        txtDiscBaht.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDiscBaht.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscBaht.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDiscBaht.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiscBahtMouseClicked(evt);
            }
        });
        txtDiscBaht.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiscBahtFocusGained(evt);
            }
        });
        txtDiscBaht.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiscBahtKeyPressed(evt);
            }
        });

        btnOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOK.setText("F5-ตกลง (OK)");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        btnOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnOKKeyPressed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExit.setText("ESC-ออก (EXIT)");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        btnExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExitKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiscPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscBaht, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDiscPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiscBaht, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("รหัสสินค้า(PLU Code)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ชื่อสินค้า (Descriotion)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("จำนวนที่ขาย (Quantity)");

        txtPCode.setEditable(false);
        txtPCode.setBackground(new java.awt.Color(255, 255, 255));
        txtPCode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPCode.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        txtPCode.setEnabled(false);

        txtPDesc.setEditable(false);
        txtPDesc.setBackground(new java.awt.Color(255, 255, 255));
        txtPDesc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPDesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPDesc.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        txtPDesc.setEnabled(false);

        txtPQuan.setEditable(false);
        txtPQuan.setBackground(new java.awt.Color(255, 255, 255));
        txtPQuan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPQuan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPQuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPQuan.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        txtPQuan.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("ราคาขาย (Price)");

        txtPrice.setEditable(false);
        txtPrice.setBackground(new java.awt.Color(255, 255, 255));
        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPrice.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        txtPrice.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("จำนวนเงิน (Amount)");

        txtPAmount.setEditable(false);
        txtPAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtPAmount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPAmount.setDisabledTextColor(new java.awt.Color(51, 102, 255));
        txtPAmount.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice))
                    .addComponent(txtPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txtPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(494, 333));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    this.dispose();
}//GEN-LAST:event_btnExitActionPerformed

private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
    if (txtDiscPercent.hasFocus()) {
        PDiscEnter();
    } else if (txtDiscBaht.hasFocus()) {
        pDescAmtEnter();
    } else {
        bntOKClick();
    }
}//GEN-LAST:event_btnOKActionPerformed

private void btnOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnOKKeyPressed
    if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_F5)) {
        btnOKActionPerformed(null);
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    }

}//GEN-LAST:event_btnOKKeyPressed

private void txtDiscPercentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscPercentKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        PDiscEnter();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        btnOKActionPerformed(null);
    }

}//GEN-LAST:event_txtDiscPercentKeyPressed

private void txtDiscBahtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscBahtKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        pDescAmtEnter();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        btnOKActionPerformed(null);
    }
}//GEN-LAST:event_txtDiscBahtKeyPressed

private void btnExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExitKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        this.dispose();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    }
}//GEN-LAST:event_btnExitKeyPressed

    boolean isSelected = false;
    private void txtDiscPercentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiscPercentFocusGained
        txtDiscPercent.selectAll();
        isSelected = true;
    }//GEN-LAST:event_txtDiscPercentFocusGained

    private void txtDiscBahtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiscBahtFocusGained
        txtDiscBaht.selectAll();
        isSelected = true;
    }//GEN-LAST:event_txtDiscBahtFocusGained

    private void txtDiscPercentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiscPercentMouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtDiscPercent, 4);
        }
    }//GEN-LAST:event_txtDiscPercentMouseClicked

    private void txtDiscBahtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiscBahtMouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtDiscBaht, 4);
        }
    }//GEN-LAST:event_txtDiscBahtMouseClicked

    public void bntOKClick() {
        String PCode = txtPCode.getText();
        String Table = tableNo;
        String Index = RIndex;

        String Disc = txtDiscPercent.getText();
        String Baht = txtDiscBaht.getText();
        double prDisc, prBaht = 0.00;
        try {
            prDisc = Double.parseDouble(Disc);
        } catch (NumberFormatException e) {
            MSG.ERR(this, e.getMessage());
            return;
        }
        try {
            prBaht = Double.parseDouble(Baht);
        } catch (NumberFormatException e) {
            MSG.ERR(this, e.getMessage());
        }

        ItemDisControl disCon = new ItemDisControl();
        disCon.saveBalanceItemDiscount(PCode, Table, Index, prDisc, prBaht, memberBean);

        this.dispose();
    }

    public void inputfrombnt(String Str) {
        if (txtDiscPercent.hasFocus()) {
            String tempstr;
            tempstr = txtDiscPercent.getText();
            if (isSelected) {
                tempstr = "";
                isSelected = false;
            }
            tempstr = tempstr + Str;
            txtDiscPercent.setText(tempstr);
        }
        if (txtDiscBaht.hasFocus()) {
            String tempstr;
            tempstr = txtDiscBaht.getText();
            if (isSelected) {
                tempstr = "";
                isSelected = false;
            }
            tempstr = tempstr + Str;
            txtDiscBaht.setText(tempstr);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtDiscBaht;
    private javax.swing.JTextField txtDiscPercent;
    private javax.swing.JTextField txtPAmount;
    private javax.swing.JTextField txtPCode;
    private javax.swing.JTextField txtPDesc;
    private javax.swing.JTextField txtPQuan;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables

    private void PDiscEnter() {
        if (!txtDiscPercent.getText().equals("")) {
            if (PUtility.ChkNumValue(txtDiscPercent.getText())) {
                XPDisc = Double.parseDouble(txtDiscPercent.getText());
                XPAmount = PUtility.RoundDecimal(XPDisc * XTotal / 100, CONFIG.getP_DiscRound());
                txtDiscBaht.setText("" + XPAmount);
                txtDiscPercent.setText("" + XPDisc);
            } else {
                MSG.ERR(this, "กรุณาป้อนข้อมูลที่เป็นตัวเลขเท่านั้น ...");
                txtDiscPercent.selectAll();
                txtDiscPercent.requestFocus();
            }
        } else {
            XPDisc = 0.0;
            XPAmount = 0.0;
            txtDiscPercent.setText("0.00");
        }

        txtDiscBaht.selectAll();
        txtDiscBaht.requestFocus();
    }

    private void pDescAmtEnter() {
        if (txtDiscBaht.getText().equals("")) {
            txtDiscBaht.setText("0.00");
        }
        if (PUtility.ChkNumValue(txtDiscPercent.getText())) {
            XPAmount = Double.parseDouble(txtDiscBaht.getText());
            XPAmount = PUtility.RoundDecimal(XPAmount, CONFIG.getP_DiscRound());
            txtDiscBaht.setText("" + XPAmount);
        } else {
            MSG.ERR(this, "กรุณาป้อนข้อมูลที่เป็นตัวเลขเท่านั้น ...");
            txtDiscBaht.selectAll();
            txtDiscBaht.requestFocus();
        }
        btnOK.requestFocus();
    }
}
