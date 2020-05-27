package com.softpos.main.program;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import database.MySQLConnect;
import java.awt.GraphicsEnvironment;
import java.sql.Statement;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import printReport.PrintDriver;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class CancelArPayment extends javax.swing.JDialog {

    DefaultTableModel model2;
    String Fat;
    PPrint prn = new PPrint();
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    Date date = new Date();
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    private POSHWSetup POSHW;
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;
    

    /**
     * Creates new form CancelArPaymentClick
     */
    public CancelArPayment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        POSHW = POSHWSetup.Bean(Value.getMacno());
        model2 = (DefaultTableModel) tblShow.getModel();
        tblShow.setShowGrid(true);
        tblShow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblShow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblShow.setRowSelectionAllowed(true);
        tblShow.setGridColor(Color.gray);
        JTableHeader header = tblShow.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));
        //this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        int[] ColSize = {175, 150, 150, 150, 150, 250, 100, 100, 100};
        for (int i = 0; i < 9; i++) {
            //int vColIndex = 0;
            TableColumn col = tblShow.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }

        LoadDataToGrid();
        tblShow.requestFocus();
    }

    public void LoadDataToGrid() {
        String sql = "";
        int RowCount = model2.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model2.removeRow(0);
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            sql = "Select * from billar left join custfile on arcode=sp_code order by ondate,ref_no";
            ResultSet rec = stmt.executeQuery(sql);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    Object[] input = {rec.getString("ref_no"),
                        rec.getDate("ondate"),
                        rec.getString("terminal"),
                        rec.getString("cashier"),
                        rec.getString("arcode"),
                        ThaiUtil.ASCII2Unicode(rec.getString("sp_desc")),
                        rec.getDouble("stotal"),
                        rec.getString("fat"),
                        rec.getString("uservoid")
                    };
                    model2.addRow(input);
                } while (rec.next());
                showCell(0, 0);
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }

        tblShow.requestFocus();
    }

    public void bntOKClick() {
        Fat = "";
        String TempBillNo = GetSelectedRow();
        if (!TempBillNo.equals("")) {
            if (!Fat.equals("V")) {
                if (PUtility.ShowConfirmMsg("ต้องการยกเลิกการรับชำระจากลูกหนี้ภายนอก เลขที่ " + TempBillNo + " หรือไม่ ?  ")) {
                    PrintCancelArPayment(TempBillNo);
                    SaveDataCancelArPayment(TempBillNo);
                    this.dispose();
                }
            } else {
                PUtility.ShowMsg("รายการนี้ได้ทำการยกเลิกไปแล้ว...");
            }
        }

    }

    public void SaveDataCancelArPayment(String TempBillNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "update billar set fat='V',uservoid='" + PublicVar._User + "' where ref_no='" + TempBillNo + "' ";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "update t_ar set fat='V' where ref_no='" + TempBillNo + "' ";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "update t_crar set fat='V' where ref_no='" + TempBillNo + "' ";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "update accr set ardocpay='',arflage='N' where ardocpay='" + TempBillNo + "' ";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }

        mysql.close();
    }

    public void PrintCancelArPayment(String TempBillNo) {
        if (!Value.getComPort().equals("NONE")) {
            if (Value.printdriver) {
                PrintCancelArPaymentDriver(TempBillNo);
            } else {
                if (prn.OpenPrint(Value.getComPort())) {
                    prn.InitPrinter();
                    prn.print(POSHW.getHeading1());
                    prn.print(POSHW.getHeading2());
                    prn.print(POSHW.getHeading3());
                    prn.print(POSHW.getHeading4());
                    prn.print("REG ID :" + Value.MACNO);
                    prn.print("***ยกเลิกรายการรับเงินลูกหนี้ภายนอก***");
                    prn.print("หมายเลขเครื่อง : " + Value.MACNO);
                    prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    prn.print("----------------------------------------");
                    prn.print("อ้างถึงใบเสร็จรับเงินเลขที่           จำนวนเงิน");
                    Double SumAmount = 0.0;
                    /**
                     * * OPEN CONNECTION **
                     */
                    MySQLConnect mysql = new MySQLConnect();
                    mysql.open();
                    try {
                        Statement stmt = mysql.getConnection().createStatement();
                        String SQLQuery = "Select *from t_ar where ref_no='" + TempBillNo + "'";
                        ResultSet rec = stmt.executeQuery(SQLQuery);
                        rec.first();
                        if (rec.getRow() == 0) {
                        } else {
                            do {
                                prn.print(rec.getString("billno") + " " + DatefmtThai.format(rec.getDate("billdate")) + " " + DecFmt.format(-1 * rec.getDouble("amount")));
                                SumAmount = SumAmount + rec.getDouble("amount");
                            } while (rec.next());
                        }
                        rec.close();
                        stmt.close();
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                    }
                    prn.print("----------------------------------------");
                    prn.print("Sub-Total................." + DecFmt.format(SumAmount));
                    prn.print("----------------------------------------");
                    prn.print("");
                    prn.print("");
                    prn.print("");

                    prn.CutPaper();
                    prn.closePrint();
                } else {
//                PUtility.showError("เครื่องพิมพ์ใบกำกับภาษีไม่สามารถพิมพ์ได้ ...");
                }
            }

        }
    }

    public void PrintCancelArPaymentDriver(String TempBillNo) {

        String t = "";
        Double SumAmount = 0.0;
        if (POSHW.getHeading1().trim().length() >= 18) {
            String[] strs = POSHW.getHeading1().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading1().trim().replace(" ", "&nbsp; ") + "_";
        }
        if (POSHW.getHeading2().length() >= 18) {
            String[] strs = POSHW.getHeading2().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
        }
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4()).trim() + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "REG.ID :" + Space + (POSHW.getTerminal()) + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + ("_");
        t += "colspan=3 align=left><font face=Angsana New size=1>" + ("ยกเลิกรายการรับเงินลูกหนี้ภายนอก") + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + "Print Date" + Space + (DatefmtThai.format(date)) + "_";
        t += "colspan=3 align=left><font face=Angsana New size=1>" + "Cashier:" + PublicVar._UserName + TAB + " Mac:" + Value.MACNO + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------------") + "_";
        try {
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SQLQuery = "Select *from t_ar where ref_no='" + TempBillNo + "'";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        t += "colspan=2 align=left><font face=Angsana New size=1>" + "อ้างถึงใบกำกับภาษีเลขที่" + "</td></font><td align=right><font face=Angsana New size=1>" + "จำนวนเงิน" + "_";
                        t += "colspan=2 align=left><font face=Angsana New size=1>" + rec.getString("billno") + TAB + DatefmtThai.format(rec.getDate("billdate")) + "</td></font><td align=right><font face=Angsana New size=1>" + DecFmt.format(-1 * rec.getDouble("amount")) + "_";
                        SumAmount = SumAmount + rec.getDouble("amount");
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            }
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------")+"_";
            t += "colspan=2 align=left><font face=Angsana New size=1>" + "Sub-Total..." + "</td></font><td align=right><font face=Angsana New size=1>" + DecFmt.format(SumAmount*-1)+"_";
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("----------------------------------------")+"_";
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }

        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getFootting1().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getFootting2().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getFootting3().trim()) + "_";
        String[] strs = t.split("_");
        PrintDriver pd = new PrintDriver();
        for (String data : strs) {
            pd.addTextIFont(data);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        pd.printHTML();
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = tblShow.getCellRect(row, column, true);
            tblShow.scrollRectToVisible(rect);
            tblShow.clearSelection();
            tblShow.setRowSelectionInterval(row, row);
        }
    }

    public String GetSelectedRow() {
        int maxrow;
        int currow = 0;
        String TableSelected = "";
        maxrow = tblShow.getRowCount();
        if (maxrow > 0) {
            for (int i = 0; i < maxrow; i++) {
                if (tblShow.isRowSelected(i)) {
                    currow = i;
                }
            }
            TableSelected = tblShow.getValueAt(currow, 0).toString();
            Fat = tblShow.getValueAt(currow, 7).toString();
        }
        return TableSelected;
    }

    public void bntExitClick() {
        PublicVar.ReturnString = "";
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblShow = new javax.swing.JTable();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ยกเลิกการรับชำระจากลูกหนี้ภายนอก");
        setLocationByPlatform(true);
        setUndecorated(true);

        tblShow.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        tblShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "เลขที่บิล", "วันที่ชำระ", "MacNo", "Cashier", "รหัสลูกหนี้", "ชื่อลูกหนี้", "จำนวนเงิน", "Fat", "User Void"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
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
        tblShow.setRowHeight(30);
        tblShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblShowKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblShow);

        bntOK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntOK.setText("Enter-ตกลง (OK)");
        bntOK.setFocusable(false);
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntExit.setText("ESC-ออก (Exit)");
        bntExit.setFocusable(false);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 21, 122));
        jLabel1.setText("ค้นหารายการที่ต้องการยกเลิก");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 311, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1178, 608));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void tblShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        GetSelectedRow();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
}//GEN-LAST:event_tblShowKeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MySQLConnect();
                CancelArPayment dialog = new CancelArPayment(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblShow;
    // End of variables declaration//GEN-END:variables
}
