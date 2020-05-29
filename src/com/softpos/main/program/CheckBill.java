package com.softpos.main.program;

import com.softpos.main.controller.PPrint;
import com.softpos.main.controller.NumberControl;
import com.softpos.main.model.POSHWSetup;
import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.Value;
import com.softpos.main.model.PublicVar;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.model.TableFileBean;
import com.softpos.main.controller.PosControl;
import com.softpos.main.controller.TableFileControl;
import com.softpos.main.model.BillNoBean;
import com.softpos.main.controller.BranchControl;
import com.softpos.main.controller.BillControl;
import com.softpos.main.model.BalanceBean;
import com.softpos.main.model.CuponBean;
import com.softpos.main.model.DiscountBean;
import com.softpos.main.view.DiscountDialog;
//import com.softpos.floorplan.DailyRep;
//import com.softpos.floorplan.DiarySale;
//import com.softpos.floorplan.MoveGroupTable;
//import com.softpos.floorplan.PaidinFrm;
//import com.softpos.floorplan.RefundBill;
//import com.softpos.floorplan.ResonPaidoutFrm;
//import com.softpos.floorplan.ShowTable;
import static com.softpos.main.controller.BalanceControl.updateProSerTable;
import com.softpos.main.model.MemberBean;
import database.MySQLConnect;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import printReport.PrintSimpleForm;
import com.softpos.main.utils.JTableControl;
import sun.natee.project.util.NumberFormat;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class CheckBill extends javax.swing.JDialog {

    public String tableNo;
    private TableFileBean tBean;
    private DefaultTableModel model;
    private DecimalFormat dec = new DecimalFormat("#,##0.00");
    private DecimalFormat intFM = new DecimalFormat("#,##0");
    private DiscountBean discBean = new DiscountBean();
    private MemberBean memberBean;
    private double TCreditCharge = 0.00;
    private double CreditCharge = 0.00;
    private POSHWSetup POSHW;
    private POSConfigSetup CONFIG;

    public CheckBill(java.awt.Dialog parent, boolean modal, String tableNo, MemberBean memberBean, String member1, String member2) {
        super(parent, modal);
        initComponents();
        DecimalFormat intFM = new DecimalFormat("##0");

        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        Dimension d = getMaximumSize();
        setSize(1024, 768);
        setLocationRelativeTo(null);
        PublicVar.temp_table = tableNo;
        this.memberBean = memberBean;
        this.tableNo = tableNo;
//        txtMember1.setText(member1);
//        txtMember2.setText(member2);
        if (memberBean != null) {
            txtMember1.setText(ThaiUtil.ASCII2Unicode(memberBean.getMember_NameThai()));
            txtMember2.setText("แต้มสะสม" + intFM.format(memberBean.getMember_TotalScore()));
        } else {
            txtMember1.setText("ค้นหาสมาชิก");
            txtMember2.setText("แต้มสะสม 0 แต้ม");
        }

        jLabel9.setVisible(false);
        jLabel11.setVisible(false);
//        txtArCode.setVisible(false);
//        txtArAmount.setVisible(false);
//        jPanel3.setVisible(false);
//        btnCredit1.setVisible(false);
//        lbCreditAmt.setVisible(false);
//        lbCreditMoney.setVisible(false);
//        bntEarnest.setEnabled(false);
//        txtReturnMoneyAmount.setEnabled(false);
        jLabel5.setVisible(true);
        jLabel10.setVisible(false);
//        jLabel7.setVisible(false);
        txtSubTotal.setVisible(true);
        txtTotalService.setVisible(true);
        jPanelDiscount.setVisible(true);
        lbCreditMoney.setVisible(false);
        jLabel11.setVisible(false);
        lbCreditAmt.setVisible(false);
        jPanel3.setVisible(false);
//        bntEarnest.setVisible(false);
//        txtReturnMoneyAmount.setVisible(false);
//        jPanelMember.setVisible(false);
        bntPrintCheckBill.setVisible(false);
        BalanceControl.updateProSerTable(tableNo, memberBean);
        initTable();

    }

    public CheckBill(Object object, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanelDiscount = new javax.swing.JPanel();
        txtTotalService = new javax.swing.JTextField();
        txtPromotion = new javax.swing.JTextField();
        txtItemDisc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bntPrintCheckBill = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowBalance = new javax.swing.JTable();
        txtBillNo = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        lbAddMoney = new javax.swing.JLabel();
        txtTotalCash = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        Digital_Msg = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtCashAmount = new javax.swing.JTextField();
        bntCash = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtCreditName = new javax.swing.JTextField();
        txtCreditTrackNo = new javax.swing.JTextField();
        txtCreditAmount = new javax.swing.JTextField();
        txtCreditNo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCredit = new javax.swing.JButton();
        btnDiscountAll = new javax.swing.JButton();
        txtDiscountAmount = new javax.swing.JTextField();
        jPanelMember = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMember1 = new javax.swing.JTextField();
        txtMember2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbArName = new javax.swing.JLabel();
        panelNumber = new javax.swing.JPanel();
        btn1000 = new javax.swing.JButton();
        btn500 = new javax.swing.JButton();
        btn100 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnCLR = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnAR = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnGiftVoucher = new javax.swing.JButton();
        txtGiftVoucherAmount = new javax.swing.JTextField();
        txtEntertainAmount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnCredit1 = new javax.swing.JButton();
        txtArCode = new javax.swing.JTextField();
        txtArAmount = new javax.swing.JTextField();
        bntEarnest = new javax.swing.JButton();
        txtReturnMoneyAmount = new javax.swing.JTextField();
        bntPrintCheckBill1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbCredit = new javax.swing.JLabel();
        lbCreditMoney = new javax.swing.JLabel();
        lbCreditAmt = new javax.swing.JLabel();
        bntPrintCheckBill2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("การรับชำระเงิน (Payment Form)");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelDiscount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelDiscount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTotalService.setEditable(false);
        txtTotalService.setBackground(new java.awt.Color(204, 255, 255));
        txtTotalService.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalService.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalService.setText("0.00");
        txtTotalService.setDisabledTextColor(java.awt.Color.black);
        txtTotalService.setEnabled(false);
        txtTotalService.setRequestFocusEnabled(false);
        jPanelDiscount.add(txtTotalService, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 88, 30));

        txtPromotion.setEditable(false);
        txtPromotion.setBackground(new java.awt.Color(204, 255, 255));
        txtPromotion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPromotion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPromotion.setText("0.00");
        txtPromotion.setDisabledTextColor(java.awt.Color.black);
        txtPromotion.setEnabled(false);
        txtPromotion.setRequestFocusEnabled(false);
        jPanelDiscount.add(txtPromotion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 89, 30));

        txtItemDisc.setEditable(false);
        txtItemDisc.setBackground(new java.awt.Color(204, 255, 255));
        txtItemDisc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtItemDisc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtItemDisc.setText("0.00");
        txtItemDisc.setDisabledTextColor(java.awt.Color.black);
        txtItemDisc.setEnabled(false);
        txtItemDisc.setRequestFocusEnabled(false);
        jPanelDiscount.add(txtItemDisc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 88, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ส่วนลดรายการ");
        jPanelDiscount.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 88, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ส่วนลดโปรโมชั่น");
        jPanelDiscount.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ค่าบริการ");
        jPanelDiscount.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 88, -1));

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(204, 255, 255));
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setText("0.00");
        txtSubTotal.setDisabledTextColor(java.awt.Color.black);
        txtSubTotal.setEnabled(false);
        txtSubTotal.setRequestFocusEnabled(false);
        jPanelDiscount.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 88, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ส่วนลดท้ายบิล");
        jPanelDiscount.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 88, -1));

        bntPrintCheckBill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntPrintCheckBill.setText("F8 พิมพ์ตรวจสอบ");
        bntPrintCheckBill.setFocusable(false);
        bntPrintCheckBill.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrintCheckBill.setRequestFocusEnabled(false);
        bntPrintCheckBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintCheckBillActionPerformed(evt);
            }
        });

        tblShowBalance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblShowBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Product Name", "Quantity", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShowBalance.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(tblShowBalance);
        if (tblShowBalance.getColumnModel().getColumnCount() > 0) {
            tblShowBalance.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblShowBalance.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        txtBillNo.setEditable(false);
        txtBillNo.setBackground(new java.awt.Color(255, 255, 255));
        txtBillNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBillNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBillNo.setFocusable(false);
        txtBillNo.setRequestFocusEnabled(false);

        jPanel22.setBackground(new java.awt.Color(0, 102, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setRequestFocusEnabled(false);

        lbAddMoney.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbAddMoney.setForeground(new java.awt.Color(255, 255, 255));
        lbAddMoney.setText("มูลค่าสินค้ารวม (Total Amount)");
        lbAddMoney.setRequestFocusEnabled(false);
        lbAddMoney.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        txtTotalCash.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalCash.setForeground(new java.awt.Color(255, 255, 0));
        txtTotalCash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalCash.setText("0.00");
        txtTotalCash.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        txtTotalCash.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbAddMoney)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalCash, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtTotalCash, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Digital_Msg.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Digital_Msg.setForeground(new java.awt.Color(255, 255, 255));
        Digital_Msg.setText("จำนวนเงิน :");
        Digital_Msg.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        txtTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(255, 255, 0));
        txtTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalAmount.setText("0.00");
        txtTotalAmount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(Digital_Msg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(Digital_Msg, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txtCashAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtCashAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCashAmount.setText("0.00");
        txtCashAmount.setFocusable(false);
        txtCashAmount.setMinimumSize(new java.awt.Dimension(170, 35));
        txtCashAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashAmountKeyPressed(evt);
            }
        });

        bntCash.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntCash.setText("เงินสด (Cash)");
        bntCash.setFocusable(false);
        bntCash.setRequestFocusEnabled(false);
        bntCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bntCash, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCashAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCashAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        txtCreditName.setEditable(false);
        txtCreditName.setBackground(new java.awt.Color(255, 255, 255));
        txtCreditName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCreditName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCreditName.setFocusable(false);

        txtCreditTrackNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCreditTrackNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCreditTrackNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCreditTrackNoMouseClicked(evt);
            }
        });
        txtCreditTrackNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditTrackNoActionPerformed(evt);
            }
        });
        txtCreditTrackNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditTrackNoFocusGained(evt);
            }
        });
        txtCreditTrackNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditTrackNoKeyPressed(evt);
            }
        });

        txtCreditAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCreditAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCreditAmount.setText("0.00");
        txtCreditAmount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCreditAmountMouseClicked(evt);
            }
        });
        txtCreditAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditAmountActionPerformed(evt);
            }
        });
        txtCreditAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditAmountFocusGained(evt);
            }
        });
        txtCreditAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditAmountKeyPressed(evt);
            }
        });

        txtCreditNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCreditNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCreditNo.setFocusable(false);
        txtCreditNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditNoFocusGained(evt);
            }
        });
        txtCreditNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditNoKeyPressed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setBackground(new java.awt.Color(153, 255, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Credit Card NO.");

        jLabel2.setBackground(new java.awt.Color(153, 255, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trace NO");

        jLabel8.setBackground(new java.awt.Color(153, 255, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("จำนวนเงิน");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(45, 45, 45))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addComponent(jLabel8))
        );

        btnCredit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCredit.setText("บัตรเครดิต (Credit Card)");
        btnCredit.setFocusable(false);
        btnCredit.setRequestFocusEnabled(false);
        btnCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(txtCreditTrackNo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCreditNo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCredit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCreditName)
                            .addComponent(txtCreditAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCreditName, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnCredit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCreditNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditTrackNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnDiscountAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDiscountAll.setText("ส่วนลดต่าง ๆ");
        btnDiscountAll.setFocusable(false);
        btnDiscountAll.setRequestFocusEnabled(false);
        btnDiscountAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountAllActionPerformed(evt);
            }
        });

        txtDiscountAmount.setEditable(false);
        txtDiscountAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtDiscountAmount.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        txtDiscountAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscountAmount.setText("0.00");
        txtDiscountAmount.setFocusable(false);

        jPanelMember.setBackground(new java.awt.Color(255, 255, 153));

        jLabel12.setBackground(new java.awt.Color(255, 255, 153));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("สมาชิก : ");

        txtMember1.setEditable(false);
        txtMember1.setBackground(new java.awt.Color(0, 102, 204));
        txtMember1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMember1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMember1.setText(" <ท่านไม่ได้ใช้ระบบสมาชิก> ");
        txtMember1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMember1.setFocusable(false);
        txtMember1.setRequestFocusEnabled(false);
        txtMember1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMember1MouseClicked(evt);
            }
        });
        txtMember1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMember1ActionPerformed(evt);
            }
        });

        txtMember2.setEditable(false);
        txtMember2.setBackground(new java.awt.Color(0, 102, 204));
        txtMember2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMember2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMember2.setText(": แต้มสะสม 0 คะแนน");
        txtMember2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelMemberLayout = new javax.swing.GroupLayout(jPanelMember);
        jPanelMember.setLayout(jPanelMemberLayout);
        jPanelMemberLayout.setHorizontalGroup(
            jPanelMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMember1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMember2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelMemberLayout.setVerticalGroup(
            jPanelMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMember1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMember2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("ใบเสร็จ");

        lbArName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        panelNumber.setMaximumSize(new java.awt.Dimension(420, 255));
        panelNumber.setRequestFocusEnabled(false);
        panelNumber.setVerifyInputWhenFocusTarget(false);
        panelNumber.setLayout(new java.awt.GridLayout(5, 5));

        btn1000.setBackground(new java.awt.Color(204, 204, 204));
        btn1000.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn1000.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1000.jpg"))); // NOI18N
        btn1000.setAlignmentX(0.5F);
        btn1000.setAutoscrolls(true);
        btn1000.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn1000.setDoubleBuffered(true);
        btn1000.setFocusable(false);
        btn1000.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1000.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn1000.setRequestFocusEnabled(false);
        btn1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1000ActionPerformed(evt);
            }
        });
        panelNumber.add(btn1000);

        btn500.setBackground(new java.awt.Color(204, 204, 255));
        btn500.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn500.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/500.jpg"))); // NOI18N
        btn500.setAlignmentX(0.5F);
        btn500.setAutoscrolls(true);
        btn500.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn500.setDoubleBuffered(true);
        btn500.setFocusable(false);
        btn500.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn500.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn500.setRequestFocusEnabled(false);
        btn500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn500ActionPerformed(evt);
            }
        });
        panelNumber.add(btn500);

        btn100.setBackground(new java.awt.Color(255, 204, 204));
        btn100.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/100.png"))); // NOI18N
        btn100.setAlignmentX(0.5F);
        btn100.setAutoscrolls(true);
        btn100.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn100.setDoubleBuffered(true);
        btn100.setFocusable(false);
        btn100.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn100.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn100.setRequestFocusEnabled(false);
        btn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn100ActionPerformed(evt);
            }
        });
        panelNumber.add(btn100);

        btn50.setBackground(new java.awt.Color(255, 204, 204));
        btn50.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/50.jpg"))); // NOI18N
        btn50.setAlignmentX(0.5F);
        btn50.setAutoscrolls(true);
        btn50.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn50.setDoubleBuffered(true);
        btn50.setFocusable(false);
        btn50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn50.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn50.setRequestFocusEnabled(false);
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });
        panelNumber.add(btn50);

        btn7.setBackground(new java.awt.Color(255, 153, 0));
        btn7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn7.setText("7");
        btn7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn7.setFocusable(false);
        btn7.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn7.setRequestFocusEnabled(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        panelNumber.add(btn7);

        btn8.setBackground(new java.awt.Color(255, 153, 0));
        btn8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn8.setText("8");
        btn8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn8.setFocusable(false);
        btn8.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn8.setRequestFocusEnabled(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        panelNumber.add(btn8);

        btn9.setBackground(new java.awt.Color(255, 153, 0));
        btn9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn9.setText("9");
        btn9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn9.setFocusable(false);
        btn9.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn9.setRequestFocusEnabled(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        panelNumber.add(btn9);

        btn20.setBackground(new java.awt.Color(0, 102, 204));
        btn20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn20.setForeground(new java.awt.Color(255, 255, 255));
        btn20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/20.png"))); // NOI18N
        btn20.setAlignmentX(0.5F);
        btn20.setAutoscrolls(true);
        btn20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn20.setDoubleBuffered(true);
        btn20.setFocusable(false);
        btn20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn20.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn20.setRequestFocusEnabled(false);
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });
        panelNumber.add(btn20);

        btn4.setBackground(new java.awt.Color(255, 153, 0));
        btn4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn4.setText("4");
        btn4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn4.setFocusable(false);
        btn4.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn4.setRequestFocusEnabled(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        panelNumber.add(btn4);

        btn5.setBackground(new java.awt.Color(255, 153, 0));
        btn5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn5.setText("5");
        btn5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn5.setFocusable(false);
        btn5.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn5.setRequestFocusEnabled(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        panelNumber.add(btn5);

        btn6.setBackground(new java.awt.Color(255, 153, 0));
        btn6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn6.setText("6");
        btn6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn6.setFocusable(false);
        btn6.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn6.setRequestFocusEnabled(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        panelNumber.add(btn6);

        btnCLR.setBackground(new java.awt.Color(0, 102, 204));
        btnCLR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCLR.setForeground(new java.awt.Color(51, 51, 0));
        btnCLR.setText("CLEAR");
        btnCLR.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCLR.setFocusable(false);
        btnCLR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCLR.setRequestFocusEnabled(false);
        btnCLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCLRActionPerformed(evt);
            }
        });
        panelNumber.add(btnCLR);

        btn1.setBackground(new java.awt.Color(255, 153, 0));
        btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn1.setText("1");
        btn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn1.setFocusable(false);
        btn1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn1.setRequestFocusEnabled(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        panelNumber.add(btn1);

        btn2.setBackground(new java.awt.Color(255, 153, 0));
        btn2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn2.setText("2");
        btn2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn2.setFocusable(false);
        btn2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn2.setRequestFocusEnabled(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        panelNumber.add(btn2);

        btn3.setBackground(new java.awt.Color(255, 153, 0));
        btn3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn3.setText("3");
        btn3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn3.setFocusable(false);
        btn3.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn3.setRequestFocusEnabled(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        panelNumber.add(btn3);

        btnExit.setBackground(new java.awt.Color(204, 0, 51));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("(ESC) ออก");
        btnExit.setFocusable(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setRequestFocusEnabled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        panelNumber.add(btnExit);

        btn0.setBackground(new java.awt.Color(255, 153, 0));
        btn0.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn0.setText("0");
        btn0.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn0.setFocusable(false);
        btn0.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn0.setRequestFocusEnabled(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        panelNumber.add(btn0);

        btnDot.setBackground(new java.awt.Color(255, 153, 0));
        btnDot.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnDot.setText(".");
        btnDot.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDot.setFocusable(false);
        btnDot.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnDot.setRequestFocusEnabled(false);
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        panelNumber.add(btnDot);

        btnAR.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAR.setText("00");
        btnAR.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAR.setFocusable(false);
        btnAR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAR.setRequestFocusEnabled(false);
        btnAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnARActionPerformed(evt);
            }
        });
        panelNumber.add(btnAR);

        btnAccept.setBackground(new java.awt.Color(0, 153, 0));
        btnAccept.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAccept.setForeground(new java.awt.Color(51, 51, 0));
        btnAccept.setText("Enter");
        btnAccept.setAlignmentX(0.5F);
        btnAccept.setAutoscrolls(true);
        btnAccept.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAccept.setDoubleBuffered(true);
        btnAccept.setFocusable(false);
        btnAccept.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAccept.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAccept.setRequestFocusEnabled(false);
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });
        btnAccept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAcceptKeyPressed(evt);
            }
        });
        panelNumber.add(btnAccept);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGiftVoucher.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGiftVoucher.setText("บัตรกำนัล/บัตรของขวัญ");
        btnGiftVoucher.setFocusable(false);
        btnGiftVoucher.setRequestFocusEnabled(false);
        btnGiftVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiftVoucherActionPerformed(evt);
            }
        });

        txtGiftVoucherAmount.setEditable(false);
        txtGiftVoucherAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtGiftVoucherAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtGiftVoucherAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGiftVoucherAmount.setText("0.00");
        txtGiftVoucherAmount.setFocusable(false);

        txtEntertainAmount.setEditable(false);
        txtEntertainAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtEntertainAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtEntertainAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEntertainAmount.setText("0.00");
        txtEntertainAmount.setFocusable(false);
        txtEntertainAmount.setMaximumSize(new java.awt.Dimension(6, 300));
        txtEntertainAmount.setMinimumSize(new java.awt.Dimension(6, 300));
        txtEntertainAmount.setPreferredSize(new java.awt.Dimension(6, 300));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Entertain");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCredit1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCredit1.setText("ลูกหนี้ (AR-Payment)");
        btnCredit1.setFocusable(false);
        btnCredit1.setRequestFocusEnabled(false);
        btnCredit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCredit1ActionPerformed(evt);
            }
        });

        txtArCode.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtArCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArCode.setFocusable(false);
        txtArCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtArCodeKeyPressed(evt);
            }
        });

        txtArAmount.setEditable(false);
        txtArAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtArAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtArAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArAmount.setText("0.00");
        txtArAmount.setFocusable(false);
        txtArAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtArAmountKeyPressed(evt);
            }
        });

        bntEarnest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntEarnest.setText("หักคืนเงินมัดจำ");
        bntEarnest.setFocusable(false);
        bntEarnest.setRequestFocusEnabled(false);
        bntEarnest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEarnestActionPerformed(evt);
            }
        });

        txtReturnMoneyAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtReturnMoneyAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtReturnMoneyAmount.setText("0.00");
        txtReturnMoneyAmount.setFocusable(false);
        txtReturnMoneyAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReturnMoneyAmountKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnGiftVoucher, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCredit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtEntertainAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGiftVoucherAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(txtArCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtArAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addComponent(txtReturnMoneyAmount))
                    .addComponent(bntEarnest))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEntertainAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(bntEarnest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGiftVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGiftVoucherAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtReturnMoneyAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntPrintCheckBill1.setBackground(new java.awt.Color(204, 51, 0));
        bntPrintCheckBill1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntPrintCheckBill1.setForeground(new java.awt.Color(255, 255, 255));
        bntPrintCheckBill1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        bntPrintCheckBill1.setText("พิมพ์ตรวจสอบ TH");
        bntPrintCheckBill1.setFocusable(false);
        bntPrintCheckBill1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrintCheckBill1.setRequestFocusEnabled(false);
        bntPrintCheckBill1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntPrintCheckBill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintCheckBill1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ค้างชำระ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("เครดิต");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("วงเงิน");

        lbCredit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCredit.setForeground(new java.awt.Color(255, 255, 255));
        lbCredit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCredit.setText("0");

        lbCreditMoney.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCreditMoney.setForeground(new java.awt.Color(255, 255, 255));
        lbCreditMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCreditMoney.setText("0.00");

        lbCreditAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCreditAmt.setForeground(new java.awt.Color(255, 255, 255));
        lbCreditAmt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCreditAmt.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(lbCreditMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCreditAmt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(lbCredit)
                    .addComponent(lbCreditMoney)
                    .addComponent(lbCreditAmt))
                .addContainerGap())
        );

        bntPrintCheckBill2.setBackground(new java.awt.Color(255, 51, 51));
        bntPrintCheckBill2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntPrintCheckBill2.setForeground(new java.awt.Color(255, 255, 255));
        bntPrintCheckBill2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        bntPrintCheckBill2.setText("พิมพ์ตรวจสอบ EN");
        bntPrintCheckBill2.setFocusable(false);
        bntPrintCheckBill2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrintCheckBill2.setRequestFocusEnabled(false);
        bntPrintCheckBill2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntPrintCheckBill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintCheckBill2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lbArName, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBillNo))
                            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(bntPrintCheckBill1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bntPrintCheckBill2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(btnDiscountAll)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDiscountAmount))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntPrintCheckBill, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntPrintCheckBill1)
                            .addComponent(bntPrintCheckBill2))
                        .addGap(117, 117, 117)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(bntPrintCheckBill, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanelDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbArName, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDiscountAll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDiscountAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountAllActionPerformed
        backupTempBalnace();
        double totalAmount = Double.parseDouble(txtTotalAmount.getText().replace(",", ""));
        DiscountDialog dd = new DiscountDialog(null, true, tableNo, totalAmount, memberBean,
                txtMember1.getText(), txtMember2.getText());
        dd.setVisible(true);
        if (dd.getDiscountBean() != null) {
            discBean = dd.getDiscountBean();
            txtDiscountAmount.setText("" + discBean.getTotalDiscount());
            loadTableBill();
        } else {
            restoreTempBalance();
        }
        LoadDisc();
    }//GEN-LAST:event_btnDiscountAllActionPerformed

    private void bntPrintCheckBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintCheckBillActionPerformed
        kichenPrintAfterPrintCheck();
        printBillCheck();

    }//GEN-LAST:event_bntPrintCheckBillActionPerformed

    private void txtCashAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_txtCashAmountKeyPressed

    private void txtArCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtArCode.getText().equals("")) {
                arCodeExits();
            }
        }
    }//GEN-LAST:event_txtArCodeKeyPressed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        clearTempGift();
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtCreditNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtCreditNo.getText().equals("")) {
                if (txtCreditNo.getText().length() > 16) {
                    JOptionPane.showMessageDialog(this, "Over Credit Number:");
                    txtCreditNo.requestFocus();
                    txtCreditNo.selectAll();
                } else {
                    txtCreditNo.setEditable(false);
                    //txtCreditTrackNo.setFocusable(true);
                    txtCreditTrackNo.requestFocus();//btnAccept
                }
            } else {
                GetEDC();
            }
        }
    }//GEN-LAST:event_txtCreditNoKeyPressed

    private void txtCreditTrackNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditTrackNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtCreditTrackNo.getText().equals("")) {
                if (txtCreditTrackNo.getText().length() > 6) {
                    JOptionPane.showMessageDialog(this, "Over Appr Code:");
                    txtCreditTrackNo.selectAll();
                    txtCreditTrackNo.requestFocus();
                } else {
                    trackNoExist();
                }
            }
        }
    }//GEN-LAST:event_txtCreditTrackNoKeyPressed

    private void txtCreditAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_txtCreditAmountKeyPressed

    private void txtReturnMoneyAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReturnMoneyAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SumReturnMoney(txtReturnMoneyAmount.getText().replace(",", ""), txtTotalAmount.getText().replace(",", ""));
        }
    }//GEN-LAST:event_txtReturnMoneyAmountKeyPressed

    private void txtArAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_txtArAmountKeyPressed

    private void btn1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1000ActionPerformed
        input("1000");
    }//GEN-LAST:event_btn1000ActionPerformed

    private void btn500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn500ActionPerformed
        input("500");
    }//GEN-LAST:event_btn500ActionPerformed

    private void btn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100ActionPerformed
        input("100");
    }//GEN-LAST:event_btn100ActionPerformed

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        input("50");
    }//GEN-LAST:event_btn50ActionPerformed

    private void btnCLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCLRActionPerformed
        backspaceText();
    }//GEN-LAST:event_btnCLRActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        input("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        input("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        input("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        input("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        input("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        input("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        input("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        input("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        input("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        input("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        input(".");
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnARActionPerformed
        input("00");
    }//GEN-LAST:event_btnARActionPerformed

    private void btnAcceptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAcceptKeyPressed

//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            btnAcceptActionPerformed(null);
//        }

    }//GEN-LAST:event_btnAcceptKeyPressed

    private void btnCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditActionPerformed
        txtCreditName.setText("");
        txtCreditNo.setText("");
        txtCreditTrackNo.setText("");
        txtCreditAmount.setText("0.00");

        double cash = 0.00;
        double amount = 0.00;
        try {
            if (txtCashAmount.getText().trim().equals("")) {
                txtCashAmount.setText("0.00");
            }
            cash = Double.parseDouble(txtCashAmount.getText().replace(",", ""));
            amount = Double.parseDouble(txtTotalAmount.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        if (cash == amount || cash > amount) {
            txtCashAmount.requestFocus();
        } else {
            checkBillOK();
            FindCredit findCredit = new FindCredit(this, true);
            findCredit.setVisible(true);
            if (findCredit.getCreditCode() != null) {
                if (!findCredit.getCreditCode().equals("")) {
                    txtCreditName.setText(findCredit.getCreditCode());
                    CreditCharge = findCredit.getCreditCharge();
                    if (CreditCharge != 0.00) {
//                    if (!CreditCharge.replace(",", "").equals("0.00")) {
                        amount = Double.parseDouble(txtTotalAmount.getText().replace(",", ""));
                        TCreditCharge = (amount * (CreditCharge) / 100);
                        amount = amount + (amount * (CreditCharge) / 100);
                        if (!CONFIG.getP_PayBahtRound().equals("O")) {
                            txtTotalAmount.setText(dec.format(NumberControl.UP_DOWN_NATURAL_BAHT(amount)));
                        } else {
                            txtTotalAmount.setText(dec.format((amount)));
                        }
//                        txtTotalAmount.setText(dec.format(NumberControl.UP_DOWN_NATURAL_BAHT(amount)));
                    }
                    txtCreditNo.setText("");
                    txtCreditTrackNo.setText("");
                    txtCreditNo.setEditable(true);
                    txtCreditNo.setFocusable(true);
                    txtCreditNo.requestFocus();
                    txtCashAmount.setEditable(false);
                }
            }
        }
    }//GEN-LAST:event_btnCreditActionPerformed

    private void btnCredit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCredit1ActionPerformed
        txtArCode.setFocusable(true);
        txtArCode.requestFocus();
    }//GEN-LAST:event_btnCredit1ActionPerformed

    private void bntCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCashActionPerformed
        txtCashAmount.setEditable(true);
        txtCashAmount.setFocusable(true);
        txtCashAmount.selectAll();
        txtCashAmount.requestFocus();
    }//GEN-LAST:event_bntCashActionPerformed

    private void btnGiftVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiftVoucherActionPerformed
        GiftVoucherDialog giftDialog = new GiftVoucherDialog(null, true, txtGiftVoucherAmount.getText(), txtTotalAmount.getText());
        giftDialog.setVisible(true);
        if (giftDialog.getTotalAmount() > 0) {
            txtGiftVoucherAmount.setText("" + giftDialog.getTotalAmount());
            txtGiftVoucherAmount.selectAll();

            double a = Double.parseDouble(txtTotalAmount.getText().replace(",", ""));
            double b = Double.parseDouble(txtGiftVoucherAmount.getText().replace(",", ""));
            double c = a - b;
            if (c < 0) {
                c = 0;
            }
//            txtTotalAmount.setText(NumberFormat.showDouble2(a - b));
            txtTotalAmount.setText(NumberFormat.showDouble2(c));
            txtCashAmount.requestFocus();
        } else {
            txtGiftVoucherAmount.setText("0.00");
            txtCashAmount.requestFocus();
        }
    }//GEN-LAST:event_btnGiftVoucherActionPerformed

    private void bntEarnestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEarnestActionPerformed
        txtReturnMoneyAmount.setFocusable(true);
        txtReturnMoneyAmount.selectAll();
        txtReturnMoneyAmount.requestFocus();
    }//GEN-LAST:event_bntEarnestActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        LoadDisc();
    }//GEN-LAST:event_formWindowOpened

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        input("20");
    }//GEN-LAST:event_btn20ActionPerformed

    private void txtCreditAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditAmountFocusGained
        if (checkStatusCreditData()) {
            trackNoExist();
        }
    }//GEN-LAST:event_txtCreditAmountFocusGained

    private void txtCreditTrackNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCreditTrackNoMouseClicked
        if (txtCreditNo.getText().length() > 16) {
            JOptionPane.showMessageDialog(this, "Over Credit Number:");
            txtCreditNo.setEnabled(true);
            txtCreditNo.requestFocus();
            txtCreditNo.selectAll();
        } else {
            txtCreditNo.setEditable(false);
        }
        if (txtCreditNo.getText().equals("")) {
            txtCreditNo.setEditable(false);
        }
    }//GEN-LAST:event_txtCreditTrackNoMouseClicked

    private void txtCreditAmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCreditAmountMouseClicked
        if (!txtCreditNo.getText().equals("") || !txtCreditTrackNo.getText().equals("")) {
            if (txtCreditTrackNo.getText().length() > 6) {
                JOptionPane.showMessageDialog(this, "Over Appr Code:");
                txtCreditTrackNo.requestFocus();
                txtCreditTrackNo.selectAll();
            }
        }
    }//GEN-LAST:event_txtCreditAmountMouseClicked

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed

        if (!txtCreditName.getText().equals("") && txtCreditNo.getText().equals("") && txtCreditTrackNo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Credit Cannot Be Blank:");
        } else if (!txtCreditName.getText().equals("") && !txtCreditNo.getText().equals("") && txtCreditTrackNo.getText().equals("")) {
            txtCreditTrackNo.requestFocus();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String sql = "select * from balance where r_table='" + tableNo + "' and r_type='1'";
                    try {
                        MySQLConnect c = new MySQLConnect();
                        c.open();
                        final ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
                        boolean isTakeOrder = isTakeOrder();
                        if (rs.next() && isTakeOrder == true) {
                            JOptionPane.showMessageDialog(null, "Food can't pay this Computer:\n เครื่องนี้ไม่สามารถชำระเงินค่าอาหารได้");
                        } else {
                            checkBillOK();
                        }
                    } catch (Exception e) {
                        MSG.ERR(e.toString());
                    }
//                    checkBillOK();
                }
            }).start();
        }
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void txtCreditTrackNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditTrackNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditTrackNoActionPerformed

    private void txtCreditNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditNoFocusGained
        if (txtCreditName.getText().equals("")) {
            txtCreditAmount.setText("0.00");

            btnCreditActionPerformed(null);
        }
    }//GEN-LAST:event_txtCreditNoFocusGained

    private void txtCreditTrackNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditTrackNoFocusGained
        if (txtCreditNo.getText().equals("")) {
            txtCreditAmount.setText("0.00");
            txtCreditNo.requestFocus();
        }
    }//GEN-LAST:event_txtCreditTrackNoFocusGained

    private void txtCreditAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditAmountActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtEntertainAmount.setText(txtTotalAmount.getText());
        double b = Double.parseDouble(txtEntertainAmount.getText().replace(",", ""));
        double a = Double.parseDouble(txtTotalAmount.getText().replace(",", ""));
        txtTotalAmount.setText(NumberFormat.showDouble2(a - b));
        txtCashAmount.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMember1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMember1ActionPerformed
