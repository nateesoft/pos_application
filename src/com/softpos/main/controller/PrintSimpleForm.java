package com.softpos.main.controller;

import com.softpos.database.util.MySQLConnect;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.print.PrintService;
import com.softpos.main.controller.TableFileControl;
import com.softpos.main.model.TableFileBean;
import com.softpos.main.model.Value;
import com.softpos.database.util.ConfigFile;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class PrintSimpleForm {

    static SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
    private DecimalFormat df = new DecimalFormat("##0.00");
    private DecimalFormat inf = new DecimalFormat("#,##0");
    private String Space = " &nbsp; ";
    private String TAB = Space + Space + Space;
    private String TAB2 = TAB + TAB;

    public String DataFullR(String Str, int Len) {
        String ReturnStr;
        String AddStr = "";
        int List1[] = {209, 212, 213, 214, 215, 216, 217, 218, 219, 231, 232, 233, 234, 235, 236, 237};
        if (Len < Str.length()) {
            ReturnStr = Str.substring(0, Len - 1);
        } else {
            for (int i = 1; i <= (Len - Str.length()); i++) {
                AddStr = AddStr + " ";
            }

            ReturnStr = Str.trim() + AddStr;
        }
        int I = 0;
        int ICnt = 0;
        char ch;
        String TempStr = ThaiUtil.Unicode2ASCII(ReturnStr);
        while (I <= TempStr.length() - 1) {   // Check TIS Upper }

            ch = TempStr.charAt(I);
            if (searchArray((int) ch, List1) != -1) {
                ICnt++;
            }
            I = I + 1;
        }

        if (ICnt > 0) {
            for (int i = 1; i <= ICnt; i++) {
                ReturnStr = ReturnStr + " ";
            }
        }
        return ReturnStr;
    }

    public int searchArray(int key, int[] list) {
        int ans = -1;
        for (int i = 0; i < list.length; i++) {
            if (key == list[i]) {
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrintSimpleForm psf = new PrintSimpleForm();
        psf.printTest("KIC1", "ทดสอบการส่งข้อมูลออกปริ้นเตอร Test Test");
    }

    public void KIC_FORM_1(String printerName, final String tableNo, final String[] PCode) {
        String t = "";
        String tt = "";
        String td = "";
        //FORM 1 **** 
        //จะ CUT ปริ้นทีละสินค้า โดย group by จำนวนสินค้า//
        //โต๊ะ 1           C0
        //***** Eat In *****
        //น้ำลำใย            5
        //__________________
        //28/04/2014 14:15 001/
        //โต๊ะ 1           C0
        //***** Eat In *****
        //น้าตะไคร้ใบเตย       2
        //__________________
        //28/04/2014 14:15 001/
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
                String PrinterName = printerName;
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
//                    break;
                }
            }
//            pj.setPrintService(ps[prnIndex]);
        } catch (Exception e) {
            
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        ItemVoidPrint(printerName, tableNo, PCode, "E");
        try {
            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,R_Quan R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "and R_PluCode='" + PCode[0] + "' "
                    + "and R_ETD='E' "
                    + "and R_VOID<>'V' "
                    //                    + sqlAdd
                    + "order by R_Index";
//            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
//                    + "R_Price, b.Macno,R_Date, R_Time,"
//                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                    + "from tablefile t,balance b "
//                    + "where t.tcode=b.r_table "
//                    + "and r_table='" + tableNo + "' "
//                    + "and R_PrintOK='Y' "
//                    + "and R_KicPrint<>'P' "
//                    + "and R_Kic<>'' "
//                    + "and R_PluCode='" + PCode[0] + "' "
//                    + "and R_ETD='E' "
//                    + "and R_VOID<>'V' "
//                    //                    + sqlAdd
//                    + "group by R_PluCode "
//                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                printerName = rs.getString("R_Kic");
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                String ETD = rs.getString("R_ETD");
                String macNo = rs.getString("macno");
                String custCount = rs.getString("TCustomer");
//                int qty = rs.getInt("R_Quan");
                double qty = rs.getDouble("R_Quan");
                String TUser = getEmpName(rs.getString("R_Emp"));
                String r_index = (rs.getString("R_Index"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + r_index + "' "
                            + "and r_void<>'V' ";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt2.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            } else {
                                RVo = RVo;
                            }

                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt2.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                t += "align=left><font face=Angsana New size=5>" + "โต๊ะ " + rs.getString("TCode") + "</td><td align=right><font face=Angsana New size=5>" + " CC. " + "</td><td align=left><font face=Angsana New size=5>" + custCount + "_";
                t += "colspan=3 align=center>_";
                if (ETD.equals("E")) {
//                    ETD = "*** DINE IN ***";
                    ETD = "*** ทานในร้าน ***";
                } else if (ETD.equals("T")) {
//                    ETD = "*** TAKE AWAY ***";
                    ETD = "*** ห่อกลับ ***";
                } else if (ETD.equals("D")) {
//                    ETD = "*** DELIVERY ***";
                    ETD = "*** ส่งถึงบ้าน ***";
                } else if (ETD.equals("P")) {
//                    ETD = "*** PINTO ***";
                    ETD = "*** ปิ่นโต ***";
                } else if (ETD.equals("W")) {
//                    ETD = "*** WHOLE SALE ***";
                    ETD = "*** ขายส่ง ***";
                }

                t += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                t += "colspan=3 align=center>_";
                String product = productName;
                if (product.length() > 16) {
                    String productSubF = product.substring(0, 16);
                    String productSubR = product.substring(16);

                    product = productSubF + "\n" + productSubR;

                }
//                t += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
                t += "colspan=3 align=left><font face=Angsana New size=5>" + (product) + "_";

                t += "<td colspan=3 align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
//                t += "colspan=3 align=right><font face=Angsana New size=3>" + "จำนวน." + Space + df.format(qty) + TAB + "-_";

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                t += "colspan=5 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                t += "colspan=3 align=left><font face=Angsana New size=2>" + simp.format(new Date()) + "_";
                t += "colspan=3 align=left><font face=Angsana New size=2>" + Space + "Mac" + Space + macNo + "/" + TUser + "_";

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                //หากมีสินค้า VOID
                try {

                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(SQLQuery2);
                        stmt1.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt3 = mysql.getConnection().createStatement();
                        stmt3.executeUpdate(sqlK);
                        stmt3.close();
                    }
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
                PrintDriver pd = new PrintDriver();
                String[] strs = t.split("_");
                for (String data1 : strs) {
                    Value.printerDriverKitChenName = "kic" + printerName;
                    pd.addTextIFont(data1);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
                pd.printHTMLKitChen();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
//        PrintDriver pd = new PrintDriver();
//        String[] strs = t.split("_");
//        for (String data1 : strs) {
//            Value.printerDriverKitChenName = "kic" + printerName;
//            pd.addTextIFont(data1);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//            }
//        }

//        pd.printHTMLKitChen();
        mysql.open();
        ItemVoidPrint(printerName, tableNo, PCode, "T");
        try {
            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "and R_PluCode='" + PCode[0] + "' "
                    + "and R_ETD='T' "
                    + "and R_VOID<>'V' "
                    //                    + sqlAdd
                    + "group by R_PluCode "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                printerName = rs.getString("R_Kic");
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                String ETD = rs.getString("R_ETD");
                String macNo = rs.getString("macno");
                String custCount = rs.getString("TCustomer");
//                int qty = rs.getInt("R_Quan");
                double qty = rs.getDouble("R_Quan");
                String TUser = getEmpName(rs.getString("R_Emp"));
                String r_index = (rs.getString("R_Index"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + r_index + "'"
                            + "and r_void<>'V' ";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt2.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            } else {
                                RVo = RVo;
                            }

                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt2.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                tt += "align=left><font face=Angsana New size=5>" + "โต๊ะ " + rs.getString("TCode") + "</td><td align=right><font face=Angsana New size=5>" + " CC. " + "</td><td align=left><font face=Angsana New size=5>" + custCount + "_";
                tt += "colspan=3 align=center>_";
                if (ETD.equals("E")) {
//                    ETD = "*** DINE IN ***";
                    ETD = "*** ทานในร้าน ***";
                } else if (ETD.equals("T")) {
//                    ETD = "*** TAKE AWAY ***";
                    ETD = "*** ห่อกลับ ***";
                } else if (ETD.equals("D")) {
//                    ETD = "*** DELIVERY ***";
                    ETD = "*** ส่งถึงบ้าน ***";
                } else if (ETD.equals("P")) {
//                    ETD = "*** PINTO ***";
                    ETD = "*** ปิ่นโต ***";
                } else if (ETD.equals("W")) {
//                    ETD = "*** WHOLE SALE ***";
                    ETD = "*** ขายส่ง ***";
                }

                tt += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                tt += "colspan=3 align=center>_";
                String product = productName;
                if (product.length() > 16) {
                    String productSubF = product.substring(0, 16);
                    String productSubR = product.substring(16);

                    product = productSubF + "\n" + productSubR;

                }
//                tt += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
                tt += "colspan=3 align=left><font face=Angsana New size=5>" + (product) + "_";
                tt += "<td colspan=3 align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
//                tt += "colspan=3 align=right><font face=Angsana New size=3>" + "จำนวน." + Space + df.format(qty) + TAB + "-_";

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                tt += "colspan=5 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                tt += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                tt += "colspan=3 align=left><font face=Angsana New size=2>" + (simp.format(new Date()) + Space + "Mac" + Space + macNo + "/" + TUser) + "_";

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(SQLQuery2);
                        stmt1.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt3 = mysql.getConnection().createStatement();
                        stmt3.executeUpdate(sqlK);
                        stmt3.close();
                    }
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
        PrintDriver pd2 = new PrintDriver();
        String[] strs1 = tt.split("_");
        for (String data2 : strs1) {
            Value.printerDriverKitChenName = "kic" + printerName;
            pd2.addTextIFont(data2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        pd2.printHTMLKitChen();

        mysql.open();
        ItemVoidPrint(printerName, tableNo, PCode, "D");
        try {
            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,R_Quan R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "and R_PluCode='" + PCode[0] + "' "
                    + "and R_ETD='D' "
                    + "and R_VOID<>'V' "
                    //                    + sqlAdd
                    + "group by R_PluCode "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                printerName = rs.getString("R_Kic");
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                String ETD = rs.getString("R_ETD");
                String macNo = rs.getString("macno");
                String custCount = rs.getString("TCustomer");
//                int qty = rs.getInt("R_Quan");
                double qty = rs.getDouble("R_Quan");
                String TUser = getEmpName(rs.getString("R_Emp"));
                String r_index = (rs.getString("R_Index"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + r_index + "'"
                            + "and r_void<>'V' ";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt2.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            } else {
                                RVo = RVo;
                            }

                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt2.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                td += "align=left><font face=Angsana New size=5>" + "โต๊ะ " + rs.getString("TCode") + "</td><td align=right><font face=Angsana New size=5>" + " CC. " + "</td><td align=left><font face=Angsana New size=5>" + custCount + "_";
                td += "colspan=3 align=center>_";
                if (ETD.equals("E")) {
//                    ETD = "*** DINE IN ***";
                    ETD = "*** ทานในร้าน ***";
                } else if (ETD.equals("T")) {
//                    ETD = "*** TAKE AWAY ***";
                    ETD = "*** ห่อกลับ ***";
                } else if (ETD.equals("D")) {
//                    ETD = "*** DELIVERY ***";
                    ETD = "*** ส่งถึงบ้าน ***";
                } else if (ETD.equals("P")) {
//                    ETD = "*** PINTO ***";
                    ETD = "*** ปิ่นโต ***";
                } else if (ETD.equals("W")) {
//                    ETD = "*** WHOLE SALE ***";
                    ETD = "*** ขายส่ง ***";
                }

                td += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                td += "colspan=3 align=center>_";
                String product = productName;
                if (product.length() > 16) {
                    String productSubF = product.substring(0, 16);
                    String productSubR = product.substring(16);

                    product = productSubF + "\n" + productSubR;

                }
//                ttd += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
                td += "colspan=3 align=left><font face=Angsana New size=5>" + (product) + "_";
                td += "<td colspan=3 align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
//                ttd += "colspan=3 align=right><font face=Angsana New size=3>" + "จำนวน." + Space + df.format(qty) + TAB + "-_";

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                td += "colspan=5 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                td += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                td += "colspan=3 align=left><font face=Angsana New size=2>" + (simp.format(new Date()) + Space + "Mac" + Space + macNo + "/" + TUser) + "_";

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(SQLQuery2);
                        stmt1.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt3 = mysql.getConnection().createStatement();
                        stmt3.executeUpdate(sqlK);
                        stmt3.close();
                    }
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
        PrintDriver pd3 = new PrintDriver();
        String[] strs3 = td.split("_");
        for (String data3 : strs3) {
            Value.printerDriverKitChenName = "kic" + printerName;
            pd3.addTextIFont(data3);
        }

        pd3.printHTMLKitChen();

    }
//    public void KIC_FORM_1(String printerName, final String tableNo, final String[] PCode) {
//        final int SpaceFront = 25;
//        final int PaperMaxLength = 18;
//
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PrintService[] ps = PrinterJob.lookupPrintServices();
//        int prnIndex = 0;
//        try {
//            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
//                if (PrinterName.equalsIgnoreCase(printerName)) {
//                    prnIndex = i;
//                    break;
//                }
//            }
//            pj.setPrintService(ps[prnIndex]);
//
//            PageFormat pf = new PageFormat();
//            Paper pp = new Paper();
//            pp.setSize(500, 1000);
//            pp.setImageableArea(0, 0, 594, 846);
//            pf.setPaper(pp);
//            pj.setPrintable(new Printable() {
//
//                //**** FORM 1 **** 
//                //จะ CUT ปริ้นทีละสินค้า โดย group by จำนวนสินค้า//
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำลำใย            5
//                //__________________
//                //28/04/2014 14:15 001/
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้าตะไคร้ใบเตย       2
//                //__________________
//                //28/04/2014 14:15 001/
//                @Override
//                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
//                    Graphics2D g2 = (Graphics2D) g;
//                    if (index == 0) {
//                        String sqlAdd = "";
//                        if (PCode.length == 1) {
//                            sqlAdd = "and R_PluCode='" + PCode[0] + "' ";
//                        } else if (PCode.length > 1) {
//                            sqlAdd = "and R_PluCode in(";
//                            for (int i = 0; i < PCode.length; i++) {
//                                sqlAdd += "'" + PCode[i] + "'";
//                                if (i < PCode.length) {
//                                    sqlAdd += ",";
//                                }
//                            }
//                            sqlAdd += ") ";
//                        }
//
//                        MySQLConnect mysql = new MySQLConnect();
//                        mysql.open();
//                        try {
//                            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
//                                    + "R_Price, b.Macno,R_Date, R_Time,"
//                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                                    + "from tablefile t,balance b "
//                                    + "where t.tcode=b.r_table "
//                                    + "and r_table='" + tableNo + "' "
//                                    + "and R_PrintOK='Y' "
//                                    + "and R_KicPrint<>'P' "
//                                    + "and R_Kic<>'' "
//                                    + sqlAdd
//                                    + "group by R_PluCode "
//                                    + "order by R_Index";
//                            Statement stmt = mysql.getConnection().createStatement();
//                            ResultSet rs = stmt.executeQuery(sql);
//                            int line = 0;
//                            while (rs.next()) {
//                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
//                                String ETD = rs.getString("R_ETD");
//                                String macNo = rs.getString("macno");
//                                String custCount = rs.getString("TCustomer");
//                                int qty = rs.getInt("R_Quan");
//                                String TUser = getEmpName(rs.getString("R_Emp"));
//
//                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
//                                ArrayList<String[]> listOpt = new ArrayList<>();
//                                try {
//                                    String sqlOpt = "select * from balance "
//                                            + "where r_table='" + tableNo + "' "
//                                            + "and r_pluCode='" + rs.getString("R_PluCode") + "'";
//                                    Statement stmt2 = mysql.getConnection().createStatement();
//                                    ResultSet rsOpt = stmt2.executeQuery(sqlOpt);
//                                    while (rsOpt.next()) {
//                                        String Vo = rsOpt.getString("R_Void");
//                                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
//                                        if (Vo.equals("V")) {
//                                            if (!RVo.equals("")) {
//                                                RVo = "ยกเลิก " + RVo;
//                                            } else {
//                                                RVo = RVo;
//                                            }
//
//                                        }
//                                        String[] OPT = new String[]{
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
//                                            RVo
//                                        };
//
//                                        listOpt.add(OPT);
//                                    }
//
//                                    rsOpt.close();
//                                    stmt2.close();
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//
//                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
//                                line += 20;
//                                g2.setFont(new Font("Angsana New", Font.PLAIN, 30));
//                                String tableHead = PUtility.DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 1);
//                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
//                                line += 25;
//
//                                //print ETD
//                                printG(g2, ETD, line);
//
//                                line += 25;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 16));
//                                String product = PUtility.DataFullR(productName, 40);
//                                g2.drawString(product.substring(0, 12), SpaceFront, line);
//
//                                g2.drawString("" + qty, SpaceFront + 150, line);
//                                System.out.println(product + qty);
//                                //********* พิมพ์ข้อความพิเศษ *************
//                                for (int x = 0; x < listOpt.size(); x++) {
//                                    String[] OPT = (String[]) listOpt.get(x);
//                                    for (String OPT1 : OPT) {
//                                        if (OPT1 != null) {
//                                            if (!OPT1.trim().equals("")) {
//                                                line += 25;
//                                                g2.drawString("*** " + OPT1, SpaceFront + 5, line);
//                                            }
//                                        }
//                                    }
//                                }
//                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
//
//                                line += 25;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 20));
//                                g2.drawString("-----------------------------------------", SpaceFront, line);
//                                line += 25;
//
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 18));
//                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);
//
//                                line += 25;
//
//                                //add kictran data
//                                String R_Que = SeekKicItemNo();
//                                int TempQue = Integer.parseInt(R_Que);
//                                String R_VOID = rs.getString("R_Void");
//                                if (R_VOID == null) {
//                                    R_VOID = "";
//                                }
//                                try {
//                                    if (R_VOID.equals("V")) {
//                                        String SQLQuery2 = "update kictran "
//                                                + "set pvoid = 'V' "
//                                                + "where pindex ='" + rs.getString("R_Index") + "' "
//                                                + "and ptable='" + rs.getString("R_Table") + "' "
//                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
//                                                + "and pkic='" + rs.getString("R_Kic") + "' "
//                                                + "and pflage='N'";
//                                        Statement stmt1 = mysql.getConnection().createStatement();
//                                        stmt1.executeUpdate(SQLQuery2);
//                                        stmt1.close();
//                                    } else {
//                                        String sqlK = "insert into kictran "
//                                                + "(pitemno,pdate,pcode,pqty,pindex,"
//                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
//                                                + "values (" + TempQue + ",curdate(),"
//                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
//                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
//                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
//                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
//                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
//                                        Statement stmt3 = mysql.getConnection().createStatement();
//                                        stmt3.executeUpdate(sqlK);
//                                        stmt3.close();
//                                    }
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//                            }
//
//                            rs.close();
//                            stmt.close();
//                        } catch (SQLException e) {
//                            MSG.ERR(e.getMessage());
//                            
//                        } finally {
//                            mysql.close();
//                        }
//
//                        return PAGE_EXISTS;
//                    } else {
//                        return NO_SUCH_PAGE;
//                    }
//                }
//            }, pf);
//            try {
//                pj.print();
//            } catch (PrinterException e) {
//                MSG.ERR(e.getMessage());
//                
//            }
//        } catch (PrinterException ex) {
//            MSG.ERR(ex.getMessage());
//            
//        }
//    }

    public void KIC_FORM_2(String printerName, final String tableNo, final String[] PCode) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;
        String t = "";

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
//                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 2 **** 
                //จะรวมจำนวนสินค้าแต่ละรายการทั้งหมด แล้วค่อย CUT กระดาษ พร้อมทั้งแสดงราคา//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            
                //จำนวน  2 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำตะใคร้ใบเตย      
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        String sqlAdd = "";
                        if (PCode.length == 1) {
                            sqlAdd = "and R_PluCode='" + PCode[0] + "' ";
                        } else if (PCode.length > 1) {
                            sqlAdd = "and R_PluCode in(";
                            for (int i = 0; i < PCode.length; i++) {
                                sqlAdd += "'" + PCode[i] + "'";
                                if (i < PCode.length) {
                                    sqlAdd += ",";
                                }
                            }
                            sqlAdd += ") ";
                        }

                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "select TUser,R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + sqlAdd
                                    + "group by R_PluCode order by R_Index";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            int line = 0;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                String macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                String TUser = getEmpName(rs.getString("R_Emp"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    Statement stmt1 = mysql.getConnection().createStatement();
                                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                                    while (rsOpt.next()) {
                                        String Vo = rsOpt.getString("R_Void");
                                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                                        if (Vo.equals("V")) {
                                            if (!RVo.equals("")) {
                                                RVo = "ยกเลิก " + RVo;
                                            } else {
                                                RVo = RVo;
                                            }

                                        }
                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            RVo
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                    stmt1.close();
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                line += 25;
                                g2.setFont(new Font("Thahoma", Font.PLAIN, 16));
                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                line += 25;

                                //print ETD
                                printG(g2, ETD, line);

                                line += 25;
                                g2.setFont(new Font("Thahoma", Font.PLAIN, 14));
                                g2.drawString(productName, SpaceFront, line);
                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("*** " + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                                line += 20;
                                g2.setFont(new Font("Thahoma", Font.PLAIN, 14));
                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
                                line += 20;
                                g2.drawString("-----------------------------------------", SpaceFront, line);
                                line += 20;

                                g2.setFont(new Font("Thahoma", Font.PLAIN, 12));
                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                                line += 25;
                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        Statement stmt1 = mysql.getConnection().createStatement();
                                        stmt1.executeUpdate(SQLQuery2);
                                        stmt1.close();
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        Statement stmt1 = mysql.getConnection().createStatement();
                                        stmt1.executeUpdate(sqlK);
                                        stmt1.close();
                                    }
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

                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                MSG.ERR(e.getMessage());
                
            }
        } catch (PrinterException ex) {
//            MSG.ERR(ex.getMessage());
//            
        }
    }
//    public void KIC_FORM_2(String printerName, final String tableNo, final String[] PCode) {
//        final int SpaceFront = 25;
//        final int PaperMaxLength = 28;
//        String t = "";
//        
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PrintService[] ps = PrinterJob.lookupPrintServices();
//        int prnIndex = 0;
//        try {
//            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
//                if (PrinterName.equalsIgnoreCase(printerName)) {
//                    prnIndex = i;
//                    break;
//                }
//            }
//            pj.setPrintService(ps[prnIndex]);
//
//            PageFormat pf = new PageFormat();
//            Paper pp = new Paper();
//            pp.setSize(500, 1000);
//            pp.setImageableArea(0, 0, 594, 846);
//            pf.setPaper(pp);
//            pj.setPrintable(new Printable() {
//
//                //**** FORM 2 **** 
//                //จะรวมจำนวนสินค้าแต่ละรายการทั้งหมด แล้วค่อย CUT กระดาษ พร้อมทั้งแสดงราคา//
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำลำใย            
//                //จำนวน  2 ราคา 45.00
//                //__________________
//                //28/04/2014 14:15 001/
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำตะใคร้ใบเตย      
//                //จำนวน  1 ราคา 45.00
//                //__________________
//                //28/04/2014 14:15 001/
//                @Override
//                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
//                    Graphics2D g2 = (Graphics2D) g;
//                    if (index == 0) {
//                        String sqlAdd = "";
//                        if (PCode.length == 1) {
//                            sqlAdd = "and R_PluCode='" + PCode[0] + "' ";
//                        } else if (PCode.length > 1) {
//                            sqlAdd = "and R_PluCode in(";
//                            for (int i = 0; i < PCode.length; i++) {
//                                sqlAdd += "'" + PCode[i] + "'";
//                                if (i < PCode.length) {
//                                    sqlAdd += ",";
//                                }
//                            }
//                            sqlAdd += ") ";
//                        }
//
//                        MySQLConnect mysql = new MySQLConnect();
//                        mysql.open();
//                        try {
//                            String sql = "select TUser,R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
//                                    + "R_Price, b.Macno,R_Date, R_Time,"
//                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                                    + "from tablefile t,balance b "
//                                    + "where t.tcode=b.r_table "
//                                    + "and r_table='" + tableNo + "' "
//                                    + "and R_PrintOK='Y' "
//                                    + "and R_KicPrint<>'P' "
//                                    + "and R_Kic<>'' "
//                                    + sqlAdd
//                                    + "group by R_PluCode order by R_Index";
//                            Statement stmt = mysql.getConnection().createStatement();
//                            ResultSet rs = stmt.executeQuery(sql);
//                            int line = 0;
//                            while (rs.next()) {
//                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
//                                String ETD = rs.getString("R_ETD");
//                                String macNo = rs.getString("macno");
//                                String custCount = rs.getString("TCustomer");
//                                int qty = rs.getInt("R_Quan");
//                                String TUser = getEmpName(rs.getString("R_Emp"));
//
//                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
//                                ArrayList<String[]> listOpt = new ArrayList<>();
//                                try {
//                                    String sqlOpt = "select * from balance "
//                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
//                                    Statement stmt1 = mysql.getConnection().createStatement();
//                                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
//                                    while (rsOpt.next()) {
//                                        String Vo = rsOpt.getString("R_Void");
//                                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
//                                        if (Vo.equals("V")) {
//                                            if (!RVo.equals("")) {
//                                                RVo = "ยกเลิก " + RVo;
//                                            } else {
//                                                RVo = RVo;
//                                            }
//
//                                        }
//                                        String[] OPT = new String[]{
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
//                                            RVo
//                                        };
//
//                                        listOpt.add(OPT);
//                                    }
//
//                                    rsOpt.close();
//                                    stmt1.close();
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//
//                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
//                                line += 25;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 16));
//                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
//                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
//                                line += 25;
//
//                                //print ETD
//                                printG(g2, ETD, line);
//
//                                line += 25;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 14));
//                                g2.drawString(productName, SpaceFront, line);
//                                //********* พิมพ์ข้อความพิเศษ *************
//                                for (int x = 0; x < listOpt.size(); x++) {
//                                    String[] OPT = (String[]) listOpt.get(x);
//                                    for (String OPT1 : OPT) {
//                                        if (OPT1 != null) {
//                                            if (!OPT1.trim().equals("")) {
//                                                line += 20;
//                                                g2.drawString("*** " + OPT1, SpaceFront + 5, line);
//                                            }
//                                        }
//                                    }
//                                }
//                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
//                                line += 20;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 14));
//                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
//                                line += 20;
//                                g2.drawString("-----------------------------------------", SpaceFront, line);
//                                line += 20;
//
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 12));
//                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);
//
//                                line += 25;
//                                //add kictran data
//                                String R_Que = SeekKicItemNo();
//                                int TempQue = Integer.parseInt(R_Que);
//                                String R_VOID = rs.getString("R_Void");
//                                if (R_VOID == null) {
//                                    R_VOID = "";
//                                }
//                                try {
//                                    if (R_VOID.equals("V")) {
//                                        String SQLQuery2 = "update kictran "
//                                                + "set pvoid = 'V' "
//                                                + "where pindex ='" + rs.getString("R_Index") + "' "
//                                                + "and ptable='" + rs.getString("R_Table") + "' "
//                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
//                                                + "and pkic='" + rs.getString("R_Kic") + "' "
//                                                + "and pflage='N'";
//                                        Statement stmt1 = mysql.getConnection().createStatement();
//                                        stmt1.executeUpdate(SQLQuery2);
//                                        stmt1.close();
//                                    } else {
//                                        String sqlK = "insert into kictran "
//                                                + "(pitemno,pdate,pcode,pqty,pindex,"
//                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
//                                                + "values (" + TempQue + ",curdate(),"
//                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
//                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
//                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
//                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
//                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
//                                        Statement stmt1 = mysql.getConnection().createStatement();
//                                        stmt1.executeUpdate(sqlK);
//                                        stmt1.close();
//                                    }
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//                            }
//
//                            rs.close();
//                            stmt.close();
//                        } catch (SQLException e) {
//                            MSG.ERR(e.getMessage());
//                            
//                        } finally {
//                            mysql.close();
//                        }
//
//                        return PAGE_EXISTS;
//                    } else {
//                        return NO_SUCH_PAGE;
//                    }
//                }
//            }, pf);
//            try {
//                pj.print();
//            } catch (PrinterException e) {
//                MSG.ERR(e.getMessage());
//                
//            }
//        } catch (PrinterException ex) {
////            MSG.ERR(ex.getMessage());
////            
//        }
//    }

    public void KIC_FORM_3(final String sql1, final String printerName, final String tableNo, final int R_Kic) {

        //**** FORM 3 **** 
        //จะปริ้นรวมเป็นแผ่นเดียว//
        //โต๊ะ 1           C0
        //***** Eat In *****
        //น้ำลำใย            3
        //น้ำตะไคร้ใบเตย       1
        //__________________
        //28/04/2014 14:15 001/
        String t = "";
        String macNo = "";
        String TUser = "";
        MySQLConnect mysql = new MySQLConnect();
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
        } catch (Exception e) {
            
        }
        mysql.open();
        String PLUCode;
        try {
            TableFileControl tCon = new TableFileControl();
            TableFileBean tBean = tCon.getData(tableNo);
            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic='" + R_Kic + "' "
                    + "and r_opt1='' and r_void<>'V' "
                    //                    + "group by R_Index, R_PluCode, R_LinkIndex, R_ETD "
                    + "group by R_PluCode, R_LinkIndex, R_ETD "
                    + "order by R_ETD, R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String ETD = "";
            String custCount = "";

            boolean printHeader = false;
            boolean printTable = false;
            String tempHeader = "";
            String tableHead = "";

            while (rs.next()) {
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                ETD = rs.getString("R_ETD");
                if (tempHeader.equals("")) {
                    tempHeader = ETD;
                }
                if (!tempHeader.equals(ETD)) {
                    tempHeader = ETD;
                    printHeader = false;
                }
                macNo = rs.getString("macno");
                custCount = rs.getString("TCustomer");
                int qty = rs.getInt("R_Quan");
                TUser = getEmpName(rs.getString("R_Emp"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + rs.getString("R_Index") + "' "
                            + "and r_kic='" + R_Kic + "' "
                            + "and R_KicPrint<>'P'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        PLUCode = rs.getString("R_PluCode");
//                        this.PLUCode = PLUCode;
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            }
                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt1.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
//                                    *********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                if (!printHeader) {
                    if (!printTable) {
                        tableHead = "Table " + rs.getString("TCode");
                    }
                    //print ETD
                    if (ETD.equals("E")) {
                        ETD = "*** ทานในร้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ห่อกลับ ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ส่งถึงบ้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ปิ่นโต ***";
                    } else if (ETD.equals("W")) {
                        ETD = "*** ขายส่ง ***";
                    }
//                    if (ETD.equals("E")) {
//                        ETD = "*** DINE IN ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** TAKE AWAY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** DELIVERY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** PINTO ***";
//                    } else if (ETD.equals("W")) {
//                        ETD = "*** WHOLE SALE ***";
//                    }
                    t += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                    printHeader = true;
                    printTable = true;
                    //Print header
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                    //Print Table to Bottom
                    if (!tBean.getMemName().equals("")) {
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + ("Name CC: " + tBean.getMemName() + " / " + tableHead) + "_";
                    } else {
                        t += "colspan=1 align=left><font face=Angsana New size=5>" + (tableHead) + "</td><td colspan=2 align=right><font face=Angsana New size=5>" + "C " + tBean.getTCustomer() + "_";
                    }
//                    t += "colspan=3 align=left><font face=Angsana New size=3>" + ("" + simp.format(new Date())) + "_";
//                    t += "colspan=2 align=left><font face=Angsana New size=3>" + "Terminal : " + Space + macNo + "</td><td align=right><font face=Angsana New size=3>" + (" Name :" + TUser) + "_";
//                    t += "colspan=3 align=right><font face=Angsana New size=3>" + ("Terminal : " + macNo) + "_";
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
                }

                String product = (productName);

                String R_Index = rs.getString("R_Index");

                t += keepTextShow(R_Index, qty, product);

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                t += "colspan=3 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                                    //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(SQLQuery2);
                        stmt2.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(sqlK);
                        stmt2.close();
                    }

                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
                //END TEMP UPDATE
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
        //Print VOID
        try {
            TableFileControl tCon = new TableFileControl();
            TableFileBean tBean = tCon.getData(tableNo);
            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic='" + R_Kic + "' "
                    + "and r_opt1='' and r_void='V' "
                    //                    + "group by R_Index, R_PluCode, R_LinkIndex, R_ETD "
                    + "group by R_PluCode, R_LinkIndex, R_ETD "
                    + "order by R_ETD, R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String ETD = "";
            String custCount = "";

            boolean printHeader = false;
            boolean printTable = false;
            String tempHeader = "";
            String tableHead = "";

            while (rs.next()) {
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                ETD = rs.getString("R_ETD");
                if (tempHeader.equals("")) {
                    tempHeader = ETD;
                }
                if (!tempHeader.equals(ETD)) {
                    tempHeader = ETD;
                    printHeader = false;
                }
                macNo = rs.getString("macno");
                custCount = rs.getString("TCustomer");
                int qty = rs.getInt("R_Quan");
                TUser = getEmpName(rs.getString("R_Emp"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + rs.getString("R_Index") + "' "
                            + "and r_kic='" + R_Kic + "' "
                            + "and R_KicPrint<>'P'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        PLUCode = rs.getString("R_PluCode");
//                        this.PLUCode = PLUCode;
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก" + RVo;
                            }
                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt1.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
//                                    *********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                if (!printHeader) {
                    if (!printTable) {
                        tableHead = "Table " + rs.getString("TCode");
                    }
                    //print ETD
                    if (ETD.equals("E")) {
                        ETD = "*** ทานในร้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ห่อกลับ ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ส่งถึงบ้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ปิ่นโต ***";
                    } else if (ETD.equals("W")) {
                        ETD = "*** ขายส่ง ***";
                    }
//                    if (ETD.equals("E")) {
//                        ETD = "*** DINE IN ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** TAKE AWAY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** DELIVERY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** PINTO ***";
//                    } else if (ETD.equals("W")) {
//                        ETD = "*** WHOLE SALE ***";
//                    }
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
                    t += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
//                    printHeader = true;
//                    printTable = true;
                    //Print header
//                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                    //Print Table to Bottom
//                    if (!tBean.getMemName().equals("")) {
//                        t += "colspan=3 align=left><font face=Angsana New size=5>" + ("Name CC: " + tBean.getMemName() + " / " + tableHead) + "_";
//                    } else {
//                        t += "colspan=1 align=left><font face=Angsana New size=5>" + (tableHead) + "</td><td colspan=2 align=right><font face=Angsana New size=5>" + "C " + tBean.getTCustomer() + "_";
//                    }
//                    t += "colspan=3 align=left><font face=Angsana New size=3>" + ("" + simp.format(new Date())) + "_";
//                    t += "colspan=2 align=left><font face=Angsana New size=3>" + "Terminal : " + Space + macNo + "</td><td align=right><font face=Angsana New size=3>" + (" Name :" + TUser) + "_";
//                    t += "colspan=3 align=right><font face=Angsana New size=3>" + ("Terminal : " + macNo) + "_";
//                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
                }

                String product = (productName);

                String R_Index = rs.getString("R_Index");

                t += keepTextShow(R_Index, qty, product);

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                if (OPT1.length() > 18) {
                                    OPT1 = OPT1.substring(0, 18);
                                    t += "colspan=3 align=left><font face=Angsana New size=4>" + Space + ("*** " + OPT1) + "_";
                                } else {
                                    t += "colspan=3 align=left><font face=Angsana New size=4>" + Space + ("*** " + OPT1) + "_";
                                }

                            }
                        }
                    }
                }
                                    //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(SQLQuery2);
                        stmt2.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(sqlK);
                        stmt2.close();
                    }

                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
                //END TEMP UPDATE
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
        try {
            TableFileControl tCon = new TableFileControl();
            TableFileBean tBean = tCon.getData(tableNo);
            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic='" + R_Kic + "' "
                    + "and R_OPT1<>'' "
                    + "group by R_Index, R_PluCode, R_LinkIndex, R_ETD "
                    + "order by R_ETD, R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String ETD = "";
            String custCount = "";
            boolean printHeader = false;
            boolean printTable = false;
            String tempHeader = "";
            String tableHead = "";

            while (rs.next()) {
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                ETD = rs.getString("R_ETD");
                if (tempHeader.equals("")) {
                    tempHeader = ETD;
                }
                if (!tempHeader.equals(ETD)) {
                    tempHeader = ETD;
                    printHeader = false;
                }
                macNo = rs.getString("macno");
                custCount = rs.getString("TCustomer");
                int qty = rs.getInt("R_Quan");
                TUser = getEmpName(rs.getString("R_Emp"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + rs.getString("R_Index") + "' "
                            + "and r_kic='" + R_Kic + "' "
                            + "and R_KicPrint<>'P'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        PLUCode = rs.getString("R_PluCode");
//                        this.PLUCode = PLUCode;
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            }
                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt1.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
//                                    *********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                if (!printHeader) {
                    if (!printTable) {
                        tableHead = "Table " + rs.getString("TCode");
                    }
                    //print ETD
                    if (ETD.equals("E")) {
                        ETD = "*** ทานในร้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ห่อกลับ ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ส่งถึงบ้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ปิ่นโต ***";
                    } else if (ETD.equals("W")) {
                        ETD = "*** ขายส่ง ***";
                    }
//                    if (ETD.equals("E")) {
//                        ETD = "*** DINE IN ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** TAKE AWAY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** DELIVERY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** PINTO ***";
//                    } else if (ETD.equals("W")) {
//                        ETD = "*** WHOLE SALE ***";
//                    }
                    //Print header
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                }

                String product = (productName);

                String R_Index = rs.getString("R_Index");

                t += keepTextShow(R_Index, qty, product);

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                t += "colspan=3 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                                    //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(SQLQuery2);
                        stmt2.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(sqlK);
                        stmt2.close();
                    }

                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
                //END TEMP UPDATE
            }
            t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
            t += "colspan=3 align=left><font face=Angsana New size=3>" + ("" + simp.format(new Date())) + "_";
            t += "colspan=2 align=left><font face=Angsana New size=3>" + "Terminal : " + Space + macNo + "</td><td align=right><font face=Angsana New size=3>" + (" Name :" + TUser) + "_";
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
        t = changeFontSize(t);
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data1 : strs) {
            Value.printerDriverKitChenName = printerName;
            pd.addTextIFont(data1);
        }

        pd.printHTMLKitChen();

    }

    public String keepTextShow(String R_Index, int qty, String product) {
        String t = "";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select R_Index, R_LinkIndex,R_Void "
                    + "from balance "
                    + "where R_Index='" + R_Index + "'";
//                    + " and r_void<>'V'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String r_void = rs.getString("R_void");
                if (r_void.equals("V")) {
                    r_void = "- ";
                    if (product.length() > 14) {
                        String productSubF = product.substring(0, 14);
                        String productSubR = product.substring(14);

                        product = productSubF + "\n" + productSubR;

                    }
                    String R_LinkIndex = rs.getString("R_LinkIndex");
                    if (R_LinkIndex.equals(R_Index)) {
//                    t += "colspan=3 align=right><font face=Angsana New size=3>" + ("" + qty) + "_";
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + inf.format(qty) + Space + (product) + "_";
//                    t += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + inf.format(qty) + TAB + "_";
                    } else if (!R_LinkIndex.equals("") && !R_LinkIndex.equals(R_Index)) {
                        t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-") + "_";
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + (product) + "_";
                    } else {
//                    t += "colspan=3 align=right><font face=Angsana New size=3>" + ("" + qty) + "_";
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + inf.format(qty) + Space + (product) + "_";
//                    t += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + inf.format(qty) + TAB + "_";
                    }
                } else {
                    r_void = " ";
                    if (product.length() > 14) {
                        String productSubF = product.substring(0, 14);
                        String productSubR = product.substring(14);

                        product = productSubF + "\n" + productSubR;

                    }
                    String R_LinkIndex = rs.getString("R_LinkIndex");
                    if (R_LinkIndex.equals(R_Index)) {
//                    t += "colspan=3 align=right><font face=Angsana New size=3>" + ("" + qty) + "_";
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + inf.format(qty) + Space + (product) + "_";
//                    t += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + inf.format(qty) + TAB + "_";
                    } else if (!R_LinkIndex.equals("") && !R_LinkIndex.equals(R_Index)) {
                        t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-") + "_";
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + (product) + "_";
                    } else {
//                    t += "colspan=3 align=right><font face=Angsana New size=3>" + ("" + qty) + "_";
                        t += "colspan=3 align=left><font face=Angsana New size=5>" + inf.format(qty) + Space + (product) + "_";
//                    t += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + inf.format(qty) + TAB + "_";
                    }
                }

            }
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
        return t;
    }

    //    public void KIC_FORM_3(final String sql1, final String printerName, final String tableNo, final int R_Kic) {
