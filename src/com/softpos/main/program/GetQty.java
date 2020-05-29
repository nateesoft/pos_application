package com.softpos.main.program;

import com.softpos.main.model.Value;
import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.softpos.main.utils.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class GetQty extends javax.swing.JDialog {

    public int ReturnQty;
    private String PCode;
    public static String []OPTION_TEXT=new String[]{"","","","","","","","",""};
    private boolean isFirst = true;

    /**
     * Creates new form GetQty
     */
    public GetQty(java.awt.Frame parent, boolean modal, String PCode) {
        super(parent, modal);
        initComponents();
        ReturnQty = 0;
        StrAmount.setText("1");
        StrAmount.selectAll();
        this.PCode = PCode;
        loadOption(PCode);

        StrAmount.requestFocus();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        StrAmount = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        calc = new javax.swing.JPanel();
        c_bnt7 = new javax.swing.JButton();
        c_bnt8 = new javax.swing.JButton();
        c_bnt9 = new javax.swing.JButton();
        c_bntplus = new javax.swing.JButton();
        c_bntbs = new javax.swing.JButton();
        c_bnt4 = new javax.swing.JButton();
        c_bnt5 = new javax.swing.JButton();
        c_bnt6 = new javax.swing.JButton();
        c_bntsub = new javax.swing.JButton();
        c_bntesc = new javax.swing.JButton();
        c_bnt1 = new javax.swing.JButton();
        c_bnt2 = new javax.swing.JButton();
        c_bnt3 = new javax.swing.JButton();
        c_bntmulti = new javax.swing.JButton();
        c_bntenter = new javax.swing.JButton();
        c_bnt0 = new javax.swing.JButton();
        c_bntdot = new javax.swing.JButton();
        c_bntclr = new javax.swing.JButton();
        c_bntsal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOptionMsg = new javax.swing.JTable();
        txtShowOption = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnKeyBoard = new javax.swing.JButton();
        chkAutoQty = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ป้อนจำนวนสินค้า");
        setAlwaysOnTop(true);
        setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("กรุณาป้อนจำนวนที่ต้องการ");

        StrAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#####0"))));
        StrAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        StrAmount.setText("1");
        StrAmount.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        StrAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StrAmountKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("กด F1 เพื่อระบุคำสั่งพิเศษเพิ่มเติม");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(StrAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StrAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        calc.setRequestFocusEnabled(false);
        calc.setVerifyInputWhenFocusTarget(false);
        calc.setLayout(new java.awt.GridLayout(4, 0));

        c_bnt7.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt7.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt7.setText("7");
        c_bnt7.setFocusable(false);
        c_bnt7.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt7ActionPerformed(evt);
            }
        });
        calc.add(c_bnt7);

        c_bnt8.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt8.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt8.setText("8");
        c_bnt8.setFocusable(false);
        c_bnt8.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt8ActionPerformed(evt);
            }
        });
        calc.add(c_bnt8);

        c_bnt9.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt9.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt9.setText("9");
        c_bnt9.setFocusable(false);
        c_bnt9.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt9ActionPerformed(evt);
            }
        });
        calc.add(c_bnt9);

        c_bntplus.setBackground(new java.awt.Color(102, 153, 255));
        c_bntplus.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bntplus.setForeground(new java.awt.Color(255, 255, 255));
        c_bntplus.setText("+");
        c_bntplus.setFocusable(false);
        c_bntplus.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntplus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntplusActionPerformed(evt);
            }
        });
        calc.add(c_bntplus);

        c_bntbs.setBackground(new java.awt.Color(102, 153, 255));
        c_bntbs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c_bntbs.setForeground(new java.awt.Color(255, 255, 255));
        c_bntbs.setText("<-BS");
        c_bntbs.setFocusable(false);
        c_bntbs.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntbsActionPerformed(evt);
            }
        });
        calc.add(c_bntbs);

        c_bnt4.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt4.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt4.setText("4");
        c_bnt4.setFocusable(false);
        c_bnt4.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt4ActionPerformed(evt);
            }
        });
        calc.add(c_bnt4);

        c_bnt5.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt5.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt5.setText("5");
        c_bnt5.setFocusable(false);
        c_bnt5.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt5ActionPerformed(evt);
            }
        });
        calc.add(c_bnt5);

        c_bnt6.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt6.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt6.setText("6");
        c_bnt6.setFocusable(false);
        c_bnt6.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt6ActionPerformed(evt);
            }
        });
        calc.add(c_bnt6);

        c_bntsub.setBackground(new java.awt.Color(102, 153, 255));
        c_bntsub.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bntsub.setForeground(new java.awt.Color(255, 255, 255));
        c_bntsub.setText("-");
        c_bntsub.setFocusable(false);
        c_bntsub.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntsubActionPerformed(evt);
            }
        });
        calc.add(c_bntsub);

        c_bntesc.setBackground(new java.awt.Color(102, 153, 255));
        c_bntesc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c_bntesc.setForeground(new java.awt.Color(255, 255, 255));
        c_bntesc.setText("ESC");
        c_bntesc.setFocusable(false);
        c_bntesc.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntescActionPerformed(evt);
            }
        });
        calc.add(c_bntesc);

        c_bnt1.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt1.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt1.setText("1");
        c_bnt1.setFocusable(false);
        c_bnt1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt1ActionPerformed(evt);
            }
        });
        calc.add(c_bnt1);

        c_bnt2.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt2.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt2.setText("2");
        c_bnt2.setFocusable(false);
        c_bnt2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt2ActionPerformed(evt);
            }
        });
        calc.add(c_bnt2);

        c_bnt3.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt3.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt3.setText("3");
        c_bnt3.setFocusable(false);
        c_bnt3.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt3ActionPerformed(evt);
            }
        });
        calc.add(c_bnt3);

        c_bntmulti.setBackground(new java.awt.Color(102, 153, 255));
        c_bntmulti.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bntmulti.setForeground(new java.awt.Color(255, 255, 255));
        c_bntmulti.setText("x");
        c_bntmulti.setFocusable(false);
        c_bntmulti.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntmulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntmultiActionPerformed(evt);
            }
        });
        calc.add(c_bntmulti);

        c_bntenter.setBackground(new java.awt.Color(102, 153, 255));
        c_bntenter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c_bntenter.setForeground(new java.awt.Color(255, 255, 255));
        c_bntenter.setText("Enter");
        c_bntenter.setFocusable(false);
        c_bntenter.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntenterActionPerformed(evt);
            }
        });
        calc.add(c_bntenter);

        c_bnt0.setBackground(new java.awt.Color(102, 153, 255));
        c_bnt0.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bnt0.setForeground(new java.awt.Color(255, 255, 255));
        c_bnt0.setText("0");
        c_bnt0.setFocusable(false);
        c_bnt0.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bnt0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bnt0ActionPerformed(evt);
            }
        });
        calc.add(c_bnt0);

        c_bntdot.setBackground(new java.awt.Color(102, 153, 255));
        c_bntdot.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bntdot.setForeground(new java.awt.Color(255, 255, 255));
        c_bntdot.setText(".");
        c_bntdot.setFocusable(false);
        c_bntdot.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntdot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                c_bntdotMouseReleased(evt);
            }
        });
        calc.add(c_bntdot);

        c_bntclr.setBackground(new java.awt.Color(102, 153, 255));
        c_bntclr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        c_bntclr.setForeground(new java.awt.Color(255, 255, 255));
        c_bntclr.setText("CLR");
        c_bntclr.setFocusable(false);
        c_bntclr.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntclr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_bntclrMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                c_bntclrMouseReleased(evt);
            }
        });
        calc.add(c_bntclr);

        c_bntsal.setBackground(new java.awt.Color(102, 153, 255));
        c_bntsal.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        c_bntsal.setForeground(new java.awt.Color(255, 255, 255));
        c_bntsal.setText("/");
        c_bntsal.setFocusable(false);
        c_bntsal.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntsalActionPerformed(evt);
            }
        });
        calc.add(c_bntsal);

        tblOptionMsg.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
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

        txtShowOption.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtShowOption.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtShowOption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtShowOptionKeyPressed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(153, 255, 153));
        btnOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOK.setText("ยืนยันคำสั่ง (ENTER)");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 153, 153));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel.setText("ยกเลิก (ESC)");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnKeyBoard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnKeyBoard.setText("F2");
        btnKeyBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeyBoardActionPerformed(evt);
            }
        });

        chkAutoQty.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkAutoQty.setText("ไม่ต้องแสดงรายการตอนสั่งสินค้า");
        chkAutoQty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkAutoQtyItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calc, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtShowOption, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKeyBoard))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkAutoQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtShowOption)
                            .addComponent(btnKeyBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkAutoQty))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calc, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void StrAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StrAmountKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        GetAndReturnQty();
    }else if(evt.getKeyCode()==KeyEvent.VK_F1){
        tblOptionMsg.requestFocus();
    }else {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ReturnQty = 0;
            clear();
            this.dispose();
        }
    }
}//GEN-LAST:event_StrAmountKeyPressed

