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
import util.MSG;

public class FindCustomer extends javax.swing.JDialog {

    DefaultTableModel model;
    static SimpleDateFormat Datefmtshow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    /** Creates new form FindMember */
    public FindCustomer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
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

        int[] ColSize = {80,150, 600, 150};
        for (int i = 0; i < 4; i++) {
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
        String ReturnValue = "00/00/0000" ;
        try {
            ReturnValue = Datefmtshow.format(tdate) ; 
        } catch (Exception e) {
            ReturnValue = "00/00/0000" ;
        }
        return ReturnValue ;
    }
    public void bntShowMemberAllClick() {
        int LineCnt = 1 ;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try { 
            Statement stmt =  mysql.getConnection().createStatement();
            String LoadTableFile = "select *from customer order by sp_desc";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            Date dt = new Date();
            ClearGrid() ;
            rec.first();
            if (rec.getRow() == 0) {
                 PUtility.showError("ไม่พบข้อมูลลูกค้า ตามที่ต้องการ...");
            } else {
                do {
                   try {
                    Object[] input = {
                        LineCnt,
                        rec.getString("sp_code"),
                        rec.getString("sp_desc"),
                        rec.getString("tel")
                    };
                    model.addRow(input);
                   } catch (SQLException e) {
                   }
                   LineCnt++ ;
                } while (rec.next());
                int RowCount = model.getRowCount();
                showCell(0,0) ;
                //ShowTableLogin.setRowSelectionInterval(0, 0);
            }
            rec.close();
            stmt.close();
            tblShowMember.requestFocus();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
        
    }
    public void ShowMemberByCode() {
        int LineCnt = 1 ;
         String TempStr = "%"+TMemCode.getText()+"%" ;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try { 
            Statement stmt =  mysql.getConnection().createStatement();
            String LoadTableFile = "select *from customer where sp_code like '"+TempStr+"' order by sp_desc";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            Date dt = new Date();
            ClearGrid() ;
            rec.first();
            if (rec.getRow() == 0) {
               MSG.ERR(this,"ไม่พบข้อมูลลูกค้า ตามที่ต้องการ...");
               TMemCode.requestFocus();
            } else {
                do {
                   try {
                    Object[] input = {
                        LineCnt,
                        rec.getString("sp_code"),
                        rec.getString("sp_desc"),
                        rec.getString("tel")
                    };
                    model.addRow(input);
                   } catch (SQLException e) {
                   }
                   LineCnt++ ;
                } while (rec.next());
                int RowCount = model.getRowCount();
                showCell(0,0) ;
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
    
    public void ShowMemberByName() {
        int LineCnt = 1 ;
        String TempStr = "%"+TMemName.getText()+"%" ;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try { 
            Statement stmt =  mysql.getConnection().createStatement();
            String LoadTableFile = "select *from customer where sp_desc like '"+TempStr+"' order by sp_desc";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            Date dt = new Date();
            ClearGrid() ;
            rec.first();
            if (rec.getRow() == 0) {
                MSG.ERR(this,"ไม่พบข้อมูลลูกค้า ตามที่ต้องการ...");
                TMemName.requestFocus();
            } else {
                do {
                   try {
                    Object[] input = {
                        LineCnt,
                        rec.getString("sp_code"),
                        rec.getString("sp_desc"),
                        rec.getString("tel")
                    };
                    model.addRow(input);
                   } catch (SQLException e) {
                   }
                   LineCnt++ ;
                } while (rec.next());
                int RowCount = model.getRowCount();
                showCell(0,0) ;
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
        int LineCnt = 1 ;
        String TempStr = "%"+TMemTel.getText()+"%" ;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String LoadTableFile = "select *from customer where (tel like '" + TempStr + "') order by sp_desc";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            Date dt = new Date();
            ClearGrid();
            rec.first();
            if (rec.getRow() == 0) {
                MSG.ERR(this, "ไม่พบข้อมูลลูกค้า ตามที่ต้องการ...");
                TMemTel.requestFocus();
            } else {
                do {
                    try {
                        Object[] input = {
                            LineCnt,
                            rec.getString("sp_code"),
                            rec.getString("sp_desc"),
                            rec.getString("tel")
                        };
                        model.addRow(input);
                    } catch (SQLException e) {
                    }
                    LineCnt++;
                } while (rec.next());
                int RowCount = model.getRowCount();
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
        if(row>0){
        Rectangle rect = tblShowMember.getCellRect(row, column, true);
        tblShowMember.scrollRectToVisible(rect);
        tblShowMember.clearSelection();
        tblShowMember.setRowSelectionInterval(row, row);
        }
    }
    public void bntExitClick() {
        PublicVar.ReturnString = "" ;
        this.dispose();
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: 
     * NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowMember = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ค้นหาข้อมูลลูกค้า (Find Customer)");
        setAlwaysOnTop(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("รหัสลูกค้า");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ชื่อลูกค้า");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("เบอร์โทรศัพท์");

        TMemCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TMemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMemCodeKeyPressed(evt);
            }
        });

        TMemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TMemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMemNameKeyPressed(evt);
            }
        });

        TMemTel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TMemTel.setDragEnabled(true);
        TMemTel.setFocusCycleRoot(true);
        TMemTel.setFocusTraversalPolicyProvider(true);
        TMemTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMemTelKeyPressed(evt);
            }
        });

        bntShowMemAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntShowMemAll.setText("F2-แสดงทั้งหมด");
        bntShowMemAll.setFocusable(false);
        bntShowMemAll.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntShowMemAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntShowMemAllActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntOK.setText("F5-ตกลง (OK)");
        bntOK.setFocusable(false);
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntExit.setText("ESC-ออก(Exit)");
        bntExit.setFocusable(false);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

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
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TMemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TMemName, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(TMemTel))
                .addGap(74, 74, 74)
                .addComponent(bntShowMemAll, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(TMemTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntShowMemAll, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblShowMember.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        tblShowMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ลำดับ", "รหัสลูกค้า (Code)", "ชื่อลูกค้า (Name)", "โทรศัพท์"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1008, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntShowMemAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntShowMemAllActionPerformed
     bntShowMemberAllClick() ;
}//GEN-LAST:event_bntShowMemAllActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick() ;
}//GEN-LAST:event_bntExitActionPerformed

private void TMemCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMemCodeKeyPressed
     if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
         if (TMemCode.getText().length()>0) {
            ShowMemberByCode() ;
         } else {
             TMemName.requestFocus();
         }
     }
     if (evt.getKeyCode()==KeyEvent.VK_F2) {
        bntShowMemberAllClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
        bntExitClick() ;
     }
     
    
}//GEN-LAST:event_TMemCodeKeyPressed

private void TMemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMemNameKeyPressed
    if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
        if (TMemName.getText().length()>0) {
           ShowMemberByName() ;
        } else {
            TMemTel.requestFocus();
        }
     }
    if (evt.getKeyCode()==KeyEvent.VK_F2) {
        bntShowMemberAllClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
        bntExitClick() ;
     }
}//GEN-LAST:event_TMemNameKeyPressed

private void TMemTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMemTelKeyPressed
    if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
        if (TMemTel.getText().length()>0) {
           ShowMemberByTel() ;
        } else {
            TMemCode.requestFocus();
        }
     }
    if (evt.getKeyCode()==KeyEvent.VK_F2) {
        bntShowMemberAllClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
        bntExitClick() ;
     }
}//GEN-LAST:event_TMemTelKeyPressed

private void tblShowMemberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowMemberKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        GetSelectedRow();
    }
     if (evt.getKeyCode()==KeyEvent.VK_F2) {
        bntShowMemberAllClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
     }
     if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
         TMemCode.setText("") ;
         TMemName.setText("") ;
         TMemTel.setText("") ;
         ClearGrid() ;
         TMemTel.requestFocus();
     }
     
     
}//GEN-LAST:event_tblShowMemberKeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
       bntOKClick() ;
}//GEN-LAST:event_bntOKActionPerformed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    TMemTel.requestFocus();
}//GEN-LAST:event_formWindowActivated
public void bntOKClick() {
    GetSelectedRow() ;
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
        if (maxrow>0) { 
           TableSelected = tblShowMember.getValueAt(currow, 1).toString();
           PublicVar.ReturnString = TableSelected;
           this.dispose();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblShowMember;
    // End of variables declaration//GEN-END:variables
}