//        final int SpaceFront = 25;
//        final int SetFrontSize = 15;
//        final int PaperMaxLength = 28;
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PrintService[] ps = PrinterJob.lookupPrintServices();
//        int prnIndex = 0;
//        try {
//            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
//                if (PrinterName.equalsIgnoreCase(printerName)) {
//                    prnIndex = i;
//                    break;
//                }
//            }
//            pj.setPrintService(ps[prnIndex]);
//
//            PageFormat pf = new PageFormat();
//            Paper pp = new Paper();
////            pp.setSize(500, 1000);
////            pp.setImageableArea(0, 0, 594, 846);
//            pp.setSize(500, 300000);
//            pp.setImageableArea(0, 0, 594, 300000);
//            pf.setPaper(pp);
//            //System.out.println("donjai:" + pf.getHeight() + "," + pf.getWidth());
//            pj.setPrintable(new Printable() {
//                private String PLUCode;
//
//                //**** FORM 3 **** 
//                //จะปริ้นรวมเป็นแผ่นเดียว//
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำลำใย            3
//                //น้ำตะไคร้ใบเตย       1
//                //__________________
//                //28/04/2014 14:15 001/
//                @Override
//                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
//                    Graphics2D g2 = (Graphics2D) g;
//                    Font currentFont = g.getFont();
//                    Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
//                    if (index == 0) {
//
//                        MySQLConnect mysql = new MySQLConnect();
//                        mysql.open();
//                        try {
//                            TableFileControl tCon = new TableFileControl();
//                            TableFileBean tBean = tCon.getData(tableNo);
//                            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
//                                    + "R_Price, b.Macno,R_Date, R_Time,"
//                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                                    + "from tablefile t,balance b "
//                                    + "where t.tcode=b.r_table "
//                                    + "and r_table='" + tableNo + "' "
//                                    + "and R_PrintOK='Y' "
//                                    + "and R_KicPrint<>'P' "
//                                    + "and R_Kic='" + R_Kic + "' "
//                                    + "group by R_Index, R_PluCode, R_LinkIndex, R_ETD "
//                                    + "order by R_ETD, R_Index";
//                            Statement stmt = mysql.getConnection().createStatement();
//                            ResultSet rs = stmt.executeQuery(sql);
//
//                            String ETD = "";
//                            String custCount = "";
//                            int line = 0;
//                            String macNo = "";
//                            String TUser = "";
//                            boolean printHeader = false;
//                            boolean printTable = false;
//                            String tempHeader = "";
//                            String tableHead = "";
//
//                            while (rs.next()) {
//                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
//                                ETD = rs.getString("R_ETD");
//                                if (tempHeader.equals("")) {
//                                    tempHeader = ETD;
//                                }
//                                if (!tempHeader.equals(ETD)) {
//                                    tempHeader = ETD;
//                                    printHeader = false;
//                                }
//                                macNo = rs.getString("macno");
//                                custCount = rs.getString("TCustomer");
//                                int qty = rs.getInt("R_Quan");
//                                TUser = getEmpName(rs.getString("R_Emp"));
//
//                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
//                                ArrayList<String[]> listOpt = new ArrayList<>();
//                                try {
//                                    String sqlOpt = "select * from balance "
//                                            + "where r_table='" + tableNo + "' "
//                                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
//                                            + "and r_index='" + rs.getString("R_Index") + "' "
//                                            + "and r_kic='" + R_Kic + "' "
//                                            + "and R_KicPrint<>'P'";
//                                    Statement stmt1 = mysql.getConnection().createStatement();
//                                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
//                                    while (rsOpt.next()) {
//                                        String PLUCode = rs.getString("R_PluCode");
//                                        this.PLUCode = PLUCode;
//                                        String Vo = rsOpt.getString("R_Void");
//                                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
//                                        if (Vo.equals("V")) {
//                                            if (!RVo.equals("")) {
//                                                RVo = "ยกเลิก " + RVo;
//                                            }
//                                        }
//                                        String[] OPT = new String[]{
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
//                                            RVo
//                                        };
//
//                                        listOpt.add(OPT);
//                                    }
//
//                                    rsOpt.close();
//                                    stmt1.close();
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//
//                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
//                                if (!printHeader) {
//                                    line += 40;
//                                    g2.setFont(new Font("Browallia New", Font.PLAIN, 30));
//                                    if (!printTable) {
//                                        tableHead = DataFullR("Table " + rs.getString("TCode"), PaperMaxLength - 13);
////                                        tableHeaderBottom = tableHead + " C " + custCount;
////                                        g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
//                                    }
//                                    line += 25;
//                                    //print ETD
//                                    printG(g2, ETD, line);
//                                    printHeader = true;
//                                    printTable = true;
//                                    line += 25;
//                                    g2.setFont(newFont);
//                                    g2.setFont(new Font("EucrosiaUPC", Font.PLAIN, 19));
//                                    g2.drawString("" + simp.format(new Date()), SpaceFront, line);
//                                    line += 20;
//                                    g2.setFont(newFont);
//                                    g2.setFont(new Font("EucrosiaUPC", +Font.PLAIN, 19));
//                                    g2.drawString(" Name :" + TUser, SpaceFront, line);
//                                    line += 20;
//                                    g2.setFont(new Font("Cordia New", Font.PLAIN, 18));
//                                    g2.drawString("Terminal : " + macNo, SpaceFront, line);
//                                    line += 25;
//                                    g2.drawString("-----------------------------------------", SpaceFront, line);
//                                }
//                                //g2.setFont(new Font("Angsana New", Font.PLAIN, 20));
//
//                                g2.setFont(newFont);
//                                g2.setFont(new Font("EucrosiaUPC", Font.PLAIN, 19));
//                                String product = DataFullR(productName, PaperMaxLength);
////
////                                g2.drawString(product, SpaceFront, line);
////                                g2.drawString("" + qty, SpaceFront + 155, line);
//                                line += 25;
//                                //g2.setFont(new Font("Angsana New", Font.PLAIN, 20));
//
//                                String R_Index = rs.getString("R_Index");
//                                line = keepTextShow(g2, R_Index, qty, line, product);
//
//                                //********* พิมพ์ข้อความพิเศษ *************
//                                for (int x = 0; x < listOpt.size(); x++) {
//                                    String[] OPT = (String[]) listOpt.get(x);
//                                    for (String OPT1 : OPT) {
//                                        if (OPT1 != null) {
//                                            if (!OPT1.trim().equals("")) {
//                                                line += 20;
//                                                g2.drawString("*** " + OPT1, SpaceFront + 5, line);
//                                            }
//                                        }
//                                    }
//                                }
//                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
//
//                                //add kictran data
//                                String R_Que = SeekKicItemNo();
//                                int TempQue = Integer.parseInt(R_Que);
//                                String R_VOID = rs.getString("R_Void");
//                                if (R_VOID == null) {
//                                    R_VOID = "";
//                                }
//                                try {
//                                    if (R_VOID.equals("V")) {
//                                        String SQLQuery2 = "update kictran "
//                                                + "set pvoid = 'V' "
//                                                + "where pindex ='" + rs.getString("R_Index") + "' "
//                                                + "and ptable='" + rs.getString("R_Table") + "' "
//                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
//                                                + "and pkic='" + rs.getString("R_Kic") + "' "
//                                                + "and pflage='N'";
//                                        Statement stmt2 = mysql.getConnection().createStatement();
//                                        stmt2.executeUpdate(SQLQuery2);
//                                        stmt2.close();
//                                    } else {
//                                        String sqlK = "insert into kictran "
//                                                + "(pitemno,pdate,pcode,pqty,pindex,"
//                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
//                                                + "values (" + TempQue + ",curdate(),"
//                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
//                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
//                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
//                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
//                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
//                                        Statement stmt2 = mysql.getConnection().createStatement();
//                                        stmt2.executeUpdate(sqlK);
//                                        stmt2.close();
//                                    }
//
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//                                //END TEMP UPDATE
//                            }
//
//                            //Print header
//                            line += 20;
//                            g2.setFont(new Font("Cordia New", Font.PLAIN, 20));
//                            g2.drawString("-----------------------------------------", SpaceFront, line);
//                            line += 20;
//
////                            g2.setFont(new Font("Cordia New", Font.PLAIN, 18));
////                            g2.drawString("" + simp.format(new Date()), SpaceFront, line);
////                            line += 20;
////                            g2.setFont(new Font("Cordia New", Font.PLAIN, 18));
////                            g2.drawString("Mac " + macNo, SpaceFront, line);
//                            //Print Table to Bottom
//                            if (!tBean.getMemName().equals("")) {
//                                line += 20;
//                                g2.setFont(new Font("Cordia New", Font.BOLD, 18));
//                                g2.drawString("Name CC: " + tBean.getMemName() + " / " + tableHead, SpaceFront, line);
//                            } else {
//                                line += 35;
//                                g2.setFont(new Font("Cordia New", Font.BOLD, 30));
//                                g2.drawString("           " + tableHead, SpaceFront, line);
//
//                            }
//                            //g2.drawString(tableHead + " C " + custCount, SpaceFront, line); 
////                            g2.drawString(tableHead, SpaceFront, line);
//
//                            rs.close();
//                            stmt.close();
//                        } catch (SQLException e) {
//                            MSG.ERR(e.getMessage());
//                            
//                        } finally {
//                            mysql.close();
//                        }
//
//                        return PAGE_EXISTS;
//                    } else {
//                        return NO_SUCH_PAGE;
//                    }
//                }
//
//                private int keepTextShow(Graphics2D g2, String R_Index, int qty, int line, String product) {
//                    MySQLConnect mysql = new MySQLConnect();
//                    mysql.open();
//                    try {
//                        String sql = "select R_Index, R_LinkIndex "
//                                + "from balance "
//                                + "where R_Index='" + R_Index + "'";
//                        Statement stmt = mysql.getConnection().createStatement();
//                        ResultSet rs = stmt.executeQuery(sql);
//                        if (rs.next()) {
//                            String R_LinkIndex = rs.getString("R_LinkIndex");
//                            if (R_LinkIndex.equals(R_Index)) {
//                                g2.drawString("" + qty, SpaceFront, line);
//                                g2.drawString(product, SpaceFront + 20, line);
//                            } else if (!R_LinkIndex.equals("") && !R_LinkIndex.equals(R_Index)) {
//                                g2.drawString("-", SpaceFront, line);
//                                g2.drawString(product, SpaceFront + 20, line);
//                            } else {
//                                g2.drawString("" + qty, SpaceFront, line);
//                                g2.drawString(product, SpaceFront + 20, line);
//                            }
//                        }
//                        rs.close();
//                    } catch (SQLException e) {
//                        MSG.ERR(e.getMessage());
//                        
//                    } finally {
//                        mysql.close();
//                    }
//
//                    return line;
//                }
//            }, pf);
//            try {
//                pj.print();
//            } catch (PrinterException e) {
//                //MSG.ERR(e.getMessage());
//                //
//                System.err.println(e.getMessage());
//            }
//        } catch (PrinterException ex) {
//            MSG.ERR(ex.getMessage());
//            
//        }
//    }
    public void KIC_FORM_4(String printerName, final String tableNo) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 4 **** 
                //จะปริ้นรวมเป็นแผ่นเดียว ไม่มีการตัดรายการ แต่จะแยกด้วย __________ //
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            
                //จำนวน  2 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำตะใคร้ใบเตย      1            
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + "group by R_PluCode order by R_Index";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            int line = 0;
                            String macNo = "";
                            boolean printTable = false;
                            String TUser = "";
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    Statement stmt1 = mysql.getConnection().createStatement();
                                    ResultSet rsOpt = stmt.executeQuery(sqlOpt);
                                    while (rsOpt.next()) {
                                        String Vo = rsOpt.getString("R_Void");
                                        String RVo = rsOpt.getString("r_opt9");
                                        if (Vo.equals("V")) {
                                            if (!RVo.equals("")) {
                                                RVo = "ยกเลิก " + RVo;
                                            } else {
                                                RVo = RVo;
                                            }

                                        }

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            RVo
                                        };

                                        listOpt.add(OPT);

                                    }
                                    rsOpt.close();
                                    stmt1.close();
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                if (!printTable) {
                                    String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                    g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                    line += 25;

                                    printTable = true;
                                }
                                //print ETD
                                printG(g2, ETD, line);

                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 15));
                                g2.drawString(productName, SpaceFront, line);
                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("*** " + OPT1, SpaceFront, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                                line += 20;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 15));
                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
                                line += 20;
                                g2.drawString("-----------------------------------------", SpaceFront, line);

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        stmt2.executeUpdate(SQLQuery2);
                                        stmt2.close();
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        Statement stmt2 = mysql.getConnection().createStatement();
                                        stmt2.executeUpdate(sqlK);
                                        stmt2.close();
                                    }
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }
                            }

                            line += 20;
                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 12));
                            g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                            rs.close();
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }

                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                MSG.ERR(e.getMessage());
                
            }
        } catch (PrinterException ex) {
//            MSG.ERR(ex.getMessage());
//            
        }
    }

    public void KIC_FORM_5(String printerName, final String tableNo) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 5 **** 
                //จะปริ้นรวมเป็นแผ่นเดียว//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //--- น้ำลำใย         3
                //--- น้ำตะไคร้ใบเตย    1
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        MySQLConnect mysql = new MySQLConnect();
                        mysql.open();
                        try {
                            String sql = "select TUser,R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + "group by R_PluCode order by R_Index";
                            Statement stmt = mysql.getConnection().createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            int line = 0;
                            String macNo = "";
                            String TUser = "";
                            boolean printHeader = false;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    Statement stmt1 = mysql.getConnection().createStatement();
                                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                                    while (rsOpt.next()) {
                                        String Vo = rsOpt.getString("R_Void");
                                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                                        if (Vo.equals("V")) {
                                            if (!RVo.equals("")) {
                                                RVo = "ยกเลิก " + RVo;
                                            }
                                        }
                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            RVo
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                    stmt1.close();
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                if (!printHeader) {
                                    line += 25;
                                    g2.setFont(new Font("Thahoma", Font.PLAIN, 30));
                                    String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                    g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                    line += 25;

                                    //print ETD
                                    printG(g2, ETD, line);
                                    printHeader = true;
                                }
                                line += 25;
                                g2.setFont(new Font("Thahoma", Font.PLAIN, 20));
                                String product = DataFullR(productName, PaperMaxLength);

                                g2.drawString("---" + product, SpaceFront, line);
                                g2.drawString("" + qty, SpaceFront + 155, line);

                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("*** " + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        Statement stmt1 = mysql.getConnection().createStatement();
                                        stmt1.executeUpdate(SQLQuery2);
                                        stmt1.close();
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        Statement stmt1 = mysql.getConnection().createStatement();
                                        stmt1.executeUpdate(sqlK);
                                        stmt1.close();
                                    }
                                } catch (SQLException e) {
                                    MSG.ERR(e.getMessage());
                                    
                                }
                            }

                            //Print header
                            line += 20;
                            g2.setFont(new Font("Thahoma", Font.PLAIN, 20));
                            g2.drawString("-----------------------------------------", SpaceFront, line);
                            line += 20;

                            g2.setFont(new Font("Thahoma", Font.PLAIN, 20));
                            g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                            rs.close();
                            stmt.close();
                        } catch (SQLException e) {
                            MSG.ERR(e.getMessage());
                            
                        } finally {
                            mysql.close();
                        }

                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                MSG.ERR(e.getMessage());
                
            }
        } catch (PrinterException ex) {
//            MSG.ERR(ex.getMessage());
//            
        }
    }

    public void KIC_FORM_6(String printerName, final String tableNo, final String R_Index, String R_Plucode, double QTY, double Total) {
        String t = "";
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
        } catch (Exception e) {
            
            MSG.ERR(e.toString());
        }
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
//            String sql = "select sum(b.R_Quan) quantity,sum(b.R_Quan)*b.R_Price total,TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, R_Quan,"
//                    + "R_Price, b.Macno,R_Date, R_Time,"
//                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                    + "from tablefile t,balance b "
//                    + "where t.tcode=b.r_table "
//                    + "and r_table='" + tableNo + "' "
//                    + "and R_PrintOK='Y' "
//                    + "and R_KicPrint<>'P' "
//                    + "and R_Plucode='" + R_Plucode + "' "
//                    + "and R_Kic<>'' "
//                    + "group by r_plucode,r_opt1"
//                    + " order by R_Index";
            String sql = "select TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Void<>'V' "
                    + "and R_Index='" + R_Index + "' "
                    + "and R_Kic<>'' "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String TUser = "";
            int line = 0;
            while (rs.next()) {
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                String ETD = rs.getString("R_ETD");
                String macNo = rs.getString("macno");
                String custCount = rs.getString("TCustomer");
//                int qty = rs.getInt("quantity");
                int qty = rs.getInt("R_Quan");
//                TUser = getEmpName(rs.getString("TUser"));
                TUser = getEmpBalance(rs.getString("R_PluCode"),rs.getString("R_Index"),rs.getString("TCode"));
                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "' and R_Index='" + R_Index + "'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "**ยกเลิก " + RVo;
                            } else {
                                RVo = RVo;
                            }

                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt1.close();
                } catch (SQLException e) {
//                        MSG.ERR(e.getMessage());
                    
                }

                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
