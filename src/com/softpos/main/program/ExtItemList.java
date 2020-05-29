package com.softpos.main.program;

import database.MySQLConnect;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import com.softpos.main.model.CustomerBean;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class ExtItemList extends javax.swing.JDialog {

    public static String data;

    public ExtItemList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        loadData();
        data = "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        chkByCode = new javax.swing.JRadioButton();
        chkByName = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCustomer = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("แสดงรายการลูกหนี้การค้า");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(chkByCode);
        chkByCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkByCode.setText("F1 - เรียงตามรหัสลูกค้า");

        buttonGroup1.add(chkByName);
        chkByName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkByName.setSelected(true);
        chkByName.setText("F2 - เรียงตามชื่อลูกค้า");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkByCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkByName)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkByCode)
                    .addComponent(chkByName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("เบอร์โทรศัพท์");

        txtTel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Enter - เลือก");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("ESC - ยกเลิก");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tbCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัส", "ชื่อลูกหนี้/บริษัท", "โทรศัพท์", "ผู้ติดต่อ", "ที่อยุ่", "ที่อยู่", "รหัสไปรษณี", "โทรสาร", "หมายเหตุ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCustomer);
        if (tbCustomer.getColumnModel().getColumnCount() > 0) {
            tbCustomer.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbCustomer.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbCustomer.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbCustomer.getColumnModel().getColumn(3).setPreferredWidth(50);
            tbCustomer.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbCustomer.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbCustomer.getColumnModel().getColumn(6).setPreferredWidth(75);
            tbCustomer.getColumnModel().getColumn(7).setPreferredWidth(75);
            tbCustomer.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Delete - ลบข้อมูล");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(55, 55, 55)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = tbCustomer.getSelectedRow();
        if (row != -1) {
            data = tbCustomer.getValueAt(row, 0).toString();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = tbCustomer.getSelectedRow();
        if (row != -1) {
            String code = tbCustomer.getValueAt(row, 0).toString();
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                String sql = "delete from customer where sp_code='" + code + "'";
                Statement stmt = mysql.getConnection().createStatement();
                if (stmt.executeUpdate(sql) > 0) {
                    MSG.ERR(this, "ลบข้อมูลเรียบร้อยแล้ว");
                }
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(this, e.getMessage());
            } finally {
                mysql.close();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ExtItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExtItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExtItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExtItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExtItemList dialog = new ExtItemList(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton chkByCode;
    private javax.swing.JRadioButton chkByName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCustomer;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            DefaultTableModel model = (DefaultTableModel) tbCustomer.getModel();
            int size = model.getRowCount();
            for (int i = 0; i < size; i++) {
                model.removeRow(0);
            }
            String sql = "select * from customer";
            if (!txtTel.getText().trim().equals("")) {
                sql += " where tel='" + txtTel.getText() + "' ";
            }
            if (chkByCode.isSelected()) {
                sql += " order by sp_code";
            } else {
                sql += " order by sp_desc";
            }

            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CustomerBean bean = new CustomerBean();
                bean.setSp_code(rs.getString("sp_code"));
                bean.setSp_Desc(ThaiUtil.ASCII2Unicode(rs.getString("sp_Desc")));
                bean.setSp_Addr1(ThaiUtil.ASCII2Unicode(rs.getString("sp_Addr1")));
                bean.setSp_Addr2(ThaiUtil.ASCII2Unicode(rs.getString("sp_Addr2")));
                bean.setSp_zip(rs.getString("sp_zip"));
                bean.setTel(rs.getString("tel"));
                bean.setFax(rs.getString("fax"));
                bean.setContack(ThaiUtil.ASCII2Unicode(rs.getString("Contack")));
                bean.setRemark(ThaiUtil.ASCII2Unicode(rs.getString("Remark")));
                bean.setRemark2(ThaiUtil.ASCII2Unicode(rs.getString("Remark2")));
                //bean.setTaxid(rs.getString("Taxid"));
                //bean.setCustBranch(rs.getString("CustBranch"));

                model.addRow(new Object[]{
                    bean.getSp_code(),
                    bean.getSp_Desc(),
                    bean.getTel(),
                    bean.getContack(),
                    bean.getSp_Addr1(),
                    bean.getSp_Addr2(),
                    bean.getSp_zip(),
                    bean.getFax(),
                    bean.getRemark()
                });

                tbCustomer.setRowHeight(30);
                tbCustomer.setFont(new Font("Norasi", Font.PLAIN, 14));
            }

            rs.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        } finally {
            mysql.close();
        }
    }
}
