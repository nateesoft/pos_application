package com.softpos.floorplan;

import java.awt.event.KeyEvent;
import com.softpos.main.program.GetUserAction;
import com.softpos.posreport.MTDArPayment;
import com.softpos.posreport.MTDCashier;
import com.softpos.posreport.MTDCredit;
import com.softpos.posreport.MTDDept;
import com.softpos.posreport.MTDHourly;
import com.softpos.posreport.MTDPLU;
import com.softpos.posreport.MTDSubDiscount;
import com.softpos.posreport.MTDTerminal;
import com.softpos.posreport.MTDTopSale;
import com.softpos.posreport.MTDVoid;
import java.awt.GraphicsEnvironment;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.UserRecord;
import com.softpos.posreport.MTDCoupon;
import com.softpos.posreport.MTDGiftVoucher;
import com.softpos.posreport.MTDHourlyOpenTB;
import com.softpos.posreport.MTDInvRep;
import com.softpos.posreport.MTDInvoice;
import util.MSG;

public class MTDRep extends javax.swing.JDialog {

    public MTDRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setUndecorated(true);
        setSize(1024, 768);
        setLocationRelativeTo(null);
//        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FunctionList = new javax.swing.JList();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("รายงานประจำเดือน");
        setUndecorated(true);
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, java.awt.Color.lightGray));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MTD Sales Report");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FunctionList.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 3, true));
        FunctionList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FunctionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "     รายงานยอดการเงินของเครื่อง (Terminal Report)", "     รายงานการขายตามกลุ่มสินค้า (Department/Group Report)", "     รายงานการขายตามรหัสสินค้า (PLU Report)", "     รายงานการขายตามช่วงเวลา (Customer per Hours Report)", "     รายงานการพิมพ์ใบเสร็จรับเงิน (Reciept Report)", "     รายงานการ Void  (Void Report)", "     รายงานการรับชำระเงินด้วยบัตรเครดิต  (Credit Report)", "     รายงานการชำระเงินด้วยบัตรกำนัล/บัตรของขวัญ (Gift Voucher Report)", "     รายงานอันดับสินค้าขายดี  (Top Sale Report)", "     รายงานส่วนลดคูปองพิเศษ (Special Cupon Report)" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        FunctionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        FunctionList.setFixedCellHeight(32);
        FunctionList.setOpaque(false);
        FunctionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FunctionListMouseClicked(evt);
            }
        });
        FunctionList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FunctionListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(FunctionList);

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("Enter เลือกรายการ");
        bntOK.setFocusable(false);
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.setRequestFocusEnabled(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC ออกจากหน้าจอ");
        bntExit.setFocusable(false);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.setRequestFocusEnabled(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("(เลือกรายการโดยใช้ ลูกศรขื้น,ลูกศรลง,คลิกเม้าส์,สัมผัสหน้าจอ)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 554, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(845, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FunctionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FunctionListMouseClicked
        bntOkClick();
    }//GEN-LAST:event_FunctionListMouseClicked

    private void FunctionListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FunctionListKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bntOkClick();
        }
    }//GEN-LAST:event_FunctionListKeyPressed

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        ClearApp();
        dispose();
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        bntOkClick();
    }//GEN-LAST:event_bntOKActionPerformed
    public void bntOkClick() {
        if (FunctionList.getSelectedIndex() == 0) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale23.equals("Y")) {
                MTDTerminal frm = new MTDTerminal(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale23.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDTerminal frm = new MTDTerminal(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
//        if (FunctionList.getSelectedIndex() == 1) {
//            PublicVar.TempUserRec = PublicVar.TUserRec;
//            if (PublicVar.TUserRec.Sale24.equals("Y")) {
//                MTDCashier frm = new MTDCashier(null, true);
//                frm.setVisible(true);
//                FunctionList.requestFocus();
//            } else {
//                GetUserAction getuser = new GetUserAction(null, true);
//                getuser.setVisible(true);
//
//                if (!PublicVar.ReturnString.equals("")) {
//                    String loginname = PublicVar.ReturnString;
//                    UserRecord supUser = new UserRecord();
//                    if (supUser.GetUserAction(loginname)) {
//                        if (supUser.Sale24.equals("Y")) {
//                            PublicVar.TUserRec = supUser;
//                            MTDCashier frm = new MTDCashier(null, true);
//                            frm.setVisible(true);
//                            FunctionList.requestFocus();
//                        } else {
//                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
//                        }
//                    } else {
//                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
//                    }
//                }
//            }
//            PublicVar.TUserRec = PublicVar.TempUserRec;
//        }
        if (FunctionList.getSelectedIndex() == 1) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale25.equals("Y")) {
                MTDDept frm = new MTDDept(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale25.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDDept frm = new MTDDept(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 2) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale26.equals("Y")) {
                MTDPLU frm = new MTDPLU(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale26.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDPLU frm = new MTDPLU(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 3) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale27.equals("Y")) {
                MTDHourlyOpenTB frm = new MTDHourlyOpenTB(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale27.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDHourlyOpenTB frm = new MTDHourlyOpenTB(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 4) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale27.equals("Y")) {
                MTDInvRep frm = new MTDInvRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale27.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDInvRep frm = new MTDInvRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
//        if (FunctionList.getSelectedIndex() == 4) {
//            PublicVar.TempUserRec = PublicVar.TUserRec;
//            if (PublicVar.TUserRec.Sale28.equals("Y")) {
//                MTDSubDiscount frm = new MTDSubDiscount(null, true); //สลับกับ Invoice <-> SubDiscount
//                frm.setVisible(true);
//                FunctionList.requestFocus();
//            } else {
//                GetUserAction getuser = new GetUserAction(null, true);
//                getuser.setVisible(true);
//
//                if (!PublicVar.ReturnString.equals("")) {
//                    String loginname = PublicVar.ReturnString;
//                    UserRecord supUser = new UserRecord();
//                    if (supUser.GetUserAction(loginname)) {
//                        if (supUser.Sale28.equals("Y")) {
//                            PublicVar.TUserRec = supUser;
//                            MTDSubDiscount frm = new MTDSubDiscount(null, true); //สลับกับ Invoice <-> SubDiscount
//                            frm.setVisible(true);
//                            FunctionList.requestFocus();
//                        } else {
//                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
//                        }
//                    } else {
//                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
//                    }
//                }
//            }
//            PublicVar.TUserRec = PublicVar.TempUserRec;
//        }
        if (FunctionList.getSelectedIndex() == 5) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale29.equals("Y")) {
                MTDVoid frm = new MTDVoid(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale29.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDVoid frm = new MTDVoid(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 6) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale30.equals("Y")) {
                MTDCredit frm = new MTDCredit(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale30.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDCredit frm = new MTDCredit(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 7) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale30.equals("Y")) {
                MTDGiftVoucher frm = new MTDGiftVoucher(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale30.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDGiftVoucher frm = new MTDGiftVoucher(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 8) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale31.equals("Y")) {
                MTDTopSale frm = new MTDTopSale(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale31.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDTopSale frm = new MTDTopSale(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 9) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale18.equals("Y")) {
                MTDCoupon frm = new MTDCoupon(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale18.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            MTDCoupon frm = new MTDCoupon(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
//        if (FunctionList.getSelectedIndex() == 9) {
//            PublicVar.TempUserRec = PublicVar.TUserRec;
//            if (PublicVar.TUserRec.Sale32.equals("Y")) {
//                MTDArPayment frm = new MTDArPayment(null, true);
//                frm.setVisible(true);
//                FunctionList.requestFocus();
//            } else {
//                GetUserAction getuser = new GetUserAction(null, true);
//                getuser.setVisible(true);
//
//                if (!PublicVar.ReturnString.equals("")) {
//                    String loginname = PublicVar.ReturnString;
//                    UserRecord supUser = new UserRecord();
//                    if (supUser.GetUserAction(loginname)) {
//                        if (supUser.Sale32.equals("Y")) {
//                            PublicVar.TUserRec = supUser;
//                            MTDArPayment frm = new MTDArPayment(null, true);
//                            frm.setVisible(true);
//                            FunctionList.requestFocus();
//                        } else {
//                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
//                        }
//                    } else {
//                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
//                    }
//                }
//            }
//            PublicVar.TUserRec = PublicVar.TempUserRec;
//        }
    }

    public void ClearApp() {
        MTDTerminal frm0 = new MTDTerminal(null, true);
        MTDCashier frm1 = new MTDCashier(null, true);
        MTDDept frm2 = new MTDDept(null, true);
        MTDPLU frm3 = new MTDPLU(null, true);
        MTDHourlyOpenTB frm4 = new MTDHourlyOpenTB(null, true);
        MTDSubDiscount frm5 = new MTDSubDiscount(null, true);
        MTDVoid frm6 = new MTDVoid(null, true);
        MTDCredit frm7 = new MTDCredit(null, true);
        MTDTopSale frm8 = new MTDTopSale(null, true);
        MTDArPayment frm9 = new MTDArPayment(null, true);
        DiarySale frm10 = new DiarySale(null, true);
        frm0.setVisible(false);
        frm1.setVisible(false);
        frm2.setVisible(false);
        frm3.setVisible(false);
        frm4.setVisible(false);
        frm5.setVisible(false);
        frm6.setVisible(false);
        frm7.setVisible(false);
        frm8.setVisible(false);
        frm9.setVisible(false);
        frm10.setVisible(false);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MTDRep dialog = new MTDRep(new javax.swing.JFrame(), true);
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
    private javax.swing.JList FunctionList;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