//                                line += 25;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 30));
//                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
//                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
//                                line += 25;
                String tableHead = "โต๊ะ " + rs.getString("TCode");
                t += "colspan=3 align=left><font face=Angsana New size=5>" + (tableHead) + " C " + custCount + TAB + "T:" + tableHead + "_";

                //print ETD
                if (ETD.equals("E")) {
                    t += "colspan=3 align=center><font face=Angsana New size=5>" + "นั่งในร้าน" + "_";
                }
                if (ETD.equals("T")) {
                    t += "colspan=3 align=center><font face=Angsana New size=5>" + "ห่อกลับ" + "_";
                }
                if (ETD.equals("D")) {
                    t += "colspan=3 align=center><font face=Angsana New size=5>" + "ส่ง" + "_";
                }
                t += ("colspan=3 align=centere><font face=Angsana New size=5>" + "-----------------------------------------" + "_");

                t += "colspan=3 align=left><font face=Angsana New size=4>" + productName + "_";

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                t += "colspan=4 align=left><font face=Angsana New size=4>" + OPT1 + "_";
                            }
                        }
                    }
                }
                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                t += "colspan=3 align=left><font face=Angsana New size=4>" + "จำนวน" + Space + df.format(QTY) + Space + "ราคา" + Space + df.format(Total) + "_";