//        MemberDialog MBD = new MemberDialog(this, true);
//        MBD.setVisible(true);

    }//GEN-LAST:event_txtMember1ActionPerformed

    private void txtMember1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMember1MouseClicked
        MemberDialog MBD = new MemberDialog(this, true, tableNo);
        MBD.setVisible(true);
        try {
            this.memberBean = MemberBean.getMember(MBD.getMemCode());
            updateProSerTable(tableNo, memberBean);
            if (memberBean != null) {
                txtMember1.setText(memberBean.getMember_NameThai());
                txtMember2.setText("แต้มสะสม : " + memberBean.getMember_TotalScore() + " แต้ม");
            } else {
                txtMember1.setText("เลือกสมาชิก");
                txtMember2.setText("แต้มสะสม : " + "0.00" + " แต้ม");
            }
        } catch (Exception e) {
            MSG.ERR(e.toString());
        }
        loadTableBill();
        LoadDisc();
    }//GEN-LAST:event_txtMember1MouseClicked

    private void bntPrintCheckBill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintCheckBill1ActionPerformed
        PublicVar.languagePrint = "TH";
        kichenPrintAfterPrintCheck();
        printBillCheck();
//        ClearApp();
    }//GEN-LAST:event_bntPrintCheckBill1ActionPerformed

    private void bntPrintCheckBill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintCheckBill2ActionPerformed
        PublicVar.languagePrint = "EN";
        kichenPrintAfterPrintCheck();
        printBillCheck();
    }//GEN-LAST:event_bntPrintCheckBill2ActionPerformed

    boolean _SubTotalOK = false;

    public void input(String Str) {
        if (txtCashAmount.hasFocus()) {
            String temp = txtCashAmount.getText().replace(",", "");
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50") || data.equals("20")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }

                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtCashAmount.setText("" + totalD);
            } else {
                if (temp.equals("0.00")) {
                    temp = "";
                }
                txtCashAmount.setText(temp + Str);
            }
        } else if (txtReturnMoneyAmount.hasFocus()) {
            String temp = txtReturnMoneyAmount.getText().trim();
            if (temp.equals("0.00")) {
                temp = "";
            }
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50") || data.equals("20")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }
                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtReturnMoneyAmount.setText("" + totalD);
            } else {
                txtReturnMoneyAmount.setText(temp + Str);
            }
        } else if (txtGiftVoucherAmount.hasFocus()) {
            String temp = txtGiftVoucherAmount.getText().trim();
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50") || data.equals("20")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }
                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtGiftVoucherAmount.setText("" + totalD);
            } else {
                txtGiftVoucherAmount.setText(temp + Str);
            }
        } else if (txtCreditAmount.hasFocus()) {
            String temp = txtCreditAmount.getText().trim();
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50") || data.equals("20")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }
                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtCreditAmount.setText("" + totalD);
            } else {
                txtCreditAmount.setText(temp + Str);
            }
        } else if (txtCreditNo.hasFocus()) {
            String temp = txtCreditNo.getText().trim();
            txtCreditNo.setText(temp + Str);
        } else if (txtCreditTrackNo.hasFocus()) {
            String temp = txtCreditTrackNo.getText().trim();
            txtCreditTrackNo.setText(temp + Str);
        }
    }

    public static void main(String args[]) {
        new MySQLConnect();
//        new MySQLConnect();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                CheckBill dialog = new CheckBill(new javax.swing.JFrame(), true, "1", null);
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Digital_Msg;
    private javax.swing.JButton bntCash;
    private javax.swing.JButton bntEarnest;
    private javax.swing.JButton bntPrintCheckBill;
    private javax.swing.JButton bntPrintCheckBill1;
    private javax.swing.JButton bntPrintCheckBill2;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn100;
    private javax.swing.JButton btn1000;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn500;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAR;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnCLR;
    private javax.swing.JButton btnCredit;
    private javax.swing.JButton btnCredit1;
    private javax.swing.JButton btnDiscountAll;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnGiftVoucher;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelDiscount;
    private javax.swing.JPanel jPanelMember;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAddMoney;
    private javax.swing.JLabel lbArName;
    private javax.swing.JLabel lbCredit;
    private javax.swing.JLabel lbCreditAmt;
    private javax.swing.JLabel lbCreditMoney;
    private javax.swing.JPanel panelNumber;
    private javax.swing.JTable tblShowBalance;
    private javax.swing.JTextField txtArAmount;
    private javax.swing.JTextField txtArCode;
    private javax.swing.JTextField txtBillNo;
    private javax.swing.JTextField txtCashAmount;
    private javax.swing.JTextField txtCreditAmount;
    private javax.swing.JTextField txtCreditName;
    private javax.swing.JTextField txtCreditNo;
    private javax.swing.JTextField txtCreditTrackNo;
    private javax.swing.JTextField txtDiscountAmount;
    private javax.swing.JTextField txtEntertainAmount;
    private javax.swing.JTextField txtGiftVoucherAmount;
    private javax.swing.JTextField txtItemDisc;
    private javax.swing.JTextField txtMember1;
    private javax.swing.JTextField txtMember2;
    private javax.swing.JTextField txtPromotion;
    private javax.swing.JTextField txtReturnMoneyAmount;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JLabel txtTotalAmount;
    private javax.swing.JLabel txtTotalCash;
    private javax.swing.JTextField txtTotalService;
    // End of variables declaration//GEN-END:variables

    private void initTable() {
        tblShowBalance.setShowGrid(true);
        tblShowBalance.setGridColor(Color.gray);
        tblShowBalance.setRowHeight(35);

        JTableControl.alignColumn(tblShowBalance, 2, "right");
        JTableControl.alignColumn(tblShowBalance, 3, "right");
        JTableControl.alignColumn(tblShowBalance, 4, "right");

        JTableHeader header = tblShowBalance.getTableHeader();
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        model = (DefaultTableModel) tblShowBalance.getModel();
        lbArName.setText("");
        lbCredit.setText("");
        lbCreditMoney.setText("");
        lbCreditAmt.setText("");
        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();
        loadTableBill();
    }

    private void loadTableBill() {
        TableFileControl tfCon = new TableFileControl();
        tBean = tfCon.getData(tableNo);
        double totalDiscount = 0.00;
        totalDiscount = tBean.getProDiscAmt() + tBean.getSpaDiscAmt() + tBean.getCuponDiscAmt()
                + tBean.getFastDiscAmt() + tBean.getEmpDiscAmt() + tBean.getTrainDiscAmt()
                + tBean.getSubDiscAmt() + tBean.getDiscBath() + tBean.getItemDiscAmt();

//        txtTotalCash.setText(dec.format(tBean.getTAmount() - totalDiscount));
        txtTotalCash.setText(dec.format(tBean.getTAmount() - totalDiscount));
        txtBillNo.setText(BillControl.getBillIDCurrent());

        PosControl posControl = new PosControl();
        POSConfigSetup config = posControl.getData();
        BalanceControl bc = new BalanceControl();
        double NetTotalUpDown = 0;
        if (!config.getP_PayBahtRound().equals("O")) {
            NetTotalUpDown = NumberControl.UP_DOWN_NATURAL_BAHT(tBean.getNetTotal());
        } else {
            NetTotalUpDown = tBean.getNetTotal();
        }
        if (config.getP_VatType().equals("I")) {
            if (!CONFIG.getP_PayBahtRound().equals("O")) {
                txtTotalAmount.setText(dec.format(NumberControl.UP_DOWN_NATURAL_BAHT(tBean.getNetTotal())));
            } else {
                txtTotalAmount.setText(dec.format((tBean.getNetTotal())));
            }

//            txtTotalAmount.setText(dec.format(NumberControl.UP_DOWN_NATURAL_BAHT(NetTotalUpDown - totalDiscount)));
        }
        if (config.getP_VatType().equals("E")) {
            NetTotalUpDown = tBean.getTAmount() - totalDiscount + tBean.getServiceAmt();
            double vat = 0;
            vat = (NetTotalUpDown * 7 / 100);
            NetTotalUpDown = NetTotalUpDown + vat;
//            txtTotalAmount.setText((dec.format((NetTotalUpDown))));
            txtTotalAmount.setText((dec.format(NumberControl.UP_DOWN_NATURAL_BAHT(NetTotalUpDown))));
        }

        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }

        //ถ้า posconfig PrintSum=Y พิมพืใบเสร็จเป็นยอดรวม
        if (config.getP_PrintSum().equals("Y")) {
            ArrayList<BalanceBean> listBean = bc.getAllBalanceSum(tableNo);
            for (int i = 0; i < listBean.size(); i++) {
                BalanceBean bean = (BalanceBean) listBean.get(i);
                if (!bean.getR_Void().equals("V")) {
                    model.addRow(new Object[]{
                        bean.getR_PluCode(), bean.getR_PName(),
                        dec.format(bean.getR_Quan()), dec.format(bean.getR_Price()), dec.format(bean.getR_Total())
                    });
                }
            }
            if (!tBean.getMemCode().equals("")) {
                txtMember1.setText(memberBean.getMember_NameThai());
                txtMember2.setText(intFM.format(memberBean.getMember_TotalScore()));
            } else {
                txtMember1.setText("เลือกสมาชิก");
                txtMember2.setText("0.00");
            }
            txtItemDisc.setText("" + dec.format(tBean.getItemDiscAmt()));
            txtPromotion.setText("" + dec.format(tBean.getProDiscAmt()));
            txtSubTotal.setText("" + dec.format(tBean.getSubDiscAmt()));
            txtTotalService.setText("" + dec.format(tBean.getServiceAmt()));
            txtSubTotal.setText(dec.format(totalDiscount));
            bntCashActionPerformed(null);
//            if (!memberBean.getMember_Code().equals("")) {
//                txtMember1.setText(memberBean.getMember_NameThai());
//                txtMember2.setText(intFM.format(memberBean.getMember_TotalScore()));
//            }
//        } else {
//            ArrayList<BalanceBean> listBean = bc.getAllBalance(tableNo);
//            for (int i = 0; i < listBean.size(); i++) {
//                BalanceBean bean = (BalanceBean) listBean.get(i);
//                if (!bean.getR_Void().equals("V")) {
//                    model.addRow(new Object[]{
//                        bean.getR_PluCode(), bean.getR_PName(),
//                        dec.format(bean.getR_Quan()), dec.format(bean.getR_Price()), dec.format(bean.getR_Total())
//                    });
//                }
//            }
//
//            txtItemDisc.setText("" + dec.format(tBean.getItemDiscAmt()));
//            txtPromotion.setText("" + dec.format(tBean.getProDiscAmt()));
//            txtSubTotal.setText("" + dec.format(tBean.getSubDiscAmt()));
//            txtTotalService.setText("" + dec.format(tBean.getServiceAmt()));
//
//            bntCashActionPerformed(null);
        }

    }

    private void backspaceText() {
        if (txtReturnMoneyAmount.hasFocus()) {
            txtReturnMoneyAmount.setText("0.00");
            txtReturnMoneyAmount.selectAll();
        } else if (txtCashAmount.hasFocus()) {
            txtCashAmount.setText("0.00");
            txtCashAmount.selectAll();
        } else if (txtCreditNo.hasFocus()) {
            txtCreditNo.setText("");
        } else if (txtCreditTrackNo.hasFocus()) {
            txtCreditTrackNo.setText("");
        } else if (txtCreditAmount.hasFocus()) {
            txtCreditAmount.setText("0.00");
        } else if (txtGiftVoucherAmount.hasFocus()) {
            txtGiftVoucherAmount.setText("0.00");
        }
    }

    private void checkBillPayment() {
        DecimalFormat DecFmt = new DecimalFormat("##0.00");
        double netTotal;
        double tmpNetTotal;
        //temp
        double totalAmount = tBean.getTAmount();

        double totalDiscount = Double.parseDouble(txtDiscountAmount.getText().replace(",", ""));
        double totalItemDisc = Double.parseDouble(txtItemDisc.getText().replace(",", ""));
        double totalService;

        PosControl posControl = new PosControl();
        POSConfigSetup config = posControl.getData();

//        if (config.getP_ServiceType().equals("N")) {
//        totalService = getServiceAllbalance(tBean.getTcode()) * (config.getP_Service() / 100);
//            totalService = (totalAmount) * (config.getP_Service() / 100);
//        } else if (config.getP_ServiceType().equals("G")) {
//            totalService = getServiceAllbalance(tBean.getTcode()) * (config.getP_Service() / 100);
//            totalService = totalAmount - totalDiscount * (config.getP_Service() / 100);
//        } else {
//            totalService = 0;
//        }
//        totalService = NumberControl.UP_DOWN_NATURAL_BAHT(totalService);
        netTotal = Double.parseDouble(DecFmt.format(tBean.getNetTotal()));

        if (config.getP_VatType().equals("I")) {
//            netTotal = Double.parseDouble(DecFmt.format(tBean.getNetTotal() - totalDiscount));
            netTotal = Double.parseDouble(DecFmt.format(tBean.getNetTotal()));
        }
        if (config.getP_VatType().equals("E")) {
//            netTotal = ((tBean.getTAmount() - tBean.getDiscBath() + tBean.getServiceAmt()));
            netTotal = ((tBean.getTAmount() - totalDiscount + tBean.getServiceAmt()));
            netTotal = netTotal + (netTotal * 7 / 100);
        }
        tmpNetTotal = Double.parseDouble(DecFmt.format(tBean.getNetTotal()));
        if (!config.getP_PayBahtRound().equals("O")) {
            netTotal = NumberControl.UP_DOWN_NATURAL_BAHT(netTotal);
        } else {
            netTotal = (netTotal);
        }

        double saveEntertain = 0.00;
        double returnMoney = Double.parseDouble(txtReturnMoneyAmount.getText().replace(",", ""));
        if (returnMoney > netTotal) {
            JOptionPane.showMessageDialog(this, "**** ยอดการหักคืนเงินมัดจำ (Earnest-Return) มากกว่ายอดที่ต้องจ่ายจริง ****");
            txtReturnMoneyAmount.selectAll();
            return;
        }
        double returnGift = Double.parseDouble(txtGiftVoucherAmount.getText().replace(",", ""));
        double returnCash = Double.parseDouble(txtCashAmount.getText().replace(",", ""));
        double saveAR = Double.parseDouble(txtArAmount.getText().replace(",", ""));
        double saveCredit = Double.parseDouble(txtCreditAmount.getText().replace(",", ""));
        saveEntertain = Double.parseDouble(txtEntertainAmount.getText().replace(",", ""));
        PublicVar.b_entertain = saveEntertain;
        double Entertain = PublicVar.b_entertain;
        if (saveAR > 0 && saveCredit > 0) {
            PublicVar.SubTotalOK = true;
        } else {
            double totalPayment = returnMoney + returnGift + returnCash;
            CuponBean cuponBean = discBean.getCuponBean(); //22/07/2014
            if (totalPayment >= netTotal) {
                Digital_Msg.setText("เงินทอน");
                double ton = 0.00;

                if (!CONFIG.getP_PayBahtRound().equals("O")) {
                    ton = NumberControl.UP_DOWN_NATURAL_BAHT(netTotal) - totalPayment;
                } else {
                    ton = netTotal - totalPayment;
                }
                if (ton < 0) {
                    ton *= -1;
                }
                txtTotalAmount.setForeground(Color.RED);
                txtTotalAmount.setText(dec.format(ton));

                BillNoBean billBean = new BillNoBean();
                billBean.setB_PayAmt(totalPayment);
                billBean.setB_Ton(ton);
                // for AR
                billBean.setB_AccrCode(txtArCode.getText());
                billBean.setB_AccrAmt(saveAR);
                int creditDay;
                try {
                    creditDay = Integer.parseInt(lbCredit.getText());
                } catch (NumberFormatException e) {
                    creditDay = 0;
                }
                billBean.setB_SubDiscBath(tBean.getDiscBath());
                billBean.setB_AccrCr(creditDay);

                // for giftvoucher
                billBean.setB_GiftVoucher(returnGift);

                // for EntertainPayment
                billBean.setB_Entertain1(Entertain);

                // for cash
                if (!CONFIG.getP_PayBahtRound().equals("O")) {
                    double cashPay = Double.parseDouble(txtCashAmount.getText().trim().replace(",", ""));
                    billBean.setB_NetDiff(tmpNetTotal - netTotal);
                } else {
                    billBean.setB_NetDiff(tmpNetTotal - netTotal);
                }
                billBean.setB_Total(netTotal);
                billBean.setB_Cash(returnCash - ton);

//                billBean.setB_ServiceAmt(totalService);
                billBean.setB_ItemDiscAmt(totalItemDisc);
                billBean.setB_NetTotal(netTotal);

                // for credit
                billBean.setB_CrCode1(txtCreditName.getText());
                billBean.setB_CardNo1(txtCreditNo.getText());
                billBean.setB_AppCode1(txtCreditTrackNo.getText());
                billBean.setB_CrAmt1(saveCredit);

                // for earnest
                billBean.setB_Earnest(returnMoney);

                TableFileControl tfCon = new TableFileControl();
                TableFileBean tBean = tfCon.getData(tableNo);

                //get from bean
                billBean.setB_ServiceAmt(tBean.getServiceAmt());
                billBean.setB_CuponDiscAmt(tBean.getCuponDiscAmt());
                billBean.setB_FastDiscAmt(tBean.getFastDiscAmt());
                billBean.setB_FastDisc(tBean.getFastDisc());
                billBean.setB_EmpDiscAmt(tBean.getEmpDiscAmt());
                billBean.setB_EmpDisc(tBean.getEmpDisc());
                billBean.setB_TrainDiscAmt(tBean.getTrainDiscAmt());
                billBean.setB_TrainDisc(tBean.getTrainDisc());
                billBean.setB_MemDiscAmt(tBean.getMemDiscAmt());
                billBean.setB_MemDisc(tBean.getMemDisc());
                billBean.setB_SubDisc(tBean.getSubDisc());
                billBean.setB_SubDiscAmt(tBean.getSubDiscAmt());

                //set for MemberScore
                if (memberBean != null) {
                    if (!memberBean.getMember_Code().equals("")) {
                        billBean.setB_SumScore(memberBean.getMember_TotalScore());
                    }
                }
                BillControl billControl = new BillControl();
                PublicVar.SubTotalOK = true;
                billControl.saveBillNo(tableNo, billBean);
                //clear tempset
                clearTempSet(tableNo);
                lockScreen1(false);
//                lockScreen(this, false);
                UpdateMember("Del");
                txtCashAmount.setEnabled(false);
            } else if (saveCredit == netTotal
                    || saveAR == netTotal
                    || (saveCredit + returnCash) == netTotal
                    || saveEntertain == netTotal
                    || returnGift + returnCash + saveCredit == netTotal) {
                Digital_Msg.setText("เงินทอน");
                double ton = 0;
                txtTotalAmount.setForeground(Color.RED);
                txtTotalAmount.setText(dec.format(ton));
                BillNoBean billBean = new BillNoBean();

                billBean.setB_PayAmt(totalPayment);
                // set ton
                billBean.setB_Ton(ton);

                // for AR
                billBean.setB_AccrCode(txtArCode.getText());
                billBean.setB_AccrAmt(saveAR);
                int creditDay;
                try {
                    creditDay = Integer.parseInt(lbCredit.getText());
                } catch (NumberFormatException e) {
                    creditDay = 0;
                }
                billBean.setB_AccrCr(creditDay);

                // for giftvoucher
                billBean.setB_GiftVoucher(returnGift);

                // for cash                
                billBean.setB_SubDiscBath(tBean.getDiscBath());
                billBean.setB_Total(netTotal);
                billBean.setB_Cash(returnCash);

//                if (config.getP_VatType().equals("I")) {
//                    billBean.setB_ServiceAmt(totalService * 7 / 107);
//                }
//                if (config.getP_VatType().equals("E")) {
//                    billBean.setB_ServiceAmt(totalService * 7 / 100);
//                }
//                billBean.setB_ServiceAmt(totalService);
                billBean.setB_ServiceAmt(tBean.getServiceAmt());
                billBean.setB_ItemDiscAmt(totalItemDisc);
                billBean.setB_NetTotal(tmpNetTotal);
                //btnAccept.setEnabled(false);

                // for earnest
                billBean.setB_Earnest(returnMoney);

                // for EntertainPay
                billBean.setB_Entertain1(saveEntertain);

                // for credit
                billBean.setB_CrCode1(txtCreditName.getText());
                billBean.setB_CardNo1(txtCreditNo.getText());
                billBean.setB_AppCode1(txtCreditTrackNo.getText());
//                btnAccept.setEnabled(false);
                billBean.setB_CrAmt1(saveCredit);

                //get from bean
//                billBean.setB_CuponDiscAmt(discBean.getCuponDiscount());
                billBean.setB_CuponDiscAmt(tBean.getCuponDiscAmt());
                billBean.setB_FastDiscAmt(discBean.getFestDiscount());
                billBean.setB_FastDisc(discBean.getStrFestDiscount());
                billBean.setB_EmpDiscAmt(discBean.getEmpDiscount());
                billBean.setB_EmpDisc(discBean.getStrEmpDiscount());
                billBean.setB_TrainDiscAmt(discBean.getTrainDiscount());
                billBean.setB_TrainDisc(discBean.getStrTrainDiscount());
                billBean.setB_MemDiscAmt(discBean.getMemDiscount());
                billBean.setB_MemDisc(discBean.getStrMemDiscount());
                billBean.setB_SubDisc(discBean.getStrCuponDiscount());
                billBean.setB_SubDiscAmt(discBean.getCuponDiscount());

                BillControl billControl = new BillControl();
//                btnAccept.setVisible(false);
                PublicVar.SubTotalOK = true;
                if (!CONFIG.getP_PayBahtRound().equals("O")) {
                    double cashPay = Double.parseDouble(txtCashAmount.getText().trim().replace(",", ""));
                    billBean.setB_NetDiff(tmpNetTotal - (cashPay + saveCredit + saveEntertain + saveAR + returnGift));
                } else {
                    billBean.setB_NetDiff(tmpNetTotal - netTotal);
                }
                if (memberBean != null) {
                    if (!memberBean.getMember_Code().equals("")) {
                        billBean.setB_SumScore(memberBean.getMember_TotalScore());
                    }
                }
                billControl.saveBillNo(tableNo, billBean);
//                btnAccept.setVisible(true);
                Value.MemberAlready = false;
//                lockScreen(this, false);
                lockScreen1(false);
                UpdateMember("Del");

            } else {
                // กรณีใส่จำนวนเงินไม่ครบ
                double total = netTotal - returnCash - returnGift - saveEntertain;
//                double total = netTotal - returnCash - returnGift - saveEntertain;
//                txtTotalAmount.setText(dec.format(Math.round(total)));
                txtTotalAmount.setText(dec.format((total)));
            }
//
//            btnAccept.setEnabled(true);
//            btnAccept.setFocusable(true);
//            btnAccept.requestFocus();
//            btnAccept.setEnabled(false);
        }
//        lockScreen1(false);
//        btnAccept.setEnabled(false);
    }

