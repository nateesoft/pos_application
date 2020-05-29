package com.softpos.floorplan;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import database.MySQLConnect;
import java.awt.GraphicsEnvironment;
import java.sql.Statement;
import com.softpos.main.model.PublicVar;
import com.softpos.main.controller.TableFileControl;
import com.softpos.main.model.Value;
import util.MSG;

public class ShowTable extends javax.swing.JDialog {

    DefaultTableModel model2;
    static SimpleDateFormat Datefmtshow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public ShowTable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        PublicVar.ReturnString = "";
        model2 = (DefaultTableModel) ShowTableLogin.getModel();
        ShowTableLogin.setShowGrid(true);
        ShowTableLogin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ShowTableLogin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ShowTableLogin.setRowSelectionAllowed(true);
        ShowTableLogin.setShowGrid(false);
        ShowTableLogin.setGridColor(Color.BLACK);

        JTableHeader header = ShowTableLogin.getTableHeader();
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));

        int[] ColSize = {80, 150, 150, 150, 150, 200, 100, 100, 100};
        for (int i = 0; i < 9; i++) {
            //int vColIndex = 0;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }

        DecimalFormat DoubleFmt = new DecimalFormat("##,###,##0.00");
        DecimalFormat IntegerFmt = new DecimalFormat("##,###,##0");
        DecimalFormat PersentFmt = new DecimalFormat("#,##0.00%");

        TableColumnModel tcm = ShowTableLogin.getColumnModel();

        TableTestFormatRenderer r = new TableTestFormatRenderer(IntegerFmt);

        r.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(3).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(4).setCellRenderer(r);

        r = new TableTestFormatRenderer(DoubleFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(5).setCellRenderer(r);

        LoadDataToGrid();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTableLogin = new javax.swing.JTable();
        bntExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("แสดงรายการ โต็ะที่มีรายการขาย ");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        ShowTableLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ShowTableLogin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ShowTableLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "เบอร์โต๊ะ", "วันที่ (Date)", "เวลาเข้า", "จำนวนลูกค้า", "จำนวนรายการ", "จำนวนเงิน ", "สถานะ", "เช็คบิล", "พิมพ์บิล"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ShowTableLogin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ShowTableLogin.setName("ShowTableLogin"); // NOI18N
        ShowTableLogin.setRequestFocusEnabled(false);
        ShowTableLogin.setRowHeight(35);
        ShowTableLogin.getTableHeader().setReorderingAllowed(false);
        ShowTableLogin.setUpdateSelectionOnSort(false);
        ShowTableLogin.setVerifyInputWhenFocusTarget(false);
        ShowTableLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowTableLoginMouseClicked(evt);
            }
        });
        ShowTableLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShowTableLoginKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ShowTableLogin);
        if (ShowTableLogin.getColumnModel().getColumnCount() > 0) {
            ShowTableLogin.getColumnModel().getColumn(0).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(1).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(2).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(3).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(4).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(5).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(6).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(7).setResizable(false);
            ShowTableLogin.getColumnModel().getColumn(8).setResizable(false);
        }

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("Close");
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(241, 10, 10));
        jLabel1.setText("กดปุ่ม F8 เพื่อเคลียร์สถานะโต๊ะ");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("เลือกโต๊ะ (F5)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1019, 651));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void ShowTableLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShowTableLoginKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_F8) {
        int maxrow;
        int currow = 0;
        String TableSelected = "";
        maxrow = ShowTableLogin.getRowCount();
        if (maxrow > 0) {
            for (int i = 0; i < maxrow; i++) {
                if (ShowTableLogin.isRowSelected(i)) {
                    currow = i;
                }
            }
            TableSelected = ShowTableLogin.getValueAt(currow, 0).toString();
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String QryUpdatetable = "update tablefile set TonAct='N' "
                        + "where (TCode='" + TableSelected + "')";
                stmt.executeUpdate(QryUpdatetable);
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            } finally {
                mysql.close();
            }
            LoadDataToGrid();
        }

    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        PublicVar.ReturnString = "";
        this.dispose();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        LoadDataToGrid();
        int row = ShowTableLogin.getSelectedRow();
        Value.TableSelected = "";
        if (row != -1) {
            String tableNo = ShowTableLogin.getValueAt(row, 0).toString();
            if (!tableNo.equals("")) {
                Value.TableSelected = tableNo;
                TableFileControl tfCont = new TableFileControl();
                if (tfCont.checkTableOpened(tableNo)) {
                    MSG.WAR("มีพนักงานกำลังใช้งานโต๊ะนี้อยู่ !!!");
                    Value.TableSelected = "";
                } else {
                    dispose();
                }
            }
        }
    }
    LoadDataToGrid();
}//GEN-LAST:event_ShowTableLoginKeyPressed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    ShowTableLogin.requestFocus();
}//GEN-LAST:event_formWindowActivated

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        PublicVar.ReturnString = "";
        Value.TableSelected = "";
        this.dispose();
    }//GEN-LAST:event_bntExitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = ShowTableLogin.getSelectedRow();
        Value.TableSelected = "";
        if (row != -1) {
            String tableNo = ShowTableLogin.getValueAt(row, 0).toString();
            if (!tableNo.equals("")) {
                Value.TableSelected = tableNo;
                TableFileControl tfCont = new TableFileControl();
                if (tfCont.checkTableOpened(tableNo)) {
                    MSG.WAR("มีพนักงานกำลังใช้งานโต๊ะนี้อยู่ !!!");
                    Value.TableSelected = "";
                } else {
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ShowTableLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowTableLoginMouseClicked
        if (evt.getClickCount() == 2) {
            jButton1ActionPerformed(null);
        } else if (evt.getClickCount() == 1) {
            int row = ShowTableLogin.getSelectedRow();
            Value.TableSelected = "";
            if (row != -1) {
                String tableNo = ShowTableLogin.getValueAt(row, 0).toString();
                if (!tableNo.equals("")) {
                    Value.TableSelected = tableNo;
                    TableFileControl tfCont = new TableFileControl();
                    if (tfCont.checkTableOpened(tableNo)) {
                        MSG.WAR("มีพนักงานกำลังใช้งานโต๊ะนี้อยู่ !!!");
                        Value.TableSelected = "";
                    } else {
                        dispose();
                    }
                }
            }
        }
    }//GEN-LAST:event_ShowTableLoginMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ShowTable dialog = new ShowTable(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void LoadDataToGrid() {
        //ให้โปรแกรมคำนวณใหม่อีกครั้งก่อนแสดงข้อมูลในตาราง

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
//            String sql = "select * from tablefile order by tcode";
            String sql = "select tcode from tablefile order by tcode";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                BalanceControl.updateProSerTable(rs.getString("tcode"), null);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        mysql.open();
        try {
//            String LoadTableFile = "select * from tablefile "
//                    + "where tonact='Y' "
//                    + "or TAmount>0 "
//                    + "or TItem > 0 "
//                    + "or Tcustomer > 0 "
//                    + "order by tcurtime";
            String LoadTableFile = "select Tcode, Tlogindate, TCurTime, TCustomer, TItem, TAmount,"
                    + "TOnAct, ChkBill, PrintChkBill"
                    + " from tablefile "
                    + "where tonact='Y' "
                    + "or TAmount>0 "
                    + "or TItem > 0 "
                    + "or Tcustomer > 0 "
                    + "order by tcurtime";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(LoadTableFile);

            int RowCount = model2.getRowCount();
            for (int i = 0; i < RowCount; i++) {
                model2.removeRow(0);
            }
            while (rs.next()) {
                Object[] input = {
                    rs.getString("Tcode"),
                    rs.getDate("Tlogindate"),
                    rs.getString("TCurTime"),
                    rs.getFloat("TCustomer"),
                    rs.getFloat("TItem"),
                    rs.getFloat("TAmount"),
                    rs.getString("TOnAct"),
                    rs.getString("ChkBill"),
                    rs.getString("PrintChkBill")
                };
                model2.addRow(input);
            }

            showCell(0, 0);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = ShowTableLogin.getCellRect(row, column, true);
            ShowTableLogin.scrollRectToVisible(rect);
            ShowTableLogin.clearSelection();
            ShowTableLogin.setRowSelectionInterval(row, row);
        }
    }

    public void GetSelectedRow() {
        int maxrow;
        int currow = 0;
        String TableSelected = "";
        maxrow = ShowTableLogin.getRowCount();
        if (maxrow > 0) {
            for (int i = 0; i < maxrow; i++) {
                if (ShowTableLogin.isRowSelected(i)) {
                    currow = i;
                }
            }
            TableSelected = ShowTableLogin.getValueAt(currow, 0).toString();
            PublicVar.ReturnString = TableSelected;
        } else {
            PublicVar.ReturnString = "";

        }
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ShowTableLogin;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public class TableTestFormatRenderer extends DefaultTableCellRenderer {

        private Format formatter;

        public TableTestFormatRenderer(Format formatter) {
            if (formatter == null) {
                throw new NullPointerException();
            }
            this.formatter = formatter;
        }

        @Override
        protected void setValue(Object obj) {
            setText(obj == null ? "" : formatter.format(obj));
        }
    }
}
