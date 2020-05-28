package com.softpos.main.view;

import database.MySQLConnect;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.softpos.main.program.AddNewMember;
import com.softpos.main.program.FindMember;
import com.softpos.main.program.POSConfigSetup;
import com.softpos.main.program.PUtility;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.TranRecord;
import util.MSG;

public class MemberDialog extends javax.swing.JDialog {

    private final SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private TranRecord[] MyArray;
    private POSConfigSetup CONFIG;
    private int ArraySize;
    private String tableNo;

    public MemberDialog(java.awt.Frame parent, boolean modal, String tableNo) {
        super(parent, modal);
        initComponents();

        CONFIG = POSConfigSetup.Bean();
        this.tableNo = tableNo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MemberPane = new javax.swing.JPanel();
        bntMember = new javax.swing.JButton();
        _MemCode = new javax.swing.JTextField();
        bntFindMember = new javax.swing.JButton();
        _MemName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        _MemLast = new javax.swing.JTextField();
        _MemSumPoint = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        _MemVaildDate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        _MemBrithDay = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        MemberPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bntMember.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntMember.setText("(F10) ค้นหาสมาชิก");
        bntMember.setEnabled(false);
        bntMember.setFocusable(false);
        bntMember.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bntMember.setRequestFocusEnabled(false);
        bntMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntMemberActionPerformed(evt);
            }
        });

        _MemCode.setEditable(false);
        _MemCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        _MemCode.setDisabledTextColor(java.awt.Color.black);
        _MemCode.setFocusable(false);
        _MemCode.setRequestFocusEnabled(false);

        bntFindMember.setText("...");
        bntFindMember.setEnabled(false);
        bntFindMember.setFocusable(false);
        bntFindMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFindMemberActionPerformed(evt);
            }
        });

        _MemName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        _MemName.setDisabledTextColor(java.awt.Color.black);
        _MemName.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("แต้มสะสมถึง");

        _MemLast.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        _MemLast.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _MemLast.setDisabledTextColor(java.awt.Color.black);
        _MemLast.setEnabled(false);

        _MemSumPoint.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        _MemSumPoint.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        _MemSumPoint.setDisabledTextColor(java.awt.Color.black);
        _MemSumPoint.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Vaild Date");

        _MemVaildDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        _MemVaildDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _MemVaildDate.setDisabledTextColor(java.awt.Color.black);
        _MemVaildDate.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Birthday");

        _MemBrithDay.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        _MemBrithDay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _MemBrithDay.setDisabledTextColor(java.awt.Color.black);
        _MemBrithDay.setEnabled(false);

        javax.swing.GroupLayout MemberPaneLayout = new javax.swing.GroupLayout(MemberPane);
        MemberPane.setLayout(MemberPaneLayout);
        MemberPaneLayout.setHorizontalGroup(
            MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemberPaneLayout.createSequentialGroup()
                .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MemberPaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(bntMember, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(_MemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(bntFindMember)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_MemName))
                    .addGroup(MemberPaneLayout.createSequentialGroup()
                        .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MemberPaneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9)
                                .addGap(4, 4, 4)
                                .addComponent(_MemLast, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(_MemSumPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel10)
                                .addGap(4, 4, 4)
                                .addComponent(_MemVaildDate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MemberPaneLayout.createSequentialGroup()
                                .addGap(337, 337, 337)
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4)
                                .addComponent(_MemBrithDay, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MemberPaneLayout.setVerticalGroup(
            MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemberPaneLayout.createSequentialGroup()
                .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MemberPaneLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntMember, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(_MemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntFindMember, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(MemberPaneLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(_MemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_MemLast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_MemSumPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_MemVaildDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MemberPaneLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))))
                .addGap(6, 6, 6)
                .addGroup(MemberPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MemberPaneLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11))
                    .addComponent(_MemBrithDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("บันทึก / ปิดหน้าต่าง");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MemberPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MemberPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntMemberActionPerformed
        bntFindMemberClick();
    }//GEN-LAST:event_bntMemberActionPerformed

    private void bntFindMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFindMemberActionPerformed
        bntFindMemberClick();
    }//GEN-LAST:event_bntFindMemberActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void bntFindMemberClick() {
        if (!PublicVar.SubTotalOK) {
            bntFindMember.setEnabled(true);
            FindMember frm = new FindMember(null, true);
            frm.setVisible(true);
            if (!PublicVar.ReturnString.equals("")) {
                _MemCode.setText(PublicVar.ReturnString);
                PublicVar.TableRec_MemBarcode = "N";
                CancelMemDisc();
                _MemCodeExit();
            }
        }
    }

    public void _MemCodeExit() {

        if (PublicVar.TableRec_MemCode.equals("")) {
            MemberPane.setBackground(Color.decode("#b8e3eb"));
        } else {
            if (PublicVar.TableRec_MemBarcode.equals("Y")) {
                MemberPane.setBackground(Color.decode("#b8e3eb"));
            } else {
                MemberPane.setBackground(Color.decode("#f74009"));
            }
        }
        String TempMem = _MemCode.getText();
        if (!TempMem.equals("")) {
            if ((TempMem.length() != 7) && (TempMem.length() != 13)) {
                MSG.WAR(this, "รหัสสมาชิกที่บันทึกรายการได้ต้องมากกว่าหรือเท่ากับ 7 หลักเท่านั้น...");
                CancelMemDisc();
                _MemCode.setText("");
                //_MemCode.requestFocus();
            } else {
                if (TempMem.length() == 13) {
                    TempMem = TempMem.substring(5, 12);
                }
                _MemCode.setText(TempMem);

                /**
                 * * OPEN CONNECTION **
                 */
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();

                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String SelectCupon = "select * from memmaster where m_code='" + TempMem + "'";
                    ResultSet rs = stmt.executeQuery(SelectCupon);
                    rs.first();
                    if (rs.getRow() != 0) {
                        if (rs.getString("M_Active").equals("Y")) {
                            PublicVar.TableRec_MemCode = rs.getString("m_code");
                            PublicVar.TableRec_MemName = rs.getString("m_name");
                            Calendar ca = Calendar.getInstance();
                            ca.add(Calendar.DAY_OF_MONTH, -30);
                            try {
                                if (PUtility.ChkValidDate(rs.getDate("m_begin"))) {
                                    PublicVar.TableRec_MemBegin = rs.getDate("m_begin");
                                } else {
                                    PublicVar.TableRec_MemBegin = ca.getTime();
                                }
                            } catch (SQLException ex) {
                                PublicVar.TableRec_MemBegin = ca.getTime();
                            }
                            try {
                                if (PUtility.ChkValidDate(rs.getDate("m_end"))) {
                                    PublicVar.TableRec_MemEnd = rs.getDate("m_end");
                                } else {
                                    PublicVar.TableRec_MemEnd = ca.getTime();
                                }
                            } catch (SQLException ex) {
                                PublicVar.TableRec_MemEnd = ca.getTime();
                            }
                            try {
                                if (PUtility.ChkValidDate(rs.getDate("m_brid"))) {
                                    PublicVar.TableRec_MemBrid = rs.getDate("m_brid");
                                } else {
                                    PublicVar.TableRec_MemBrid = ca.getTime();
                                }
                            } catch (SQLException ex) {
                                PublicVar.TableRec_MemBrid = ca.getTime();
                            }
                            try {
                                if (PUtility.ChkValidDate(rs.getDate("m_lsev"))) {
                                    PublicVar.TableRec_MemLastDate = DatefmtShow.format(rs.getDate("m_lsev"));
                                } else {
                                    PublicVar.TableRec_MemLastDate = "00/00/0000";
                                }
                            } catch (SQLException ex) {
                                PublicVar.TableRec_MemLastDate = "00/00/0000";
                            }

                            ProcessMem();
                        } else {
                            MSG.WAR(this, "บัตรสมาชิกท่านนี้ได้ถูกยกเลิกแล้ว... !!!");
                            ClearDataMem();
                            CancelMemDisc();
                        }

                    } else {
                        if (PUtility.ShowConfirmMsg("ไม่พบข้อมูลสมาชิกหมายเลข " + TempMem + " ในฐานข้อมูล..." + " ต้องการเพิ่มใหม่หรือไม่ ?")) {
                            AddNewMember frm = new AddNewMember(null, true);
                            frm.setVisible(true);
                            _MemCode.setText("");
                        } else {
                            ClearDataMem();
                            CancelMemDisc();
                        }
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    MSG.ERR(this, ex.getMessage());
                    
                } finally {
                    mysql.close();
                }
            }
        } else {
            ClearDataMem();
            ClearMember();
            CancelMemDisc();
        }
    }

    public void ClearMember() {
        _MemCode.setText("");
        _MemName.setText("");
        _MemLast.setText("");
        _MemSumPoint.setText("");
        _MemVaildDate.setText("");
        _MemBrithDay.setText("");
        _MemCode.setFocusable(false);
    }

    public String ChkDiscOK(String _Disc, String _DiscChk, String _TMax) {
        String ReturnValue;
        String _TDisc1, _TDisc2, _TDisc3;
        String _TMax1, _TMax2, _TMax3;
        _Disc = PUtility.DataFull(_Disc, 8);
        _TDisc1 = PUtility.Addzero(_Disc.substring(0, 2), 2);
        _TDisc2 = PUtility.Addzero(_Disc.substring(3, 5), 2);
        _TDisc3 = PUtility.Addzero(_Disc.substring(6, 8), 2);
        _TMax1 = PUtility.Addzero(_TMax.substring(0, 2), 2);
        _TMax2 = PUtility.Addzero(_TMax.substring(3, 5), 2);
        _TMax3 = PUtility.Addzero(_TMax.substring(6, 8), 2);
        if ((!PUtility.ChkNumValue(_TDisc1)) || (!_DiscChk.substring(0, 1).equals("Y"))) {
            _TDisc1 = "0";
        }
        if ((!PUtility.ChkNumValue(_TDisc2)) || (!_DiscChk.substring(2, 3).equals("Y"))) {
            _TDisc2 = "0";
        }
        if ((!PUtility.ChkNumValue(_TDisc3)) || (!_DiscChk.substring(4, 5).equals("Y"))) {
            _TDisc3 = "0";
        }
        if (PUtility.ChkNumValue(_TMax1)) {
            if (_TDisc1.compareTo(_TMax1) > 0) {
                _TDisc1 = _TMax1;
            }
        }
        if (PUtility.ChkNumValue(_TMax2)) {
            if (_TDisc2.compareTo(_TMax2) > 0) {
                _TDisc2 = _TMax2;
            }
        }
        if (PUtility.ChkNumValue(_TMax3)) {
            if (_TDisc3.compareTo(_TMax3) > 0) {
                _TDisc3 = _TMax3;
            }
        }
        ReturnValue = PUtility.Addzero(_TDisc1, 2) + "/" + PUtility.Addzero(_TDisc2, 2) + "/" + PUtility.Addzero(_TDisc3, 2);
        return ReturnValue;
    }

    public void ProcessMem() {
        double TPNormal, TPConsign, TPSpecial;
        double TNormal, TConsign, TSpecial;
        double XSumDisc = 0.0;
        String tempv = CONFIG.getP_MemDisc();
        if (PublicVar.TableRec_MemBarcode.equals("Y")) {
            PublicVar.TableRec_MemDisc = ChkDiscOK(tempv, CONFIG.getP_MemDiscChk(), CONFIG.getP_MemDiscMax());
        } else {
            PublicVar.TableRec_MemDisc = "00/00/00";
        }
        if (PublicVar.TableRec_NetTotal - PublicVar.TableRec_ServiceAmt < 100) {
            PublicVar.TableRec_MemDisc = "00/00/00";
        }
        String TempDisc = PUtility.DataFull(PublicVar.TableRec_MemDisc, 8);
        TPNormal = Double.parseDouble(PUtility.Addzero(TempDisc.substring(0, 2), 2));
        TPConsign = Double.parseDouble(PUtility.Addzero(TempDisc.substring(3, 5), 2));
        TPSpecial = Double.parseDouble(PUtility.Addzero(TempDisc.substring(6, 8), 2));
        for (int i = 0; i < ArraySize; i++) {
            TNormal = 0.0;
            TConsign = 0.0;
            TSpecial = 0.0;
            if ((MyArray[i].R_PrSubType.equals("-M")) && (!MyArray[i].R_Void.equals("V"))) {
                MyArray[i].R_QuanCanDisc = MyArray[i].R_QuanCanDisc + MyArray[i].R_PrSubQuan;
                MyArray[i].R_PrSubType = "";
                MyArray[i].R_PrSubCode = "";
                MyArray[i].R_PrSubQuan = 0.0;
                MyArray[i].R_PrSubDisc = 0.0;
                MyArray[i].R_PrSubAmt = 0.0;

                /**
                 * * OPEN CONNECTION **
                 */
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();

                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String UpdateBalance = "update balance set r_prsubtype=?,r_prsubcode=?,r_prsubdisc=?,r_prsubamt=?,r_prsubquan=?,r_quancandisc=? where (r_index=?) and (r_table=?)";
                    PreparedStatement prm = (PreparedStatement) mysql.getConnection().prepareStatement(UpdateBalance);
                    prm.setString(1, MyArray[i].R_PrSubType);
                    prm.setString(2, MyArray[i].R_PrSubCode);
                    prm.setDouble(3, MyArray[i].R_PrSubDisc);
                    prm.setDouble(4, MyArray[i].R_PrSubAmt);
                    prm.setDouble(5, MyArray[i].R_PrSubQuan);
                    prm.setDouble(6, MyArray[i].R_QuanCanDisc);
                    prm.setString(7, MyArray[i].R_Index);
                    prm.setString(8, tableNo);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                } finally {
                    mysql.close();
                }
            }
            if ((!MyArray[i].R_Void.equals("V")) && (MyArray[i].R_QuanCanDisc > 0)) {
                if ((MyArray[i].R_Normal.equals("N")) && (TPNormal > 0) && (MyArray[i].R_Discount.equals("Y"))) {
                    TNormal = TNormal + (MyArray[i].R_QuanCanDisc * MyArray[i].R_Price);
                    MyArray[i].R_PrSubType = "-M";
                    MyArray[i].R_PrSubCode = "MEM";
                    MyArray[i].R_PrSubQuan = MyArray[i].R_QuanCanDisc;
                    MyArray[i].R_QuanCanDisc = 0.0;
                    MyArray[i].R_PrSubDisc = TPNormal;
                    MyArray[i].R_PrSubAmt = TNormal * TPNormal / 100;
                }
                if ((MyArray[i].R_Normal.equals("C")) && (TPConsign > 0) && (MyArray[i].R_Discount.equals("Y"))) {
                    TConsign = TConsign + (MyArray[i].R_QuanCanDisc * MyArray[i].R_Price);
                    MyArray[i].R_PrSubType = "-M";
                    MyArray[i].R_PrSubCode = "MEM";
                    MyArray[i].R_PrSubQuan = MyArray[i].R_QuanCanDisc;
                    MyArray[i].R_QuanCanDisc = 0.0;
                    MyArray[i].R_PrSubDisc = TPConsign;
                    MyArray[i].R_PrSubAmt = TConsign * TPConsign / 100;
                }
                if ((MyArray[i].R_Normal.equals("S")) && (TPSpecial > 0) && (MyArray[i].R_Discount.equals("Y"))) {
                    TSpecial = TSpecial + (MyArray[i].R_QuanCanDisc * MyArray[i].R_Price);
                    MyArray[i].R_PrSubType = "-M";
                    MyArray[i].R_PrSubCode = "MEM";
                    MyArray[i].R_PrSubQuan = MyArray[i].R_QuanCanDisc;
                    MyArray[i].R_QuanCanDisc = 0.0;
                    MyArray[i].R_PrSubDisc = TPSpecial;
                    MyArray[i].R_PrSubAmt = TSpecial * TPSpecial / 100;
                }
                TNormal = TNormal * TPNormal / 100;
                TConsign = TConsign * TPConsign / 100;
                TSpecial = TSpecial * TPSpecial / 100;
                XSumDisc = XSumDisc + (TNormal + TConsign + TSpecial);
                if (TNormal + TConsign + TSpecial > 0) {
                    /**
                     * * OPEN CONNECTION **
                     */
                    MySQLConnect mysql = new MySQLConnect();
                    mysql.open();

                    try {
                        Statement stmt = mysql.getConnection().createStatement();
                        String UpdateBalance = "update balance set r_prsubtype=?,r_prsubcode=?,r_prsubdisc=?,r_prsubamt=?,r_prsubquan=?,r_quancandisc=? where (r_index=?) and (r_table=?)";
                        PreparedStatement prm = (PreparedStatement) mysql.getConnection().prepareStatement(UpdateBalance);
                        prm.setString(1, MyArray[i].R_PrSubType);
                        prm.setString(2, MyArray[i].R_PrSubCode);
                        prm.setDouble(3, MyArray[i].R_PrSubDisc);
                        prm.setDouble(4, MyArray[i].R_PrSubAmt);
                        prm.setDouble(5, MyArray[i].R_PrSubQuan);
                        prm.setDouble(6, MyArray[i].R_QuanCanDisc);
                        prm.setString(7, MyArray[i].R_Index);
                        prm.setString(8, tableNo);
                        prm.executeUpdate();
                        stmt.close();
                    } catch (SQLException e) {
                        MSG.ERR(this, e.getMessage());
                        
                    } finally {
                        mysql.close();
                    }
                }
            }
        }
        PublicVar.TableRec_MemDiscAmt = PUtility.RoundDecimal(XSumDisc, CONFIG.getP_DiscRound());
    }

    public void ClearDataMem() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, -30);
        PublicVar.TableRec_MemCode = "";
        PublicVar.TableRec_MemName = "";
        PublicVar.TableRec_MemBegin = ca.getTime();
        PublicVar.TableRec_MemEnd = ca.getTime();
        PublicVar.TableRec_MemBrid = ca.getTime();
        PublicVar.TableRec_MemLastDate = "";
        PublicVar.TableRec_MemCurAmt = 0.0;
        PublicVar.TableRec_Score = 0.0;
        PublicVar.TableRec_SumScoreal = 0.0;
        _MemCode.setText("");
        _MemName.setText("");
        _MemVaildDate.setText("");
        _MemLast.setText("");
        _MemBrithDay.setText("");
        _MemSumPoint.setText("");
        if (PublicVar.TableRec_MemCode.equals("")) {
            MemberPane.setBackground(Color.decode("#b8e3eb"));
        } else {
            if (PublicVar.TableRec_MemBarcode.equals("Y")) {
                MemberPane.setBackground(Color.decode("#b8e3eb"));
            } else {
                MemberPane.setBackground(Color.decode("#f74009"));
            }
        }
        ClearMember();
        bntFindMember.setEnabled(false);
    }

    public void CancelMemDisc() {
        PublicVar.TableRec_MemDisc = "";
        PublicVar.TableRec_MemDiscAmt = 0.0;
        for (int i = 0; i < ArraySize; i++) {
            if ((MyArray[i].R_PrSubType.equals("-M")) && (!MyArray[i].R_Void.equals("V"))) {
                MyArray[i].R_QuanCanDisc = MyArray[i].R_QuanCanDisc + MyArray[i].R_PrSubQuan;
                MyArray[i].R_PrSubType = "";
                MyArray[i].R_PrSubCode = "";
                MyArray[i].R_PrSubQuan = 0.0;
                MyArray[i].R_PrSubDisc = 0.0;
                MyArray[i].R_PrSubAmt = 0.0;
                /**
                 * * OPEN CONNECTION **
                 */
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();

                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String UpdateBalance = "update balance set r_prsubtype=?,r_prsubcode=?,r_prsubdisc=?,r_prsubamt=?,"
                            + "r_prsubquan=?,r_quancandisc=? "
                            + "where (r_index=?) and (r_table=?)";
                    PreparedStatement prm = (PreparedStatement) mysql.getConnection().prepareStatement(UpdateBalance);
                    prm.setString(1, MyArray[i].R_PrSubType);
                    prm.setString(2, MyArray[i].R_PrSubCode);
                    prm.setDouble(3, MyArray[i].R_PrSubDisc);
                    prm.setDouble(4, MyArray[i].R_PrSubAmt);
                    prm.setDouble(5, MyArray[i].R_PrSubQuan);
                    prm.setDouble(6, MyArray[i].R_QuanCanDisc);
                    prm.setString(7, MyArray[i].R_Index);
                    prm.setString(8, tableNo);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(this, e.getMessage());
                    
                } finally{
                    mysql.close();
                }
            }
        }
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
            java.util.logging.Logger.getLogger(MemberDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MemberDialog dialog = new MemberDialog(new javax.swing.JFrame(), true, "1");
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
    private javax.swing.JPanel MemberPane;
    private javax.swing.JTextField _MemBrithDay;
    private javax.swing.JTextField _MemCode;
    private javax.swing.JTextField _MemLast;
    private javax.swing.JTextField _MemName;
    private javax.swing.JTextField _MemSumPoint;
    private javax.swing.JTextField _MemVaildDate;
    private javax.swing.JButton bntFindMember;
    private javax.swing.JButton bntMember;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
