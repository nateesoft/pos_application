package setupmenu;

import database.MySQLConnect;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import soft.virtual.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class SetHeaderMenu extends javax.swing.JDialog {

    /**
     * Creates new form SetHeaderMenu
     */
    public SetHeaderMenu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHeader1 = new javax.swing.JTextField();
        txtHeader2 = new javax.swing.JTextField();
        txtHeader3 = new javax.swing.JTextField();
        txtHeader4 = new javax.swing.JTextField();
        cmdCancel = new javax.swing.JButton();
        cmdOk = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtHeader5 = new javax.swing.JTextField();
        txtHeader6 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHeader9 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHeader8 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHeader7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("กำหนดค่าข้อความบน Tab");
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel1.setText("Head 1 :");

        jLabel2.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel2.setText("Head 2 :");

        jLabel3.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel3.setText("Head 3 :");

        jLabel4.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel4.setText("Head 4 :");

        txtHeader1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader1MouseClicked(evt);
            }
        });
        txtHeader1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader1KeyPressed(evt);
            }
        });

        txtHeader2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader2MouseClicked(evt);
            }
        });
        txtHeader2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader2KeyPressed(evt);
            }
        });

        txtHeader3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader3MouseClicked(evt);
            }
        });
        txtHeader3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader3KeyPressed(evt);
            }
        });

        txtHeader4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader4MouseClicked(evt);
            }
        });
        txtHeader4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader4KeyPressed(evt);
            }
        });

        cmdCancel.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        cmdCancel.setText("ยกเลิก");
        cmdCancel.setFocusable(false);
        cmdCancel.setRequestFocusEnabled(false);
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        cmdOk.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        cmdOk.setText("บันทึกข้อมูล");
        cmdOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOkActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel5.setText("Head 5 :");

        txtHeader5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader5MouseClicked(evt);
            }
        });
        txtHeader5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader5KeyPressed(evt);
            }
        });

        txtHeader6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader6MouseClicked(evt);
            }
        });
        txtHeader6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader6KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel6.setText("Head 6 :");

        txtHeader9.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader9MouseClicked(evt);
            }
        });
        txtHeader9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader9KeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel7.setText("Head 9 :");

        txtHeader8.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader8MouseClicked(evt);
            }
        });
        txtHeader8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader8KeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel8.setText("Head 8 :");

        txtHeader7.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtHeader7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHeader7MouseClicked(evt);
            }
        });
        txtHeader7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHeader7KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Norasi", 1, 15)); // NOI18N
        jLabel9.setText("Head 7 :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader8, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeader9, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(cmdOk, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHeader3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtHeader9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdOk)
                    .addComponent(cmdCancel))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(329, 466));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void cmdOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOkActionPerformed
    /**
     * * OPEN CONNECTION **
     */
    MySQLConnect mysql = new MySQLConnect();
    mysql.open();

    try {
        String head1 = ThaiUtil.Unicode2ASCII(txtHeader1.getText());
        String head2 = ThaiUtil.Unicode2ASCII(txtHeader2.getText());
        String head3 = ThaiUtil.Unicode2ASCII(txtHeader3.getText());
        String head4 = ThaiUtil.Unicode2ASCII(txtHeader4.getText());
        String head5 = ThaiUtil.Unicode2ASCII(txtHeader5.getText());
        String head6 = ThaiUtil.Unicode2ASCII(txtHeader6.getText());
        String head7 = ThaiUtil.Unicode2ASCII(txtHeader7.getText());
        String head8 = ThaiUtil.Unicode2ASCII(txtHeader8.getText());
        String head9 = ThaiUtil.Unicode2ASCII(txtHeader9.getText());
        String sql = "update company set "
                + "head1='" + head1 + "',"
                + "head2='" + head2 + "',"
                + "head3='" + head3 + "',"
                + "head4='" + head4 + "',"
                + "head5='" + head5 + "',"
                + "head6='" + head6 + "',"
                + "head7='" + head7 + "',"
                + "head8='" + head8 + "',"
                + "head9='" + head9 + "'";
        Statement stmt = mysql.getConnection().createStatement();
        if (stmt.executeUpdate(sql) > 0) {
            MSG.NOTICE(this, "บันทึกข้อมูลเรียบร้อยแล้ว");
        }

        stmt.close();
    } catch (SQLException e) {
        MSG.ERR(this, e.getMessage());
        e.printStackTrace();
    } finally {
        mysql.close();
    }

    dispose();
}//GEN-LAST:event_cmdOkActionPerformed

