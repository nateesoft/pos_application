package com.softpos.main.program;

import com.softpos.main.utils.PUtility;
import com.softpos.main.model.TempSetBean;
import com.softpos.main.controller.TempSetController;
import com.softpos.database.util.MySQLConnect;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

// Panel Option ข้อความพิเศษ สำหรับสินค้าแต่ละรายการ
public class ModalPopup1 extends javax.swing.JDialog {

    private String PCode = "";
    private String MenuCode = "";
    private String PCodeSet = "";
    private String PName = "";
    private String PNameSet = "";
    private String Main = "";
    private String TableNo = "";

    public ModalPopup1(java.awt.Frame parent, boolean modal,
            String PCode, String PName, String TableNo, String Main, String MenuCode) {
        super(parent, modal);
        initComponents();

//        Dimension d = getMaximumSize();
//        setSize(1000, 750);
//        setLocationRelativeTo(null);
        this.PCode = PCode;
        this.PName = PName;
        this.Main = Main;
        this.TableNo = TableNo;
        this.MenuCode = MenuCode;

        if (!loadMenu2Pcs()) {
            loadOptionProduct();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton76 = new javax.swing.JButton();
        jButton77 = new javax.swing.JButton();
        jButton78 = new javax.swing.JButton();
        jButton79 = new javax.swing.JButton();
        jButton80 = new javax.swing.JButton();
        jButton81 = new javax.swing.JButton();
        jButton82 = new javax.swing.JButton();
        jButton83 = new javax.swing.JButton();
        jButton84 = new javax.swing.JButton();
        jButton85 = new javax.swing.JButton();
        jButton86 = new javax.swing.JButton();
        jButton87 = new javax.swing.JButton();
        jButton88 = new javax.swing.JButton();
        jButton89 = new javax.swing.JButton();
        jButton90 = new javax.swing.JButton();
        jButton91 = new javax.swing.JButton();
        jButton92 = new javax.swing.JButton();
        jButton93 = new javax.swing.JButton();
        jButton94 = new javax.swing.JButton();
        jButton95 = new javax.swing.JButton();
        jButton96 = new javax.swing.JButton();
        jButton97 = new javax.swing.JButton();
        jButton98 = new javax.swing.JButton();
        jButton99 = new javax.swing.JButton();
        jButton100 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton101 = new javax.swing.JButton();
        jButton102 = new javax.swing.JButton();
        jButton103 = new javax.swing.JButton();
        jButton104 = new javax.swing.JButton();
        jButton105 = new javax.swing.JButton();
        jButton106 = new javax.swing.JButton();
        jButton107 = new javax.swing.JButton();
        jButton108 = new javax.swing.JButton();
        jButton109 = new javax.swing.JButton();
        jButton110 = new javax.swing.JButton();
        jButton111 = new javax.swing.JButton();
        jButton112 = new javax.swing.JButton();
        jButton113 = new javax.swing.JButton();
        jButton114 = new javax.swing.JButton();
        jButton115 = new javax.swing.JButton();
        jButton116 = new javax.swing.JButton();
        jButton117 = new javax.swing.JButton();
        jButton118 = new javax.swing.JButton();
        jButton119 = new javax.swing.JButton();
        jButton120 = new javax.swing.JButton();
        jButton121 = new javax.swing.JButton();
        jButton122 = new javax.swing.JButton();
        jButton123 = new javax.swing.JButton();
        jButton124 = new javax.swing.JButton();
        jButton125 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton126 = new javax.swing.JButton();
        jButton127 = new javax.swing.JButton();
        jButton128 = new javax.swing.JButton();
        jButton129 = new javax.swing.JButton();
        jButton130 = new javax.swing.JButton();
        jButton131 = new javax.swing.JButton();
        jButton132 = new javax.swing.JButton();
        jButton133 = new javax.swing.JButton();
        jButton134 = new javax.swing.JButton();
        jButton135 = new javax.swing.JButton();
        jButton136 = new javax.swing.JButton();
        jButton137 = new javax.swing.JButton();
        jButton138 = new javax.swing.JButton();
        jButton139 = new javax.swing.JButton();
        jButton140 = new javax.swing.JButton();
        jButton141 = new javax.swing.JButton();
        jButton142 = new javax.swing.JButton();
        jButton143 = new javax.swing.JButton();
        jButton144 = new javax.swing.JButton();
        jButton145 = new javax.swing.JButton();
        jButton146 = new javax.swing.JButton();
        jButton147 = new javax.swing.JButton();
        jButton148 = new javax.swing.JButton();
        jButton149 = new javax.swing.JButton();
        jButton150 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton151 = new javax.swing.JButton();
        jButton152 = new javax.swing.JButton();
        jButton153 = new javax.swing.JButton();
        jButton154 = new javax.swing.JButton();
        jButton155 = new javax.swing.JButton();
        jButton156 = new javax.swing.JButton();
        jButton157 = new javax.swing.JButton();
        jButton158 = new javax.swing.JButton();
        jButton159 = new javax.swing.JButton();
        jButton160 = new javax.swing.JButton();
        jButton161 = new javax.swing.JButton();
        jButton162 = new javax.swing.JButton();
        jButton163 = new javax.swing.JButton();
        jButton164 = new javax.swing.JButton();
        jButton165 = new javax.swing.JButton();
        jButton166 = new javax.swing.JButton();
        jButton167 = new javax.swing.JButton();
        jButton168 = new javax.swing.JButton();
        jButton169 = new javax.swing.JButton();
        jButton170 = new javax.swing.JButton();
        jButton171 = new javax.swing.JButton();
        jButton172 = new javax.swing.JButton();
        jButton173 = new javax.swing.JButton();
        jButton174 = new javax.swing.JButton();
        jButton175 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton176 = new javax.swing.JButton();
        jButton177 = new javax.swing.JButton();
        jButton178 = new javax.swing.JButton();
        jButton179 = new javax.swing.JButton();
        jButton180 = new javax.swing.JButton();
        jButton181 = new javax.swing.JButton();
        jButton182 = new javax.swing.JButton();
        jButton183 = new javax.swing.JButton();
        jButton184 = new javax.swing.JButton();
        jButton185 = new javax.swing.JButton();
        jButton186 = new javax.swing.JButton();
        jButton187 = new javax.swing.JButton();
        jButton188 = new javax.swing.JButton();
        jButton189 = new javax.swing.JButton();
        jButton190 = new javax.swing.JButton();
        jButton191 = new javax.swing.JButton();
        jButton192 = new javax.swing.JButton();
        jButton193 = new javax.swing.JButton();
        jButton194 = new javax.swing.JButton();
        jButton195 = new javax.swing.JButton();
        jButton196 = new javax.swing.JButton();
        jButton197 = new javax.swing.JButton();
        jButton198 = new javax.swing.JButton();
        jButton199 = new javax.swing.JButton();
        jButton200 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(174, 72));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new java.awt.GridLayout(5, 5));

        jButton1.setBackground(new java.awt.Color(255, 255, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton1);

        jButton2.setBackground(new java.awt.Color(255, 255, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton2);

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton3);

        jButton4.setBackground(new java.awt.Color(255, 255, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton4);

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(255, 255, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton6);

        jButton7.setBackground(new java.awt.Color(255, 255, 153));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton7);

        jButton8.setBackground(new java.awt.Color(255, 255, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton8);

        jButton9.setBackground(new java.awt.Color(255, 255, 153));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton9);

        jButton10.setBackground(new java.awt.Color(255, 255, 153));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton10);

