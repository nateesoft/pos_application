package com.softpos.main.program;

import com.softpos.main.model.PublicVar;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.model.BalanceBean;
import com.softpos.database.util.MySQLConnect;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class ChangTypeDialog extends javax.swing.JDialog {

    private DefaultTableModel model;
    private String tableNo;
    private BalanceControl balanceControl;
    private ArrayList<BalanceBean> listBalance;
    private String tempETD;

    /**
     * Creates new form chang
     */
    public ChangTypeDialog(java.awt.Frame parent, boolean modal, String tableNo, String tempETD) {
        super(parent, modal);
        initComponents();
        bntDelivery.setVisible(false);
        bntPinto.setVisible(false);
        bntWholeSale.setVisible(false);
        this.tableNo = tableNo;
        this.tempETD = tempETD;

        balanceControl = new BalanceControl();

        model = (DefaultTableModel) tblshowplu.getModel();
        tblshowplu.setShowGrid(true);
        tblshowplu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblshowplu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblshowplu.setRowSelectionAllowed(true);
        tblshowplu.setGridColor(Color.gray);
        JTableHeader header = tblshowplu.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));
        /* Set Column Size      */
        int[] ColSize = {120, 30, 30, 120, 250, 80};
        for (int i = 0; i < 6; i++) {
            //int vColIndex = 0;
            TableColumn col = tblshowplu.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }
        ClearTable();
        LoadDataToTable();
    }

    private void LoadDataToTable() {
        listBalance = balanceControl.getAllBalance(tableNo);
        for (int i = 0; i < listBalance.size(); i++) {
            BalanceBean bean = (BalanceBean) listBalance.get(i);

            Object[] input = {bean.getR_Index(), bean.getR_Void(), bean.getR_ETD(), bean.getR_PluCode(),
                ThaiUtil.ASCII2Unicode(bean.getR_PName()), bean.getR_Price()};
            model.addRow(input);
        }

        int RowCount = model.getRowCount();
        showCell(RowCount - 1, 0);

    }

    private void ClearTable() {
        int RowCount = model.getRowCount();
        for (int i = 0; i < RowCount; i++) {
            model.removeRow(0);
        }
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = tblshowplu.getCellRect(row, column, true);
            tblshowplu.scrollRectToVisible(rect);
            tblshowplu.clearSelection();
            tblshowplu.setRowSelectionInterval(row, row);
        }
    }

    void updateBalance(String R_Index, String R_ETD) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update balance set R_ETD='" + R_ETD + "' "
                    + "where R_Index='" + R_Index + "' and R_Table='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            
        }
    }

    public void bntEatinClick() {
        if (!ChkAllType.isSelected()) {
            String R_Index = GetSelectedRow();
            for (int i = 0; i < listBalance.size(); i++) {
                BalanceBean bean = (BalanceBean) listBalance.get(i);
                if (bean.getR_Index().equals(R_Index)) {
                    if (tempETD.equals("D") || tempETD.equals("P") || tempETD.equals("W")) {
                        MSG.ERR(this, "ไม่สามารถเปลี่ยน Type ได้...");
                    } else {
                        updateBalance(bean.getR_Index(), "E");
                    }
                }
            }
        } else {
            for (int i = 0; i < listBalance.size(); i++) {
                BalanceBean bean = (BalanceBean) listBalance.get(i);
                updateBalance(bean.getR_Index(), "E");
            }
        }

        ClearTable();
        LoadDataToTable();
    }

    public void bntTakeAwayClick() {
        if (!ChkAllType.isSelected()) {
            if (tempETD.equals("D") || tempETD.equals("P") || tempETD.equals("W")) {
                MSG.ERR(this, "ไม่สามารถเปลี่ยน Type ได้...");
            } else {
                String R_Index = GetSelectedRow();
                for (int i = 0; i < listBalance.size(); i++) {
                    BalanceBean bean = (BalanceBean) listBalance.get(i);
                    if (bean.getR_Index().equals(R_Index)) {
                        updateBalance(bean.getR_Index(), "T");
                    }
                }
            }
        } else {
            for (int i = 0; i < listBalance.size(); i++) {
                BalanceBean bean = (BalanceBean) listBalance.get(i);
                updateBalance(bean.getR_Index(), "T");
            }
        }

        ClearTable();
        LoadDataToTable();
    }

    public void bntDeliveryClick() {
        if (!ChkAllType.isSelected()) {
            MSG.ERR(this, "ไม่สามารถเปลี่ยนบางรายการเป็น Type 'D' ได้ ต้องเปลี่ยนทุกรายการ 'Change All' เท่านั้น... ");
        } else {
            for (int i = 0; i < listBalance.size(); i++) {
                BalanceBean bean = (BalanceBean) listBalance.get(i);
                updateBalance(bean.getR_Index(), "D");
            }
            ClearTable();
            LoadDataToTable();
        }
    }

    public void bntPintoClick() {
        if (!ChkAllType.isSelected()) {
            MSG.ERR(this, "ไม่สามารถเปลี่ยนบางรายการเป็น Type 'P' ได้ ต้องเปลี่ยนทุกรายการ 'Change All' เท่านั้น... ");
        } else {
            for (int i = 0; i < listBalance.size(); i++) {
                BalanceBean bean = (BalanceBean) listBalance.get(i);
                updateBalance(bean.getR_Index(), "P");
            }
            ClearTable();
            LoadDataToTable();
        }
    }

    public void bntWholeSaleClick() {
        if (!ChkAllType.isSelected()) {
            MSG.ERR(this, "ไม่สามารถเปลี่ยนบางรายการเป็น Type 'W' ได้ ต้องเปลี่ยนทุกรายการ 'Change All' เท่านั้น... ");
        } else {
            for (int i = 0; i < listBalance.size(); i++) {
                BalanceBean bean = (BalanceBean) listBalance.get(i);
                updateBalance(bean.getR_Index(), "W");
            }
            ClearTable();
            LoadDataToTable();
        }
    }

    public String GetSelectedRow() {
        int maxrow;
        int currow = 0;
        String TableSelected;
        maxrow = tblshowplu.getRowCount();
        for (int i = 0; i < maxrow; i++) {
            if (tblshowplu.isRowSelected(i)) {
                currow = i;
            }
        }
        TableSelected = tblshowplu.getValueAt(currow, 0).toString();
        return TableSelected;
    }

    public void bntOKClick() {
        PublicVar.ChangTypeOK = true;
        this.dispose();
    }

    public void bntCancelClick() {
        PublicVar.ChangTypeOK = false;
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblshowplu = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        bntEatin = new javax.swing.JButton();
        bntTakeAway = new javax.swing.JButton();
        bntDelivery = new javax.swing.JButton();
        bntPinto = new javax.swing.JButton();
        bntWholeSale = new javax.swing.JButton();
        bntCancel = new javax.swing.JButton();
        ChkAllType = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("เปลี่ยนประเภทการขาย");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblshowplu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblshowplu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblshowplu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index Key", "Void", "Type", "PLU Code", "Description", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblshowplu.setRowHeight(30);
        tblshowplu.setUpdateSelectionOnSort(false);
        tblshowplu.setVerifyInputWhenFocusTarget(false);
        tblshowplu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblshowpluKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblshowplu);
        if (tblshowplu.getColumnModel().getColumnCount() > 0) {
            tblshowplu.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblshowplu.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        bntEatin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntEatin.setText("Eat In");
        bntEatin.setRequestFocusEnabled(false);
        bntEatin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEatinActionPerformed(evt);
            }
        });
        jPanel1.add(bntEatin);

        bntTakeAway.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntTakeAway.setText("Take Away");
        bntTakeAway.setRequestFocusEnabled(false);
        bntTakeAway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntTakeAwayActionPerformed(evt);
            }
        });
        jPanel1.add(bntTakeAway);

        bntDelivery.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntDelivery.setText("Delivery");
        bntDelivery.setRequestFocusEnabled(false);
        bntDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDeliveryActionPerformed(evt);
            }
        });
        jPanel1.add(bntDelivery);

        bntPinto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntPinto.setText("Pinto");
        bntPinto.setRequestFocusEnabled(false);
        bntPinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPintoActionPerformed(evt);
            }
        });
        jPanel1.add(bntPinto);

        bntWholeSale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntWholeSale.setText("Whole Sale");
        bntWholeSale.setRequestFocusEnabled(false);
        bntWholeSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntWholeSaleActionPerformed(evt);
            }
        });
        jPanel1.add(bntWholeSale);

        bntCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntCancel.setText("ESC ปิดหน้าจอ");
        bntCancel.setRequestFocusEnabled(false);
        bntCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelActionPerformed(evt);
            }
        });

        ChkAllType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ChkAllType.setText("F1-เปลี่ยนทุกรายการ");
        ChkAllType.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(20, 30, 115));
        jLabel1.setText("เลือก Type การขายที่ต้องการเปลี่ยน");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ChkAllType)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntCancel)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChkAllType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        setSize(new java.awt.Dimension(833, 528));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelActionPerformed
    bntCancelClick();
}//GEN-LAST:event_bntCancelActionPerformed

