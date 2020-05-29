package com.softpos.main.program;

import com.softpos.main.model.POSHWSetup;
import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.utils.PUtility;
import com.softpos.main.model.Value;
import com.softpos.main.model.PublicVar;
import com.softpos.main.model.UserRecord;
import com.softpos.main.controller.BalanceControl;
import com.softpos.main.model.ProductBean;
import com.softpos.main.model.TableFileBean;
import com.softpos.main.controller.TableFileControl;
import com.softpos.main.controller.ProductControl;
import com.softpos.main.model.BranchBean;
import com.softpos.main.controller.BranchControl;
import com.softpos.main.model.BalanceBean;
import com.softpos.main.view.DiscountDialog;
import com.softpos.floorplan.DailyRep;
import com.softpos.floorplan.DiarySale;
import com.softpos.floorplan.FloorPlanDialog;
import com.softpos.floorplan.MoveGroupTable;
import printReport.PrintSimpleForm;
import com.softpos.floorplan.RefundBill;
import com.softpos.floorplan.ShowTable;
import com.softpos.floorplan.PaidinFrm;
import com.softpos.floorplan.ResonPaidoutFrm;
import com.softpos.main.controller.PriceController;
import com.softpos.main.model.TempSetBean;
import com.softpos.main.controller.TempSetController;
import static com.softpos.main.controller.BalanceControl.updateProSerTable;
import static com.softpos.main.controller.BalanceControl.updateProSerTableMem;
import static com.softpos.main.controller.BalanceControl.updateProSerTableMemVIP;
import com.softpos.main.model.MemberBean;
import com.softpos.main.controller.MemberControl;
import database.ConfigFile;
import database.MySQLConnect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import posbean.CustomerBean;
import setupmenu.SetHeaderMenu;
import static soft.virtual.JTableControl.alignColumn;
import soft.virtual.KeyBoardDialog;
import sun.natee.project.util.ThaiUtil;
import util.Option;
import util.MSG;

public class MainSale extends javax.swing.JDialog {

    private PPrint Prn = new PPrint();
    private Timer timer;
    private DefaultTableModel model;
    private SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm:ss");
    private boolean TableOpenStatus;
    private DecimalFormat QtyIntFmt = new DecimalFormat("###########0");
    private String TempStatus = "";
    private POSHWSetup POSHW;
    private POSConfigSetup CONFIG;
    public static String INDEX_NAME = "";
    private ArrayList<Integer> historyBack;
    private DecimalFormat dc1 = new DecimalFormat("#,##0.00");
    private MemberBean memberBean;
    private String tableNo;
    private String SALE_DINE_IN = "Dine In";
    private String SALE_TAKE_AWAY = "Take Away";
    private String SALE_Delivery = "Delivery";
    private boolean btnClickPrintKic = false;

    public MainSale(java.awt.Frame parent, boolean modal, String tableNo) {
        super(parent, modal);
        initComponents();
//        btnPrintKic.setEnabled(false);
        MMainMenu1.setVisible(true);
        jMenu1.setVisible(true);
        jMenu2.setVisible(true);
        txtDisplayDiscount.setVisible(true);
        txtDiscount.setVisible(true);
        jPanelMember.setVisible(false);
        jPanel5.setVisible(false);
        txtDisplayDiscount.setVisible(false);
        txtDiscount.setVisible(false);

        if (btnLangTH.isSelected()) {
            PublicVar.languagePrint = "TH";
        }
        if (btnLangEN.isSelected()) {
            PublicVar.languagePrint = "EN";
        }
        if (tableNo.indexOf("(") != -1) {
            String T1 = tableNo.substring(0, tableNo.indexOf("("));
            tableNo = T1;
        }
        TableFileControl tbControl = new TableFileControl();
        TableFileBean tbBean = tbControl.getData(tableNo);
        this.memberBean = MemberBean.getMember(tbBean.getMemCode());
        if (memberBean == null) {
            BalanceControl.updateProSerTable(tableNo, memberBean);
        } else {
            if (!memberBean.getMember_Code().equals("")) {
                BalanceControl.updateProSerTable(tableNo, memberBean);
                txtMember1.setText(memberBean.getMember_NameThai());
                txtMember2.setText("แต้มสะสม " + memberBean.getMember_TotalScore() + " แต้ม");
            } else if (tbBean.getMemDiscAmt() != 0) {
                updateProSerTableMemVIP(tableNo, tbBean.getMemDisc());
            }

        }
        txtDiscount.setText("- " + BalanceControl.GetDiscount(tableNo));
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        setupMenu();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        Value.MemberAlready = false;
//        try {
//            Image im = ImageIO.read(getClass().getResource("/images/tb.png"));
//            setIconImage(im);
//        } catch (IOException ex) {
//            MSG.ERR(null, ex.getMessage());
//        }
//        loadVariable();

        TimeOfDay time = new TimeOfDay();
        timer = new Timer(1000, time);
        timer.start();

        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();

        initScreen();
//        setupMenu();
        super.setTitle(super.getTitle() + " (" + Value.USERCODE + ") " + "พนักงาน: " + PublicVar._UserName);
        BranchBean branchBean = BranchControl.getData();
        if (branchBean.getLocation_Area().equals("02")) {
            txtShowETD.setText("T");
        }

        this.tableNo = tableNo;
        txtTable.setText(tableNo);
        tblShowPluShow(txtTable.getText());

        historyBack = new ArrayList<>();

        //setUndecorated(true);
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        Dimension d = getMaximumSize();
        setSize(1024, 768);
        setLocationRelativeTo(null);
        sumSplit();
        txtPluCode.setEditable(true);
        txtPluCode.setFocusable(true);
        txtPluCode.requestFocus();
        jMenu1.setVisible(true);
        MAddNewMember1.setVisible(true);

        //String[] CheckTOrder = CheckTOrder();
        tbpMain.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tab_placement, int run_count, int max_tab_height) {
                if (tbpMain.getTabCount() > 1) {
                    return -1;
                } else {
                    return -1;
                }
            }
        });
        upDateTableFile();

        showCustomerInput();
    }

    @SuppressWarnings("static-access")
    private boolean chkEJPath() {
        if (POSHW.getEJounal().equals("Y")) {
            try {
                String TempFile = POSHW.getEJDailyPath();
                File fObject = new File(TempFile);
                if (!fObject.exists()) {
                    return false;
                }
                return true;
            } catch (HeadlessException e) {
                MSG.ERR(this, "ไม่สามารถสร้าง Log File/EJFile ตามตำแหน่งที่เก็บข้อมูล Log File/EJ ได้ กรุณาติดต่อเจ้าหน้าที่ Support...");
                return false;
            }
        } else {
            return true;
        }
    }

    private void loadLoginForm() {
        this.setVisible(false);
        showTableOpen();
    }

    void showTableOpen() {
        this.setVisible(false);
    }

//    private void loadVariable() {
//        /**
//         * * OPEN CONNECTION **
//         */
//        MySQLConnect mysql = new MySQLConnect();
//        mysql.open();
//        try {
//            Statement stmt = mysql.getConnection().createStatement();
//            String SQLQuery2 = "select *from billno order by b_ondate";
//            ResultSet rec2 = stmt.executeQuery(SQLQuery2);
//            rec2.first();
//            if (rec2.getRow() == 0) {
//                PublicVar.SaleDate = new Date();
//            } else {
//                PublicVar.SaleDate = rec2.getDate("b_Ondate");
//            }
//            rec2.close();
//            stmt.close();
//        } catch (SQLException e) {
//            MSG.ERR(this, e.getMessage());
//            
//        }
//        //check cr_cardno1 max = 100;
//        try {
//            String sql = "show columns from invcashdoc";
//            Statement stmt = mysql.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String field = rs.getString(1);
//                String type = rs.getString(2);
//                if (field.equalsIgnoreCase("crno")) {
//                    if (!type.equalsIgnoreCase("varchar(100)")) {
//                        //modify
//                        String sql2 = "alter table invcashdoc "
//                                + "change CrNo CrNo varchar(100) NOT NULL Default '' ";
//                        Statement stmt2 = mysql.getConnection().createStatement();
//                        stmt2.executeUpdate(sql2);
//                        stmt2.close();
//                    }
//                }
//            }
//            rs.close();
//            stmt.close();
//        } catch (SQLException e) {
//            //MSG.ERR(this, e.getMessage());
//            
//        }
//
//        mysql.close();
//    }
    private void showSaleType(String SaleType) {
        txtShowETD.setVisible(false);
        txtShowETD.setText(SaleType);
    }

    private void initScreen() {
        model = (DefaultTableModel) tblShowBalance.getModel();

        tblShowBalance.setShowGrid(true);
        tblShowBalance.setGridColor(Color.gray);
        tblShowBalance.setRowHeight(35);

        JTableHeader header = tblShowBalance.getTableHeader();
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));

        alignColumn(tblShowBalance, 2, "right");
        alignColumn(tblShowBalance, 3, "right");
        alignColumn(tblShowBalance, 4, "right");

        PublicVar.CheckStockOnLine = PUtility.GetStockOnLine();

        TableOpenStatus = false;
        PublicVar.ErrorColect = false;

        showSaleType(POSHW.getSaleType());
        PublicVar.P_LogoffOK = false;

        PublicVar.CouponCnt = 0;
        PublicVar.P_ItemCount = 0;
        PublicVar.P_TotalAmt = 0.00;
        PublicVar.P_TotService = 0.00;
        PublicVar.P_TotDiscount = 0.00;
        PublicVar.P_NetAmt = 0.00;

        txtTable.setText("");
        txtCust.setText("");
        txtPluCode.setText("");
        txtTable.setEditable(true);
        txtCust.setEditable(false);
        txtPluCode.setEditable(false);

        txtTable.requestFocus();

        lbTotalAmount.setText("0.00");
        jLabel1.setText("จำนวนรายการสินค้า: 0 รายการ");

        changeSaleType("E");

        getTableAction();

    }

    private void getTableAction() {
        txtTable.setEditable(true);
        txtTable.requestFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowBalance = new javax.swing.JTable();
        txtPluCode = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtTable = new javax.swing.JTextField();
        txtShowETD = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCust = new javax.swing.JTextField();
        txtTypeDesc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnHold = new javax.swing.JButton();
        bntPrintCheckBill = new javax.swing.JButton();
        btnSplit = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbTotalAmount = new javax.swing.JLabel();
        tbpMain = new javax.swing.JTabbedPane();
        pMenu1 = new javax.swing.JPanel();
        pMenu2 = new javax.swing.JPanel();
        pMenu3 = new javax.swing.JPanel();
        pMenu4 = new javax.swing.JPanel();
        pMenu5 = new javax.swing.JPanel();
        pMenu6 = new javax.swing.JPanel();
        pMenu7 = new javax.swing.JPanel();
        pMenu8 = new javax.swing.JPanel();
        pMenu9 = new javax.swing.JPanel();
        pSubMenu1 = new javax.swing.JPanel();
        pSubMenu2 = new javax.swing.JPanel();
        pSubMenu3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanelMember = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMember1 = new javax.swing.JTextField();
        txtMember2 = new javax.swing.JTextField();
        txtDisplayDiscount = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        btnPrintKic = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbCredit = new javax.swing.JLabel();
        lbCreditMoney = new javax.swing.JLabel();
        lbCreditAmt = new javax.swing.JLabel();
        btnLangTH = new javax.swing.JRadioButton();
        btnLangEN = new javax.swing.JRadioButton();
        jMenuBar11 = new javax.swing.JMenuBar();
        MMainMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MCancelArPayment2 = new javax.swing.JMenuItem();
        MCancelArPayment1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MAddNewAccr1 = new javax.swing.JMenuItem();
        MRepArNotPayment1 = new javax.swing.JMenuItem();
        MRepArHistory1 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        MAddNewMember1 = new javax.swing.JMenuItem();
        MRepMemberHistory1 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        MRepInvCash1 = new javax.swing.JMenuItem();
        MRepInvAr1 = new javax.swing.JMenuItem();
        MHeaderBill1 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();

        jPopupMenu1.setBackground(new java.awt.Color(102, 153, 0));
        jPopupMenu1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPopupMenu1.setForeground(new java.awt.Color(255, 255, 255));

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem1.setText("แก้ไขรายการ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem2.setText("กำหนดประเภทปุ่ม");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("โปรแกรมร้านอาหาร");
        setBackground(new java.awt.Color(255, 204, 204));
        setModal(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblShowBalance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblShowBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Product Name", "QTY.", "Price", "Total", "Void Flag", "Type Sale", "Print to Kic", "Order Send", "Promotion", "RIndex", "Pause", "Employ", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShowBalance.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblShowBalance.setFocusable(false);
        tblShowBalance.setRequestFocusEnabled(false);
        tblShowBalance.setRowHeight(25);
        tblShowBalance.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tblShowBalance.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblShowBalance.setShowVerticalLines(false);
        tblShowBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblShowBalanceMouseClicked(evt);
            }
        });
        tblShowBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblShowBalanceKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblShowBalance);
        if (tblShowBalance.getColumnModel().getColumnCount() > 0) {
            tblShowBalance.getColumnModel().getColumn(0).setMinWidth(0);
            tblShowBalance.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblShowBalance.getColumnModel().getColumn(0).setMaxWidth(0);
            tblShowBalance.getColumnModel().getColumn(1).setPreferredWidth(265);
            tblShowBalance.getColumnModel().getColumn(2).setPreferredWidth(65);
        }

        txtPluCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPluCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPluCode.setMargin(new java.awt.Insets(2, 0, 2, 2));
        txtPluCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPluCodeMouseClicked(evt);
            }
        });
        txtPluCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPluCodeFocusGained(evt);
            }
        });
        txtPluCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPluCodeKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 51, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTable.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtTable.setForeground(new java.awt.Color(255, 0, 0));
        txtTable.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtTable.setDisabledTextColor(java.awt.Color.red);
        txtTable.setFocusable(false);
        txtTable.setMargin(new java.awt.Insets(2, 10, 2, 2));
        txtTable.setRequestFocusEnabled(false);
        txtTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTableFocusGained(evt);
            }
        });
        txtTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTableKeyPressed(evt);
            }
        });

        txtShowETD.setEditable(false);
        txtShowETD.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        txtShowETD.setForeground(new java.awt.Color(255, 255, 255));
        txtShowETD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtShowETD.setText("E");
        txtShowETD.setDisabledTextColor(java.awt.Color.black);
        txtShowETD.setEnabled(false);
        txtShowETD.setFocusable(false);
        txtShowETD.setRequestFocusEnabled(false);
        txtShowETD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtShowETDMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("ลูกค้า");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("โต๊ะ");

        txtCust.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtCust.setForeground(new java.awt.Color(255, 0, 0));
        txtCust.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCust.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCust.setDisabledTextColor(java.awt.Color.black);
        txtCust.setFocusable(false);
        txtCust.setRequestFocusEnabled(false);
        txtCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustMouseClicked(evt);
            }
        });
        txtCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustActionPerformed(evt);
            }
        });
        txtCust.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCustFocusGained(evt);
            }
        });
        txtCust.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCustKeyPressed(evt);
            }
        });

        txtTypeDesc.setEditable(false);
        txtTypeDesc.setBackground(new java.awt.Color(204, 204, 255));
        txtTypeDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTypeDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTypeDesc.setText("DINE IN");
        txtTypeDesc.setBorder(null);
        txtTypeDesc.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTypeDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTypeDescMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTable, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCust, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtShowETD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTypeDesc)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtCust, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtTable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTypeDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtShowETD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnCancel.setBackground(new java.awt.Color(204, 51, 0));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("<html>ยกเลิกรายการ<br />ก่อนส่งครัว</html>");
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setRequestFocusEnabled(false);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancel);

        btnHold.setBackground(new java.awt.Color(204, 51, 0));
        btnHold.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHold.setForeground(new java.awt.Color(255, 255, 255));
        btnHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hold.png"))); // NOI18N
        btnHold.setText("ส่งครัว/พักบิล");
        btnHold.setActionCommand("พักบิล/พักโต๊ะ (F3)");
        btnHold.setFocusable(false);
        btnHold.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHold.setPreferredSize(new java.awt.Dimension(70, 61));
        btnHold.setRequestFocusEnabled(false);
        btnHold.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoldActionPerformed(evt);
            }
        });
        jPanel2.add(btnHold);

        bntPrintCheckBill.setBackground(new java.awt.Color(204, 51, 0));
        bntPrintCheckBill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntPrintCheckBill.setForeground(new java.awt.Color(255, 255, 255));
        bntPrintCheckBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        bntPrintCheckBill.setText("พิมพ์ตรวจสอบ");
        bntPrintCheckBill.setFocusable(false);
        bntPrintCheckBill.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrintCheckBill.setRequestFocusEnabled(false);
        bntPrintCheckBill.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntPrintCheckBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintCheckBillActionPerformed(evt);
            }
        });
        jPanel2.add(bntPrintCheckBill);

        btnSplit.setBackground(new java.awt.Color(204, 51, 0));
        btnSplit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSplit.setForeground(new java.awt.Color(255, 255, 255));
        btnSplit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/split.png"))); // NOI18N
        btnSplit.setText("แยกชำระ");
        btnSplit.setFocusable(false);
        btnSplit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSplit.setPreferredSize(new java.awt.Dimension(70, 61));
        btnSplit.setRequestFocusEnabled(false);
        btnSplit.setRolloverEnabled(false);
        btnSplit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSplit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSplitActionPerformed(evt);
            }
        });
        jPanel2.add(btnSplit);

        btnPayment.setBackground(new java.awt.Color(204, 51, 0));
        btnPayment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/เช็คบิล.png"))); // NOI18N
        btnPayment.setText("ชำระเงิน");
        btnPayment.setFocusable(false);
        btnPayment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPayment.setRequestFocusEnabled(false);
        btnPayment.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        jPanel2.add(btnPayment);

        jPanel3.setBackground(new java.awt.Color(204, 51, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbTotalAmount.setBackground(new java.awt.Color(153, 255, 153));
        lbTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        lbTotalAmount.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalAmount.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );

        tbpMain.setBackground(new java.awt.Color(255, 255, 255));
        tbpMain.setForeground(new java.awt.Color(255, 255, 255));
        tbpMain.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tbpMain.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tbpMain.setFocusable(false);
        tbpMain.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        tbpMain.setPreferredSize(new java.awt.Dimension(0, 0));
        tbpMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpMainMouseClicked(evt);
            }
        });

        pMenu1.setBackground(new java.awt.Color(255, 255, 255));
        pMenu1.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu1);

        pMenu2.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu2);

        pMenu3.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu3);

        pMenu4.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu4);

        pMenu5.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu5);

        pMenu6.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu6);

        pMenu7.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu7);

        pMenu8.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu8);

        pMenu9.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pMenu9);

        pSubMenu1.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pSubMenu1);

        pSubMenu2.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pSubMenu2);

        pSubMenu3.setLayout(new java.awt.GridLayout(4, 4));
        tbpMain.addTab("", pSubMenu3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(tbpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 546, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(tbpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText(" 0  รายการ");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("แป้นพิมพ์");
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanelMember.setBackground(new java.awt.Color(255, 255, 153));

        jLabel12.setBackground(new java.awt.Color(255, 255, 153));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("สมาชิก : ");

        txtMember1.setEditable(false);
        txtMember1.setBackground(new java.awt.Color(0, 102, 204));
        txtMember1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMember1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMember1.setText(" <ค้นหาสมาชิก> ");
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
        txtMember2.setText(": แต้มสะสม");
        txtMember2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelMemberLayout = new javax.swing.GroupLayout(jPanelMember);
        jPanelMember.setLayout(jPanelMemberLayout);
        jPanelMemberLayout.setHorizontalGroup(
            jPanelMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMemberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMember1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMember2))
        );
        jPanelMemberLayout.setVerticalGroup(
            jPanelMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMember1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMember2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtDisplayDiscount.setEditable(false);
        txtDisplayDiscount.setBackground(new java.awt.Color(255, 153, 153));
        txtDisplayDiscount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtDisplayDiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDisplayDiscount.setText("Discount");
        txtDisplayDiscount.setBorder(null);
        txtDisplayDiscount.setCaretColor(new java.awt.Color(255, 255, 255));

        txtDiscount.setEditable(false);
        txtDiscount.setBackground(new java.awt.Color(255, 153, 153));
        txtDiscount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiscount.setText("0.00");
        txtDiscount.setBorder(null);
        txtDiscount.setCaretColor(new java.awt.Color(255, 255, 255));

        btnPrintKic.setBackground(new java.awt.Color(0, 0, 204));
        btnPrintKic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrintKic.setForeground(new java.awt.Color(255, 255, 255));
        btnPrintKic.setText("Print - ON");
        btnPrintKic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintKicActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ค้างชำระ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("เครดิต");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("วงเงิน");

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(lbCreditMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCreditAmt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(lbCredit)
                    .addComponent(lbCreditMoney)
                    .addComponent(lbCreditAmt))
                .addContainerGap())
        );

        buttonGroup1.add(btnLangTH);
        btnLangTH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLangTH.setSelected(true);
        btnLangTH.setText("TH");
        btnLangTH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnLangTHItemStateChanged(evt);
            }
        });

        buttonGroup1.add(btnLangEN);
        btnLangEN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLangEN.setText("EN");
        btnLangEN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnLangENItemStateChanged(evt);
            }
        });

        jMenuBar11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar11MouseClicked(evt);
            }
        });

        MMainMenu1.setText("เมนูหลักระบบ (Main Menu)         ");
        MMainMenu1.setDelay(100);
        MMainMenu1.setDoubleBuffered(true);
        MMainMenu1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MMainMenu1.setRequestFocusEnabled(false);
        MMainMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MMainMenuActionPerformed(evt);
            }
        });

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem3.setText("ย้ายรายการ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem3);

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem7.setText("คืนเงินมัดจำเป็นเงินสด");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem8.setText("ยกเลิกการคืนเงินมัดจำ");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem8);
        MMainMenu1.add(jSeparator1);

        MCancelArPayment2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MCancelArPayment2.setText("รับชำระจากลูกหนี้ภายนอก");
        MCancelArPayment2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCancelArPayment2ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MCancelArPayment2);

        MCancelArPayment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MCancelArPayment1.setText("ยกเลิกการรับชำระจากลูกหนี้ภายนอก");
        MCancelArPayment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCancelArPayment1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MCancelArPayment1);
        MMainMenu1.add(jSeparator2);

        MAddNewAccr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAddNewAccr1.setText("ปรับปรุงรายละเอียดลูกหนี้ภายนอก");
        MAddNewAccr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAddNewAccr1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MAddNewAccr1);

        MRepArNotPayment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepArNotPayment1.setText("รายงานลูกหนี้ภายนอกค้างชำระ");
        MRepArNotPayment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepArNotPayment1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepArNotPayment1);

        MRepArHistory1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepArHistory1.setText("ประวัติการซื้อของลูกหนี้ภายนอก");
        MRepArHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepArHistory1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepArHistory1);
        MMainMenu1.add(jSeparator10);

        MAddNewMember1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAddNewMember1.setForeground(new java.awt.Color(255, 51, 51));
        MAddNewMember1.setText("ปรับปรุงข้อมูลสมาชิก (Member)ส่วนนี้อาจจะเอาออกเพราะใน CRM มีอยู่แล้ว");
        MAddNewMember1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAddNewMember1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MAddNewMember1);

        MRepMemberHistory1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepMemberHistory1.setText("ประวัติการซื้อของสมาชิก");
        MRepMemberHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepMemberHistory1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepMemberHistory1);
        MMainMenu1.add(jSeparator11);

        MRepInvCash1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepInvCash1.setText("พิมพ์ใบกำกับภาษี/ใบเสร็จรับเงิน");
        MRepInvCash1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepInvCash1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepInvCash1);

        MRepInvAr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepInvAr1.setForeground(new java.awt.Color(255, 51, 51));
        MRepInvAr1.setText("พิมพ์ใบกำกับภาษี/ใบแจ้งหนี้(ยกเลิกหัวข้อนี้)");
        MRepInvAr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepInvAr1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepInvAr1);

        MHeaderBill1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MHeaderBill1.setText("พิมพ์หัวกระดาษ/ท้ายกระดาษ");
        MHeaderBill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MHeaderBill1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MHeaderBill1);
        MMainMenu1.add(jSeparator12);

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem4.setText("กำหนด Header TAB");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem4);

        jMenuBar11.add(MMainMenu1);

        jMenu2.setText("กำหนดรายละเอียดสินค้า     ");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jCheckBoxMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, 0));
        jCheckBoxMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("การกำหนดรายละเอียด Option");
        jCheckBoxMenuItem3.setActionCommand("การกำหนดรายละเอียด Option\nปลดล็อกการวอย Void Unlock");
        jCheckBoxMenuItem3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItem3ItemStateChanged(evt);
            }
        });
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem3);

        jMenuBar11.add(jMenu2);

        jMenu1.setText("ช่วยเหลือ (Help)     ");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jCheckBoxMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, 0));
        jCheckBoxMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("ใช้คีย์ลัด ไม่แสดงปุ่มคำสั่ง");
        jCheckBoxMenuItem1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItem1ItemStateChanged(evt);
            }
        });
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, 0));
        jCheckBoxMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("ซ่อน Number Pad");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem2);

        jMenuBar11.add(jMenu1);

        setJMenuBar(jMenuBar11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDisplayDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiscount))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanelMember, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPrintKic, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPluCode, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLangTH)
                                .addGap(18, 18, 18)
                                .addComponent(btnLangEN)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLangTH)
                            .addComponent(btnLangEN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPrintKic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPluCode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDisplayDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //คำสั่งกำหนดเบอร์โต๊ะ

    private void tableOpened() {
        if (!txtTable.getText().trim().equals("")) {
            txtTableOnExit();
            showSum();
            if (CONFIG.getP_EmpUse().equals("Y")) {

            } else {
                if (PublicVar.TableRec_TCustomer == 0) {
                    txtCust.setEditable(true);
                    txtCust.requestFocus();
                    txtCust.selectAll();
                } else {
                    txtPluCode.setEditable(true);
                    txtPluCode.setBackground(Color.WHITE);
                    txtPluCode.requestFocus();
                }
            }
        } else {
            if (txtTable.getText().trim().length() > 5) {
                MSG.ERR(this, "หมายเลขโต๊ะต้องกำหนดเป็นตัวเลข 0-9 เท่านั้น และกำหนดได้ไม่เกิน 5 ตัวอักษร...");
                txtTable.selectAll();
                txtTable.requestFocus();
            }
        }
    }

private void txtTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTableKeyPressed
//    String[] CheckTOrder = CheckTOrder();
//    if(CheckTOrder[0].equals("N")){

    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tableOpened();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        txtTable.setText("");
        bntlogoffuserClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
        showFloorPlan();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        showTableAvialble();
    } else if (evt.getKeyCode() == KeyEvent.VK_F8) {
        showPaidIn();
    } else if (evt.getKeyCode() == KeyEvent.VK_F9) {
        showPaidOut();
    } else if (evt.getKeyCode() == KeyEvent.VK_F6) {
        showBillDuplicate();
    } else if (evt.getKeyCode() == KeyEvent.VK_F7) {
        showRefundBill();
    } else if (evt.getKeyCode() == KeyEvent.VK_F12) {
        showPayAR();
    }
