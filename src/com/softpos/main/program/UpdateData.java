/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softpos.main.program;

import database.MySQLConnect;
import database.SQLServerConnect;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class UpdateData extends javax.swing.JDialog {

    /**
     * Creates new form UpdateData
     */
    public UpdateData(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblCount.setText("");
        SVStatusCheck();
        LoadData();
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
        btnUpdate = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCommand = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblCount = new javax.swing.JLabel();
        pbCheckUpdate = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        PanelStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("Click Here! to Update Menu");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCommand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Command!"
            }
        ));
        jScrollPane1.setViewportView(tblCommand);

        jPanel3.setBackground(new java.awt.Color(102, 255, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblCount.setText("jLabel1");
        lblCount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCount, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCount, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        pbCheckUpdate.setBackground(new java.awt.Color(255, 153, 153));
        pbCheckUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pbCheckUpdate.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbCheckUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pbCheckUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        PanelStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("STATUS");
        lblStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout PanelStatusLayout = new javax.swing.GroupLayout(PanelStatus);
        PanelStatus.setLayout(PanelStatusLayout);
        PanelStatusLayout.setHorizontalGroup(
            PanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelStatusLayout.setVerticalGroup(
            PanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        btnUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void btnUpdate() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        new Thread(new Runnable() {
            private volatile boolean flag = false;

            public void setFlag(boolean value) {
                flag = value;
            }

            @Override
            @SuppressWarnings({"static-access", "UnusedAssignment"})
            public void run() {
                try {
                    DecimalFormat df = new DecimalFormat("#,###");
                    while (!flag) {
                        try {
                            setFlag(true);
                            DefaultTableModel model = (DefaultTableModel) tblCommand.getModel();
                            ArrayList<Object[]> ListObj = LoadData();
                            try {
                                MySQLConnect c = new MySQLConnect();
                                c.open();
                                if (ListObj != null && ListObj.size() > 0) {
                                    for (int i = 0; i < ListObj.size(); i++) {
                                        String sql = ThaiUtil.ASCII2Unicode(ListObj.get(i)[0].toString());
                                        model.addRow(new Object[]{sql});
                                        try {
                                            c.getConnection().createStatement().executeUpdate(sql);
                                            lblCount.setText("Update Complete >>> " + df.format(i) + " : Item");
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                                c.close();
                            } catch (Exception e) {
                            }
                            setFlag(true);
                        } catch (Exception e) {
                        }
                    }
                    try {
                        Thread.sleep(200);
                        System.out.println("Success View Data");
                        btnUpdate.setEnabled(flag);

                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    pbCheckUpdate.setString("Load data Complete ");
                } catch (Exception e) {
                }
            }
        }).start();

    }

    public ArrayList<Object[]> LoadData() {

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ArrayList<Object[]> ListObj = new ArrayList<>();
        String BType = "-";
        try {
            SQLServerConnect sv = new SQLServerConnect();
            MySQLConnect c = new MySQLConnect();

            c.open();
            String sqlGetBType = "Select btype btype from tranconfig";
            ResultSet rsGetBtype = c.getConnection().createStatement().executeQuery(sqlGetBType);
            if (rsGetBtype.next()) {
                BType = rsGetBtype.getString("btype");
            }
            String sql = "select * from QForBranch where active = 'Y' and btype='" + BType + "';";
            ResultSet rs = SQLServerConnect.conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String sqlUpdate = "";
                sqlUpdate = rs.getString("qforbranch");
                ListObj.add(new Object[]{sqlUpdate});
            }
            sv.close();
            rs.close();
        } catch (Exception e) {
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return ListObj;
    }

    public void SVStatusCheck() {
        try {
            SQLServerConnect SV = new SQLServerConnect();
            String sql = "select * from company";
            ResultSet rs = SV.getResultSet(sql);
            if (rs.next()) {
                lblStatus.setText("Online");
                PanelStatus.setBackground(Color.green);
            }
            rs.close();
            SV.close();
        } catch (Exception e) {
            lblStatus.setText("OFFLINE");
            PanelStatus.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "ไม่สามารถเชื่อมต่อฐานข้อมูลเซิร์ฟเวอร์ได้กรุณาตรวจสอบอินเทอร์เน็ต");
        }
    }

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
            java.util.logging.Logger.getLogger(UpdateData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateData dialog = new UpdateData(new javax.swing.JDialog(), true);
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
    private javax.swing.JPanel PanelStatus;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar pbCheckUpdate;
    private javax.swing.JTable tblCommand;
    // End of variables declaration//GEN-END:variables
}