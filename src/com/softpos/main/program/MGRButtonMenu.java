package com.softpos.main.program;

import database.MySQLConnect;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import setupmenu.DlgBrowseProduct;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class MGRButtonMenu extends javax.swing.JDialog {

    private String menuCode;
    private int menuIndex;
    private boolean editOK = false;
    private MenuMGR mgr;

    public MGRButtonMenu(java.awt.Frame parent, boolean modal, String menuCode, int menuIndex) {
        super(parent, modal);
        initComponents();

        this.menuCode = menuCode;
        this.menuIndex = menuIndex;

        loadInit(menuCode, menuIndex);

        if (menuCode.length() == 9) {
            cbTypeMenu.setSelectedIndex(1);
            cbTypeMenu.setEnabled(false);
        }
    }

    public MGRButtonMenu() {

    }

    public boolean getOK() {
        return editOK;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        cbTypeMenu = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtShortName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonLayout3 = new javax.swing.JButton();
        buttonLayout2 = new javax.swing.JButton();
        buttonLayout6 = new javax.swing.JButton();
        buttonLayout7 = new javax.swing.JButton();
        buttonLayout4 = new javax.swing.JButton();
        buttonLayout5 = new javax.swing.JButton();
        buttonLayout1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtPathIMG = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnFindIMG = new javax.swing.JButton();
        cbLayoutMenu = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        chkSaveTemplateAll = new javax.swing.JCheckBox();
        btnDelImg = new javax.swing.JButton();
        txtImgSize = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtFontSize = new javax.swing.JTextField();
        btnFontColor = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBGColor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        chkFontBold = new javax.swing.JCheckBox();
        chkFontItalic = new javax.swing.JCheckBox();
        chkFontBoldAndItalic = new javax.swing.JCheckBox();
        cbFontList = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtPCode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnFindProduct = new javax.swing.JButton();
        txtPDesc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("กำหนดคุณสมบัติปุ่มเมนูใช้งานลัด");
        setUndecorated(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        cbTypeMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTypeMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "กลุ่มเมนู (Group)", "เมนูหลัก (Code)" }));
        cbTypeMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTypeMenuItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ชื่อเล่น");

        txtShortName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtShortName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtShortNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtShortNameKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("(ไม่ควรเกิน 30 ตัวอักษร)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("เลือกประเภท");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ใส่ภาพพื้นหลัง และ Layout", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(731, 296));

        buttonLayout3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout3.setForeground(new java.awt.Color(255, 255, 0));
        buttonLayout3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temp_menu.png"))); // NOI18N
        buttonLayout3.setText("ตัวอย่าง (3)");
        buttonLayout3.setToolTipText("แสดงชื่ออยู่ตรงกลาง");
        buttonLayout3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout3ActionPerformed(evt);
            }
        });

        buttonLayout2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temp_menu.png"))); // NOI18N
        buttonLayout2.setText("ตัวอย่าง (2)");
        buttonLayout2.setToolTipText("แสดงชื่ออยู่ด้านบน");
        buttonLayout2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        buttonLayout2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        buttonLayout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout2ActionPerformed(evt);
            }
        });

        buttonLayout6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temp_menu.png"))); // NOI18N
        buttonLayout6.setToolTipText("แสดงเฉพาะรูปภาพ");
        buttonLayout6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonLayout6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout6ActionPerformed(evt);
            }
        });

        buttonLayout7.setBackground(new java.awt.Color(0, 0, 0));
        buttonLayout7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temp_menu.png"))); // NOI18N
        buttonLayout7.setText("ตัวอย่าง (7)");
        buttonLayout7.setToolTipText("แสดงรูปภาพพร้อมพื้นหลัง");
        buttonLayout7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        buttonLayout7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonLayout7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout7ActionPerformed(evt);
            }
        });

        buttonLayout4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout4.setForeground(new java.awt.Color(0, 0, 204));
        buttonLayout4.setText("ตัวอย่าง (4)");
        buttonLayout4.setToolTipText("แสดงเฉพาะตัวหนังสือ");
        buttonLayout4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout4ActionPerformed(evt);
            }
        });

        buttonLayout5.setBackground(new java.awt.Color(255, 153, 51));
        buttonLayout5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout5.setForeground(new java.awt.Color(0, 0, 204));
        buttonLayout5.setText("ตัวอย่าง (5)");
        buttonLayout5.setToolTipText("แสดงตัวหนังสือพร้อมพื้นหลัง");
        buttonLayout5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout5ActionPerformed(evt);
            }
        });

        buttonLayout1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonLayout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/temp_menu.png"))); // NOI18N
        buttonLayout1.setText("ตัวอย่าง (1)");
        buttonLayout1.setToolTipText("แสดงชื่ออยู่ด้านล่าง");
        buttonLayout1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLayout1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        buttonLayout1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonLayout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLayout1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ตัวอย่าง (6)");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPathIMG.setEditable(false);
        txtPathIMG.setBackground(new java.awt.Color(255, 255, 255));
        txtPathIMG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("เลือกรูปภาพสินค้า");

        btnFindIMG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFindIMG.setText("เลือก");
        btnFindIMG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindIMGActionPerformed(evt);
            }
        });

        cbLayoutMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbLayoutMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ตัวอย่างที่ 1", "ตัวอย่างที่ 2", "ตัวอย่างที่ 3", "ตัวอย่างที่ 4", "ตัวอย่างที่ 5", "ตัวอย่างที่ 6", "ตัวอย่างที่ 7" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("เลือก Layout Menu");

        chkSaveTemplateAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkSaveTemplateAll.setText("ให้ใช้กับทุกปุ่มที่มีลักษณะเดียวกัน");

        btnDelImg.setBackground(new java.awt.Color(255, 0, 51));
        btnDelImg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelImg.setText("ลบ");
        btnDelImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelImgActionPerformed(evt);
            }
        });

        txtImgSize.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtImgSize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImgSize.setText("125");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("ขนาดรูปภาพ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtImgSize)
                        .addGap(195, 195, 195))
                    .addComponent(cbLayoutMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPathIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelImg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindIMG, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkSaveTemplateAll)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPathIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btnFindIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLayoutMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImgSize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSaveTemplateAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonLayout1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLayout2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLayout3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLayout4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLayout5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonLayout6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLayout7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonLayout4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonLayout5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonLayout1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLayout2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLayout3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonLayout7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonLayout6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "คุณสมบัติของปุ่ม", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFontSize.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFontSize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFontSize.setText("12");
        txtFontSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFontSizeKeyReleased(evt);
            }
        });

        btnFontColor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFontColor.setText("เลือกสี");
        btnFontColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFontColorActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("แบบตัวอักษร");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("สีพื้นหลัง");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("สีตัวอักษร");

        btnBGColor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBGColor.setText("เลือกสี");
        btnBGColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBGColorActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("ขนาด");

        chkFontBold.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkFontBold.setText("หนา (Bold)");
        chkFontBold.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFontBoldItemStateChanged(evt);
            }
        });

        chkFontItalic.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        chkFontItalic.setText("เอียง (Italic)");
        chkFontItalic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFontItalicItemStateChanged(evt);
            }
        });

        chkFontBoldAndItalic.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        chkFontBoldAndItalic.setText("หนา และเอียง (Bold and Italic)");
        chkFontBoldAndItalic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFontBoldAndItalicItemStateChanged(evt);
            }
        });

        cbFontList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFontList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tahoma" }));
        cbFontList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFontListItemStateChanged(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("บันทึก / Exit");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        btnSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSaveKeyPressed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("ลบปุ่ม (Delete)");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setText("ยกเลิก (Cancel)");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbFontList, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFontColor, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFontSize, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBGColor, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkFontBold)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkFontItalic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkFontBoldAndItalic)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(btnBGColor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFontBold)
                    .addComponent(chkFontItalic)
                    .addComponent(chkFontBoldAndItalic)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFontList, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnFontColor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtPCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPCode.setEnabled(false);
        txtPCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCodeKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("รหัสสินค้า");

        btnFindProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFindProduct.setText("ค้นหา");
        btnFindProduct.setEnabled(false);
        btnFindProduct.setOpaque(false);
        btnFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProductActionPerformed(evt);
            }
        });

        txtPDesc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPDesc.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbTypeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPDesc)
                                .addGap(18, 18, 18)
                                .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTypeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete(menuCode);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbTypeMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTypeMenuItemStateChanged
        if (cbTypeMenu.getSelectedIndex() == 0) {
            txtPCode.setEnabled(false);
            btnFindProduct.setEnabled(false);
        } else {
            txtPCode.setEnabled(true);
            btnFindProduct.setEnabled(true);

            txtPCode.requestFocus();
        }
    }//GEN-LAST:event_cbTypeMenuItemStateChanged

    private void txtPCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtShortName.requestFocus();
            Selectmenu();
        }
    }//GEN-LAST:event_txtPCodeKeyPressed

    private void txtShortNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShortNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSave.requestFocus();
        }
    }//GEN-LAST:event_txtShortNameKeyPressed

    private void btnSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSaveKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            save();
        }
    }//GEN-LAST:event_btnSaveKeyPressed

    private void btnBGColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBGColorActionPerformed
        Color color = JColorChooser.showDialog(this, "Setting Font Color.", btnBGColor.getBackground());
        btnBGColor.setBackground(color);

        autoPreview();
    }//GEN-LAST:event_btnBGColorActionPerformed

    private void btnFontColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFontColorActionPerformed
        Color color = JColorChooser.showDialog(this, "Setting Font Color.", btnFontColor.getForeground());
        btnFontColor.setForeground(color);

        autoPreview();
    }//GEN-LAST:event_btnFontColorActionPerformed

    private void btnFindIMGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindIMGActionPerformed
        JFileChooser chooser = new JFileChooser();
        // select home
        File file = new File("~");
        chooser.setCurrentDirectory(file);
        chooser.setMultiSelectionEnabled(false);
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                boolean success;
                success = f.getName().toLowerCase().endsWith(".gif")
                        || f.getName().toLowerCase().endsWith(".jpg")
                        || f.getName().toLowerCase().endsWith(".png")
                        || f.isDirectory();
                return success;
            }

            @Override
            public String getDescription() {
                return "gif, jpg, png";
            }
        };
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showSaveDialog(this);
        File selected = chooser.getSelectedFile();
        if (selected != null) {
            txtPathIMG.setText(selected.getPath());

            autoPreview();
        }
    }//GEN-LAST:event_btnFindIMGActionPerformed

    private void txtFontSizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFontSizeKeyReleased
        autoPreview();
    }//GEN-LAST:event_txtFontSizeKeyReleased

    private void txtShortNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShortNameKeyReleased
        autoPreview();
    }//GEN-LAST:event_txtShortNameKeyReleased

    private void cbFontListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFontListItemStateChanged
        autoPreview();
    }//GEN-LAST:event_cbFontListItemStateChanged

    private void chkFontBoldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFontBoldItemStateChanged
        autoPreview();
    }//GEN-LAST:event_chkFontBoldItemStateChanged

    private void chkFontItalicItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFontItalicItemStateChanged
        autoPreview();
    }//GEN-LAST:event_chkFontItalicItemStateChanged

    private void chkFontBoldAndItalicItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFontBoldAndItalicItemStateChanged
        autoPreview();
    }//GEN-LAST:event_chkFontBoldAndItalicItemStateChanged

    private void buttonLayout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout1ActionPerformed
        cbLayoutMenu.setSelectedIndex(0);
    }//GEN-LAST:event_buttonLayout1ActionPerformed

    private void buttonLayout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout2ActionPerformed
        cbLayoutMenu.setSelectedIndex(1);
    }//GEN-LAST:event_buttonLayout2ActionPerformed

    private void buttonLayout3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout3ActionPerformed
        cbLayoutMenu.setSelectedIndex(2);
    }//GEN-LAST:event_buttonLayout3ActionPerformed

    private void buttonLayout4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout4ActionPerformed
        cbLayoutMenu.setSelectedIndex(3);
    }//GEN-LAST:event_buttonLayout4ActionPerformed

    private void buttonLayout5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout5ActionPerformed
        cbLayoutMenu.setSelectedIndex(4);
    }//GEN-LAST:event_buttonLayout5ActionPerformed

    private void buttonLayout6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout6ActionPerformed
        cbLayoutMenu.setSelectedIndex(5);
    }//GEN-LAST:event_buttonLayout6ActionPerformed

    private void buttonLayout7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLayout7ActionPerformed
        cbLayoutMenu.setSelectedIndex(6);
    }//GEN-LAST:event_buttonLayout7ActionPerformed

    private void btnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProductActionPerformed
        DlgBrowseProduct browse = new DlgBrowseProduct(null, true);
        browse.setVisible(true);
        if (browse.getSelectPlu() != null) {
            txtPCode.setText(browse.getSelectPlu().getCode());
            txtPDesc.setText(browse.getSelectPlu().getName());

            txtShortName.setText(browse.getSelectPlu().getName());
            txtShortName.requestFocus();
        }
    }//GEN-LAST:event_btnFindProductActionPerformed

    private void btnDelImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelImgActionPerformed
        txtPathIMG.setText("");
    }//GEN-LAST:event_btnDelImgActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MySQLConnect();
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
            java.util.logging.Logger.getLogger(MGRButtonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MGRButtonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MGRButtonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MGRButtonMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MGRButtonMenu dialog = new MGRButtonMenu(new javax.swing.JFrame(), true, "A01I01", 1);
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
    private javax.swing.JButton btnBGColor;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelImg;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFindIMG;
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnFontColor;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton buttonLayout1;
    private javax.swing.JButton buttonLayout2;
    private javax.swing.JButton buttonLayout3;
    private javax.swing.JButton buttonLayout4;
    private javax.swing.JButton buttonLayout5;
    private javax.swing.JButton buttonLayout6;
    private javax.swing.JButton buttonLayout7;
    private javax.swing.JComboBox cbFontList;
    private javax.swing.JComboBox cbLayoutMenu;
    private javax.swing.JComboBox cbTypeMenu;
    private javax.swing.JCheckBox chkFontBold;
    private javax.swing.JCheckBox chkFontBoldAndItalic;
    private javax.swing.JCheckBox chkFontItalic;
    private javax.swing.JCheckBox chkSaveTemplateAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtFontSize;
    private javax.swing.JTextField txtImgSize;
    private javax.swing.JTextField txtPCode;
    private javax.swing.JTextField txtPDesc;
    private javax.swing.JTextField txtPathIMG;
    private javax.swing.JTextField txtShortName;
    // End of variables declaration//GEN-END:variables

    public JButton getButtonLayout(String menuCode, int menuIndex) {

        MenuMGR m = new MenuMGR();
        int fontType = Font.PLAIN;
        int layout = 0;

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from soft_menusetup "
                    + "where MenuCode='" + menuCode + "' "
                    + "and M_Index='" + menuIndex + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                m.setMenuCode(rs.getString("MenuCode"));
                m.setMenuType(rs.getInt("MenuType"));
                m.setPCode(rs.getString("PCode"));
                m.setMenuShowText(ThaiUtil.ASCII2Unicode(rs.getString("MenuShowText")));
                m.setIMG(rs.getString("IMG"));
                m.setFontColor(rs.getString("FontColor"));
                m.setBGColor(rs.getString("BGColor"));
                m.setLayout(rs.getInt("Layout"));
                m.setFontSize(rs.getInt("FontSize"));
                m.setFontName(rs.getString("FontName"));
                m.setMIndex(rs.getInt("M_Index"));
                m.setFontAttr(rs.getString("FontAttr"));
                m.setImgSize(rs.getInt("IMG_SIZE"));
                layout = m.getLayout();

                if (m.getFontAttr().equals("B")) {
                    fontType = Font.BOLD;
                } else if (m.getFontAttr().equals("I")) {
                    fontType = Font.ITALIC;
                } else if (m.getFontAttr().equals("BI")) {
                    fontType = Font.BOLD | Font.ITALIC;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        JButton button = new JButton("");
        button.setName(menuCode);
        if (m.getIMG() == null) {
            m.setIMG("");
        }
        if (layout == 0) {
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            if (!m.getIMG().equals("")) {
                updateIconFull(button, m.getIMG(), m.getImgSize());
            }
            button.setText("<html>" + m.getMenuShowText() + "</html>");
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.TOP);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setForeground(getColorFormat(m.getFontColor()));
        } else if (layout == 1) {
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            if (!m.getIMG().equals("")) {
                updateIconFull(button, m.getIMG(), m.getImgSize());
            }
            button.setText("<html>" + m.getMenuShowText() + "</html>");
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.BOTTOM);
            button.setVerticalTextPosition(SwingConstants.TOP);
            button.setForeground(getColorFormat(m.getFontColor()));
        } else if (layout == 2) {
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            button.setForeground(getColorFormat(m.getFontColor()));
            if (!m.getIMG().equals("")) {
                updateIconFull(button, m.getIMG(), m.getImgSize());
            }
            button.setText("<html>" + m.getMenuShowText() + "</html>");
            button.setHorizontalTextPosition(SwingConstants.CENTER);
        } else if (layout == 3) {
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            button.setForeground(getColorFormat(m.getFontColor()));
            button.setText("<html><center>" + m.getMenuShowText() + "</center></html>");
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            if (!m.getIMG().equals("")) {
                updateIconFull(button, m.getIMG(), m.getImgSize());
            }
        } else if (layout == 4) {
            button.setBackground(getColorFormat(m.getBGColor()));
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            button.setForeground(getColorFormat(m.getFontColor()));
            button.setText("<html>" + m.getMenuShowText() + "</html>");
            button.setHorizontalTextPosition(SwingConstants.CENTER);
        } else if (layout == 5) {
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            if (!m.getIMG().equals("")) {
                updateIconFull(button, m.getIMG(), m.getImgSize());
            }
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
        } else if (layout == 6) {
            button.setBackground(getColorFormat(m.getBGColor()));
            button.setFont(new Font(m.getFontName(), fontType, m.getFontSize()));
            if (!m.getIMG().equals("")) {
                updateIconFull(button, m.getIMG(), m.getImgSize());
            }
            button.setText("<html>" + m.getMenuShowText() + "</html>");
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.TOP);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setForeground(getColorFormat(m.getFontColor()));
        }

        //check empty text name
        if (button.getText().trim().equals("<html>null</html>")) {
//            button.setBackground(Color.PINK);
            button.setOpaque(false);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }

        return button;
    }

    private void save() {
        //set value;
        MenuMGR mgr = new MenuMGR();
        mgr.setMIndex(menuIndex);
        mgr.setMenuCode(menuCode);
        mgr.setMenuType(cbTypeMenu.getSelectedIndex());
        mgr.setPCode(txtPCode.getText());
        mgr.setMenuShowText(txtShortName.getText());

        String imgPath = txtPathIMG.getText();
        imgPath = imgPath.replace("\\", "/");
        mgr.setIMG(imgPath);

        mgr.setFontColor(getColorButtonText(btnFontColor));
        mgr.setBGColor(getColorButtonBG(btnBGColor));

        mgr.setLayout(cbLayoutMenu.getSelectedIndex());
        mgr.setFontSize(Integer.parseInt(txtFontSize.getText()));
        mgr.setFontName(cbFontList.getSelectedItem().toString());
        mgr.setImgSize(Integer.parseInt(txtImgSize.getText()));

        String fontAttr;
        if (chkFontBold.isSelected()) {
            fontAttr = "B";
        } else if (chkFontItalic.isSelected()) {
            fontAttr = "I";
        } else if (chkFontBoldAndItalic.isSelected()) {
            fontAttr = "BI";
        } else {
            fontAttr = "P";
        }
        mgr.setFontAttr(fontAttr);

        mgr.setOptSet("N");
        mgr.setPSet("N");

        if (cbTypeMenu.getSelectedIndex() == 0) {
            if (txtShortName.getText().trim().equals("")) {
                txtShortName.requestFocus();
            } else {
                saveMenu(mgr);
                if (chkSaveTemplateAll.isSelected()) {
                    saveMenuAll(mgr);
                }
                editOK = true;
                dispose();
            }
        } else {
            if (txtPCode.getText().trim().equals("")) {
                txtPCode.requestFocus();
            } else {
                saveMenu(mgr);
                if (chkSaveTemplateAll.isSelected()) {
                    saveMenuAll(mgr);
                }
                editOK = true;
                dispose();
            }
        }
    }

    private void delete(String menucode) {
        String ShortName = txtShortName.getText();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from soft_menusetup "
                    + "where MenuCode ='" + menucode + "'"
                    + "AND MenuShowText='" + ThaiUtil.Unicode2ASCII(ShortName) + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            MSG.WAR(e.getMessage());
        } finally {
            mysql.close();
        }

        dispose();
    }

    private void saveMenu(MenuMGR mgr) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into soft_menusetup"
                    + "(MenuCode,MenuType,OptSet,PSet,PCode,MenuShowText,"
                    + "IMG,FontColor,BGColor,Layout,"
                    + "FontSize,FontName,FontAttr,M_Index,IMG_SIZE) "
                    + "values("
                    + "'" + mgr.getMenuCode() + "','" + mgr.getMenuType() + "','" + mgr.getOptSet() + "','" + mgr.getPSet() + "',"
                    + "'" + mgr.getPCode() + "','" + ThaiUtil.Unicode2ASCII(mgr.getMenuShowText()) + "',"
                    + "'" + mgr.getIMG() + "','" + mgr.getFontColor() + "','" + mgr.getBGColor() + "','" + mgr.getLayout() + "',"
                    + "'" + mgr.getFontSize() + "','" + mgr.getFontName() + "','" + mgr.getFontAttr() + "'"
                    + ",'" + mgr.getMIndex() + "','" + mgr.getImgSize() + "');";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("delete from soft_menusetup where MenuCode='" + mgr.getMenuCode() + "'");
            if (stmt.executeUpdate(sql) > 0) {
                JOptionPane.showMessageDialog(this, "บันทึกข้อมูลเมนูเรียบร้อยแล้ว");
                dispose();
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
            mysql.close();
        }
    }

    private void saveMenuAll(MenuMGR mgr) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update soft_menusetup "
                    + "set "
                    + "FontColor='" + mgr.getFontColor() + "',"
                    + "BGColor='" + mgr.getBGColor() + "',"
                    + "Layout='" + mgr.getLayout() + "',"
                    + "FontSize='" + mgr.getFontSize() + "',"
                    + "FontName='" + mgr.getFontName() + "',"
                    + "FontAttr='" + mgr.getFontAttr() + "' "
                    + "where MenuType='" + mgr.getMenuType() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            if (stmt.executeUpdate(sql) > 0) {
                stmt.close();
                JOptionPane.showMessageDialog(this, "บันทึกข้อมูลเมนูเรียบร้อยแล้ว");
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }finally{
            mysql.close();
        }
    }

    private String getColorButtonBG(JButton btn) {
        int red = btn.getBackground().getRed();
        int green = btn.getBackground().getGreen();
        int blue = btn.getBackground().getBlue();

        return red + "," + green + "," + blue;
    }

    private String getColorButtonText(JButton btn) {
        int red = btn.getForeground().getRed();
        int green = btn.getForeground().getGreen();
        int blue = btn.getForeground().getBlue();

        return red + "," + green + "," + blue;
    }

    private void loadInit(String menuCode, int menuIndex) {
        loadFontList();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from soft_menusetup "
                    + "where MenuCode='" + menuCode + "' "
                    + "and M_Index='" + menuIndex + "'";
            Statement stmt = mysql.getConnection().createStatement();
ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MenuMGR m = new MenuMGR();
                m.setMenuCode(rs.getString("MenuCode"));
                m.setMenuType(rs.getInt("MenuType"));
                m.setOptSet(rs.getString("OptSet"));
                m.setPSet(rs.getString("PSet"));
                m.setPCode(rs.getString("PCode"));
                m.setMenuShowText(ThaiUtil.ASCII2Unicode(rs.getString("MenuShowText")));
                m.setIMG(rs.getString("IMG"));
                m.setFontColor(rs.getString("FontColor"));
                m.setBGColor(rs.getString("BGColor"));
                m.setLayout(rs.getInt("Layout"));
                m.setFontSize(rs.getInt("FontSize"));
                m.setFontName(rs.getString("FontName"));
                m.setMIndex(rs.getInt("M_Index"));
                m.setFontAttr(rs.getString("FontAttr"));

                cbTypeMenu.setSelectedIndex(m.getMenuType());
                if (m.getMenuType() == 0) {
                    txtPCode.setEnabled(false);
                    btnFindProduct.setEnabled(false);
                } else {
                    txtPCode.setEnabled(true);
                    btnFindProduct.setEnabled(true);
                }
                txtPCode.setText(m.getPCode());
                try {
                    String sqlFind = "select PDesc from product "
                            + "where pcode='" + m.getPCode() + "' "
                            + "and PActive='Y'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rsFind = stmt1.executeQuery(sqlFind);
                    if (rsFind.next()) {
                        txtPDesc.setText(ThaiUtil.ASCII2Unicode(rsFind.getString("PDesc")));
                    }

                    rsFind.close();
                    stmt1.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                txtShortName.setText(m.getMenuShowText());
                Color BGColor = getColorFormat(m.getBGColor());
                btnBGColor.setBackground(BGColor);
                if (m.getFontAttr().equals("B")) {
                    chkFontBold.setSelected(true);
                    chkFontItalic.setSelected(false);
                    chkFontBoldAndItalic.setSelected(false);
                } else if (m.getFontAttr().equals("I")) {
                    chkFontItalic.setSelected(true);
                    chkFontBold.setSelected(true);
                    chkFontBoldAndItalic.setSelected(false);
                } else if (m.getFontAttr().equals("BI")) {
                    chkFontBoldAndItalic.setSelected(true);
                    chkFontBold.setSelected(true);
                    chkFontItalic.setSelected(false);
                } else {
                    chkFontBold.setSelected(false);
                    chkFontItalic.setSelected(false);
                    chkFontBoldAndItalic.setSelected(false);
                }

                btnFontColor.setForeground(getColorFormat(m.getFontColor()));
                if (m.getFontName().equals("")) {
                    cbFontList.setSelectedItem("Tahoma");
                } else {
                    cbFontList.setSelectedItem(m.getFontName());
                }

                cbLayoutMenu.setSelectedIndex(m.getLayout());
                txtPathIMG.setText(m.getIMG());
                txtImgSize.setText("" + m.getImgSize());

                autoPreview();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }finally{
            mysql.close();
        }
    }

    private void autoPreview() {
        buttonLayout5.setBackground(btnBGColor.getBackground());
        buttonLayout7.setBackground(btnBGColor.getBackground());

        buttonLayout1.setForeground(btnFontColor.getForeground());
        buttonLayout2.setForeground(btnFontColor.getForeground());
        buttonLayout3.setForeground(btnFontColor.getForeground());
        buttonLayout4.setForeground(btnFontColor.getForeground());
        buttonLayout5.setForeground(btnFontColor.getForeground());
        buttonLayout6.setForeground(btnFontColor.getForeground());
        buttonLayout7.setForeground(btnFontColor.getForeground());

        int size = 14;
        try {
            size = Integer.parseInt(txtFontSize.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        Font font;
        String fontText = "Tahoma";//cbFontList.getSelectedItem().toString()
        if (cbFontList.getSelectedItem() != null) {
            fontText = cbFontList.getSelectedItem().toString();
        }
        if (chkFontBold.isSelected()) {
            font = new Font(fontText, Font.BOLD, size);
        } else if (chkFontItalic.isSelected()) {
            font = new Font(fontText, Font.ITALIC, size);
        } else if (chkFontBoldAndItalic.isSelected()) {
            font = new Font(fontText, Font.BOLD | Font.ITALIC, size);
        } else {
            font = new Font(fontText, Font.PLAIN, size);
        }

        buttonLayout1.setFont(font);
        buttonLayout2.setFont(font);
        buttonLayout3.setFont(font);
        buttonLayout4.setFont(font);
        buttonLayout5.setFont(font);
        buttonLayout7.setFont(font);

        String nickName = txtShortName.getText().trim();
        if (!nickName.equals("")) {
            buttonLayout1.setText("<html>" + nickName + "</html>");
            buttonLayout2.setText("<html>" + nickName + "</html>");
            buttonLayout3.setText("<html>" + nickName + "</html>");
            buttonLayout4.setText("<html>" + nickName + "</html>");
            buttonLayout5.setText("<html>" + nickName + "</html>");
            buttonLayout7.setText("<html>" + nickName + "</html>");
        }

        if (!txtPathIMG.getText().trim().equals("")) {
            File file = new File(txtPathIMG.getText());
            if (file.exists()) {
                updateImageFull(buttonLayout1, file);
                updateImage(buttonLayout2, file);
                updateImageFull(buttonLayout3, file);
                updateImageFull(buttonLayout6, file);
                updateImage(buttonLayout7, file);
            }
        }
    }

    private void updateImage(JButton button, File file) {
        ImageIcon icon = new ImageIcon(file.getPath());
        int scale = 1; // 2 times smaller
        int width = button.getWidth() - 35;//icon.getIconWidth();
        try {
            width = Integer.parseInt(txtImgSize.getText());
        } catch (NumberFormatException e) {
            width = 125;
        }
        if (width <= 0) {
            width = 125;
        }
        int newWidth = width / scale;
        button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(newWidth, -1, Image.SCALE_SMOOTH)));
    }

    private void updateImageFull(JButton button, File file) {
        ImageIcon icon = new ImageIcon(file.getPath());
        int scale = 1; // 2 times smaller
        int width = button.getWidth();//icon.getIconWidth();
        try {
            width = Integer.parseInt(txtImgSize.getText());
        } catch (NumberFormatException e) {
            width = 125;
        }
        if (width <= 0) {
            width = 125;
        }
        int newWidth = width / scale;
        button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(newWidth, -1,
                java.awt.Image.SCALE_SMOOTH)));
    }

    private void loadFontList() {
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        cbFontList.removeAllItems();
        for (String font : fonts) {
            cbFontList.addItem(font);
        }

        cbFontList.setSelectedItem("Tahoma");
    }

    private Color getColorFormat(String bgColor) {
        int red = 240, green = 240, blue = 240;
        if (bgColor != null) {
            String[] color = bgColor.split(",");
            if (color.length == 3) {
                try {
                    red = Integer.parseInt(color[0]);
                    green = Integer.parseInt(color[1]);
                    blue = Integer.parseInt(color[2]);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return new Color(red, green, blue);
    }

    private void updateIconFull(JButton button, String img, int imgSize) {
        //full button image
        ImageIcon icon = new ImageIcon(img);
        int scale = 1;
        int width = imgSize;//icon.getIconWidth();
        if (width <= 0) {
            width = 125;
        }
        int newWidth = width / scale;
        button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(newWidth, -1, Image.SCALE_SMOOTH)));
    }

    private void updateIconDefault(JButton button, String img) {
        ImageIcon icon = new ImageIcon(img);
        int scale = 1; // 2 times smaller
        int width = button.getWidth() - 35;//icon.getIconWidth();
        int newWidth = width / scale;
        button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(newWidth, -1, Image.SCALE_SMOOTH)));
    }

    private void Selectmenu() {
        String pcode = txtPCode.getText();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sqlFind = "select PDesc from product "
                    + "where pcode='" + pcode + "' "
                    + "and PActive='Y'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rsFind = stmt.executeQuery(sqlFind);
            if (rsFind.next()) {
                txtShortName.setText(ThaiUtil.ASCII2Unicode(rsFind.getString("PDesc")));
            }

            rsFind.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
    }

    class MenuMGR {

        private String MenuCode;
        private int MenuType;
        private String OptSet;
        private String PSet;
        private String PCode;
        private String MenuShowText;
        private String IMG;
        private String FontColor;
        private String BGColor;
        private int Layout;
        private int FontSize;
        private String FontName;
        private String FontAttr;
        private int MIndex;
        private int ImgSize;

        public int getImgSize() {
            return ImgSize;
        }

        public void setImgSize(int ImgSize) {
            this.ImgSize = ImgSize;
        }

        public int getMIndex() {
            return MIndex;
        }

        public String getOptSet() {
            return OptSet;
        }

        public void setOptSet(String OptSet) {
            this.OptSet = OptSet;
        }

        public String getPSet() {
            return PSet;
        }

        public void setPSet(String PSet) {
            this.PSet = PSet;
        }

        public void setMIndex(int MIndex) {
            this.MIndex = MIndex;
        }

        public String getMenuCode() {
            return MenuCode;
        }

        public void setMenuCode(String MenuCode) {
            this.MenuCode = MenuCode;
        }

        public int getMenuType() {
            return MenuType;
        }

        public void setMenuType(int MenuType) {
            this.MenuType = MenuType;
        }

        public String getPCode() {
            return PCode;
        }

        public void setPCode(String PCode) {
            this.PCode = PCode;
        }

        public String getMenuShowText() {
            return MenuShowText;
        }

        public void setMenuShowText(String MenuShowText) {
            this.MenuShowText = MenuShowText;
        }

        public String getIMG() {
            return IMG;
        }

        public void setIMG(String IMG) {
            this.IMG = IMG;
        }

        public String getFontColor() {
            return FontColor;
        }

        public void setFontColor(String FontColor) {
            this.FontColor = FontColor;
        }

        public String getBGColor() {
            return BGColor;
        }

        public void setBGColor(String BGColor) {
            this.BGColor = BGColor;
        }

        public int getLayout() {
            return Layout;
        }

        public void setLayout(int Layout) {
            this.Layout = Layout;
        }

        public int getFontSize() {
            return FontSize;
        }

        public void setFontSize(int FontSize) {
            this.FontSize = FontSize;
        }

        public String getFontName() {
            return FontName;
        }

        public void setFontName(String FontName) {
            this.FontName = FontName;
        }

        public String getFontAttr() {
            return FontAttr;
        }

        public void setFontAttr(String FontAttr) {
            this.FontAttr = FontAttr;
        }

    }
}