//    }

}//GEN-LAST:event_txtTableKeyPressed

    private void tblShowPluShow(String P_Table) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String LoadBalance = "select * from balance "
                    + "where r_table='" + P_Table + "' "
                    + "order by r_index,r_etd";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(LoadBalance);
            int RowCount = model.getRowCount();
            for (int i = 0; i < RowCount; i++) {
                model.removeRow(0);
            }

            while (rs.next()) {
                String r_linkindex = rs.getString("r_linkindex");
                String r_index = rs.getString("r_index");
                String voidStr = rs.getString("r_void");
//                String PName = PUtility.DataFullR(ThaiUtil.ASCII2Unicode(rs.getString("r_pname")), 30);
                String PName = ThaiUtil.ASCII2Unicode(rs.getString("r_pname"));

                if (voidStr.equals("V")) {
                    PName = "<html><strike><font color=red>" + PName + "</font></strike></html>";
                }
                if (r_index.equals(r_linkindex)) {
                    PName = "<html><b><font color=blue>" + PName + "</font></b></html>";
                }
                String empName = "";
                String employ = rs.getString("r_emp");
                String sqlGetEmployName = "select name from employ where code='" + employ + "'";
                ResultSet rsGetEmpName = mysql.getConnection().createStatement().executeQuery(sqlGetEmployName);
                if (rsGetEmpName.next()) {
                    empName = ThaiUtil.ASCII2Unicode(rsGetEmpName.getString("name"));
                }
                Object[] input = {
                    rs.getString("r_plucode"),
                    PName,
                    dc1.format(rs.getDouble("r_quan")),
                    dc1.format(rs.getDouble("r_price")),
                    dc1.format(rs.getDouble("r_total")),
                    rs.getString("r_void"),
                    rs.getString("r_etd"),
                    rs.getString("r_kicprint"),
                    rs.getString("r_kicok"),
                    rs.getString("r_prtype"),
                    rs.getString("r_index"),
                    rs.getString("r_pause"),
                    empName,
                    rs.getString("r_time"),};
                model.addRow(input);
                //วนหาข้อความพิเศษ
                String str = "";
                for (int i = 1; i <= 9; i++) {
                    String R_Opt = rs.getString("R_Opt" + i);
                    if (R_Opt == null) {
                        R_Opt = "";
                    }
                    if (!R_Opt.equals("")) {
                        if (str.equals("")) {
                            if (voidStr.equals("V")) {
                                str += "  - ";
                            } else {
                                str += "  + ";
                            }
                        }
                        str += ThaiUtil.ASCII2Unicode(rs.getString("R_Opt" + i)) + ",";
                    }
                }
                if (!str.equals("")) {
                    if (str.indexOf("-") != -1) {
                        str = "<html><font color=red>" + str + "</font></html>";
                    }
                    model.addRow(new Object[]{"", str});
                }

                //ถ้าเป็นสินค้า Set
            }

            RowCount = model.getRowCount();
            showCell(RowCount - 1, 0);
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        showSum();
    }

//    private void loadDataFromBalance(String P_Table) {
//        /**
//         * * OPEN CONNECTION **
//         */
//        MySQLConnect mysql = new MySQLConnect();
//        mysql.open();
//        try {
//            String sql = "select * from balance "
//                    + "where r_table='" + P_Table + "' "
//                    + "order by r_index";
////            Statement stmt = mysql.getConnection().createStatement();
//            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
//
//            int size = model.getRowCount();
//            for (int i = 0; i <= size - 1; i++) {
//                model.removeRow(0);
//            }
//
//            while (rs.next()) {
//                String r_index = rs.getString("r_index");
//                String r_linkindex = rs.getString("r_linkindex");
//                String plucode = rs.getString("r_plucode");
//                String PName = ThaiUtil.ASCII2Unicode(rs.getString("r_pname"));
//                double quan = rs.getDouble("r_quan");
//                double price = rs.getDouble("r_price");
//                double total = rs.getDouble("r_total");
//                String voidStr = rs.getString("r_void");
//                String etd = rs.getString("r_etd");
//                String kicprint = rs.getString("r_kicprint");
//                String kicok = rs.getString("r_kicok");
//                String prtype = rs.getString("r_prtype");
//                String pause = rs.getString("r_pause");
//
//                if (voidStr.equals("V")) {
//                    PName = "<html><strike><font color=red>" + PName + "</font></strike></html>";
//                }
//                if (r_index.equals(r_linkindex)) {
//                    PName = "<html><b><font color=blue>" + PName + "</font></b></html>";
//                }
////                if (r_linkindex.equals("")) {
////                    PName = "<html><b><font color=green>" + PName + "</font></b></html>";
////                }
//                Object[] input = {
//                    plucode, PName, quan, price, total, voidStr, etd, kicprint, kicok, prtype, r_index, pause};
//
//                model.addRow(input);
//
//                String str = "";
//                for (int i = 1; i <= 9; i++) {
//                    if (!rs.getString("R_Opt" + i).equals("")) {
//                        if (voidStr.equals("V")) {
//                            str += "  - ";
//                        } else {
//                            str += "  + ";
//                        }
//                        str += ThaiUtil.ASCII2Unicode(rs.getString("R_Opt" + i)) + ",";
//                    }
//                }
//                if (!str.equals("")) {
//                    if (str.indexOf("-") != -1) {
//                        str = "<html><font color=red>" + str + "</font></html>";
//                    }
//                    model.addRow(new Object[]{"", str});
//                }
////                updateProSerTable(tableNo, memberBean);
//            }
//            size = model.getRowCount();
//            showCell(size - 1, 0);
//            rs.close();
////            stmt.close();
//            txtPluCode.requestFocus();
//        } catch (SQLException e) {
//            MSG.ERR(e.getMessage());
//            
//        } finally {
//            mysql.close();
//        }
//
//        showSum();
//    }
    private void showSum() { //คำสั่งกำหนดให้ไปโชว์ค่ายอดเงินในส่วนต่างๆ
        //show sum
        TableFileControl tCon = new TableFileControl();
        TableFileBean tfBean = tCon.getData(tableNo);
        double totalDiscount = 0.00;
        totalDiscount = tfBean.getProDiscAmt() + tfBean.getSpaDiscAmt() + tfBean.getCuponDiscAmt()
                + tfBean.getFastDiscAmt() + tfBean.getEmpDiscAmt() + tfBean.getTrainDiscAmt()
                + tfBean.getSubDiscAmt() + tfBean.getDiscBath() + tfBean.getItemDiscAmt();
        if (CONFIG.getP_VatType().equals("I")) {
//            lbTotalAmount.setText("" + dc1.format(NumberControl.UP_DOWN_NATURAL_BAHT(tfBean.getNetTotal())));
            if (!CONFIG.getP_PayBahtRound().equals("O")) {
                lbTotalAmount.setText("" + dc1.format(NumberControl.UP_DOWN_NATURAL_BAHT((tfBean.getNetTotal()))));
            } else {
                lbTotalAmount.setText("" + dc1.format((tfBean.getNetTotal())));
            }
        }
        if (CONFIG.getP_VatType().equals("E")) {
            double vat = (tfBean.getTAmount() - totalDiscount + tfBean.getServiceAmt()) * 7 / 100;
            lbTotalAmount.setText(
                    dc1.format(((tfBean.getTAmount() - totalDiscount + tfBean.getServiceAmt()) + vat))
            );
        }

        jLabel1.setText("จำนวนรายการสินค้า: " + tCon.getItemCount(tableNo) + " รายการ");
        txtCust.setText("" + tfBean.getTCustomer());

        // for member
        String MemberCode = tfBean.getMemCode();
//        String MemberNameThai = tfBean.getMemName();
        if (MemberCode != null && !MemberCode.equals("")) {
            Value.MemberAlready = true;
            memberBean = MemberBean.getMember(MemberCode);
            txtMember1.setText(memberBean.getMember_NameThai());
            txtMember2.setText("แต้มสะสม : " + memberBean.getMember_TotalScore() + " แต้ม");
        }
    }

private void txtCustKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtPluCode.setEditable(true);
        txtPluCode.requestFocus();
        //txtCust.setSelectionEnd(0);

        txtCustOnExit();
    }
}//GEN-LAST:event_txtCustKeyPressed
    //คำสั่งกำหนดจำนวนลูกค้า
    private void txtCustOnExit() {
        if (PUtility.ChkIntValue(txtCust.getText())) {
            try {
                int TableRec_TCustomer = Integer.parseInt(txtCust.getText());
                if (TableRec_TCustomer > 999) {
                    MSG.ERR("จำนวนลูกค้าป้อนได้ไม่เกิน 999 คน...");
                    txtCust.requestFocus();
                    txtCust.equals("1");
                } else {
                    if (txtCust.getText().equals("0")) {
                        updateCustomerCount(TableRec_TCustomer = 1);
                        txtCust.setText("1");
                    }
                    updateCustomerCount(TableRec_TCustomer);
                    txtCust.setEditable(false);
                    txtPluCode.setEditable(true);
                    txtPluCode.requestFocus();
                }
            } catch (NumberFormatException e) {
                MSG.ERR(this, "จำนวนลูกค้าป้อนได้ไม่เกิน 999 คน...");
                PublicVar.TableRec_TCustomer = 1;
                txtCust.requestFocus();
            }
        } else {
            MSG.ERR("กรุณาป้อนจำนวนลูกค้า เป็นตัวเลขเท่านั้น...");
            txtCust.requestFocus();
        }
    }