//                t += "colspan=3 align=left><font face=Angsana New size=4>" + "จำนวน" + TAB + qty + Space + "ราคา" + TAB + rs.getDouble("R_Price") + "_";
                t += ("colspan=3 align=centere><font face=Angsana New size=5>" + "-----------------------------------------" + "_");
                t += ("colspan=3 align=left><font face=Angsana New size=3>" + simp.format(new Date()) + Space + "_Mac: " + macNo + "/" + TUser + Space + printerName + "_");

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(SQLQuery2);
                        stmt1.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(sqlK);
                        stmt1.close();
                    }
                } catch (SQLException e) {
//                        MSG.ERR(e.getMessage());
                    
                }
            }
            try {
                String sqlUPdate = "update balance "
                        + "set R_KicPrint='P' "
                        + "where r_table='" + tableNo + "' "
                        + "and r_index='" + R_Index + "' "
                        + "and r_plucode='" + R_Plucode + "'";
                Statement stmtUpdate = mysql.getConnection().createStatement();
                stmtUpdate.executeUpdate(sqlUPdate);
                stmtUpdate.close();
            } catch (Exception e) {
                
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
//                MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
        System.out.println("Printting Job Kitchen" + printerName);
        t = changeFontSize(t);
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data1 : strs) {
            Value.printerDriverKitChenName = printerName;
            pd.addTextIFont(data1);
        }
        pd.printHTMLKitChen();
