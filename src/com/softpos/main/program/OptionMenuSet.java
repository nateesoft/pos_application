package com.softpos.main.program;

import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import setupmenu.DlgBrowseProduct;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class OptionMenuSet extends javax.swing.JDialog {

    private String TableNo;
    private DefaultTableModel model;
    private String index;

    public OptionMenuSet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        initTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbOption = new javax.swing.JTable();
        txtOptionName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnFindProduct1 = new javax.swing.JButton();
        btnFindProduct2 = new javax.swing.JButton();
        txtPCode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPDesc = new javax.swing.JTextField();
        btnFindProduct = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายการ Menu Set");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("ปิดหน้าต่าง (Close)");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tbOption.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสินค้า", "ชื่อสินค้า", "OPTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbOptionMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbOption);

        txtOptionName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtOptionName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOptionNameKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("ชื่อOption");

        btnFindProduct1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFindProduct1.setText("บันทึก");
        btnFindProduct1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFindProduct1MouseClicked(evt);
            }
        });
        btnFindProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProduct1ActionPerformed(evt);
            }
        });
        btnFindProduct1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFindProduct1KeyPressed(evt);
            }
        });

        btnFindProduct2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFindProduct2.setText("ลบ");
        btnFindProduct2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFindProduct2MouseClicked(evt);
            }
        });
        btnFindProduct2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProduct2ActionPerformed(evt);
            }
        });
        btnFindProduct2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFindProduct2KeyPressed(evt);
            }
        });

        txtPCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCodeKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("รหัสสินค้า");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("ชื่อสินค้า");

        txtPDesc.setEditable(false);
        txtPDesc.setBackground(new java.awt.Color(255, 255, 255));
        txtPDesc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnFindProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFindProduct.setText("ค้นหา");
        btnFindProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFindProductMouseClicked(evt);
            }
        });
        btnFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProductActionPerformed(evt);
            }
        });
        btnFindProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFindProductKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(2, 2, 2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtPDesc)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txtOptionName, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFindProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFindProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel14)
                        .addComponent(jScrollPane3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtOptionName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("รายการ Option สินค้า");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnFindProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindProductMouseClicked

    private void btnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProductActionPerformed
        DlgBrowseProduct browse = new DlgBrowseProduct(null, true);
        browse.setVisible(true);
        if (browse.getSelectPlu() != null) {
            txtPCode.setText(browse.getSelectPlu().getCode());
            txtPDesc.setText(browse.getSelectPlu().getName());
        }
        LoadOpt();
    }//GEN-LAST:event_btnFindProductActionPerformed

    private void btnFindProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFindProductKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindProductKeyPressed

    private void btnFindProduct1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFindProduct1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindProduct1KeyPressed

    private void btnFindProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProduct1ActionPerformed
        String pcode = txtPCode.getText();
        String pdesc = txtPDesc.getText();
        String opcode = "";
        String opname = txtOptionName.getText().trim();

        if (!opname.equals("")) {
            Save(pcode, pdesc, opcode, opname);
        }
    }//GEN-LAST:event_btnFindProduct1ActionPerformed

    private void btnFindProduct1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindProduct1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindProduct1MouseClicked

    private void btnFindProduct2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFindProduct2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindProduct2MouseClicked

    private void btnFindProduct2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProduct2ActionPerformed
        int row = tbOption.getSelectedRow();
        if (row != -1) {
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                String sql = "delete from optionset "
                        + "where pcode='" + txtPCode.getText() + "' "
                        + "and optionname='" + ThaiUtil.Unicode2ASCII(txtOptionName.getText()) + "'";
                Statement stmt = mysql.getConnection().createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            } finally {
                mysql.close();
            }

            txtOptionName.setText("");
            LoadOpt();
        }
    }//GEN-LAST:event_btnFindProduct2ActionPerformed

    private void btnFindProduct2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFindProduct2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindProduct2KeyPressed

    private void tbOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOptionMouseClicked
        int row = tbOption.getSelectedRow();
        String value = tbOption.getValueAt(row, 2).toString();
        txtOptionName.setText(value);
    }//GEN-LAST:event_tbOptionMouseClicked

    private void txtOptionNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOptionNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnFindProduct1.requestFocus();
        }
    }//GEN-LAST:event_txtOptionNameKeyPressed

    private void txtPCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCodeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            LoadOpt();
        }
    }//GEN-LAST:event_txtPCodeKeyPressed

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
            java.util.logging.Logger.getLogger(OptionMenuSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OptionMenuSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OptionMenuSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OptionMenuSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OptionMenuSet dialog = new OptionMenuSet(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnFindProduct1;
    private javax.swing.JButton btnFindProduct2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbOption;
    private javax.swing.JTextField txtOptionName;
    private javax.swing.JTextField txtPCode;
    private javax.swing.JTextField txtPDesc;
    // End of variables declaration//GEN-END:variables

//    private void CheckOpt(String pcode, String pdesc, String opcode, String opname) {
//        try {
//            String sql = "select * from optionset where PCode = '" + pcode + "'";
//            ResultSet rs = MySQLConnect.getResultSet(sql);
//            if (rs.next()) {
//                Update(pcode,pdesc,opcode,opname);
//            } else {
//                Save(pcode,pdesc,opcode,opname);
//            }
//            rs.close();
//        } catch (Exception e) {
//            MSG.WAR(e.getMessage());
//        }
//
//    }
//    private void Update(String pcode, String pdesc, String opcode, String opname) {
//        try {
//            String sql = "UPDATE optionmenuset SET "
//                    + "Opt1= '" + opt1 + "', Opt2= '" + opt2 + "', Opt3= '" + opt3 + "', Opt4= '" + opt4 + "', "
//                    + "Opt5= '" + opt5 + "', Opt6= '" + opt6 + "', Opt7= '" + opt7 + "', Opt8= '" + opt8 + "' "
//                    + "WHERE PCode='" + pcode + "'";
//
//            MySQLConnect.exeUpdate(sql);
//            LoadOpt();
//
//        } catch (Exception e) {
//            MSG.ERR(null, e.getMessage());
//        }
//    }
    private void Save(String pcode, String pdesc, String opcode, String opname) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "INSERT INTO optionset "
                    + "(PCode, PDesc, OptionCode, OptionName) "
                    + "VALUES ('" + pcode + "', "
                    + "'" + ThaiUtil.Unicode2ASCII(pdesc) + "', "
                    + "'" + opcode + "', "
                    + "'" + ThaiUtil.Unicode2ASCII(opname) + "');";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            LoadOpt();

            txtOptionName.setText("");
            txtOptionName.requestFocus();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void LoadOpt() {
        DefaultTableModel md = (DefaultTableModel) tbOption.getModel();
        tbOption.setRowHeight(35);
        int size = md.getRowCount();
        for (int i = 0; i < size; i++) {
            md.removeRow(0);
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from optionset "
                    + "where PCode = '" + txtPCode.getText() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String PC = ThaiUtil.ASCII2Unicode(rs.getString("PCode"));
                String PD = ThaiUtil.ASCII2Unicode(rs.getString("PDesc"));
                txtPDesc.setText(PD);
                //String OC = ThaiUtil.ASCII2Unicode(rs.getString("OptionCode"));
                String ON = ThaiUtil.ASCII2Unicode(rs.getString("OptionName"));

                md.addRow(new Object[]{PC, PD, ON});

                tbOption.setToolTipText("");

            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        }finally{
            mysql.close();
        }

    }

    private void initTable() {
        tbOption.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JTableHeader tHeader = tbOption.getTableHeader();
        tHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tbOption.setRowHeight(30);
    }

}