private void txtPluCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPluCodeKeyPressed
    //คำสั่ง Enter,ESCAPE
    if (!isTakeOrder()) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pCodeEnter();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //ProcessErrorColect();
        } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
            FindProduct find = new FindProduct(null, true);
            find.setVisible(true);

            if (!find.getPCode().equals("")) {
                txtPluCode.setText(txtPluCode.getText() + find.getPCode());

            }
        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            showHoldTable();
        } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
            showCheckBill();
        } else if (evt.getKeyCode() == KeyEvent.VK_F6) {
            showBillCheck();
        } else if (evt.getKeyCode() == KeyEvent.VK_F9) {
            showCustomerInput();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            selectedTableBalance();
        } else if (evt.getKeyCode() == KeyEvent.VK_F11) {
            showMember();
        }
    }

}//GEN-LAST:event_txtPluCodeKeyPressed

private void tblShowBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowBalanceKeyPressed
    //คำสั่ง Enter,ESCAPE
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        txtPluCode.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            Object r_index = model.getValueAt(row, 10);
            Object voidMsg = model.getValueAt(row, 5);
            String strVoid;
            if (voidMsg != null) {
                strVoid = voidMsg.toString();
            } else {
                strVoid = "";
            }

            if (r_index != null && !strVoid.equalsIgnoreCase("V")) {

                selectedOptionBill();

                txtPluCode.requestFocus();
            }
        }
    }

}//GEN-LAST:event_tblShowBalanceKeyPressed

private void MMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MMainMenuActionPerformed

}//GEN-LAST:event_MMainMenuActionPerformed

private void MAddNewAccr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAddNewAccr1ActionPerformed
    PublicVar.TempUserRec = PublicVar.TUserRec;
    if (PublicVar.TUserRec.Sale7.equals("Y")) {
        AddNewArCustomer fmt = new AddNewArCustomer(null, true);
        fmt.setVisible(true);
    } else {
        GetUserAction getuser = new GetUserAction(null, true);
        getuser.setVisible(true);

        if (!PublicVar.ReturnString.equals("")) {
            String loginname = PublicVar.ReturnString;
            UserRecord supUser = new UserRecord();
            if (supUser.GetUserAction(loginname)) {
                if (supUser.Sale7.equals("Y")) {
                    PublicVar.TUserRec = supUser;
                    AddNewArCustomer fmt = new AddNewArCustomer(null, true);
                    fmt.setVisible(true);
                } else {
                    MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                }
            } else {
                MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
            }
        }
    }
    PublicVar.TUserRec = PublicVar.TempUserRec;
}//GEN-LAST:event_MAddNewAccr1ActionPerformed

private void MRepArNotPayment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepArNotPayment1ActionPerformed
    PublicVar.TempUserRec = PublicVar.TUserRec;
    if (PublicVar.TUserRec.Sale8.equals("Y")) {
        ArNotPay frm = new ArNotPay(null, true);
        frm.setVisible(true);
    } else {
        GetUserAction getuser = new GetUserAction(null, true);
        getuser.setVisible(true);

        if (!PublicVar.ReturnString.equals("")) {
            String loginname = PublicVar.ReturnString;
            UserRecord supUser = new UserRecord();
            if (supUser.GetUserAction(loginname)) {
                if (supUser.Sale8.equals("Y")) {
                    PublicVar.TUserRec = supUser;
                    ArNotPay frm = new ArNotPay(null, true);
                    frm.setVisible(true);
                } else {
                    MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                }
            } else {
                MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
            }
        }
    }

    PublicVar.TUserRec = PublicVar.TempUserRec;

}//GEN-LAST:event_MRepArNotPayment1ActionPerformed

private void MRepArHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepArHistory1ActionPerformed
    PublicVar.TempUserRec = PublicVar.TUserRec;
    if (PublicVar.TUserRec.Sale9.equals("Y")) {
        ArHistory frm = new ArHistory(null, true);
        frm.setVisible(true);
    } else {
        GetUserAction getuser = new GetUserAction(null, true);
        getuser.setVisible(true);

        if (!PublicVar.ReturnString.equals("")) {
            String loginname = PublicVar.ReturnString;
            UserRecord supUser = new UserRecord();
            if (supUser.GetUserAction(loginname)) {
                if (supUser.Sale9.equals("Y")) {
                    PublicVar.TUserRec = supUser;
                    ArHistory frm = new ArHistory(null, true);
                    frm.setVisible(true);
                } else {
                    MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                }
            } else {
                MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
            }
        }
    }
    PublicVar.TUserRec = PublicVar.TempUserRec;
}//GEN-LAST:event_MRepArHistory1ActionPerformed

private void MAddNewMember1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAddNewMember1ActionPerformed
    AddMemberMaster master = new AddMemberMaster(null, true);
    master.setVisible(true);
}//GEN-LAST:event_MAddNewMember1ActionPerformed

private void MRepMemberHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepMemberHistory1ActionPerformed
    RepMember frm = new RepMember(null, true);
    frm.setVisible(true);
}//GEN-LAST:event_MRepMemberHistory1ActionPerformed

private void MHeaderBill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MHeaderBill1ActionPerformed
    if (Value.useprint) {
        PPrint prn = new PPrint();
        prn.printHeaderBill();
    }
}//GEN-LAST:event_MHeaderBill1ActionPerformed

private void MRepInvCash1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepInvCash1ActionPerformed
    PrintInv1 frm = new PrintInv1(null, true);
    frm.setVisible(true);
}//GEN-LAST:event_MRepInvCash1ActionPerformed

private void MRepInvAr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepInvAr1ActionPerformed
    PrintInv2 frm = new PrintInv2(null, true);
    frm.setVisible(true);
}//GEN-LAST:event_MRepInvAr1ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    SetHeaderMenu s = new SetHeaderMenu(null, true);
    s.setVisible(true);

    loadHeaderMenu();
}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void clearTable() {
        tblShowBalance.setBackground(null);
        txtTableOnEnter();
        changeSaleType("E");
        txtTable.setText("");
        txtTable.requestFocus();
    }

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        CancelCashBack c = new CancelCashBack(null, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CashBackDialog c = new CashBackDialog(null, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void MCancelArPayment2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCancelArPayment2ActionPerformed
        ARPayment Ar = new ARPayment(null, true);
        Ar.setVisible(true);

    }//GEN-LAST:event_MCancelArPayment2ActionPerformed

    private void MCancelArPayment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCancelArPayment1ActionPerformed
        if (PublicVar.TUserRec.Sale7.equals("Y")) {
            if (PUtility.CheckSaleDateOK()) {
                cancelArPaymentClick();
            }
        } else {
            GetUserAction getuser = new GetUserAction(null, true);
            getuser.setVisible(true);

            if (!PublicVar.ReturnString.equals("")) {
                String loginname = PublicVar.ReturnString;
                UserRecord supUser = new UserRecord();
                if (supUser.GetUserAction(loginname)) {
                    if (supUser.Sale7.equals("Y")) {
                        PublicVar.TUserRec = supUser;
                        if (PUtility.CheckSaleDateOK()) {
                            cancelArPaymentClick();
                        }
                    } else {
                        MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                    }
                } else {
                    MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                }
            }
        }
    }//GEN-LAST:event_MCancelArPayment1ActionPerformed

    private void txtShowETDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtShowETDMouseClicked
        if (txtShowETD.getText().equals("E")) {
            txtShowETD.setText("T");
            changeSaleType("T");
        } else if (txtShowETD.getText().equals("T")) {
            txtShowETD.setText("D");
            changeSaleType("D");
        } else if (txtShowETD.getText().equals("D")) {
            txtShowETD.setText("E");
            changeSaleType("E");
        }
    }//GEN-LAST:event_txtShowETDMouseClicked

    private void tblShowBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblShowBalanceMouseClicked
        if (evt.getClickCount() == 2) {
            selectedOptionBill();
        }
    }//GEN-LAST:event_tblShowBalanceMouseClicked

    private void btnHoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoldActionPerformed
        showHoldTable();
//        ClearApp();
    }//GEN-LAST:event_btnHoldActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        if (btnClickPrintKic == true) {
            String sqlTurnPrintKicOff = "update balance set r_kic='0' where r_kicprint<>'P';";
            try {
                MySQLConnect c = new MySQLConnect();
                c.open();
                c.getConnection().createStatement().executeUpdate(sqlTurnPrintKicOff);
                c.close();
            } catch (Exception e) {
                
            }
        }
        if (lbTotalAmount.getText().equals("0.00")) {
            JOptionPane.showMessageDialog(this, "ไม่สามารถชำระเงินที่มูลค่าเป็น 0 ได้");
        } else {
            showCheckBill();

//            loadDataFromBalance(tableNo);
//            ClearApp();
        }

    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnSplitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSplitActionPerformed
        showSplitBill();

    }//GEN-LAST:event_btnSplitActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cancelItemBeforeHold();

    }//GEN-LAST:event_btnCancelActionPerformed

    private void jCheckBoxMenuItem1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ItemStateChanged
        //panelMenuFunction.setVisible(jCheckBoxMenuItem1.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItem1ItemStateChanged

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed

    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void txtTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTableFocusGained
        if (!Value.TableSelected.equals("")) {
            txtTable.setText(tableNo);
            Value.TableSelected = "";
        }
    }//GEN-LAST:event_txtTableFocusGained

    private void txtPluCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPluCodeFocusGained
        txtPluCode.setEditable(true);
        txtPluCode.requestFocus();
    }//GEN-LAST:event_txtPluCodeFocusGained

    boolean isSelected = false;

    private void txtCustFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCustFocusGained
        isSelected = true;
    }//GEN-LAST:event_txtCustFocusGained

    private void txtPluCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPluCodeMouseClicked
        if (evt.getClickCount() == 2) {

        }
    }//GEN-LAST:event_txtPluCodeMouseClicked

    private void txtCustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustMouseClicked
        //showCustomerInput();
        CustomerCountDialog ccd = new CustomerCountDialog(null, true, txtTable.getText(), txtShowETD.getText());
        ccd.setVisible(true);

        showSum();
    }//GEN-LAST:event_txtCustMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        MGRButtonMenu mgr = new MGRButtonMenu(null, true, buttonName, buttonIndex);
        mgr.setVisible(true);
        setupMenu();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (tableNo.indexOf("T") != -1) {
            txtShowETD.setText("T");
            changeSaleType("T");
            txtTypeDesc.setText(SALE_TAKE_AWAY);
        } else if (tableNo.indexOf("DE") != -1) {
            txtShowETD.setText("D");
            changeSaleType("D");
            txtTypeDesc.setText(SALE_Delivery);
        } else if (tableNo.indexOf("E") != -1) {
            txtShowETD.setText("E");
            changeSaleType("E");
            txtTypeDesc.setText(SALE_DINE_IN);
        }
        pMenu2.setVisible(false);
        pMenu3.setVisible(false);
        pMenu4.setVisible(false);
        pMenu5.setVisible(false);
        pMenu6.setVisible(false);

        isTakeOrder();

        //MSG.WAR(txtTypeDesc.getText());
        if (!txtTypeDesc.getText().equals(SALE_DINE_IN)) {
            CustomerName ccd = new CustomerName(null, true, txtTable.getText(), txtShowETD.getText());
            ccd.setVisible(true);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jMenuBar11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar11MouseClicked
        if (MMainMenu1.isVisible() == false && jMenu1.isVisible() == false && jMenu2.isVisible() == false) {
            MMainMenu1.setVisible(true);
            jMenu1.setVisible(true);
            jMenu2.setVisible(true);
        } else {
            MMainMenu1.setVisible(false);
            jMenu1.setVisible(false);
            jMenu2.setVisible(false);
        }
    }//GEN-LAST:event_jMenuBar11MouseClicked

    private void bntPrintCheckBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintCheckBillActionPerformed
        if (btnClickPrintKic == true) {
            String sqlTurnPrintKicOff = "update balance set r_kic='0' where r_kicprint<>'P';";
            try {
                MySQLConnect c = new MySQLConnect();
                c.open();
                c.getConnection().createStatement().executeUpdate(sqlTurnPrintKicOff);
                c.close();
            } catch (Exception e) {
                MSG.ERR(e.toString());
            }
        }

        kichenPrintAfterPrintCheck();
        printBillCheck();
