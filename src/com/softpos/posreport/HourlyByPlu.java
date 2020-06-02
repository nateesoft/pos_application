package com.softpos.posreport;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.softpos.database.util.MySQLConnect;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.softpos.main.program.Jdi_dailyReport_Hourly_Plu;
import com.softpos.main.model.POSHWSetup;
import com.softpos.main.controller.PPrint;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.Value;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class HourlyByPlu extends javax.swing.JDialog {

    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy(HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    PPrint prn = new PPrint();
    private POSHWSetup POSHW;

    /** Creates new form HourlyByPlu */
    public HourlyByPlu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtTime1.setText("00:00");
        txtTime2.setText("23:59");
        txtPlu1.setText("aaaa");
        txtPlu2.setText("zzzz");
        
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPlu1 = new javax.swing.JTextField();
        txtPlu2 = new javax.swing.JTextField();
        txtTime1 = new javax.swing.JFormattedTextField();
        txtTime2 = new javax.swing.JFormattedTextField();
        bntExit = new javax.swing.JButton();
        bntOK1 = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการขายรายชั่วโมงตามรหัสสินค้า");
        setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("ช่วงเวลา");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("รหัสสินค้า");

        txtPlu1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txtPlu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlu1KeyPressed(evt);
            }
        });

        txtPlu2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txtPlu2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlu2KeyPressed(evt);
            }
        });

        try {
            txtTime1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            
        }
        txtTime1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txtTime1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTime1KeyPressed(evt);
            }
        });

        try {
            txtTime2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            
        }
        txtTime2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txtTime2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTime2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPlu1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPlu2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntOK1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK1.setText("F1- จอภาพ");
        bntOK1.setFocusable(false);
        bntOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOK1ActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bntOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void txtPlu2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlu2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtTime1.requestFocus();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
}//GEN-LAST:event_txtPlu2KeyPressed

private void txtPlu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlu1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtPlu2.requestFocus();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
}//GEN-LAST:event_txtPlu1KeyPressed

private void txtTime1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTime1KeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
     if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtTime2.requestFocus();
    }
}//GEN-LAST:event_txtTime1KeyPressed

private void txtTime2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTime2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtPlu1.requestFocus();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
}//GEN-LAST:event_txtTime2KeyPressed