//    private void checkBillPayment() {
//        double netTotal;
//        double tmpNetTotal;
//
//        //temp
//        double totalAmount = tBean.getTAmount();
//
//        double totalDiscount = Double.parseDouble(txtDiscountAmount.getText().replace(",", ""));
//        double totalItemDisc = Double.parseDouble(txtItemDisc.getText().replace(",", ""));
//        double totalService;
//        PosControl posControl = new PosControl();
//        POSConfigSetup config = posControl.getData();
//        if (config.getP_ServiceType().equals("N")) {
//            totalService = (totalAmount - totalDiscount) * (config.getP_Service() / 100);
//        } else if (config.getP_ServiceType().equals("G")) {
//            totalService = totalAmount * (config.getP_Service() / 100);
//        } else {
//            totalService = 0;
//        }
//        DecimalFormat DecFmt = new DecimalFormat("##0.00");
//        netTotal = Double.parseDouble(DecFmt.format(tBean.getNetTotal() + TCreditCharge));
//
//        tmpNetTotal = netTotal;
////        netTotal = NumberControl.UP_DOWN_NATURAL_BAHT(netTotal);
//        netTotal = NumberControl.UP_DOWN_NATURAL_BAHT(netTotal);
//        double saveEntertain = 0.00;
//        double returnMoney = Double.parseDouble(txtReturnMoneyAmount.getText().replace(",", ""));
//        if (returnMoney > netTotal) {
//            JOptionPane.showMessageDialog(this, "**** ยอดการหักคืนเงินมัดจำ (Earnest-Return) มากกว่ายอดที่ต้องจ่ายจริง ****");
//            txtReturnMoneyAmount.selectAll();
//            return;
//        }
//        double returnGift = Double.parseDouble(txtGiftVoucherAmount.getText().replace(",", ""));
//        double returnCash = Double.parseDouble(txtCashAmount.getText().replace(",", ""));
//        double saveAR = Double.parseDouble(txtArAmount.getText().replace(",", ""));
//        double saveCredit = NumberControl.UP_DOWN_NATURAL_BAHT(Double.parseDouble(txtCreditAmount.getText().replace(",", "")));
//        saveEntertain = Double.parseDouble(txtEntertainAmount.getText().replace(",", ""));
//        PublicVar.b_entertain = saveEntertain;
//        double Entertain = PublicVar.b_entertain;
//        if (saveAR > 0 && saveCredit > 0) {
//            PublicVar.SubTotalOK = true;
//        } else {
//            double totalPayment = returnMoney + returnGift + returnCash;
//            CuponBean cuponBean = discBean.getCuponBean(); //22/07/2014
//            if (totalPayment >= netTotal) {
//                Digital_Msg.setText("เงินทอน");
//                double ton = netTotal - totalPayment;
//                if (ton < 0) {
//                    ton *= -1;
//                }
//                txtTotalAmount.setForeground(Color.RED);
//                txtTotalAmount.setText(dec.format(ton));
//
//                BillNoBean billBean = new BillNoBean();
//                billBean.setB_PayAmt(totalPayment);
//                billBean.setB_Ton(ton);
//                // for AR
//                billBean.setB_AccrCode(txtArCode.getText());
//                billBean.setB_AccrAmt(saveAR);
//                int creditDay;
//                try {
//                    creditDay = Integer.parseInt(lbCredit.getText());
//                } catch (NumberFormatException e) {
//                    creditDay = 0;
//                }
//                billBean.setB_AccrCr(creditDay);
//
//                // for giftvoucher
//                billBean.setB_GiftVoucher(returnGift);
//
//                // for EntertainPayment
//                billBean.setB_Entertain1(Entertain);
//
//                // for cash
//                billBean.setB_NetDiff(tmpNetTotal - netTotal);
//                billBean.setB_Total(netTotal);
//                billBean.setB_Cash(returnCash - ton);
//
//                billBean.setB_ServiceAmt(totalService);
//                billBean.setB_ItemDiscAmt(totalItemDisc);
//                billBean.setB_NetTotal(tmpNetTotal);
//
//                // for credit
//                billBean.setB_CrCode1(txtCreditName.getText());
//                billBean.setB_CardNo1(txtCreditNo.getText());
//                billBean.setB_AppCode1(txtCreditTrackNo.getText());
//                billBean.setB_CrAmt1(saveCredit);
//                billBean.setB_CrCharge1((CreditCharge));
//
//                // for earnest
//                billBean.setB_Earnest(returnMoney);
//
//                TableFileControl tfCon = new TableFileControl();
//                TableFileBean tBean = tfCon.getData(tableNo);
//
//                //get from bean
//                billBean.setB_CuponDiscAmt(tBean.getCuponDiscAmt());
//                billBean.setB_FastDiscAmt(tBean.getFastDiscAmt());
//                billBean.setB_FastDisc(tBean.getFastDisc());
//                billBean.setB_EmpDiscAmt(tBean.getEmpDiscAmt());
//                billBean.setB_EmpDisc(tBean.getEmpDisc());
//                billBean.setB_TrainDiscAmt(tBean.getTrainDiscAmt());
//                billBean.setB_TrainDisc(tBean.getTrainDisc());
//                billBean.setB_MemDiscAmt(tBean.getMemDiscAmt());
//                billBean.setB_MemDisc(tBean.getMemDisc());
//                billBean.setB_SubDisc(tBean.getSubDisc());
//                billBean.setB_SubDiscAmt(tBean.getSubDiscAmt());
//                billBean.setB_SubDiscBath(0);
//
//                BillControl billControl = new BillControl();
//                PublicVar.SubTotalOK = true;
//                btnAccept.setVisible(false);
//                billControl.saveBillNo(tableNo, billBean);
//                btnAccept.setVisible(true);
//                //clear tempset
//                clearTempSet(tableNo);
//                lockScreen(this, false);
//            } else if (saveCredit == netTotal
//                    || saveAR == netTotal
//                    || (saveCredit + returnCash) == netTotal
//                    || saveEntertain == netTotal
//                    || returnGift + returnCash + saveCredit == netTotal) {
//                Digital_Msg.setText("เงินทอน");
//                double ton = 0;
//                txtTotalAmount.setForeground(Color.RED);
//                txtTotalAmount.setText(dec.format(ton));
//                BillNoBean billBean = new BillNoBean();
//
//                billBean.setB_PayAmt(totalPayment);
//                // set ton
//                billBean.setB_Ton(ton);
//
//                // for AR
//                billBean.setB_AccrCode(txtArCode.getText());
//                billBean.setB_AccrAmt(saveAR);
//                int creditDay;
//                try {
//                    creditDay = Integer.parseInt(lbCredit.getText());
//                } catch (NumberFormatException e) {
//                    creditDay = 0;
//                }
//                billBean.setB_AccrCr(creditDay);
//
//                // for giftvoucher
//                billBean.setB_GiftVoucher(returnGift);
//
//                // for cash                
//                billBean.setB_NetDiff(tmpNetTotal - netTotal);
//                billBean.setB_Total(netTotal);
//                billBean.setB_Cash(returnCash);
//                billBean.setB_ServiceAmt(totalService);
//                billBean.setB_ItemDiscAmt(totalItemDisc);
//                billBean.setB_NetTotal(tmpNetTotal);
//                //btnAccept.setEnabled(false);
//
//                // for earnest
//                billBean.setB_Earnest(returnMoney);
//
//                // for EntertainPay
//                billBean.setB_Entertain1(PublicVar.b_entertain);
//
//                // for credit
//                billBean.setB_CrCode1(txtCreditName.getText());
//                billBean.setB_CardNo1(txtCreditNo.getText());
//                billBean.setB_AppCode1(txtCreditTrackNo.getText());
//                btnAccept.setEnabled(false);
//                billBean.setB_CrAmt1(saveCredit);
//                billBean.setB_CrChargeAmt1(TCreditCharge);
//                billBean.setB_CrCharge1(CreditCharge);
//
//                //get from bean
////                billBean.setB_CuponDiscAmt(discBean.getCuponDiscount());
//                billBean.setB_CuponDiscAmt(tBean.getCuponDiscAmt());
//                billBean.setB_FastDiscAmt(discBean.getFestDiscount());
//                billBean.setB_FastDisc(discBean.getStrFestDiscount());
//                billBean.setB_EmpDiscAmt(discBean.getEmpDiscount());
//                billBean.setB_EmpDisc(discBean.getStrEmpDiscount());
//                billBean.setB_TrainDiscAmt(discBean.getTrainDiscount());
//                billBean.setB_TrainDisc(discBean.getStrTrainDiscount());
//                billBean.setB_MemDiscAmt(discBean.getMemDiscount());
//                billBean.setB_MemDisc(discBean.getStrMemDiscount());
//                billBean.setB_SubDisc(discBean.getStrCuponDiscount());
//                billBean.setB_SubDiscAmt(discBean.getCuponDiscount());
//
//                BillControl billControl = new BillControl();
//                btnAccept.setVisible(false);
//                PublicVar.SubTotalOK = true;
//                billControl.saveBillNo(tableNo, billBean);
//                btnAccept.setVisible(true);
//
//                Value.MemberAlready = false;
//
//                lockScreen(this, false);
//            } else {
//                // กรณีใส่จำนวนเงินไม่ครบ
//                double total = netTotal - returnCash - returnGift - saveEntertain;
//                txtTotalAmount.setText(dec.format(total));
//            }
//
////            btnAccept.setEnabled(true);
////            btnAccept.setFocusable(true);
////            btnAccept.requestFocus();
////            btnAccept.setEnabled(false);
//        }
//        //btnAccept.setEnabled(false);
//    }
//    private double getServiceAllbalance(String table) {
//        double service = 0.00;
//        String etd = "";
//        double sumEtdE = 0.00;
//        double sumEtdT = 0.00;
//        double sumEtdD = 0.00;
//        double sumEtdP = 0.00;
//        double sumEtdW = 0.00;
//        
//        String[] strs = POSConfigSetup.Bean().getP_SerChkBySaleType().split("/");
//        String serviceSaleTypeE = "";
//        String serviceSaleTypeT = "";
//        String serviceSaleTypeD = "";
//        String serviceSaleTypeP = "";
//        String serviceSaleTypeW = "";
//        for (String data : strs) {
//            serviceSaleTypeE = strs[0];
//            serviceSaleTypeT = strs[1];
//            serviceSaleTypeD = strs[2];
//            serviceSaleTypeP = strs[3];
//            serviceSaleTypeW = strs[4];
//        }
//        try {
//            MySQLConnect c = new MySQLConnect();
//            c.open();
//            
//            for (int i = 0; i <= 4; i++) {
//                if (i == 0) {
//                    etd += "E";
//                } else if (i == 1) {
//                    etd += "T";
//                } else if (i == 2) {
//                    etd += "D";
//                } else if (i == 3) {
//                    etd += "P";
//                } else if (i == 4) {
//                    etd += "W";
//                }
//                String sql = "select "
//                        + "sum(r_total) r_toal "
//                        + "from "
//                        + "balance "
//                        + "where "
//                        + "r_table='" + table + "' and r_etd='" + etd + "';";
//                ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
//                if (rs.next() && !rs.wasNull()) {
//                    if (etd.equals("E") && serviceSaleTypeE.equals("Y")) {
//                        sumEtdE = rs.getDouble("r_toal");
//                    } else if (etd.endsWith("T") && serviceSaleTypeE.equals("Y")) {
//                        sumEtdT = rs.getDouble("r_toal");
//                    } else if (etd.endsWith("D") && serviceSaleTypeE.equals("Y")) {
//                        sumEtdD = rs.getDouble("r_toal");
//                    } else if (etd.endsWith("P") && serviceSaleTypeE.equals("Y")) {
//                        sumEtdP = rs.getDouble("r_toal");
//                    } else if (etd.endsWith("W") && serviceSaleTypeE.equals("Y")) {
//                        sumEtdW = rs.getDouble("r_toal");
//                    } else if (etd.equals("E") && serviceSaleTypeE.equals("N")) {
//                        sumEtdE = 0;
//                    } else if (etd.endsWith("T") && serviceSaleTypeE.equals("N")) {
//                        sumEtdT = 0;
//                    } else if (etd.endsWith("D") && serviceSaleTypeE.equals("N")) {
//                        sumEtdD = 0;
//                    } else if (etd.endsWith("P") && serviceSaleTypeE.equals("N")) {
//                        sumEtdP = 0;
//                    } else if (etd.endsWith("W") && serviceSaleTypeE.equals("N")) {
//                        sumEtdW = 0;
//                    }
//                }
//                service = sumEtdE + sumEtdT + sumEtdD + sumEtdP + sumEtdW;
//            }
//            c.close();
//        } catch (Exception e) {
//            MSG.ERR(e.toString());
//        }
//        return service;
//    }
    private void printBillCheck() {
        PPrint print = new PPrint();
        if (Value.useprint) {
            print.PrintCheckBill(tableNo);
        } else {
            //JOptionPane.showMessageDialog(this, "ระบบไม่ได้กำหนดให้ใช้งานเครื่องพิมพ์ !!!");
            print.printCheckBillDriver(tableNo);
        }
    }

    private void lockScreen(Container container, boolean b) {

        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(b);
            component.setFocusable(false);
            if (component instanceof Container) {
                lockScreen((Container) component, b);
            }
        }
        btnAccept.setEnabled(true);
        btnExit.setEnabled(true);
    }

    private void lockScreen1(boolean b) {
        jButton1.setEnabled(b);
        btnGiftVoucher.setEnabled(b);
        bntCash.setEnabled(b);
        btnCredit.setEnabled(b);
        btnDiscountAll.setEnabled(b);
        txtCreditNo.setEnabled(b);
        txtCreditTrackNo.setEnabled(b);
        txtCreditAmount.setEnabled(b);
        txtCashAmount.setEditable(b);
        panelNumber.setEnabled(b);
        btnExit.setEnabled(true);
        btnAccept.setEnabled(true);
        btnAccept.setFocusable(true);

    }

    private void trackNoExist() {
        try {
            String strTotalAmount = txtTotalAmount.getText().replace(",", "");
            double TotalAmount = Double.parseDouble(strTotalAmount);
            txtCreditAmount.setFocusable(true);
            txtCreditAmount.setText("" + TotalAmount);
            txtCreditAmount.requestFocus();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private void arCodeExits() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();

        try {
            String sql = "select sp_desc,sp_cr,sp_cramt "
                    + "from custfile "
                    + "where sp_code='" + txtArCode.getText() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lbArName.setText(ThaiUtil.ASCII2Unicode(rs.getString("sp_desc")));
                lbCredit.setText("" + rs.getInt("sp_cr"));
                lbCreditMoney.setText("" + rs.getDouble("sp_cramt"));

                try {
                    String sql2 = "select sum(aramount) total "
                            + "from accr "
                            + "where arcode='" + txtArCode.getText() + "' "
                            + "group by arcode";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                    if (rs2.next()) {
                        lbCreditAmt.setText("" + rs2.getDouble("total"));
                    } else {
                        lbCreditAmt.setText("0.00");
                    }

                    rs2.close();
                    stmt2.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                txtArAmount.setFocusable(true);
                txtArAmount.setText(txtTotalAmount.getText());
                txtArAmount.requestFocus();
                txtArAmount.selectAll();
            } else {
                //แจ้งเตือนให้เพิ่มลูกค้าเพื่อเป็นหนี้ใหม่
                int confirm = JOptionPane.showConfirmDialog(this, "ไม่พบรหัสลูกหนี้ " + txtArCode.getText() + " ในแฟ้มข้อมูลลูกหนี้ .. ต้องการเพิ่มใหม่หรือไม่ ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    AddNewArCustomer addNew = new AddNewArCustomer(null, true);
                    addNew.setVisible(true);
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private void SumReturnMoney(String Ret, String Amt) {
        if (Ret.equals("") || Ret.equals("0") || Ret.equals("0.00")) {
            txtReturnMoneyAmount.setText("0.00");
            ReturnSum(txtTotalCash.getText().replace(",", ""), txtTotalService.getText().replace(",", ""));
        } else {
            double R = Double.parseDouble(Ret);
            double A = Double.parseDouble(Amt);

            txtTotalAmount.setText(dec.format(A - R));
//            txtTotalAmount.setText(NumberFormat.showDouble2(A - R));
            txtCashAmount.requestFocus();
        }
    }

    private void ReturnSum(String TCash, String TService) {
        double TC = Double.parseDouble(TCash);
        double TS = Double.parseDouble(TService);

        double SumCS = TC + TS;
        txtTotalAmount.setText(dec.format(SumCS));
//        txtTotalAmount.setText(NumberFormat.showDouble2(SumCS));
        txtCashAmount.requestFocus();
    }

    private void LoadDisc() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String query = "select sum(FastDiscAmt+EmpDiscAmt+MemDiscAmt+TrainDiscAmt+SubDiscAmt+DiscBath+CuponDiscAmt) AAA from tablefile where Tcode = '" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String DiscTotal = ThaiUtil.ASCII2Unicode(rs.getString("AAA"));
                txtDiscountAmount.setText(DiscTotal);
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            MSG.ERR(ex.getMessage());
            
        } finally {
            mysql.close();
        }

    }

    private void clearTempSet(String tableNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tempset "
                    + "where PTableNo='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private void checkBillOK() {
        if (txtCreditNo.getText().length() > 16 || txtCreditTrackNo.getText().length() > 6) {
            JOptionPane.showMessageDialog(this, "Over Credit Number Or Over Appr Code : Please Check!");
            txtCreditNo.setEditable(true);
            txtCreditTrackNo.setEditable(true);
        } else {
            if (txtCreditNo.hasFocus()) {
                txtCreditTrackNo.setFocusable(true);
                txtCreditTrackNo.requestFocus();
            } else if (txtCreditTrackNo.hasFocus()) {
                trackNoExist();
            } else if (txtArCode.hasFocus()) {
                arCodeExits();
            } else {
                if (!PublicVar.SubTotalOK) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkBillPayment();
                        }
                    }).start();
                } else {
                    dispose();
                    jPanel6.setVisible(false);
                }
            }
        }
    }

    private void GetEDC() {
        double TempCreditAmt = Double.parseDouble(PUtility.ConvertReal(txtCreditAmount.getText().replace(",", "")));
        POSHWSetup hw = POSHWSetup.Bean(Value.MACNO);
        if (!hw.getEDCPort().equals("NONE")) {
            try {
                EDCControl frm = new EDCControl(null, true, hw.getEDCPort(), TempCreditAmt);
                frm.setVisible(true);
                while (!frm.ProcessFinish) {

                }
                if (!frm.ProcessError) {
                    txtCreditNo.setText(frm.CardCode);
                    txtCreditTrackNo.setText(frm.AppCode);
                    txtCreditAmount.setText("" + TempCreditAmt);
                    txtCreditNo.setFocusable(false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "มีปัญหาในการติดต่อเครื่องอนุมัติบัตรเครดิต...กรุณาตรวจสอบ", "Show Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void backupTempBalnace() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql1 = "delete from temp_balance where r_table='" + tableNo + "'";
            String sql2 = "insert ignore into temp_balance select * from balance "
                    + "where r_table='" + tableNo + "' order by r_index";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
    }

    private void restoreTempBalance() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from temp_balance where r_table ='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                try {
                    String sql1 = "delete from balance where r_table='" + tableNo + "'";
                    String sql2 = "insert into balance select * from temp_balance where r_table='" + tableNo + "' order by r_index";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    stmt2.executeUpdate(sql1);
                    stmt2.executeUpdate(sql2);
                    stmt2.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
            }
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

    }

    private void clearTempGift() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tempgift";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private boolean checkStatusCreditData() {
        if (txtCreditName.getText().equals("")) {
            btnCreditActionPerformed(null);
            return false;
        }
        if (txtCreditNo.getText().equals("")) {
            txtCreditNo.requestFocus();
            return false;
        }
        if (txtCreditTrackNo.getText().equals("")) {
            txtCreditTrackNo.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void kichenPrintAfterPrintCheck() {
        PrintSimpleForm printSimpleForm = new PrintSimpleForm();

        try {
            String printerName;
            String[] kicMaster = BranchControl.getKicData20();
            // หาจำนวนปริ้นเตอร์ว่าต้องออกกี่เครื่อง
            String sqlShowKic = "select r_kic from balance "
                    + "where r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "group by r_kic "
                    + "order by r_kic";
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt1 = mysql.getConnection().createStatement();
                ResultSet rsKic = stmt1.executeQuery(sqlShowKic);
                while (rsKic.next()) {
                    String rKic = rsKic.getString("r_kic");
                    if (!rKic.equals("")) {
                        try {
                            int iKic = Integer.parseInt(rKic);
                            if (iKic - 1 < 0) {
                                //ถ้าเป็น iKic=0 จะเป็นการไม่กำหนดให้ปริ้นออกครัว
                            } else {
                                if (kicMaster[iKic - 1].equals("N")) {
                                    //NOT PRINT or Print already
                                } else {
                                    printerName = "KIC" + rKic;
                                    String printerForm = BranchControl.getForm(rKic);
                                    if (printerForm.equals("1") || printerForm.equals("2")) {
                                        String sql1 = "select * from balance "
                                                + "where r_table='" + tableNo + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                + "and R_Void <> 'V' "
                                                + "group by r_plucode";
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        ResultSet rs1 = stmt2.executeQuery(sql1);
                                        while (rs1.next()) {
                                            String PCode = rs1.getString("R_PluCode");
                                            if (printerForm.equals("1")) {
                                                if (Value.printkic) {
                                                    printSimpleForm.KIC_FORM_1(printerName, tableNo, new String[]{PCode});
                                                }
                                            } else if (printerForm.equals("2")) {
                                                if (Value.printkic) {
                                                    printSimpleForm.KIC_FORM_2(printerName, tableNo, new String[]{PCode});
                                                }
                                            }
                                        }

                                        rs1.close();
                                        stmt2.close();
                                    } else if (printerForm.equals("6")) {
                                        String sql2 = "select sum(b.r_quan) R_Quan,sum(b.r_quan)*b.r_price Total, b.* from balance b "
                                                + "where r_table='" + tableNo + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                + "and R_Void<>'V' and R_KIC='" + rKic + "' "
                                                + "group by r_plucode order by r_opt1";
//                                        String sql2 = "select * from balance "
//                                                + "where r_table='" + tableNo + "' "
//                                                + "and R_PrintOK='Y' "
//                                                + "and R_KicPrint<>'P' "
//                                                + "and R_Kic<>'' "
//                                                + "and R_Void<>'V' ";
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        ResultSet rs2 = stmt2.executeQuery(sql2);
                                        while (rs2.next()) {
                                            if (Value.printkic) {

                                                String R_Index = rs2.getString("R_Index");
                                                String R_Plucode = rs2.getString("R_Plucode");
                                                double qty = rs2.getDouble("R_Quan");
                                                double priceTotal = rs2.getDouble("Total");
                                                printSimpleForm.KIC_FORM_6(printerName, tableNo, R_Index, R_Plucode, qty, priceTotal);
//                                                printBillCheck();
                                            }
                                        }

                                        rs2.close();
                                        stmt2.close();
                                    } else if (printerForm.equals("3") || printerForm.equals("4") || printerForm.equals("5")) {

                                        if (printerForm.equals("3")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_3("", printerName, tableNo, iKic);
//                                                printBillVoidCheck();
                                            }
                                        } else if (printerForm.equals("4")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_4(printerName, tableNo);
//                                                printBillVoidCheck();
                                            }
                                        } else if (printerForm.equals("5")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_5(printerName, tableNo);
//                                                printBillVoidCheck();
                                            }
                                        }
                                    } else {
                                        MSG.ERR(this, "ไม่พบฟอร์มปริ้นเตอร์ในระบบที่สามารใช้งานได้ !!!");
                                    }
                                }
                            }
                        } catch (SQLException e) {
                            MSG.ERR(this, e.getMessage());
                        }
                    }
                }

                rsKic.close();
                stmt1.close();

//                CheckKicPrint();
                //update r_kicprint
                try {
                    String sql = "update balance "
                            + "set r_kicprint='P',"
                            + "r_pause='Y' "
                            + "where r_table='" + tableNo + "' "
                            + "and r_kicprint<>'P' "
                            + "and r_printOk='Y' "
                            + "and r_kic<>'';";
                    Statement stmt = mysql.getConnection().createStatement();
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    MSG.ERR(this, e.getMessage());
                }
            } catch (SQLException e) {
                MSG.ERR(null, e.getMessage());
            } finally {
                mysql.close();
            }
        } catch (HeadlessException e) {
            MSG.ERR(null, e.getMessage());
        }
    }