//        ClearApp();
    }//GEN-LAST:event_bntPrintCheckBillActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        MGRButtonSetup mgr = new MGRButtonSetup(null, true, buttonName, buttonIndex);
        mgr.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem3ItemStateChanged

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
        OptionMenuSet browse = new OptionMenuSet(null, true);
        browse.setVisible(true);
    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new KeyBoardDialog(null, true, 4).get(txtPluCode, 4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustActionPerformed

    }//GEN-LAST:event_txtCustActionPerformed

    private void txtMember1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMember1MouseClicked
        MemberDialog MBD = new MemberDialog(this, true, tableNo);
        MBD.setVisible(true);
        this.memberBean = MemberBean.getMember(MBD.getMemCode());
        updateProSerTable(tableNo, memberBean);
        if (!memberBean.getMember_Code().equals("")) {
            txtMember1.setText(memberBean.getMember_NameThai());
            txtMember2.setText(QtyIntFmt.format(memberBean.getMember_TotalScore()));
//            loadDataFromBalance(tableNo);
            tblShowPluShow(txtTable.getText());
        }

    }//GEN-LAST:event_txtMember1MouseClicked

    private void tbpMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpMainMouseClicked
        clearHistory();
        addHistory(tbpMain.getSelectedIndex());//
    }//GEN-LAST:event_tbpMainMouseClicked

    private void txtTypeDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTypeDescMouseClicked
        if (txtTypeDesc.getText().equals(SALE_DINE_IN)) {
            txtShowETD.setText("T");
            changeSaleType("T");
            txtTypeDesc.setText(SALE_TAKE_AWAY);
        } else if (txtTypeDesc.getText().equals(SALE_TAKE_AWAY)) {
            txtShowETD.setText("D");
            changeSaleType("D");
            txtTypeDesc.setText(SALE_Delivery);
        } else if (txtTypeDesc.getText().equals(SALE_Delivery)) {
            txtShowETD.setText("E");
            changeSaleType("E");
            txtTypeDesc.setText(SALE_DINE_IN);
        }
    }//GEN-LAST:event_txtTypeDescMouseClicked

    private void btnPrintKicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintKicActionPerformed
        String Check = btnPrintKic.getText();
        if (Check.equals("Print - OFF")) {
            btnClickPrintKic = false;
            btnPrintKic.setText("Print - ON");
            btnPrintKic.setBackground(Color.blue);
        }
        if (Check.equals("Print - ON")) {
            btnClickPrintKic = true;
            btnPrintKic.setText("Print - OFF");
            btnPrintKic.setBackground(Color.red);
        }
    }//GEN-LAST:event_btnPrintKicActionPerformed

    private void txtMember1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMember1ActionPerformed
        MemberDialog MBD = new MemberDialog(this, true, tableNo);
        MBD.setVisible(true);
    }//GEN-LAST:event_txtMember1ActionPerformed

    private void btnLangTHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnLangTHItemStateChanged
        PublicVar.languagePrint = "TH";
    }//GEN-LAST:event_btnLangTHItemStateChanged

    private void btnLangENItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnLangENItemStateChanged
        PublicVar.languagePrint = "EN";
    }//GEN-LAST:event_btnLangENItemStateChanged

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        MoveItemDialog move = new MoveItemDialog(null, true, txtTable.getText());
        move.setVisible(true);
        tblShowPluShow(txtTable.getText());

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void cancelArPaymentClick() {
        PublicVar.TempUserRec = PublicVar.TUserRec;

        if (PublicVar.TUserRec.Sale6.equals("Y")) {
            CancelArPayment frm = new CancelArPayment(null, true);
            frm.setVisible(true);
        } else {
            GetUserAction getuser = new GetUserAction(null, true);
            getuser.setVisible(true);

            if (!PublicVar.ReturnString.equals("")) {
                String loginname = PublicVar.ReturnString;
                UserRecord supUser = new UserRecord();
                if (supUser.GetUserAction(loginname)) {
                    if (supUser.Sale6.equals("Y")) {
                        PublicVar.TUserRec = supUser;
                        CancelArPayment frm = new CancelArPayment(null, true);
                        frm.setVisible(true);
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

    private void bntcheckbillClick() {
        if (!chkEJPath()) {
            return;
        }
        PublicVar.ErrorColect = false;
        PublicVar.ProcessType = "1"; //For Check Bill

        CheckBill frm = new CheckBill(null, true, txtTable.getText(), memberBean, "", "");
        frm.setVisible(true);

        if (PublicVar.SubTotalOK) {
            bntHoldTableClick();
            clearTable();
            tbpMain.setSelectedIndex(0);

            showTableOpen();
        }
    }

    private void bntPaymentClick() {
        if (!chkEJPath()) {
            return;
        }

        PublicVar.SubTotalOK = false;

        //visible MainSale
        setVisible(false);
        CheckBill frm = new CheckBill(null, true, txtTable.getText(), memberBean, "", "");
        frm.setVisible(true);
        if (PublicVar.SubTotalOK) {
            initScreen();
            clearTable();
            txtTable.setText("");
            tbpMain.setSelectedIndex(0);
            Value.TableSelected = "";
//            dispose();
        } else {
//            loadDataFromBalance(tableNo);
            tblShowPluShow(txtTable.getText());
            showSum();
            setVisible(true);
        }
    }

    private void bntVoidClick() {
        String Name = "";
        String Name1 = "";
        int row = getSelectedRowIndex();
        if (row == -1) {
            return;
        }
        String R_Index = model.getValueAt(row, 10).toString();
        boolean isPermit = false;
        //check permission
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select Username, Sale3,Name "
                    + "from posuser "
                    + "where username='" + Value.USERCODE + "' "
                    + "and Sale3='Y'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isPermit = true;
                Name = ThaiUtil.ASCII2Unicode(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }

        if (isPermit) {//มีสิทธิ์ Void
            VoidPopupDialog voidD = new VoidPopupDialog(null, true, txtTable.getText(), memberBean);
            voidD.setVisible(true);
            if (!VoidPopupDialog.VOID_MSG[0].equals("")) {

                //check link r (26/02/2016 15:12)
                boolean hasValue = false;
                try {
                    String sql = "select R_Index, R_LinkIndex from balance "
                            + "where R_LinkIndex='" + R_Index + "'";
                    Statement stmt = mysql.getConnection().createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        hasValue = true;
                        procVoid(rs.getString("R_Index"), VoidPopupDialog.VOID_MSG[1], Value.USERCODE);
                    }

                    rs.close();
                    stmt.close();
                } catch (Exception e) {
                    MSG.ERR(e.getMessage());
                    
                }

                if (!hasValue) {
                    procVoid(R_Index, VoidPopupDialog.VOID_MSG[1], Value.USERCODE);
                }
                showCell(row, 0);
            }
        } else {
            if (!PublicVar.TableRec_PrintChkBill.equals("Y")) {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale3.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            VoidPopupDialog voidD = new VoidPopupDialog(null, true, txtTable.getText(), memberBean);
                            voidD.setVisible(true);
                            if (!VoidPopupDialog.VOID_MSG[0].equals("")) {
                                //check link r (28/02/2016 15:12)
                                boolean hasValue = false;
                                try {
                                    String sql = "select R_Index, R_LinkIndex from balance "
                                            + "where R_LinkIndex='" + R_Index + "'";
                                    Statement stmt = mysql.getConnection().createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    while (rs.next()) {
                                        hasValue = true;
                                        procVoid(rs.getString("R_Index"), VoidPopupDialog.VOID_MSG[1], loginname);
                                        txtDiscount.setText("- " + BalanceControl.GetDiscount(txtTable.getText()));
                                    }

                                    rs.close();
                                    stmt.close();
                                } catch (Exception e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }

                                if (!hasValue) {
                                    procVoid(R_Index, VoidPopupDialog.VOID_MSG[1], loginname);
                                    txtDiscount.setText("- " + BalanceControl.GetDiscount(txtTable.getText()));
                                }
                                showCell(row, 0);
                            }
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale4.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            VoidPopupDialog voidD = new VoidPopupDialog(null, true, txtTable.getText(), memberBean);
                            voidD.setVisible(true);
                            if (!VoidPopupDialog.VOID_MSG[0].equals("")) {

                                //check link r (28/02/2016 15:12)
                                boolean hasValue = false;
                                try {
                                    String sql = "select R_Index, R_LinkIndex from balance "
                                            + "where R_LinkIndex='" + R_Index + "'";
                                    Statement stmt = mysql.getConnection().createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    while (rs.next()) {
                                        hasValue = true;
                                        procVoid(rs.getString("R_Index"), VoidPopupDialog.VOID_MSG[1], loginname);
                                    }

                                    rs.close();
                                    stmt.close();
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }

                                if (!hasValue) {
                                    procVoid(R_Index, VoidPopupDialog.VOID_MSG[1], loginname);
                                }
                                showCell(row, 0);
                            }
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
        }

        mysql.close();
    }

    private void procVoid(String RIndex, String voidMsg, String LoginName) {
        BalanceControl bc = new BalanceControl();
        BalanceBean bean = bc.getBalanceIndex(txtTable.getText(), RIndex);

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();

        if (bean.getR_Void().equals("V")) {
            bean.setR_Void("");
            bean.setR_VoidUser("");
            bean.setR_VoidTime("");
            bean.setR_DiscBath(0.00);

            if (PublicVar.Branch_Saveorder.equals("Y")) {
//                PrintSaveOrder(IndexRow) ;
            }

            String StkRemark;
            String DocNo;
            String StkCode = PUtility.GetStkCode();
            if (PublicVar.ChargeCode.equals("")) {
                StkRemark = "SAL";
                DocNo = "EV" + txtTable.getText() + "/" + Timefmt.format(new Date());
            } else {
                StkRemark = "FRE";
                if (PublicVar.ChargeDocNo.equals("")) {
                    DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
                    PublicVar.ChargeDocNo = DocNo;
                } else {
                    DocNo = PublicVar.ChargeDocNo;
                }
            }

            Date TDate = new Date();
            PUtility.ProcessStockOut(DocNo, StkCode, bean.getR_PluCode(), TDate,
                    StkRemark, bean.getR_Quan(), bean.getR_Total(), bean.getCashier(),
                    bean.getR_Stock(), bean.getR_Set(), bean.getR_Index(), "1");

            //ตัดสต็อกสินค้าที่มี Ingredent
            try {
                String sql1 = "select i.*,pdesc,PBPack "
                        + "from product p, pingredent i "
                        + "where p.pcode=i.pingcode "
                        + "and i.pcode='" + bean.getR_PluCode() + "' "
                        + "and PFix='L' and PStock='Y'";
                Statement stmt1 = mysql.getConnection().createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);
                while (rs.next()) {
                    String R_PluCode = rs.getString("PingCode");
                    double PBPack = rs.getDouble("PBPack");
                    if (PBPack <= 0) {
                        PBPack = 1;
                    }
                    double R_QuanIng = (rs.getDouble("PingQty") * bean.getR_Quan()) / PBPack;
                    double R_Total = 0;
                    System.out.println("ตัดสต็อก " + R_PluCode);
                    PUtility.ProcessStockOut(DocNo, StkCode, R_PluCode, TDate,
                            StkRemark, R_QuanIng, R_Total, bean.getCashier(),
                            "Y", "", "", "");//edit by  nathee 30/10/2016
                }

                rs.close();
                stmt1.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            }
        } else {
            bean.setR_Void("V");
            bean.setR_VoidUser(LoginName);
            bean.setR_VoidTime(Timefmt.format(new Date()));
            bean.setR_DiscBath(0.00);

            String StkCode = PUtility.GetStkCode();
            String StkRemark;
            String DocNo;
            if (PublicVar.ChargeCode.equals("")) {
                StkRemark = "SAL";
                DocNo = "V" + txtTable.getText() + "/" + Timefmt.format(new Date());
            } else {
                StkRemark = "FRE";
                if (PublicVar.ChargeDocNo.equals("")) {
                    DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
                    PublicVar.ChargeDocNo = DocNo;
                } else {
                    DocNo = PublicVar.ChargeDocNo;
                }
            }

            Date TDate = new Date();
            PUtility.ProcessStockOut(DocNo, StkCode, bean.getR_PluCode(), TDate, StkRemark, -1 * bean.getR_Quan(), -1 * bean.getR_Total(),
                    PublicVar.TUserRec.UserCode, bean.getR_Stock(), bean.getR_Set(), bean.getR_Index(), "1");

            //ตัดสต็อกสินค้าที่มี Ingredent
            try {
                String sql1 = "select i.*,pdesc,PBPack "
                        + "from product p, pingredent i "
                        + "where p.pcode=i.pingcode "
                        + "and i.pcode='" + bean.getR_PluCode() + "' "
                        + "and PFix='L' and PStock='Y'";
                Statement stmt = mysql.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql1);
                while (rs.next()) {
                    String R_PluCode = rs.getString("PingCode");
                    double PBPack = rs.getDouble("PBPack");
                    if (PBPack <= 0) {
                        PBPack = 1;
                    }
                    String pname = ThaiUtil.ASCII2Unicode(rs.getString("pdesc"));
                    double R_QuanIng = (rs.getDouble("PingQty") * bean.getR_Quan()) / PBPack;
                    double R_Total = 0;
                    System.out.println("ตัดสต็อก -" + R_PluCode + " " + pname + " จำนวน -" + R_QuanIng);
                    PUtility.ProcessStockOut(DocNo, StkCode, R_PluCode, TDate, StkRemark, -1 * R_QuanIng, R_Total,
                            PublicVar.TUserRec.UserCode, "Y", "", "", "");
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            }
        }

        //Update  Balance File For Void
        try {
            String updBalance = "update balance "
                    + "set r_void='" + bean.getR_Void() + "',"
                    + "cashier='" + Value.USERCODE + "',"
                    + "r_emp='" + bean.getR_Emp() + "',"
                    + "r_voiduser='" + bean.getR_VoidUser() + "',"
                    + "r_voidtime='" + bean.getR_VoidTime() + "',"
                    + "r_discbath='" + bean.getR_DiscBath() + "',"
                    + "r_kicprint='',"
                    + "r_opt9='" + ThaiUtil.Unicode2ASCII(voidMsg) + "',"
                    + "voidmsg='" + ThaiUtil.Unicode2ASCII(voidMsg) + "' "
                    + "where r_index='" + bean.getR_Index() + "' "
                    + "and r_table='" + bean.getR_Table() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(updBalance);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        }
        if ((bean.getR_Set().equals("Y")) && checkPSetSelect(bean.getR_PluCode())) {
            //Update  Balance File For Void
            try {
                String updateBalance = "update balance "
                        + "set r_void='" + bean.getR_Void() + "',"
                        + "cashier='" + Value.USERCODE + "',"
                        + "r_emp='" + bean.getR_Emp() + "',"
                        + "r_opt9='" + ThaiUtil.Unicode2ASCII(voidMsg) + "',"
                        + "voidmsg='" + ThaiUtil.Unicode2ASCII(voidMsg) + "' "
                        + "r_kicprint='' "
                        + "where r_index='" + bean.getR_Index() + "' "
                        + "and r_table='" + bean.getR_Table() + "'";
                Statement stmt = mysql.getConnection().createStatement();
                stmt.executeUpdate(updateBalance);
                stmt.close();
            } catch (Exception e) {
                MSG.ERR(this, e.getMessage());
            }
        }

        //update promotion, discount
        BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
        PublicVar.ErrorColect = false;
        PublicVar.TableRec_DiscBath = 0.0;

        mysql.close();

        tblShowPluShow(txtTable.getText());
    }

    private void showCell(int row, int column) {
        //System.err.println("ROW[" + row + "],COLUMN[" + column + "]");
        int sizeTable = tblShowBalance.getRowCount();
        if (row > 0) {
            if (row > sizeTable - 1) {
                row = sizeTable - 1;
            }
            Rectangle rect = tblShowBalance.getCellRect(row, column, true);
            tblShowBalance.scrollRectToVisible(rect);
            tblShowBalance.clearSelection();
            tblShowBalance.setRowSelectionInterval(row, row);
        }
    }

    private void bntoptionClick() {
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            String RKicPrint = model.getValueAt(row, 8).toString();
            String RVoid = model.getValueAt(row, 5).toString();
            String RIndex = model.getValueAt(row, 10).toString();
            if (!RKicPrint.equals("P")) {
                if (!RVoid.equals("V")) {
                    OptionMsg frm = new OptionMsg(null, true, txtTable.getText(), RIndex);
                    frm.setVisible(true);
                } else {
                    MSG.WAR(this, "รายการนี้ได้ยกเลิกออกจากบิลแล้ว ไม่สามารถใส่ข้อความพิเศษได้ !");
                }

            } else {
                PUtility.ShowMsg("รายการนี้ได้มีการพิมพ์ออกครัวไปแล้ว...ไม่สามารถกำหนด Option เพิ่มเติมได้...");
                txtPluCode.requestFocus();
            }
        }
    }

    private int getSelectedRowIndex() {
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            return row;
        } else {
            return -1;
        }
    }

    boolean seekPluCode() {
        String PluCode;
        String StrQty;
        String TempCode = txtPluCode.getText();
        PublicVar.P_Code = "";
        PublicVar.P_Status = "";
        PublicVar.P_Qty = 0.0;
        boolean found = false;
        double Qty;
        if (TempCode.length() > 0) {
            int index = TempCode.indexOf("*");
            if (index != -1) {
                StrQty = TempCode.substring(0, index);
                PluCode = TempCode.substring(index + 1);
                if (!PUtility.ChkNumValue(StrQty)) {
                    MSG.ERR("ป้อนจำนวนไม่ถูกต้อง..กรุณาป้อนใหม่...");
                    txtPluCode.setText("");
                    txtPluCode.requestFocus();
                }
                Qty = Double.parseDouble(StrQty);
            } else {
                Qty = 1;
                PluCode = TempCode;
            }
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            if (Qty > 0) {
                if (POSHW.getMenuItemList().equals("Y")) {
                    if (PluCode.length() <= 3) {
                        try {
                            Statement stmt = mysql.getConnection().createStatement();
                            String SqlQuery = "select *from menulist where menuitem=('" + PluCode + "') and (menuactive='Y')";
                            ResultSet rec = stmt.executeQuery(SqlQuery);
                            rec.first();
                            if (rec.getRow() == 0) {
                                MSG.ERR("ไม่พบรหัส Menu Items " + PluCode + " ในฐานข้อมูล !!!");
                                txtPluCode.setText("");
                                rec.close();
                                stmt.close();
                                txtPluCode.selectAll();
                                txtPluCode.requestFocus();
                                return false;
                            } else {
                                PluCode = rec.getString("plucode");
                            }
                            rec.close();
                            stmt.close();

                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                        }
                    }
                }
                try {

                    Statement stmt = mysql.getConnection().createStatement();
                    String sql = "select * "
                            + "from product "
                            + "where pcode='" + PluCode + "' "
                            + "and pactive='Y'";
                    ResultSet rec = stmt.executeQuery(sql);
                    rec.first();
                    if (rec.getRow() == 0) {
                        TempStatus = "";
                        String TempCode2 = seekBarCode(PluCode);
                        if (TempCode2.equals("")) {
                            MSG.ERR("ไม่พบรหัสสินค้า " + PluCode + " ในฐานข้อมูล หรือรหัสสินค้านี้อาจถูกยกเลิกการขายแล้ว...");
                            txtPluCode.setText("");
                        } else {
                            PluCode = TempCode2;
                            found = true;
                            PublicVar.P_Code = PluCode;
                            PublicVar.P_Status = TempStatus;
                            PublicVar.P_Qty = Qty;
                        }
                        rec.close();
                        stmt.close();

                        txtPluCode.selectAll();
                        txtPluCode.requestFocus();
                    } else {
                        found = true;
                        PublicVar.P_Code = PluCode;
                        PublicVar.P_Status = rec.getString("pstatus");
                        PublicVar.P_Qty = Qty;
                    }
                    rec.close();
                    stmt.close();

                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }
            } else {
                PUtility.ShowMsg("จำนวนขายต้องมากกว่า 0...");
                txtPluCode.requestFocus();
            }

            mysql.close();
        }

        return found;
    }

    private void changTypeClick() {
        boolean ChangOk = false;
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            if (!txtTable.getText().trim().equals("")) {
                PublicVar.ChangTypeOK = false;
                ChangTypeDialog frm = new ChangTypeDialog(null, true, txtTable.getText(), txtShowETD.getText());
                frm.setVisible(true);
                if (PublicVar.ChangTypeOK) {
                    if (ChangOk) {
                        clearDataMem();

//                        loadDataFromBalance(txtTable.getText());
                        tblShowPluShow(txtTable.getText());
                        txtPluCode.requestFocus();
                    }
                }
            }
        }
    }

    private void clearDataMem() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, -30);
    }

    private boolean findPluCode() {
        String PluCode;
        String StrQty;
        String TempCode = txtPluCode.getText();
        if (!TempCode.equals(null) || !TempCode.equals("null")) {
            if (TempCode.substring(0, 1).equals("*")) {
                TempCode = TempCode.replace("*", "");
            }
        }
        PublicVar.P_Code = "";
        PublicVar.P_Status = "";
        PublicVar.P_Qty = 0.0;
        boolean found = false;
        double Qty;

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        //check outofstock
        try {
            String sql = "select * from outstocklist "
                    + "where pcode='" + TempCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                MSG.ERR("สินค้ามีไม่ในสต๊อก หรือถูกยกเลิกการขายไปแล้ว กรุณาตรวจสอบ !!!");
                txtPluCode.setText("");
                txtPluCode.requestFocus();
                return false;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            return false;
        }

        if (TempCode.length() > 0) {
            int index = TempCode.indexOf("*");
            if (index != -1) {
                StrQty = TempCode.substring(0, index);
                PluCode = TempCode.substring(index + 1);
                if (!PUtility.ChkNumValue(StrQty)) {
                    MSG.ERR("ป้อนจำนวนไม่ถูกต้อง..กรุณาป้อนใหม่...");
                    txtPluCode.setText("");
                    txtPluCode.requestFocus();
                }
                Qty = Double.parseDouble(StrQty);
            } else {
                Qty = 1;
                PluCode = TempCode;
            }
            if (Qty > 0) {

                //for menuitem
                if (PluCode.length() <= 3) {
                    try {
                        if (POSHW.getMenuItemList().equals("Y")) {
                            Statement stmt = mysql.getConnection().createStatement();
                            String SqlQuery = "select *from menulist "
                                    + "where menuitem=('" + PluCode + "') and (menuactive='Y')";
                            ResultSet rec = stmt.executeQuery(SqlQuery);
                            rec.first();
                            if (rec.getRow() == 0) {
                                MSG.ERR("ไม่พบรหัส Menu Items " + PluCode + " ในฐานข้อมูล !!!");
                                txtPluCode.setText("");
                                rec.close();
                                stmt.close();
                                txtPluCode.selectAll();
                                txtPluCode.requestFocus();
                                return false;
                            } else {
                                PluCode = rec.getString("plucode");
                            }
                            rec.close();
                            stmt.close();
                        }
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                    }
                }
                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String SqlQuery = "select *from product "
                            + "where pcode='" + PluCode + "' and pactive='Y'";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                        TempStatus = "";
                        String TempCode2 = seekBarCode(PluCode);
                        if (TempCode2.equals("")) {
                            MSG.ERR("ไม่พบรหัสสินค้า " + PluCode + " ในฐานข้อมูล หรือรหัสสินค้านี้อาจถูกยกเลิกการขายแล้ว...");
                            txtPluCode.setText("");
                        } else {
                            PluCode = TempCode2;
                            found = true;
                            PublicVar.P_Code = PluCode;
                            PublicVar.P_Status = TempStatus;
                            PublicVar.P_Qty = Qty;
                        }
                        rec.close();
                        stmt.close();

                        txtPluCode.selectAll();
                        txtPluCode.requestFocus();
                    } else {
                        found = true;
                        PublicVar.P_Code = PluCode;
                        PublicVar.P_Status = rec.getString("pstatus");
                        PublicVar.P_Qty = Qty;
                    }
                    rec.close();
                    stmt.close();

                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }
            } else {
                PUtility.ShowMsg("จำนวนขายต้องมากกว่า 0...");
                txtPluCode.requestFocus();
            }
        }

        mysql.close();

        return found;
    }

    private boolean checkPSetSelect(String PCode) {
        boolean RetValue = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from product "
                    + "where pcode='" + PCode + "' "
                    + "and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetValue = false;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        } finally {
            mysql.close();
        }

        return RetValue;
    }

    private String seekBarCode(String BarCode) {
        String RetVal = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from product "
                    + "where pbarcode='" + BarCode + "' and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = "";
                TempStatus = "";
            } else {
                RetVal = rec.getString("pcode");
                TempStatus = rec.getString("pstatus");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return RetVal;
    }

    private void getPrice() {
        txtPluCode.setEditable(false);
    }

    private void saveToBalance() {
        String PCode = txtPluCode.getText();

        String StkCode = PUtility.GetStkCode();
        String emp = Value.EMP_CODE;
        String etd = txtShowETD.getText();
        String[] data = Option.splitPrice(PCode);
        double R_Quan = Double.parseDouble(data[0]);
        PCode = data[1];

        ProductControl pCon = new ProductControl();
        ProductBean productBean = pCon.getData(PCode);
        BalanceBean balance = new BalanceBean();
        balance.setStkCode(StkCode);

        double Price = 0.00;
        if (productBean.getPStatus().equals("S")) {
            getPrice();
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select * from product "
                    + "where pcode='" + PCode + "' "
                    + "and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                MSG.ERR("ไม่พบรหัสสินค้า " + PCode + " ในฐานข้อมูล หรือ รหัสสินค้านี้อาจยกเลิกการขายแล้ว...");
                txtPluCode.setText("");
                rec.close();
                stmt.close();
                txtPluCode.selectAll();
                txtPluCode.requestFocus();
            } else {
                if (!PUtility.CheckStockOK(PCode, R_Quan)) {
                    txtPluCode.setText("");
                    txtPluCode.selectAll();
                    txtPluCode.requestFocus();
                } else {
                    balance.setR_Opt1(GetQty.OPTION_TEXT[0]);
                    balance.setR_Opt2(GetQty.OPTION_TEXT[1]);
                    balance.setR_Opt3(GetQty.OPTION_TEXT[2]);
                    balance.setR_Opt4(GetQty.OPTION_TEXT[3]);
                    balance.setR_Opt5(GetQty.OPTION_TEXT[4]);
                    balance.setR_Opt6(GetQty.OPTION_TEXT[5]);
                    balance.setR_Opt7(GetQty.OPTION_TEXT[6]);
                    balance.setR_Opt8(GetQty.OPTION_TEXT[7]);
                    balance.setR_Opt9(GetQty.OPTION_TEXT[8]);

                    GetQty.clear();//clear temp option
                    balance.setR_PrintOK(PublicVar.PrintOK);
                    balance.setMacno(Value.MACNO);
                    balance.setCashier(Value.USERCODE);
                    balance.setR_ETD(etd);
                    balance.setR_Quan(R_Quan);
                    balance.setR_Table(txtTable.getText());
                    balance.setR_Emp(emp);

                    balance.setR_PrCuType("");
                    balance.setR_PrCuQuan(0.00);
                    balance.setR_PrCuAmt(0.00);

                    balance.setR_PluCode(productBean.getPCode());
                    balance.setR_Group(productBean.getPGroup());
                    balance.setR_Status(productBean.getPStatus());
                    balance.setR_Normal(productBean.getPNormal());
                    balance.setR_Discount(productBean.getPDiscount());
                    balance.setR_Service(productBean.getPService());
                    balance.setR_Vat(productBean.getPVat());
                    balance.setR_Type(productBean.getPType());
                    balance.setR_Stock(productBean.getPStock());
                    balance.setR_PName(productBean.getPDesc());
                    balance.setR_PEName(productBean.getPEDesc());
                    balance.setR_Unit(productBean.getPUnit1());
                    balance.setR_Set(productBean.getPSet());

                    if (balance.getR_Status().equals("P")) {
                        balance.setR_Price(PriceController.getPrice(etd, productBean));
                    } else {
                        balance.setR_Price(Price);
                    }

                    balance.setR_Total(balance.getR_Quan() * balance.getR_Price());
                    balance.setR_PrChkType("");

                    BalanceControl balanceControl = new BalanceControl();
                    String R_Index = balanceControl.getIndexBalance(balance.getR_Table());
                    balance.setR_Index(R_Index);

                    // for member discount
                    if (Value.MemberAlready && balance.getR_Discount().equals("Y")) {
                        balance.setR_PrSubType("-M");
                        balance.setR_PrSubCode("MEM");
                        balance.setR_PrSubQuan(balance.getR_Quan());

                        // คำนวณหาว่าลดเท่าไหร่
                        String[] subPercent = memberBean.getMember_DiscountRate().split("/");
                        int Percent = 0;
                        if (subPercent.length == 3) {
                            if (balance.getR_Normal().equals("N")) {
                                Percent = Integer.parseInt(subPercent[0].trim());
                            } else if (balance.getR_Normal().equals("C")) {
                                Percent = Integer.parseInt(subPercent[1].trim());
                            } else if (balance.getR_Normal().equals("S")) {
                                Percent = Integer.parseInt(subPercent[2].trim());
                            }
                        }
                        balance.setR_PrSubDisc(Percent);
                        balance.setR_PrSubBath(0);
                        balance.setR_PrSubAmt((balance.getR_Total() * Percent) / 100);
                        balance.setR_QuanCanDisc(0);// if member default 0
                    } else {
                        memberBean = null;
                        // for not member
                        balance.setR_PrSubType("");
                        balance.setR_PrSubCode("");
                        balance.setR_PrSubQuan(0);// not member default 0
                        balance.setR_PrSubDisc(0);
                        balance.setR_PrSubBath(0);
                        balance.setR_PrSubAmt(0);
                        balance.setR_QuanCanDisc(balance.getR_Quan());
                    }

                    balance.setR_Pause("P");

                    balanceControl.saveBalance(balance);

                    //update temptset
                    updateTempTset(balance);

                    stmt.close();

                    //Process Stock Out
                    String StkRemark = "SAL";
                    String DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
                    if (productBean.getPStock().equals("Y") && productBean.getPActive().equals("Y")) {
                        System.out.println("กำลังตัดสต๊อกสินค้านี้" + productBean.getPCode() + productBean.getPDesc());
                        PUtility.ProcessStockOut(DocNo, StkCode, balance.getR_PluCode(), new Date(), StkRemark, balance.getR_Quan(), balance.getR_Total(),
                                balance.getCashier(), balance.getR_Stock(), balance.getR_Set(), R_Index, "1");

                    }

                    //ตัดสต็อกสินค้าที่มี Ingredent
                    try {
                        String sql = "select i.*,pdesc,PBPack,pstock,pactive "
                                + "from product p, pingredent i "
                                + "where p.pcode=i.pingcode "
                                + "and i.pcode='" + balance.getR_PluCode() + "' "
                                + "and PFix='L' and PStock='Y'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        ResultSet rs = stmt1.executeQuery(sql);
                        while (rs.next()) {
                            if (rs.getString("pstock").equals("Y") && rs.getString("pactive").equals("Y")) {
                                String R_PluCode = rs.getString("PingCode");
                                double PBPack = rs.getDouble("PBPack");
                                if (PBPack <= 0) {
                                    PBPack = 1;
                                }
                                double R_QuanIng = (rs.getDouble("PingQty") * balance.getR_Quan()) / PBPack;
                                double R_Total = 0;
                                System.out.println("ตัดสต็อก " + R_PluCode);
                                PUtility.ProcessStockOut(DocNo, StkCode, R_PluCode, new Date(), StkRemark, R_QuanIng, R_Total,
                                        balance.getCashier(), "Y", "", "", "");
                            }

                        }

                        rs.close();
                        stmt1.close();
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                    }

                    //update promotion
                    BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
                    String Discount = BalanceControl.GetDiscount(txtTable.getText());
                    txtDiscount.setText("- " + Discount);
                    PublicVar.ErrorColect = true;
                } //end of Load Data
            }
            rec.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            txtPluCode.requestFocus();
        } finally {
            mysql.close();
        }
    }

    private String indexList = "";

    private void kichenPrint() {
        PrintSimpleForm printSimpleForm = new PrintSimpleForm();
        try {
            String printerName;
            String[] kicMaster = BranchControl.getKicData20();
            // หาจำนวนปริ้นเตอร์ว่าต้องออกกี่เครื่อง
            String sqlShowKic = "select r_kic from balance "
                    + "where r_table='" + txtTable.getText() + "' "
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
            String sqlGetSaveOrder = "select SaveOrder from branch";

            try {
                ResultSet rsGetSaveOrderConfig = mysql.getConnection().createStatement().executeQuery(sqlGetSaveOrder);
                if (rsGetSaveOrderConfig.next() && !rsGetSaveOrderConfig.wasNull()) {
                    String config = rsGetSaveOrderConfig.getString("SaveOrder");
                    if (!config.equals("N")) {
                        PublicVar.Branch_Saveorder = config;
                    }
                }

                Statement stmt1 = mysql.getConnection().createStatement();
                ResultSet rsKic = stmt1.executeQuery(sqlShowKic);

                ResultSet rsKicSaveOrder = mysql.getConnection().createStatement().executeQuery(sqlShowKic);
                if (rsKicSaveOrder.next() && !rsKicSaveOrder.wasNull()) {
                    if (!PublicVar.Branch_Saveorder.equals("N")) {
                        printSimpleForm.KIC_FORM_SaveOrder("", "SaveOrder", tableNo, 0);
                    }
                }
                //วนคำสั่งเพื่อพิมพ์ให้ครบทุกครัว
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
//                                    printerName = rKic;
                                    printerName = "kic" + rKic;
                                    String printerForm = BranchControl.getForm(rKic);
//                                    if (printerForm.equals("1") || printerForm.equals("2")) {
//                                    if (printerForm.equals("2")) {
//                                        String sql1 = "select * from balance "
//                                                + "where r_table='" + txtTable.getText() + "' "
//                                                + "and R_PrintOK='Y' "
//                                                + "and R_KicPrint<>'P' "
//                                                + "and R_Kic<>'' "
//                                                + "and R_kic='" + rKic + "' "
//                                                + "group by r_plucode";
//                                        Statement stmt2 = mysql.getConnection().createStatement();
//                                        ResultSet rs1 = stmt2.executeQuery(sql1);
//                                        while (rs1.next()) {
//                                            String PCode = rs1.getString("R_PluCode");
//                                            if (Value.printkic) {
//                                                printSimpleForm.KIC_FORM_2(printerName, txtTable.getText(), new String[]{PCode});
//                                            }
//                                        }
//                                    }
                                    if (printerForm.equals("1")) {
                                        String sql1 = "select * from balance "
                                                + "where r_table='" + txtTable.getText() + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                + "and R_kic='" + rKic + "' "
                                                + "group by r_plucode";
                                        printerName = "kic" + rKic;
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        ResultSet rs1 = stmt2.executeQuery(sql1);
                                        while (rs1.next()) {
                                            String PCode = rs1.getString("R_PluCode");
                                            if (printerForm.equals("1")) {
                                                if (Value.printkic) {
                                                    printSimpleForm.KIC_FORM_1(printerName, txtTable.getText(), new String[]{PCode});
                                                }
                                            }
                                        }
                                        rs1.close();
                                        stmt2.close();
//                                    } 

                                    } else if (printerForm.equals("6")) {
                                        String sql2 = "select sum(b.r_quan) R_Quan,sum(b.r_quan)*b.r_price Total, b.* from balance b "
                                                + "where r_table='" + txtTable.getText() + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                //                                                + "and R_Void<>'V' "
                                                + "and R_KIC='" + rKic + "' "
                                                + "group by r_plucode,r_void order by r_opt1";
//                                        String sql2 = "select * from balance "
//                                                + "where r_table='" + txtTable.getText() + "' "
//                                                + "and R_PrintOK='Y' "
//                                                + "and R_KicPrint<>'P' "
//                                                + "and R_Kic<>'' "
//                                                + "and R_Void<>'V' and R_KIC='"+rKic+"'";
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        ResultSet rs2 = stmt2.executeQuery(sql2);
                                        while (rs2.next()) {
                                            if (Value.printkic) {
                                                String R_Index = rs2.getString("R_Index");
                                                String R_PLUCODE = rs2.getString("R_Plucode");
                                                double qty = rs2.getDouble("R_Quan");
                                                double priceTotal = rs2.getDouble("Total");
                                                printSimpleForm.KIC_FORM_6(printerName, txtTable.getText(), R_Index, R_PLUCODE, qty, priceTotal);
//                                                printBillCheck();
//                                                printBillVoidCheck();
                                            }
                                        }
                                        //update r_kicprint
//                                        try {
//                                            String sql = "update balance "
//                                                    + "set r_kicprint='P',"
//                                                    + "r_pause='Y' "
//                                                    + "where r_table='" + txtTable.getText() + "' "
//                                                    + "and r_kicprint<>'P' "
//                                                    + "and r_printOk='Y' "
//                                                    + "and r_kic<>'';";
//                                            Statement stmt = mysql.getConnection().createStatement();
//                                            stmt.executeUpdate(sql);
//                                        } catch (SQLException e) {
//                                            MSG.ERR(this, e.getMessage());
//                                        }
                                        rs2.close();
                                        stmt2.close();
                                    } else if (printerForm.equals("3") || printerForm.equals("4") || printerForm.equals("5")) {

                                        if (printerForm.equals("3")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_3("", printerName, txtTable.getText(), iKic);
                                                String CheckBillBeforeCash = CONFIG.getP_CheckBillBeforCash();
                                                if (CheckBillBeforeCash.equals("Y")) {
                                                    printBillVoidCheck();
                                                }
                                            }
                                        } else if (printerForm.equals("4")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_4(printerName, txtTable.getText());
                                                printBillVoidCheck();
                                            }
                                        } else if (printerForm.equals("5")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_5(printerName, txtTable.getText());
                                                printBillVoidCheck();
                                            }
                                        }

                                    } else if (printerForm.equals("7") || printerForm.equals("2")) {
                                        if (Value.printkic) {
                                            printSimpleForm.KIC_FORM_7(printerName, txtTable.getText());
                                            printBillVoidCheck();
                                        }
                                    } else {
                                        printBillVoidCheck();
                                        MSG.ERR(this, "ไม่พบฟอร์มปริ้นเตอร์ครัวในระบบที่สามารใช้งานได้ !!!");
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

                CheckKicPrint();

                //update r_kicprint
                try {
                    String sql = "update balance "
                            + "set r_kicprint='P',"
                            + "r_pause='Y' "
                            + "where r_table='" + txtTable.getText() + "' "
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

    private void kichenPrintAfterPrintCheck() {
        PrintSimpleForm printSimpleForm = new PrintSimpleForm();

        try {
            String printerName;
            String[] kicMaster = BranchControl.getKicData20();
            // หาจำนวนปริ้นเตอร์ว่าต้องออกกี่เครื่อง
            String sqlShowKic = "select r_kic from balance "
                    + "where r_table='" + txtTable.getText() + "' "
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
                                                + "where r_table='" + txtTable.getText() + "' "
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
                                                    printSimpleForm.KIC_FORM_1(printerName, txtTable.getText(), new String[]{PCode});
                                                }
                                            } else if (printerForm.equals("2")) {
                                                if (Value.printkic) {
                                                    printSimpleForm.KIC_FORM_2(printerName, txtTable.getText(), new String[]{PCode});
                                                }
                                            }
                                        }

                                        rs1.close();
                                        stmt2.close();
                                    } else if (printerForm.equals("6")) {
                                        String sql2 = "select sum(b.r_quan) R_Quan,sum(b.r_quan)*b.r_price Total, b.* from balance b "
                                                + "where r_table='" + txtTable.getText() + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                + "and R_Void<>'V' and R_KIC='" + rKic + "' "
                                                + "group by r_plucode order by r_opt1";
//                                        String sql2 = "select * from balance "
//                                                + "where r_table='" + txtTable.getText() + "' "
//                                                + "and R_PrintOK='Y' "
//                                                + "and R_KicPrint<>'P' "
//                                                + "and R_Kic<>'' "
//                                                + "and R_Void<>'V' ";
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        ResultSet rs2 = stmt2.executeQuery(sql2);
                                        while (rs2.next()) {
                                            if (Value.printkic) {
                                                String R_Index = rs2.getString("R_Index");
                                                String R_PLUCODE = rs2.getString("R_Plucode");
                                                double qty = rs2.getDouble("R_Quan");
                                                double priceTotal = rs2.getDouble("Total");
                                                printSimpleForm.KIC_FORM_6(printerName, txtTable.getText(), R_Index, R_PLUCODE, qty, priceTotal);
//                                                printBillCheck();
                                            }
                                        }

                                        rs2.close();
                                        stmt2.close();
                                    } else if (printerForm.equals("3") || printerForm.equals("4") || printerForm.equals("5")) {

                                        if (printerForm.equals("3")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_3("", printerName, txtTable.getText(), iKic);
//                                                printBillVoidCheck();
                                            }
                                        } else if (printerForm.equals("4")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_4(printerName, txtTable.getText());
//                                                printBillVoidCheck();
                                            }
                                        } else if (printerForm.equals("5")) {
                                            if (Value.printkic) {
                                                printSimpleForm.KIC_FORM_5(printerName, txtTable.getText());
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
                    } else {

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
                            + "where r_table='" + txtTable.getText() + "' "
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

    private void changeSaleType(String SaleType) {
        // description กำหนดประเภทการขาย
        if (SaleType.equals("E")) {
            txtTypeDesc.setText(SALE_DINE_IN);
        } else if (SaleType.equals("T")) {
            txtTypeDesc.setText(SALE_TAKE_AWAY);
        } else if (SaleType.equals("D")) {
            txtTypeDesc.setText(SALE_Delivery);
        }

        String oldType = txtShowETD.getText();
        if (SaleType.equals("E")) {
            if (oldType.equals("E") || oldType.equals("T") || model.getRowCount() == 0) {
                txtShowETD.setText("E");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        } else if (SaleType.equals("T")) {
            if (((oldType.equals("E")) | (oldType.equals("T")) | (model.getRowCount() == 0))
                    & !PublicVar.ChargeGroup.equals("2")) {
                txtShowETD.setText("T");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        }
    }

    private void txtTableOnEnter() {
        txtCust.setEditable(false);

        int RowCount = model.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model.removeRow(0);
        }
    }

    private void txtTableOnExit() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String sql = "select * from tablefile "
                    + "where tcode='" + txtTable.getText().trim() + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                if (rs.getString("tonact").equals("Y") && rs.getDouble("TAmount") > 0) {
                    MSG.WAR("มีพนักงานกำลังป้อนรายการโต๊ะนี้อยู่...");
                    TableOpenStatus = false;
                    txtTable.setText("");
                    txtTable.setEditable(true);
                    txtTable.requestFocus();
                } else {
                    //load data from tablefile
                    txtTable.setEditable(false);
                    txtCust.setText(rs.getString("TCustomer"));
                    txtCust.setEditable(false);
                    try {
                        String UpdateTable = "update tablefile set "
                                + "tonact='Y',"
                                + "macno='" + Value.MACNO + "',"
                                + "cashier='" + Value.USERCODE + "',"
                                + "EmpDisc='0',"
                                + "FastDisc='0',"
                                + "TrainDisc='0',"
                                + "PrintTime1='',"
                                + "TUser='',"
                                + "tlogindate=curdate(),"
                                + "tlogintime=curtime(),"
                                + "TCurTime=curtime()"
                                + "where tcode='" + txtTable.getText().trim() + "'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(UpdateTable);
                        stmt1.close();
                        tbpMain.setSelectedIndex(0);

                        //load data to table
//                        loadDataFromBalance(txtTable.getText());
                        tblShowPluShow(txtTable.getText());
                        TableOpenStatus = true;
                    } catch (Exception ex) {
                        MSG.ERR(this, ex.getMessage());
                        TableOpenStatus = false;
                        txtTable.setText("");
                        txtTable.setEditable(true);
                        txtTable.requestFocus();
                        txtTable.setText("");
                    }
                }
            } else {
                MSG.ERR(this, "หมายเลขนี้ไม่ได้มีการกำหนดไว้ในการทำงานโต๊ะหลัก !!!");
                TableOpenStatus = false;
                txtTable.setText("");
                txtTable.setEditable(true);
                txtTable.requestFocus();
                txtTable.setText("");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());

            TableOpenStatus = false;
            txtTable.setText("");
            txtTable.setEditable(true);
            txtTable.requestFocus();
            txtTable.setText("");
        } finally {
            mysql.close();
        }

    }

    private void txtPluCodeOnExit() {
        long s = System.currentTimeMillis();
        saveToBalance();
        tblShowPluShow(txtTable.getText());
        long e = System.currentTimeMillis();
        System.out.println("Time Estimate: " + (e - s));
        txtPluCode.setText("");
        txtPluCode.requestFocus();
    }

    //ปุ่มพักโต๊ะ
    private void bntHoldTableClick() {
        if (txtTable.getText().length() > 0 && tblShowBalance.getRowCount() > 0) {
            if (btnClickPrintKic == true) {
                String sqlTurnPrintKicOff = "update balance set r_kic='0' where r_kicprint<>'P' and r_table='" + tableNo + "';";
                try {
                    MySQLConnect c = new MySQLConnect();
                    c.open();
                    c.getConnection().createStatement().executeUpdate(sqlTurnPrintKicOff);
                    c.close();
                } catch (SQLException e) {
                    MSG.ERR(e.toString());
                }
            }
            kichenPrint();
            holdTableAndSave();
            PublicVar.ErrorColect = false;
            initScreen();
        } else {
            try {
                MySQLConnect c = new MySQLConnect();
                String sql = "update tablefile set tonact ='N',tcurtime='00:00:00',tcustomer='0' where tcode='" + txtTable.getText() + "';";
                c.open();
                c.getConnection().createStatement().executeUpdate(sql);
                c.close();
            } catch (SQLException e) {
                MSG.ERR(e.toString());
            }
        }
    }

    private void updateCustomerCount(int custCount) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile "
                    + "set tcustomer='" + custCount + "',"
                    + "macno='" + Value.MACNO + "' "
                    + "where tcode='" + txtTable.getText() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            MSG.ERR(this, e.getMessage());
        } finally {
            mysql.close();
        }
        txtPluCode.requestFocus();
    }

    private void holdTableAndSave() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        BalanceBean balanceBean = new BalanceBean();
        mysql.open();
        try {
            String getLogIntimeBalance = "select r_time,r_date from balance where r_table ='" + txtTable.getText() + "'";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(getLogIntimeBalance);
            if (rs.next()) {
                balanceBean.setLoginTime(rs.getString("r_time"));
                balanceBean.setR_LoginDate(rs.getString("r_date"));
            }
            String UpdateTableFile = "update tablefile "
                    + "set tonact='N', tlogintime ='" + balanceBean.getLoginTime() + "',"
                    + "macno='" + Value.MACNO + "',"
                    + "tlogindate='" + balanceBean.getR_LoginDate() + "' "
                    + "where tcode='" + txtTable.getText() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(UpdateTableFile);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        showSum();
    }

    private void bntlogoffuserClick() {
        if (MSG.CONF(this, "ยืนยันการออกจากระบบการขาย (Logoff User) ? ")) {
            if (model.getRowCount() == 0) {
                PublicVar.P_LineCount = 1;
                PublicVar.P_LogoffOK = false;
                /**
                 * * OPEN CONNECTION **
                 */
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();
                try {
                    Statement stmt = mysql.getConnection().createStatement();
                    String QryUpdatePosuser = "update posuser set onact='N',macno='' where (username='" + PublicVar._User + "')";
                    stmt.executeUpdate(QryUpdatePosuser);
                    String QryUpdatePosHeSetup = "update poshwsetup set onact='N' where(terminal='" + Value.MACNO + "')";
                    stmt.executeUpdate(QryUpdatePosHeSetup);
                    stmt.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                } finally {
                    mysql.close();
                }

                if (this.chkEJPath()) {
                    Prn.printLogout();
                }
                if (updateLogout(PublicVar._RealUser)) {
                    loadLoginForm();
                }
            } else {
                MSG.WAR(this, "มีรายการขายค้างอยู่ไม่สามารถ Logoff ออกจากระบบได้...");
                if (TableOpenStatus) {
                    txtPluCode.requestFocus();
                } else {
                    txtTable.requestFocus();
                }
            }
        } else {
            if (TableOpenStatus) {
                txtPluCode.requestFocus();
            } else {
                txtTable.requestFocus();
            }
        }

    }

    private boolean updateLogout(String UserCode) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "update posuser set onact='N',macno='' where username='" + UserCode + "'";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
            Value.CASHIER = "";

            return true;
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            return false;
        } finally {
            mysql.close();
        }

    }

    private void bntPaidinClick() {
        if (!chkEJPath()) {
            return;
        }
        if (model.getRowCount() == 0) {
            PaidinFrm frm = new PaidinFrm(null, true);
            frm.setVisible(true);
        }
    }

    private void bntPaidoutClick() {
        chkEJPath();
    }

    private void showTableAvialble() {
        if (!PUtility.CheckCashierClose(PublicVar._User)) {
            if (txtTable.getText().trim().equals("")) {
                ShowTable frm = new ShowTable(null, true);
                frm.setVisible(true);
                if (!PublicVar.ReturnString.equals("")) {
                    txtTable.setText(PublicVar.ReturnString);
                    if (txtTable.getText().trim().length() > 0) {
                        txtTable.setEditable(false);
                        txtTableOnExit();
                        //ShowSum();
                        if (CONFIG.getP_EmpUse().equals("Y")) {

                        } else {
                            if (PublicVar.TableRec_TCustomer == 0) {
                                txtCust.setEditable(true);
                                txtCust.requestFocus();
                                txtCust.selectAll();
                            } else {
                                txtPluCode.setEditable(true);
                                txtPluCode.requestFocus();
                                txtCust.setSelectionEnd(0);
                                txtCust.setEditable(false);
                            }
                        }
                    }
                }
            } else {
                PUtility.ShowWaring("มีการกำหนดหมายเลขโต๊ะไว้แล้ว...");
            }
        } else {
            txtTable.setText("");
            txtTable.requestFocus();
        }
    }

    private void showBillDuplicate() {
        if (!chkEJPath()) {
            return;
        }
        if (model.getRowCount() == 0) {
            CopyBill frm = new CopyBill(null, true);
            frm.setVisible(true);
            initScreen();
        }
    }

    private void showRefundBill() {
        if (!chkEJPath()) {
            return;
        }
        PublicVar.TempUserRec = PublicVar.TUserRec;
        if (model.getRowCount() == 0) {
            if (PublicVar.TUserRec.Sale2.equals("Y")) {
                RefundBill frm = new RefundBill(null, true);
                frm.setVisible(true);
                PublicVar.P_ItemCount = 0;
                initScreen();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale2.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            RefundBill frm = new RefundBill(null, true);
                            frm.setVisible(true);
                            PublicVar.P_ItemCount = 0;
                            initScreen();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
        }
        PublicVar.TUserRec = PublicVar.TempUserRec;
    }

    private void bntArPaymentClick() {
        if (!chkEJPath()) {
            return;
        }
        if (!PUtility.CheckSaleDateOK()) {
            return;
        }

        PublicVar.TempUserRec = PublicVar.TUserRec;
        if (model.getRowCount() == 0) {
            if (PublicVar.TUserRec.Sale5.equals("Y")) {
                ARPayment frm = new ARPayment(null, true);
                frm.setVisible(true);
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale5.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            ARPayment frm = new ARPayment(null, true);
                            frm.setVisible(true);
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
        }
        PublicVar.TUserRec = PublicVar.TempUserRec;
    }

    public static void main(String args[]) {
        new MySQLConnect();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("OptionPane.messageFont", new javax.swing.plaf.FontUIResource(new java.awt.Font(
                    "Norasi", java.awt.Font.PLAIN, 14)));
        } catch (ClassNotFoundException e) {
            MSG.ERR(null, e.getMessage());
        } catch (InstantiationException e) {
            MSG.ERR(null, e.getMessage());
        } catch (IllegalAccessException e) {
            MSG.ERR(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            MSG.ERR(null, e.getMessage());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainSale(null, false, "1").setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MAddNewAccr1;
    private javax.swing.JMenuItem MAddNewMember1;
    private javax.swing.JMenuItem MCancelArPayment1;
    private javax.swing.JMenuItem MCancelArPayment2;
    private javax.swing.JMenuItem MHeaderBill1;
    private javax.swing.JMenu MMainMenu1;
    private javax.swing.JMenuItem MRepArHistory1;
    private javax.swing.JMenuItem MRepArNotPayment1;
    private javax.swing.JMenuItem MRepInvAr1;
    private javax.swing.JMenuItem MRepInvCash1;
    private javax.swing.JMenuItem MRepMemberHistory1;
    private javax.swing.JButton bntPrintCheckBill;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnHold;
    private javax.swing.JRadioButton btnLangEN;
    private javax.swing.JRadioButton btnLangTH;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPrintKic;
    private javax.swing.JButton btnSplit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar11;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelMember;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lbCredit;
    private javax.swing.JLabel lbCreditAmt;
    private javax.swing.JLabel lbCreditMoney;
    private javax.swing.JLabel lbTotalAmount;
    private javax.swing.JPanel pMenu1;
    private javax.swing.JPanel pMenu2;
    private javax.swing.JPanel pMenu3;
    private javax.swing.JPanel pMenu4;
    private javax.swing.JPanel pMenu5;
    private javax.swing.JPanel pMenu6;
    private javax.swing.JPanel pMenu7;
    private javax.swing.JPanel pMenu8;
    private javax.swing.JPanel pMenu9;
    private javax.swing.JPanel pSubMenu1;
    private javax.swing.JPanel pSubMenu2;
    private javax.swing.JPanel pSubMenu3;
    private javax.swing.JTable tblShowBalance;
    private javax.swing.JTabbedPane tbpMain;
    private javax.swing.JTextField txtCust;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtDisplayDiscount;
    private javax.swing.JTextField txtMember1;
    private javax.swing.JTextField txtMember2;
    private javax.swing.JTextField txtPluCode;
    private javax.swing.JTextField txtShowETD;
    private javax.swing.JTextField txtTable;
    private javax.swing.JTextField txtTypeDesc;
    // End of variables declaration//GEN-END:variables

    private void showFloorPlan() {
        if (txtTable.hasFocus() && txtTable.getText().trim().equals("") && txtTable.getText().endsWith("")) {
//            dispose();
        } else {
            MSG.ERR(this, "กรุณาพักโต๊ะก่อนการใช้งาน Floor Plan");
        }
    }

    private void showSplitBill() {
        //if (txtPluCode.hasFocus() && model.getRowCount() > 0) {
        SplitBillPayment sp = new SplitBillPayment(null, true, txtTable.getText());
        sp.setVisible(true);

        if (!sp.getTable2().equals("")) {
            this.setVisible(true);
            txtTable.setText(sp.getTable2());
            tableOpened();
            txtCustOnExit();
            txtDiscount.setText(BalanceControl.GetDiscount(txtTable.getText()));
        }

        // }
        sumSplit();
    }

    private void showMember() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        if (Value.MemberAlready == true) {
            int confirm = JOptionPane.showConfirmDialog(this, "มีการป้อนรหัสสมาชิกไว้แล้วต้องการเปลี่ยนใหม่หรือไม่...?");
            if (confirm == JOptionPane.YES_OPTION) {
                Value.MemberAlready = false;
                try {
                    String sql = "update tablefile set "
                            + "MemDisc='', "
                            + "MemDiscAmt='0.00', "
                            + "MemCode='', "
                            + "MemCurAmt='0.00', "
                            + "MemName='' "
                            + "where TCode='" + txtTable.getText() + "'";
                    Statement stmt = mysql.getConnection().createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();

                    showMember();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        } else {
            MemberDialog frm = new MemberDialog(null, true, tableNo);
            frm.setVisible(true);

            if (frm.getMemCode() != null) {
                Value.MemberAlready = true;
                memberBean = MemberBean.getMember(frm.getMemCode());
                String memDisc = memberBean.getMember_DiscountRate();
                // update member in tablefile
                SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    String sql = "update tablefile set "
                            + "MemDisc='" + memberBean.getMember_DiscountRate() + "', "
                            + "MemDiscAmt='0.00', "
                            + "MemCode='" + memberBean.getMember_Code() + "', "
                            + "MemCurAmt='" + memberBean.getMember_TotalScore() + "', "
                            + "MemName='" + ThaiUtil.Unicode2ASCII(memberBean.getMember_NameThai()) + "', "
                            + "MemBegin='" + simp.format(memberBean.getMember_Brithday()) + "', "
                            + "MemEnd='" + simp.format(memberBean.getMember_ExpiredDate()) + "' "
                            + "where TCode='" + txtTable.getText() + "'";
                    Statement stmt = mysql.getConnection().createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

                // update old order
                MemberControl mc = new MemberControl();
                mc.updateMemberAllBalance(txtTable.getText(), memberBean);

                // update all discount and promotion
                BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
            }
        }

        mysql.close();

        showSum();
    }

    private void showHoldTable() {
        tbpMain.setSelectedIndex(0);

        bntHoldTableClick();
        txtTable.setText("");
        txtCust.setText("");

        clearTable();
        showTableOpen();
        clearHistory();
        Value.TableSelected = "";
    }

    private void showPaidIn() {
        if (PUtility.CheckSaleDateOK()) {
            bntPaidinClick();
        }
    }

    private void showPaidOut() {
        if (PUtility.CheckSaleDateOK()) {
            bntPaidoutClick();
        }
    }

    private void showBillCheck() {
        if (model.getRowCount() > 0) {
            kichenPrint();
            bntcheckbillClick();
        }
    }

    private void showPayAR() {
        if (PUtility.CheckSaleDateOK()) {
            bntArPaymentClick();
        }
    }

    private void showCheckBill() {
        if (model.getRowCount() > 0) {
//            loadDataFromBalance(tableNo);
            kichenPrint();
            bntPaymentClick();
            showSum();
        }
    }

    private void selectedTableBalance() {
        if (tblShowBalance.getRowCount() > 0) {
            tblShowBalance.requestFocus();
            Rectangle rect = tblShowBalance.getCellRect(0, 0, true);
            tblShowBalance.scrollRectToVisible(rect);
            tblShowBalance.clearSelection();
            tblShowBalance.setRowSelectionInterval(0, 0);
        }
    }

    private void selectedOptionBill() {
        int row = tblShowBalance.getSelectedRow();
        String chkPCode = "" + tblShowBalance.getValueAt(row, 0);
        System.out.println(chkPCode);
        if (chkPCode.equals("")) {
            return;
        }

        String chkRIndex = "" + tblShowBalance.getValueAt(row, 10);
        //find data set
        if (!checkRIndex(chkRIndex, chkPCode) && chkRIndex != null) {
            return;
        }

        if (row != -1) {
            PopupItemJDialog popup = new PopupItemJDialog(null, true);
            popup.setVisible(true);

            String typeIndex = popup.getTypeIndex();

            if (!typeIndex.equals("none")) {
                String PCode = model.getValueAt(row, 0).toString();
                String PVoid = model.getValueAt(row, 5).toString();
                String RIndex = model.getValueAt(row, 10).toString();
                String RPause = model.getValueAt(row, 11).toString();
                String RKicPrint = model.getValueAt(row, 7).toString();

                if (typeIndex.equals("ItemOption")) {
                    bntoptionClick();
                    tblShowPluShow(txtTable.getText());
                } else if (typeIndex.equals("TypeSale")) {
                    if (model.getRowCount() > 0) {

                        if (!PCode.equals("") && !PVoid.equals("V")) {
                            changTypeClick();
                        } else {
                            PUtility.ShowMsg("รายการนี้ได้มีการพิมพ์ออกครัวไปแล้ว...\nไม่สามารถเปลี่ยนประเภทการขายได้...");
                        }
                    }
                } else if (typeIndex.equals("ItemDiscount")) {

                    // ตรวจสอบว่าสามารถให้ส่วนลดได้อีกหรือไม่ ?
                    if (checkCanDisc(RIndex)) {
                        if (model.getRowCount() > 0) {
                            if (!PCode.equals("") && !PVoid.equals("V")) {
                                ItemDiscount i = new ItemDiscount(null, true, txtTable.getText(), RIndex, memberBean);
                                i.setVisible(true);

                                tblShowPluShow(txtTable.getText());
                                txtPluCode.setText("");
                                txtPluCode.requestFocus();
                            }
                        }
                    } // หากไม่สามารถให้ส่วนลดรายการได้
                    else {
                        MSG.WAR(this, "รายการสินค้านี้มีการให้ส่วนลดอื่นไปแล้วไม่สามารถให้ส่วนลดได้อีก");
                    }

                } else if (typeIndex.equals("ItemVoid")) {
                    if (RPause.equalsIgnoreCase("P") && !RKicPrint.equals("P")) {
                        cancelItemBeforeHold();
                    } else {
                        if (!PCode.equals("") && !PVoid.equals("V")) {
                            bntVoidClick();
                            double totalAmount = Double.parseDouble(lbTotalAmount.getText().replace(",", ""));
                            DiscountDialog dd = new DiscountDialog(null, true, tableNo, totalAmount, memberBean,
                                    txtMember1.getText(), txtMember2.getText());
                            dd.clearMemberDiscount();
                        }
                    }
                } else if (typeIndex.equals("ItemMove")) {
//                    ซ่อนไว้ก่อนที่ jeffer ยังไม่ได้ใช้ (29/02/2016)
                    MoveItemDialog m = new MoveItemDialog(null, true, txtTable.getText());
                    m.setVisible(true);
                } else if (typeIndex.equals("EditQty")) {
                    ItemEditQty itemEditQty = new ItemEditQty(null, true, txtTable.getText(), RIndex, memberBean);
                    itemEditQty.setVisible(true);
                }
            }
            BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
            //load data from balance
//            loadDataFromBalance(txtTable.getText());
            tblShowPluShow(txtTable.getText());
        }
    }

    private void pCodeEnter() {
        String pluCode = txtPluCode.getText().trim();
        String chkOpt = "";
        if (!pluCode.equals("")) {
            chkOpt = pluCode.substring(pluCode.length() - 1, pluCode.length());
        }

        if (chkOpt.equals("+")) {
            //สามารถเลือกจำนวนได้เลย

            pluCode = pluCode.substring(0, pluCode.length() - 1);

            int qtySet = 0;
            if (Value.autoqty) {
                GetQty frm = new GetQty(null, true, pluCode);
                frm.setVisible(true);

                qtySet = frm.ReturnQty;
            } else {
                qtySet = 1;
            }
            if (!pluCode.equals("")) {
                if (qtySet > 0) {
                    txtPluCode.setText(QtyIntFmt.format(qtySet).trim() + "*" + pluCode);
                    if (seekPluCode()) {
                        if (PublicVar.P_Status.equals("S")) {
                            getPrice();
                        } else {
                            txtPluCodeOnExit();
                        }
                    }
                } else {
                    txtPluCode.setText("");
                    txtPluCode.requestFocus();
                }
            }
        } else {
            if (findPluCode()) {
                if (PublicVar.P_Status.equals("S")) {
                    getPrice();
                } else {
                    txtPluCodeOnExit();
                }
            }
        }
    }

    private void clearHistory() {
        int size = historyBack.size();
        for (int i = 0; i < size; i++) {
            historyBack.remove(0);
        }
    }

    private String buttonName;

    private int buttonIndex;

    private void addMouseEvent(final JButton btn, final int index) {
        btn.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {//click ขวา
                    buttonName = btn.getName();
                    buttonIndex = index;
                    if (!ProductControl.checkProductItem(buttonName)) {
                        jMenuItem2.setVisible(false);
                    } else {
                        jMenuItem2.setVisible(true);
                    }
                    jPopupMenu1.show(btn, e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void clearPanelMenu() {
        pMenu1.removeAll();
        pMenu2.removeAll();
        pMenu3.removeAll();
        pMenu4.removeAll();
        pMenu5.removeAll();
        pMenu6.removeAll();
        pMenu7.removeAll();
        pMenu8.removeAll();
        pMenu9.removeAll();
    }

    private boolean checkCanDisc(String RIndex) {
        boolean result = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select R_QuanCanDisc from balance "
                    + "where R_Index='" + RIndex + "' "
                    + "and R_QuanCanDisc>0";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            result = false;
        } finally {
            mysql.close();
        }

        return result;
    }

    private void sumSplit() {
        POSConfigSetup.Bean().getP_Service();
        String table = txtTable.getText();

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select sum(R_Total) from balance where R_Table ='" + table + "'and R_Void <> 'V'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {

                double RTotal = rs.getDouble("sum(R_Total)");
                try {
                    double totalServiceAmt = 0;
                    double serviceAmt = POSConfigSetup.Bean().getP_Service();
                    if (serviceAmt == 0) {
                        totalServiceAmt = 0;
                    } else {
                        totalServiceAmt = RTotal / POSConfigSetup.Bean().getP_Service();
                    }

                } catch (Exception e) {
//                    txtTotalService.setText("0.00");
                }
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }

        updatetable();
    }

    private void updatetable() {

        String table = txtTable.getText();
        String ta = lbTotalAmount.getText();
//        String tc = txtTotalAmount.getText();
        String cus = txtCust.getText();
//        String ser = txtTotalService.getText();

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "SELECT COUNT(R_PName) FROM balance where r_table = '" + table + "'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);

            while (rs.next()) {
                String R_PName = rs.getString("COUNT(R_PName)");
                String UpdateTableFile = "update tablefile "
                        + "set tonact='N',"
                        + "macno='" + Value.MACNO + "',"
                        + "TCurTime = CurTime(),"
                        + "TCustomer = '" + cus + "',"
                        + "TItem = '" + R_PName + "',"
                        //                        + "TAmount = '" + tc + "',"
                        + "Service = '" + POSConfigSetup.Bean().getP_Service() + "' "
                        //                        + "ServiceAmt = '" + ser + "',"
//                        + "NetTotal = '" + ta.replace(",", "") + "'"
                        + "where tcode='" + txtTable.getText() + "'";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(UpdateTableFile);
                stmt1.close();
            }

            rs.close();
//            stmt.close();
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
            String sql = "SELECT TakeOrderChk,terminal FROM poshwsetup "
                    + "where Terminal = '" + Value.MACNO + "' "
                    + "and TakeOrderChk='Y'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {

//                String sqlBL = "select * from balance where r_table='" + tableNo + "' and r_type='1'";
//                ResultSet rs2 = mysql.getConnection().createStatement().executeQuery(sqlBL);
//                if (rs2.next()) {
                btnPayment.setVisible(false);
                btnSplit.setVisible(false);
//                } 
//                else {
//                    btnPayment.setVisible(true);
//                    btnSplit.setVisible(true);
//                }
//                if  (ConfigFile.getProperties("useprint").equals("false")){
                bntPrintCheckBill.setVisible(false);
//                }

                isTakeOrder = true;

            }
//            if (rs.next()) {
//                btnSplit.setVisible(false);
//                btnPayment.setVisible(false);
//
//                isTakeOrder = true;
//            }

            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return isTakeOrder;
    }

    private void updateTempTset(BalanceBean bBean) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();

        try {
            String sqlUpd = "update tempset set "
                    + "PIndex='" + bBean.getR_Index() + "' "
                    + "where PTableNo='" + bBean.getR_Table() + "' ";
//            Statement stmt = mysql.getConnection().createStatement();
            mysql.getConnection().createStatement().executeUpdate(sqlUpd);

            String sql = "select * from tempset "
                    + "where PIndex='" + bBean.getR_Index() + "' ";
//            Statement stmt1 = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("PCode").equals(bBean.getR_PluCode())) {
                    updateBalanceOptionFromTemp(bBean.getR_Index(), bBean.getR_Table(), bBean.getR_PluCode());
                } else {
                    //อย่าลืมเพิ่มข้อมูลใน balance ด้วย
                    String PCode = rs.getString("PCode");
                    if (!PCode.equals("")) {
                        String StkCode = PUtility.GetStkCode();
                        String emp = Value.EMP_CODE;
                        String etd = txtShowETD.getText();
                        String[] data = Option.splitPrice(PCode);
                        double R_Quan = Double.parseDouble(data[0]);
                        PCode = data[1];

                        ProductControl pCon = new ProductControl();
                        ProductBean productBean = pCon.getData(PCode);

                        BalanceBean balance = new BalanceBean();
                        balance.setStkCode(StkCode);
                        balance.setR_PrintOK(PublicVar.PrintOK);
                        balance.setMacno(Value.MACNO);
                        balance.setCashier(Value.USERCODE);
                        balance.setR_ETD(etd);
                        balance.setR_Quan(R_Quan);
                        balance.setR_Table(txtTable.getText());
                        balance.setR_Emp(emp);

                        balance.setR_PrCuType("");
                        balance.setR_PrCuQuan(0.00);
                        balance.setR_PrCuAmt(0.00);

                        balance.setR_PluCode(productBean.getPCode());
                        balance.setR_Group(productBean.getPGroup());
                        balance.setR_Status(productBean.getPStatus());
                        balance.setR_Normal(productBean.getPNormal());
                        balance.setR_Discount(productBean.getPDiscount());
                        balance.setR_Service(productBean.getPService());
                        balance.setR_Vat(productBean.getPVat());
                        balance.setR_Type(productBean.getPType());
                        balance.setR_Stock(productBean.getPStock());
                        balance.setR_PName(productBean.getPDesc());
                        balance.setR_Unit(productBean.getPUnit1());
                        balance.setR_Set(productBean.getPSet());

                        if (balance.getR_Status().equals("P")) {
                            if (etd.equals("E")) {
                                balance.setR_Price(productBean.getPPrice11());
                            } else if (etd.equals("T")) {
                                balance.setR_Price(productBean.getPPrice12());
                            } else if (etd.equals("D")) {
                                balance.setR_Price(productBean.getPPrice13());
                            } else if (etd.equals("P")) {
                                balance.setR_Price(productBean.getPPrice14());
                            } else if (etd.equals("W")) {
                                balance.setR_Price(productBean.getPPrice15());
                            } else {
                                txtShowETD.setText("E");
                            }
                        }

                        balance.setR_Total(balance.getR_Quan() * balance.getR_Price());
                        balance.setR_PrChkType("");

                        BalanceControl balanceControl = new BalanceControl();
                        String R_Index = balanceControl.getIndexBalance(balance.getR_Table());
                        balance.setR_Index(R_Index);
                        memberBean = null;

                        // for not member
                        balance.setR_PrSubType("");
                        balance.setR_PrSubCode("");
                        balance.setR_PrSubQuan(0);// not member default 0
                        balance.setR_PrSubDisc(0);
                        balance.setR_PrSubBath(0);
                        balance.setR_PrSubAmt(0);
                        balance.setR_QuanCanDisc(balance.getR_Quan());
                        balance.setR_KicPrint("");
                        balance.setR_Pause("P");

                        balanceControl.saveBalance(balance);
                        updateBalanceOptionFromTemp(bBean.getR_Index(), balance.getR_Table(), PCode);

                        //Process stock out
                        String StkRemark = "SAL";
                        String DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());

                        if (productBean.getPStock().equals("Y") && productBean.getPActive().equals("Y")) {
                            PUtility.ProcessStockOut(DocNo, StkCode, balance.getR_PluCode(), new Date(), StkRemark, balance.getR_Quan(), balance.getR_Total(),
                                    balance.getCashier(), balance.getR_Stock(), balance.getR_Set(), R_Index, "1");

                        }

                        //ตัดสต็อกสินค้าที่มี Ingredent
                        try {

                            String sql1 = "select i.*,pdesc,PBPack,pstock,pactive "
                                    + "from product p, pingredent i "
                                    + "where p.pcode=i.pingcode "
                                    + "and i.pcode='" + balance.getR_PluCode() + "' "
                                    + "and PFix='L' and PStock='Y'";
                            Statement stmt2 = mysql.getConnection().createStatement();
                            ResultSet rs1 = stmt2.executeQuery(sql1);
                            while (rs1.next()) {
                                String R_PluCode = rs1.getString("PingCode");
                                double PBPack = rs1.getDouble("PBPack");
                                if (PBPack <= 0) {
                                    PBPack = 1;
                                }
                                double R_QuanIng = (rs1.getDouble("PingQty") * balance.getR_Quan()) / PBPack;
                                double R_Total = 0;
                                System.out.println("ตัดสต็อก " + R_PluCode);
                                if (rs1.getString("pstock").equals("Y") && rs1.getString("pactive").equals("Y")) {
                                    PUtility.ProcessStockOut(DocNo, StkCode, R_PluCode, new Date(), StkRemark, R_QuanIng, R_Total,
                                            balance.getCashier(), "Y", "", "", "");
                                }

                            }

                            rs1.close();
                            stmt2.close();
                        } catch (Exception e) {
                            MSG.ERR(e.getMessage());
                        }
                    }
                }
            }

            //clear tempset
            try {
                String sqlClear = "delete from tempset where PTableNo='" + bBean.getR_Table() + "'";
                Statement stmt2 = mysql.getConnection().createStatement();
                stmt2.executeUpdate(sqlClear);
                stmt2.close();
            } catch (Exception e) {
                MSG.ERR(e.getMessage());
                
            }

            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void updateBalanceOptionFromTemp(String R_Index, String TableNo, String PCode) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from tempset "
                    + "where PIndex='" + R_Index + "' "
                    + "and PCode='" + PCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String opt = ThaiUtil.Unicode2ASCII(rs.getString("POption"));
                String sql1 = "update balance "
                        + "set R_Opt1='" + opt + "',"
                        + "R_LinkIndex='" + R_Index + "' "
                        + "where R_Table='" + TableNo + "' "
                        + "and R_PluCode='" + PCode + "' "
                        + "and R_LinkIndex=''";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sql1);
                stmt1.close();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

    }

    private boolean checkRIndex(String chkRIndex, String code) {
        boolean isCheck = true;//defalse ต้องเป็นถูกไว้ก่อน
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select r_linkindex from balance "
                    + "where r_index='" + chkRIndex + "' "
                    + "and r_linkindex<>''";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isCheck = false;
                String r_linkindex = rs.getString("r_linkindex");
                if (r_linkindex.equals(chkRIndex)) {
                    isCheck = true;
                } else {
                    isCheck = false;
                }
            } else {
                isCheck = true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return isCheck;
    }

    private void cancelItemBeforeHold() {
        BalanceControl bc = new BalanceControl();

        int[] rows = tblShowBalance.getSelectedRows();
        String StkRemark = "SAL";
        Date TDate = new Date();
        String DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        for (int i = 0; i < rows.length; i++) {
            String r_index = "" + tblShowBalance.getValueAt(rows[i], 10);
            try {
                BalanceBean bean = bc.getBalanceIndex(txtTable.getText(), r_index);
                if (bean == null) {
                    continue;
                }

                boolean isMenuSet = false;
                String r_linkIndex = bean.getR_LinkIndex();
                if (r_linkIndex == null || r_linkIndex.equals("null")) {
                    r_linkIndex = "";
                }
                if (!r_linkIndex.equals("") && r_linkIndex != null) {
                    if (!bean.getR_Index().equals(bean.getR_LinkIndex())) {
                        isMenuSet = true;
                        r_index = r_linkIndex;
                    } else {
                        isMenuSet = true;
                    }
                }
//                if (!bean.getR_LinkIndex().equals("")) {
//                    if (!bean.getR_Index().equals(bean.getR_LinkIndex())) {
//                        isMenuSet = true;
//                        r_index = bean.getR_LinkIndex();
//                    } else {
//                        isMenuSet = true;
//                    }
//                }

                String sql = "delete from balance "
                        + "where r_index='" + r_index + "' "
                        + "and r_pause='P' and r_kicprint<>'P'";
                Statement stmt = mysql.getConnection().createStatement();
                String sqldelTempSet = "delete from tempset where PTableNo='" + txtTable.getText() + "';";
                mysql.getConnection().createStatement().executeUpdate(sqldelTempSet);

                int result = stmt.executeUpdate(sql);
//                String sqlDelTempset = "delete from tempset where ptableno='" + txtTable.getText() + "'";
//                mysql.getConnection().createStatement().executeUpdate(sqlDelTempset);
                stmt.close();
                if (result <= 0) {
                    MSG.WAR("รายการอาหาร " + bean.getR_PName() + " ถูกส่งครัวไปแล้ว ไม่สามารถลบออกได้ จะต้อง Void เท่านั้น");
                } else {
                    // ################ โค้ดสำหรับคืน Stock
                    PUtility.ProcessStockOut(DocNo, bean.getStkCode(), bean.getR_PluCode(), TDate, StkRemark, -1 * bean.getR_Quan(),
                            -1 * bean.getR_Total(), bean.getCashier(), bean.getR_Stock(), bean.getR_Set(), bean.getR_Index(), "1");

                    //ตัดสต็อกสินค้าที่มี Ingredent
                    try {
                        String sql1 = "select i.*,pdesc,PBPack "
                                + "from product p, pingredent i "
                                + "where p.pcode=i.pingcode "
                                + "and i.pcode='" + bean.getR_PluCode() + "' "
                                + "and PFix='L' and PStock='Y'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        ResultSet rs = stmt1.executeQuery(sql1);
                        while (rs.next()) {
                            String R_PluCode = rs.getString("PingCode");
                            double PBPack = rs.getDouble("PBPack");
                            if (PBPack <= 0) {
                                PBPack = 1;
                            }
                            double R_QuanIng = (rs.getDouble("PingQty") * bean.getR_Quan());
                            double R_Total = 0;
                            System.out.println("ตัดสต็อก -" + R_PluCode);
                            PUtility.ProcessStockOut(DocNo, bean.getStkCode(), R_PluCode, TDate, StkRemark, -1 * R_QuanIng,
                                    R_Total, bean.getCashier(), "Y", "", "", "");
                        }

                        rs.close();
                        stmt1.close();
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                        
                    }

                    //ตรวจสอบถ้าเป็น menu set ให้ลบข้อมูลภายใน Set ด้วย
                    if (isMenuSet) {
                        try {
                            String sql3 = "select R_Index,R_Stock from balance "
                                    + "where R_LinkIndex='" + r_index + "'";
                            Statement stmt2 = mysql.getConnection().createStatement();
                            ResultSet rs = stmt2.executeQuery(sql3);
                            BalanceControl balanceControl = new BalanceControl();
                            while (rs.next()) {
                                BalanceBean bean2 = balanceControl.getBalanceIndex(txtTable.getText(), rs.getString("R_Index"));
                                String sqlDel = "delete from balance "
                                        + "where r_index='" + rs.getString("R_Index") + "' "
                                        + "and r_pause='P'";
                                Statement stmt3 = mysql.getConnection().createStatement();
                                stmt3.executeUpdate(sqlDel);
                                stmt3.close();
                                PUtility.ProcessStockOut(DocNo, bean2.getStkCode(), bean2.getR_PluCode(), TDate, StkRemark, -1 * bean2.getR_Quan(),
                                        -1 * bean2.getR_Total(), bean2.getCashier(), bean2.getR_Stock(), bean2.getR_Set(), bean2.getR_Index(), "1");

                                //ตัดสต็อกสินค้าที่มี Ingredent
                                try {
                                    String sql1 = "select i.*,pdesc,PBPack "
                                            + "from product p, pingredent i "
                                            + "where p.pcode=i.pingcode "
                                            + "and i.pcode='" + bean2.getR_PluCode() + "' "
                                            + "and PFix='L' and PStock='Y'";
                                    Statement stmt4 = mysql.getConnection().createStatement();
                                    ResultSet rs2 = stmt4.executeQuery(sql1);
                                    while (rs2.next()) {
                                        String R_PluCode = rs2.getString("PingCode");
                                        double PBPack = rs2.getDouble("PBPack");
                                        if (PBPack <= 0) {
                                            PBPack = 1;
                                        }
                                        double R_QuanIng = (rs2.getDouble("PingQty") * bean2.getR_Quan());
                                        double R_Total = 0;
                                        System.out.println("ตัดสต็อก -" + R_PluCode);
                                        PUtility.ProcessStockOut(DocNo, bean2.getStkCode(), R_PluCode, TDate, StkRemark, -1 * R_QuanIng,
                                                R_Total, bean2.getCashier(), "Y", "", "", "");
                                    }

                                    rs2.close();
                                    stmt4.close();
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }
                            }

                            rs.close();
                            stmt2.close();
                        } catch (Exception e) {
                            MSG.ERR(e.getMessage());
                            
                        }
                    }
                    // ################ END คืน Stock 
                }

            } catch (Exception e) {
                MSG.ERR(e.getMessage());
                
            }
        }

        mysql.close();
        //update tablefile
        BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
        txtDiscount.setText("- " + BalanceControl.GetDiscount(txtTable.getText()));
        showSum();
        tblShowPluShow(txtTable.getText());
    }

    private void upDateTableFile() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "UPDATE tablefile SET "
                    + "TOnAct= 'Y',"
                    + "macno='" + Value.MACNO + "' "
                    + "WHERE Tcode='" + txtTable.getText() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
//            dispose();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void CheckKicPrint() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select r_kicprint "
                    + "from balance where r_table='" + tableNo + "'"
                    + " and r_kicprint <> 'P' "
                    + " and R_PName <> ''";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String CheckBillBeforeCash = CONFIG.getP_CheckBillBeforCash();
                if (CheckBillBeforeCash.equals("Y")) {
                    printBillVoidCheck();
                }
//                printBillCheck();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();

        }

    }

    class TimeOfDay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat tf = new SimpleDateFormat("(HH:mm:ss)", Locale.ENGLISH);
            String St = tf.format(new Date());
            //txtShowTime.setText(St);
        }
    }

    public class TableTestFormatRenderer extends DefaultTableCellRenderer {

        private Format formatter;

        public TableTestFormatRenderer(Format formatter) {
            if (formatter == null) {
                throw new NullPointerException();
            }
            this.formatter = formatter;
        }

        @Override
        protected void setValue(Object obj) {
            setText(obj == null ? "" : formatter.format(obj));
        }
    }

