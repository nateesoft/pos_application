package com.softpos.main.program;

import com.softpos.main.model.BalanceBean;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class MenuSetChoice extends javax.swing.JDialog {

    private String TableNo;
    private String PCode;
    private DefaultTableModel model;
    private String index;
    private BalanceBean bean;

    public MenuSetChoice(java.awt.Frame parent, boolean modal, String PCode, String ETD, String TableNo) {
        super(parent, modal);
        initComponents();

        this.index = index;
        this.TableNo = TableNo;
        this.PCode = PCode;

        LoadPSet(PCode, ETD, TableNo);

    }

    private MenuSetChoice(JFrame jFrame, boolean b) {
        return;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMenuSet = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายการ Menu Set");
        setUndecorated(true);

        tbMenuSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัส Menu Set", "รายการ Menu Set", "ราคา"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMenuSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMenuSetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbMenuSetMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbMenuSet);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );

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

    private void tbMenuSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMenuSetMouseClicked

        if (evt.getClickCount() == 2) {  //จำนวนครั้งที่คลิกต้องการโชว์
            int row = tbMenuSet.getSelectedRow();
            //int Allrow = TableSet2.getRowCount();
            if (row != -1) {

                String subcode = "" + tbMenuSet.getValueAt(row, 0);  //เมื่อต้องการโชว์ค่าที่ txtUser
                String name = "" + tbMenuSet.getValueAt(row, 1);
                String price = "" + tbMenuSet.getValueAt(row, 2);//เมื่อต้องการโชว์ค่าที่ txtpass

                //MoveTable(subcode, name, price);
                //CheckOder1(Allrow);
                CheckPset(subcode, name, price);

                //                txtcode1.setText(code);
                //                txtName1.setText(name);
                //                if (!txtcode1.getText().equals("") || txtcode1.getText().equals(code)) {
                //                    txtcode2.setText(code);
                //                    txtName2.setText(name);
                //                }if (!txtcode2.getText().equals("")) {
                //                    txtcode3.setText(code);
                //                    txtName3.setText(name);
                //                }
            }

        }

    }//GEN-LAST:event_tbMenuSetMouseClicked

    private void tbMenuSetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMenuSetMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMenuSetMouseEntered

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
            java.util.logging.Logger.getLogger(MenuSetChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuSetChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuSetChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuSetChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuSetChoice dialog = new MenuSetChoice(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMenuSet;
    // End of variables declaration//GEN-END:variables

    private void LoadPSet(String PSubCode, String etd, String tableNo) {

        DefaultTableModel model = (DefaultTableModel) tbMenuSet.getModel();
        tbMenuSet.setRowHeight(35);
        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }
        if (etd.equals("E")) {
            etd = "PPrice11";
        }
        if (etd.equals("T")) {
            etd = "PPrice12";
        }
        if (etd.equals("D")) {
            etd = "PPrice13";
        }
        if (etd.equals("P")) {
            etd = "PPrice14";
        }
        if (etd.equals("W")) {
            etd = "PPrice15";
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql1 = "select * from pset "
                    + "where pcode ='" + PSubCode + "';";
            Statement stmt1 = mysql.getConnection().createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);

            while (rs1.next()) {
                String sc = rs1.getString("PSubcode");

                String sql2 = "select PCode, PDesc, " + etd + " from product "
                        + "where pcode ='" + sc + "';";
                Statement stmt2 = mysql.getConnection().createStatement();
                ResultSet rs2 = stmt2.executeQuery(sql2);

                while (rs2.next()) {

                    String code = rs2.getString("PCode");
                    System.out.println(code);
                    String pdesc = ThaiUtil.ASCII2Unicode(rs2.getString("PDesc"));
                    System.out.println(pdesc);
                    String pprice = rs2.getString(etd);
                    System.out.println(pprice);

                    model.addRow(new Object[]{code, pdesc, pprice});

                }

                rs2.close();
                stmt2.close();
            }

            rs1.close();
            stmt1.close();
            //rs2.close();
            tbMenuSet.setToolTipText("");

        } catch (Exception e) {
            //MSG.ERR(this, e.getMessage());
        }
    }

    private String CheckPset(String subcode, String name, String price) {
        String index = TableNo + "/001";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select max(PIndex) PIndex "
                    + "from temppset "
                    + "where PTable = '" + TableNo + "' "
                    + "order by PIndex";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String PIndex;
            boolean notfound = false;
            while (rs.next()) {
                notfound = true;
                PIndex = rs.getString("PIndex");

                if (PIndex == null) {
                    break;
                }

                String[] data = PIndex.split("/");
                int id;
                try {
                    id = Integer.parseInt(data[1]);
                    id = id + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }

                if (id < 10) {
                    index = TableNo + "/00" + id;
                } else if (id < 100) {
                    index = TableNo + "/0" + id;
                } else if (id < 1000) {
                    index = TableNo + "/" + id;
                }
            }
            rs.close();
            stmt.close();
            if (!notfound) {
                //not found data
                index = TableNo + "/001";
            }
        } catch (Exception e) {
            MSG.WAR(e.getMessage());
            index = TableNo + "/001";
        }
        CheckPset(subcode, name, price, index);
        return index;

    }

    private void CheckPset(String subcode, String name, String price, String index) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql2 = "select PSubQty from pset"
                    + " where psubcode = '" + subcode + "'"
                    + " group by pcode;";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs2 = stmt.executeQuery(sql2);

            if (rs2.next() || !rs2.next()) {
                double qty = rs2.getFloat("PSubQty");
                System.out.println(qty);

                SavePSet(subcode, name, price, index, qty);
                CheckOption(subcode, index);
            }
            rs2.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.WAR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void SavePSet(String subcode, String name, String price, String index, double qty) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql1 = "select PStock from stcard,product "
                    + "where pcode = '" + subcode + "';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs1 = stmt.executeQuery(sql1);

            if (rs1.next()) {

                String pstock = rs1.getString("PStock");
                System.out.println(pstock);

                String sql = "INSERT INTO temppset "
                        + "(PIndex, PTable, PCode, PQty, PSubCode, TryPro, PSubQty, "
                        + "PSubTotalQty, PStock, PVoid, PStkCode, PSubPrice, PSubName) "
                        + "VALUES ('" + index + "', '" + TableNo + "', '" + PCode + "', '1',"
                        + " '" + subcode + "', 'S'," + qty + ", " + qty + ", '" + pstock + "',"
                        + " '-', '', " + price + ", '" + ThaiUtil.Unicode2ASCII(name) + "');";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sql);
                stmt1.close();
            }

            rs1.close();
            stmt.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        dispose();
    }

    private void CheckOption(String subcode, String index) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "SELECT OptSet FROM soft_menusetup "
                    + "where PCode = '" + subcode + "';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String Opt = rs.getString("OptSet");
                if (Opt != null) {
                    if (Opt.equals("Y")) {
                        
                    }
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
    }
}
