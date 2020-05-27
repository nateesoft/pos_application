package com.softpos.main.program;

import database.MySQLConnect;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class AddNewArCustomer extends javax.swing.JDialog {

    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();

    public AddNewArCustomer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        ClearVariable();
        sp_code.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sp_code = new javax.swing.JTextField();
        sp_desc = new javax.swing.JTextField();
        sp_address1 = new javax.swing.JTextField();
        sp_address2 = new javax.swing.JTextField();
        sp_zip = new javax.swing.JTextField();
        sp_tel = new javax.swing.JTextField();
        sp_contack = new javax.swing.JTextField();
        sp_remark = new javax.swing.JTextField();
        sp_tax = new javax.swing.JTextField();
        sp_cramount = new javax.swing.JFormattedTextField();
        sp_crday = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        bntSave = new javax.swing.JButton();
        bntCancel = new javax.swing.JButton();
        bntRemove = new javax.swing.JButton();
        bntDisplay = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ปรับรุงรายการลูกหนี้การค้า");
        setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("รหัสลูกหนี้");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ชื่อลูกหนี้/บริษัท");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ที่อยู่");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("รหัสไปรษณีย์");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("โทรศัพท์");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("ชื่อผู้ติดต่อ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("หมายเหตุ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("เลขประจำตัวผู้เสียภาษี");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("วงเงินสินเชื่อ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("จำนวนวันที่ให้เครดิต");

        sp_code.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_codeKeyPressed(evt);
            }
        });

        sp_desc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_desc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_descKeyPressed(evt);
            }
        });

        sp_address1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_address1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_address1KeyPressed(evt);
            }
        });

        sp_address2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_address2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_address2KeyPressed(evt);
            }
        });

        sp_zip.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_zip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_zipKeyPressed(evt);
            }
        });

        sp_tel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_telKeyPressed(evt);
            }
        });

        sp_contack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_contack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_contackKeyPressed(evt);
            }
        });

        sp_remark.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_remark.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_remarkKeyPressed(evt);
            }
        });

        sp_tax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_tax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_taxKeyPressed(evt);
            }
        });

        sp_cramount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        sp_cramount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sp_cramount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_cramountKeyPressed(evt);
            }
        });

        sp_crday.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        sp_crday.setFont(new java.awt.Font("Nueva Std", 0, 14)); // NOI18N
        sp_crday.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sp_crdayKeyPressed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_crday, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_cramount, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_remark, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_contack, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_zip, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_address2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_address1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sp_code, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sp_code, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sp_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sp_address1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addComponent(sp_address2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(sp_zip, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(sp_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(sp_contack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(sp_remark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(sp_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(sp_cramount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sp_crday, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        bntSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntSave.setText("บันทึกข้อมูล");
        bntSave.setFocusable(false);
        bntSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntSave.setIconTextGap(0);
        bntSave.setPreferredSize(new java.awt.Dimension(80, 70));
        bntSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSaveActionPerformed(evt);
            }
        });
        jPanel2.add(bntSave);

        bntCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntCancel.setText("  ยกเลิก  ");
        bntCancel.setFocusable(false);
        bntCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntCancel.setIconTextGap(0);
        bntCancel.setPreferredSize(new java.awt.Dimension(80, 70));
        bntCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelActionPerformed(evt);
            }
        });
        jPanel2.add(bntCancel);

        bntRemove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntRemove.setText("ลบข้อมูล");
        bntRemove.setFocusable(false);
        bntRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntRemove.setIconTextGap(0);
        bntRemove.setPreferredSize(new java.awt.Dimension(80, 70));
        bntRemove.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntRemoveActionPerformed(evt);
            }
        });
        jPanel2.add(bntRemove);

        bntDisplay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntDisplay.setText("แสดงรายการ");
        bntDisplay.setFocusable(false);
        bntDisplay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntDisplay.setIconTextGap(0);
        bntDisplay.setPreferredSize(new java.awt.Dimension(90, 70));
        bntDisplay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDisplayActionPerformed(evt);
            }
        });
        jPanel2.add(bntDisplay);

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ออก(Exit)");
        bntExit.setFocusable(false);
        bntExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntExit.setIconTextGap(0);
        bntExit.setPreferredSize(new java.awt.Dimension(80, 70));
        bntExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });
        jPanel2.add(bntExit);

        jMenu1.setText("Function");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMenuItem1.setText("ยกเลิกการแก้ไข (Cancel Edit)");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMenuItem2.setText("บันทึกข้อมูล (Save/Update)");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMenuItem3.setText("ลบข้อมูล (Delete/Remove)");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMenuItem4.setText("ออกจากการทำงาน (Exit)");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(650, 506));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void sp_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_codeKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_codeExit();
    } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntDisplayClick();
    }
}//GEN-LAST:event_sp_codeKeyPressed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    ClearVariable();
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    bntSaveClick();
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    bntRemoveClick();
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    bntExitClick();
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void sp_descKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_descKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_address1.requestFocus();
    }
}//GEN-LAST:event_sp_descKeyPressed

