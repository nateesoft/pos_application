package setupmenu;

import database.MySQLConnect_Bak;
import java.awt.Color;
import java.awt.Cursor;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sun.natee.project.util.ThaiUtil;
import util.JTableUtility;

public class DlgBrowseProduct extends javax.swing.JDialog {

    public DlgBrowseProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        new MySQLConnect_Bak();
        
        initTblGroup();
        initTblPlu();
        loadTblGroup();
        cmdOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblGroup.setBackground(new Color(231, 231, 253));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGroup = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlu = new javax.swing.JTable();
        cmdExit = new javax.swing.JButton();
        cmdOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("แสดงรายการรหัสสินค้า (PLU List)");
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblGroup.setBackground(new java.awt.Color(231, 231, 253));
        tblGroup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสกลุ่ม", "รายชื่อกลุ่ม"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGroup.setRowHeight(25);
        tblGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblGroupMouseReleased(evt);
            }
        });
        tblGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblGroupKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblGroup);

        jPanel5.setBackground(new java.awt.Color(170, 168, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("กลุ่มสินค้า (Product Group)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(255, 203, 152));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("รายการสินค้า (Item PLU)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        tblPlu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPlu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสินค้า", "ชื่อสินค้า/รายการ", "Eat-In", "Take Away", "Delivery", "Pinto", "Whole"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlu.setRowHeight(25);
        tblPlu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPluMouseReleased(evt);
            }
        });
        tblPlu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblPluKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblPlu);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        cmdExit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdExit.setText("ออก (Exit)");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        cmdOk.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdOk.setText("ตกลง (OK)");
        cmdOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdOk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1008, 659));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


private void tblGroupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGroupMouseReleased
    if (evt.getClickCount() == 1) {
        onTblGroupSelectEvent();
    }
}//GEN-LAST:event_tblGroupMouseReleased

private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
    dispose();
}//GEN-LAST:event_cmdExitActionPerformed

private void cmdOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOkActionPerformed
    onOk();
    dispose();
}//GEN-LAST:event_cmdOkActionPerformed

private void tblPluMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPluMouseReleased
    if (evt.getClickCount() == 2) {
        onOk();
        dispose();
    }
}//GEN-LAST:event_tblPluMouseReleased

private void tblPluKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPluKeyReleased
    if (evt.getKeyCode() == evt.VK_ENTER) {
        onOkEnter();
        dispose();
    }
    if (evt.getKeyCode() == evt.VK_ESCAPE) {
        dispose();
    }
    if (evt.getKeyCode() == evt.VK_LEFT) {
        tblGroup.requestFocus();
    }
}//GEN-LAST:event_tblPluKeyReleased

private void tblGroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGroupKeyReleased
    if ((evt.getKeyCode() == evt.VK_UP) || (evt.getKeyCode() == evt.VK_DOWN)) {
        onTblGroupSelectEvent();
    } else {
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            tblPlu.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            dispose();
        }
    }
}//GEN-LAST:event_tblGroupKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgBrowseProduct dialog = new DlgBrowseProduct(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblGroup;
    private javax.swing.JTable tblPlu;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel mdlGroup;
    private DefaultTableModel mdlPlu;

    private ChoosePluBean selectPlu;

    private void initTblGroup() {
        mdlGroup = (DefaultTableModel) tblGroup.getModel();
        JTableUtility.initDefaultTable(tblGroup);
        int[] column = {70, 215};
        JTableUtility.initColumnSizeTable(tblGroup, column);
        JTableUtility.initHorizontalAlignmentTable(tblGroup, null, null, null);
        //tblGroup.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void initTblPlu() {
        mdlPlu = (DefaultTableModel) tblPlu.getModel();
        JTableUtility.initDefaultTable(tblPlu);
        int[] column = {80, 250, 80, 80, 80, 80, 80};
        JTableUtility.initColumnSizeTable(tblPlu, column);
        int[] right = {2, 3, 4, 5, 6};
        JTableUtility.initHorizontalAlignmentTable(tblPlu, null, null, right);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    private void loadTblGroup() {
        JTableUtility.resetTableModel(mdlGroup);
        DbGroupfile dgroup = new DbGroupfile();
        List<Map<String, Object>> list = dgroup.getAllData();

        for (Map<String, Object> row : list) {
            String[] data = new String[tblGroup.getColumnCount()];
            data[0] = row.get("groupcode").toString();
            data[1] = ThaiUtil.ASCII2Unicode(row.get("groupname").toString());
            mdlGroup.addRow(data);
        }

        if (mdlGroup.getRowCount() != 0) {
            tblGroup.setRowSelectionInterval(0, 0);
            loadTblPlu(tblGroup.getValueAt(0, 0).toString());
        }
    }

    private void loadTblPlu(String pgroup) {
        JTableUtility.resetTableModel(mdlPlu);
        DbProduct dproduct = new DbProduct();
        List<Map<String, Object>> list = dproduct.getAtPgroup(pgroup);
        for (Map<String, Object> row : list) {
            String[] data = new String[tblPlu.getColumnCount()];
            data[0] = row.get("pcode").toString();
            data[1] = ThaiUtil.ASCII2Unicode(row.get("pdesc").toString());
            data[2] = row.get("pprice11").toString();
            data[3] = row.get("pprice12").toString();
            data[4] = row.get("pprice13").toString();
            data[5] = row.get("pprice14").toString();
            data[6] = row.get("pprice15").toString();
            mdlPlu.addRow(data);
        }
        if (mdlPlu.getRowCount() != 0) {
            tblPlu.setRowSelectionInterval(0, 0);
        }
    }

    private void onTblGroupSelectEvent() {
        int index = tblGroup.getSelectedRow();
        if (index != -1) {
            loadTblPlu(tblGroup.getValueAt(index, 0).toString());
        }

    }

    private void onOk() {
        int index = tblPlu.getSelectedRow();

        if (index == -1) {
            index = 0;
        }
        selectPlu = new ChoosePluBean();
        selectPlu.code = tblPlu.getValueAt(index, 0).toString();
        selectPlu.name = tblPlu.getValueAt(index, 1).toString();
        try {
            selectPlu.price = Double.parseDouble(tblPlu.getValueAt(index, 2).toString());
        } catch (NumberFormatException e) {
            selectPlu.price = -1.0;
        }
    }

    private void onOkEnter() {
        int index = tblPlu.getSelectedRow() - 1;

        if (index == -1) {
            index = 0;
        }
        selectPlu = new ChoosePluBean();
        selectPlu.code = tblPlu.getValueAt(index, 0).toString();
        selectPlu.name = tblPlu.getValueAt(index, 1).toString();
        try {
            selectPlu.price = Double.parseDouble(tblPlu.getValueAt(index, 2).toString());
        } catch (NumberFormatException e) {
            selectPlu.price = -1.0;
        }
    }

    public ChoosePluBean getSelectPlu() {
        return selectPlu;
    }

    public class ChoosePluBean {

        private String code;
        private String name;
        private double price;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

    }

    private void onScrollTblPluAction(JTable table, int i) {
        int row = table.getSelectedRow();
        if (row == 0) {
            if (i != -1) {
                row += i;
            }
        } else if (row == table.getRowCount() - 1) {
            if (i != 1) {
                row += i;
            }
        } else {
            row += i;
        }

        if (table.getRowCount() != 0) {
            table.setRowSelectionInterval(row, row);
            table.scrollRectToVisible(table.getCellRect(row, 0, true));
        }
    }
}