private void bntEatinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEatinActionPerformed
    bntEatinClick();
}//GEN-LAST:event_bntEatinActionPerformed

private void bntTakeAwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntTakeAwayActionPerformed
    bntTakeAwayClick();
}//GEN-LAST:event_bntTakeAwayActionPerformed

private void bntDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDeliveryActionPerformed
    bntDeliveryClick();
}//GEN-LAST:event_bntDeliveryActionPerformed

private void bntPintoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPintoActionPerformed
    bntPintoClick();
}//GEN-LAST:event_bntPintoActionPerformed

private void bntWholeSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntWholeSaleActionPerformed
    bntWholeSaleClick();
}//GEN-LAST:event_bntWholeSaleActionPerformed

private void tblshowpluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblshowpluKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_E) {
        bntEatinClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_T) {
        bntTakeAwayClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_D) {
        bntDeliveryClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_P) {
        bntPintoClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_W) {
        bntWholeSaleClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        if (ChkAllType.isSelected()) {
            ChkAllType.setSelected(false);
        } else {
            ChkAllType.setSelected(true);
        }
    }
}//GEN-LAST:event_tblshowpluKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ChkAllType;
    private javax.swing.JButton bntCancel;
    private javax.swing.JButton bntDelivery;
    private javax.swing.JButton bntEatin;
    private javax.swing.JButton bntPinto;
    private javax.swing.JButton bntTakeAway;
    private javax.swing.JButton bntWholeSale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblshowplu;
    // End of variables declaration//GEN-END:variables
}
