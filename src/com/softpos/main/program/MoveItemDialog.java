package com.softpos.main.program;

import com.softpos.main.model.Value;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.controller.TableMoveControl;
import com.softpos.main.controller.TableFileControl;
import com.softpos.main.model.BalanceBean;
import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.softpos.main.utils.KeyBoardDialog;
import com.softpos.main.utils.MSG;

public class MoveItemDialog extends javax.swing.JDialog {

    private String TABLE_NO;
    private String TABLE_2;
    private boolean loadDataActive = false;

    public MoveItemDialog(java.awt.Frame parent, boolean modal, String TABLE_NO) {
        super(parent, modal);
        initComponents();

        this.TABLE_NO = TABLE_NO;
        this.TABLE_2 = TABLE_NO;

        txtTable1.setText(TABLE_NO);
        backupData();

        loadTable1();
        loadTable2();

        txtTable2.requestFocus();
    }

    public String getTable2() {
        return TABLE_2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTable2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTable1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ย้ายเฉพาะรายการสินค้า");
        setUndecorated(true);

        table1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P", "V", "R_ETD", "PLU_Code", "Description", "Qty", "Price", "Amount", "PRType", "Disc", "Baht", "R_Index"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setPreferredWidth(25);
            table1.getColumnModel().getColumn(1).setPreferredWidth(25);
            table1.getColumnModel().getColumn(2).setPreferredWidth(25);
            table1.getColumnModel().getColumn(3).setPreferredWidth(50);
            table1.getColumnModel().getColumn(4).setPreferredWidth(150);
            table1.getColumnModel().getColumn(5).setPreferredWidth(35);
            table1.getColumnModel().getColumn(6).setPreferredWidth(35);
            table1.getColumnModel().getColumn(7).setPreferredWidth(35);
            table1.getColumnModel().getColumn(8).setPreferredWidth(35);
            table1.getColumnModel().getColumn(9).setPreferredWidth(35);
            table1.getColumnModel().getColumn(10).setPreferredWidth(35);
        }

        jLabel1.setText("Table");

        txtTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("( โต๊ะที่ต้องการย้ายไป )");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("* การย้ายรายการอาหารจะสามารถย้ายรายการจากด้านบนลงด้านล่างเท่านั้น หากย้ายผิดให้ยกเลิกแล้วเริ่มใหม่อีกครั้ง");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("** การย้ายรายการอาหารจะถอดส่วนลดต่างๆ ออก และจะทำการคำนวณโปรโมชั่นใหม่");

        jLabel5.setText("Table");

        txtTable1.setEditable(false);
        txtTable1.setBackground(new java.awt.Color(255, 255, 255));
        txtTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTable1KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("( รายการอาหารของโต๊ะปัจจุบัน )");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("F5-ตกลง (OK)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("F3- ยกเลิก (Cancel)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        table2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P", "V", "R_ETD", "PLU_Code", "Description", "Qty", "Price", "Amount", "PRType", "Disc", "Baht", "R_Index"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setPreferredWidth(25);
            table2.getColumnModel().getColumn(1).setPreferredWidth(25);
            table2.getColumnModel().getColumn(2).setPreferredWidth(25);
            table2.getColumnModel().getColumn(3).setPreferredWidth(50);
            table2.getColumnModel().getColumn(4).setPreferredWidth(150);
            table2.getColumnModel().getColumn(5).setPreferredWidth(35);
            table2.getColumnModel().getColumn(6).setPreferredWidth(35);
            table2.getColumnModel().getColumn(7).setPreferredWidth(35);
            table2.getColumnModel().getColumn(8).setPreferredWidth(35);
            table2.getColumnModel().getColumn(9).setPreferredWidth(35);
            table2.getColumnModel().getColumn(10).setPreferredWidth(35);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(1, 10));

