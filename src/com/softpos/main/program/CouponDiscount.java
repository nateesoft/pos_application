package com.softpos.main.program;

import com.softpos.cupon.CuponBean;
import com.softpos.cupon.CuponControl;
import com.softpos.cupon.CuponListControl;
import com.softpos.cupon.CuponlistBean;
import com.softpos.main.controller.TempCuponController;
import com.softpos.main.model.TempCuponBean;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import database.MySQLConnect;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.JTableHeader;
import soft.virtual.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class CouponDiscount extends javax.swing.JDialog {

    private DefaultTableModel model2;
    private SimpleDateFormat Datefmtshow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    private SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm");
    private POSConfigSetup CONFIG;
    private String tableNo;
    private double cuponDiscAmt = 0;
    private CuponBean cuponBean = null;
    SimpleDateFormat simp = new SimpleDateFormat("EE", Locale.ENGLISH);
    private String EE = "";
    private String Member1;
    private String Member2;
    private double totalAmount;

    public CouponDiscount(java.awt.Frame parent, boolean modal, String tableNo, String Member1, String Member2, double totalAmount) {

        super(parent, modal);
        initComponents();
        //LoadDataToGrid();
        EE = simp.format(new Date());
        this.tableNo = tableNo;
        this.Member1 = Member1;
        this.Member2 = Member2;
        this.totalAmount = totalAmount;

        CONFIG = POSConfigSetup.Bean();

        initComponents();

        PublicVar.ReturnString = "";
        model2 = (DefaultTableModel) ShowTable.getModel();
        ShowTable.setShowGrid(true);
        ShowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ShowTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ShowTable.setRowSelectionAllowed(true);
        ShowTable.setGridColor(Color.gray);

        JTableHeader header = ShowTable.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));

//        int[] ColSize = {40, 80, 260};
//        for (int i = 0; i < 3; i++) {
//            //int vColIndex = 0;
//            TableColumn col = ShowTable.getColumnModel().getColumn(i);
//            col.setPreferredWidth(ColSize[i]);
//        }
        ShowTable.requestFocus();
        loadDataToGrid();
    }

    public CuponBean getCuponBean() {
        return cuponBean;
    }

    public double getCuponDiscount() {
        return cuponDiscAmt;
    }

    public CouponDiscount() {
        initComponents();
        PublicVar.ReturnString = "";
        model2 = (DefaultTableModel) ShowTable.getModel();
        ShowTable.setShowGrid(true);
        ShowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ShowTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ShowTable.setRowSelectionAllowed(true);
        ShowTable.setGridColor(Color.gray);

        JTableHeader header = ShowTable.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));

        loadDataToGrid2();
        loadDataToGrid();
        txtCucode.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCuQty = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCucode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cupon List");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntOK.setText("F5 ตกลง (OK)");
        bntOK.setRequestFocusEnabled(false);
        bntOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bntOKMouseClicked(evt);
            }
        });
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });
        bntOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bntOKKeyPressed(evt);
            }
        });

        bntExit.setBackground(new java.awt.Color(204, 0, 51));
        bntExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntExit.setText("ออก (Close)");
        bntExit.setRequestFocusEnabled(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        ShowTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ShowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Code", "Description/รายการ", "   Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ShowTable.setRowHeight(35);
        ShowTable.setUpdateSelectionOnSort(false);
        ShowTable.setVerifyInputWhenFocusTarget(false);
        ShowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowTableMouseClicked(evt);
            }
        });
        ShowTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShowTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ShowTable);
        if (ShowTable.getColumnModel().getColumnCount() > 0) {
            ShowTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("จำนวนคูปอง");

        txtCuQty.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCuQty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuQty.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCuQty.setRequestFocusEnabled(false);
        txtCuQty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCuQtyMouseClicked(evt);
            }
        });
        txtCuQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuQtyKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("รหัสคูปอง");

        txtCucode.setEditable(false);
        txtCucode.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCucode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCucodeActionPerformed(evt);
            }
        });
        txtCucode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCucodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bntOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntExit))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCucode, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuQty, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCuQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtCucode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(549, 332));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    cuponBean = null;
    dispose();
}//GEN-LAST:event_bntExitActionPerformed

