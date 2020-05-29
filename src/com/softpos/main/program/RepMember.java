package com.softpos.main.program;

import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PublicVar;
import java.awt.Color;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import com.softpos.main.utils.DateChooseDialog;
import com.softpos.main.utils.MSG;

public class RepMember extends javax.swing.JDialog {

    DefaultTableModel model2;
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    Double XTotalCnt = 0.0;
    Double XTotalAmt = 0.0;
    Double XTotalDisc = 0.0;
    PPrint prn = new PPrint();

    /**
     * Creates new form ArNotPay
     */
    public RepMember(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        model2 = (DefaultTableModel) tblShow.getModel();
        tblShow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblShow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblShow.setRowSelectionAllowed(true);
        tblShow.setShowGrid(true);
        tblShow.setGridColor(Color.BLACK);

        JTableHeader header = tblShow.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

        int[] ColSize = {100, 200, 100, 150, 150, 100, 80, 100};
        for (int i = 0; i < 8; i++) {
            //int vColIndex = 0;
            TableColumn col = tblShow.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }
        ClearVariable();

        arcode1.requestFocus();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TotalCnt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TotalAmt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TotalDisc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        arcode1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        arcode2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ardate1 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        ardate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose2 = new javax.swing.JButton();
        cmdDateChoose3 = new javax.swing.JButton();
        bntOk = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการซื้อของสมาชิก");
        setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tblShow.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        tblShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสมาชิก", "ชื่อสมาชิก", "วันที่ซื้อ", "เลขที่ใบเสร็จ", "จำนวนเงิน", "ส่วนลด", "แต้ม", "ยอดซื้อสะสม"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShow.setFocusTraversalPolicyProvider(true);
        tblShow.setNextFocusableComponent(arcode1);
        tblShow.setRowHeight(25);
        tblShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblShowKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblShow);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("จำนวนรายการทั้งสิ้น");

        TotalCnt.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalCnt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalCnt.setFocusable(false);
        TotalCnt.setRequestFocusEnabled(false);

        jLabel6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel6.setText("จำนวนเงินรวม");

        TotalAmt.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalAmt.setFocusable(false);
        TotalAmt.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel7.setText("จำนวนส่วนลดรวม");

        TotalDisc.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        TotalDisc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalDisc.setFocusable(false);
        TotalDisc.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalCnt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(TotalCnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(TotalDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setFocusable(false);

        arcode1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        arcode1.setNextFocusableComponent(arcode2);
        arcode1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arcode1KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel2.setText("ถึง");

        arcode2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        arcode2.setNextFocusableComponent(ardate1);
        arcode2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arcode2KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel1.setText("รหัสสมาชิก");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifying glass.jpg"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setRequestFocusEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifying glass.jpg"))); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(arcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(arcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(arcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(arcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel3.setText("ช่วงวันที่");

        ardate1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ardate1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        ardate1.setNextFocusableComponent(ardate2);
        ardate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ardate1KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel4.setText("ถึง");

        ardate2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ardate2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        ardate2.setNextFocusableComponent(tblShow);
        ardate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ardate2KeyPressed(evt);
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

        cmdDateChoose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose3.setFocusable(false);
        cmdDateChoose3.setRequestFocusEnabled(false);
        cmdDateChoose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose3ActionPerformed(evt);
            }
        });
        cmdDateChoose3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmdDateChoose3FocusGained(evt);
            }
        });
        cmdDateChoose3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdDateChoose3KeyPressed(evt);
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
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(ardate2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(ardate1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ardate2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntOk.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntOk.setText("F5 ประมวลผล");
        bntOk.setFocusable(false);
        bntOk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntOk.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOk.setRequestFocusEnabled(false);
        bntOk.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOkActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntExit.setText("ออก(Exit)");
        bntExit.setFocusable(false);
        bntExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.setRequestFocusEnabled(false);
        bntExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jMenu1.setText("Function");
        jMenu1.setFocusable(false);
        jMenu1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jMenuItem1.setText("ประมาลผล ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jMenuItem3.setText("ออก (Exit)");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntOk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void tblShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowKeyPressed

    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        arcode1.requestFocus();;
    }

}//GEN-LAST:event_tblShowKeyPressed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void bntOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOkActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOkActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    FindAr1Click();
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    FindAr2Click();
}//GEN-LAST:event_jButton4ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    bntOKClick();
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    bntExitClick();
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void arcode1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arcode1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        FindAr1Click();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        arcode2.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_arcode1KeyPressed

private void arcode2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arcode2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        FindAr2Click();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ardate1.requestFocus();
    }
}//GEN-LAST:event_arcode2KeyPressed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
   //arcode1.requestFocus();
}//GEN-LAST:event_formWindowActivated

private void ardate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ardate1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ardate2.requestFocus();
    }
}//GEN-LAST:event_ardate1KeyPressed

private void ardate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ardate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        arcode1.requestFocus();
    }
}//GEN-LAST:event_ardate2KeyPressed