//        
        String t1 = "";
        PrinterJob pj1 = PrinterJob.getPrinterJob();
        PrintService[] ps1 = PrinterJob.lookupPrintServices();
        int prnIndex1 = 0;
        try {
            for (int i = 0; i < ps1.length; i++) {
                String PrinterName = ps1[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex1 = i;
                    break;
                }
            }
        } catch (Exception e) {
            
        }
//        MySQLConnect mysql = new MySQLConnect();
//        mysql.open();
//        try {
////            String sql = "select sum(b.R_Quan) quantity,sum(b.R_Quan)*b.R_Price total,TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, R_Quan,"
////                    + "R_Price, b.Macno,R_Date, R_Time,"
////                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
////                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
////                    + "from tablefile t,balance b "
////                    + "where t.tcode=b.r_table "
////                    + "and r_table='" + tableNo + "' "
////                    + "and R_PrintOK='Y' "
////                    + "and R_KicPrint<>'P' "
////                    + "and R_Plucode='" + R_Plucode + "' "
////                    + "and R_Kic<>'' "
////                    + "group by r_plucode,r_opt1"
////                    + " order by R_Index";
//            String sql = "select TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, R_Quan,"
//                    + "R_Price, b.Macno,R_Date, R_Time,"
//                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                    + "from tablefile t,balance b "
//                    + "where t.tcode=b.r_table "
//                    + "and r_table='" + tableNo + "' "
//                    + "and R_PrintOK='Y' "
//                    + "and R_KicPrint<>'P' "
//                    + "and R_Void='V' "
//                    + "and R_Index='" + R_Index + "' "
//                    + "and R_Kic<>'' "
//                    + "order by R_Index";
//            Statement stmt = mysql.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            String TUser = "";
//            int line = 0;
//            while (rs.next()) {
//                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
//                String ETD = rs.getString("R_ETD");
//                String macNo = rs.getString("macno");
//                String custCount = rs.getString("TCustomer");
////                int qty = rs.getInt("quantity");
//                int qty = rs.getInt("R_Quan");
//                TUser = getEmpName(rs.getString("TUser"));
//
//                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
//                ArrayList<String[]> listOpt = new ArrayList<>();
//                try {
//                    String sqlOpt = "select * from balance "
//                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "' and R_Index='" + R_Index + "'";
//                    Statement stmt1 = mysql.getConnection().createStatement();
//                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
//                    while (rsOpt.next()) {
//                        String Vo = rsOpt.getString("R_Void");
//                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
//                        if (Vo.equals("V")) {
//                            if (!RVo.equals("")) {
//                                RVo = "ยกเลิก " + RVo;
//                            } else {
//                                RVo = RVo;
//                            }
//
//                        }
//                        String[] OPT = new String[]{
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
//                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
//                            RVo
//                        };
//
//                        listOpt.add(OPT);
//                    }
//
//                    rsOpt.close();
//                    stmt1.close();
//                } catch (SQLException e) {
////                        MSG.ERR(e.getMessage());
//                    
//                }
//
//                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
////                                line += 25;
////                                g2.setFont(new Font("Thahoma", Font.PLAIN, 30));
////                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
////                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
////                                line += 25;
//                String tableHead = "โต๊ะ " + rs.getString("TCode");
//                t1 += "colspan=3 align=left><font face=Angsana New size=5>" + (tableHead) + " C " + custCount + TAB + "T:" + tableHead + "_";
//
//                //print ETD
//                if (ETD.equals("E")) {
//                    t1 += "colspan=3 align=center><font face=Angsana New size=5>" + "นั่งในร้าน" + "_";
//                }
//                if (ETD.equals("T")) {
//                    t1 += "colspan=3 align=center><font face=Angsana New size=5>" + "ห่อกลับ" + "_";
//                }
//                if (ETD.equals("D")) {
//                    t1 += "colspan=3 align=center><font face=Angsana New size=5>" + "ส่ง" + "_";
//                }
//                t1 += ("colspan=3 align=centere><font face=Angsana New size=5>" + "-----------------------------------------" + "_");
//
//                t1 += "colspan=3 align=left><font face=Angsana New size=4>" + productName + "_";
//
//                //********* พิมพ์ข้อความพิเศษ *************
//                for (int x = 0; x < listOpt.size(); x++) {
//                    String[] OPT = (String[]) listOpt.get(x);
//                    for (String OPT1 : OPT) {
//                        if (OPT1 != null) {
//                            if (!OPT1.trim().equals("")) {
//                                t1 += "colspan=4 align=left><font face=Angsana New size=4>" + OPT1 + "_";
//                            }
//                        }
//                    }
//                }
//                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
//                t1 += "colspan=3 align=left><font face=Angsana New size=4>" + "จำนวน" + Space + df.format(QTY) + Space + "ราคา" + Space + df.format(Total) + "_";
////                t += "colspan=3 align=left><font face=Angsana New size=4>" + "จำนวน" + TAB + qty + Space + "ราคา" + TAB + rs.getDouble("R_Price") + "_";
//                t1 += ("colspan=3 align=centere><font face=Angsana New size=5>" + "-----------------------------------------" + "_");
//                t1 += ("colspan=3 align=left><font face=Angsana New size=3>" + simp.format(new Date()) + Space + "_Mac: " + macNo + "/" + TUser + Space + printerName + "_");
//
//                //add kictran data
//                String R_Que = SeekKicItemNo();
//                int TempQue = Integer.parseInt(R_Que);
//                String R_VOID = rs.getString("R_Void");
//                if (R_VOID == null) {
//                    R_VOID = "";
//                }
//                try {
//                    if (R_VOID.equals("V")) {
//                        String SQLQuery2 = "update kictran "
//                                + "set pvoid = 'V' "
//                                + "where pindex ='" + rs.getString("R_Index") + "' "
//                                + "and ptable='" + rs.getString("R_Table") + "' "
//                                + "and pcode='" + rs.getString("R_PluCode") + "' "
//                                + "and pkic='" + rs.getString("R_Kic") + "' "
//                                + "and pflage='N'";
//                        Statement stmt1 = mysql.getConnection().createStatement();
//                        stmt1.executeUpdate(SQLQuery2);
//                        stmt1.close();
//                    } else {
//                        String sqlK = "insert into kictran "
//                                + "(pitemno,pdate,pcode,pqty,pindex,"
//                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
//                                + "values (" + TempQue + ",curdate(),"
//                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
//                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
//                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
//                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
//                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
//                        Statement stmt1 = mysql.getConnection().createStatement();
//                        stmt1.executeUpdate(sqlK);
//                        stmt1.close();
//                    }
//                } catch (SQLException e) {
////                        MSG.ERR(e.getMessage());
//                    
//                }
//            }
//
//            rs.close();
//            stmt.close();
//        } catch (SQLException e) {
////                MSG.ERR(e.getMessage());
//            
//        } finally {
//            mysql.close();
//        }
//        t1 = changeFontSize(t1);
//        PrintDriver pd1 = new PrintDriver();
//        String[] strs1 = t1.split("_");
//        for (String data1 : strs1) {
//            Value.printerDriverKitChenName = printerName;
//            pd1.addTextIFont(data1);
//        }
//        pd1.printHTMLKitChen();
    }