        jButton11.setBackground(new java.awt.Color(255, 255, 153));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton11);

        jButton12.setBackground(new java.awt.Color(255, 255, 153));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton12);

        jButton13.setBackground(new java.awt.Color(255, 255, 153));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton13);

        jButton14.setBackground(new java.awt.Color(255, 255, 153));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton14);

        jButton15.setBackground(new java.awt.Color(255, 255, 153));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton15);

        jButton16.setBackground(new java.awt.Color(255, 255, 153));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton16);

        jButton17.setBackground(new java.awt.Color(255, 255, 153));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton17);

        jButton18.setBackground(new java.awt.Color(255, 255, 153));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton18);

        jButton19.setBackground(new java.awt.Color(255, 255, 153));
        jButton19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton19);

        jButton20.setBackground(new java.awt.Color(255, 255, 153));
        jButton20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton20);

        jButton21.setBackground(new java.awt.Color(255, 255, 153));
        jButton21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton21);

        jButton22.setBackground(new java.awt.Color(255, 255, 153));
        jButton22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton22);

        jButton23.setBackground(new java.awt.Color(255, 255, 153));
        jButton23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton23);

        jButton24.setBackground(new java.awt.Color(255, 255, 153));
        jButton24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton24);

        jButton25.setBackground(new java.awt.Color(255, 255, 153));
        jButton25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jButton25);

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new java.awt.GridLayout(5, 5));

        jButton26.setBackground(new java.awt.Color(255, 255, 153));
        jButton26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton26);

        jButton27.setBackground(new java.awt.Color(255, 255, 153));
        jButton27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton27);

        jButton28.setBackground(new java.awt.Color(255, 255, 153));
        jButton28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton28);

        jButton29.setBackground(new java.awt.Color(255, 255, 153));
        jButton29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton29);

        jButton30.setBackground(new java.awt.Color(255, 255, 153));
        jButton30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton30);

        jButton31.setBackground(new java.awt.Color(255, 255, 153));
        jButton31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton31);

        jButton32.setBackground(new java.awt.Color(255, 255, 153));
        jButton32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton32);

        jButton33.setBackground(new java.awt.Color(255, 255, 153));
        jButton33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton33);

        jButton34.setBackground(new java.awt.Color(255, 255, 153));
        jButton34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton34);

        jButton35.setBackground(new java.awt.Color(255, 255, 153));
        jButton35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton35);

        jButton36.setBackground(new java.awt.Color(255, 255, 153));
        jButton36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton36);

        jButton37.setBackground(new java.awt.Color(255, 255, 153));
        jButton37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton37);

        jButton38.setBackground(new java.awt.Color(255, 255, 153));
        jButton38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton38);

        jButton39.setBackground(new java.awt.Color(255, 255, 153));
        jButton39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton39);

        jButton40.setBackground(new java.awt.Color(255, 255, 153));
        jButton40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton40);

        jButton41.setBackground(new java.awt.Color(255, 255, 153));
        jButton41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton41);

        jButton42.setBackground(new java.awt.Color(255, 255, 153));
        jButton42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton42);

        jButton43.setBackground(new java.awt.Color(255, 255, 153));
        jButton43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton43);

        jButton44.setBackground(new java.awt.Color(255, 255, 153));
        jButton44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton44);

        jButton45.setBackground(new java.awt.Color(255, 255, 153));
        jButton45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton45);

        jButton46.setBackground(new java.awt.Color(255, 255, 153));
        jButton46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton46);

        jButton47.setBackground(new java.awt.Color(255, 255, 153));
        jButton47.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton47);

        jButton48.setBackground(new java.awt.Color(255, 255, 153));
        jButton48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton48);

        jButton49.setBackground(new java.awt.Color(255, 255, 153));
        jButton49.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton49);

        jButton50.setBackground(new java.awt.Color(255, 255, 153));
        jButton50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jButton50);

        jTabbedPane1.addTab("tab2", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.setLayout(new java.awt.GridLayout(5, 5));

        jButton51.setBackground(new java.awt.Color(255, 255, 153));
        jButton51.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton51);

        jButton52.setBackground(new java.awt.Color(255, 255, 153));
        jButton52.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton52);

        jButton53.setBackground(new java.awt.Color(255, 255, 153));
        jButton53.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton53);

        jButton54.setBackground(new java.awt.Color(255, 255, 153));
        jButton54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton54);

        jButton55.setBackground(new java.awt.Color(255, 255, 153));
        jButton55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton55);

        jButton56.setBackground(new java.awt.Color(255, 255, 153));
        jButton56.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton56);

        jButton57.setBackground(new java.awt.Color(255, 255, 153));
        jButton57.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton57);

        jButton58.setBackground(new java.awt.Color(255, 255, 153));
        jButton58.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton58);

        jButton59.setBackground(new java.awt.Color(255, 255, 153));
        jButton59.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton59);

        jButton60.setBackground(new java.awt.Color(255, 255, 153));
        jButton60.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton60);

        jButton61.setBackground(new java.awt.Color(255, 255, 153));
        jButton61.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton61);

        jButton62.setBackground(new java.awt.Color(255, 255, 153));
        jButton62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton62);

        jButton63.setBackground(new java.awt.Color(255, 255, 153));
        jButton63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton63);

        jButton64.setBackground(new java.awt.Color(255, 255, 153));
        jButton64.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton64);

        jButton65.setBackground(new java.awt.Color(255, 255, 153));
        jButton65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton65);

        jButton66.setBackground(new java.awt.Color(255, 255, 153));
        jButton66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton66);

        jButton67.setBackground(new java.awt.Color(255, 255, 153));
        jButton67.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton67);

        jButton68.setBackground(new java.awt.Color(255, 255, 153));
        jButton68.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton68);

        jButton69.setBackground(new java.awt.Color(255, 255, 153));
        jButton69.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton69);

        jButton70.setBackground(new java.awt.Color(255, 255, 153));
        jButton70.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton70);

        jButton71.setBackground(new java.awt.Color(255, 255, 153));
        jButton71.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton71);

        jButton72.setBackground(new java.awt.Color(255, 255, 153));
        jButton72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton72);

        jButton73.setBackground(new java.awt.Color(255, 255, 153));
        jButton73.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton73);

        jButton74.setBackground(new java.awt.Color(255, 255, 153));
        jButton74.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton74);

        jButton75.setBackground(new java.awt.Color(255, 255, 153));
        jButton75.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(jButton75);

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setLayout(new java.awt.GridLayout(5, 5));

        jButton76.setBackground(new java.awt.Color(255, 255, 153));
        jButton76.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton76);

        jButton77.setBackground(new java.awt.Color(255, 255, 153));
        jButton77.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton77);

        jButton78.setBackground(new java.awt.Color(255, 255, 153));
        jButton78.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton78);

        jButton79.setBackground(new java.awt.Color(255, 255, 153));
        jButton79.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton79);

        jButton80.setBackground(new java.awt.Color(255, 255, 153));
        jButton80.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton80);

        jButton81.setBackground(new java.awt.Color(255, 255, 153));
        jButton81.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton81);

        jButton82.setBackground(new java.awt.Color(255, 255, 153));
        jButton82.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton82);

        jButton83.setBackground(new java.awt.Color(255, 255, 153));
        jButton83.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton83);

        jButton84.setBackground(new java.awt.Color(255, 255, 153));
        jButton84.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton84);

        jButton85.setBackground(new java.awt.Color(255, 255, 153));
        jButton85.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton85);

        jButton86.setBackground(new java.awt.Color(255, 255, 153));
        jButton86.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton86);

        jButton87.setBackground(new java.awt.Color(255, 255, 153));
        jButton87.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton87);

        jButton88.setBackground(new java.awt.Color(255, 255, 153));
        jButton88.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton88);

        jButton89.setBackground(new java.awt.Color(255, 255, 153));
        jButton89.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton89);

        jButton90.setBackground(new java.awt.Color(255, 255, 153));
        jButton90.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton90);

        jButton91.setBackground(new java.awt.Color(255, 255, 153));
        jButton91.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton91);

        jButton92.setBackground(new java.awt.Color(255, 255, 153));
        jButton92.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton92);

        jButton93.setBackground(new java.awt.Color(255, 255, 153));
        jButton93.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton93);

        jButton94.setBackground(new java.awt.Color(255, 255, 153));
        jButton94.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton94);

        jButton95.setBackground(new java.awt.Color(255, 255, 153));
        jButton95.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton95);

        jButton96.setBackground(new java.awt.Color(255, 255, 153));
        jButton96.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton96);

        jButton97.setBackground(new java.awt.Color(255, 255, 153));
        jButton97.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton97);

        jButton98.setBackground(new java.awt.Color(255, 255, 153));
        jButton98.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton98);

        jButton99.setBackground(new java.awt.Color(255, 255, 153));
        jButton99.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton99);

        jButton100.setBackground(new java.awt.Color(255, 255, 153));
        jButton100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(jButton100);

        jTabbedPane1.addTab("tab4", jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.setLayout(new java.awt.GridLayout(5, 5));

        jButton101.setBackground(new java.awt.Color(255, 255, 153));
        jButton101.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton101);

        jButton102.setBackground(new java.awt.Color(255, 255, 153));
        jButton102.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton102);

        jButton103.setBackground(new java.awt.Color(255, 255, 153));
        jButton103.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton103);

        jButton104.setBackground(new java.awt.Color(255, 255, 153));
        jButton104.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton104);

        jButton105.setBackground(new java.awt.Color(255, 255, 153));
        jButton105.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton105);

        jButton106.setBackground(new java.awt.Color(255, 255, 153));
        jButton106.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton106);

        jButton107.setBackground(new java.awt.Color(255, 255, 153));
        jButton107.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton107);

        jButton108.setBackground(new java.awt.Color(255, 255, 153));
        jButton108.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton108);

        jButton109.setBackground(new java.awt.Color(255, 255, 153));
        jButton109.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton109);

        jButton110.setBackground(new java.awt.Color(255, 255, 153));
        jButton110.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton110);

        jButton111.setBackground(new java.awt.Color(255, 255, 153));
        jButton111.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton111);

        jButton112.setBackground(new java.awt.Color(255, 255, 153));
        jButton112.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton112);

        jButton113.setBackground(new java.awt.Color(255, 255, 153));
        jButton113.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton113);

        jButton114.setBackground(new java.awt.Color(255, 255, 153));
        jButton114.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton114);

        jButton115.setBackground(new java.awt.Color(255, 255, 153));
        jButton115.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton115);

        jButton116.setBackground(new java.awt.Color(255, 255, 153));
        jButton116.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton116);

        jButton117.setBackground(new java.awt.Color(255, 255, 153));
        jButton117.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton117);

        jButton118.setBackground(new java.awt.Color(255, 255, 153));
        jButton118.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton118);

        jButton119.setBackground(new java.awt.Color(255, 255, 153));
        jButton119.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton119);

        jButton120.setBackground(new java.awt.Color(255, 255, 153));
        jButton120.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton120);

        jButton121.setBackground(new java.awt.Color(255, 255, 153));
        jButton121.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton121);

        jButton122.setBackground(new java.awt.Color(255, 255, 153));
        jButton122.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton122);

        jButton123.setBackground(new java.awt.Color(255, 255, 153));
        jButton123.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton123);

        jButton124.setBackground(new java.awt.Color(255, 255, 153));
        jButton124.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton124);

        jButton125.setBackground(new java.awt.Color(255, 255, 153));
        jButton125.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(jButton125);

        jTabbedPane1.addTab("tab5", jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setLayout(new java.awt.GridLayout(5, 5));

        jButton126.setBackground(new java.awt.Color(255, 255, 153));
        jButton126.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton126);

        jButton127.setBackground(new java.awt.Color(255, 255, 153));
        jButton127.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton127);

        jButton128.setBackground(new java.awt.Color(255, 255, 153));
        jButton128.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton128);

        jButton129.setBackground(new java.awt.Color(255, 255, 153));
        jButton129.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton129);

        jButton130.setBackground(new java.awt.Color(255, 255, 153));
        jButton130.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton130);

        jButton131.setBackground(new java.awt.Color(255, 255, 153));
        jButton131.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton131);

        jButton132.setBackground(new java.awt.Color(255, 255, 153));
        jButton132.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton132);

        jButton133.setBackground(new java.awt.Color(255, 255, 153));
        jButton133.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton133);

        jButton134.setBackground(new java.awt.Color(255, 255, 153));
        jButton134.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton134);

        jButton135.setBackground(new java.awt.Color(255, 255, 153));
        jButton135.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton135);

        jButton136.setBackground(new java.awt.Color(255, 255, 153));
        jButton136.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton136);

        jButton137.setBackground(new java.awt.Color(255, 255, 153));
        jButton137.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton137);

        jButton138.setBackground(new java.awt.Color(255, 255, 153));
        jButton138.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton138);

        jButton139.setBackground(new java.awt.Color(255, 255, 153));
        jButton139.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton139);

        jButton140.setBackground(new java.awt.Color(255, 255, 153));
        jButton140.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton140);

        jButton141.setBackground(new java.awt.Color(255, 255, 153));
        jButton141.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton141);

        jButton142.setBackground(new java.awt.Color(255, 255, 153));
        jButton142.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton142);

        jButton143.setBackground(new java.awt.Color(255, 255, 153));
        jButton143.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton143);

        jButton144.setBackground(new java.awt.Color(255, 255, 153));
        jButton144.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton144);

        jButton145.setBackground(new java.awt.Color(255, 255, 153));
        jButton145.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton145);

        jButton146.setBackground(new java.awt.Color(255, 255, 153));
        jButton146.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton146);

        jButton147.setBackground(new java.awt.Color(255, 255, 153));
        jButton147.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton147);

        jButton148.setBackground(new java.awt.Color(255, 255, 153));
        jButton148.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton148);

        jButton149.setBackground(new java.awt.Color(255, 255, 153));
        jButton149.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton149);

        jButton150.setBackground(new java.awt.Color(255, 255, 153));
        jButton150.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel6.add(jButton150);

        jTabbedPane1.addTab("tab6", jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel7.setLayout(new java.awt.GridLayout(5, 5));

        jButton151.setBackground(new java.awt.Color(255, 255, 153));
        jButton151.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton151);

        jButton152.setBackground(new java.awt.Color(255, 255, 153));
        jButton152.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton152);

        jButton153.setBackground(new java.awt.Color(255, 255, 153));
        jButton153.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton153);

        jButton154.setBackground(new java.awt.Color(255, 255, 153));
        jButton154.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton154);

        jButton155.setBackground(new java.awt.Color(255, 255, 153));
        jButton155.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton155);

        jButton156.setBackground(new java.awt.Color(255, 255, 153));
        jButton156.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton156);

        jButton157.setBackground(new java.awt.Color(255, 255, 153));
        jButton157.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton157);

        jButton158.setBackground(new java.awt.Color(255, 255, 153));
        jButton158.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton158);

        jButton159.setBackground(new java.awt.Color(255, 255, 153));
        jButton159.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton159);

        jButton160.setBackground(new java.awt.Color(255, 255, 153));
        jButton160.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton160);

        jButton161.setBackground(new java.awt.Color(255, 255, 153));
        jButton161.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton161);

        jButton162.setBackground(new java.awt.Color(255, 255, 153));
        jButton162.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton162);

        jButton163.setBackground(new java.awt.Color(255, 255, 153));
        jButton163.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton163);

        jButton164.setBackground(new java.awt.Color(255, 255, 153));
        jButton164.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton164);

        jButton165.setBackground(new java.awt.Color(255, 255, 153));
        jButton165.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton165);

        jButton166.setBackground(new java.awt.Color(255, 255, 153));
        jButton166.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton166);

        jButton167.setBackground(new java.awt.Color(255, 255, 153));
        jButton167.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton167);

        jButton168.setBackground(new java.awt.Color(255, 255, 153));
        jButton168.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton168);

        jButton169.setBackground(new java.awt.Color(255, 255, 153));
        jButton169.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton169);

        jButton170.setBackground(new java.awt.Color(255, 255, 153));
        jButton170.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton170);

        jButton171.setBackground(new java.awt.Color(255, 255, 153));
        jButton171.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton171);

        jButton172.setBackground(new java.awt.Color(255, 255, 153));
        jButton172.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton172);

        jButton173.setBackground(new java.awt.Color(255, 255, 153));
        jButton173.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton173);

        jButton174.setBackground(new java.awt.Color(255, 255, 153));
        jButton174.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton174);

        jButton175.setBackground(new java.awt.Color(255, 255, 153));
        jButton175.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel7.add(jButton175);

        jTabbedPane1.addTab("tab7", jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel8.setLayout(new java.awt.GridLayout(5, 5));

        jButton176.setBackground(new java.awt.Color(255, 255, 153));
        jButton176.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton176);

        jButton177.setBackground(new java.awt.Color(255, 255, 153));
        jButton177.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton177);

        jButton178.setBackground(new java.awt.Color(255, 255, 153));
        jButton178.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton178);

        jButton179.setBackground(new java.awt.Color(255, 255, 153));
        jButton179.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton179);

        jButton180.setBackground(new java.awt.Color(255, 255, 153));
        jButton180.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton180);

        jButton181.setBackground(new java.awt.Color(255, 255, 153));
        jButton181.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton181);

        jButton182.setBackground(new java.awt.Color(255, 255, 153));
        jButton182.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton182);

        jButton183.setBackground(new java.awt.Color(255, 255, 153));
        jButton183.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton183);

        jButton184.setBackground(new java.awt.Color(255, 255, 153));
        jButton184.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton184);

        jButton185.setBackground(new java.awt.Color(255, 255, 153));
        jButton185.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton185);

        jButton186.setBackground(new java.awt.Color(255, 255, 153));
        jButton186.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton186);

        jButton187.setBackground(new java.awt.Color(255, 255, 153));
        jButton187.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton187);

        jButton188.setBackground(new java.awt.Color(255, 255, 153));
        jButton188.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton188);

        jButton189.setBackground(new java.awt.Color(255, 255, 153));
        jButton189.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton189);

        jButton190.setBackground(new java.awt.Color(255, 255, 153));
        jButton190.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton190);

        jButton191.setBackground(new java.awt.Color(255, 255, 153));
        jButton191.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton191);

        jButton192.setBackground(new java.awt.Color(255, 255, 153));
        jButton192.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton192);

        jButton193.setBackground(new java.awt.Color(255, 255, 153));
        jButton193.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton193);

        jButton194.setBackground(new java.awt.Color(255, 255, 153));
        jButton194.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton194);

        jButton195.setBackground(new java.awt.Color(255, 255, 153));
        jButton195.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton195);

        jButton196.setBackground(new java.awt.Color(255, 255, 153));
        jButton196.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton196);

        jButton197.setBackground(new java.awt.Color(255, 255, 153));
        jButton197.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton197);

        jButton198.setBackground(new java.awt.Color(255, 255, 153));
        jButton198.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton198);

        jButton199.setBackground(new java.awt.Color(255, 255, 153));
        jButton199.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton199);

        jButton200.setBackground(new java.awt.Color(255, 255, 153));
        jButton200.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel8.add(jButton200);

        jTabbedPane1.addTab("tab8", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton100;
    private javax.swing.JButton jButton101;
    private javax.swing.JButton jButton102;
    private javax.swing.JButton jButton103;
    private javax.swing.JButton jButton104;
    private javax.swing.JButton jButton105;
    private javax.swing.JButton jButton106;
    private javax.swing.JButton jButton107;
    private javax.swing.JButton jButton108;
    private javax.swing.JButton jButton109;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton110;
    private javax.swing.JButton jButton111;
    private javax.swing.JButton jButton112;
    private javax.swing.JButton jButton113;
    private javax.swing.JButton jButton114;
    private javax.swing.JButton jButton115;
    private javax.swing.JButton jButton116;
    private javax.swing.JButton jButton117;
    private javax.swing.JButton jButton118;
    private javax.swing.JButton jButton119;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton120;
    private javax.swing.JButton jButton121;
    private javax.swing.JButton jButton122;
    private javax.swing.JButton jButton123;
    private javax.swing.JButton jButton124;
    private javax.swing.JButton jButton125;
    private javax.swing.JButton jButton126;
    private javax.swing.JButton jButton127;
    private javax.swing.JButton jButton128;
    private javax.swing.JButton jButton129;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton130;
    private javax.swing.JButton jButton131;
    private javax.swing.JButton jButton132;
    private javax.swing.JButton jButton133;
    private javax.swing.JButton jButton134;
    private javax.swing.JButton jButton135;
    private javax.swing.JButton jButton136;
    private javax.swing.JButton jButton137;
    private javax.swing.JButton jButton138;
    private javax.swing.JButton jButton139;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton140;
    private javax.swing.JButton jButton141;
    private javax.swing.JButton jButton142;
    private javax.swing.JButton jButton143;
    private javax.swing.JButton jButton144;
    private javax.swing.JButton jButton145;
    private javax.swing.JButton jButton146;
    private javax.swing.JButton jButton147;
    private javax.swing.JButton jButton148;
    private javax.swing.JButton jButton149;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton150;
    private javax.swing.JButton jButton151;
    private javax.swing.JButton jButton152;
    private javax.swing.JButton jButton153;
    private javax.swing.JButton jButton154;
    private javax.swing.JButton jButton155;
    private javax.swing.JButton jButton156;
    private javax.swing.JButton jButton157;
    private javax.swing.JButton jButton158;
    private javax.swing.JButton jButton159;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton160;
    private javax.swing.JButton jButton161;
    private javax.swing.JButton jButton162;
    private javax.swing.JButton jButton163;
    private javax.swing.JButton jButton164;
    private javax.swing.JButton jButton165;
    private javax.swing.JButton jButton166;
    private javax.swing.JButton jButton167;
    private javax.swing.JButton jButton168;
    private javax.swing.JButton jButton169;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton170;
    private javax.swing.JButton jButton171;
    private javax.swing.JButton jButton172;
    private javax.swing.JButton jButton173;
    private javax.swing.JButton jButton174;
    private javax.swing.JButton jButton175;
    private javax.swing.JButton jButton176;
    private javax.swing.JButton jButton177;
    private javax.swing.JButton jButton178;
    private javax.swing.JButton jButton179;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton180;
    private javax.swing.JButton jButton181;
    private javax.swing.JButton jButton182;
    private javax.swing.JButton jButton183;
    private javax.swing.JButton jButton184;
    private javax.swing.JButton jButton185;
    private javax.swing.JButton jButton186;
    private javax.swing.JButton jButton187;
    private javax.swing.JButton jButton188;
    private javax.swing.JButton jButton189;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton190;
    private javax.swing.JButton jButton191;
    private javax.swing.JButton jButton192;
    private javax.swing.JButton jButton193;
    private javax.swing.JButton jButton194;
    private javax.swing.JButton jButton195;
    private javax.swing.JButton jButton196;
    private javax.swing.JButton jButton197;
    private javax.swing.JButton jButton198;
    private javax.swing.JButton jButton199;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton200;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton80;
    private javax.swing.JButton jButton81;
    private javax.swing.JButton jButton82;
    private javax.swing.JButton jButton83;
    private javax.swing.JButton jButton84;
    private javax.swing.JButton jButton85;
    private javax.swing.JButton jButton86;
    private javax.swing.JButton jButton87;
    private javax.swing.JButton jButton88;
    private javax.swing.JButton jButton89;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton90;
    private javax.swing.JButton jButton91;
    private javax.swing.JButton jButton92;
    private javax.swing.JButton jButton93;
    private javax.swing.JButton jButton94;
    private javax.swing.JButton jButton95;
    private javax.swing.JButton jButton96;
    private javax.swing.JButton jButton97;
    private javax.swing.JButton jButton98;
    private javax.swing.JButton jButton99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    private void loadOptionProduct() {
        JButton[] button = new JButton[200];
        button[0] = jButton1;
        button[1] = jButton2;
        button[2] = jButton3;
        button[3] = jButton4;
        button[4] = jButton5;
        button[5] = jButton6;
        button[6] = jButton7;
        button[7] = jButton8;
        button[8] = jButton9;
        button[9] = jButton10;
        button[10] = jButton11;
        button[11] = jButton12;
        button[12] = jButton13;
        button[13] = jButton14;
        button[14] = jButton15;
        button[15] = jButton16;
        button[16] = jButton17;
        button[17] = jButton18;
        button[18] = jButton19;
        button[19] = jButton20;
        button[20] = jButton21;
        button[21] = jButton22;
        button[22] = jButton23;
        button[23] = jButton24;
        button[24] = jButton25;
        button[25] = jButton26;
        button[26] = jButton27;
        button[27] = jButton28;
        button[28] = jButton29;
        button[29] = jButton30;
        button[30] = jButton31;
        button[31] = jButton32;
        button[32] = jButton33;
        button[33] = jButton34;
        button[34] = jButton35;
        button[35] = jButton36;
        button[36] = jButton37;
        button[37] = jButton38;
        button[38] = jButton39;
        button[39] = jButton40;
        button[40] = jButton41;
        button[41] = jButton42;
        button[42] = jButton43;
        button[43] = jButton44;
        button[44] = jButton45;
        button[45] = jButton46;
        button[46] = jButton47;
        button[47] = jButton48;
        button[48] = jButton49;
        button[49] = jButton50;
        button[50] = jButton51;
        button[51] = jButton52;
        button[52] = jButton53;
        button[53] = jButton54;
        button[54] = jButton55;
        button[55] = jButton56;
        button[56] = jButton57;
        button[57] = jButton58;
        button[58] = jButton59;
        button[59] = jButton60;
        button[60] = jButton61;
        button[61] = jButton62;
        button[62] = jButton63;
        button[63] = jButton64;
        button[64] = jButton65;
        button[65] = jButton66;
        button[66] = jButton67;
        button[67] = jButton68;
        button[68] = jButton69;
        button[69] = jButton70;
        button[70] = jButton71;
        button[71] = jButton72;
        button[72] = jButton73;
        button[73] = jButton74;
        button[74] = jButton75;
        button[75] = jButton76;
        button[76] = jButton77;
        button[77] = jButton78;
        button[78] = jButton79;
        button[79] = jButton80;
        button[80] = jButton81;
        button[81] = jButton82;
        button[82] = jButton83;
        button[83] = jButton84;
        button[84] = jButton85;
        button[85] = jButton86;
        button[86] = jButton87;
        button[87] = jButton88;
        button[88] = jButton89;
        button[89] = jButton90;
        button[90] = jButton91;
        button[91] = jButton92;
        button[92] = jButton93;
        button[93] = jButton94;
        button[94] = jButton95;
        button[95] = jButton96;
        button[96] = jButton97;
        button[97] = jButton98;
        button[98] = jButton99;
        button[99] = jButton100;
        button[100] = jButton101;
        button[101] = jButton102;
        button[102] = jButton103;
        button[103] = jButton104;
        button[104] = jButton105;
        button[105] = jButton106;
        button[106] = jButton107;
        button[107] = jButton108;
        button[108] = jButton109;
        button[109] = jButton110;
        button[110] = jButton111;
        button[111] = jButton112;
        button[112] = jButton113;
        button[113] = jButton114;
        button[114] = jButton115;
        button[115] = jButton116;
        button[116] = jButton117;
        button[117] = jButton118;
        button[118] = jButton119;
        button[119] = jButton120;
        button[120] = jButton121;
        button[121] = jButton122;
        button[122] = jButton123;
        button[123] = jButton124;
        button[124] = jButton125;
        button[125] = jButton126;
        button[126] = jButton127;
        button[127] = jButton128;
        button[128] = jButton129;
        button[129] = jButton130;
        button[130] = jButton131;
        button[131] = jButton132;
        button[132] = jButton133;
        button[133] = jButton134;
        button[134] = jButton135;
        button[135] = jButton136;
        button[136] = jButton137;
        button[137] = jButton138;
        button[138] = jButton139;
        button[139] = jButton140;
        button[140] = jButton141;
        button[141] = jButton142;
        button[142] = jButton143;
        button[143] = jButton144;
        button[144] = jButton145;
        button[145] = jButton146;
        button[146] = jButton147;
        button[147] = jButton148;
        button[148] = jButton149;
        button[149] = jButton150;
        button[150] = jButton151;
        button[151] = jButton152;
        button[152] = jButton153;
        button[153] = jButton154;
        button[154] = jButton155;
        button[155] = jButton156;
        button[156] = jButton157;
        button[157] = jButton158;
        button[158] = jButton159;
        button[159] = jButton160;
        button[160] = jButton161;
        button[161] = jButton162;
        button[162] = jButton163;
        button[163] = jButton164;
        button[164] = jButton165;
        button[165] = jButton166;
        button[166] = jButton167;
        button[167] = jButton168;
        button[168] = jButton169;
        button[169] = jButton170;
        button[170] = jButton171;
        button[171] = jButton172;
        button[172] = jButton173;
        button[173] = jButton174;
        button[174] = jButton175;
        button[175] = jButton176;
        button[176] = jButton177;
        button[177] = jButton178;
        button[178] = jButton179;
        button[179] = jButton180;
        button[180] = jButton181;
        button[181] = jButton182;
        button[182] = jButton183;
        button[183] = jButton184;
        button[184] = jButton185;
        button[185] = jButton186;
        button[186] = jButton187;
        button[187] = jButton188;
        button[188] = jButton189;
        button[189] = jButton190;
        button[190] = jButton191;
        button[191] = jButton192;
        button[192] = jButton193;
        button[193] = jButton194;
        button[194] = jButton195;
        button[195] = jButton196;
        button[196] = jButton197;
        button[197] = jButton198;
        button[198] = jButton199;
        button[199] = jButton200;

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from optionset "
                    + "where pcode='" + PCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                button[count].setText("<html><center>" + ThaiUtil.ASCII2Unicode(rs.getString("OptionName")) + "</center></html>");
                button[count].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //
                        JButton btn = (JButton) e.getSource();
                        String ProMain = btn.getText().replace("<html>", "").replace("<center>", "").replace("</center>", "").replace("</html>", "");

                        UpdateTempmenusetProduct("", PCode, PName, ProMain, Main);
                        loadProductSideDish();
                    }

                    private void UpdateTempmenusetProduct(String Index, String PCode, String PName, String ProMain, String Main) {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sqll = "select pcode, pdesc from mgrbuttonsetup "
                                    + "where pcode = '" + PCode + "'";
                            String pstock = PUtility.GetStkCode();
                            Statement stmt1 = mysql.getConnection().createStatement();
                            ResultSet rss = stmt1.executeQuery(sqll);
                            if (rss.next()) {
                                TempSetBean tempBean = new TempSetBean();
                                tempBean.setPTableNo(TableNo);
                                tempBean.setPIndex(Index);
                                tempBean.setPCode(rss.getString("pcode"));
                                tempBean.setPDesc(PName);
                                tempBean.setPPostStock(PUtility.GetStkCode());
                                tempBean.setPProTry(Main);
                                tempBean.setPOption(ProMain);
                                tempBean.setPTime("curtime()");

                                TempSetController.save(tempBean, mysql);
                            } else {
                                TempSetBean tempBean = new TempSetBean();
                                tempBean.setPTableNo(TableNo);
                                tempBean.setPIndex(Index);
                                tempBean.setPCode(PCode);
                                tempBean.setPDesc(PName);
                                tempBean.setPPostStock(PUtility.GetStkCode());
                                tempBean.setPProTry("");
                                tempBean.setPOption(ProMain);
                                tempBean.setPTime("curtime()");

                                TempSetController.save(tempBean, mysql);
                            }

                            rss.close();
                            stmt1.close();
                        } catch (SQLException e) {
                            MSG.ERR(null, e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }
                    }

                });
                count++;
            }

            if (count == 0) {
                loadProductSideDish();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private void loadProductSideDish() {
        //JOptionPane.showMessageDialog(this, "Show SideDish");
        JButton[] button = new JButton[200];
        button[0] = jButton1;
        button[1] = jButton2;
        button[2] = jButton3;
        button[3] = jButton4;
        button[4] = jButton5;
        button[5] = jButton6;
        button[6] = jButton7;
        button[7] = jButton8;
        button[8] = jButton9;
        button[9] = jButton10;
        button[10] = jButton11;
        button[11] = jButton12;
        button[12] = jButton13;
        button[13] = jButton14;
        button[14] = jButton15;
        button[15] = jButton16;
        button[16] = jButton17;
        button[17] = jButton18;
        button[18] = jButton19;
        button[19] = jButton20;
        button[20] = jButton21;
        button[21] = jButton22;
        button[22] = jButton23;
        button[23] = jButton24;
        button[24] = jButton25;
        button[25] = jButton26;
        button[26] = jButton27;
        button[27] = jButton28;
        button[28] = jButton29;
        button[29] = jButton30;
        button[30] = jButton31;
        button[31] = jButton32;
        button[32] = jButton33;
        button[33] = jButton34;
        button[34] = jButton35;
        button[35] = jButton36;
        button[36] = jButton37;
        button[37] = jButton38;
        button[38] = jButton39;
        button[39] = jButton40;
        button[40] = jButton41;
        button[41] = jButton42;
        button[42] = jButton43;
        button[43] = jButton44;
        button[44] = jButton45;
        button[45] = jButton46;
        button[46] = jButton47;
        button[47] = jButton48;
        button[48] = jButton49;
        button[49] = jButton50;
        button[50] = jButton51;
        button[51] = jButton52;
        button[52] = jButton53;
        button[53] = jButton54;
        button[54] = jButton55;
        button[55] = jButton56;
        button[56] = jButton57;
        button[57] = jButton58;
        button[58] = jButton59;
        button[59] = jButton60;
        button[60] = jButton61;
        button[61] = jButton62;
        button[62] = jButton63;
        button[63] = jButton64;
        button[64] = jButton65;
        button[65] = jButton66;
        button[66] = jButton67;
        button[67] = jButton68;
        button[68] = jButton69;
        button[69] = jButton70;
        button[70] = jButton71;
        button[71] = jButton72;
        button[72] = jButton73;
        button[73] = jButton74;
        button[74] = jButton75;
        button[75] = jButton76;
        button[76] = jButton77;
        button[77] = jButton78;
        button[78] = jButton79;
        button[79] = jButton80;
        button[80] = jButton81;
        button[81] = jButton82;
        button[82] = jButton83;
        button[83] = jButton84;
        button[84] = jButton85;
        button[85] = jButton86;
        button[86] = jButton87;
        button[87] = jButton88;
        button[88] = jButton89;
        button[89] = jButton90;
        button[90] = jButton91;
        button[91] = jButton92;
        button[92] = jButton93;
        button[93] = jButton94;
        button[94] = jButton95;
        button[95] = jButton96;
        button[96] = jButton97;
        button[97] = jButton98;
        button[98] = jButton99;
        button[99] = jButton100;
        button[100] = jButton101;
        button[101] = jButton102;
        button[102] = jButton103;
        button[103] = jButton104;
        button[104] = jButton105;
        button[105] = jButton106;
        button[106] = jButton107;
        button[107] = jButton108;
        button[108] = jButton109;
        button[109] = jButton110;
        button[110] = jButton111;
        button[111] = jButton112;
        button[112] = jButton113;
        button[113] = jButton114;
        button[114] = jButton115;
        button[115] = jButton116;
        button[116] = jButton117;
        button[117] = jButton118;
        button[118] = jButton119;
        button[119] = jButton120;
        button[120] = jButton121;
        button[121] = jButton122;
        button[122] = jButton123;
        button[123] = jButton124;
        button[124] = jButton125;
        button[125] = jButton126;
        button[126] = jButton127;
        button[127] = jButton128;
        button[128] = jButton129;
        button[129] = jButton130;
        button[130] = jButton131;
        button[131] = jButton132;
        button[132] = jButton133;
        button[133] = jButton134;
        button[134] = jButton135;
        button[135] = jButton136;
        button[136] = jButton137;
        button[137] = jButton138;
        button[138] = jButton139;
        button[139] = jButton140;
        button[140] = jButton141;
        button[141] = jButton142;
        button[142] = jButton143;
        button[143] = jButton144;
        button[144] = jButton145;
        button[145] = jButton146;
        button[146] = jButton147;
        button[147] = jButton148;
        button[148] = jButton149;
        button[149] = jButton150;
        button[150] = jButton151;
        button[151] = jButton152;
        button[152] = jButton153;
        button[153] = jButton154;
        button[154] = jButton155;
        button[155] = jButton156;
        button[156] = jButton157;
        button[157] = jButton158;
        button[158] = jButton159;
        button[159] = jButton160;
        button[160] = jButton161;
        button[161] = jButton162;
        button[162] = jButton163;
        button[163] = jButton164;
        button[164] = jButton165;
        button[165] = jButton166;
        button[166] = jButton167;
        button[167] = jButton168;
        button[168] = jButton169;
        button[169] = jButton170;
        button[170] = jButton171;
        button[171] = jButton172;
        button[172] = jButton173;
        button[173] = jButton174;
        button[174] = jButton175;
        button[175] = jButton176;
        button[176] = jButton177;
        button[177] = jButton178;
        button[178] = jButton179;
        button[179] = jButton180;
        button[180] = jButton181;
        button[181] = jButton182;
        button[182] = jButton183;
        button[183] = jButton184;
        button[184] = jButton185;
        button[185] = jButton186;
        button[186] = jButton187;
        button[187] = jButton188;
        button[188] = jButton189;
        button[189] = jButton190;
        button[190] = jButton191;
        button[191] = jButton192;
        button[192] = jButton193;
        button[193] = jButton194;
        button[194] = jButton195;
        button[195] = jButton196;
        button[196] = jButton197;
        button[197] = jButton198;
        button[198] = jButton199;
        button[199] = jButton200;

        button[24].setText("SKIP");
        button[24].setBackground(Color.GREEN);
        button[24].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadProductExtra();
            }
        });

        for (JButton currentButton : button) {
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }
        }

        for (JButton button1 : button) {
            button1.setText("");
            button1.setBackground(Color.PINK);
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select sd_pcode, sd_pdesc from mgrbuttonsetup "
                    + "where pcode='" + PCode + "' and sd_pcode<>''";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String PNameSide = ThaiUtil.ASCII2Unicode(rs.getString("sd_pdesc"));
                this.PNameSet = PNameSide;
                String PCodeSide = rs.getString("sd_pcode");
                this.PCodeSet = PCodeSide;
                button[count].setName(rs.getString("sd_pcode"));
                button[count].setText("<html><center>" + PNameSet + "</center></html>");
                button[count].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton btn = (JButton) e.getSource();
                        String ProFree = btn.getText().replace("<html>", "").replace("<center>", "").replace("</center>", "").replace("</html>", "");
                        UpdateTempmenusetSideDish("", btn.getName(), ProFree, "", "free");
                        loadProductExtra();

                    }

                    private void UpdateTempmenusetSideDish(String Index, String PCodeSet, String PNameSet, String ProFree, String free) {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sqll = "select sd_pcode, sd_pdesc "
                                    + "from mgrbuttonsetup "
                                    + "where pcode = '" + PCode + "'";
                            Statement stmt1 = mysql.getConnection().createStatement();
                            ResultSet rs = stmt1.executeQuery(sqll);
                            if (rs.next()) {
                                TempSetBean tempBean = new TempSetBean();
                                tempBean.setPTableNo(TableNo);
                                tempBean.setPIndex(Index);
                                tempBean.setPCode(PCodeSet);
                                tempBean.setPDesc(PNameSet);
                                tempBean.setPPostStock(PUtility.GetStkCode());
                                tempBean.setPProTry(free);
                                tempBean.setPOption(ProFree);
                                tempBean.setPTime("curtime()");

                                TempSetController.save(tempBean, mysql);
                            }

                            rs.close();
                            stmt1.close();
                        } catch (SQLException e) {
                            MSG.ERR(null, e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }
                    }
                });
                count++;
            }

            if (count == 0) {
                loadProductExtra();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            MSG.ERR(null, e.getMessage());
            
        }
    }

    private void loadProductExtra() {
        //JOptionPane.showMessageDialog(this, "Show Extra");
        JButton[] button = new JButton[200];
        button[0] = jButton1;
        button[1] = jButton2;
        button[2] = jButton3;
        button[3] = jButton4;
        button[4] = jButton5;
        button[5] = jButton6;
        button[6] = jButton7;
        button[7] = jButton8;
        button[8] = jButton9;
        button[9] = jButton10;
        button[10] = jButton11;
        button[11] = jButton12;
        button[12] = jButton13;
        button[13] = jButton14;
        button[14] = jButton15;
        button[15] = jButton16;
        button[16] = jButton17;
        button[17] = jButton18;
        button[18] = jButton19;
        button[19] = jButton20;
        button[20] = jButton21;
        button[21] = jButton22;
        button[22] = jButton23;
        button[23] = jButton24;
        button[24] = jButton25;
        button[25] = jButton26;
        button[26] = jButton27;
        button[27] = jButton28;
        button[28] = jButton29;
        button[29] = jButton30;
        button[30] = jButton31;
        button[31] = jButton32;
        button[32] = jButton33;
        button[33] = jButton34;
        button[34] = jButton35;
        button[35] = jButton36;
        button[36] = jButton37;
        button[37] = jButton38;
        button[38] = jButton39;
        button[39] = jButton40;
        button[40] = jButton41;
        button[41] = jButton42;
        button[42] = jButton43;
        button[43] = jButton44;
        button[44] = jButton45;
        button[45] = jButton46;
        button[46] = jButton47;
        button[47] = jButton48;
        button[48] = jButton49;
        button[49] = jButton50;
        button[50] = jButton51;
        button[51] = jButton52;
        button[52] = jButton53;
        button[53] = jButton54;
        button[54] = jButton55;
        button[55] = jButton56;
        button[56] = jButton57;
        button[57] = jButton58;
        button[58] = jButton59;
        button[59] = jButton60;
        button[60] = jButton61;
        button[61] = jButton62;
        button[62] = jButton63;
        button[63] = jButton64;
        button[64] = jButton65;
        button[65] = jButton66;
        button[66] = jButton67;
        button[67] = jButton68;
        button[68] = jButton69;
        button[69] = jButton70;
        button[70] = jButton71;
        button[71] = jButton72;
        button[72] = jButton73;
        button[73] = jButton74;
        button[74] = jButton75;
        button[75] = jButton76;
        button[76] = jButton77;
        button[77] = jButton78;
        button[78] = jButton79;
        button[79] = jButton80;
        button[80] = jButton81;
        button[81] = jButton82;
        button[82] = jButton83;
        button[83] = jButton84;
        button[84] = jButton85;
        button[85] = jButton86;
        button[86] = jButton87;
        button[87] = jButton88;
        button[88] = jButton89;
        button[89] = jButton90;
        button[90] = jButton91;
        button[91] = jButton92;
        button[92] = jButton93;
        button[93] = jButton94;
        button[94] = jButton95;
        button[95] = jButton96;
        button[96] = jButton97;
        button[97] = jButton98;
        button[98] = jButton99;
        button[99] = jButton100;
        button[100] = jButton101;
        button[101] = jButton102;
        button[102] = jButton103;
        button[103] = jButton104;
        button[104] = jButton105;
        button[105] = jButton106;
        button[106] = jButton107;
        button[107] = jButton108;
        button[108] = jButton109;
        button[109] = jButton110;
        button[110] = jButton111;
        button[111] = jButton112;
        button[112] = jButton113;
        button[113] = jButton114;
        button[114] = jButton115;
        button[115] = jButton116;
        button[116] = jButton117;
        button[117] = jButton118;
        button[118] = jButton119;
        button[119] = jButton120;
        button[120] = jButton121;
        button[121] = jButton122;
        button[122] = jButton123;
        button[123] = jButton124;
        button[124] = jButton125;
        button[125] = jButton126;
        button[126] = jButton127;
        button[127] = jButton128;
        button[128] = jButton129;
        button[129] = jButton130;
        button[130] = jButton131;
        button[131] = jButton132;
        button[132] = jButton133;
        button[133] = jButton134;
        button[134] = jButton135;
        button[135] = jButton136;
        button[136] = jButton137;
        button[137] = jButton138;
        button[138] = jButton139;
        button[139] = jButton140;
        button[140] = jButton141;
        button[141] = jButton142;
        button[142] = jButton143;
        button[143] = jButton144;
        button[144] = jButton145;
        button[145] = jButton146;
        button[146] = jButton147;
        button[147] = jButton148;
        button[148] = jButton149;
        button[149] = jButton150;
        button[150] = jButton151;
        button[151] = jButton152;
        button[152] = jButton153;
        button[153] = jButton154;
        button[154] = jButton155;
        button[155] = jButton156;
        button[156] = jButton157;
        button[157] = jButton158;
        button[158] = jButton159;
        button[159] = jButton160;
        button[160] = jButton161;
        button[161] = jButton162;
        button[162] = jButton163;
        button[163] = jButton164;
        button[164] = jButton165;
        button[165] = jButton166;
        button[166] = jButton167;
        button[167] = jButton168;
        button[168] = jButton169;
        button[169] = jButton170;
        button[170] = jButton171;
        button[171] = jButton172;
        button[172] = jButton173;
        button[173] = jButton174;
        button[174] = jButton175;
        button[175] = jButton176;
        button[176] = jButton177;
        button[177] = jButton178;
        button[178] = jButton179;
        button[179] = jButton180;
        button[180] = jButton181;
        button[181] = jButton182;
        button[182] = jButton183;
        button[183] = jButton184;
        button[184] = jButton185;
        button[185] = jButton186;
        button[186] = jButton187;
        button[187] = jButton188;
        button[188] = jButton189;
        button[189] = jButton190;
        button[190] = jButton191;
        button[191] = jButton192;
        button[192] = jButton193;
        button[193] = jButton194;
        button[194] = jButton195;
        button[195] = jButton196;
        button[196] = jButton197;
        button[197] = jButton198;
        button[198] = jButton199;
        button[199] = jButton200;

        for (JButton currentButton : button) {
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }
        }

        for (JButton button1 : button) {
            button1.setText("");
            button1.setBackground(new Color(153, 255, 204));
        }

        button[24].setText("SKIP");
        button[24].setBackground(Color.GREEN);
        button[24].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select ex_pcode, ex_pdesc from mgrbuttonsetup "
                    + "where pcode='" + PCode + "' and ex_pcode<>''";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                String pcode = rs.getString("ex_pcode");
                this.PCode = pcode;
                String pname = ThaiUtil.ASCII2Unicode(rs.getString("ex_pdesc"));
                this.PName = pname;
                button[count].setText("<html><center>" + pname + "</center></html>");
                button[count].setName(rs.getString("ex_pcode"));
                button[count].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton btn = (JButton) e.getSource();
                        String btnPCode = btn.getName();
                        String PDesc = btn.getText().replace("<html>", "").replace("<center>", "").replace("</center>", "").replace("</html>", "");
                        if (showPopupOption(btnPCode)) {
                            UpdateTempmenusetExtra("", btnPCode, PDesc, "", "extra");
                            loadProductExtraOption(btnPCode);
                        } else {
                            UpdateTempmenusetExtra("", btnPCode, PName, "", "extra");
                            if (!checkLimiExtra(TableNo)) {
                                dispose();
                            } else {
                                btn.setIcon(new ImageIcon(getClass().getResource("/images/button_ok.png")));
                                jButton25.setText("ยืนยัน (OK)");
                            }
                        }
                    }

                    private boolean checkLimiExtra(String TableNo) {
                        boolean checkExtra = false;
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "select PCode from tempset "
                                    + "where PTableNo='" + TableNo + "' and PProTry='main'";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            if (rs.next()) {
                                String PCode = rs.getString("PCode");
                                String sql2 = "select check_extra "
                                        + "from mgrbuttonsetup "
                                        + "where pcode='" + PCode + "' "
                                        + "and check_extra='Y'";
                                Statement stmt1 = mysql.getConnection().createStatement();
                                ResultSet rs2 = stmt1.executeQuery(sql2);
                                if (rs2.next()) {
                                    checkExtra = true;
                                }

                                rs2.close();
                                stmt1.close();
                            }

                            rs.close();
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }

                        return checkExtra;
                    }

                });
                count++;
            }

            if (count == 0) {
                dispose();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private void loadProductExtraOption(String PCodeItem) {

        JButton[] button = new JButton[200];
        button[0] = jButton1;
        button[1] = jButton2;
        button[2] = jButton3;
        button[3] = jButton4;
        button[4] = jButton5;
        button[5] = jButton6;
        button[6] = jButton7;
        button[7] = jButton8;
        button[8] = jButton9;
        button[9] = jButton10;
        button[10] = jButton11;
        button[11] = jButton12;
        button[12] = jButton13;
        button[13] = jButton14;
        button[14] = jButton15;
        button[15] = jButton16;
        button[16] = jButton17;
        button[17] = jButton18;
        button[18] = jButton19;
        button[19] = jButton20;
        button[20] = jButton21;
        button[21] = jButton22;
        button[22] = jButton23;
        button[23] = jButton24;
        button[24] = jButton25;
        button[25] = jButton26;
        button[26] = jButton27;
        button[27] = jButton28;
        button[28] = jButton29;
        button[29] = jButton30;
        button[30] = jButton31;
        button[31] = jButton32;
        button[32] = jButton33;
        button[33] = jButton34;
        button[34] = jButton35;
        button[35] = jButton36;
        button[36] = jButton37;
        button[37] = jButton38;
        button[38] = jButton39;
        button[39] = jButton40;
        button[40] = jButton41;
        button[41] = jButton42;
        button[42] = jButton43;
        button[43] = jButton44;
        button[44] = jButton45;
        button[45] = jButton46;
        button[46] = jButton47;
        button[47] = jButton48;
        button[48] = jButton49;
        button[49] = jButton50;
        button[50] = jButton51;
        button[51] = jButton52;
        button[52] = jButton53;
        button[53] = jButton54;
        button[54] = jButton55;
        button[55] = jButton56;
        button[56] = jButton57;
        button[57] = jButton58;
        button[58] = jButton59;
        button[59] = jButton60;
        button[60] = jButton61;
        button[61] = jButton62;
        button[62] = jButton63;
        button[63] = jButton64;
        button[64] = jButton65;
        button[65] = jButton66;
        button[66] = jButton67;
        button[67] = jButton68;
        button[68] = jButton69;
        button[69] = jButton70;
        button[70] = jButton71;
        button[71] = jButton72;
        button[72] = jButton73;
        button[73] = jButton74;
        button[74] = jButton75;
        button[75] = jButton76;
        button[76] = jButton77;
        button[77] = jButton78;
        button[78] = jButton79;
        button[79] = jButton80;
        button[80] = jButton81;
        button[81] = jButton82;
        button[82] = jButton83;
        button[83] = jButton84;
        button[84] = jButton85;
        button[85] = jButton86;
        button[86] = jButton87;
        button[87] = jButton88;
        button[88] = jButton89;
        button[89] = jButton90;
        button[90] = jButton91;
        button[91] = jButton92;
        button[92] = jButton93;
        button[93] = jButton94;
        button[94] = jButton95;
        button[95] = jButton96;
        button[96] = jButton97;
        button[97] = jButton98;
        button[98] = jButton99;
        button[99] = jButton100;
        button[100] = jButton101;
        button[101] = jButton102;
        button[102] = jButton103;
        button[103] = jButton104;
        button[104] = jButton105;
        button[105] = jButton106;
        button[106] = jButton107;
        button[107] = jButton108;
        button[108] = jButton109;
        button[109] = jButton110;
        button[110] = jButton111;
        button[111] = jButton112;
        button[112] = jButton113;
        button[113] = jButton114;
        button[114] = jButton115;
        button[115] = jButton116;
        button[116] = jButton117;
        button[117] = jButton118;
        button[118] = jButton119;
        button[119] = jButton120;
        button[120] = jButton121;
        button[121] = jButton122;
        button[122] = jButton123;
        button[123] = jButton124;
        button[124] = jButton125;
        button[125] = jButton126;
        button[126] = jButton127;
        button[127] = jButton128;
        button[128] = jButton129;
        button[129] = jButton130;
        button[130] = jButton131;
        button[131] = jButton132;
        button[132] = jButton133;
        button[133] = jButton134;
        button[134] = jButton135;
        button[135] = jButton136;
        button[136] = jButton137;
        button[137] = jButton138;
        button[138] = jButton139;
        button[139] = jButton140;
        button[140] = jButton141;
        button[141] = jButton142;
        button[142] = jButton143;
        button[143] = jButton144;
        button[144] = jButton145;
        button[145] = jButton146;
        button[146] = jButton147;
        button[147] = jButton148;
        button[148] = jButton149;
        button[149] = jButton150;
        button[150] = jButton151;
        button[151] = jButton152;
        button[152] = jButton153;
        button[153] = jButton154;
        button[154] = jButton155;
        button[155] = jButton156;
        button[156] = jButton157;
        button[157] = jButton158;
        button[158] = jButton159;
        button[159] = jButton160;
        button[160] = jButton161;
        button[161] = jButton162;
        button[162] = jButton163;
        button[163] = jButton164;
        button[164] = jButton165;
        button[165] = jButton166;
        button[166] = jButton167;
        button[167] = jButton168;
        button[168] = jButton169;
        button[169] = jButton170;
        button[170] = jButton171;
        button[171] = jButton172;
        button[172] = jButton173;
        button[173] = jButton174;
        button[174] = jButton175;
        button[175] = jButton176;
        button[176] = jButton177;
        button[177] = jButton178;
        button[178] = jButton179;
        button[179] = jButton180;
        button[180] = jButton181;
        button[181] = jButton182;
        button[182] = jButton183;
        button[183] = jButton184;
        button[184] = jButton185;
        button[185] = jButton186;
        button[186] = jButton187;
        button[187] = jButton188;
        button[188] = jButton189;
        button[189] = jButton190;
        button[190] = jButton191;
        button[191] = jButton192;
        button[192] = jButton193;
        button[193] = jButton194;
        button[194] = jButton195;
        button[195] = jButton196;
        button[196] = jButton197;
        button[197] = jButton198;
        button[198] = jButton199;
        button[199] = jButton200;

        for (JButton currentButton : button) {
            for (ActionListener al : currentButton.getActionListeners()) {
                currentButton.removeActionListener(al);
            }
        }

        for (JButton button1 : button) {
            button1.setText("");
            button1.setBackground(new Color(255, 255, 153));
            button1.setIcon(null);
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from optionset "
                    + "where pcode='" + PCodeItem + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String POpName = ThaiUtil.ASCII2Unicode(rs.getString("OptionName"));
                String PNameEx = ThaiUtil.ASCII2Unicode(rs.getString("pdesc"));
                this.PNameSet = PNameEx;
                String PCodeEx = ThaiUtil.ASCII2Unicode(rs.getString("pcode"));
                this.PCodeSet = PCodeEx;
                button[count].setText("<html><center>" + POpName + "</center></html>");
                button[count].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton btn1 = (JButton) e.getSource();
                        String Extra = btn1.getText().replace("<html>", "").replace("<center>", "").replace("</center>", "").replace("</html>", "");
                        UpdateTempmenusetExtraOption(PCodeSet, Extra);
                        dispose();
                    }

                    private void UpdateTempmenusetExtraOption(String PCode, String POption) {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "update tempset set "
                                    + "POption='" + ThaiUtil.Unicode2ASCII(POption) + "' "
                                    + "where PCode='" + PCode + "'";
                            Statement stmt = mysql.getConnection().createStatement();
                            stmt.executeUpdate(sql);
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }
                    }
                });
                count++;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            System.err.println(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    private boolean showPopupOption(String pCodeItem) {
        boolean isCheck = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from optionset where pcode ='" + pCodeItem + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isCheck = true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            System.err.println(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return isCheck;
    }

    private void UpdateTempmenusetExtra(String Index, String PCode, String PName, String Option, String TryName) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select ex_pcode , ex_pdesc from mgrbuttonsetup "
                    + "where ex_pdesc = '" + ThaiUtil.Unicode2ASCII(PName) + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                TempSetBean tempBean = new TempSetBean();
                tempBean.setPTableNo(TableNo);
                tempBean.setPIndex(Index);
                tempBean.setPCode(PCode);
                tempBean.setPDesc(PName);
                tempBean.setPPostStock(PUtility.GetStkCode());
                tempBean.setPProTry(TryName);
                tempBean.setPOption(Option);
                tempBean.setPTime("curtime()");

                TempSetController.save(tempBean, mysql);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            
        } finally {
            mysql.close();
        }

    }

    private boolean loadMenu2Pcs() {
        boolean show = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select qty from mgrbuttonsetup "
                    + "where pcode='" + PCode + "' "
                    + "and check_qty='Y'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                show = true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        if (!show) {
            return false;
        }

        JButton[] button = new JButton[200];
        button[0] = jButton1;
        button[1] = jButton2;
        button[2] = jButton3;
        button[3] = jButton4;
        button[4] = jButton5;
        button[5] = jButton6;
        button[6] = jButton7;
        button[7] = jButton8;
        button[8] = jButton9;
        button[9] = jButton10;
        button[10] = jButton11;
        button[11] = jButton12;
        button[12] = jButton13;
        button[13] = jButton14;
        button[14] = jButton15;
        button[15] = jButton16;
        button[16] = jButton17;
        button[17] = jButton18;
        button[18] = jButton19;
        button[19] = jButton20;
        button[20] = jButton21;
        button[21] = jButton22;
        button[22] = jButton23;
        button[23] = jButton24;
        button[24] = jButton25;
        button[25] = jButton26;
        button[26] = jButton27;
        button[27] = jButton28;
        button[28] = jButton29;
        button[29] = jButton30;
        button[30] = jButton31;
        button[31] = jButton32;
        button[32] = jButton33;
        button[33] = jButton34;
        button[34] = jButton35;
        button[35] = jButton36;
        button[36] = jButton37;
        button[37] = jButton38;
        button[38] = jButton39;
        button[39] = jButton40;
        button[40] = jButton41;
        button[41] = jButton42;
        button[42] = jButton43;
        button[43] = jButton44;
        button[44] = jButton45;
        button[45] = jButton46;
        button[46] = jButton47;
        button[47] = jButton48;
        button[48] = jButton49;
        button[49] = jButton50;
        button[50] = jButton51;
        button[51] = jButton52;
        button[52] = jButton53;
        button[53] = jButton54;
        button[54] = jButton55;
        button[55] = jButton56;
        button[56] = jButton57;
        button[57] = jButton58;
        button[58] = jButton59;
        button[59] = jButton60;
        button[60] = jButton61;
        button[61] = jButton62;
        button[62] = jButton63;
        button[63] = jButton64;
        button[64] = jButton65;
        button[65] = jButton66;
        button[66] = jButton67;
        button[67] = jButton68;
        button[68] = jButton69;
        button[69] = jButton70;
        button[70] = jButton71;
        button[71] = jButton72;
        button[72] = jButton73;
        button[73] = jButton74;
        button[74] = jButton75;
        button[75] = jButton76;
        button[76] = jButton77;
        button[77] = jButton78;
        button[78] = jButton79;
        button[79] = jButton80;
        button[80] = jButton81;
        button[81] = jButton82;
        button[82] = jButton83;
        button[83] = jButton84;
        button[84] = jButton85;
        button[85] = jButton86;
        button[86] = jButton87;
        button[87] = jButton88;
        button[88] = jButton89;
        button[89] = jButton90;
        button[90] = jButton91;
        button[91] = jButton92;
        button[92] = jButton93;
        button[93] = jButton94;
        button[94] = jButton95;
        button[95] = jButton96;
        button[96] = jButton97;
        button[97] = jButton98;
        button[98] = jButton99;
        button[99] = jButton100;
        button[100] = jButton101;
        button[101] = jButton102;
        button[102] = jButton103;
        button[103] = jButton104;
        button[104] = jButton105;
        button[105] = jButton106;
        button[106] = jButton107;
        button[107] = jButton108;
        button[108] = jButton109;
        button[109] = jButton110;
        button[110] = jButton111;
        button[111] = jButton112;
        button[112] = jButton113;
        button[113] = jButton114;
        button[114] = jButton115;
        button[115] = jButton116;
        button[116] = jButton117;
        button[117] = jButton118;
        button[118] = jButton119;
        button[119] = jButton120;
        button[120] = jButton121;
        button[121] = jButton122;
        button[122] = jButton123;
        button[123] = jButton124;
        button[124] = jButton125;
        button[125] = jButton126;
        button[126] = jButton127;
        button[127] = jButton128;
        button[128] = jButton129;
        button[129] = jButton130;
        button[130] = jButton131;
        button[131] = jButton132;
        button[132] = jButton133;
        button[133] = jButton134;
        button[134] = jButton135;
        button[135] = jButton136;
        button[136] = jButton137;
        button[137] = jButton138;
        button[138] = jButton139;
        button[139] = jButton140;
        button[140] = jButton141;
        button[141] = jButton142;
        button[142] = jButton143;
        button[143] = jButton144;
        button[144] = jButton145;
        button[145] = jButton146;
        button[146] = jButton147;
        button[147] = jButton148;
        button[148] = jButton149;
        button[149] = jButton150;
        button[150] = jButton151;
        button[151] = jButton152;
        button[152] = jButton153;
        button[153] = jButton154;
        button[154] = jButton155;
        button[155] = jButton156;
        button[156] = jButton157;
        button[157] = jButton158;
        button[158] = jButton159;
        button[159] = jButton160;
        button[160] = jButton161;
        button[161] = jButton162;
        button[162] = jButton163;
        button[163] = jButton164;
        button[164] = jButton165;
        button[165] = jButton166;
        button[166] = jButton167;
        button[167] = jButton168;
        button[168] = jButton169;
        button[169] = jButton170;
        button[170] = jButton171;
        button[171] = jButton172;
        button[172] = jButton173;
        button[173] = jButton174;
        button[174] = jButton175;
        button[175] = jButton176;
        button[176] = jButton177;
        button[177] = jButton178;
        button[178] = jButton179;
        button[179] = jButton180;
        button[180] = jButton181;
        button[181] = jButton182;
        button[182] = jButton183;
        button[183] = jButton184;
        button[184] = jButton185;
        button[185] = jButton186;
        button[186] = jButton187;
        button[187] = jButton188;
        button[188] = jButton189;
        button[189] = jButton190;
        button[190] = jButton191;
        button[191] = jButton192;
        button[192] = jButton193;
        button[193] = jButton194;
        button[194] = jButton195;
        button[195] = jButton196;
        button[196] = jButton197;
        button[197] = jButton198;
        button[198] = jButton199;
        button[199] = jButton200;

        mysql.open();
        try {
            String menuSub = "";
            if (MenuCode.length() > 5) {
                menuSub = MenuCode.substring(0, 5);
            }
            String sql = "select * from soft_menusetup "
                    + "where menucode like '" + menuSub + "%' and menutype='1'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                button[count].setName(rs.getString("PCode"));
                button[count].setText("<html><center>" + ThaiUtil.ASCII2Unicode(rs.getString("MenuShowText")) + "</center></html>");
                button[count].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //
                        JButton btn = (JButton) e.getSource();
                        String ProMain = btn.getText().replace("<html>", "").replace("<center>", "").replace("</center>", "").replace("</html>", "");
                        String PCode = btn.getName();

                        UpdateTempmenusetProduct("", PCode, PName, ProMain, "qty");
                        loadProductSideDish();
                    }

                    private void UpdateTempmenusetProduct(String Index, String PCode, String PName, String ProMain, String Main) {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sqll = "select pcode, pdesc from mgrbuttonsetup "
                                    + "where pcode = '" + PCode + "'";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rss = stmt.executeQuery(sqll);
                            if (rss.next()) {
                                TempSetBean tempBean = new TempSetBean();
                                tempBean.setPTableNo(TableNo);
                                tempBean.setPIndex(Index);
                                tempBean.setPCode(rss.getString("pcode"));
                                tempBean.setPDesc(ProMain);
                                tempBean.setPPostStock(PUtility.GetStkCode());
                                tempBean.setPProTry(Main);
                                tempBean.setPOption("");
                                tempBean.setPTime("curtime()");

                                TempSetController.save(tempBean, mysql);
                            }

                            rss.close();
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(null, e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }
                    }
                });
                count++;
            }

            if (count == 0) {
                loadProductSideDish();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            System.err.println(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return show;
    }
}