//----------SetupMenu-----------------------
    private void setupMenu() { //คำสั่งกำหนด Buttom เมนูอาหาร
        clearPanelMenu();
        final MGRButtonMenu mgr = new MGRButtonMenu();
        POSHW = POSHWSetup.Bean(Value.getMacno());
        loadHeaderMenu();
//        String[] menuAt = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "J"};
        String[] menuAt = new String[]{"A", "B"};
//        final JPanel[] panel = new JPanel[]{pMenu1, pMenu2, pMenu3, pMenu4, pMenu5, pMenu6, pMenu7, pMenu8, pMenu9};
        final JPanel[] panel = new JPanel[]{pMenu1, pMenu2};
        final int sizeButtonFix = 15;
        for (int a = 0; a < menuAt.length; a++) {  //การเพิ่ม Buttom ในเมนู Item
            for (int b = 0; b < sizeButtonFix; b++) {
                String MCode;
                if ((b + 1) < 10) {
                    MCode = menuAt[a] + "0" + (b + 1);
                } else {
                    MCode = menuAt[a] + (b + 1);
                }

                final JButton btn1 = mgr.getButtonLayout(MCode, b);
                btn1.setFocusable(false);
                btn1.setOpaque(false);

                addMouseEvent(btn1, b);
                btn1.addActionListener(new ActionListener() {

//                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean isAllow = false;
                        String customerCount = txtCust.getText();
                        String r_etd = txtShowETD.getText();

                        int cusInt = 0;
                        try {
                            cusInt = Integer.parseInt(customerCount);
                        } catch (NumberFormatException e1) {
                            System.err.println(e1.getMessage());
                        }

                        if (r_etd.equalsIgnoreCase("E") && cusInt > 0) {
                            isAllow = true;
                        } else if (r_etd.equalsIgnoreCase("T") && cusInt > 0) {
                            /**
                             * * OPEN CONNECTION **
                             */
                            MySQLConnect mysql = new MySQLConnect();
                            mysql.open();
                            try {
                                String sql = "select memname from tablefile "
                                        + "where tcode='" + txtTable.getText() + "'";
                                Statement stmt = mysql.getConnection().createStatement();
                                ResultSet rs = stmt.executeQuery(sql);
                                if (rs.next()) {
                                    isAllow = true;
                                } else {
                                    isAllow = false;
                                }

                                rs.close();
                                stmt.close();
                            } catch (SQLException e1) {
                                MSG.ERR(e1.getMessage());
                            }
                        } else if (r_etd.equalsIgnoreCase("D") && cusInt > 0) {
                            /**
                             * * OPEN CONNECTION **
                             */
                            MySQLConnect mysql = new MySQLConnect();
                            mysql.open();
                            try {
                                String sql = "select memname from tablefile "
                                        + "where tcode='" + txtTable.getText() + "'";
                                Statement stmt = mysql.getConnection().createStatement();
                                ResultSet rs = stmt.executeQuery(sql);
                                if (rs.next()) {
                                    isAllow = true;
                                } else {
                                    isAllow = false;
                                }

                                rs.close();
                                stmt.close();
                            } catch (SQLException e1) {
                                MSG.ERR(e1.getMessage());
                            }
                        }

                        if (isAllow) {
                            if (btn1.getText() == null) {
                                return;
                            }
                            if (!showPopupOption(btn1.getName())) {
                                return;
                            }
                            String[] data = checkGroup(btn1.getName());

                            if (data[0].equals("0")) {
                                addHistory(tbpMain.getSelectedIndex());
                                pSubMenu1.removeAll();

                                String MCode2;
                                for (int c = 0; c < sizeButtonFix; c++) {
                                    if ((c + 1) < 10) {
                                        MCode2 = btn1.getName() + "0" + (c + 1);
                                    } else {
                                        MCode2 = btn1.getName() + (c + 1);
                                    }

                                    final JButton btn2 = mgr.getButtonLayout(MCode2, c);
                                    btn2.setFocusable(false);
                                    addMouseEvent(btn2, c);
                                    btn2.setName(MCode2);
                                    pSubMenu1.add(btn2);
                                    tbpMain.setSelectedIndex(9);

                                    btn2.addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (btn2.getText() == null) {
                                                return;
                                            }

                                            if (!showPopupOption(btn2.getName())) {
                                                return;
                                            }

//                                          }
                                            String[] data = checkGroup(btn2.getName());

                                            if (data[0].equals("0")) {
                                                addHistory(9);
                                                addHistory(tbpMain.getSelectedIndex());
                                                pSubMenu2.removeAll();

                                                String MCode3;
                                                for (int d = 0; d < sizeButtonFix; d++) {
                                                    if ((d + 1) < 10) {
                                                        MCode3 = btn2.getName() + "0" + (d + 1);
                                                    } else {
                                                        MCode3 = btn2.getName() + (d + 1);
                                                    }

                                                    final JButton btn3 = mgr.getButtonLayout(MCode3, d);
                                                    btn3.setFocusable(false);
                                                    addMouseEvent(btn3, d);
                                                    btn3.setName(MCode3);
                                                    pSubMenu2.add(btn3);
                                                    tbpMain.setSelectedIndex(10);

                                                    btn3.addActionListener(new ActionListener() {

                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (btn3.getText() == null) {
                                                                return;
                                                            }
                                                            if (!showPopupOption(btn3.getName())) {
                                                                return;
                                                            }
                                                            String[] data = checkGroup(btn3.getName());
                                                            if (data[0].equals("0")) {
                                                                addHistory(10);
                                                                addHistory(tbpMain.getSelectedIndex());
                                                                pSubMenu3.removeAll();

                                                                String MCode4;
                                                                for (int f = 0; f < sizeButtonFix; f++) {
                                                                    if ((f + 1) < 10) {
                                                                        MCode4 = btn3.getName() + "0" + (f + 1);
                                                                    } else {
                                                                        MCode4 = btn3.getName() + (f + 1);
                                                                    }
                                                                    //setupMenu();
                                                                    final JButton btn4 = mgr.getButtonLayout(MCode4, f);
                                                                    btn4.setFocusable(false);
                                                                    addMouseEvent(btn4, f);
                                                                    btn4.setName(MCode4);
                                                                    btn4.addActionListener(new ActionListener() {

                                                                        @Override
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            if (btn4.getText() == null) {
                                                                                return;
                                                                            }

                                                                            if (!showPopupOption(btn4.getName())) {
                                                                                return;
                                                                            }

                                                                            String[] data = checkGroup(btn4.getName());

                                                                            if (data[0].equals("1") && !data[1].equals("")) {
                                                                                //add Product
                                                                                String PCode = data[1];
                                                                                addProductFromButtonMenu(PCode);
                                                                            }
                                                                        }
                                                                    });
                                                                    pSubMenu3.add(btn4);
                                                                    tbpMain.setSelectedIndex(11);
                                                                }

                                                                JButton btnBack = new JButton("กลับ");
                                                                btnBack.setFocusable(false);
                                                                btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                                                                btnBack.setBackground(Color.RED);
                                                                btnBack.addActionListener(new ActionListener() {

                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        backHistory();
                                                                    }
                                                                });
                                                                pSubMenu3.add(btnBack);
                                                            } else {
                                                                // add product
                                                                if (data[0].equals("1")) {
                                                                    String PCode = data[1];
                                                                    addProductFromButtonMenu(PCode);
                                                                }
                                                            }
                                                        }

                                                    });
                                                }

                                                JButton btnBack = new JButton("กลับ");
                                                btnBack.setFocusable(false);
                                                btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                                                btnBack.setBackground(Color.RED);
                                                btnBack.addActionListener(new ActionListener() {

                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        backHistory();
                                                    }
                                                });
                                                pSubMenu2.add(btnBack);
//                                                pSubMenu2.add(new JButton());
                                            } else {
                                                //add product
                                                if (data[0].equals("1")) {
                                                    String PCode = data[1];
                                                    addProductFromButtonMenu(PCode);
                                                    //UpdateTemPSet();
                                                }
                                            }
                                        }
                                    });
                                }

                                JButton btnBack = new JButton("กลับ");
                                btnBack.setFocusable(false);
                                btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                                btnBack.setBackground(Color.RED);
                                btnBack.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        backHistory();
                                    }
                                });
                                pSubMenu1.add(btnBack);