//    public void ClearApp() {
//        ModalPopup popup = new ModalPopup(null, true, "", "", "", "", "");
//        popup.setVisible(false);
//        ShowTable s = new ShowTable(null, true);
//        MoveGroupTable move = new MoveGroupTable(null, true);
//        RefundBill refund = new RefundBill(null, true);
//        CopyBill c = new CopyBill(null, true);
//        FindCredit Find = new FindCredit(null, true);
//        CustomerCountDialog Cuscount = new CustomerCountDialog(null, true, "", "");
//        PopupItemJDialog ItemDialog = new PopupItemJDialog(null, true);
//        SplitBillPayment SplitBill = new SplitBillPayment(null, true, "");
//        VoidPopupDialog VoidPopUp = new VoidPopupDialog(null, true, "", memberBean);
//        CouponDiscount Cupon = new CouponDiscount(null, true, "", "", "", 0.00);
//        s.setVisible(false);
//        move.setVisible(false);
//        refund.setVisible(false);
//        c.setVisible(false);
//        popup.setVisible(false);
//        Find.setVisible(false);
//        Cuscount.setVisible(false);
//        ItemDialog.setVisible(false);
//        SplitBill.setVisible(false);
//        VoidPopUp.setVisible(false);
//        Cupon.setVisible(false);
//        PaidinFrm frm = new PaidinFrm(null, true);
//        frm.setVisible(false);
//        ResonPaidoutFrm frm1 = new ResonPaidoutFrm(null, true);
//        frm1.setVisible(false);
//        DiarySale d = new DiarySale(null, true);
//        d.setVisible(false);
//        DailyRep frm2 = new DailyRep(null, true);
//        frm2.setVisible(false);
//        DiscountDialog Dis = new DiscountDialog(null, true, "", 0, memberBean, "", "");
//        Dis.setVisible(false);
//    }
    private void clearCuponSpecail() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        //clear temp cupon
        try {
            String sql = "delete from tempcupon where r_table='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        mysql.open();
        try {
            String sql = "select * from temp_balance "
                    + "where r_table='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String sql1 = "delete from balance where r_table='" + tableNo + "'";
                String sql2 = "insert into balance select * from temp_balance "
                        + "where r_table='" + tableNo + "' "
                        + "order by r_index";
                String sql3 = "delete from temp_balance where r_table='" + tableNo + "'";
                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql3);

                BalanceControl.updateProSerTable(tableNo, memberBean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private boolean isTakeOrder() {
        boolean isTakeOrder = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "SELECT TakeOrderChk FROM poshwsetup "
                    + "where Terminal = '" + Value.MACNO + "' "
                    + "and TakeOrderChk='Y'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isTakeOrder = true;
            } else {
                isTakeOrder = false;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return isTakeOrder;
    }

    public void UpdateMember(String choice) {
        try {
            MySQLConnect c = new MySQLConnect();
            c.open();
            String sql = "";
            if (choice.equals("Ins")) {
//                sql = "Update tablefile set memcode='" + MemCode + "',memname='" + ThaiUtil.Unicode2ASCII(MemName) + "' where tcode='" + tableNo + "';";
            } else {
                sql = "Update tablefile set memcode='',memname='',memdisc='',memdiscamt='0',nettotal=tamount where tcode='" + tableNo + "'";
            }
            switch (choice) {
                case "Ins":
                    c.getConnection().createStatement().executeUpdate(sql);
                    break;
                case "Del":
                    c.getConnection().createStatement().executeUpdate(sql);
                    break;
            }
        } catch (Exception e) {
            MSG.ERR(e.toString());
        }
    }

}