private void txtCuQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuQtyKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    } else {
        int row = ShowTable.getSelectedRow();
        String CuQty = txtCuQty.getText();

        if (!CuQty.equals("")) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                ShowTable.setValueAt(CuQty, row, 3);
                bntOK.requestFocus();
            }
        }
    }
}//GEN-LAST:event_txtCuQtyKeyPressed

private void ShowTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShowTableKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {

    }
}//GEN-LAST:event_ShowTableKeyPressed

private void txtCucodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCucodeKeyPressed
    // TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (txtCucode.getText().length() > 0) {

        }
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {

    }
}//GEN-LAST:event_txtCucodeKeyPressed

    private void ShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowTableMouseClicked
        int row = ShowTable.getSelectedRow();
        if (row != -1) {
            txtCucode.setText(ShowTable.getValueAt(row, 1).toString());
            txtCuQty.requestFocus();
        }
    }//GEN-LAST:event_ShowTableMouseClicked

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        if (!txtCucode.getText().equals("") || txtCucode.getText().equals(null)) {
            process();
        } else {
            dispose();
        }
    }//GEN-LAST:event_bntOKActionPerformed

    private void bntOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bntOKKeyPressed
        dispose();
    }//GEN-LAST:event_bntOKKeyPressed

    private void bntOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntOKMouseClicked
        cuponDiscAmt = 0;
        for (int i = 0; i < model2.getRowCount(); i++) {
            double total = Double.parseDouble(model2.getValueAt(i, 3).toString().replace(",", ""));
            cuponDiscAmt += total;
        }

        cuponBean = new CuponBean();
        cuponBean.setCuCode(txtCucode.getText());

        dispose();
    }//GEN-LAST:event_bntOKMouseClicked

    private void txtCucodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCucodeActionPerformed

    }//GEN-LAST:event_txtCucodeActionPerformed

    private void txtCuQtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCuQtyMouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtCuQty, 4);
        }
    }//GEN-LAST:event_txtCuQtyMouseClicked

    public void bntExitClick() {
        this.dispose();
    }

    private void loadDataToGrid() {
        DefaultTableModel model = (DefaultTableModel) ShowTable.getModel();
        ShowTable.setRowHeight(35);
        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from cupon "
                    + "where (curdate()>=cubegin) and (curdate()<=cuend) "
                    + "and (cutype is not null) and (cucode is not null) "
                    + "and CuStrDay like '%" + EE + "%' "
                    + "and ChkMember='N' "
                    + "order by cutype,cucode";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rec = stmt.executeQuery(sql);
            while (rec.next()) {
                Object[] row = new Object[ShowTable.getColumnCount()];
                row[0] = rec.getString("cutype");
                row[1] = rec.getString("cucode");
                row[2] = ThaiUtil.ASCII2Unicode(rec.getString("cuname"));
                row[3] = getCountTable("" + row[1]);

                model.addRow(row);
            }
            rec.close();
            stmt.close();

            ShowTable.setToolTipText("");
            //SelectRow();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

    }

    public void AssignGrid(CouponRec[] CuArray) {
        int RowCount = model2.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model2.removeRow(0);
        }
        int CuponSize = CuArray.length;
        for (int i = 0; i < CuponSize; i++) {
            Object[] input = {
                "",
                "",
                CuArray[i].CuName,
                CuArray[i].CuQty
            };
            model2.addRow(input);
        }
        showCell(0, 0);
        ShowTable.setRequestFocusEnabled(true);
        txtCucode.requestFocus(true);
    }

    private void loadDataToGrid2() {
        String CuDate = Datefmt.format(new Date());
        SimpleDateFormat DayFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
        String CurDay = DayFormat.format(new Date());
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from cupon "
                    + "where ('" + CuDate + "'>=cubegin) "
                    + "and ('" + CuDate + "'<=cuend) "
                    + "and (cutype is not null) "
                    + "and (cucode is not null) "
                    + "order by cutype,cucode";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            int Cnt = 0;
            if (rec.getRow() == 0) {
            } else {
                do {
                    CouponRec CuRec = new CouponRec();
                    if ((rec.getString("custrday").indexOf(CurDay) >= 0)) {

                        CuRec.CuType = rec.getString("cutype");
                        CuRec.CuCode = rec.getString("cucode");
                        CuRec.CuName = rec.getString("cuname");
                        CuRec.CuDisc = rec.getDouble("cudisc");
                        CuRec.CuDiscBath = rec.getDouble("cudiscbath");
                        CuRec.ChkMember = rec.getString("chkmember");
                        CuRec.CuQty = SeekTempCoupon(rec.getString("cucode"));
                        CuRec.SMS_Code = SeekSMS_Code(rec.getString("cucode"));
                        CuRec.M_Code = SeekM_Code(rec.getString("cucode"));
//                        CuRec.CuSaleType = rec.getString("custrtype");
//                        CuRec.SaleMin = rec.getDouble("cuminsale");
                        if (CuRec.ChkMember.equals("Y")) {
                            if (!PublicVar.TableRec_MemCode.equals("")) {
                                Cnt++;
                            }
                        } else {
                            Cnt++;
                        }
                    }

                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

    }

    public void AssignGrid2(CouponRec[] CuArray) {
        int RowCount = model2.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model2.removeRow(0);
        }
        int CuponSize = CuArray.length;
        for (int i = 0; i < CuponSize; i++) {
            Object[] input = {
                "",
                "",
                CuArray[i].CuName,
                CuArray[i].CuQty
            };
            model2.addRow(input);
        }

        showCell(0, 0);
    }

    public int SeekTempCoupon(String CuCode) {
        int RetValue = 0;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from tempcupon "
                    + "where (r_table='" + tableNo + "') "
                    + "and (cucode='" + CuCode + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetValue = rec.getInt("cuquan");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return RetValue;
    }

    public String SeekSMS_Code(String CuCode) {
        String RetValue = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from tempcupon "
                    + "where (r_table='" + tableNo + "') "
                    + "and (cucode='" + CuCode + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetValue = rec.getString("sms_code");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        if (RetValue == null) {
            RetValue = "";
        }
        return RetValue;
    }

    public String SeekM_Code(String CuCode) {
        String RetValue = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from tempcupon "
                    + "where (r_table='" + tableNo + "') "
                    + "and (cucode='" + CuCode + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetValue = rec.getString("m_code");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        if (RetValue == null) {
            RetValue = "";
        }
        return RetValue;
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = ShowTable.getCellRect(row, column, true);
            ShowTable.scrollRectToVisible(rect);
            ShowTable.clearSelection();
            ShowTable.setRowSelectionInterval(row, row);
        }
    }

    public int GetSelectedRowQty() {
        int maxrow;
        int currow;
        int SelectedQty = 0;
        maxrow = ShowTable.getRowCount();
        if (maxrow > 0) {
            for (int i = 0; i < maxrow; i++) {
                if (ShowTable.isRowSelected(i)) {
                    currow = i;
                    SelectedQty = (Integer) ShowTable.getValueAt(currow, 3);
                }
            }
        } else {
        }
        return SelectedQty;
    }

    public void txtCuQtyExit() {
        txtCuQty.setText("");
        txtCuQty.requestFocus(false);
        txtCucode.requestFocus(true);
    }

    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                CouponDiscount dialog = new CouponDiscount(new javax.swing.JFrame(), true, "", "", "", 0.00);
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
    private javax.swing.JTable ShowTable;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtCuQty;
    private javax.swing.JTextField txtCucode;
    // End of variables declaration//GEN-END:variables

    private void process() {
        //check จำนวนรายการว่ามีรายการไหนบ้างที่ยังสามารถให้ส่วนลดได้อยู่
        String msgError = "จำนวนคูปองส่วนลดพิเศษ ไม่สามารถใช้ได้สำหรับบิลนี้ !";
        int cuponQty = 0;
        try {
            cuponQty = Integer.parseInt(txtCuQty.getText());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        if (cuponQty <= 0) {
            txtCuQty.setText("0");
            txtCuQty.selectAll();
            txtCuQty.requestFocus();
            return;
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select count(*) itemCount from balance "
                    + "where r_table='" + tableNo + "' "
                    + "and R_PRAmt='0' "
                    + "and R_Discount ='Y' "
                    //                    + "and r_quancandisc>0 "
                    + "and r_void<>'V'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);//เช็คว่าสินค้าไหนใช้คูปองได้บ้าง
            int itemForDisc = 0;
            int tempitemForDisc = 0;
            if (rs.next()) {
                itemForDisc = rs.getInt("itemCount");//จำนวนสินค้าที่ให้คูปองได้
                tempitemForDisc = itemForDisc;
            }

            if (itemForDisc < cuponQty) {
                MSG.WAR(msgError);
                txtCuQty.setText("0");
                txtCuQty.selectAll();
                txtCuQty.requestFocus();
            } else {
                //ตรวจสอบคูปองส่วนลดที่นำมาใช้
                String cuCode = txtCucode.getText();
                CuponControl cc = new CuponControl();
                CuponBean cBean = cc.getCupon(cuCode);
                if (cBean.getCuType().equals("A") || cBean.getCuType().equals("C")) {

                    if (cuponQty > 1 && cBean.getCuType().equals("C")) {
                        MSG.WAR("บัตรคูปองที่ร่วมรายการนี้ รับได้สูงสุดแค่ 1 บัตรเท่านั้น");
                        txtCuQty.setText("1");
                        txtCuQty.selectAll();
                        txtCuQty.requestFocus();
                        return;
                    }

                    //check from cuponlist
                    CuponListControl lc = new CuponListControl();
                    ArrayList<CuponlistBean> listBean = lc.listCuponlist(cuCode);
                    //******* START ******//
                    if (listBean.isEmpty()) {
                        MSG.WAR(msgError);
                        txtCuQty.setText("0");
                        txtCuQty.selectAll();
                        txtCuQty.requestFocus();
                    } else {
                        int countCheck = 0;
                        for (int i = 0; i < listBean.size(); i++) {
                            CuponlistBean lBean = (CuponlistBean) listBean.get(i);
                            try {
                                String sql1 = "select * from balance "
                                        + "where r_quancandisc>0 "
                                        + "and r_table='" + tableNo + "' "
                                        + "and r_plucode='" + lBean.getPCode() + "'";
                                Statement stmt1 = mysql.getConnection().createStatement();
                                ResultSet rs1 = stmt1.executeQuery(sql1);
                                while (rs1.next()) {
                                    countCheck += rs1.getInt("r_quancandisc");

                                    //ตรวจสอบประเภทการให้ส่วนลด
                                    if (cBean.getCuSelectDisc().equals("1")) {//คิดยอดรวมสินค้า
                                        itemForDisc--;
                                        if (cBean.getCuDisc() > 0) {//ตรวจสอบส่วนลด % ก่อน
                                            int itemDisc = cuponQty;
                                            if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                itemDisc = rs1.getInt("R_QuanCanDisc");
                                                cuponQty -= itemDisc;
                                            } else {
                                                cuponQty = 0;
                                            }

//                                            updatePercentCupon(itemDisc, cuCode, cBean.getCuDisc(), rs1);
                                            updatePercentCupon(1, cuCode, cBean.getCuDisc(), rs1);
                                        } else if (cBean.getCuDiscBath() > 0) {//ส่วนสอบส่วนลดบาท
                                            int itemDisc = cuponQty;
                                            if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                itemDisc = rs1.getInt("R_QuanCanDisc");
                                                cuponQty -= itemDisc;
                                            } else {
                                                cuponQty = 0;
                                            }

                                            updateBathCupon(rs1, itemDisc, cBean.getCuDiscBath(), cuCode);
                                        }
                                    } else if (cBean.getCuSelectDisc().equals("2")) {//คิดตามประเภทสินค้า NN/CC/SS
                                        itemForDisc--;
                                        //ตรวจสอบประเภทสินค้า
                                        String productType = rs1.getString("R_Normal");
                                        if (productType.equals("N")) {//Normal
                                            if (cBean.getCuDisc1() > 0) {
                                                int itemDisc = cuponQty;
                                                if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                    itemDisc = rs1.getInt("R_QuanCanDisc");
                                                    cuponQty -= itemDisc;
                                                } else {
                                                    cuponQty = 0;
                                                }

                                                updatePercentCupon(itemDisc, cuCode, cBean.getCuDisc1(), rs1);
                                            } else if (cBean.getCuDiscBath1() > 0) {
                                                int itemDisc = cuponQty;
                                                if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                    itemDisc = rs1.getInt("R_QuanCanDisc");
                                                    cuponQty -= itemDisc;
                                                } else {
                                                    cuponQty = 0;
                                                }

                                                updateBathCupon(rs1, itemDisc, cBean.getCuDiscBath1(), cuCode);
                                            }
                                        } else if (productType.equals("C")) {//Consign
                                            if (cBean.getCuDisc2() > 0) {
                                                int itemDisc = cuponQty;
                                                if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                    itemDisc = rs1.getInt("R_QuanCanDisc");
                                                    cuponQty -= itemDisc;
                                                } else {
                                                    cuponQty = 0;
                                                }

                                                updatePercentCupon(itemDisc, cuCode, cBean.getCuDisc2(), rs1);
                                            } else if (cBean.getCuDiscBath2() > 0) {
                                                int itemDisc = cuponQty;
                                                if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                    itemDisc = rs1.getInt("R_QuanCanDisc");
                                                    cuponQty -= itemDisc;
                                                } else {
                                                    cuponQty = 0;
                                                }

                                                updateBathCupon(rs1, itemDisc, cBean.getCuDiscBath2(), cuCode);
                                            }
                                        } else if (productType.equals("S")) {//Special
                                            if (cBean.getCuDisc3() > 0) {
                                                int itemDisc = cuponQty;
                                                if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                    itemDisc = rs1.getInt("R_QuanCanDisc");
                                                    cuponQty -= itemDisc;
                                                } else {
                                                    cuponQty = 0;
                                                }

                                                updatePercentCupon(itemDisc, cuCode, cBean.getCuDisc3(), rs1);
                                            } else if (cBean.getCuDiscBath3() > 0) {
                                                int itemDisc = cuponQty;
                                                if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                                    itemDisc = rs1.getInt("R_QuanCanDisc");
                                                    cuponQty -= itemDisc;
                                                } else {
                                                    cuponQty = 0;
                                                }

                                                updateBathCupon(rs1, itemDisc, cBean.getCuDiscBath3(), cuCode);
                                            }
                                        }
                                    } else {
                                        MSG.WAR("ไม่พบประเภทการให้คูปองส่วนลดสำหรับคูปองนี้ !");
                                        txtCuQty.setText("0");
                                        txtCuQty.selectAll();
                                        txtCuQty.requestFocus();
                                    }
                                }

                                rs1.close();
                                stmt1.close();
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }

                        if (countCheck == 0) {
                            MSG.WAR(msgError);
                            txtCuQty.setText("0");
                            txtCuQty.selectAll();
                            txtCuQty.requestFocus();
                        } else {

                        }
                    }
                    //******* END *******//
                } else if (cBean.getCuType().equals("B")) {
                    try {
                        String sql1 = "select * from balance "
                                + "where R_PRAmt='0' "
                                + "and R_Discount ='Y' "
                                + "and r_table='" + tableNo + "' "
                                + "and r_void<>'V' ";
//                        String sql1 = "select * from balance "
//                                + "where r_quancandisc>0 "
//                                + "and r_table='" + tableNo + "' "
//                                + "and r_void<>'V' ";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        ResultSet rs1 = stmt1.executeQuery(sql1);
                        while (rs1.next()) {
                            //ตรวจสอบประเภทการให้ส่วนลด
                            if (cBean.getCuSelectDisc().equals("1")) {//คิดยอดรวมสินค้า

                                itemForDisc--;
                                if (cBean.getCuDisc() > 0) {//ตรวจสอบส่วนลด % ก่อน
                                    int itemDisc = cuponQty;
                                    if (rs1.getInt("R_QuanCanDisc") > 0) {
                                        itemDisc = rs1.getInt("R_QuanCanDisc");
                                        cuponQty -= itemDisc;
                                    } else {
                                        cuponQty = 0;
                                    }

                                    updatePercentCupon(rs1.getInt("R_QuanCanDisc"), cuCode, cBean.getCuDisc(), rs1);
                                } else if (cBean.getCuBDiscBath() > 0) {//ส่วนสอบส่วนลดบาท
                                    int itemDisc = cuponQty;
                                    if (rs1.getInt("R_QuanCanDisc") > 0) {
                                        itemDisc = rs1.getInt("R_QuanCanDisc");
                                        cuponQty -= itemDisc;
                                    } else {
                                        cuponQty = 0;
                                    }
                                    updateBathCupon(rs1, rs1.getInt("R_QuanCanDisc"), cBean.getCuBDiscBath(), cuCode);
                                } else if (cBean.getCuDiscBath() > 0) {
                                    int itemDisc = cuponQty;
                                    if (rs1.getInt("R_QuanCanDisc") > 0) {
                                        itemDisc = rs1.getInt("R_QuanCanDisc");
                                        cuponQty -= itemDisc;
                                    } else {
                                        cuponQty = 0;
                                    }
                                    if (cBean.getCuDiscBath() > totalAmount) {
                                        cBean.setCuDiscBath(totalAmount);
                                        cuponQty = 0;
                                    }
                                    updateBathCupon(rs1, rs1.getInt("R_QuanCanDisc"), cBean.getCuDiscBath(), cuCode);
                                    if (cuponQty <= 0) {
                                        break;
                                    }
                                }
                            } else if (cBean.getCuSelectDisc().equals("2")) {//คิดตามประเภทสินค้า NN/CC/SS
                                itemForDisc--;
                                //ตรวจสอบประเภทสินค้า
                                String productType = rs1.getString("R_Normal");
                                if (productType.equals("N")) {//Normal
                                    if (cBean.getCuDisc1() > 0) {
                                        int itemDisc = cuponQty;
                                        if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                            itemDisc = rs1.getInt("R_QuanCanDisc");
                                            cuponQty -= itemDisc;
                                        } else {
                                            cuponQty = 0;
                                        }

                                        updatePercentCupon(itemForDisc, cuCode, cBean.getCuDisc1(), rs1);

                                    } else if (cBean.getCuDiscBath1() > 0) {
                                        int itemDisc = cuponQty;
                                        if (rs1.getInt("R_QuanCanDisc") < itemDisc) {
                                            itemDisc = rs1.getInt("R_QuanCanDisc");
                                            cuponQty -= itemDisc;
                                        } else {
                                            cuponQty = 0;
                                        }

                                        updateBathCupon(rs1, itemDisc, cBean.getCuDiscBath1(), cuCode);
                                    }
                                } else if (productType.equals("C")) {//Consign
                                    if (cBean.getCuDisc2() > 0) {
                                        int itemDisc = cuponQty;
                                        if (rs1.getInt("R_QuanCanDisc") > 0) {
                                            itemDisc = rs1.getInt("R_QuanCanDisc");
                                            cuponQty -= itemDisc;
                                        } else {
                                            cuponQty = 0;
                                        }

                                        updatePercentCupon(rs1.getInt("R_QuanCanDisc"), cuCode, cBean.getCuDisc2(), rs1);
                                    } else if (cBean.getCuDiscBath2() > 0) {
                                        int itemDisc = cuponQty;
                                        if (rs1.getInt("R_QuanCanDisc") > 0) {
                                            itemDisc = rs1.getInt("R_QuanCanDisc");
                                            cuponQty -= itemDisc;
                                        } else {
                                            cuponQty = 0;
                                        }

                                        updateBathCupon(rs1, rs1.getInt("R_QuanCanDisc"), cBean.getCuDiscBath2(), cuCode);
                                    }
                                } else if (productType.equals("S")) {//Special
                                    if (cBean.getCuDisc3() > 0) {
                                        int itemDisc = cuponQty;
                                        if (rs1.getInt("R_QuanCanDisc") > 0) {
                                            itemDisc = rs1.getInt("R_QuanCanDisc");
                                            cuponQty -= itemDisc;
                                        } else {
                                            cuponQty = 0;
                                        }

                                        updatePercentCupon(rs1.getInt("R_QuanCanDisc"), cuCode, cBean.getCuDisc3(), rs1);
                                    } else if (cBean.getCuDiscBath3() > 0) {
                                        int itemDisc = cuponQty;
                                        if (rs1.getInt("R_QuanCanDisc") > 0) {
                                            itemDisc = rs1.getInt("R_QuanCanDisc");
                                            cuponQty -= itemDisc;
                                        } else {
                                            cuponQty = 0;
                                        }

                                        updateBathCupon(rs1, rs1.getInt("R_QuanCanDisc"), cBean.getCuDiscBath3(), cuCode);
                                    }
                                }
                            } else {
                                MSG.WAR("ไม่พบประเภทการให้คูปองส่วนลดสำหรับคูปองนี้ !");
                                txtCuQty.setText("0");
                                txtCuQty.selectAll();
                                txtCuQty.requestFocus();
                            }
                        }

                        rs1.close();
                        stmt1.close();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    MSG.WAR("ไม่พบข้อมูลบัตรคูปองที่ท่านต้องการใช้งาน !");
                    txtCuQty.setText("0");
                    txtCuQty.selectAll();
                    txtCuQty.requestFocus();
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        //update tablefile
        BalanceControl.updateProSerTable(tableNo, null);

        TableFileControl tfCon = new TableFileControl();
        TableFileBean tBean = tfCon.getData(tableNo);

        CuponControl cuCon = new CuponControl();
        CuponBean cuBean = cuCon.getCupon(txtCucode.getText());

        int cuQuan = 0;
        try {
            cuQuan = Integer.parseInt(txtCuQty.getText().replace(",", ""));
            if (cuQuan < 0) {
                cuQuan = 0;
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        //update tempcupon
        TempCuponController temp = new TempCuponController();
        TempCuponBean bean = new TempCuponBean();
        bean.setR_Index(tableNo + tBean.getMacNo());
        bean.setR_Table(tableNo);
        bean.setTerminal(tBean.getMacNo());
        bean.setCashier(tBean.getCashier());
        bean.setTime("");
        bean.setCuCode(txtCucode.getText());
        bean.setCuQuan(cuQuan);
        bean.setCuAmt(tBean.getCuponDiscAmt());
        bean.setCuTotal(tBean.getCuponDiscAmt());
        bean.setCuDisc(cuBean.getCuDisc());
        bean.setCuRedule(0);
        bean.setCuPayment(0);
        bean.setCuTextCode("");
        bean.setCuTextComment("");

        temp.saveTempCupon(bean);
        //end update tempcupon

        dispose();
    }

    private void updatePercentCupon(int itemDisc, String cuCode, double percent, ResultSet rs1) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            double rTotal = rs1.getDouble("R_Price") * itemDisc;
            double total = rTotal * percent / 100;
            String sql3 = "update balance set "
                    + "R_PrCuType='-C',"
                    + "R_PrCuQuan=" + itemDisc + ","
                    + "R_PrCuAmt='" + total + "',"
                    + "R_PrCuCode='" + cuCode + "',"
                    + "R_PrCuDisc='" + percent + "',"
                    + "R_QuanCanDisc=R_QuanCanDisc-" + itemDisc + " "
                    + "where R_Index='" + rs1.getString("R_Index") + "' "
                    + "and R_Table='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql3);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

    private void updateBathCupon(ResultSet rs1, double itemDisc, double bath, String cuCode) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            double rTotal = rs1.getDouble("R_Price") * itemDisc;
            double total = 0;
            if (rTotal == 0) {
                total = 0;
            } else {
                if (!CONFIG.getP_DiscRound().equals("N")) {
                    total = NumberControl.UP_DOWN_NATURAL_BAHT(bath / rTotal * 100);
                } else {
                    total = (bath / rTotal * 100);
                }

            }
            String sql3 = "update balance set "
                    + "R_PrCuType='-C',"
                    + "R_PrCuQuan=" + itemDisc + ","
                    + "R_PrCuAmt='" + bath + "',"
                    + "R_PrCuCode='" + cuCode + "',"
                    + "R_PrCuDisc='" + total + "',"
                    + "R_QuanCanDisc=R_QuanCanDisc-" + itemDisc + " "
                    + "where R_Index='" + rs1.getString("R_Index") + "' "
                    + "and R_Table='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql3);
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

    private int getCountTable(String cuCode) {
        int count = 0;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select sum(r_prcuquan) qty "
                    + "from balance "
                    + "where r_table='" + tableNo + "' "
                    + "and r_prcucode='" + cuCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("qty");
            }

            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return count;
    }

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