//                                pSubMenu1.add(new JButton());

                            } else {
                                //add product
                                if (data[0].equals("1")) {
                                    String PCode = data[1];
                                    addProductFromButtonMenu(PCode);
                                }
                            }
                        } else {//ระบุจำนวนลูกค้า หากเปิดโต๊ะใหม่
                            //JOptionPane.showMessageDialog(null, "กรุณาระบุจำนวนลูกค้า");

                        }
                    }

                    private String[] checkGroup(String MenuCode) {
                        String[] data = new String[]{"", ""};
                        /**
                         * * OPEN CONNECTION **
                         */
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "select MenuType, PCode "
                                    + "from soft_menusetup where MenuCode='" + MenuCode + "'";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            if (rs.next()) {
                                String MenuType = rs.getString("MenuType");
                                String PCode = rs.getString("PCode");
                                data[0] = MenuType;
                                data[1] = PCode;
                            }

                            rs.close();
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                        } finally {
                            mysql.close();
                        }

                        return data;
                    }

                    private void addProductFromButtonMenu(String PCode) {
                        txtPluCode.setText(txtPluCode.getText().trim() + "*" + PCode);
                        if (findPluCode()) {
                            if (PublicVar.P_Status.equals("S")) {
                                getPrice();
                            } else {
                                //สามารถเลือกจำนวนได้เลย
                                double qtySet = 0;
                                if (Value.autoqty) {
                                    GetQty frm = new GetQty(null, true, txtPluCode.getText());
                                    frm.setVisible(true);

                                    qtySet = frm.ReturnQty;
                                } else {
                                    qtySet = PublicVar.P_Qty;
                                }

                                if (!txtPluCode.getText().trim().equals("")) {
                                    if (qtySet > 0) {
                                        txtPluCode.setText("" + qtySet + "*" + PCode);
                                        if (seekPluCode()) {
                                            if (PublicVar.P_Status.equals("S")) {
                                                getPrice();
                                            } else {
                                                txtPluCodeOnExit();
                                            }
                                        }
                                    } else {
                                        txtPluCode.setText("");
                                        txtPluCode.requestFocus();
                                    }
                                }
                            }
                        }
                    }

                });
                panel[a].add(btn1);
            }

            panel[a].add(new JButton());
        }

    }

    private void addHistory(int index) {
        int size = historyBack.size();
        boolean isExists = false;
        for (int i = 0; i < size; i++) {
            int a = (Integer) historyBack.get(i);
            if (a == index) {
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            historyBack.add(index);
        }
    }

    private void backHistory() {
        int size = historyBack.size();
        if (size > 0) {
            tbpMain.setSelectedIndex(historyBack.get(size - 1));
            historyBack.remove(size - 1);
        } else {
            clearHistory();
            tbpMain.setSelectedIndex(0);
        }
    }

    private void loadHeaderMenu() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
//            String sql = "SELECT * FROM company limit 1";
            String sql = "SELECT * FROM company limit 1";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                for (int i = 0; i < 9; i++) {
                    String h = ThaiUtil.ASCII2Unicode(rs.getString("head" + (i + 1)));
                    tbpMain.setTitleAt(i, h);
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        } finally {
            mysql.close();
        }

    }

    private void printBillCheck() {
        if (Value.useprint) {
            PPrint print = new PPrint();
            print.PrintCheckBill(txtTable.getText());
        } else {
            JOptionPane.showMessageDialog(this, "ระบบไม่ได้กำหนดให้ใช้งานเครื่องพิมพ์ !!!" + Value.useprint);
        }
    }

    private void printBillVoidCheck() {
        if (Value.useprint) {
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                String sql = "select * from balance "
                        + "where r_table='" + tableNo + "' "
                        + "and r_void='V'";
                Statement stmt = mysql.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    PPrint print = new PPrint();
                    print.PrintVoidBill(tableNo);
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
            } finally {
                mysql.close();
            }
        }
    }

    private boolean showPopupOption(String MenuCode) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select n.PCode,n.MenuShowText from optionset o,soft_menusetup n "
                    + "where o.pcode = n.pcode and n.menucode='" + MenuCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String pcode = rs.getString("PCode");
                String pname = ThaiUtil.Unicode2ASCII(rs.getString("MenuShowText"));
                String main = "main";

                ModalPopup popup = new ModalPopup(null, true, pcode, pname, tableNo, main, MenuCode);
                popup.setVisible(true);
            } else {
                try {
                    String sqll = "select o.PCode,o.PDesc, o.check_before from mgrbuttonsetup o,soft_menusetup n "
                            + "where o.pcode = n.pcode and n.menucode='" + MenuCode + "'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rss = stmt1.executeQuery(sqll);
                    if (rss.next()) {
                        String PCode = rss.getString("PCode");

                        //check before order foods
                        boolean checkBefore = rss.getString("Check_before").equals("Y");
                        boolean passBefore;
                        if (checkBefore) {
                            try {
                                String sqlChkTable = "select * from balance "
                                        + "where r_table='" + txtTable.getText() + "'";
                                Statement stmt2 = mysql.getConnection().createStatement();
                                ResultSet rsTb = stmt2.executeQuery(sqlChkTable);
                                if (rsTb.next()) {
                                    passBefore = true;
                                } else {
                                    passBefore = false;
                                }

                                rsTb.close();
                                stmt2.close();
                            } catch (SQLException e) {
                                MSG.ERR(e.getMessage());
                                passBefore = false;
                            }

                            if (!passBefore) {
                                MSG.WAR("ไม่มีรายการอาหาร กรุณาเลือกเมนูอาหารหลักก่อน");
                                passBefore = false;

                                return false;
                            }
                        }
                        //end before order foods

                        //check auto add before
                        boolean isAutoAdd = false;
                        String pstock = PUtility.GetStkCode();
                        try {
                            String sqlA1 = "select * from mgrbuttonsetup "
                                    + "where pcode='" + PCode + "' and auto_pcode<>''";
                            Statement stmt3 = mysql.getConnection().createStatement();
                            ResultSet rsA1 = stmt3.executeQuery(sqlA1);
                            while (rsA1.next()) {
                                isAutoAdd = true;
                                
                                TempSetBean tempBean = new TempSetBean();
                                tempBean.setPTableNo(txtTable.getText());
                                tempBean.setPIndex("");
                                tempBean.setPCode(rsA1.getString("auto_pcode"));
                                tempBean.setPDesc(rsA1.getString("auto_pdesc"));
                                tempBean.setPPostStock(pstock);
                                tempBean.setPProTry("auto");
                                tempBean.setPOption("");
                                tempBean.setPTime("curtime()");
                                
                                TempSetController.save(tempBean, mysql);
                            }

                            rsA1.close();
                            stmt3.close();
                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                        }

                        //end loop autoadd
                        String pname = ThaiUtil.Unicode2ASCII(rss.getString("PDesc"));
                        String main = "main";
                        updateTempmenuset("", PCode, pname, "", main);

                        if (!isAutoAdd) {
                            ModalPopup popup = new ModalPopup(null, true, PCode, pname, tableNo, main, MenuCode);
                            popup.setVisible(true);
                        }
                    }
                    rss.close();
                    stmt1.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return true;
    }

    private void updateTempmenuset(String Index, String PCode, String PName, String Option, String TryName) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            TempSetBean tempBean = new TempSetBean();
            tempBean.setPTableNo(tableNo);
            tempBean.setPIndex(Index);
            tempBean.setPCode(PCode);
            tempBean.setPDesc(PName);
            tempBean.setPPostStock(PUtility.GetStkCode());
            tempBean.setPProTry(TryName);
            tempBean.setPOption(Option);
            tempBean.setPTime("curtime()");

            TempSetController.save(tempBean, mysql);
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void showCustomerInput() {
        String r_etd = txtShowETD.getText();
        String customerCount = txtCust.getText();
        try {
            int cc = Integer.parseInt(customerCount);
            if (cc == 0 && r_etd.equalsIgnoreCase("E")) {
                CustomerCountDialog ccd = new CustomerCountDialog(null, true, txtTable.getText(), txtShowETD.getText());
                ccd.setVisible(true);

                txtCust.setText("" + ccd.getCountCustomer());
                int custCount = ccd.getCountCustomer();
                if (r_etd.equalsIgnoreCase("T")) {
                    txtCustOnExit();
                } else {
                    if (custCount > 0) {
                        txtCustOnExit();
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
