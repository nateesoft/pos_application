package com.softpos.floorplan;

import database.MySQLConnect;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import printReport.PrintSimpleForm;
import com.softpos.main.program.BalanceControl;
import com.softpos.main.program.EMPListDialog;
import com.softpos.main.program.TableFileBean;
import com.softpos.main.program.TableFileControl;
import com.softpos.main.program.TableMoveControl;
import java.sql.SQLException;
import java.sql.Statement;
import soft.virtual.KeyBoardDialog;
import util.MSG;

public class MoveGroupTable extends javax.swing.JDialog {

    private String tableNo;

    public MoveGroupTable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        txtTable1.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTable1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTable2 = new javax.swing.JTextField();
        txtUser = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Move Table Form (บันทึกการย้าย/รวมโต๊ะ)");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ย้ายข้อมูลจากโต๊ะ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ไปยังโต๊ะ");

        txtTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTable1MouseClicked(evt);
            }
        });
        txtTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTable1KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("กรุณาป้อนหมายเลขโต๊ะที่ต้องการย้าย หรือรวมโต๊ะ !!!");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ผู้ดูแล");

        txtTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTable2MouseClicked(evt);
            }
        });
        txtTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTable2KeyPressed(evt);
            }
        });

        txtUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserMouseClicked(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(txtTable2))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton10.setText("CE");
        jButton10.setFocusable(false);
        jButton10.setRequestFocusEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton11.setText("/");
        jButton11.setFocusable(false);
        jButton11.setRequestFocusEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton12.setText("*");
        jButton12.setFocusable(false);
        jButton12.setRequestFocusEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton13.setText("-");
        jButton13.setFocusable(false);
        jButton13.setRequestFocusEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setText("Enter");
        jButton14.setFocusable(false);
        jButton14.setRequestFocusEnabled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setText("9");
        jButton4.setFocusable(false);
        jButton4.setRequestFocusEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setText("8");
        jButton5.setFocusable(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setText("7");
        jButton6.setFocusable(false);
        jButton6.setRequestFocusEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setText("4");
        jButton7.setFocusable(false);
        jButton7.setRequestFocusEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setText("5");
        jButton8.setFocusable(false);
        jButton8.setRequestFocusEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton9.setText("6");
        jButton9.setFocusable(false);
        jButton9.setRequestFocusEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton15.setText("3");
        jButton15.setFocusable(false);
        jButton15.setRequestFocusEnabled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton16.setText("2");
        jButton16.setFocusable(false);
        jButton16.setRequestFocusEnabled(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton17.setText("1");
        jButton17.setFocusable(false);
        jButton17.setRequestFocusEnabled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton18.setText("0");
        jButton18.setFocusable(false);
        jButton18.setRequestFocusEnabled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton19.setText(".");
        jButton19.setFocusable(false);
        jButton19.setRequestFocusEnabled(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton20.setText("+");
        jButton20.setFocusable(false);
        jButton20.setRequestFocusEnabled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTable1.getText().trim().equals("")) {
                txtTable1.setText(txtTable1.getText().toUpperCase());
                txtTable2.requestFocus();
            } else {
                txtTable1.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EMPListDialog emp = new EMPListDialog(null, true);
        emp.setVisible(true);

        if (EMPListDialog.data != null || EMPListDialog.data.length == 0) {
            txtUser.setText(EMPListDialog.data[0]);
//            lbName.setText(EMPListDialog.data[1]);
        } else {
            txtUser.setText("");
//            lbName.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTable2.getText().trim().equals("")) {
                txtTable2.setText(txtTable2.getText().toUpperCase());
                txtUser.requestFocus();
            } else {
                txtTable2.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTable2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (validData()) {
            TableFileControl tbControl = new TableFileControl();
            TableFileBean t1 = tbControl.getData(txtTable1.getText().toUpperCase());
            TableFileBean t2 = tbControl.getData(txtTable2.getText().toUpperCase());

            boolean isSave = false;
            if (t1 == null || t2 == null) {
                //กำหนดเบอร์โต๊ะไม่ถูกต้อง
                MSG.ERR(this, "ท่านกำหนดเบอร์โต๊ะไม่ถูกต้อง กรุณาตรวจสอบ !!!");
                txtTable1.setText("");
                txtTable2.setText("");

                txtTable1.requestFocus();
            } else if (t1.getTcode().toUpperCase().equals(t2.getTcode().toUpperCase())) {
                //กรุณาป้อนหมายเลขโต๊ะให้ถูกต้อง
                MSG.ERR(this, "ท่านกำหนดเบอร์โต๊ะไม่ถูกต้อง กรุณาตรวจสอบ !!!");
                txtTable1.setText("");
                txtTable2.setText("");

                txtTable1.requestFocus();
            } else if (t1.getSoneCode() == null || t2.getSoneCode() == null) {
                //หมายเลขโต๊ะนี้ไม่อยู่ในโซนที่เครื่อง POS ยืนยันการย้ายโต๊ะหรือไม่

                if (t1.getTAmount() == 0 && t2.getTAmount() >= 0) {
                    //ไม่พบข้อมูลที่จะทำการย้าย
                    MSG.ERR(this, "ไม่พบข้อมูลการขาย กรุณาตรวจสอบ !!!");
                    txtTable1.setText("");
                    txtTable2.setText("");

                    txtTable1.requestFocus();
                } else if (t1.getTAmount() > 0 && t2.getTAmount() == 0) {
                    //ข้อมูลที่ทำการย้ายจากโต๊ะ 1 ไป โต๊ะ 2 จำนวน 1 รายการ
                    MSG.NOTICE(this, "ข้อมูลที่ทำการย้ายจากโต๊ะ " + t1.getTcode().toUpperCase() + " ไป โต๊ะ " + txtTable2.getText().toUpperCase() + " มูลค่า " + t1.getTAmount() + "");
                    isSave = true;
                } else if (t1.getTAmount() > 0 && t2.getTAmount() > 0) {
                    //โต๊ะหมายเลข 4 มีข้อมูลอยู่แล้วต้องการจะรวมโต๊ะใช่หรือไม่ ?
                    int confirm = JOptionPane.showConfirmDialog(this, "โต๊ะหมายเลข " + t2.getTAmount() + " มีข้อมูลอยู่แล้วต้องการจะรวมโต๊ะใช่หรือไม่ ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        //ข้อมูลที่ทำการย้ายจากโต๊ะ 1 ไปรวมกันกับโต๊ะ 3 จำนวน 2 รายการ
                        MSG.NOTICE(this, "ข้อมูลที่ทำการย้ายจากโต๊ะ " + t1.getTcode().toUpperCase() + " ไป โต๊ะ " + t2.getTcode().toUpperCase() + " มูลค่า " + t1.getTAmount() + "");
                        isSave = true;
                    } else {
                        isSave = false;
                    }
                }
            } else if (t1.getTAmount() == 0 && t2.getTAmount() >= 0) {
                //ไม่พบข้อมูลที่จะทำการย้าย
                MSG.ERR(this, "ไม่พบข้อมูลการขาย กรุณาตรวจสอบ !!!");
                txtTable1.setText("");
                txtTable2.setText("");

                txtTable1.requestFocus();
            } else if (t1.getTAmount() > 0 && t2.getTAmount() == 0) {
                //ข้อมูลที่ทำการย้ายจากโต๊ะ 1 ไป โต๊ะ 2 จำนวน 1 รายการ
                MSG.NOTICE(this, "ข้อมูลที่ทำการย้ายจากโต๊ะ " + t1.getTcode().toUpperCase() + " ไป โต๊ะ " + txtTable2.getText().toUpperCase() + " มูลค่า " + t1.getTAmount() + "");
                isSave = true;
            } else if (t1.getTAmount() > 0 && t2.getTAmount() > 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "โต๊ะหมายเลข " + t2.getTcode().toUpperCase() + " มีข้อมูลอยู่แล้วต้องการจะรวมโต๊ะใช่หรือไม่ ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    //ข้อมูลที่ทำการย้ายจากโต๊ะ 1 ไปรวมกันกับโต๊ะ 3 จำนวน 2 รายการ
                    MSG.NOTICE(this, "ข้อมูลที่ทำการย้ายจากโต๊ะ " + t1.getTcode().toUpperCase() + " ไป โต๊ะ " + t2.getTcode().toUpperCase() + " จำนวน " + t1.getTAmount() + "");
                    isSave = true;
                } else {
                    isSave = false;
                }
            }

            if (isSave) {
                updateForVoidAfterMoveTable(txtTable2.getText().toUpperCase());
                PrintSimpleForm s = new PrintSimpleForm();

                // update customer count
                /**
                 * * OPEN CONNECTION **
                 */
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();
                try {
                    int ct1 = 0, ct2 = 0, c = 0;
                    String sqlPlusTCust1 = "select tcustomer tcustFrom1 "
                            + "from tablefile "
                            + "where tcode='" + txtTable1.getText() + "'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rs1 = stmt1.executeQuery(sqlPlusTCust1);
                    if (rs1.next()) {
                        ct1 = rs1.getInt("tcustFrom1");
                    }

                    rs1.close();
                    stmt1.close();

                    String sqlPlusTCust2 = "select tcustomer tcustFrom2 "
                            + "from tablefile "
                            + "where tcode='" + txtTable2.getText() + "'";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    ResultSet rs2 = stmt2.executeQuery(sqlPlusTCust2);
                    if (rs2.next()) {
                        ct2 = rs2.getInt("tcustFrom2");
                    }
                    rs2.close();
                    stmt2.close();

                    c = ct1 + ct2;
                    String sql = "update tablefile "
                            + "set tcustomer='" + c + "' "
                            + "where tcode='" + txtTable2.getText().toUpperCase() + "'";
                    Statement stmt3 = mysql.getConnection().createStatement();
                    stmt3.executeUpdate(sql);
                    stmt3.close();

                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    e.printStackTrace();
                } finally {
                    mysql.close();
                }

                // backup tmp
                tmpTableBeforeMove(txtTable1.getText().toUpperCase());

                TableMoveControl.moveTable(txtTable1.getText().toUpperCase(), txtTable2.getText().toUpperCase());

                //คำนวณโปรโมชัน + ค่าบริการ และคำนวณภาษีมูลค่าเพิ่ม
                BalanceControl.updateProSerTable(txtTable2.getText().toUpperCase(), null);
                //printBillCheck();

                String strKic = "";
                /*
                 ค้นหาปริ้นเตอร์ในตารางนั้นๆ ก่อนว่ามีปริ้นเตอร์อะไรบ้าง ที่จะต้องปริ้นออก
                 */
                mysql.open();
                try {
                    String sql1 = "select r_kic from balance where r_table='" + txtTable2.getText() + "' group by r_kic;";
                    Statement stmt = mysql.getConnection().createStatement();
                    ResultSet rs = stmt.executeQuery(sql1);
                    while (rs.next()) {
                        strKic += "kic" + rs.getString("r_kic") + ",";//เก็บข้อมูลปริ้นเตอร์ลงในตัวแปร
                    }

                    rs.close();
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    e.printStackTrace();
                } finally {
                    mysql.close();
                }

                //แยกปริ้นเตอร์ สำหรับพิมพ์ออกจากระบบ
                String[] RKic = strKic.split(",");
                for (String RKic1 : RKic) {
                    try {
                        s.KIC_FORM_Move(txtTable1.getText(), txtTable2.getText(), RKic1);
                    } catch (Exception ex) {
                        MSG.ERR(ex.getMessage());
                        ex.printStackTrace();
                    }
                }

                // clear tmp
                //clearTmpTableBeforeMove();
                dispose();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton2ActionPerformed(null);
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void txtTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTable1MouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtTable1, 4);
        }
    }//GEN-LAST:event_txtTable1MouseClicked

    private void txtTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTable2MouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtTable2, 4);
        }
    }//GEN-LAST:event_txtTable2MouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //input("+");
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        //input(".");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        input("0");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        input("1");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        input("2");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        input("3");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        input("6");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        input("5");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        input("4");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        input("7");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        input("8");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        input("9");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        SaveDataPaidin();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        //input("-");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        input("*");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        input("/");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (txtTable1.hasFocus()) {
            txtTable1.setText("");
            txtTable1.requestFocus();
        } else if (txtTable2.hasFocus()) {
            txtTable2.setText("");
            txtTable2.requestFocus();
        } else if (txtUser.hasFocus()) {
            txtUser.setText("");
            txtUser.requestFocus();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            jButton1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            process();
        }

    }//GEN-LAST:event_txtUserKeyPressed

    private void txtUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserMouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtUser, 4);
        }
    }//GEN-LAST:event_txtUserMouseClicked

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
            java.util.logging.Logger.getLogger(MoveGroupTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoveGroupTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoveGroupTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoveGroupTable.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MoveGroupTable dialog = new MoveGroupTable(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTable1;
    private javax.swing.JTextField txtTable2;
    private javax.swing.JPasswordField txtUser;
    // End of variables declaration//GEN-END:variables

    private void input(String str) {
        if (txtTable1.hasFocus()) {
            String temp = txtTable1.getText();
            if (str.equals("clear")) {
                txtTable1.setText("");
                txtTable1.requestFocus();
            } else if (str.equals("enter")) {
                txtTable2.requestFocus();
            } else {
                String data = temp + str;
                txtTable1.setText(data);
            }
        } else if (txtTable2.hasFocus()) {
            String temp = txtTable2.getText();
            if (str.equals("clear")) {
                txtTable2.setText("");
            } else if (str.equals("enter")) {
                txtUser.requestFocus();
            } else {
                String data = temp + str;
                txtTable2.setText(data);
            }
        } else if (txtUser.hasFocus()) {
            String temp = txtUser.getText();
            if (str.equals("clear")) {
                txtUser.setText("");
            } else if (str.equals("enter")) {
                process();
            } else {
                String data = temp + str;
                txtUser.setText(data);
            }
        }
    }

    private void process() {
        if (!txtUser.getText().equals("")) {
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                String sql = "select code from employ where code='" + txtUser.getText() + "'";
                Statement stmt = mysql.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    jButton2.requestFocus();
                } else {
                    MSG.ERR(this, "กรุณาระบุรหัสบริกรให้ถูกต้อง เนื่องจากไม่พบข้อมูลในระบบ !!!");
                    txtUser.setText("");
                    txtUser.requestFocus();
                }

                rs.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                e.printStackTrace();
            } finally {
                mysql.close();
            }
        } else {
            txtUser.requestFocus();
        }
    }

    private void backText() {
        String temp;
        if (txtTable1.hasFocus()) {
            temp = txtTable1.getText();
            if (temp.length() > 0) {
                temp = temp.substring(0, temp.length() - 1);
            }

            txtTable1.setText(temp);
        } else if (txtTable2.hasFocus()) {
            temp = txtTable2.getText();
            if (temp.length() > 0) {
                temp = temp.substring(0, temp.length() - 1);
            }

            txtTable2.setText(temp);
        } else if (txtUser.hasFocus()) {
            temp = txtUser.getText();
            if (temp.length() > 0) {
                temp = temp.substring(0, temp.length() - 1);
            }

            txtUser.setText(temp);
        }
    }

    private boolean validData() {
        if (txtTable1.getText().equals("")) {
            txtTable1.requestFocus();
            return false;
        }
        if (txtTable2.getText().equals("")) {
            txtTable2.requestFocus();
            return false;
        }
        if (txtUser.getText().equals("")) {
            txtUser.requestFocus();
            return false;
        }

        return true;
    }

    private void SaveDataPaidin() {
        if (txtTable1.hasFocus()) {
            txtTable2.requestFocus();
        } else if (txtTable2.hasFocus()) {
            txtUser.requestFocus();
        } else if (txtUser.hasFocus()) {
            txtTable1.requestFocus();
        }
    }

    private void updateForVoidAfterMoveTable(String table) {
        try {
            String sql = "update balance set r_spindex=r_index ,r_linkIndex=r_index where r_table='" + table + "'";
            MySQLConnect c = new MySQLConnect();
            c.open();
            c.getConnection().createStatement().executeUpdate(sql);
            c.close();
        } catch (Exception e) {
            MSG.ERR(e.toString());
        }
    }

    private void tmpTableBeforeMove(String table) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String[] sql = new String[]{
                "drop table if exists tmp_tablefile;",
                "drop table if exists tmp_balance;",
                "create table if not exists tmp_tablefile select * from tablefile where tcode='" + table + "';",
                "create table if not exists tmp_balance select * from balance where r_table = '" + table + "';"
            };
            Statement stmt = mysql.getConnection().createStatement();
            for (int i = 0; i < sql.length; i++) {
                stmt.executeUpdate(sql[i]);
            }
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

}
