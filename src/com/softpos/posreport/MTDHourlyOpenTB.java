package com.softpos.posreport;

import com.softpos.main.model.POSHWSetup;
import database.MySQLConnect;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import sun.natee.project.util.KeyBoardDialog;
import com.softpos.main.utils.DateChooseDialog;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.Value;
import java.util.Date;
import com.softpos.main.model.PublicVar;
import java.text.ParseException;
import printReport.PrintDriver;
import com.softpos.main.utils.MSG;

public class MTDHourlyOpenTB extends javax.swing.JDialog {

    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    PPrint prn = new PPrint();
    Date date = new Date();
    Date TDate1 = new Date();
    Date TDate2 = new Date();
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;

    private POSHWSetup POSHW;

    public MTDHourlyOpenTB(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate1.setText(DatefmtShow.format(date));
        txtDate2.setText(DatefmtShow.format(date));
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    public ArrayList<Object[]> LoadData() {
        ArrayList<Object[]> ListObj = new ArrayList<>();
        MySQLConnect c = new MySQLConnect();
        c.open();
        try {
            DecimalFormat df = new DecimalFormat("00.00");
            DecimalFormat df1 = new DecimalFormat("00.00");
            DecimalFormat df2 = new DecimalFormat("#,##0");
            DefaultTableModel model = (DefaultTableModel) tblSalePerHour.getModel();
            int size1 = model.getRowCount();
            int sumCustE = 0;
            int sumCustT = 0;
            int TotalCustE = 0;
            int TotalCustT = 0;
            int totalCC = 0;
            double sumNet = 0.00;
            double sumBill = 0.00;
            int TTLCC = 0;
            for (int i = 0; i < size1; i++) {
                model.removeRow(0);//ลบข้อมูลบนตารางจนเหลือ 0 ก่อนที่จะโหลดขึ้นมาใหม่
            }
            int t1 = 05;
            int t2 = 06;
            for (int i = 0; i < 24; i++) {
                if (t2 == 23) {
                    t2 = -1;
                }
                if (t1 == 23) {
                    t1 = -1;
                }
                t1++;
                t2++;
                String time1 = df.format(t1).replace(".", ":");
                String time2 = df1.format(t2).replace(".", ":");
                time1 += ":01";
                time2 += ":00";
                String dateFrom = txtDate1.getText().replace("/", "");
                String ddFrom = dateFrom.substring(0, 2);
                String mmFrom = dateFrom.substring(2, 4);
                String yyyyFrom = dateFrom.substring(4, 8);
                dateFrom = yyyyFrom + "-" + mmFrom + "-" + ddFrom;
                String dateTo = txtDate2.getText().replace("/", "");
                String ddTo = dateTo.substring(0, 2);
                String mmTo = dateTo.substring(2, 4);
                String yyyyTo = dateTo.substring(4, 8);
                dateTo = yyyyTo + "-" + mmTo + "-" + ddTo;
                int sumB_custE = 0;
                int sumB_custT = 0;
                double sumNettotalE = 0.00;
                double sumNettotalT = 0.00;
                int sumBillE = 0;
                int sumBillT = 0;
                System.out.println(time1 + time2);
                if (time2.equals("00:00:00")) {
                    time2 = "23:59:59";
                }
                //หาจำนวนบิลที่ชำระเงินในช่วงเวลา E ราคารวม E
                String sqlE = "select count(b_refno) sumBillE,"
                        + "sum(b_cust) sumBcustE,"
                        + "sum(b_nettotal) sumNettotalE "
                        + "from s_invoice  "
                        + "where s_date between '" + dateFrom + "' and '" + dateTo + "' "
                        + "and b_logintime between'" + time1 + "' and '" + time2 + "' and b_void<>'V' and b_etd='E';";
                //หาจำนวนบิลที่ชำระเงินในช่วงเวลา T ราคารวม T
                String sqlT = "select count(b_refno) sumBillT,"
                        + "sum(b_cust) sumBcustT,"
                        + "sum(b_nettotal) sumNettotalT "
                        + "from s_invoice  "
                        + "where s_date between '" + dateFrom + "' and '" + dateTo + "' "
                        + "and b_logintime between'" + time1 + "' and '" + time2 + "' and b_void<>'V' and b_etd='T';";
                //หาจำนวนลูกค้า E
                String sqlE1 = "select sum(b_cust) sumB_custE1 "
                        + "from s_invoice  "
                        + "where s_date between '" + dateFrom + "' and '" + dateTo + "' "
                        + "and b_void<>'V' and b_etd='E';";
                //หาจำนวนลูกค้า T
                String sqlT1 = "select sum(b_cust) sumB_custT1 "
                        + "from s_invoice  "
                        + "where s_date between '" + dateFrom + "' and '" + dateTo + "' "
                        + "and b_void<>'V' and b_etd='T';";
                ResultSet rsTypeE = c.getConnection().createStatement().executeQuery(sqlE);
                if (rsTypeE.next()) {
                    sumB_custE = rsTypeE.getInt("sumBcustE");//จำนวนลูกค้า
                    sumBillE = rsTypeE.getInt("sumBillE");//จำนวนบิล
                    sumNettotalE = rsTypeE.getDouble("sumNettotalE");//มูลค่า
                    sumCustE += sumB_custE;
                    sumNet += sumNettotalE;
                    sumBill += sumBillE;
                }
                ResultSet rsTypeT = c.getConnection().createStatement().executeQuery(sqlT);
                if (rsTypeT.next()) {
                    sumB_custT = rsTypeT.getInt("sumBcustT");
                    sumBillT = rsTypeT.getInt("sumBillT");
                    sumNettotalT = rsTypeT.getDouble("sumNettotalT");
                    sumCustT += sumB_custT;
                    sumNet += sumNettotalT;
                    sumBill += sumBillT;
                }
                ResultSet rs1 = c.getConnection().createStatement().executeQuery(sqlE1);
                if (rs1.next()) {
                    TotalCustE = rs1.getInt("sumB_custE1");
                }
                ResultSet rs2 = c.getConnection().createStatement().executeQuery(sqlT1);
                if (rs2.next()) {
                    TotalCustT = rs2.getInt("sumB_custT1");
                }
                TTLCC = sumB_custE + sumB_custT;
                totalCC += TTLCC;
                model.addRow(new Object[]{
                    df.format(t1) + " - " + df1.format(t2),//เวลา
                    IntFmt.format(sumB_custE + sumB_custT),//จำนวนลูกค้า
                    IntFmt.format(sumBillE + sumBillT),//จำนวนบิล
                    IntFmt.format(sumNettotalE + sumNettotalT)//มูลค่า
                });
                ListObj.add(new Object[]{df.format(t1).replace(".", ":") + " - " + df1.format(t2).replace(".", ":"),
                    IntFmt.format(sumB_custE + sumB_custT),
                    IntFmt.format(sumBillE + sumBillT),
                    IntFmt.format(sumNettotalE + sumNettotalT)
                });
            }
            ListObj.add(new Object[]{
                IntFmt.format(totalCC),
                IntFmt.format(sumBill),
                IntFmt.format(sumNet)
            });

            model.addRow(new Object[]{
                "TOTAL : >>>",
                IntFmt.format(sumCustE + sumCustT),
                IntFmt.format(sumBill),
                IntFmt.format(sumNet)
            });
            c.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }
        return ListObj;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblSalePerHour = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JFormattedTextField();
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        bntOK1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        tblSalePerHour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "เวลา", "จำนวนลูกค้า", "จำนวนบิล", "จำนวนเงิน"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSalePerHour);
        if (tblSalePerHour.getColumnModel().getColumnCount() > 0) {
            tblSalePerHour.getColumnModel().getColumn(0).setMinWidth(100);
            tblSalePerHour.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงวันที่ๆต้องการ (Date)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txtDate1.setEditable(false);
        txtDate1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate1MouseClicked(evt);
            }
        });
        txtDate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate1KeyPressed(evt);
            }
        });

        txtDate2.setEditable(false);
        txtDate2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate2MouseClicked(evt);
            }
        });
        txtDate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate2KeyPressed(evt);
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ถึง");

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
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("ประมวลผล");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntOK1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK1.setText("พิมพ์ (F5)");
        bntOK1.setFocusable(false);
        bntOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Customer Count");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Time");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TTL CC");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate1MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtDate1);
        }
    }//GEN-LAST:event_txtDate1MouseClicked

    private void txtDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            //bntOKClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDate2.requestFocus();
        }
    }//GEN-LAST:event_txtDate1KeyPressed

    private void txtDate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtDate2);
        }
    }//GEN-LAST:event_txtDate2MouseClicked

    private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //bntExitClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            //bntOKClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDate1.requestFocus();
        }
    }//GEN-LAST:event_txtDate2KeyPressed

    private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
        Point point = cmdDateChoose2.getLocation();
        point.setLocation(point.getX(), point.getY());
        DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, cmdDateChoose1.getLocationOnScreen());
        dcd.setVisible(true);
        // dcd.showDialog(new LookAndFeelFrame(), true, point);
        txtDate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
        txtDate1.requestFocus();
    }//GEN-LAST:event_cmdDateChoose1ActionPerformed

    private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
        Point point = cmdDateChoose2.getLocation();
        point.setLocation(point.getX(), point.getY());
        DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, cmdDateChoose1.getLocationOnScreen());
        dcd.setVisible(true);
        // dcd.showDialog(new LookAndFeelFrame(), true, point);
        txtDate2.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
        txtDate2.requestFocus();
    }//GEN-LAST:event_cmdDateChoose2ActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        LoadData();
    }//GEN-LAST:event_bntOKActionPerformed

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        btnExitClick();
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOK1ActionPerformed
        LoadData();
        ProcessProc();
    }//GEN-LAST:event_bntOK1ActionPerformed
    public void btnOKClick() {
        String TDate = txtDate1.getText();
        try {
            TDate1 = DatefmtShow.parse(txtDate1.getText());
            TDate2 = DatefmtShow.parse(txtDate2.getText());
            if (!PUtility.ChkValidDate(TDate1)) {
                txtDate1.requestFocus();
            }
            if (!PUtility.ChkValidDate(TDate2)) {
                txtDate2.requestFocus();
            }
            ProcessProc();
        } catch (ParseException ex) {
            MSG.ERR(this, "กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
        }

//        if (!txtDate1.getText().trim().equals("") || txtDate2.getText().trim().equals("")) {
//            LoadData();
//        }
    }

    public void ProcessProc() {
        PrintHourlyByCust();
    }

    public void btnExitClick() {
        this.dispose();
    }

    public void PrintHourlyByCust() {
        ArrayList<Object[]> ListObj = LoadData();
        if (Value.printdriver) {
            PrintHourlyDriverByCust();
        } else {
            if (!Value.getComPort().equals("NONE")) {
                if (prn.OpenPrint(Value.getComPort())) {
                    prn.InitPrinter();
                    prn.print(POSHW.getHeading1());
                    prn.print(POSHW.getHeading2());
                    prn.print(POSHW.getHeading3());
                    prn.print(POSHW.getHeading4());
                    prn.print("REG ID :" + Value.MACNO);
                    prn.print("       (MTD Customer count per Hours)");
                    prn.print("ช่วงวันที่  :" + txtDate1.getText() + " ..." + txtDate2.getText());
                    prn.print(" ");
                    Date dateP = new Date();
                    prn.print(DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    prn.print("----------------------------------------");
                    prn.print("    Time       CC       Bill      Amount");
                    prn.print("----------------------------------------");
                    if (ListObj != null && ListObj.size() > 0) {
                        String time = "-";
                        String DineIn = "0";
                        String TakeAway = "0";
                        String TTLCustomer = "0";
                        for (int i = 0; i < 24; i++) {
                            time = (ListObj.get(i)[0].toString().replace(" ", ""));
                            DineIn = (ListObj.get(i)[1].toString());
                            TakeAway = (ListObj.get(i)[2].toString());
                            TTLCustomer = (ListObj.get(i)[3].toString());
                            prn.print(time + " " + PUtility.DataFull((DineIn), 5) + PUtility.DataFull((TakeAway), 10) + PUtility.DataFull((TTLCustomer), 11));
                        }
                        prn.print(" ");
                        prn.print("TTL CC >>> " + ListObj.get(24)[0]);
                        prn.print("TTL Bill >>> " + ListObj.get(24)[1]);
                        prn.print("TTL Amount >>> " + ListObj.get(24)[2]);
                    }
                    prn.print("----------------------------------------");
                    prn.print("----------------------------------------");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");
                    prn.print(" ");

                    prn.CutPaper();
                    prn.closePrint();
                } else {
//                PUtility.showError("เครื่องพิมพ์ใบกำกับภาษีไม่สามารถพิมพ์ได้ ...");
                }
            }
        }
    }

    public void PrintHourlyDriverByCust() {
        ArrayList<Object[]> ListObj = LoadData();
        String t = "";
        if (POSHW.getHeading1().trim().length() >= 18) {
            String[] strs = POSHW.getHeading1().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading1().trim().replace(" ", "&nbsp; ") + "_";
        }
        if (POSHW.getHeading2().trim().length() >= 18) {
            String[] strs = POSHW.getHeading2().trim().replace(" ", Space).split("_");
            for (String data : strs) {
                t += "colspan=3 align=center><font face=Angsana New size=1>" + data + "_";
            }
        } else {
            t += "colspan=3 align=center><font face=Angsana New size=1>" + POSHW.getHeading2().trim().replace(" ", "&nbsp; ") + "_";
        }
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading3().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (POSHW.getHeading4().trim()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "REG.ID :" + POSHW.getTerminal() + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("(MTD Customer count per Hours)") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("ช่วงวันที่  :" + txtDate1.getText() + "..." + txtDate2.getText()) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + "_";
        Date dateP = new Date();
        t += "colspan=3 align=center><font face=Angsana New size=1>" + (DatefmtThai.format(dateP) + Space + "Cashier:" + PublicVar._User + Space + "Mac:" + Space + Value.MACNO) + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("Time" + Space + "CC" + Space + "Bill" + Space + "Amount" + "_");
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------" + "_");
        if (ListObj != null && ListObj.size() > 0) {
            String time = "-";
            String DineIn = "0";
            String TakeAway = "0";
            String TTLCustomer = "0";
            for (int i = 0; i < 24; i++) {
                time = (ListObj.get(i)[0].toString().replace(" ", ""));
                DineIn = (ListObj.get(i)[1].toString());
                TakeAway = (ListObj.get(i)[2].toString());
                TTLCustomer = (ListObj.get(i)[3].toString());
                t += "colspan=3 align=center><font face=Angsana New size=1>" + time + PUtility.DataFullSpace((DineIn), 5) + PUtility.DataFullSpace((TakeAway), 10) + PUtility.DataFullSpace((TTLCustomer), 11) + "_";
            }
            t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------") + "_";
            t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + ("TTL CC : " + "</td><td align=right><font face=Angsana New size=1>" + ListObj.get(24)[0]) + "_";
            t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + ("TTL Bill : " + "</td><td align=right><font face=Angsana New size=1>" + ListObj.get(24)[1]) + "_";
            t += "colspan=2 align=left><font face=Angsana New size=1>" + TAB + ("TTL Amount : " + "</td><td align=right><font face=Angsana New size=1>" + ListObj.get(24)[2]) + "_";
        }
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("-----------------------------------------------------") + "_";
        t += "colspan=3 align=center><font face=Angsana New size=1>" + ("_");

        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");

        for (String data1 : strs) {
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        pd.printHTML();
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
            java.util.logging.Logger.getLogger(MTDHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MTDHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MTDHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MTDHourlyOpenTB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MTDHourlyOpenTB dialog = new MTDHourlyOpenTB(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntOK1;
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblSalePerHour;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    // End of variables declaration//GEN-END:variables
}