private void c_bnt8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt8ActionPerformed
    inputfrombnt("8");
}//GEN-LAST:event_c_bnt8ActionPerformed

private void c_bntsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntsubActionPerformed
    inputfrombnt("-");
}//GEN-LAST:event_c_bntsubActionPerformed

private void c_bnt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt3ActionPerformed
    inputfrombnt("3");
}//GEN-LAST:event_c_bnt3ActionPerformed

private void c_bntdotMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_bntdotMouseReleased
// TODO add your handling code here:
    inputfrombnt(".");
}//GEN-LAST:event_c_bntdotMouseReleased

private void c_bntclrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_bntclrMouseClicked

}//GEN-LAST:event_c_bntclrMouseClicked

private void c_bntclrMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_bntclrMouseReleased
// TODO add your handling code here:
    StrAmount.setText("");
}//GEN-LAST:event_c_bntclrMouseReleased

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        GetAndReturnQty();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        ReturnQty = 0;
        clear();
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblOptionMsgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOptionMsgMouseClicked
        int rows = tblOptionMsg.getSelectedRow();
        if(rows!=-1){
            String opt = tblOptionMsg.getValueAt(rows, 0).toString();
            String optAll = txtShowOption.getText()+opt+",";
            txtShowOption.setText(optAll);
            txtShowOption.requestFocus();
        }
    }//GEN-LAST:event_tblOptionMsgMouseClicked

    private void btnKeyBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeyBoardActionPerformed
        KeyBoardDialog kb = new KeyBoardDialog(new JFrame(), true, 4);
        kb.setVisible(true);
        
        if(!KeyBoardDialog.TEXT_INPUT.equals("")){
            String optAll = txtShowOption.getText()+KeyBoardDialog.TEXT_INPUT+",";
            txtShowOption.setText(optAll);
            txtShowOption.requestFocus();
        }
    }//GEN-LAST:event_btnKeyBoardActionPerformed

    private void txtShowOptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShowOptionKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_F2){
            btnKeyBoardActionPerformed(null);
            txtShowOption.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tblOptionMsg.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnOKActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            StrAmount.requestFocus();
        }
    }//GEN-LAST:event_txtShowOptionKeyPressed

    private void tblOptionMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblOptionMsgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int rows = tblOptionMsg.getSelectedRow();
            if(rows!=-1){
                String opt = tblOptionMsg.getValueAt(rows, 0).toString();
                String optAll = txtShowOption.getText()+opt+",";
                txtShowOption.setText(optAll);
                txtShowOption.requestFocus();
            }
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            txtShowOption.requestFocus();
        }
    }//GEN-LAST:event_tblOptionMsgKeyPressed

    private void c_bnt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt1ActionPerformed
        inputfrombnt("1");
    }//GEN-LAST:event_c_bnt1ActionPerformed

    private void c_bnt0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt0ActionPerformed
       inputfrombnt("0");
    }//GEN-LAST:event_c_bnt0ActionPerformed

    private void c_bnt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt2ActionPerformed
        inputfrombnt("2");
    }//GEN-LAST:event_c_bnt2ActionPerformed

    private void c_bnt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt4ActionPerformed
        inputfrombnt("4");
    }//GEN-LAST:event_c_bnt4ActionPerformed

    private void c_bnt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt5ActionPerformed
        inputfrombnt("5");
    }//GEN-LAST:event_c_bnt5ActionPerformed

    private void c_bnt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt6ActionPerformed
        inputfrombnt("6");
    }//GEN-LAST:event_c_bnt6ActionPerformed

    private void c_bnt7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt7ActionPerformed
        inputfrombnt("7");
    }//GEN-LAST:event_c_bnt7ActionPerformed

    private void c_bnt9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bnt9ActionPerformed
        inputfrombnt("9");
    }//GEN-LAST:event_c_bnt9ActionPerformed

    private void c_bntplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntplusActionPerformed
        inputfrombnt("+");
    }//GEN-LAST:event_c_bntplusActionPerformed

    private void c_bntmultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntmultiActionPerformed
        inputfrombnt("*");
    }//GEN-LAST:event_c_bntmultiActionPerformed

    private void c_bntsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntsalActionPerformed
        inputfrombnt("/");
    }//GEN-LAST:event_c_bntsalActionPerformed

    private void c_bntenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntenterActionPerformed
        GetAndReturnQty();
    }//GEN-LAST:event_c_bntenterActionPerformed

    private void c_bntescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntescActionPerformed
        ReturnQty = 0;
        this.dispose();
    }//GEN-LAST:event_c_bntescActionPerformed

    private void c_bntbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntbsActionPerformed
        if(StrAmount.hasFocus()){
            String tempstr = "";
            String tempstr2 = "";
            tempstr = StrAmount.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            StrAmount.setText(tempstr2);
        }else if(txtShowOption.hasFocus()){
            String tempstr = "";
            String tempstr2 = "";
            tempstr = txtShowOption.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            txtShowOption.setText(tempstr2);
        }
    }//GEN-LAST:event_c_bntbsActionPerformed

    private void chkAutoQtyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkAutoQtyItemStateChanged
        if(chkAutoQty.isSelected()){
            // set fix autoqty = false
            Value.autoqty = false;
        }else{
            // set fix autoqty = true;
            Value.autoqty = true;
        }
    }//GEN-LAST:event_chkAutoQtyItemStateChanged

    public void inputfrombnt(String str) {
        String tempstr;        
        tempstr = StrAmount.getText();
        if(isFirst){
            tempstr = "";
            isFirst = false;
        }
        tempstr = tempstr + str;
        StrAmount.setText(tempstr);
    }

    public void GetAndReturnQty() {
        try {
            int TempQty = Integer.parseInt(StrAmount.getText());
            ReturnQty = TempQty;
            
            String []data = txtShowOption.getText().split(",");
            System.arraycopy(data, 0, OPTION_TEXT, 0, data.length);
            this.dispose();
        } catch (NumberFormatException e) {
            MSG.ERR(this, "กรุณาป้อน จำนวนที่ต้องการให้ถูกต้อง!!!");
            StrAmount.requestFocus();
        }
    }
    
    public static void clear(){
        OPTION_TEXT = new String[]{"","","","","","","","",""};
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GetQty dialog = new GetQty(new javax.swing.JFrame(), true, "20801");
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
    private javax.swing.JFormattedTextField StrAmount;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnKeyBoard;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton c_bnt0;
    private javax.swing.JButton c_bnt1;
    private javax.swing.JButton c_bnt2;
    private javax.swing.JButton c_bnt3;
    private javax.swing.JButton c_bnt4;
    private javax.swing.JButton c_bnt5;
    private javax.swing.JButton c_bnt6;
    private javax.swing.JButton c_bnt7;
    private javax.swing.JButton c_bnt8;
    private javax.swing.JButton c_bnt9;
    private javax.swing.JButton c_bntbs;
    private javax.swing.JButton c_bntclr;
    private javax.swing.JButton c_bntdot;
    private javax.swing.JButton c_bntenter;
    private javax.swing.JButton c_bntesc;
    private javax.swing.JButton c_bntmulti;
    private javax.swing.JButton c_bntplus;
    private javax.swing.JButton c_bntsal;
    private javax.swing.JButton c_bntsub;
    private javax.swing.JPanel calc;
    private javax.swing.JCheckBox chkAutoQty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblOptionMsg;
    private javax.swing.JTextField txtShowOption;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel model1;

    private void loadOption(String PCode) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            model1 = (DefaultTableModel) tblOptionMsg.getModel();
            tblOptionMsg.setFont(new Font("Norasi", Font.PLAIN, 14));
            tblOptionMsg.setRowHeight(30);
            JTableHeader tHeader = tblOptionMsg.getTableHeader();
            tHeader.setFont(new Font("Norasi", Font.BOLD, 14));

            Statement stmt = mysql.getConnection().createStatement();
            String sql = "select o.* "
                    + "from product p, optionfile o "
                    + "where p.pgroup=o.pgroup "
                    + "and p.pcode='" + PCode + "';";
            ResultSet rec = stmt.executeQuery(sql);

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
            }
            rec.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
    }

}
