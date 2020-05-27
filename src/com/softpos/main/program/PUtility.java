package com.softpos.main.program;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import database.MySQLConnect;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class PUtility {

    static DecimalFormat DecFmt = new DecimalFormat("#######0.00");
    Font myfont = new Font("Tahoma", Font.PLAIN, 14);
    static SimpleDateFormat Dateft = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    Date date = new Date();

    public static Boolean CheckEAN13Format(String TempBarcode) {

        return true;
    }

    public static int GetActionMon(Date EndofdayDate) {
        int RetVal = 0;
        SimpleDateFormat XYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        SimpleDateFormat XMonth = new SimpleDateFormat("MM", Locale.ENGLISH);
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from company";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                String TempYear = XYear.format(rec.getDate("accterm"));
                String TempMonth = XMonth.format(rec.getDate("accterm"));
                String CurYear = XYear.format(EndofdayDate);
                String CurMonth = XMonth.format(EndofdayDate);
                if (TempYear.equals(CurYear)) {
                    RetVal = Integer.parseInt(CurMonth) + 12;
                } else {
                    if (Integer.parseInt(CurYear) == Integer.parseInt(TempYear) - 1) {
                        RetVal = Integer.parseInt(CurMonth);
                    } else {
                        RetVal = 0;
                    }
                }
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
        return RetVal;
    }

    public static boolean CheckEndOfDay() {
        return true;
    }

    public static boolean CheckPrintAutoSum() {
        return true;
    }

    public static void UpdateRePrintAutoSum() {
    }

    public static boolean CheckSaleDateOK() {
        return true;
    }

    public static String GetStkCode() {
        String RetVal = "A1";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from company ";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = "A1";
            } else {
                RetVal = rec.getString("posstock");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
        return RetVal;
    }

    public static String GetStockOnLine() {
        String RetVal = "N";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select * from company ";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = "N";
            } else {
                RetVal = "N";//rec.getString("minimumchk");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
        return RetVal;
    }

    public static Boolean CheckStockOK(String TempCode, double TempQty) {
        String T_Stk = GetStkCode();
        Boolean RetVal = true;
        if (PublicVar.CheckStockOnLine.equals("Y")) {
            //Date date = new Date();
            Boolean StkProc = false;
            Boolean SetProc = false;
            String PName = "";
            Date TDate = new Date();
            int TempAct = GetActionMon(TDate);
            String T_Mon = "bqty" + String.valueOf(TempAct);
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SqlQuery = "select *from product where pcode='" + TempCode + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    PName = rec.getString("pdesc");
                    if (rec.getString("pstock").equals("Y")) {
                        StkProc = true;
                    } else {
                        StkProc = false;
                    }
                    if (rec.getString("pset").equals("Y")) {
                        SetProc = true;
                    } else {
                        SetProc = false;
                    }
                }
                rec.close();
                stmt.close();
                if (StkProc) {
                    Statement stmt2 = mysql.getConnection().createStatement();
                    String LoadTableFile = "select *from stkfile where (bpcode='" + TempCode + "') and (bstk='" + T_Stk + "') ";
                    ResultSet rec2 = stmt2.executeQuery(LoadTableFile);
                    rec2.first();
                    if (rec2.getRow() == 0) {
                        ShowMsg("(" + PName + ") ปริมาณสินค้าคงเหลือในคลังสินค้าน้อยกว่าจำนวนที่ทำการขาย !!!" + "\n...ปริมาณคงเหลือ = 0");
                        RetVal = false;
                    } else {
                        double OnHand = rec2.getDouble(T_Mon);
                        if (OnHand >= TempQty) {
                            RetVal = true;
                        } else {
                            ShowMsg("(" + PName + ") ปริมาณสินค้าคงเหลือในคลังสินค้าน้อยกว่าจำนวนที่ทำการขาย !!!" + "\n...ปริมาณคงเหลือ = " + OnHand);
                            RetVal = false;
                        }
                    }
                    rec2.close();
                    stmt2.close();
                }
                if (SetProc) {
                    try {
                        Statement stmt3 = mysql.getConnection().createStatement();
                        String SQLQuery = "select *from pset where pcode='" + TempCode + "'";
                        ResultSet rec3 = stmt3.executeQuery(SQLQuery);
                        rec3.first();
                        if (rec3.getRow() == 0) {
                        } else {
                            RetVal = true;
                            do {
                                String TempSub = rec3.getString("psubcode");
                                Double TempSubQty = rec3.getDouble("psubqty") * TempQty;
                                StkProc = false;
                                Statement stmt4 = mysql.getConnection().createStatement();
                                String LoadTableFile = "select *from product where pcode='" + TempSub + "'";
                                ResultSet rec4 = stmt4.executeQuery(LoadTableFile);
                                rec4.first();
                                if (rec4.getRow() == 0) {
                                } else {
                                    if (rec4.getString("pstock").equals("Y")) {
                                        StkProc = true;
                                    } else {
                                        StkProc = false;
                                    }
                                }
                                rec4.close();
                                stmt4.close();
                                if (StkProc) {
                                    Statement stmt5 = mysql.getConnection().createStatement();
                                    String LoadTableFile2 = "select *from stkfile where (bpcode='" + TempSub + "') and (bstk='" + T_Stk + "') ";
                                    ResultSet rec5 = stmt5.executeQuery(LoadTableFile2);
                                    rec5.first();
                                    if (rec5.getRow() == 0) {
                                        ShowMsg("(" + PUtility.SeekProductName(TempSub) + ") ปริมาณสินค้าคงเหลือในคลังสินค้าน้อยกว่าจำนวนที่ทำการขาย !!!" + "\n...ปริมาณคงเหลือ = 0");
                                        RetVal = false;
                                        break;
                                    } else {
                                        double OnHand = rec5.getDouble(T_Mon);
                                        if (OnHand >= TempQty) {
                                            RetVal = true;
                                        } else {
                                            ShowMsg("(" + PUtility.SeekProductName(TempSub) + ") ปริมาณสินค้าคงเหลือในคลังสินค้าน้อยกว่าจำนวนที่ทำการขาย !!!" + "\n...ปริมาณคงเหลือ = " + OnHand);
                                            RetVal = false;
                                            break;
                                        }
                                    }
                                    rec5.close();
                                    stmt5.close();
                                }
                            } while (rec3.next());
                        }
                        rec3.close();
                        stmt3.close();
                    } catch (SQLException e) {
                        MSG.ERR(null, e.getMessage());
                    }

                }
            } catch (SQLException e) {
                MSG.ERR(null, e.getMessage());
            } finally {
                mysql.close();
            }
        }
        return RetVal;
    }

    public static Boolean SeekStkFile(String TempCode, String T_Stk) {
        Boolean RetVal = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String LoadTableFile = "select *from stkfile where (bpcode='" + TempCode + "') and (bstk='" + T_Stk + "') ";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = false;
            } else {
                RetVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
        return RetVal;
    }

    public static void ProcessStockOut(String DocNo, String StkCode, String PCode, Date TDate,
            String Stk_Remark, Double Qty, Double Amount, String UserPost, String PStock,
            String PSet, String r_index, String SaleOrRefund) {
        SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        String TempCode = PCode;
        Double TempQty = Qty;
        Double TempAmt = Amount;
        String T_Rem = Stk_Remark;
        Boolean StkProc = true;
        Boolean SetProc = false;
        Boolean SelectSet = false;
        if (PStock.equals("Y")) {
            StkProc = true;
        } else {
            StkProc = false;
        }
        if (PSet.equals("Y")) {
            SetProc = true;
            SelectSet = CheckPSetSelect(PCode);
        } else {
            SetProc = false;
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            if (StkProc) {
                Statement stmt2 = mysql.getConnection().createStatement();
                String InsertQuery = "insert into stcard (s_date,s_no,s_stk,s_pcode,s_que,s_in,s_incost,"
                        + "s_out,s_outcost,s_rem,s_user,s_entrydate,s_entrytime) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement prm = mysql.getConnection().prepareStatement(InsertQuery);
                prm.setString(1, SqlDateFmt.format(date));
                prm.setString(2, DocNo);
                prm.setString(3, StkCode);
                prm.setString(4, TempCode);
                prm.setInt(5, 1);
                prm.setDouble(6, 0);
                prm.setDouble(7, 0);
                prm.setDouble(8, TempQty);
                prm.setDouble(9, TempAmt);
                prm.setString(10, T_Rem);
                prm.setString(11, UserPost); //User

                prm.setString(12, SqlDateFmt.format(date));
                prm.setString(13, TimeFmt.format(date));
                prm.executeUpdate();
                stmt2.close();
                int TempAct = GetActionMon(TDate);
                if (!SeekStkFile(TempCode, StkCode)) {
                    Statement stmt4 = mysql.getConnection().createStatement();
                    String InsertQuery4 = "insert into stkfile (bpcode,bstk) values (?,?)";
                    PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                    prm4.setString(1, TempCode);
                    prm4.setString(2, StkCode);
                    prm4.executeUpdate();
                    stmt4.close();
                }
                for (int i = TempAct; i <= 24; i++) {
                    String T_Mon = "bqty" + String.valueOf(i);
                    Statement stmt4 = mysql.getConnection().createStatement();
                    String InsertQuery4 = "update stkfile set " + T_Mon + "=" + T_Mon + "-? where (bpcode=?) and (bstk=?)";
                    PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                    prm4.setDouble(1, TempQty);
                    prm4.setString(2, TempCode);
                    prm4.setString(3, StkCode);
                    prm4.executeUpdate();
                    stmt4.close();
                }
            }
            if (SetProc) {
                if (!SelectSet) {
                    ProcessSetUpdateStockOut(DocNo, StkCode, PCode, TDate, Stk_Remark, Qty, UserPost);
                } else {
                    if (SaleOrRefund.equals("1")) {
                        ProcessSelectSetUpdateStockOut(DocNo, StkCode, PCode, TDate, Stk_Remark, Qty, UserPost, r_index);
                    } else if (SaleOrRefund.equals("2")) {
                        ProcessSelectSetUpdateStockOutRefund(DocNo, StkCode, PCode, TDate, Stk_Remark, Qty, UserPost, r_index);
                    }

                }
            }
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static void ProcessStockIn(String DocNo, String StkCode, String PCode, Date TDate,
            String Stk_Remark, Double Qty, Double Amount, String UserPost, String PStock,
            String PSet, String r_index, String SaleOrRefund) {
        SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        String TempCode = PCode;
        Double TempQty = Qty;
        Double TempAmt = Amount;
        String T_Rem = Stk_Remark;
        Boolean StkProc = true;
        Boolean SetProc = false;
        Boolean SelectSet = false;
        if (PStock.equals("Y")) {
            StkProc = true;
        } else {
            StkProc = false;
        }
        if (PSet.equals("Y")) {
            SetProc = true;
            SelectSet = CheckPSetSelect(PCode);
        } else {
            SetProc = false;
        }

        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            if (StkProc) {
                Statement stmt2 = mysql.getConnection().createStatement();
                String InsertQuery = "insert into stcard (s_date,s_no,s_stk,s_pcode,s_que,s_in,s_incost,"
                        + "s_out,s_outcost,s_rem,s_user,s_entrydate,s_entrytime) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement prm = mysql.getConnection().prepareStatement(InsertQuery);
                prm.setString(1, SqlDateFmt.format(date));
                prm.setString(2, DocNo);
                prm.setString(3, StkCode);
                prm.setString(4, TempCode);
                prm.setInt(5, 1);
                prm.setDouble(6, 0);
                prm.setDouble(7, 0);
                prm.setDouble(8, TempQty);
                prm.setDouble(9, TempAmt);
                prm.setString(10, T_Rem);
                prm.setString(11, UserPost); //User

                prm.setString(12, SqlDateFmt.format(date));
                prm.setString(13, TimeFmt.format(date));
                prm.executeUpdate();
                stmt2.close();
                int TempAct = GetActionMon(TDate);
                if (!SeekStkFile(TempCode, StkCode)) {
                    Statement stmt4 = mysql.getConnection().createStatement();
                    String InsertQuery4 = "insert into stkfile (bpcode,bstk) values (?,?)";
                    PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                    prm4.setString(1, TempCode);
                    prm4.setString(2, StkCode);
                    prm4.executeUpdate();
                    stmt4.close();
                }
                for (int i = TempAct; i <= 24; i++) {
                    String T_Mon = "bqty" + String.valueOf(i);
                    Statement stmt4 = mysql.getConnection().createStatement();
                    String InsertQuery4 = "update stkfile set " + T_Mon + "=" + T_Mon + "-? where (bpcode=?) and (bstk=?)";
                    PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                    prm4.setDouble(1, TempQty);
                    prm4.setString(2, TempCode);
                    prm4.setString(3, StkCode);
                    prm4.executeUpdate();
                    stmt4.close();
                }
            }
            if (SetProc) {
                if (!SelectSet) {
                    ProcessSetUpdateStockOut(DocNo, StkCode, PCode, TDate, Stk_Remark, Qty, UserPost);
                } else {
                    if (SaleOrRefund.equals("1")) {
                        ProcessSelectSetUpdateStockOut(DocNo, StkCode, PCode, TDate, Stk_Remark, Qty, UserPost, r_index);
                    } else if (SaleOrRefund.equals("2")) {
                        ProcessSelectSetUpdateStockOutRefund(DocNo, StkCode, PCode, TDate, Stk_Remark, Qty, UserPost, r_index);
                    }

                }
            }
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static boolean CheckPSetSelect(String PCode) {
        boolean RetValue = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SqlQuery = "select *from product where pcode='" + PublicVar.P_Code + "' and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetValue = false;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return RetValue;
    }

    public static void ProcessSetUpdateStockOut(String DocNo, String StkCode, String XCode, Date TDate, String StkRemark, Double XQty, String UserPost) {
        SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "select * from pset where pcode='" + XCode + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    String TempCode = rec.getString("psubcode");
                    Double TempQty = rec.getDouble("psubqty") * XQty;
                    Double TempAmt = 0.0;
                    String T_Rem = StkRemark;
                    Boolean StkProc = false;
                    Statement stmt3 = mysql.getConnection().createStatement();
                    String LoadTableFile = "select *from product where pcode='" + TempCode + "'";
                    ResultSet rec3 = stmt3.executeQuery(LoadTableFile);
                    rec3.first();
                    if (rec3.getRow() == 0) {
                    } else {
                        if (rec3.getString("pstock").equals("Y")) {
                            StkProc = true;
                        } else {
                            StkProc = false;
                        }
                        TempAmt = rec3.getDouble("pprice11") * XQty;
                    }
                    rec3.close();
                    stmt3.close();
                    if (StkProc) {
                        Statement stmt2 = mysql.getConnection().createStatement();
                        String InsertQuery = "insert into stcard (s_date,s_no,s_stk,s_pcode,s_que,s_in,s_incost,"
                                + "s_out,s_outcost,s_rem,s_user,s_entrydate,s_entrytime) "
                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement prm = mysql.getConnection().prepareStatement(InsertQuery);
                        prm.setString(1, SqlDateFmt.format(TDate));
                        prm.setString(2, DocNo + "@" + XCode);
                        prm.setString(3, StkCode);
                        prm.setString(4, TempCode);
                        prm.setInt(5, 1);
                        prm.setDouble(6, 0);
                        prm.setDouble(7, 0);
                        prm.setDouble(8, TempQty);
                        prm.setDouble(9, TempAmt);
                        prm.setString(10, T_Rem);
                        prm.setString(11, UserPost); //User

                        prm.setString(12, SqlDateFmt.format(date));
                        prm.setString(13, TimeFmt.format(date));
                        prm.executeUpdate();
                        stmt2.close();
                        int TempAct = GetActionMon(TDate);
                        if (!SeekStkFile(TempCode, StkCode)) {
                            Statement stmt4 = mysql.getConnection().createStatement();
                            String InsertQuery4 = "insert into stkfile (bpcode,bstk) values (?,?)";
                            PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                            prm4.setString(1, TempCode);
                            prm4.setString(2, StkCode);
                            prm4.executeUpdate();
                            stmt4.close();
                        }
                        for (int i = TempAct; i <= 24; i++) {
                            String T_Mon = "bqty" + String.valueOf(i);
                            Statement stmt4 = mysql.getConnection().createStatement();
                            String InsertQuery4 = "update stkfile set " + T_Mon + "=" + T_Mon + "-? where (bpcode=?) and (bstk=?)";
                            PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                            prm4.setDouble(1, TempQty);
                            prm4.setString(2, TempCode);
                            prm4.setString(3, StkCode);
                            prm4.executeUpdate();
                            stmt4.close();
                        }
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static void ProcessSelectSetUpdateStockOut(String DocNo, String StkCode, String XCode, Date TDate, String StkRemark, Double XQty, String UserPost, String r_index) {
        SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "select *from balanceset "
                    + "where r_plucode='" + XCode + "' "
                    + "and r_index='" + r_index + "' ";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    String TempCode = rec.getString("r_psubcode");
                    Double TempQty = rec.getDouble("r_setqty") * XQty;
                    Double TempAmt = 0.0;
                    String T_Rem = StkRemark;
                    Boolean StkProc = false;
                    Statement stmt3 = mysql.getConnection().createStatement();
                    String LoadTableFile = "select * from product "
                            + "where pcode='" + TempCode + "'";
                    ResultSet rec3 = stmt3.executeQuery(LoadTableFile);
                    rec3.first();
                    if (rec3.getRow() == 0) {
                    } else {
                        if (rec3.getString("pstock").equals("Y")) {
                            StkProc = true;
                        } else {
                            StkProc = false;
                        }
                        TempAmt = rec3.getDouble("pprice11") * XQty;
                    }
                    rec3.close();
                    stmt3.close();
                    if (StkProc) {
                        Statement stmt2 = mysql.getConnection().createStatement();
                        String InsertQuery = "insert into stcard (s_date,s_no,s_stk,s_pcode,s_que,s_in,s_incost,"
                                + "s_out,s_outcost,s_rem,s_user,s_entrydate,s_entrytime) "
                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement prm = mysql.getConnection().prepareStatement(InsertQuery);
                        prm.setString(1, SqlDateFmt.format(TDate));
                        prm.setString(2, DocNo + "@" + XCode);
                        prm.setString(3, StkCode);
                        prm.setString(4, TempCode);
                        prm.setInt(5, 1);
                        prm.setDouble(6, 0);
                        prm.setDouble(7, 0);
                        prm.setDouble(8, TempQty);
                        prm.setDouble(9, TempAmt);
                        prm.setString(10, T_Rem);
                        prm.setString(11, UserPost); //User

                        prm.setString(12, SqlDateFmt.format(date));
                        prm.setString(13, TimeFmt.format(date));
                        prm.executeUpdate();
                        stmt2.close();
                        int TempAct = GetActionMon(TDate);
                        if (!SeekStkFile(TempCode, StkCode)) {
                            Statement stmt4 = mysql.getConnection().createStatement();
                            String InsertQuery4 = "insert into stkfile (bpcode,bstk) values (?,?)";
                            PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                            prm4.setString(1, TempCode);
                            prm4.setString(2, StkCode);
                            prm4.executeUpdate();
                            stmt4.close();
                        }
                        for (int i = TempAct; i <= 24; i++) {
                            String T_Mon = "bqty" + String.valueOf(i);
                            Statement stmt4 = mysql.getConnection().createStatement();
                            String InsertQuery4 = "update stkfile set " + T_Mon + "=" + T_Mon + "-? where (bpcode=?) and (bstk=?)";
                            PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                            prm4.setDouble(1, TempQty);
                            prm4.setString(2, TempCode);
                            prm4.setString(3, StkCode);
                            prm4.executeUpdate();
                            stmt4.close();
                        }
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static void ProcessSelectSetUpdateStockOutRefund(String DocNo, String StkCode, String XCode, Date TDate, String StkRemark, Double XQty, String UserPost, String r_index) {
        SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String XDocNo = DocNo.substring(1, 8);
        Date date = new Date();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "select *from t_saleset where r_plucode='" + XCode + "' and r_index='" + r_index + "'  and r_refno='" + XDocNo + "' ";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    String TempCode = rec.getString("r_psubcode");
                    Double TempQty = rec.getDouble("r_setqty") * XQty;
                    Double TempAmt = 0.0;
                    String T_Rem = StkRemark;
                    Boolean StkProc = false;
                    Statement stmt3 = mysql.getConnection().createStatement();
                    String LoadTableFile = "select *from product where pcode='" + TempCode + "'";
                    ResultSet rec3 = stmt3.executeQuery(LoadTableFile);
                    rec3.first();
                    if (rec3.getRow() == 0) {
                    } else {
                        if (rec3.getString("pstock").equals("Y")) {
                            StkProc = true;
                        } else {
                            StkProc = false;
                        }
                        TempAmt = rec3.getDouble("pprice11") * XQty;
                    }
                    rec3.close();
                    stmt3.close();
                    if (StkProc) {
                        Statement stmt2 = mysql.getConnection().createStatement();
                        String InsertQuery = "insert into stcard (s_date,s_no,s_stk,s_pcode,s_que,s_in,s_incost,"
                                + "s_out,s_outcost,s_rem,s_user,s_entrydate,s_entrytime) "
                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement prm = mysql.getConnection().prepareStatement(InsertQuery);
                        prm.setString(1, SqlDateFmt.format(TDate));
                        prm.setString(2, DocNo + "@" + XCode);
                        prm.setString(3, StkCode);
                        prm.setString(4, TempCode);
                        prm.setInt(5, 1);
                        prm.setDouble(6, 0);
                        prm.setDouble(7, 0);
                        prm.setDouble(8, TempQty);
                        prm.setDouble(9, TempAmt);
                        prm.setString(10, T_Rem);
                        prm.setString(11, UserPost); //User

                        prm.setString(12, SqlDateFmt.format(date));
                        prm.setString(13, TimeFmt.format(date));
                        prm.executeUpdate();
                        stmt2.close();
                        int TempAct = GetActionMon(TDate);
                        if (!SeekStkFile(TempCode, StkCode)) {
                            Statement stmt4 = mysql.getConnection().createStatement();
                            String InsertQuery4 = "insert into stkfile (bpcode,bstk) values (?,?)";
                            PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                            prm4.setString(1, TempCode);
                            prm4.setString(2, StkCode);
                            prm4.executeUpdate();
                            stmt4.close();
                        }
                        for (int i = TempAct; i <= 24; i++) {
                            String T_Mon = "bqty" + String.valueOf(i);
                            Statement stmt4 = mysql.getConnection().createStatement();
                            String InsertQuery4 = "update stkfile set " + T_Mon + "=" + T_Mon + "-? where (bpcode=?) and (bstk=?)";
                            PreparedStatement prm4 = mysql.getConnection().prepareStatement(InsertQuery4);
                            prm4.setDouble(1, TempQty);
                            prm4.setString(2, TempCode);
                            prm4.setString(3, StkCode);
                            prm4.executeUpdate();
                            stmt4.close();
                        }
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static String GetKeyAddMemberValue(String MemberCode) {
        SimpleDateFormat Timefmt = new SimpleDateFormat("hhmmss", Locale.ENGLISH);
        DecimalFormat IntFmt = new DecimalFormat("##########");
        Date date2 = new Date();
        String PTime = Timefmt.format(date2);
        int Sumx = 0;
        for (int i = 0; i < PTime.length(); i++) {
            String ch = PTime.substring(i, i + 1);
            Sumx = Sumx + Integer.parseInt(ch);
        }
        String EndCode = MemberCode.substring(4, 7);
        int IntEndCode = Integer.parseInt(EndCode);
        int SumCode = 0;
        for (int i = 0; i < MemberCode.length(); i++) {
            String ch = MemberCode.substring(i, i + 1);
            SumCode = SumCode + (Integer.parseInt(ch) * 3);
        }
        int Sum1 = SumCode * Sumx * IntEndCode;
        String StrSum1 = IntFmt.format(Sum1);
        String StrSumx = IntFmt.format(Sumx);
        String Tempcode1;
        String Tempcode2;
        if (StrSum1.length() >= 6) {
            Tempcode1 = StrSum1.substring(0, 6);
        } else {
            Tempcode1 = PUtility.Addzero(StrSum1, 6);
        }
        if (StrSumx.length() >= 2) {
            Tempcode2 = StrSumx.substring(0, 2);
        } else {
            Tempcode2 = PUtility.Addzero(StrSumx, 2);
        }
        String XKeyCode = Tempcode1 + Tempcode2;
        return XKeyCode;
    }

    public static Boolean CheckKeyAddMemberValue(String MemberCode, String KeyCheck) {
        SimpleDateFormat Timefmt = new SimpleDateFormat("hhmmss", Locale.ENGLISH);
        DecimalFormat IntFmt = new DecimalFormat("##########");
        String StrSumx = KeyCheck.substring(6, 8);
        int Sumx = Integer.parseInt(StrSumx);
        String EndCode = MemberCode.substring(4, 7);
        int IntEndCode = Integer.parseInt(EndCode);
        int SumCode = 0;
        for (int i = 0; i < MemberCode.length(); i++) {
            String ch = MemberCode.substring(i, i + 1);
            SumCode = SumCode + (Integer.parseInt(ch) * 3);
        }
        int Sum1 = SumCode * Sumx * IntEndCode;
        String StrSum1 = IntFmt.format(Sum1);
        String Tempcode1;
        String Tempcode2;
        if (StrSum1.length() >= 6) {
            Tempcode1 = StrSum1.substring(0, 6);
        } else {
            Tempcode1 = PUtility.Addzero(StrSum1, 6);
        }
        if (StrSumx.length() >= 2) {
            Tempcode2 = StrSumx.substring(0, 2);
        } else {
            Tempcode2 = PUtility.Addzero(StrSumx, 2);
        }
        String XKeyCode = Tempcode1 + Tempcode2;
        if (XKeyCode.equals(KeyCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean CheckCouponRedule(String XCode2) {
        Boolean ReturnVal = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "select *from cupon where cucode=" + "'" + XCode2 + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnVal = false;
            } else {
                if (rec.getString("reduleDiscount").equals("Y")) {
                    ReturnVal = true;
                } else {
                    ReturnVal = false;
                }
            }
        } catch (SQLException ex) {
            MSG.ERR(ex.getMessage());
        } finally {
            mysql.close();
        }

        return ReturnVal;
    }

    public static int GetUserRound(String XUser) {
        int ReturnVal = 1;
        String TempUser = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        for (int i = 1; i < 30; i++) {
            TempUser = XUser + "-" + i;
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SQLQuery = "select *from billno where b_cashier=" + "'" + TempUser + "'";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    ReturnVal = i;
                    break;
                } else {
                    if (rec.getString("b_roundclose").equals("Y")) {
                    } else {
                        ReturnVal = i;
                        break;
                    }
                }
            } catch (SQLException ex) {
                MSG.ERR(ex.getMessage());
            }
        }

        mysql.close();

        return ReturnVal;

    }

    public static boolean CheckCashierClose(String XUser) {
        boolean ReturnVal = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "select *from billno where b_cashier=" + "'" + XUser + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnVal = false;
            } else {
                if (rec.getString("b_roundclose").equals("Y")) {
                    ReturnVal = true;
                } else {
                    ReturnVal = false;
                }
            }
        } catch (SQLException ex) {
            MSG.ERR(ex.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnVal;

    }

    public static String ConvertReal(String TStr) {
        String TempStr = "";
        if (TStr.equals("")) {
            TStr = "0";
        }
        if (TStr.indexOf(",") > 0) {
            for (int i = 0; i < TStr.length(); i++) {
                char ch = TStr.charAt(i);
                String StrCh = "";
                StrCh = StrCh + ch;
                if (!StrCh.equals(",")) {
                    TempStr = TempStr + StrCh;
                }
            }
        } else {
            TempStr = TStr;
        }
        return TempStr;
    }

    public static Boolean ChkValidDate(Date tdate) {
        Boolean ReturnValue = true;
        String TempStr = "";
        try {
            TempStr = Dateft.format(tdate);
            ReturnValue = true;
        } catch (Exception e) {
            ReturnValue = false;
        }
        return ReturnValue;
    }

    public static Boolean ChkDate(String tdate) {
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean ReturnValue = true;
        Date TempDate = new Date();
        try {
            TempDate = DateFmt.parse(tdate);
            if (TempDate.getYear() > 2200) {
                MSG.ERR(null, "กรุณาป้อนวันที่ให้ถูกต้อง...EXP(dd/MM/yyyy) โดยป้อนปีเป็นปี คศ.เท่านั้น...");
                ReturnValue = false;
            } else {
                ReturnValue = true;
            }
        } catch (ParseException ex) {
            MSG.ERR(null, "กรุณาป้อนวันที่ให้ถูกต้อง...EXP(dd/MM/yyyy)");
            ReturnValue = false;
        }

        return ReturnValue;
    }

    public static java.util.Calendar StrToCalendar(String tdate) {
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean ReturnValue = true;
        Calendar ca2 = Calendar.getInstance();
        Date TempDate = new Date();
        String[] casplit = tdate.split("/");
        try {
            TempDate = DateFmt.parse(tdate);
            ca2.set(Integer.parseInt(casplit[2]), Integer.parseInt(casplit[1]) - 1, Integer.parseInt(casplit[0]));
        } catch (ParseException ex) {
        }
        return ca2;
    }

    public static int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end

            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
                - d1.get(java.util.Calendar.DAY_OF_YEAR);
        int y2 = d2.get(java.util.Calendar.YEAR);
        if (d1.get(java.util.Calendar.YEAR) != y2) {
            d1 = (java.util.Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
                d1.add(java.util.Calendar.YEAR, 1);
            } while (d1.get(java.util.Calendar.YEAR) != y2);
        }
        return days;
    }

    public static Boolean ShowConfirmMsg(String ConfirmMsg) {
        int option = JOptionPane.showConfirmDialog(null, ConfirmMsg, "Confirm Dialog ....", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public static String Addzero(String Str, int Len) {
        String TempStr = "";
        String ReturnStr = "";
        int StrLen = Str.trim().length();
        if (StrLen < Len) {
            for (int i = 1; i <= Len - StrLen; i++) {
                TempStr = TempStr + "0";
            }
            ReturnStr = TempStr + Str.trim();
        } else {
            ReturnStr = Str;
        }
        return ReturnStr;
    }

    public static boolean ChkNumValue(String Str) {
        String NumList[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "-", ","};
        char ch;
        String Tmp;
        int Num = 0;
        int Chk;
        for (int j = 0; j < Str.length(); j++) {
            ch = Str.charAt(j);
            Tmp = "";
            Tmp = Tmp + ch;

            Chk = 0;
            for (String NumList1 : NumList) {
                if (NumList1.equals(Tmp)) {
                    Chk++;
                }
            }
            if (Chk > 0) {
                Num++;
            }
        }
        return Num == Str.length() && Str.length() != 0;
    }

    public static boolean ChkIntValue(String Str) {
        String NumList[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        char ch;
        String Tmp;
        int Num = 0;
        int Chk = 0;
        for (int j = 0; j < Str.length(); j++) {
            ch = Str.charAt(j);
            Tmp = "";
            Tmp = Tmp + ch;

            Chk = 0;
            for (int i = 0; i < NumList.length; i++) {
                if (NumList[i].equals(Tmp)) {
                    Chk++;
                }
            }
            if (Chk > 0) {
                Num++;
            }
        }
        if (Num == Str.length() && Str.length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String DataFull2(String Str, int Len) {
        return TAB + Str;
    }

    public static String DataFull(String Str, int Len) {
        if (Str == null) {
            return "";
        }
        String ReturnStr = "";
        String AddStr = "";
        if (Len < Str.length()) {
            ReturnStr = Str.substring(0, Len - 1);
        } else {
            for (int i = 1; i <= (Len - Str.length()); i++) {
                AddStr = AddStr + " ";
            }
            ReturnStr = AddStr + Str.trim();
        }

        return ReturnStr;
    }

    public static String DataFullSpace(String Str, int Len) {
        if (Str == null) {
            return "";
        }
        String ReturnStr = "";
        String AddStr = "";
        if (Len < Str.length()) {
            ReturnStr = Str.substring(0, Len - 1);
        } else {
            for (int i = 1; i <= (Len - Str.length()); i++) {
                AddStr = AddStr + "&nbsp; ";
            }
            ReturnStr = AddStr + Str.trim();
        }

        return ReturnStr;
    }

    public static String TAB = "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;";

    public static String DataFullR2(String Str, int Len) {
        return TAB + Str;
    }

    public static String DataFullR(String Str, int Len) {
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
        PPrint prn = new PPrint();
        int I = 0;
        int ICnt = 0;
        char ch;
        String TempStr = ThaiUtil.Unicode2ASCII(ReturnStr);
        while (I <= TempStr.length() - 1) {   // Check TIS Upper }

            ch = TempStr.charAt(I);
            if (PPrint.SearchArray((int) ch, List1) != -1) {
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

    public static String CurDate() {
        Date d = new Date();
        String StrDate;
        return StrDate = Dateft.format(d);
    }

    public static void ShowMsg(String StrMsg) {
        MSG.NOTICE(null, StrMsg);
    }

    public static void showError(String StrMsg) {
        MSG.ERR(JOptionPane.getRootFrame(), StrMsg);
    }

    public static void ShowMyMsg(java.awt.Component TComponent, String StrMsg) {
        MSG.NOTICE(TComponent, StrMsg);
    }

    public static void ShowWaring(String StrMsg) {
        MSG.WAR(null, StrMsg);
    }

    public static TranRecord[] addArray(TranRecord[] MyArray) {
        int aSize = MyArray.length;
        TranRecord[] NewArray;
        NewArray = new TranRecord[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }

    public static CreditRec[] addCrArray(CreditRec[] MyArray) {
        int aSize = MyArray.length;
        CreditRec[] NewArray;
        NewArray = new CreditRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }

    public static CouponRec[] addCuArray(CouponRec[] CuArray) {
        int aSize = CuArray.length;
        CouponRec[] NewArray;
        NewArray = new CouponRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = CuArray[i];
        }
        CuArray = NewArray;
        return CuArray;

    }

    public static CreditPaymentRec[] addCreditArray(CreditPaymentRec[] CuArray) {
        int aSize = CuArray.length;
        CreditPaymentRec[] NewArray;
        NewArray = new CreditPaymentRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = CuArray[i];
        }
        CuArray = NewArray;
        return CuArray;

    }

    public static CouponDetailRec[] addCuDetailArray(CouponDetailRec[] CuDetailArray) {
        int aSize = CuDetailArray.length;
        CouponDetailRec[] NewArray;
        NewArray = new CouponDetailRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = CuDetailArray[i];
        }
        CuDetailArray = NewArray;
        return CuDetailArray;

    }

    public static PluRec[] addPluArray(PluRec[] MyArray) {
        int aSize = MyArray.length;
        PluRec[] NewArray;
        NewArray = new PluRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }

    public static HourlyRec[] addHourlyArray(HourlyRec[] MyArray) {
        int aSize = MyArray.length;
        HourlyRec[] NewArray;
        NewArray = new HourlyRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }

    public static String ReadRefBillNo(String Macno) {
        String StrRefBillNo = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();

            //Load Data From PosConfigSetup ;
            String SQLQuery = "select * from poshwsetup where terminal='" + Macno + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                showError("กรุณากำหนดค่าเริ่มต้นก่อนการใช้งาน !!!!");
            } else {
                int Refbillno = rec.getInt("receno1");
                StrRefBillNo = Integer.toString(Refbillno);
            }
            rec.close();
        } catch (SQLException e) {
            showError(e.getMessage());
        } finally {
            mysql.close();
        }
        return PUtility.Addzero(StrRefBillNo, 7);
    }

    public static String ReadChargeBillNo(String Macno) {
        String StrRefBillNo = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            //Load Data From PosConfigSetup ;
            String SQLQuery = "select *from branch ";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                showError("กรุณากำหนดค่าเริ่มต้นก่อนการใช้งาน !!!!");
            } else {

                int Refbillno = rec.getInt("Chargeno1");
                StrRefBillNo = Integer.toString(Refbillno);
            }
            rec.close();
        } catch (SQLException e) {
            showError(e.getMessage());
        } finally {
            mysql.close();
        }
        return PublicVar.Branch_Code + PUtility.Addzero(StrRefBillNo, 7);
    }

    public static Double ProcessScore(String BranchCode, Double Amount) {
        Double ReturnValue = 0.0;
        String XPT1 = "";
        String XPT2 = "";
        String XPT3 = "";
        String XPT4 = "";
        String XPT5 = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SQLQuery = "select *from branch ";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                showError("กรุณากำหนดข้อมูลสาขาก่อนการใช้งาน...");
            } else {
                XPT1 = rec.getString("PT1");
                XPT2 = rec.getString("PT2");
                XPT3 = rec.getString("PT3");
                XPT4 = rec.getString("PT4");
                XPT5 = rec.getString("PT5");
            }
            rec.close();
        } catch (SQLException e) {
            showError(e.getMessage());
        } finally {
            mysql.close();
        }
        Double TempPoint = 0.0;
        if (ChkMemPoint(XPT1)) {
            TempPoint = PublicVar.XPoint;
        } else if (ChkMemPoint(XPT2)) {
            TempPoint = PublicVar.XPoint;
        } else if (ChkMemPoint(XPT3)) {
            TempPoint = PublicVar.XPoint;
        } else if (ChkMemPoint(XPT4)) {
            TempPoint = PublicVar.XPoint;
        } else if (ChkMemPoint(XPT5)) {
            TempPoint = PublicVar.XPoint;
        }
        if (TempPoint > 0) {
            ReturnValue = Amount / TempPoint;
        } else {
            ReturnValue = 0.0;
        }
        return ReturnValue;
    }

    public static Boolean ChkMemPoint(String ProCode) {
        Boolean ReturnValue = false;
        if (ProCode.equals("")) {
            ReturnValue = false;
        } else {
            Date CurDate = new Date();
            SimpleDateFormat SqlDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            SimpleDateFormat DayFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
            SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm");
            String CurTime = TimeFormat.format(CurDate);
            String CurDay = DayFormat.format(CurDate);

            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                Statement stmt = mysql.getConnection().createStatement();
                String SQLQuery = "select *from mpointtype where ptcode= '" + ProCode + "'";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    showError("ไม่พบตารางสำหรับคำนวณแต้มสะสมของสมาชิก ..." + "Code : " + ProCode);
                } else {
                    PublicVar.Procode = rec.getString("ptcode");
                    PublicVar.PDate1 = rec.getDate("ptstartdate");
                    PublicVar.PDate2 = rec.getDate("ptenddate");
                    PublicVar.PSTRDay = rec.getString("ptstrday");
                    PublicVar.PTime1S = rec.getString("ptstarttime1");
                    PublicVar.PTime1E = rec.getString("ptendtime1");
                    PublicVar.PDisc1 = rec.getDouble("ptrate1");
                    PublicVar.PTime2S = rec.getString("ptstarttime2");
                    PublicVar.PTime2E = rec.getString("ptendtime2");
                    PublicVar.PDisc2 = rec.getDouble("ptrate2");
                    PublicVar.PTime3S = rec.getString("ptstarttime3");
                    PublicVar.PTime3E = rec.getString("ptendtime3");
                    PublicVar.PDisc3 = rec.getDouble("ptrate3");
                }
                rec.close();
            } catch (SQLException e) {
                showError(e.getMessage());
            } finally {
                mysql.close();
            }
            if ((SqlDate.format(CurDate).compareTo(SqlDate.format(PublicVar.PDate1)) >= 0) && (SqlDate.format(CurDate).compareTo(SqlDate.format(PublicVar.PDate2)) <= 0)) {
                //if (!((CurDate.compareTo(PublicVar.PDate1) < 0) || (CurDate.compareTo(PublicVar.PDate2) > 0))) {
                if (PublicVar.PSTRDay.indexOf(CurDay) >= 0) {
                    if (!((CurTime.compareTo(PublicVar.PTime1S) < 0) || (CurTime.compareTo(PublicVar.PTime1E) > 0))) {
                        PublicVar.XPoint = PublicVar.PDisc1;
                        ReturnValue = true;
                    } else if (!((CurTime.compareTo(PublicVar.PTime2S) < 0) || (CurTime.compareTo(PublicVar.PTime2E) > 0))) {
                        PublicVar.XPoint = PublicVar.PDisc2;
                        ReturnValue = true;
                    } else if (!((CurTime.compareTo(PublicVar.PTime3S) < 0) || (CurTime.compareTo(PublicVar.PTime3E) > 0))) {
                        PublicVar.XPoint = PublicVar.PDisc3;
                        ReturnValue = true;
                    }
                }
            }
        }
        return ReturnValue;
    }

    public static Boolean SeekPromotion(String XPro1) {
        Boolean ReturnVal = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            //Load Data From Promotion ;
            String SQLQuery = "select *from protab where procode=" + "'" + XPro1 + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnVal = false;
            } else {
//                String fixbranch = rec.getString("fixbranch");
                String fixbranch = "";
                if ((fixbranch.length() == 0) || (fixbranch.indexOf(PublicVar.Branch_Code) > 0)) {
                    PublicVar.Procode = rec.getString("procode");
                    PublicVar.ProDesc = rec.getString("prodesc");
                    PublicVar.PDate1 = rec.getDate("pdate1");
                    PublicVar.PDate2 = rec.getDate("pdate2");
                    PublicVar.PSTRDay = rec.getString("pstrday");
                    PublicVar.PTime1S = rec.getString("ptime1s");
                    PublicVar.PTime1E = rec.getString("ptime1e");
                    PublicVar.PDisc1 = rec.getDouble("pdisc1");
                    PublicVar.PSpDisc1 = rec.getDouble("pspdisc1");
                    PublicVar.PTS1 = rec.getString("pts1");
                    PublicVar.PTime2S = rec.getString("ptime2s");
                    PublicVar.PTime2E = rec.getString("ptime2e");
                    PublicVar.PDisc2 = rec.getDouble("pdisc2");
                    PublicVar.PSpDisc2 = rec.getDouble("pspdisc2");
                    PublicVar.PTS2 = rec.getString("pts2");
                    PublicVar.PTime3S = rec.getString("ptime3s");
                    PublicVar.PTime3E = rec.getString("ptime3e");
                    PublicVar.PDisc3 = rec.getDouble("pdisc3");
                    PublicVar.PSpDisc3 = rec.getDouble("pspdisc3");
                    PublicVar.PTS3 = rec.getString("pts3");
                    PublicVar.PType = rec.getString("ptype");
                    PublicVar.PSale1 = rec.getInt("psale1");
                    PublicVar.PFree1 = rec.getInt("pfree1");
                    PublicVar.PSum1 = rec.getInt("psum1");
                    PublicVar.PDiscFree1 = rec.getDouble("pdiscfree1");
                    PublicVar.PSale2 = rec.getInt("psale2");
                    PublicVar.PFree2 = rec.getInt("pfree2");
                    PublicVar.PSum2 = rec.getInt("psum2");
                    PublicVar.PDiscFree2 = rec.getDouble("pdiscfree2");
                    PublicVar.PSale3 = rec.getInt("psale3");
                    PublicVar.PFree3 = rec.getInt("pfree3");
                    PublicVar.PSum3 = rec.getInt("psum3");
                    PublicVar.PDiscFree3 = rec.getDouble("pdiscfree3");
                    PublicVar.PSale41 = rec.getInt("psale41");
                    PublicVar.PSale42 = rec.getInt("psale42");
                    PublicVar.PSale43 = rec.getInt("psale43");
                    PublicVar.PFree41 = rec.getInt("pfree41");
                    PublicVar.PFree42 = rec.getInt("pfree42");
                    PublicVar.PFree43 = rec.getInt("pfree43");
                    PublicVar.fixbranch = rec.getString("fixbranch");
                    ReturnVal = true;
                } else {
                    ReturnVal = false;
                }
            }
            rec.close();
        } catch (SQLException e) {
            showError(e.getMessage());
            ReturnVal = false;
        } finally {
            mysql.close();
        }

        return ReturnVal;
    }

    public static void ClearTempPromotion(String Table) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UpdateQry = "delete from temppromotion where tableonno=" + "'" + Table + "'";
            stmt.executeUpdate(UpdateQry);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

    }

    public static double RoundDecimal(double TempAmount, String RoundType) {
        double ReturnVal;
        double decimal = DecimalChk(TempAmount);
        double number = NumberChk(TempAmount);
        if (RoundType.equals("U")) {
            if (decimal > 0) {
                ReturnVal = number + 1;
            } else {
                ReturnVal = number;
            }
        } else if (RoundType.equals("D")) {
            ReturnVal = number;
        } else if (RoundType.equals("N")) {
            if (decimal >= 0.50) {
                ReturnVal = number + 1;
            } else {
                ReturnVal = number;
            }
        } else if (RoundType.equals("F")) {
            if (decimal >= 0.75) {
                if (decimal >= 0.87) {
                    ReturnVal = number + 1;
                } else {
                    ReturnVal = number + 0.75;
                }
            } else {
                if (decimal >= 0.50) {
                    if (decimal >= 0.63) {
                        ReturnVal = number + 0.75;
                    } else {
                        ReturnVal = number + 0.50;
                    }
                } else {
                    if (decimal >= 0.25) {
                        if (decimal >= 0.38) {
                            ReturnVal = number + 0.50;
                        } else {
                            ReturnVal = number + 0.25;
                        }
                    } else {
                        if (decimal >= 0.13) {
                            ReturnVal = number + 0.25;
                        } else {
                            ReturnVal = number;
                        }
                    }
                }
            }

        } else {
            return TempAmount;
        }

        return ReturnVal;
    }

    public static double NumberChk(Double TempAmount) {
        Double ReturnVal = 0.0;
        String TempNum = "";
        String TempDec = "";
        TempNum = TempAmount.toString();
        int index = TempNum.indexOf(".");
        if (index != -1) {
            TempDec = TempNum.substring(0, index);
            ReturnVal = Double.parseDouble(PUtility.ConvertReal(TempDec));
        } else {
            ReturnVal = 0.0;
        }
        return ReturnVal;
    }

    public static double DecimalChk(Double TempAmount) {
        Double ReturnVal = 0.0;
        String TempNum = "";
        String TempDec = "";
        TempNum = TempAmount.toString();
        int index = TempNum.indexOf(".");
        if (index != -1) {
            TempDec = TempNum.substring(index + 1);
            ReturnVal = Double.parseDouble(PUtility.ConvertReal(TempDec));
        } else {
            ReturnVal = 0.0;
        }
        return ReturnVal;
    }

    public static String SeekGroupName(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from groupfile where groupcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("groupname"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekProductName(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from product where pcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("pdesc"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekProductUnit(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from product where pcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("punit1"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static double getPricePluCodeEtdType(String TCode, String Etd) {
        double ReturnValues = 0.0;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from product where pcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnValues = 0.0;
            } else {
                if (Etd.equals("E")) {
                    ReturnValues = rec.getDouble("pprice11");
                }
                if (Etd.equals("T")) {
                    ReturnValues = rec.getDouble("pprice12");
                }
                if (Etd.equals("D")) {
                    ReturnValues = rec.getDouble("pprice13");
                }
                if (Etd.equals("P")) {
                    ReturnValues = rec.getDouble("pprice14");
                }
                if (Etd.equals("W")) {
                    ReturnValues = rec.getDouble("pprice15");
                }
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekCreditName(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from creditfile where crcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("crname"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekCuponName(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from cupon where cucode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("cuname"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekArName(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from custfile where sp_code='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("sp_desc"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekRegNo(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from poshwsetup where terminal='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "";
            } else {
                ReturnValues = rec.getString("macno");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static String SeekPromotionName(String TCode) {
        String ReturnValues = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String UserGroupFile = "select *from protab where procode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnValues = "*****";
            } else {
                ReturnValues = ThaiUtil.ASCII2Unicode(rec.getString("prodesc"));
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
        return ReturnValues;
    }

    public static Boolean SeekPromotion2(String Macno, String TCode) {
        boolean ReturnVal = false;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String SeekPromotion2 = "select *from promotion2 where (macno= '" + Macno + "') and (pcode= '" + TCode + "')";
            ResultSet rec = stmt.executeQuery(SeekPromotion2);
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
        } finally {
            mysql.close();
        }
        return ReturnVal;
    }

    public static Boolean ChkPromotion(String XPro1, TranRecord TranRec) {
        Boolean ReturnVal = false;
        if (XPro1.trim().length() == 0) {
            ReturnVal = false;
        } else {
            Date CurDate = new Date();
            //-----------------------
            SimpleDateFormat DayFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
            SimpleDateFormat SqlDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm");
            String CurTime = TimeFormat.format(CurDate);
            String CurDay = DayFormat.format(CurDate);
            if (PUtility.SeekPromotion(XPro1)) {
                if ((SqlDate.format(CurDate).compareTo(SqlDate.format(PublicVar.PDate1)) >= 0) && (SqlDate.format(CurDate).compareTo(SqlDate.format(PublicVar.PDate2)) <= 0)) {
                    if (PublicVar.PSTRDay.indexOf(CurDay) > 0) {
                        if (!((CurTime.compareTo(PublicVar.PTime1S) < 0) || (CurTime.compareTo(PublicVar.PTime1E) > 0))) {
                            if (PublicVar.PTS1.indexOf(TranRec.R_ETD) > 0) {
                                PublicVar.ProType = PublicVar.PType;
                                ReturnVal = true;
                                if (PublicVar.PType.equals("1")) {
                                    PublicVar.PDisc = PublicVar.PDisc1;
                                    PublicVar.PSpDisc = PublicVar.PSpDisc1;
                                    PublicVar.ProType = PublicVar.PType;
                                    if ((PublicVar.PDisc != 0) || (PublicVar.PSpDisc != 0)) {
                                        ReturnVal = true;
                                    } else {
                                        PublicVar.ProType = PublicVar.PType;
                                        ReturnVal = true;
                                    }
                                }
                            }
                        } else if (!((CurTime.compareTo(PublicVar.PTime2S) < 0) || (CurTime.compareTo(PublicVar.PTime2E) > 0))) {
                            if (PublicVar.PTS2.indexOf(TranRec.R_ETD) > 0) {
                                ReturnVal = true;
                                PublicVar.ProType = PublicVar.PType;
                                if (PublicVar.PType.equals("1")) {
                                    PublicVar.PDisc = PublicVar.PDisc2;
                                    PublicVar.PSpDisc = PublicVar.PSpDisc2;
                                    PublicVar.ProType = PublicVar.PType;
                                    if ((PublicVar.PDisc != 0) || (PublicVar.PSpDisc != 0)) {
                                        ReturnVal = true;
                                    } else {
                                        PublicVar.ProType = PublicVar.PType;
                                        ReturnVal = true;
                                    }
                                }
                            }
                        } else if (!((CurTime.compareTo(PublicVar.PTime3S) < 0) || (CurTime.compareTo(PublicVar.PTime3E) > 0))) {
                            if (PublicVar.PTS3.indexOf(TranRec.R_ETD) > 0) {
                                ReturnVal = true;
                                PublicVar.ProType = PublicVar.PType;
                                if (PublicVar.PType.equals("1")) {
                                    PublicVar.PDisc = PublicVar.PDisc3;
                                    PublicVar.PSpDisc = PublicVar.PSpDisc3;
                                    PublicVar.ProType = PublicVar.PType;
                                    if ((PublicVar.PDisc != 0) || (PublicVar.PSpDisc != 0)) {
                                        ReturnVal = true;
                                    } else {
                                        PublicVar.ProType = PublicVar.PType;
                                        ReturnVal = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                MSG.ERR("ไม่พบรายการโปรโมชั่น " + XPro1 + " ในแฟ้มข้อมูลโปรโมชั่น !!!");
                ReturnVal = false;
            }
        }
        return ReturnVal;
    }
}