private void sp_address1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_address1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_address2.requestFocus();
    }
}//GEN-LAST:event_sp_address1KeyPressed

private void sp_address2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_address2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_zip.requestFocus();
    }
}//GEN-LAST:event_sp_address2KeyPressed

private void sp_zipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_zipKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_tel.requestFocus();
    }
}//GEN-LAST:event_sp_zipKeyPressed

private void sp_telKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_telKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_contack.requestFocus();
    }
}//GEN-LAST:event_sp_telKeyPressed

private void sp_contackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_contackKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_remark.requestFocus();
    }
}//GEN-LAST:event_sp_contackKeyPressed

private void sp_remarkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_remarkKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_tax.requestFocus();
    }
}//GEN-LAST:event_sp_remarkKeyPressed

private void sp_taxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_taxKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_cramount.requestFocus();
    }
}//GEN-LAST:event_sp_taxKeyPressed

private void sp_cramountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_cramountKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_crday.requestFocus();
    }
}//GEN-LAST:event_sp_cramountKeyPressed

private void sp_crdayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sp_crdayKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        sp_desc.requestFocus();
    }
}//GEN-LAST:event_sp_crdayKeyPressed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    FindAr1Click();
}//GEN-LAST:event_jButton5ActionPerformed

    private void bntSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSaveActionPerformed
        bntSaveClick();
    }//GEN-LAST:event_bntSaveActionPerformed

    private void bntCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelActionPerformed
        ClearVariable();
    }//GEN-LAST:event_bntCancelActionPerformed

    private void bntRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntRemoveActionPerformed
        bntRemoveClick();
    }//GEN-LAST:event_bntRemoveActionPerformed

    private void bntDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDisplayActionPerformed
        bntDisplayClick();
    }//GEN-LAST:event_bntDisplayActionPerformed

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        bntExitClick();
    }//GEN-LAST:event_bntExitActionPerformed
    public void FindAr1Click() {
        FindAr frm = new FindAr(null, true);
        frm.setVisible(true);
        if (!PublicVar.ReturnString.equals("")) {
            sp_code.setText(PublicVar.ReturnString);
        }
    }

    public void bntDisplayClick() {
        FindAr frm = new FindAr(null, true);
        frm.setVisible(true);
        if (!PublicVar.ReturnString.equals("")) {
            sp_code.setText(PublicVar.ReturnString);
            sp_codeExit();
        }

    }

    public void bntRemoveClick() {
        String TempCode = sp_code.getText();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        if (!TempCode.equals("")) {
            if (PUtility.ShowConfirmMsg("ยืนยันการลบข้อมูลลูกหนี้การค้า รายการนี้ ?")) {
                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String SQLQuery = "delete from custfile where sp_code='" + TempCode + "'";
                    stmt.executeUpdate(SQLQuery);
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    e.printStackTrace();
                }
                ClearVariable();
            }
        }

        mysql.close();
    }

    public void bntSaveClick() {
        String TempCode = sp_code.getText();
        if (!TempCode.equals("")) {
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            if (SeekCustFile(TempCode)) {
                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String SQLQuery = "update custfile set sp_desc='" + ThaiUtil.Unicode2ASCII(sp_desc.getText()) + "',"
                            + "sp_addr1='" + ThaiUtil.Unicode2ASCII(sp_address1.getText()) + "',"
                            + "sp_addr2='" + ThaiUtil.Unicode2ASCII(sp_address2.getText()) + "',"
                            + "sp_zip='" + sp_zip.getText() + "',"
                            + "contack='" + ThaiUtil.Unicode2ASCII(sp_contack.getText()) + "',"
                            + "tel='" + sp_tel.getText() + "',"
                            + "fax='',"
                            + "remark='" + ThaiUtil.Unicode2ASCII(sp_remark.getText()) + "',"
                            + "sp_tax='" + sp_tax.getText() + "',"
                            + "sp_cr=" + sp_crday.getValue() + ","
                            + "sp_cramt=" + sp_cramount.getValue() + ","
                            + "sp_date='" + Datefmt.format(date) + "' "
                            + "where sp_code='" + TempCode + "'";
                    stmt.executeUpdate(SQLQuery);
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    e.printStackTrace();
                }
                ClearVariable();
            } else {
                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String SQLQuery = "insert into custfile (sp_code,sp_desc,sp_addr1,sp_addr2,sp_zip,contack,tel,fax,remark,"
                            + "sp_tax,sp_cr,sp_cramt) "
                            + "values ('" + sp_code.getText() + "','" + ThaiUtil.Unicode2ASCII(sp_desc.getText()) + "','" + ThaiUtil.Unicode2ASCII(sp_address1.getText()) + "',"
                            + "'" + ThaiUtil.Unicode2ASCII(sp_address2.getText()) + "','" + sp_zip.getText() + "','" + ThaiUtil.Unicode2ASCII(sp_contack.getText()) + "',"
                            + "'" + sp_tel.getText() + "','','" + ThaiUtil.Unicode2ASCII(sp_remark.getText()) + "','" + sp_tax.getText() + "',"
                            + "" + sp_crday.getValue() + "," + sp_cramount.getValue() + ")";
                    stmt.executeUpdate(SQLQuery);
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }
                ClearVariable();
            }

            mysql.close();
        }

    }

    public void ClearVariable() {
        sp_code.setText("");
        sp_desc.setText("");
        sp_address1.setText("");
        sp_address2.setText("");
        sp_zip.setText("");
        sp_tel.setText("");
        sp_contack.setText("");
        sp_remark.setText("");
        sp_tax.setText("");
        sp_cramount.setText("");
        sp_crday.setText("");
        sp_code.setFocusable(true);
        sp_desc.setFocusable(false);
        sp_address1.setFocusable(false);
        sp_address2.setFocusable(false);
        sp_tel.setFocusable(false);
        sp_zip.setFocusable(false);
        sp_contack.setFocusable(false);
        sp_remark.setFocusable(false);
        sp_tax.setFocusable(false);
        sp_cramount.setFocusable(false);
        sp_crday.setFocusable(false);
        sp_code.requestFocus();

    }

    public void sp_codeExit() {
        String TempCode = sp_code.getText();
        if (!TempCode.equals("")) {
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SQLQuery = "Select *from custfile "
                        + "where sp_code='" + TempCode + "'";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    sp_desc.setText(ThaiUtil.ASCII2Unicode(rec.getString("sp_desc")));
                    sp_address1.setText(ThaiUtil.ASCII2Unicode(rec.getString("sp_addr1")));
                    sp_address2.setText(ThaiUtil.ASCII2Unicode(rec.getString("sp_addr2")));
                    sp_zip.setText(rec.getString("sp_zip"));
                    sp_tel.setText(rec.getString("tel"));
                    sp_contack.setText(ThaiUtil.ASCII2Unicode(rec.getString("contack")));
                    sp_remark.setText(ThaiUtil.ASCII2Unicode(rec.getString("remark")));
                    sp_tax.setText(rec.getString("sp_tax"));
                    sp_cramount.setValue(rec.getDouble("sp_cramt"));
                    sp_crday.setValue(rec.getInt("sp_cr"));
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                e.printStackTrace();
            } finally{
                mysql.close();
            }
            
            GetDataValue();
        }
    }

    public Boolean SeekCustFile(String TempCode) {
        Boolean RetVal = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "Select *from custfile where sp_code='" + TempCode + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = false;
            } else {
                RetVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }
        
        return RetVal;
    }

    public void GetDataValue() {
        sp_code.setFocusable(false);
        sp_desc.setFocusable(true);
        sp_address1.setFocusable(true);
        sp_address2.setFocusable(true);
        sp_tel.setFocusable(true);
        sp_zip.setFocusable(true);
        sp_contack.setFocusable(true);
        sp_remark.setFocusable(true);
        sp_tax.setFocusable(true);
        sp_cramount.setFocusable(true);
        sp_crday.setFocusable(true);
        sp_desc.requestFocus();
    }

    public void bntExitClick() {
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddNewArCustomer dialog = new AddNewArCustomer(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bntCancel;
    private javax.swing.JButton bntDisplay;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntRemove;
    private javax.swing.JButton bntSave;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField sp_address1;
    private javax.swing.JTextField sp_address2;
    private javax.swing.JTextField sp_code;
    private javax.swing.JTextField sp_contack;
    private javax.swing.JFormattedTextField sp_cramount;
    private javax.swing.JFormattedTextField sp_crday;
    private javax.swing.JTextField sp_desc;
    private javax.swing.JTextField sp_remark;
    private javax.swing.JTextField sp_tax;
    private javax.swing.JTextField sp_tel;
    private javax.swing.JTextField sp_zip;
    // End of variables declaration//GEN-END:variables
}
