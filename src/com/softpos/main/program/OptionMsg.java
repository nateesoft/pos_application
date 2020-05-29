package com.softpos.main.program;

import com.softpos.main.utils.PUtility;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.model.BalanceBean;
import java.awt.Color;
import database.MySQLConnect;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import com.softpos.main.utils.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class OptionMsg extends javax.swing.JDialog {

    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private String tableNo;
    private String index;
    private BalanceBean bean;

    public OptionMsg(java.awt.Frame parent, boolean modal, String tableNo, String index) {
        super(parent, modal);
        initComponents();

        this.index = index;
        this.tableNo = tableNo;

        BalanceControl bControl = new BalanceControl();
        bean = bControl.getBalanceIndex(tableNo, index);

        model1 = (DefaultTableModel) tblOptionMsg.getModel();
        tblOptionMsg.setShowGrid(true);
        tblOptionMsg.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblOptionMsg.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblOptionMsg.setRowSelectionAllowed(true);
        tblOptionMsg.setGridColor(Color.gray);

        JTableHeader header1 = tblOptionMsg.getTableHeader();
        //header.setBackground(Color.yellow);
        header1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));
        int[] ColSize = {250};
        for (int i = 0; i < 1; i++) {
            //int vColIndex = 0;
            TableColumn col1 = tblOptionMsg.getColumnModel().getColumn(i);
            col1.setPreferredWidth(ColSize[i]);
        }

        model2 = (DefaultTableModel) tblSelected.getModel();
        tblSelected.setShowGrid(true);
        tblSelected.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblSelected.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSelected.setRowSelectionAllowed(true);
        tblSelected.setGridColor(Color.gray);

        JTableHeader header2 = tblSelected.getTableHeader();
        //header.setBackground(Color.yellow);
        header2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));
        int[] ColSize2 = {250};
        for (int i = 0; i < 1; i++) {
            //int vColIndex = 0;
            TableColumn col2 = tblSelected.getColumnModel().getColumn(i);
            col2.setPreferredWidth(ColSize2[i]);
        }
        LoadDataFromDb();
        LoadOptionArray();
    }

    private void LoadOptionArray() {
        int RowCount = model2.getRowCount();
        for (int i = 0; i < RowCount; i++) {
            model2.removeRow(0);
        }
        String[] opt = new String[]{
            bean.getR_Opt1(),
            bean.getR_Opt2(),
            bean.getR_Opt3(),
            bean.getR_Opt4(),
            bean.getR_Opt5(),
            bean.getR_Opt6(),
            bean.getR_Opt7(),
            bean.getR_Opt8()
        };
        for (String opt1 : opt) {
            Object[] input = {ThaiUtil.ASCII2Unicode(opt1)};
            if (!opt1.equals("")) {
                model2.addRow(input);
            }
        }
    }

    private void LoadDataFromDb() {
        ShowGroup.setText("กลุ่ม : " + PUtility.SeekGroupName(bean.getR_Group()));
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadOption = "select *from optionfile where pgroup='" + bean.getR_Group() + "'";
            ResultSet rec = stmt.executeQuery(LoadOption);
            //Clear tblOptionMsg       
            int RowCount = model1.getRowCount();
            for (int i = 0; i <= RowCount - 1; i++) {
                model1.removeRow(0);
            }
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    Object[] input = {ThaiUtil.ASCII2Unicode(rec.getString("optionname"))};
                    model1.addRow(input);
                } while (rec.next());
                showCell1(0, 0);
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void bntSelectedClick() {
        int row = tblOptionMsg.getSelectedRow();
        if (row != -1) {
            String data = tblOptionMsg.getValueAt(row, 0).toString();
            int rowModel2 = model2.getRowCount();
            boolean isAdd = false;
            if (rowModel2 == 0) {
                isAdd = true;
            }

            if (rowModel2 <= 8) {
                for (int i = 0; i < rowModel2; i++) {
                    isAdd = true;
                    String data2 = model2.getValueAt(i, 0).toString();
                    if (data.equals(data2)) {
                        isAdd = false;
                        break;
                    }
                }

                if (isAdd) {
                    model2.addRow(new String[]{data});
                }
            }
        }
    }

    public void InputMsgToSelectedTable(String dataOpt) {
//        boolean inputData = false;
//        for (int i = 0; i < 8; i++) {
//            if (!inputData) {
//                if (!OptionArray[i].equals(dataOpt)) {
//                    if (OptionArray[i].equals("")) {
//                        OptionArray[i] = dataOpt;
//                        inputData = true;
//                    }
//                } else {
//                    inputData = true;
//                }
//            }
//        }

        LoadOptionArray();
    }

    public void showCell1(int row, int column) {
        Rectangle rect = tblOptionMsg.getCellRect(row, column, true);
        tblOptionMsg.scrollRectToVisible(rect);
        tblOptionMsg.clearSelection();
        tblOptionMsg.setRowSelectionInterval(row, row);
    }

    public void bntDeleteClick() {
        LoadOptionArray();
    }

    public void bntOKClick() {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String[] opt = new String[]{"", "", "", "", "", "", "", ""};
            BalanceBean bBean = new BalanceBean();
            bBean.setR_Index(index);
            bBean.setR_Table(tableNo);

            for (int i = 0; i < model2.getRowCount(); i++) {
                opt[i] = model2.getValueAt(i, 0).toString();
            }

            String SqlQuery = "update balance set "
                    + "r_opt1='" + ThaiUtil.Unicode2ASCII(opt[0]) + "',"
                    + "r_opt2='" + ThaiUtil.Unicode2ASCII(opt[1]) + "',"
                    + "r_opt3='" + ThaiUtil.Unicode2ASCII(opt[2]) + "',"
                    + "r_opt4='" + ThaiUtil.Unicode2ASCII(opt[3]) + "',"
                    + "r_opt5='" + ThaiUtil.Unicode2ASCII(opt[4]) + "',"
                    + "r_opt6='" + ThaiUtil.Unicode2ASCII(opt[5]) + "',"
                    + "r_opt7='" + ThaiUtil.Unicode2ASCII(opt[6]) + "',"
                    + "r_opt8='" + ThaiUtil.Unicode2ASCII(opt[7]) + "' "
                    + "where r_index='" + index + "' "
                    + "and r_table='" + tableNo + "'";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        this.dispose();
    }

    public void bntAddClick() {
        txtAdd.setFocusable(true);
        txtAdd.requestFocus();
    }

    public void bntAddExit() {
        if (!txtAdd.getText().equals("")) {
            InputMsgToSelectedTable(txtAdd.getText());
            int icon = JOptionPane.showConfirmDialog(this, "ต้องการให้เพิ่มข้อมูลเก็บไว้ในระบบ เพื่อใช้ในครั้งต่อไปหรือไม่ ? \nกด Yes เพื่อยืนยัน.");
            if (icon == JOptionPane.YES_OPTION) {
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();
                try {
                    String sqlDel = "delete from optionfile "
                            + "where PGroup='" + bean.getR_Group() + "' "
                            + "and OptionName='" + ThaiUtil.Unicode2ASCII(txtAdd.getText()) + "'";
                    Statement stmt = mysql.getConnection().createStatement();
                    stmt.executeUpdate(sqlDel);

                    String sql = "insert into optionfile(PGroup, OptionName) "
                            + "values('" + bean.getR_Group() + "','" + ThaiUtil.Unicode2ASCII(txtAdd.getText()) + "');";
                    stmt.executeUpdate(sql);
                    
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(this, e.getMessage());
                }finally{
                    mysql.close();
                }
            }
            txtAdd.setFocusable(false);
            txtAdd.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pShowGroup = new javax.swing.JPanel();
        ShowGroup = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSelected = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOptionMsg = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtAdd = new javax.swing.JTextField();
        bntAdd = new javax.swing.JButton();
        btnKeyboard = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bntOK1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("เลือกคำสั่งพิเศษ พิมพ์ออกครัว");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pShowGroup.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ShowGroup.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ShowGroup.setText("กลุ่ม : เครื่องดื่ม");

        javax.swing.GroupLayout pShowGroupLayout = new javax.swing.GroupLayout(pShowGroup);
        pShowGroup.setLayout(pShowGroupLayout);
        pShowGroupLayout.setHorizontalGroup(
            pShowGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pShowGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ShowGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pShowGroupLayout.setVerticalGroup(
            pShowGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pShowGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ShowGroup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSelected.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblSelected.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รายการคำสั่งพิเศษที่เลือก"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSelected.setOpaque(false);
        tblSelected.setRowHeight(25);
        tblSelected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSelectedMouseClicked(evt);
            }
        });
        tblSelected.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSelectedKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSelected);

        tblOptionMsg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblOptionMsg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รายการคำสั่งพิเศษ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOptionMsg.setOpaque(false);
        tblOptionMsg.setRowHeight(25);
        tblOptionMsg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOptionMsgMouseClicked(evt);
            }
        });
        tblOptionMsg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblOptionMsgKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblOptionMsg);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddKeyPressed(evt);
            }
        });

        bntAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntAdd.setText("F2-เพิ่มรายการพิเศษ");
        bntAdd.setFocusable(false);
        bntAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAddActionPerformed(evt);
            }
        });

        btnKeyboard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKeyboard.setText("KeyBoard");
        btnKeyboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeyboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnKeyboard, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKeyboard, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("ตกลง (OK)");
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(17, 29, 149));
        jLabel2.setText("เลือก >>");

        bntOK1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK1.setText("ออก (Exit)");
        bntOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pShowGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pShowGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(732, 520));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAddActionPerformed
    addOtherOption();
}//GEN-LAST:event_bntAddActionPerformed