private void txtHeader1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtHeader2.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_txtHeader1KeyPressed

private void txtHeader2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader2KeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtHeader3.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_txtHeader2KeyPressed

private void txtHeader3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader3KeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtHeader4.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_txtHeader3KeyPressed

private void txtHeader4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader4KeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        cmdOk.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_txtHeader4KeyPressed

private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
    dispose();
}//GEN-LAST:event_cmdCancelActionPerformed

    private void txtHeader5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeader5KeyPressed

    private void txtHeader6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeader6KeyPressed

    private void txtHeader9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeader9KeyPressed

    private void txtHeader8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeader8KeyPressed

    private void txtHeader7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeader7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeader7KeyPressed

    private void txtHeader1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader1MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader1);
        }
    }//GEN-LAST:event_txtHeader1MouseClicked

    private void txtHeader2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader2MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader2);
        }
    }//GEN-LAST:event_txtHeader2MouseClicked

    private void txtHeader3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader3MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader3);
        }
    }//GEN-LAST:event_txtHeader3MouseClicked

    private void txtHeader4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader4MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader4);
        }
    }//GEN-LAST:event_txtHeader4MouseClicked

    private void txtHeader5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader5MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader5);
        }
    }//GEN-LAST:event_txtHeader5MouseClicked

    private void txtHeader6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader6MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader6);
        }
    }//GEN-LAST:event_txtHeader6MouseClicked

    private void txtHeader7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader7MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader9);
        }
    }//GEN-LAST:event_txtHeader7MouseClicked

    private void txtHeader8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader8MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader8);
        }
    }//GEN-LAST:event_txtHeader8MouseClicked

    private void txtHeader9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeader9MouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtHeader7);
        }
    }//GEN-LAST:event_txtHeader9MouseClicked

    public static void main(String args[]) {
        new MySQLConnect();
        SetHeaderMenu dialog = new SetHeaderMenu(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtHeader1;
    private javax.swing.JTextField txtHeader2;
    private javax.swing.JTextField txtHeader3;
    private javax.swing.JTextField txtHeader4;
    private javax.swing.JTextField txtHeader5;
    private javax.swing.JTextField txtHeader6;
    private javax.swing.JTextField txtHeader7;
    private javax.swing.JTextField txtHeader8;
    private javax.swing.JTextField txtHeader9;
    // End of variables declaration//GEN-END:variables

    void loadData() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        String sql = "SELECT * FROM company";
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String hh1 = ThaiUtil.ASCII2Unicode(rs.getString("head1"));
                String hh2 = ThaiUtil.ASCII2Unicode(rs.getString("head2"));
                String hh3 = ThaiUtil.ASCII2Unicode(rs.getString("head3"));
                String hh4 = ThaiUtil.ASCII2Unicode(rs.getString("head4"));
                String hh5 = ThaiUtil.ASCII2Unicode(rs.getString("head5"));
                String hh6 = ThaiUtil.ASCII2Unicode(rs.getString("head6"));
                String hh7 = ThaiUtil.ASCII2Unicode(rs.getString("head7"));
                String hh8 = ThaiUtil.ASCII2Unicode(rs.getString("head8"));
                String hh9 = ThaiUtil.ASCII2Unicode(rs.getString("head9"));

                txtHeader1.setText(hh1);
                txtHeader2.setText(hh2);
                txtHeader3.setText(hh3);
                txtHeader4.setText(hh4);
                txtHeader5.setText(hh5);
                txtHeader6.setText(hh6);
                txtHeader7.setText(hh7);
                txtHeader8.setText(hh8);
                txtHeader9.setText(hh9);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }
    }

}