        jButton13.setBackground(new java.awt.Color(255, 204, 204));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton13.setText("<<");
        jButton13.setRequestFocusEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13);

        jButton3.setBackground(new java.awt.Color(0, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("9");
        jButton3.setRequestFocusEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setBackground(new java.awt.Color(0, 204, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("8");
        jButton4.setRequestFocusEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setBackground(new java.awt.Color(0, 204, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setText("7");
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(0, 204, 204));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("6");
        jButton6.setRequestFocusEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);

        jButton7.setBackground(new java.awt.Color(0, 204, 204));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setText("5");
        jButton7.setRequestFocusEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);

        jButton8.setBackground(new java.awt.Color(0, 204, 204));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton8.setText("4");
        jButton8.setRequestFocusEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);

        jButton9.setBackground(new java.awt.Color(0, 204, 204));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton9.setText("3");
        jButton9.setRequestFocusEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);

        jButton10.setBackground(new java.awt.Color(0, 204, 204));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton10.setText("2");
        jButton10.setRequestFocusEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);

        jButton11.setBackground(new java.awt.Color(0, 204, 204));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton11.setText("1");
        jButton11.setRequestFocusEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);

        jButton12.setBackground(new java.awt.Color(0, 204, 204));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton12.setText("0");
        jButton12.setRequestFocusEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);

        jButton14.setBackground(new java.awt.Color(204, 255, 204));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton14.setText("OK");
        jButton14.setRequestFocusEnabled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TABLE_2 = "";
        Value.TableSelected = TABLE_NO;
        restoreData();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadTable1();
        }
    }//GEN-LAST:event_txtTable1KeyPressed

    private void txtTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkLoadTable2();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_txtTable2KeyPressed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        if (txtTable2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุโต๊ะที่ต้องการย้ายรายการอาหารไป !");
            txtTable2.setText("");
            txtTable2.requestFocus();
        } else {
            if (loadDataActive) {
                int row = table1.getSelectedRow();
                if (row != -1) {
                    String R_Index = table1.getValueAt(row, 11).toString();
                    String R_Void = table1.getValueAt(row, 1).toString();
                    if (R_Void.equalsIgnoreCase("V")) {
                        JOptionPane.showMessageDialog(this, "รายการสินค้าเป็นรายการยกเลิก ไม่สามารถย้ายรายการได้ !");
                    } else {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "select tcode from tablefile where tcode='" + txtTable2.getText() + "'";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            if (rs.next()) {
                                //add data
                                TableMoveControl.moveProduct(txtTable1.getText(), txtTable2.getText(), R_Index);
                            } else {
                                //create table2 before add data
                                TableFileControl tfc = new TableFileControl();
                                tfc.createNewTableSplit(tfc.getData(txtTable1.getText()), txtTable2.getText());

                                //add data
                                TableMoveControl.moveProduct(txtTable1.getText(), txtTable2.getText(), R_Index);
                            }

                            rs.close();
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(this, e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }
                    }
                }

                //load table1
                loadTable1();

                //load table2
                loadTable2();
            }
        }

    }//GEN-LAST:event_table1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (loadDataActive) {
            if (!txtTable2.getText().equals("") && !txtTable1.getText().equals(txtTable2.getText())) {
                TABLE_2 = txtTable2.getText();

                //none active table1
                TableFileControl tfControl = new TableFileControl();
                tfControl.updateTableNotActive(TABLE_NO);
                try {
                    MySQLConnect c = new MySQLConnect();
                    c.open();
                    String sql = "update balance set r_linkindex='' where r_table='" + TABLE_2 + "'";
                    c.getConnection().createStatement().executeUpdate(sql);
                } catch (Exception e) {
                    MSG.ERR(e.toString());
                }
//                tfControl.updateTableActive(TABLE_2);
                Value.TableSelected = TABLE_2;
                dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTable2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtTable2);
        }
    }//GEN-LAST:event_txtTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inputNumber(jButton3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        inputNumber(jButton13);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        inputNumber(jButton4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        inputNumber(jButton5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        inputNumber(jButton6);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        inputNumber(jButton7);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        inputNumber(jButton8);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        inputNumber(jButton9);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        inputNumber(jButton10);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        inputNumber(jButton11);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        inputNumber(jButton12);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        inputNumber(jButton14);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyPressed

        if (txtTable2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุโต๊ะที่ต้องการย้ายรายการอาหารไป !");
            txtTable2.setText("");
            txtTable2.requestFocus();
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (loadDataActive) {
                    int row = table1.getSelectedRow();
                    if (row != -1) {
                        String R_Index = table1.getValueAt(row, 11).toString();
                        String R_Void = table1.getValueAt(row, 1).toString();
                        if (R_Void.equalsIgnoreCase("V")) {
                            JOptionPane.showMessageDialog(this, "รายการสินค้าเป็นรายการยกเลิก ไม่สามารถย้ายรายการได้ !");
                        } else {
                            MySQLConnect mysql = new MySQLConnect();
                            mysql.open();
                            try {
                                String sql = "select tcode from tablefile where tcode='" + txtTable2.getText() + "'";
                                Statement stmt = mysql.getConnection().createStatement();
                                ResultSet rs = stmt.executeQuery(sql);
                                if (rs.next()) {
                                    //add data
                                    TableMoveControl.moveProduct(txtTable1.getText(), txtTable2.getText(), R_Index);
                                } else {
                                    //create table2 before add data
                                    TableFileControl tfc = new TableFileControl();
                                    tfc.createNewTableSplit(tfc.getData(txtTable1.getText()), txtTable2.getText());

                                    //add data
                                    TableMoveControl.moveProduct(txtTable1.getText(), txtTable2.getText(), R_Index);
                                }

                                rs.close();
                                stmt.close();
                            } catch (SQLException e) {
                                MSG.ERR(this, e.getMessage());
                            } finally {
                                mysql.close();
                            }
                        }
                    }

                    //load table1
                    loadTable1();

                    //load table2
                    loadTable2();
                }
            }
        }
    }//GEN-LAST:event_table1KeyPressed

    private void table1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table1KeyReleased

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MoveItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoveItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoveItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoveItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MoveItemDialog dialog = new MoveItemDialog(new javax.swing.JFrame(), true, "1");
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
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField txtTable1;
    private javax.swing.JTextField txtTable2;
    // End of variables declaration//GEN-END:variables

    private void loadTable1() {
        BalanceControl bc = new BalanceControl();
        ArrayList<BalanceBean> listBalance = bc.getAllBalance(txtTable1.getText());
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        table1.setRowHeight(35);
        table1.setFont(new Font("Norasi", Font.PLAIN, 14));

        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }

        for (int i = 0; i < listBalance.size(); i++) {
            BalanceBean bean = (BalanceBean) listBalance.get(i);
            model.addRow(new Object[]{
                bean.getR_PrintOK(),
                bean.getR_Void(),
                bean.getR_ETD(),
                bean.getR_PluCode(),
                bean.getR_PName(),
                bean.getR_Quan(),
                bean.getR_Price(),
                bean.getR_Total(),
                bean.getR_PrType(),
                bean.getR_Discount(),
                bean.getR_DiscBath(),
                bean.getR_Index()
            });
        }
    }

    private void loadTable2() {
        BalanceControl bc = new BalanceControl();
        ArrayList<BalanceBean> listBalance = bc.getAllBalance(txtTable2.getText());
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        table2.setRowHeight(35);
        table2.setFont(new Font("Norasi", Font.PLAIN, 14));

        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }

        for (int i = 0; i < listBalance.size(); i++) {
            BalanceBean bean = (BalanceBean) listBalance.get(i);
            model.addRow(new Object[]{
                bean.getR_PrintOK(),
                bean.getR_Void(),
                bean.getR_ETD(),
                bean.getR_PluCode(),
                bean.getR_PName(),
                bean.getR_Quan(),
                bean.getR_Price(),
                bean.getR_Total(),
                bean.getR_PrType(),
                bean.getR_Discount(),
                bean.getR_DiscBath(),
                bean.getR_Index()
            });
        }
    }

    private void backupData() {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            /*
             drop table IF EXISTS temp_tablefile;
             create table IF NOT EXISTS temp_tablefile select * from tablefile where tcode in('1','2');

             drop table IF EXISTS temp_balance;
             create table IF NOT EXISTS temp_balance select * from balance where R_Table in('1','2');
             */

            Statement stmt = mysql.getConnection().createStatement();

            stmt.executeUpdate("drop table IF EXISTS temp_tablefile;");
            stmt.executeUpdate("create table IF NOT EXISTS temp_tablefile "
                    + "select * from tablefile "
                    + "where tcode in('" + txtTable1.getText() + "','" + txtTable2.getText() + "');");
            stmt.executeUpdate("drop table IF EXISTS temp_balance;");
            stmt.executeUpdate("create table IF NOT EXISTS temp_balance "
                    + "select * from balance "
                    + "where R_Table in('" + txtTable1.getText() + "','" + txtTable2.getText() + "');");
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private void restoreData() {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("delete from tablefile "
                    + "where tcode in('" + txtTable1.getText() + "','" + txtTable2.getText() + "');");
            stmt.executeUpdate("delete from balance "
                    + "where r_table in('" + txtTable1.getText() + "','" + txtTable2.getText() + "');");
            stmt.executeUpdate("insert into tablefile select * from temp_tablefile "
                    + "where tcode in('" + txtTable1.getText() + "','" + txtTable2.getText() + "');");
            stmt.executeUpdate("insert into balance select * from temp_balance "
                    + "where r_table in('" + txtTable1.getText() + "','" + txtTable2.getText() + "');");
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void inputNumber(JButton btn) {
        if (btn.getText().equals("OK")) {
            checkLoadTable2();
        } else if (txtTable2.hasFocus()) {
            String temp = txtTable2.getText();

            if (btn.getText().equals("<<")) {
                if (temp.length() > 0) {
                    temp = temp.substring(0, temp.length() - 1);
                }
            } else {
                temp = txtTable2.getText() + btn.getText();
            }

            txtTable2.setText(temp);
        }
    }

    private void checkLoadTable2() {
        backupData();
        TableFileControl tfCon = new TableFileControl();
        if (!tfCon.checkTableOpened(txtTable2.getText())) {
            txtTable2.setEditable(false);
            loadTable2();
            loadDataActive = true;

        } else {
            JOptionPane.showMessageDialog(this, "โต๊ะ " + txtTable2.getText() + " ถูกเปิดใช้งานอยู่ ไม่สามารถย้ายรายการได้");
            txtTable2.setEditable(true);
            txtTable2.setText("");
            txtTable2.requestFocus();
        }
    }
}