private void tblOptionMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblOptionMsgKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1 || evt.getKeyCode() == KeyEvent.VK_RIGHT) {
        bntSelectedClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F2) {
        bntAddClick();
    }

}//GEN-LAST:event_tblOptionMsgKeyPressed

private void tblSelectedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSelectedKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.dispose();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F2) {
        bntAddClick();
    }
}//GEN-LAST:event_tblSelectedKeyPressed

    private void tblOptionMsgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOptionMsgMouseClicked
        bntSelectedClick();
    }//GEN-LAST:event_tblOptionMsgMouseClicked

    private void btnKeyboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeyboardActionPerformed
        bntAddActionPerformed(null);

        KeyBoardDialog kb = new KeyBoardDialog(null, true, 0);
        kb.setVisible(true);

        if (!KeyBoardDialog.TEXT_INPUT.equals("")) {
            txtAdd.setText(KeyBoardDialog.TEXT_INPUT);
        }
    }//GEN-LAST:event_btnKeyboardActionPerformed

    private void tblSelectedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectedMouseClicked
        removeOption();
    }//GEN-LAST:event_tblSelectedMouseClicked

    private void txtAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !txtAdd.getText().trim().equals("")) {
            addOtherOption();
        } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
            btnKeyboardActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bntOKClick();
        }
    }//GEN-LAST:event_txtAddKeyPressed

    private void bntOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOK1ActionPerformed
        dispose();
    }//GEN-LAST:event_bntOK1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OptionMsg dialog = new OptionMsg(new javax.swing.JFrame(), true, "1", "1/001");
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
    private javax.swing.JLabel ShowGroup;
    private javax.swing.JButton bntAdd;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntOK1;
    private javax.swing.JButton btnKeyboard;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pShowGroup;
    private javax.swing.JTable tblOptionMsg;
    private javax.swing.JTable tblSelected;
    private javax.swing.JTextField txtAdd;
    // End of variables declaration//GEN-END:variables

    private void removeOption() {
        int row = tblSelected.getSelectedRow();
        if (row != -1) {
            model2.removeRow(row);
        }
    }

    private void addOtherOption() {
        String data = txtAdd.getText();
        if (data.trim().equals("")) {
            return;
        }
        int rowModel2 = model2.getRowCount();
        boolean isAdd = false;
        if (rowModel2 == 0) {
            isAdd = true;
        }

        if (rowModel2 <= 8) {
            for (int i = 0; i < rowModel2; i++) {
                isAdd = true;
                String data2 = model2.getValueAt(i, 0).toString();
                if (data.equals(data2)) {
                    isAdd = false;
                    break;
                }
            }

            if (isAdd) {
                model2.addRow(new String[]{data});
                txtAdd.setText("");
            }
        }
    }

}
