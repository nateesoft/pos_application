package com.softpos.floorplan;

import java.awt.event.KeyEvent;
import com.softpos.posreport.ArPaymentRep;
import com.softpos.posreport.AutoSumXRep;
import com.softpos.posreport.AutoXRep;
import com.softpos.posreport.CashierRep;
import com.softpos.posreport.CouponRep;
import com.softpos.posreport.CreditRep;
import com.softpos.posreport.DeptRep;
import com.softpos.main.program.GetUserAction;
import com.softpos.posreport.GiftVoucherRep;
import com.softpos.posreport.HourlyByPlu;
import com.softpos.posreport.HourlyRep;
import com.softpos.posreport.InvRep;
import com.softpos.posreport.PLURep;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.posreport.PromotionRep;
import com.softpos.main.model.PublicVar;
import com.softpos.posreport.TerminalRep;
import com.softpos.posreport.TopSaleRep;
import com.softpos.main.model.UserRecord;
import com.softpos.main.model.Value;
import com.softpos.posreport.MTDHourlyOpenTB;
import com.softpos.posreport.VoidRep;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import util.MSG;
import com.softpos.posreport.DailyHourlyOpenTB;

public class DailyRep extends javax.swing.JDialog {

    PPrint prn = new PPrint();

    public DailyRep(java.awt.Frame parent, boolean modal) {
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
        setTitle("รายงานการขายประจำวัน (Daily Report)");
        setUndecorated(true);
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(102, 153, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, java.awt.Color.lightGray));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Daily Sales Report");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        FunctionList.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 3, true));
        FunctionList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FunctionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " รายงานโต๊ะค้างยังไม่ได้ชำระเงิน (Table On Action)", " รายงานยอดการเงินของเครื่อง (Terminal Report)", " รายงานการขายตามกลุ่มสินค้า (Department/Group Report)", " รายงานการขายตามรหัสสินค้า (PLU Report)", " รายงานการขายตามช่วงเวลา (Customer Per Hour Report)", " รายงานการพิมพ์ใบเสร็จรับเงิน (Reciept Report)", " รายงานการ Void  (Void Report)", " รายงานการรับชำระเงินด้วยบัตรเครดิต  (Credit Report)", " รายงานการชำระเงินด้วยบัตรกำนัล/บัตรของขวัญ (Gift Voucher Report)", " รายงานอันดับสินค้าขายดี  (Top Sale Report)", " รายงานส่วนลดคูปองพิเศษ (Special Cupon Report)", " รายงานส่วนลดโปรโมชั่น (Promotion Report)", " รายงานการรับชำระลูกหนี้ภายนอก(Ar Payment Report)", " รายงานอัตโนมัติเฉพาะเครื่อง(Automatic X)" };
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(FunctionList);

        setSize(new java.awt.Dimension(950, 742));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void FunctionListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FunctionListKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        bntOkClick();
    }
}//GEN-LAST:event_FunctionListKeyPressed

    private void FunctionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FunctionListMouseClicked
        if (evt.getClickCount() == 2) {
            if (FunctionList.getSelectedIndex() == 0) {
                if (Value.useprint) {
                    prn.PrintTableAction();
                }
            }
        }
        bntOkClick();
    }//GEN-LAST:event_FunctionListMouseClicked

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
//        ClearApp();
        dispose();

    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        bntOkClick();
    }//GEN-LAST:event_bntOKActionPerformed

    public void bntOkClick() {
        if (FunctionList.getSelectedIndex() == 1) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale10.equals("Y")) {
                TerminalRep frm = new TerminalRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale10.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            TerminalRep frm = new TerminalRep(null, true);
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
//        if (FunctionList.getSelectedIndex() == 2) {
//            PublicVar.TempUserRec = PublicVar.TUserRec;
//            if (PublicVar.TUserRec.Sale11.equals("Y")) {
//                CashierRep frm = new CashierRep(null, true);
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
//                        if (supUser.Sale11.equals("Y")) {
//                            PublicVar.TUserRec = supUser;
//                            CashierRep frm = new CashierRep(null, true);
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
        if (FunctionList.getSelectedIndex() == 2) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale12.equals("Y")) {
                DeptRep frm = new DeptRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale12.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            DeptRep frm = new DeptRep(null, true);
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
            if (PublicVar.TUserRec.Sale13.equals("Y")) {
                PLURep frm = new PLURep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale13.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            PLURep frm = new PLURep(null, true);
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
            if (PublicVar.TUserRec.Sale14.equals("Y")) {
                DailyHourlyOpenTB frm = new DailyHourlyOpenTB(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale14.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            DailyHourlyOpenTB frm = new DailyHourlyOpenTB(null, true);
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
        if (FunctionList.getSelectedIndex() == 5) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale15.equals("Y")) {
                InvRep frm = new InvRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale15.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            InvRep frm = new InvRep(null, true);
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
            if (PublicVar.TUserRec.Sale16.equals("Y")) {
                VoidRep frm = new VoidRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale16.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            VoidRep frm = new VoidRep(null, true);
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
            if (PublicVar.TUserRec.Sale17.equals("Y")) {
                CreditRep frm = new CreditRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale17.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            CreditRep frm = new CreditRep(null, true);
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
            if (PublicVar.TUserRec.Sale17.equals("Y")) {
                GiftVoucherRep frm = new GiftVoucherRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale17.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            GiftVoucherRep frm = new GiftVoucherRep(null, true);
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
                TopSaleRep frm = new TopSaleRep(null, true);
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
                            TopSaleRep frm = new TopSaleRep(null, true);
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

//        if (FunctionList.getSelectedIndex() == 12) {
//            PublicVar.TempUserRec = PublicVar.TUserRec;
//            if (PublicVar.TUserRec.Sale20.equals("Y")) {
//                HourlyByPlu frm = new HourlyByPlu(null, true);
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
//                        if (supUser.Sale20.equals("Y")) {
//                            PublicVar.TUserRec = supUser;
//                            HourlyByPlu frm = new HourlyByPlu(null, true);
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
//        if (FunctionList.getSelectedIndex() == 14) {
//            PublicVar.TempUserRec = PublicVar.TUserRec;
//            if (PublicVar.TUserRec.Sale20.equals("Y")) {
//                MTDHourlyOpenTB frm = new MTDHourlyOpenTB(null, true);
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
//                        if (supUser.Sale20.equals("Y")) {
//                            PublicVar.TUserRec = supUser;
//                            MTDHourlyOpenTB frm = new MTDHourlyOpenTB(null, true);
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
        if (FunctionList.getSelectedIndex() == 10) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale18.equals("Y")) {
                CouponRep frm = new CouponRep(null, true);
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
                            CouponRep frm = new CouponRep(null, true);
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
        if (FunctionList.getSelectedIndex() == 11) {
            PromotionRep frm = new PromotionRep(null, true);
            frm.setVisible(true);
            FunctionList.requestFocus();
        }
        if (FunctionList.getSelectedIndex() == 12) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale19.equals("Y")) {
                ArPaymentRep frm = new ArPaymentRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale19.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            ArPaymentRep frm = new ArPaymentRep(null, true);
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
        if (FunctionList.getSelectedIndex() == 13) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale21.equals("Y")) {
                AutoXRep frm = new AutoXRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale21.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            AutoXRep frm = new AutoXRep(null, true);
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

        if (FunctionList.getSelectedIndex() == 14) {
            if (!PUtility.ShowConfirmMsg("การพิมพ์รายงานอัตโนมัติรวมทุกเครื่อง เป็นการสิ้นสุดการขายสินค้าของวันนั้น หลังจากพิมพ์รายงานนี้แล้วจะไม่สามารถทำรายการขายได้อีก \n ยืนยันการพิมพ์รายงานสรุปยอดการขายหรือไม่ (Y/N)?... ")) {
                return;
            }
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale22.equals("Y")) {
                AutoSumXRep frm = new AutoSumXRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale22.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            AutoSumXRep frm = new AutoSumXRep(null, true);
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
    }

//    public void ClearApp() {
//        TerminalRep frm0 = new TerminalRep(null, true);
//        DeptRep frm1 = new DeptRep(null, true);
//        PLURep frm2 = new PLURep(null, true);
//        DailyHourlyOpenTB frm3 = new DailyHourlyOpenTB(null, true);
//        InvRep frm4 = new InvRep(null, true);
//        VoidRep frm5 = new VoidRep(null, true);
//        CreditRep frm6 = new CreditRep(null, true);
//        GiftVoucherRep frm7 = new GiftVoucherRep(null, true);
//        TopSaleRep frm9 = new TopSaleRep(null, true);
//        ArPaymentRep frm10 = new ArPaymentRep(null, true);
//        HourlyByPlu frm11 = new HourlyByPlu(null, true);
//        MTDHourlyOpenTB frm12 = new MTDHourlyOpenTB(null, true);
//        CashierRep frm13 = new CashierRep(null, true);
//        DiarySale frm14 = new DiarySale(null, true);
//        frm0.setVisible(false);
//        frm1.setVisible(false);
//        frm2.setVisible(false);
//        frm3.setVisible(false);
//        frm4.setVisible(false);
//        frm5.setVisible(false);
//        frm6.setVisible(false);
//        frm7.setVisible(false);
//        frm9.setVisible(false);
//        frm10.setVisible(false);
//        frm11.setVisible(false);
//        frm12.setVisible(false);
//        frm13.setVisible(false);
//        frm14.setVisible(false);
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DailyRep dialog = new DailyRep(new javax.swing.JFrame(), true);
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