//    public void KIC_FORM_6(String printerName, final String tableNo, final String R_Index) {
//        final int SpaceFront = 25;
//        final int PaperMaxLength = 28;
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PrintService[] ps = PrinterJob.lookupPrintServices();
//        int prnIndex = 0;
//        try {
//            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
//                if (PrinterName.equalsIgnoreCase(printerName)) {
//                    prnIndex = i;
//                    break;
//                }
//            }
//            pj.setPrintService(ps[prnIndex]);
//
//            PageFormat pf = new PageFormat();
//            Paper pp = new Paper();
//            pp.setSize(500, 1000);
//            pp.setImageableArea(0, 0, 594, 846);
//            pf.setPaper(pp);
//            pj.setPrintable(new Printable() {
//
//                //**** FORM 6 **** 
//                //จะ CUT ปริ้นทีละสินค้าตาม Order รายการในบิล และแสดงราคาสินค้าด้วย//
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำลำใย            
//                //จำนวน  1 ราคา 45.00
//                //__________________
//                //28/04/2014 14:15 001/
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำลำใย            
//                //จำนวน  1 ราคา 45.00
//                //__________________
//                //28/04/2014 14:15 001/
//                //โต๊ะ 1           C0
//                //***** Eat In *****
//                //น้ำตะใคร้ใบเตย      1 
//                //จำนวน  1 ราคา 45.00
//                //__________________
//                //28/04/2014 14:15 001/
//                @Override
//                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
//                    Graphics2D g2 = (Graphics2D) g;
//                    if (index == 0) {
//                        String t =  "";
//                        MySQLConnect mysql = new MySQLConnect();
//                        mysql.open();
//                        try {
//                            String sql = "select TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, R_Quan,"
//                                    + "R_Price, b.Macno,R_Date, R_Time,"
//                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
//                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
//                                    + "from tablefile t,balance b "
//                                    + "where t.tcode=b.r_table "
//                                    + "and r_table='" + tableNo + "' "
//                                    + "and R_PrintOK='Y' "
//                                    + "and R_KicPrint<>'P' "
//                                    + "and R_Index='" + R_Index + "' "
//                                    + "and R_Kic<>'' "
//                                    + "order by R_Index";
//                            Statement stmt = mysql.getConnection().createStatement();
//                            ResultSet rs = stmt.executeQuery(sql);
//                            String TUser = "";
//                            int line = 0;
//                            while (rs.next()) {
//                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
//                                String ETD = rs.getString("R_ETD");
//                                String macNo = rs.getString("macno");
//                                String custCount = rs.getString("TCustomer");
//                                int qty = rs.getInt("R_Quan");
//                                TUser = getEmpName(rs.getString("TUser"));
//
//                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
//                                ArrayList<String[]> listOpt = new ArrayList<>();
//                                try {
//                                    String sqlOpt = "select * from balance "
//                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
//                                    Statement stmt1 = mysql.getConnection().createStatement();
//                                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
//                                    while (rsOpt.next()) {
//                                        String Vo = rsOpt.getString("R_Void");
//                                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
//                                        if (Vo.equals("V")) {
//                                            if (!RVo.equals("")) {
//                                                RVo = "ยกเลิก " + RVo;
//                                            } else {
//                                                RVo = RVo;
//                                            }
//
//                                        }
//                                        String[] OPT = new String[]{
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
//                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
//                                            RVo
//                                        };
//
//                                        listOpt.add(OPT);
//                                    }
//
//                                    rsOpt.close();
//                                    stmt1.close();
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//
//                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
////                                line += 25;
////                                g2.setFont(new Font("Thahoma", Font.PLAIN, 30));
////                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
////                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
////                                line += 25;
//                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
//                                t += "colspan=3 align=left><font face=Angsana New size=5>" + (tableHead) + " C " + custCount + "T:" + tableHead + "_";
//                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
//                                line += 25;
//
//                                //print ETD
//                                printG(g2, ETD, line);
//
//                                line += 25;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 20));
//                                g2.drawString(productName, SpaceFront, line);
//                                //********* พิมพ์ข้อความพิเศษ *************
//                                for (int x = 0; x < listOpt.size(); x++) {
//                                    String[] OPT = (String[]) listOpt.get(x);
//                                    for (String OPT1 : OPT) {
//                                        if (OPT1 != null) {
//                                            if (!OPT1.trim().equals("")) {
//                                                line += 20;
//                                                g2.drawString("*** " + OPT1, SpaceFront + 5, line);
//                                            }
//                                        }
//                                    }
//                                }
//                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
//                                line += 20;
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 20));
//                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
//                                line += 20;
//                                g2.drawString("-----------------------------------------", SpaceFront, line);
//                                line += 20;
//
//                                g2.setFont(new Font("Thahoma", Font.PLAIN, 18));
//                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);
//
//                                line += 25;
//
//                                //add kictran data
//                                String R_Que = SeekKicItemNo();
//                                int TempQue = Integer.parseInt(R_Que);
//                                String R_VOID = rs.getString("R_Void");
//                                if (R_VOID == null) {
//                                    R_VOID = "";
//                                }
//                                try {
//                                    if (R_VOID.equals("V")) {
//                                        String SQLQuery2 = "update kictran "
//                                                + "set pvoid = 'V' "
//                                                + "where pindex ='" + rs.getString("R_Index") + "' "
//                                                + "and ptable='" + rs.getString("R_Table") + "' "
//                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
//                                                + "and pkic='" + rs.getString("R_Kic") + "' "
//                                                + "and pflage='N'";
//                                        Statement stmt1 = mysql.getConnection().createStatement();
//                                        stmt1.executeUpdate(SQLQuery2);
//                                        stmt1.close();
//                                    } else {
//                                        String sqlK = "insert into kictran "
//                                                + "(pitemno,pdate,pcode,pqty,pindex,"
//                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
//                                                + "values (" + TempQue + ",curdate(),"
//                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
//                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
//                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
//                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
//                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
//                                        Statement stmt1 = mysql.getConnection().createStatement();
//                                        stmt1.executeUpdate(sqlK);
//                                        stmt1.close();
//                                    }
//                                } catch (SQLException e) {
//                                    MSG.ERR(e.getMessage());
//                                    
//                                }
//                            }
//                          
//                            rs.close();
//                            stmt.close();
//                        } catch (SQLException e) {
//                            MSG.ERR(e.getMessage());
//                            
//                        } finally {
//                            mysql.close();
//                        }
//                        
//                        return PAGE_EXISTS;
//                    } else {
//                        return NO_SUCH_PAGE;
//                    }
//                }
//            }, pf);
////            try {
////                pj.print();
////            } catch (PrinterException e) {
////                MSG.ERR(e.getMessage());
////                
////            }
//        } catch (PrinterException ex) {
////            MSG.ERR(ex.getMessage());
////            
//        }
//
//    }

    public void KIC_FORM_7(String printerName, final String tableNo) {
        MySQLConnect mysql = new MySQLConnect();
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
//                    break;
                }
            }
        } catch (Exception e) {
        }
        //FORM 7 **** 
        //จะ CUT ปริ้นทีละสินค้าตาม Order รายการในบิล และแสดงราคาสินค้าด้วย//
        //โต๊ะ 1           C0
        //**** ทานในร้าน ****
        //..................
        //น้ำลำใย            
        //จำนวน  1 ราคา 45.00
        //__________________
        //T1 28/04/2014 14:15 001/พนักงาน
        //หากสั่งมากกว่า 1 ที่
        //โต๊ะ 1           C0
        //**** ทานในร้าน ****
        //..................
        //น้ำลำใย            
        //จำนวน  2 ราคา 90.00
        //__________________
        //T1 28/04/2014 14:15 001/พนักงาน
        mysql.open();
        try {
            String sql = "select TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, sum(R_Quan) R_Quan,"
                    + "sum(R_Total) R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "and R_Kic='" + printerName.replace("kic", "") + "' "
                    + "group by b.R_PluCode,r_etd "
                    + "order by R_ETD,R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String TUser = "";
            int line = 0;
            while (rs.next()) {
                if (rs.getString("R_Kic").equals(printerName.replace("kic", ""))) {
                    String t = "";
                    String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                    String ETD = rs.getString("R_ETD");
                    String macNo = rs.getString("macno");
                    String custCount = rs.getString("TCustomer");
                    int qty = rs.getInt("R_Quan");
                    TUser = getEmpName(rs.getString("TUser"));
                    String r_index = rs.getString("R_Index");

                    //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                    ArrayList<String[]> listOpt = new ArrayList<>();
                    try {
                        String sqlOpt = "select * from balance "
                                + "where r_table='" + tableNo
                                + "' and r_pluCode='"
                                + rs.getString("R_PluCode") + "' and r_index='" + r_index + "' ";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                        while (rsOpt.next()) {
                            String Vo = rsOpt.getString("R_Void");
                            String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                            if (Vo.equals("V")) {
                                if (!RVo.equals("")) {
                                    RVo = "ยกเลิก " + RVo;
                                } else {
                                    RVo = RVo;
                                }

                            }
                            String[] OPT = new String[]{
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                RVo
                            };

                            listOpt.add(OPT);
                        }

                        rsOpt.close();
                        stmt1.close();
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                        
                    }

                    //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                    t += "align=left><font face=Angsana New size=3>" + printerName + "_";
                    t += "align=left><font face=Angsana New size=5>" + "โต๊ะ " + ThaiUtil.ASCII2Unicode(rs.getString("TCode")) + "</td><td align=right><font face=Angsana New size=5>" + " CC. " + "</td><td align=left><font face=Angsana New size=5>" + custCount + "_";
                    t += "colspan=3 align=center>_";
                    if (ETD.equals("E")) {
                        ETD = "*** ทานในร้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ห่อกลับ ***";
                    } else if (ETD.equals("D")) {
                        ETD = "*** ส่งถึงบ้าน ***";
                    } else if (ETD.equals("P")) {
                        ETD = "*** ปิ่นโต ***";
                    } else if (ETD.equals("W")) {
                        ETD = "*** ขายส่ง ***";
                    }

                    t += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                    t += "colspan=3 align=center>" + "............................................." + "_";
                    String product = productName;
                    String productSubF = "";
                    String productSubR = "";
                    if (product.length() > 16) {
                        productSubF = product.substring(0, 16);
                        productSubR = product.substring(16);
                        product = productSubF + "\n" + productSubR;
                    } else if (product.length() > 32) {
                        productSubF = product.substring(0, 16);
                        productSubR = product.substring(16, 32);
                        productSubR = product.substring(32);
                    }
                    t += "colspan=3 align=left><font face=Angsana New size=3>" + (product) + "_";

                    t += "colspan=3 align=left><font face=Angsana New size=3>" + "จำนวน" + Space + inf.format(qty) + TAB + TAB + "ราคา" + Space + inf.format(rs.getDouble("R_Price")) + "_";

                    //********* พิมพ์ข้อความพิเศษ *************
                    for (int x = 0; x < listOpt.size(); x++) {
                        String[] OPT = (String[]) listOpt.get(x);
                        for (String OPT1 : OPT) {
                            if (OPT1 != null) {
                                if (!OPT1.trim().equals("")) {
                                    t += "colspan=3 align=left><font face=Angsana New size=3>" + TAB + "*** " + OPT1 + "_";
                                }
                            }
                        }
                    }
                    //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + "-----------------------------------------" + "_";
                    t += "colspan=3 align=left><font face=Angsana New size=2>" + "โต๊ะ " + Space + ThaiUtil.ASCII2Unicode(rs.getString("TCode")) + TAB + simp.format(new Date()) + "_";
                    t += "colspan=3 align=left><font face=Angsana New size=2>" + "Mac " + macNo + "/" + TAB + TUser + "_";

                    PrintDriver pd = new PrintDriver();
                    String[] strs = t.split("_");
                    for (String data1 : strs) {
                        Value.printerDriverKitChenName = printerName;
                        pd.addTextIFont(data1);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            
                        }
                    }

                    pd.printHTMLKitChen();
                    //add kictran data
                    String R_Que = SeekKicItemNo();
                    int TempQue = Integer.parseInt(R_Que);
                    String R_VOID = rs.getString("R_Void");
                    if (R_VOID == null) {
                        R_VOID = "";
                    }
                    try {
                        if (R_VOID.equals("V")) {
                            String SQLQuery2 = "update kictran "
                                    + "set pvoid = 'V' "
                                    + "where pindex ='" + rs.getString("R_Index") + "' "
                                    + "and ptable='" + rs.getString("R_Table") + "' "
                                    + "and pcode='" + rs.getString("R_PluCode") + "' "
                                    + "and pkic='" + rs.getString("R_Kic") + "' "
                                    + "and pflage='N'";
                            Statement stmt1 = mysql.getConnection().createStatement();
                            stmt1.executeUpdate(SQLQuery2);
                            stmt1.close();
                        } else {
                            String sqlK = "insert into kictran "
                                    + "(pitemno,pdate,pcode,pqty,pindex,"
                                    + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                    + "values (" + TempQue + ",curdate(),"
                                    + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                    + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                    + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                    + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                    + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                            Statement stmt1 = mysql.getConnection().createStatement();
                            stmt1.executeUpdate(sqlK);
                            stmt1.close();
                        }
                    } catch (SQLException e) {
                        MSG.ERR(e.getMessage());
                        
                    }
                }
            }
            String sqlUpdateBalance = "update balance set R_KicPrint='P' "
                    //                    + "where r_table='" + tableNo + "';";
                    + "where r_table='" + tableNo + "' and r_kic='" + printerName.replace("kic", "") + "';";
            mysql.getConnection().createStatement().executeUpdate(sqlUpdateBalance);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

    }
    int line22 = 25;

    int lineLineLin = 0;

