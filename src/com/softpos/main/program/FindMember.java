package com.softpos.main.program;

import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PublicVar;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.softpos.main.utils.MSG;

public class FindMember extends javax.swing.JDialog {

    DefaultTableModel model;
    SimpleDateFormat Datefmtshow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    /**
     * Creates new form FindMember
     */
    public FindMember(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        PublicVar.ReturnString = "";
        model = (DefaultTableModel) tblShowMember.getModel();
        tblShowMember.setShowGrid(true);
        tblShowMember.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblShowMember.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblShowMember.setRowSelectionAllowed(true);
        tblShowMember.setGridColor(Color.gray);

        JTableHeader header = tblShowMember.getTableHeader();
        header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

        int[] ColSize = {80, 120, 250, 100, 100, 120, 100, 100};
        for (int i = 0; i < 8; i++) {
            int vColIndex = 0;
            TableColumn col = tblShowMember.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }
        ActivateModule();

    }

    public void ActivateModule() {
        //Clear Variable
        TMemCode.setText("");
        TMemName.setText("");
        TMemTel.setText("");
        ClearGrid();

    }

    public void ClearGrid() {
        int RowCount = model.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model.removeRow(0);
        }
    }

    public String ChkValidDate(Date tdate) {
        String ReturnValue;
        try {
            ReturnValue = Datefmtshow.format(tdate);
        } catch (Exception e) {
            ReturnValue = "00/00/0000";
        }
        return ReturnValue;
    }

    public void bntShowMemberAllClick() {
        int LineCnt = 1;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String sql = "select *from memmaster order by m_name";
            ResultSet rs = stmt.executeQuery(sql);
            ClearGrid();

            while (rs.next()) {
                String TDate1 = "";
                String TDate2 = "";
                try {
                    TDate1 = Datefmtshow.format(rs.getDate("m_end"));
                    TDate2 = Datefmtshow.format(rs.getDate("m_brid"));
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                Object[] input = {
                    LineCnt, rs.getString("m_code"), rs.getString("m_name"),
                    rs.getString("m_tel"), rs.getString("m_mobile"),
                    rs.getString("m_office"), TDate1, TDate2
                };
                model.addRow(input);
                LineCnt++;
            }

            showCell(0, 0);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR_MSG(this, e.getMessage());
        } finally {
            mysql.close();
        }

    }

    public void ShowMemberByCode() {
        int LineCnt = 1;
        if ((TMemCode.getText().length() == 7) || (TMemCode.getText().length() == 13)) {
            if (TMemCode.getText().length() == 13) {
                String TempCode = TMemCode.getText();
                TempCode = TempCode.substring(5, 12);
                TMemCode.setText(TempCode);
            }
            String TempStr = TMemCode.getText();
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String LoadTableFile = "select *from memmaster where m_code = '" + TempStr + "' order by m_name";
                ResultSet rec = stmt.executeQuery(LoadTableFile);
                Date dt = new Date();
                ClearGrid();
                rec.first();
                if (rec.getRow() == 0) {
                    MSG.ERR(this, "ไม่พบข้อมูลสมาชิก ตามที่ต้องการ...");
                    TMemCode.requestFocus();
                } else {
                    do {
                        String TDate1 = "";
                        String TDate2 = "";
                        try {
                            TDate1 = Datefmtshow.format(rec.getDate("m_end"));
                            TDate2 = Datefmtshow.format(rec.getDate("m_brid"));
                        } catch (SQLException e) {
                        }
                        try {
                            Object[] input = {
                                LineCnt,
                                rec.getString("m_code"),
                                rec.getString("m_name"),
                                rec.getString("m_tel"),
                                rec.getString("m_mobile"),
                                rec.getString("m_office"),
                                TDate1, TDate2
                            };
                            model.addRow(input);
                        } catch (SQLException e) {
                        }
                        LineCnt++;
                    } while (rec.next());
                    showCell(0, 0);
                    tblShowMember.requestFocus(true);
                    //ShowTableLogin.setRowSelectionInterval(0, 0);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            }finally{
                mysql.close();
            }
        } else {
            PUtility.ShowMsg("รหัสสมาชิกต้องมีขนาด 7 หลักเท่านั้น...");
            TMemCode.setText("");
            TMemCode.requestFocus();
        }
    }

    public void ShowMemberByName() {
        int LineCnt = 1;
        String TempStr = "%" + TMemName.getText() + "%";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from memmaster where m_name like '" + TempStr + "' order by m_name";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            Date dt = new Date();
            ClearGrid();
            rec.first();
            if (rec.getRow() == 0) {
                MSG.ERR(this, "ไม่พบข้อมูลสมาชิก ตามที่ต้องการ...");
                TMemName.requestFocus();
            } else {
                do {
                    String TDate1 = "";
                    String TDate2 = "";
                    try {
                        TDate1 = Datefmtshow.format(rec.getDate("m_end"));
                        TDate2 = Datefmtshow.format(rec.getDate("m_brid"));
                    } catch (SQLException e) {
                    }
                    try {
                        Object[] input = {
                            LineCnt,
                            rec.getString("m_code"),
                            rec.getString("m_name"),
                            rec.getString("m_tel"),
                            rec.getString("m_mobile"),
                            rec.getString("m_office"),
                            TDate1, TDate2
                        };
                        model.addRow(input);
                    } catch (SQLException e) {
                    }
                    LineCnt++;
                } while (rec.next());
                showCell(0, 0);
                tblShowMember.requestFocus(true);
                //ShowTableLogin.setRowSelectionInterval(0, 0);
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
    }

    public void ShowMemberByTel() {
        int LineCnt = 1;
        String TempStr = "%" + TMemTel.getText() + "%";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from memmaster where (m_tel like '" + TempStr + "') or (m_mobile like '" + TempStr + "') or (m_office like '" + TempStr + "') order by m_name";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            Date dt = new Date();
            ClearGrid();
            rec.first();
            if (rec.getRow() == 0) {
                MSG.ERR(this, "ไม่พบข้อมูลสมาชิก ตามที่ต้องการ...");
                TMemTel.requestFocus();
            } else {
                do {
                    String TDate1 = "";
                    String TDate2 = "";
                    try {
                        TDate1 = Datefmtshow.format(rec.getDate("m_end"));
                        TDate2 = Datefmtshow.format(rec.getDate("m_brid"));
                    } catch (SQLException e) {
                    }
                    try {
                        Object[] input = {
                            LineCnt,
                            rec.getString("m_code"),
                            rec.getString("m_name"),
                            rec.getString("m_tel"),
                            rec.getString("m_mobile"),
                            rec.getString("m_office"),
                            TDate1, TDate2
                        };
                        model.addRow(input);
                    } catch (SQLException e) {
                    }
                    LineCnt++;
                } while (rec.next());
                showCell(0, 0);
                tblShowMember.requestFocus(true);
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }

    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = tblShowMember.getCellRect(row, column, true);
            tblShowMember.scrollRectToVisible(rect);
            tblShowMember.clearSelection();
            tblShowMember.setRowSelectionInterval(row, row);
        }
    }

    public void bntExitClick() {
        PublicVar.ReturnString = "";
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TMemCode = new javax.swing.JTextField();
        TMemName = new javax.swing.JTextField();
        TMemTel = new javax.swing.JTextField();
        bntShowMemAll = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowMember = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ค้นหาข้อมูลสมาชิก (Find Member)");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("รหัสสมาชิก");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ชื่อสมาชิก");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("เบอร์โทรศัพท์");

        TMemCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TMemCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TMemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMemCodeKeyPressed(evt);
            }
        });

        TMemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TMemName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TMemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMemNameKeyPressed(evt);
            }
        });

        TMemTel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TMemTel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TMemTel.setDragEnabled(true);
        TMemTel.setFocusCycleRoot(true);
        TMemTel.setFocusTraversalPolicyProvider(true);
        TMemTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMemTelKeyPressed(evt);
            }
        });

        bntShowMemAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntShowMemAll.setText("F2-แสดงทั้งหมด");
        bntShowMemAll.setFocusable(false);
        bntShowMemAll.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntShowMemAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntShowMemAllActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntOK.setText("F5-ตกลง (OK)");
        bntOK.setFocusable(false);
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntExit.setText("ESC-ออก(Exit)");
        bntExit.setFocusable(false);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 18, 130));
        jLabel4.setText("กด Enter เพื่อเลือกหัวข้อการป้อนข้อมูล");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TMemName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(TMemTel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntShowMemAll, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TMemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TMemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TMemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(TMemTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bntShowMemAll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tblShowMember.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        tblShowMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ลำดับ", "รหัสสมาชิก (Code)", "ชื่อสมาชิก (Name)", "โทรศัพท์บ้าน", "โทรศัพท์มือถือ", "โทรศัพท์ที่ทำงาน", "วันหมดอายุ", "วันเกิด"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblShowMember.setOpaque(false);
        tblShowMember.setRowHeight(25);
        tblShowMember.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblShowMemberKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblShowMember);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1008, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntShowMemAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntShowMemAllActionPerformed
    bntShowMemberAllClick();
}//GEN-LAST:event_bntShowMemAllActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void TMemCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMemCodeKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (TMemCode.getText().length() > 0) {
            ShowMemberByCode();
        } else {
            TMemName.requestFocus();
        }
    }
    if (evt.getKeyCode() == KeyEvent.VK_F2) {
        bntShowMemberAllClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }


}//GEN-LAST:event_TMemCodeKeyPressed