private void bntOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOK1ActionPerformed
// TODO add your handling code here:
       bntViewClick() ;
}//GEN-LAST:event_bntOK1ActionPerformed
   public void bntViewClick() {
        String Time1 = txtTime1.getText();
        String Time2 = txtTime2.getText();
        String Plu1 = txtPlu1.getText();
        String Plu2 = txtPlu2.getText();        
        List data = new ArrayList();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "delete from temptopsale where terminal='" + Value.MACNO + "'";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }

        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select *from t_sale where (r_plucode>='" + Plu1 + "') and (r_plucode<='" + Plu2 + "') and (r_time>='" + Time1 + "') and (r_time<='" + Time2 + "') and (r_void<>'V') and (r_refund<>'V')";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    String XTime = rec.getString("r_time").substring(0, 3) + "00";
                    String TCode = rec.getString("r_plucode");
                    String TName = ThaiUtil.ASCII2Unicode(rec.getString("r_pname"));
                    Double TQuan = rec.getDouble("r_quan");
                    Double Tamount = rec.getDouble("r_total");
                    if (SeekTemp(XTime, TCode)) {
                        UpdateTemp(XTime, TCode, TName, TQuan, Tamount);
                    } else {
                        InsertTemp(XTime, TCode, TName, TQuan, Tamount);
                    }
                    String[] dataToDisplay  = new String[5];
                    dataToDisplay[0] = XTime;
                    dataToDisplay[1] = TCode;
                    dataToDisplay[2] = TName;
                    dataToDisplay[3] = TQuan+"";
                    dataToDisplay[4] = Tamount+"";
                    data.add(dataToDisplay);  
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }
        
        mysql.close();
        Jdi_dailyReport_Hourly_Plu jdi = new Jdi_dailyReport_Hourly_Plu(new Frame(),true);
        jdi.setData(data);
        jdi.setHeaderPage(Time1, Time2, Plu1, Plu2);
        jdi.setVisible(true);
   }
    public void bntOKClick() {

        String Time1 = txtTime1.getText();
        String Time2 = txtTime2.getText();
        String Plu1 = txtPlu1.getText();
        String Plu2 = txtPlu2.getText();

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "delete from temptopsale where terminal='" + Value.MACNO + "'";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }

        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select *from t_sale where (r_plucode>='" + Plu1 + "') "
                    + "and (r_plucode<='" + Plu2 + "') and (r_time>='" + Time1 + "') "
                    + "and (r_time<='" + Time2 + "') and (r_void<>'V') "
                    + "and (r_refund<>'V') "
                    + "and r_date=curdate()";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    String XTime = rec.getString("r_time").substring(0, 3) + "00";
                    String TCode = rec.getString("r_plucode");
                    String TName = rec.getString("r_pname");
                    Double TQuan = rec.getDouble("r_quan");
                    Double Tamount = rec.getDouble("r_total");
                    if (SeekTemp(XTime, TCode)) {
                        UpdateTemp(XTime, TCode, TName, TQuan, Tamount);
                    } else {
                        InsertTemp(XTime, TCode, TName, TQuan, Tamount);
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }
        
        if(Value.printdriver){
            JOptionPane.showMessageDialog(null, Value.driverNotSupport);
        }else if (!Value.getComPort().equals("NONE")) {
            if (prn.OpenPrint(Value.getComPort())) {
                prn.InitPrinter();
                prn.print(POSHW.getHeading1());
                prn.print(POSHW.getHeading2());
                prn.print(POSHW.getHeading3());
                prn.print(POSHW.getHeading4());
                prn.print("REG ID :" + Value.MACNO);
                prn.print("          รายงานการขายรายชั่วโมง");
                prn.print("         (Hourly By PLU Report)");
                prn.print(" ");
                prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                prn.print("----------------------------------------");
                prn.print("เวลา");
                prn.print("รหัสสินค้า               จำนวน    จำนวนเงิน ");
                prn.print("----------------------------------------");
                String TempGroup = "";
                int Cnt = 1;
                //int CntOrder = 10 ;
                try {
                    Statement stmt =  mysql.getConnection().createStatement();
                    String SqlQuery = "select *from temptopsale where (terminal='" + Value.MACNO + "') order by ttime,r_plucode";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("***" + rec.getString("ttime"));
                        TempGroup = rec.getString("ttime");
                        do {
                            if (!rec.getString("ttime").equals(TempGroup)) {
                                prn.print("***" + rec.getString("ttime"));
                                TempGroup = rec.getString("ttime");
                                Cnt = 1;
                            }
                            prn.print("   "+PUtility.DataFullR(rec.getString("r_plucode"),13)+ " " + PUtility.DataFullR(rec.getString("r_pname"), 20));
                            prn.print("   "+"              "+PUtility.DataFull(IntFmt.format(rec.getDouble("r_quan")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("r_total")), 13));
                           
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }
                prn.print("----------------------------------------");
                prn.print(" ");
                prn.print(" ");
                prn.print(" ");                
                prn.CutPaper();
                prn.closePrint();
            }
        }
        mysql.close();
        txtTime1.requestFocus();

    }

    public void InsertTemp(String XTime, String TCode, String TName, Double TQuan, Double Tamount) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "insert into temptopsale (terminal,ttime,r_plucode,r_pname,r_quan,r_total) " +
                    "values ('" + Value.MACNO + "','" + XTime + "','" + TCode + "','" + TName + "'," + TQuan + "," + Tamount + ")";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }
    }

    public void UpdateTemp(String XTime, String TCode, String TName, Double TQuan, Double Tamount) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "update temptopsale set r_quan=r_quan+" + TQuan + ",r_total=r_total+" + Tamount + ",r_pname='" + TName + "',terminal='" + Value.MACNO + "' " +
                    "where (ttime='" + XTime + "') and (r_plucode='" + TCode + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }
    }

    public Boolean SeekTemp(String XTime, String TCode) {
        Boolean ReturnVal = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt =  mysql.getConnection().createStatement();
            String SqlQuery = "select *from temptopsale where (ttime='" + XTime + "') and (r_plucode='" + TCode + "')";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnVal = false;
            } else {
                ReturnVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally{
            mysql.close();
        }
        
        return ReturnVal;
    }

    public void bntExitClick() {
        this.dispose();
    }

    public void inputfrombnt(String str) {
        if (txtTime1.hasFocus()) {
            String tempstr = "";
            tempstr = txtTime1.getText();
            tempstr = tempstr + str;
            txtTime1.setText(tempstr);
        }
        if (txtTime2.hasFocus()) {
            String tempstr = "";
            tempstr = txtTime2.getText();
            tempstr = tempstr + str;
            txtTime2.setText(tempstr);
        }
        if (txtPlu1.hasFocus()) {
            String tempstr = "";
            tempstr = txtPlu1.getText();
            tempstr = tempstr + str;
            txtPlu1.setText(tempstr);
        }
        if (txtPlu2.hasFocus()) {
            String tempstr = "";
            tempstr = txtPlu2.getText();
            tempstr = tempstr + str;
            txtPlu2.setText(tempstr);
        }
    }

    public void ProcessChkKey(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bntExitClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            bntOKClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtTime1.hasFocus()) {
                txtTime2.requestFocus();
            }
            if (txtTime2.hasFocus()) {
                txtPlu1.requestFocus();
            }
            if (txtPlu1.hasFocus()) {
                txtPlu2.requestFocus();
            }
            if (txtPlu2.hasFocus()) {
                txtTime1.requestFocus();
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntOK1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtPlu1;
    private javax.swing.JTextField txtPlu2;
    private javax.swing.JFormattedTextField txtTime1;
    private javax.swing.JFormattedTextField txtTime2;
    // End of variables declaration//GEN-END:variables
}