//    public void KIC_FORM_Move(final String table, final String tableto, final String kicName) throws Exception {
//        final int SpaceFront = 25;
//        final int PaperMaxLength = 28;
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PrintService[] ps = PrinterJob.lookupPrintServices();
//        int prnIndex = 0;
//        lineLineLin = -75;
//        try {
//            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
//                if (PrinterName.equalsIgnoreCase(kicName)) {
//                    prnIndex = i;
//                    break;
//                }
//            }
//            pj.setPrintService(ps[prnIndex]);
//            PageFormat pf = new PageFormat();
//            Paper pp = new Paper();
//            pp.setSize(100, 300);
//            pp.setImageableArea(0, 0, 594, 300);
//            pf.setPaper(pp);
//            pj.setPrintable(new Printable() {
//
//                @Override
//                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
//                    Graphics2D g2 = (Graphics2D) g;
//                    if (index == 0) {
//                        g2.setFont(new Font("Browallia New", Font.BOLD, 46));
//                        g2.drawString("ย้าย:" + table, SpaceFront, lineLineLin);
//                        lineLineLin += 35;
//                        g2.setFont(new Font("Browallia New", Font.PLAIN, 30));
//                        g2.drawString("ไป>>>", SpaceFront, lineLineLin);
//                        lineLineLin += 50;
//                        g2.setFont(new Font("Browallia New", Font.BOLD, 46));
//                        g2.drawString("โต๊ะ : " + tableto, SpaceFront, lineLineLin);
//                        lineLineLin += 25;
//                        g2.setFont(new Font("Browallia New", Font.PLAIN, 18));
//                        g2.drawString("" + simp.format(new Date()) + "(" + kicName + ")", SpaceFront, lineLineLin);
//                        return PAGE_EXISTS;
//                    } else {
//                        return NO_SUCH_PAGE;
//                    }
//                }
//            }, pf);
//            try {
//                pj.print();
//            } catch (PrinterException e) {
//                System.out.println(e.getMessage());
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public void KIC_FORM_Move(final String table, final String tableto, final String kicName) throws Exception {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        lineLineLin = -75;

        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(kicName)) {
                    prnIndex = i;
                    break;
                }
            }

            String t = "";
            t += "colspan=3 align=left><font face=Angsana New size=5>" + (table + Space + "ย้าย:") + "_";
            t += "colspan=3 align=left><font face=Angsana New size=5>" + ("ไป>>>" + "_");
            t += "colspan=3 align=left><font face=Angsana New size=5>" + ("โต๊ะ : " + tableto + "_");
            t += "colspan=3 align=left><font face=Angsana New size=5>" + ("" + simp.format(new Date()) + "(" + kicName + ")" + "_");
            PrintDriver pd = new PrintDriver();
            String[] strs = t.split("_");
            for (String data1 : strs) {
                Value.printerDriverKitChenName = "kic" + kicName;
                pd.addTextIFont(data1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }

            pd.printHTMLKitChen();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void KIC_FORM_SaveOrder(final String sql1, final String printerName, final String tableNo, final int R_Kic) {

        String t = "";
        MySQLConnect mysql = new MySQLConnect();
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PrintService[] ps = PrinterJob.lookupPrintServices();
//        int prnIndex = 0;
//        try {
//            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
//                if (PrinterName.equalsIgnoreCase(printerName)) {
//                    prnIndex = i;
////                    break;
//                }
//            }
//        } catch (Exception e) {
//        }
        mysql.open();
        String PLUCode;
        try {
            TableFileControl tCon = new TableFileControl();
            TableFileBean tBean = tCon.getData(tableNo);
            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    //                    + "and R_Kic='" + R_Kic + "' "
                    //                    + "group by R_Index, R_PluCode, R_LinkIndex, R_ETD "
                    //                    + "group by R_PluCode, R_LinkIndex, R_ETD "
                    + "group by R_PluCode, R_ETD "
                    + "order by R_ETD, R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String ETD = "";
            String custCount = "";
            String macNo = "";
            String TUser = "";
            boolean printHeader = false;
            boolean printTable = false;
            String tempHeader = "";
            String tableHead = "";
            t += "colspan=3 align=center><font face=Angsana New size=3>" + "SAVE ORDER" + "_";
            t += "colspan=2 align=left><font face=Angsana New size=5>" + ("โต๊ะ : " + ThaiUtil.ASCII2Unicode(tableNo)) + "_";
            while (rs.next()) {
                ETD = rs.getString("R_ETD");
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                if (tempHeader.equals("")) {
                    tempHeader = ETD;
                }
                if (!tempHeader.equals(ETD)) {
                    tempHeader = ETD;
                    printHeader = false;
                }
                macNo = rs.getString("macno");
                custCount = rs.getString("TCustomer");
                int qty = rs.getInt("R_Quan");
                TUser = getEmpName(rs.getString("R_Emp"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + ThaiUtil.ASCII2Unicode(tableNo) + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + rs.getString("R_Index") + "' "
                            //                            + "and r_kic='" + R_Kic + "' "
                            + "and R_KicPrint<>'P'";
                    Statement stmt1 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt1.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        PLUCode = rs.getString("R_PluCode");
//                        this.PLUCode = PLUCode;
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            }
                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);

                    }

                    rsOpt.close();
                    stmt1.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }
//                                    *********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                if (!printHeader) {
                    if (!printTable) {
//                        tableHead = "Table " + rs.getString("TCode");

                    }
                    //print ETD
                    if (ETD.equals("E")) {
                        ETD = "*** ทานในร้าน ***";
                    } else if (ETD.equals("T")) {
                        ETD = "*** ห่อกลับ ***";
                    } else if (ETD.equals("D")) {
                        ETD = "*** ส่งถึงบ้าน ***";
                    } else if (ETD.equals("P")) {
                        ETD = "*** ปิ่นโต ***";
                    } else if (ETD.equals("W")) {
                        ETD = "*** ขายส่ง ***";
                    }
//                    if (ETD.equals("E")) {
//                        ETD = "*** DINE IN ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** TAKE AWAY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** DELIVERY ***";
//                    } else if (ETD.equals("T")) {
//                        ETD = "*** PINTO ***";
//                    } else if (ETD.equals("W")) {
//                        ETD = "*** WHOLE SALE ***";
//                    }
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + "_";
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
//                    t += "colspan=3 align=center><font face=Angsana New size=3>" + "SAVE ORDER" + "_";
                    t += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                    printHeader = true;
                    printTable = true;
//                    t += "colspan=2 align=left><font face=Angsana New size=5>" + ("โต๊ะ : " + tableNo) + "_";
                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
                }

                String product = (productName);

                String R_Index = rs.getString("R_Index");

                t += keepTextShow(R_Index, qty, product);

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                t += "colspan=3 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                                    //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                try {
                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                + "and pflage='N'";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(SQLQuery2);
                        stmt2.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt2 = mysql.getConnection().createStatement();
                        stmt2.executeUpdate(sqlK);
                        stmt2.close();
                    }
                    //Print header
