package com.softpos.main.program;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Point;
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
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import util.DateChooseDialog;
import util.MSG;

public class DispInv1 extends javax.swing.JDialog {

    DefaultTableModel model2;
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();

    /**
     * Creates new form DispInv1
     */
    public DispInv1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        PublicVar.ReturnString = "";
        model2 = (DefaultTableModel) tblShow.getModel();
        tblShow.setShowGrid(true);
        tblShow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblShow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblShow.setRowSelectionAllowed(true);
        tblShow.setGridColor(Color.gray);

        JTableHeader header = tblShow.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

        int[] ColSize = {50, 100, 80, 50, 80, 100, 250, 100, 100, 100, 200, 100, 100};
        for (int i = 0; i < 13; i++) {
            //int vColIndex = 0;
            TableColumn col = tblShow.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }
        ClearVariable();
    }

    public void ClearVariable() {
        ardate1.setText(ShowDatefmt.format(date));
        ardate2.setText(ShowDatefmt.format(date));
        ardate1.requestFocus();
    }

    public Boolean ChkValidDate() {
        Boolean RetVal = true;
        if (!PUtility.ChkDate(ardate1.getText())) {
            ardate1.requestFocus();
            RetVal = false;
        }
        if (!PUtility.ChkDate(ardate2.getText())) {
            ardate2.requestFocus();
            RetVal = false;
        }
        return RetVal;
    }

    public void bntOKClick() {
        if (ChkValidDate()) {
            Date TempDate1 = new Date();
            Date TempDate2 = new Date();
            try {
                TempDate2 = ShowDatefmt.parse(ardate2.getText());
                TempDate1 = ShowDatefmt.parse(ardate1.getText());
            } catch (ParseException e) {
            }
            int RowCount = model2.getRowCount();
            for (int i = 0; i <= RowCount - 1; i++) {
                model2.removeRow(0);
            }
            int XTotalCnt = 0;
            double XAmt1 = 0.0;
            double XAmt2 = 0.0;
            double XAmt3 = 0.0;
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SQLQuery = "select *from invcashdoc where (invdate>='" + Datefmt.format(TempDate1) + "') and (invdate<='" + Datefmt.format(TempDate2) + "') and substring(invno,1,1)='P' "
                        + "order by invno";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        String VoidDate = "";
                        try {
                            VoidDate = ShowDatefmt.format(rec.getDate("voiddate"));
                        } catch (SQLException e) {

                        }
                        XTotalCnt++;
                        Object[] input = {rec.getString("void"),
                            rec.getString("invno"),
                            ShowDatefmt.format(rec.getDate("invdate")),
                            rec.getString("macno"),
                            rec.getString("refno"),
                            rec.getString("custcode"),
                            rec.getString("custname"),
                            rec.getDouble("subtotal"),
                            rec.getDouble("vat"),
                            rec.getDouble("amount"),
                            rec.getString("voidmessage"),
                            rec.getString("uservoid"),
                            VoidDate
                        };
                        XAmt1 = XAmt1 + rec.getDouble("subtotal");
                        XAmt2 = XAmt2 + rec.getDouble("vat");
                        XAmt3 = XAmt3 + rec.getDouble("amount");
                        model2.addRow(input);
                    } while (rec.next());
                    showCell(0, 0);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            }finally{
                mysql.close();
            }
            TotalCnt.setText(IntFmt.format(XTotalCnt));
            TotalAmt1.setText(DecFmt.format(XAmt1));
            TotalAmt2.setText(DecFmt.format(XAmt2));
            TotalAmt3.setText(DecFmt.format(XAmt3));
            tblShow.requestFocus();
        }
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = tblShow.getCellRect(row, column, true);
            tblShow.scrollRectToVisible(rect);
            tblShow.clearSelection();
            tblShow.setRowSelectionInterval(row, row);
        }
    }

    public void bntExitClick() {
        this.dispose();
    }

    public void bntPrintClick() {
        String d1 = ardate1.getText();
        String d2 = ardate2.getText();
        Date dd = new Date();
        try {
            dd = ShowDatefmt.parse(d1);
            dd = ShowDatefmt.parse(d2);
            if (!d1.equals("") && !d2.equals("")) {
                ViewReport view = new ViewReport();
                view.printReportPVatDaily(d1, d2);
            }
        } catch (Exception e) {
            MSG.ERR(this, "วันที่ไม่ถูกต้อง");
        }

    }

    public void GetSelectedRow() {
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
            TableSelected = tblShow.getValueAt(currow, 1).toString();
            PublicVar.ReturnString = TableSelected;
        } else {
            PublicVar.ReturnString = "";

        }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ardate1 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        ardate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        cmdDateChoose2 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        bntOk = new javax.swing.JButton();
        bntPrint1 = new javax.swing.JButton();
        bntPrint = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShow = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        TotalCnt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TotalAmt3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TotalAmt2 = new javax.swing.JTextField();
        TotalAmt1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));
        jPanel1.setFocusable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ช่วงวันที่");

        ardate1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ardate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ardate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ardate1KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ถึง");

        ardate2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ardate2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ardate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ardate2KeyPressed(evt);
            }
        });

        cmdDateChoose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose1.setFocusable(false);
        cmdDateChoose1.setRequestFocusEnabled(false);
        cmdDateChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose1ActionPerformed(evt);
            }
        });

        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.setRequestFocusEnabled(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ardate1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ardate2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(ardate1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ardate2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setRollover(true);

        bntOk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softpos/main/program/ScrOnOff.jpg"))); // NOI18N
        bntOk.setText("F5 ประมวลผล");
        bntOk.setDefaultCapable(false);
        bntOk.setFocusable(false);
        bntOk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntOk.setIconTextGap(0);
        bntOk.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOk.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOkActionPerformed(evt);
            }
        });
        jToolBar1.add(bntOk);

        bntPrint1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softpos/main/program/PullBill.jpg"))); // NOI18N
        bntPrint1.setText("F1 เลือก");
        bntPrint1.setDefaultCapable(false);
        bntPrint1.setFocusable(false);
        bntPrint1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrint1.setIconTextGap(0);
        bntPrint1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntPrint1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrint1ActionPerformed(evt);
            }
        });
        jToolBar1.add(bntPrint1);

        bntPrint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softpos/main/program/PaidIn2.jpg"))); // NOI18N
        bntPrint.setText("F10 พิมพ์");
        bntPrint.setDefaultCapable(false);
        bntPrint.setFocusable(false);
        bntPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrint.setIconTextGap(0);
        bntPrint.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintActionPerformed(evt);
            }
        });
        jToolBar1.add(bntPrint);

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softpos/main/program/Logout.jpg"))); // NOI18N
        bntExit.setText("ออก(Exit)");
        bntExit.setDefaultCapable(false);
        bntExit.setFocusable(false);
        bntExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntExit.setIconTextGap(0);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });
        jToolBar1.add(bntExit);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblShow.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        tblShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ยกเลิก", "เลขที่ใบแจ้งหนี้", "วันที่ (Date)", "MacNo", "Ref-No", "รหัสลูกค้า", "ชื่อลูกค้า/บริษัท", "มูลค่าสินค้า", "ภาษี (Vat)", "จำนวนเงิน", "สาเหตุการยเลิก", "ผู้ยกเลิก", "วันที่ยกเลิก"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShow.setFocusTraversalPolicyProvider(true);
        tblShow.setRowHeight(20);
        tblShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblShowKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblShow);

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("จำนวนรายการทั้งสิ้น");

        TotalCnt.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalCnt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalCnt.setFocusable(false);
        TotalCnt.setRequestFocusEnabled(false);

        jLabel6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel6.setText("จำนวนเงินรวม");

        TotalAmt3.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalAmt3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalAmt3.setFocusable(false);
        TotalAmt3.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel7.setText("ภาษีมูลค่าเพิ่ม");

        jLabel8.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("มูลค่าสินค้า");

        TotalAmt2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalAmt2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalAmt2.setFocusable(false);
        TotalAmt2.setRequestFocusEnabled(false);

        TotalAmt1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalAmt1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalAmt1.setFocusable(false);
        TotalAmt1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TotalCnt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TotalAmt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalAmt2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TotalAmt3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel6)
                                .addGap(47, 47, 47))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalAmt3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalCnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalAmt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalAmt1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1008, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOkActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOkActionPerformed

private void bntPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintActionPerformed
    bntPrintClick();
}//GEN-LAST:event_bntPrintActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void tblShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowKeyPressed

    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F10) {
        bntPrintClick();
    }
}//GEN-LAST:event_tblShowKeyPressed

private void ardate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ardate1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ardate2.selectAll();
        ardate2.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F10) {
        bntPrintClick();
    }
}//GEN-LAST:event_ardate1KeyPressed

private void ardate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ardate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ardate1.selectAll();
        ardate1.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F10) {
        bntPrintClick();
    }
}//GEN-LAST:event_ardate2KeyPressed

private void bntPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrint1ActionPerformed
    GetSelectedRow();
}//GEN-LAST:event_bntPrint1ActionPerformed

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    ardate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    ardate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    ardate2.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    ardate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DispInv1 dialog = new DispInv1(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField TotalAmt1;
    private javax.swing.JTextField TotalAmt2;
    private javax.swing.JTextField TotalAmt3;
    private javax.swing.JTextField TotalCnt;
    private javax.swing.JFormattedTextField ardate1;
    private javax.swing.JFormattedTextField ardate2;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOk;
    private javax.swing.JButton bntPrint;
    private javax.swing.JButton bntPrint1;
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblShow;
    // End of variables declaration//GEN-END:variables

}
