package com.softpos.main.program;

import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class FindProduct extends javax.swing.JDialog {

    private String PCode = "";

    public FindProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        loadProductAll();
    }

    public String getPCode() {
        return PCode;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        chkProductExpire = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProduct = new javax.swing.JTable();
        lbTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ค้นหาสินค้า");
        setUndecorated(true);

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFind.setText("ค้นหา");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        chkProductExpire.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkProductExpire.setText("ค้นหาสินค้าที่ไม่มีการใช้งาน");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("รหัส/ชื่อ");

        tbProduct.setAutoCreateRowSorter(true);
        tbProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PCode", "PDesc", "PUnit1", "PPrice11", "PGroup", "GroupName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProduct.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductMouseClicked(evt);
            }
        });
        tbProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProductKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbProduct);
        if (tbProduct.getColumnModel().getColumnCount() > 0) {
            tbProduct.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbProduct.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbProduct.getColumnModel().getColumn(5).setPreferredWidth(250);
        }

        lbTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTotal.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(chkProductExpire)
                        .addGap(170, 170, 170))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTotal)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkProductExpire)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotal)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        loadProductAll();
    }//GEN-LAST:event_btnFindActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadProductAll();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            PCode = "";
            dispose();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tbProduct.requestFocus();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tbProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tbProduct.getSelectedRow();
            if (row != -1) {
                PCode = tbProduct.getValueAt(row, 0).toString();
                dispose();
            } else {
                PCode = "";
            }
        }
    }//GEN-LAST:event_tbProductMouseClicked

    private void tbProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = tbProduct.getSelectedRow();
            if (row != -1) {
                PCode = tbProduct.getValueAt(row, 0).toString();
                dispose();
            } else {
                PCode = "";
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            PCode = "";
            dispose();
        }
    }//GEN-LAST:event_tbProductKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
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
            java.util.logging.Logger.getLogger(FindProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FindProduct dialog = new FindProduct(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFind;
    private javax.swing.JCheckBox chkProductExpire;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tbProduct;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void loadProductAll() {
        DefaultTableModel model = (DefaultTableModel) tbProduct.getModel();
        tbProduct.setRowHeight(35);
        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }
        JTableHeader tHeader = tbProduct.getTableHeader();
        tHeader.setFont(new Font("Tahoma", Font.BOLD, 14));

        String sqlPlus = "";
        if (chkProductExpire.isSelected()) {
            sqlPlus = "and PActive in ('Y', 'N') ";
        } else {
            sqlPlus = "and PActive='Y' ";
        }

        String word = ThaiUtil.Unicode2ASCII(txtSearch.getText());
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select PCode, PDesc, PUnit1, PPrice11, PGroup, GroupName "
                    + "from product p,groupfile g "
                    + "where p.pgroup=g.groupcode "
                    + "and (PCode like '%" + word + "%' "
                    + "or PDesc like '%" + word + "%') "
                    + sqlPlus
                    + "order by PCode";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("PCode"),
                    ThaiUtil.ASCII2Unicode(rs.getString("PDesc")),
                    ThaiUtil.ASCII2Unicode(rs.getString("PUnit1")),
                    rs.getDouble("PPrice11"),
                    rs.getString("PGroup"),
                    ThaiUtil.ASCII2Unicode(rs.getString("GroupName"))
                });
            }

            lbTotal.setText("รวมรายการ " + model.getRowCount() + " รายการ");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
    }
}