private void TMemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMemNameKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (TMemName.getText().length() > 0) {
            ShowMemberByName();
        } else {
            TMemTel.requestFocus();
        }
    }
    if (evt.getKeyCode() == KeyEvent.VK_F2) {
        bntShowMemberAllClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }

}//GEN-LAST:event_TMemNameKeyPressed

private void TMemTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMemTelKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (TMemTel.getText().length() > 0) {
            ShowMemberByTel();
        } else {
            TMemCode.requestFocus();
        }
    }
    if (evt.getKeyCode() == KeyEvent.VK_F2) {
        bntShowMemberAllClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }

}//GEN-LAST:event_TMemTelKeyPressed

private void tblShowMemberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowMemberKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        GetSelectedRow();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F2) {
        bntShowMemberAllClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        TMemCode.setText("");
        TMemName.setText("");
        TMemTel.setText("");
        ClearGrid();
        TMemTel.requestFocus();
    }
}//GEN-LAST:event_tblShowMemberKeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    TMemTel.requestFocus();
}//GEN-LAST:event_formWindowActivated
    public void bntOKClick() {
        GetSelectedRow();
    }

    public void GetSelectedRow() {
        int maxrow;
        int currow = 0;
        String TableSelected = "";
        maxrow = tblShowMember.getRowCount();
        for (int i = 0; i < maxrow; i++) {
            if (tblShowMember.isRowSelected(i)) {
                currow = i;
            }
        }
        if (maxrow > 0) {
            TableSelected = tblShowMember.getValueAt(currow, 1).toString();
            PublicVar.ReturnString = TableSelected;
            this.dispose();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FindMember dialog = new FindMember(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField TMemCode;
    private javax.swing.JTextField TMemName;
    private javax.swing.JTextField TMemTel;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntShowMemAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblShowMember;
    // End of variables declaration//GEN-END:variables
}