//                    t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                    //Print Table to Bottom
//                    if (!tBean.getMemName().equals("")) {
//                        t += "colspan=3 align=left><font face=Angsana New size=3>" + ("Name CC: " + tBean.getMemName() + " / " + tableHead) + "_";
//                    } else {
//                        t += "colspan=3 align=right><font face=Angsana New size=3>" + (tableHead) + "_";
//                        t += "colspan=3 align=left><font face=Angsana New size=3>" + ("" + simp.format(new Date())) + "_";
//                        t += "colspan=2 align=left><font face=Angsana New size=3>" + "Terminal : " + Space + macNo + "</td><td align=right><font face=Angsana New size=3>" + (" Name :" + TUser) + "_";
//
//                    }
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                //END TEMP UPDATE
            }
            t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";
            t += "colspan=3 align=left><font face=Angsana New size=2>" + "Terminal : " + Space + macNo + (" Name :" + TUser) + "_";
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data1 : strs) {
            Value.printerDriverKitChenName = printerName;
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        pd.printHTMLKitChen();

    }

    public void ItemVoidPrint(String printerName, final String tableNo, final String[] PCode, String ETD) {
        String t = "";
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
//                String PrinterName = ps[i].getName();
                String PrinterName = printerName;
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
//            pj.setPrintService(ps[prnIndex]);
        } catch (Exception e) {
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,R_Quan R_Quan,"
                    + "R_Price, b.Macno,R_Date, R_Time,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                    + "from tablefile t,balance b "
                    + "where t.tcode=b.r_table "
                    + "and r_table='" + tableNo + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "and R_PluCode='" + PCode[0] + "' "
                    + "and R_ETD='" + ETD + "' "
                    + "and R_VOID='V' "
                    + "group by R_PluCode "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                printerName = rs.getString("R_Kic");
                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                String macNo = rs.getString("macno");
                String custCount = rs.getString("TCustomer");
                double qty = rs.getDouble("R_Quan");
                String TUser = getEmpName(rs.getString("R_Emp"));
                String r_index = (rs.getString("R_Index"));

                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                ArrayList<String[]> listOpt = new ArrayList<>();
                try {
                    String sqlOpt = "select * from balance "
                            + "where r_table='" + tableNo + "' "
                            + "and r_pluCode='" + rs.getString("R_PluCode") + "' "
                            + "and r_index='" + r_index + "'"
                            + "and r_void='V' ";
                    Statement stmt2 = mysql.getConnection().createStatement();
                    ResultSet rsOpt = stmt2.executeQuery(sqlOpt);
                    while (rsOpt.next()) {
                        String Vo = rsOpt.getString("R_Void");
                        String RVo = ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"));
                        if (Vo.equals("V")) {
                            if (!RVo.equals("")) {
                                RVo = "ยกเลิก " + RVo;
                            } else {
                                RVo = RVo;
                            }

                        }
                        String[] OPT = new String[]{
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                            RVo
                        };

                        listOpt.add(OPT);
                    }

                    rsOpt.close();
                    stmt2.close();
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                    
                }

                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                t += "align=left><font face=Angsana New size=5>" + "โต๊ะ " + ThaiUtil.ASCII2Unicode(rs.getString("TCode")) + "</td><td align=right><font face=Angsana New size=5>" + " CC. " + "</td><td align=left><font face=Angsana New size=5>" + custCount + "_";
                t += "colspan=3 align=center>_";
                if (ETD.equals("E")) {
//                    ETD = "*** DINE IN ***";
                    ETD = "*** ทานในร้าน ***";
                } else if (ETD.equals("T")) {
//                    ETD = "*** TAKE AWAY ***";
                    ETD = "*** ห่อกลับ ***";
                } else if (ETD.equals("D")) {
//                    ETD = "*** DELIVERY ***";
                    ETD = "*** ส่งถึงบ้าน ***";
                } else if (ETD.equals("P")) {
//                    ETD = "*** PINTO ***";
                    ETD = "*** ปิ่นโต ***";
                } else if (ETD.equals("W")) {
//                    ETD = "*** WHOLE SALE ***";
                    ETD = "*** ขายส่ง ***";
                }

                t += "colspan=3 align=center><font face=Angsana New size=5>" + ETD + "_";
                t += "colspan=3 align=center>_";
                String product = productName;
                if (product.length() > 16) {
                    String productSubF = product.substring(0, 16);
                    String productSubR = product.substring(16);

                    product = productSubF + "\n" + productSubR;

                }
//                t += "colspan=2 align=left><font face=Angsana New size=5>" + (product) + "</td><td align=right><font face=Angsana New size=5>" + df.format(qty) + "_";
                t += "colspan=3 align=left><font face=Angsana New size=5>" + (product) + "_";

                t += "<td colspan=3 align=right><font face=Angsana New size=5>" + "- " + df.format(qty) + "_";
//                t += "colspan=3 align=right><font face=Angsana New size=3>" + "จำนวน." + Space + df.format(qty) + TAB + "-_";

                //********* พิมพ์ข้อความพิเศษ *************
                for (int x = 0; x < listOpt.size(); x++) {
                    String[] OPT = (String[]) listOpt.get(x);
                    for (String OPT1 : OPT) {
                        if (OPT1 != null) {
                            if (!OPT1.trim().equals("")) {
                                t += "colspan=5 align=left><font face=Angsana New size=3>" + Space + ("*** " + OPT1) + "_";
                            }
                        }
                    }
                }
                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                t += "colspan=3 align=center><font face=Angsana New size=3>" + ("-----------------------------------------") + "_";

                t += "colspan=3 align=left><font face=Angsana New size=2>" + (simp.format(new Date()) + Space + "Mac" + Space + macNo + "/" + TUser) + "_";

                //add kictran data
                String R_Que = SeekKicItemNo();
                int TempQue = Integer.parseInt(R_Que);
                String R_VOID = rs.getString("R_Void");
                if (R_VOID == null) {
                    R_VOID = "";
                }
                //หากมีสินค้า VOID
                try {

                    if (R_VOID.equals("V")) {
                        String SQLQuery2 = "update kictran "
                                + "set pvoid = 'V' "
                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                + "and ptable='" + rs.getString("R_Table") + "' "
                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                //                                + "and r_void='V' "
                                + "and pflage='N'";
                        Statement stmt1 = mysql.getConnection().createStatement();
                        stmt1.executeUpdate(SQLQuery2);
                        stmt1.close();
                    } else {
                        String sqlK = "insert into kictran "
                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                + "values (" + TempQue + ",curdate(),"
                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                        Statement stmt3 = mysql.getConnection().createStatement();
                        stmt3.executeUpdate(sqlK);
                        stmt3.close();
                    }
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
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data1 : strs) {
            Value.printerDriverKitChenName = "kic" + printerName;
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        pd.printHTMLKitChen();

    }

    private static void printG(Graphics2D g2, String ETD, int line) {
        final int SpaceFront = 25;
        if (ETD.equals("E")) {
            g2.drawString("** DINE IN **", SpaceFront, line);
        } else if (ETD.equals("T")) {
            g2.drawString("** TAKE AWAY **", SpaceFront, line);
        } else if (ETD.equals("D")) {
            g2.drawString("** DELIVERY **", SpaceFront, line);
        } else if (ETD.equals("P")) {
            g2.drawString("*** PINTO ***", SpaceFront, line);
        } else if (ETD.equals("W")) {
            g2.drawString("*** WHOLE SALE ***", SpaceFront, line);
        }
    }

    private String SeekKicItemNo() {
        DecimalFormat QtyIntFmt = new DecimalFormat("###########0");
        int KicItemNo = 0;

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "Select * from branch";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                KicItemNo = rec.getInt("kicitemno") + 1;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "update branch set kicitemno =" + KicItemNo;
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        mysql.close();

        return QtyIntFmt.format(KicItemNo);
    }

    public void printTest(String prnName, final String data) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equals(prnName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                @Override
                public int print(Graphics g, PageFormat pf, int index) {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        int line = 0;
                        int space = 15;
                        try {
                            //Thahoma
                            //angsau
                            g2.setFont(new Font("Thahoma", Font.PLAIN, 16));
                            for (int i = 0; i < 5; i++) {
                                line += 25;
                                g2.drawString(data, space, line);
                            }
                        } catch (Exception e) {
                            MSG.ERR(e.getMessage());
                        }

                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Font FontTTF() {
        try {
            //Returned font is of pt size 1
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("D:/NetbeansProjects/fonts/07-TH-Sarabun-PSK/angsau.ttf"));
            return font.deriveFont(Font.BOLD);
        } catch (FontFormatException e) {
            MSG.ERR(e.getMessage());
            return null;
        } catch (IOException e) {
            MSG.ERR(e.getMessage());
            return null;
        }
    }

    public String SubStringText(String str, int prefix) {
        int i = 0;
        i = str.length();
        if (i > prefix) {
            str = str.substring(0, prefix);
            str.replace(" ", Space);
        } else {
            int strLength = str.length();
            strLength = prefix - strLength;
//            for (int j = strLength; j >= 0; j--) {
//                str += Space;
//            }
        }
        return str;
    }

    public String changeFontSize(String text) {
        String t = text;
        String sizeHead = ConfigFile.getProperties("kicFontHeadder");
        String sizeBody = ConfigFile.getProperties("kicFontDetail");
        t = t.replace("size=5", sizeHead);
        t = t.replace("size=4", sizeBody);
        return t;
    }

    public String getEmpName(String Code) {
        String empName = "";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select Name from employ where code='" + Code + "';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                empName = ThaiUtil.ASCII2Unicode(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return empName;
    }

    public String getEmpBalance(String Code, String r_index, String table) {
        String empName = "";
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select r_emp "
                    + "from balance "
                    + "where r_table='" + table + "' "
                    + "and r_index='" + r_index + "' "
                    + "and r_plucode='" + Code + "';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                empName = ThaiUtil.ASCII2Unicode(rs.getString("r_emp"));
                empName = getEmpName(empName);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return empName;
    }

}