private void cmdDateChoose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose3ActionPerformed
    Point point = cmdDateChoose2.getLocationOnScreen();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(null, true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    ardate2.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
}//GEN-LAST:event_cmdDateChoose3ActionPerformed

private void cmdDateChoose3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmdDateChoose3FocusGained
// TODO add your handling code here:
}//GEN-LAST:event_cmdDateChoose3FocusGained

private void cmdDateChoose3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdDateChoose3KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_cmdDateChoose3KeyPressed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed

    Point point = cmdDateChoose2.getLocationOnScreen();
    point.setLocation(point.getX(), point.getY());
    com.softpos.main.utils.DateChooseDialog dcd = new com.softpos.main.utils.DateChooseDialog(new java.awt.Frame(), true, point);
    dcd.setVisible(true);
    //dcd.showDialog(new LookAndFeelFrame(), true, point);
    ardate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

    public void FindAr1Click() {
        FindMember frm = new FindMember(null, true);
        frm.setVisible(true);
        if (!PublicVar.ReturnString.equals("")) {
            arcode1.setText(PublicVar.ReturnString);
        }
    }

    public void FindAr2Click() {
        FindMember frm = new FindMember(null, true);
        frm.setVisible(true);
        if (!PublicVar.ReturnString.equals("")) {
            arcode2.setText(PublicVar.ReturnString);
        }
    }

    public void bntExitClick() {
        this.dispose();
    }

    public Boolean ChkValidDate() {
        Boolean RetVal = true;
        if (!PUtility.ChkDate(ardate1.getText())) {
            PUtility.ShowMsg("กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
            ardate1.requestFocus();
            RetVal = false;
        }
        if (!PUtility.ChkDate(ardate2.getText())) {
            PUtility.ShowMsg("กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
            ardate2.requestFocus();
            RetVal = false;
        }
        return RetVal;
    }

    public void bntOKClick() {
        if (ChkValidDate()) {
            String TempCode1 = arcode1.getText();
            String TempCode2 = "";
            Date TempDate1 = new Date();
            Date TempDate2 = new Date();
            XTotalCnt = 0.0;
            XTotalAmt = 0.0;
            XTotalDisc = 0.0;
            try {
                TempDate2 = ShowDatefmt.parse(ardate2.getText());
                TempDate1 = ShowDatefmt.parse(ardate1.getText());
            } catch (Exception e) {

            }
            if (arcode2.getText().equals("")) {
                TempCode2 = "ZZZZ";
            } else {
                TempCode2 = arcode2.getText();
            }
            int RowCount = model2.getRowCount();
            for (int i = 0; i <= RowCount - 1; i++) {
                model2.removeRow(0);
            }

            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SQLQuery = "select *from mtran left join memmaster on mtran.m_code=memmaster.m_code "
                        + "where (mtran.m_code>='" + TempCode1 + "') and (mtran.m_code<='" + TempCode2 + "') and (m_date>='" + Datefmt.format(TempDate1) + "') and (m_date<='" + Datefmt.format(TempDate2) + "') order by mtran.m_code,m_date,m_billno";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        XTotalCnt++;
                        XTotalAmt = XTotalAmt + rec.getDouble("m_netamt");
                        XTotalDisc = XTotalDisc + rec.getDouble("m_disc");
                        Object[] input = {rec.getString("m_code"),
                            rec.getString("m_name"),
                            ShowDatefmt.format(rec.getDate("m_date")),
                            rec.getString("m_billno"),
                            rec.getDouble("m_netamt"),
                            rec.getDouble("m_disc"),
                            rec.getDouble("m_score"),
                            rec.getDouble("m_sum")

                        };
                        model2.addRow(input);
                    } while (rec.next());
                    showCell(0, 0);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            } finally {
                mysql.close();
            }

            TotalCnt.setText(IntFmt.format(XTotalCnt));
            TotalAmt.setText(DecFmt.format(XTotalAmt));
            TotalDisc.setText(DecFmt.format(XTotalDisc));
            tblShow.setGridColor(Color.black);
            tblShow.setShowGrid(true);

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

    public void ClearVariable() {
        arcode1.setText("");
        arcode2.setText("");
        ardate1.setText(ShowDatefmt.format(date));
        ardate2.setText(ShowDatefmt.format(date));
        XTotalCnt = 0.0;
        XTotalAmt = 0.0;
        XTotalDisc = 0.0;
        TotalCnt.setText("");
        TotalAmt.setText("");

        arcode1.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ArNotPay dialog = new ArNotPay(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField TotalAmt;
    private javax.swing.JTextField TotalCnt;
    private javax.swing.JTextField TotalDisc;
    private javax.swing.JTextField arcode1;
    private javax.swing.JTextField arcode2;
    private javax.swing.JFormattedTextField ardate1;
    private javax.swing.JFormattedTextField ardate2;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOk;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JButton cmdDateChoose3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblShow;
    // End of variables declaration//GEN-END:variables
}
