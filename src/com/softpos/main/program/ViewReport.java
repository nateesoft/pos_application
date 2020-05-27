package com.softpos.main.program;

import database.MySQLConnect;
import java.io.File;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;
import printReport.convertToChar;
import util.MSG;

public final class ViewReport {

    private Statement stmt;
    DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");
    SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public void printReportPVat(String vatNo) {
        ResultSet rs1;
        String comName = null, address = null, comTel = null, comFax = null, no = null, tax = null;
        String sub = "", prov = "", city = "", post = "";
        String sqlCompany = "SELECT c.Name, c.Address, c.Subprovince,"
                + " c.Province, c.City, c.POST, c.Tel, c.Fax, c.Tax"
                + " FROM company c";

        try {
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                rs1 = mysql.getConnection().createStatement().executeQuery(sqlCompany);
                if (rs1 != null) {
                    if (rs1.next()) {
                        comName = rs1.getString("c.Name");
                        address = rs1.getString("c.Address");
                        sub = rs1.getString("c.Subprovince");
                        prov = rs1.getString("c.Province");
                        city = rs1.getString("c.City");
                        post = rs1.getString("c.POST");
                        comTel = rs1.getString("c.Tel");
                        comFax = rs1.getString("c.Fax");
                        tax = rs1.getString("c.Tax");
                        address += sub + prov + city + post;
                    }
                }
                rs1.close();
            } catch (Exception e) {
                MSG.NOTICE(e.toString());
            }

            String sql = "SELECT *  FROM invcashdoc  WHERE invNo = '" + vatNo + "' ;";
            String[] hrecive = new String[4];
            ResultSet rs;
            int check = 0;
            String amoutlist = "";
            String amount = "";
            String cashPay = "";
            String crPay = "";
            String crNo = "";
            String cupon = "";
            String onDate = "";
            try {
                rs = mysql.getConnection().createStatement().executeQuery(sql);
                if (rs != null) {
                    if (rs.next()) {
                        onDate = rs.getString("InvDate");
                        amount = rs.getString("Amount");
                        cashPay = rs.getString("CashPay");
                        crPay = rs.getString("CrPay");
                        crNo = rs.getString("CrNo");
                        cupon = rs.getString("Cupon");
                        check = 1;
                    }
                }

                rs.close();
            } catch (Exception e) {
                MSG.NOTICE(e.toString());
                
            }

            if (check > 0) {

                String sqlBranch = "SELECT * FROM branch ";
                String branchName = "";
                try {
                    rs = stmt.executeQuery(sqlBranch);
                    if (rs != null) {
                        if (rs.next()) {
                            branchName = rs.getString("Name");
                        }
                    }
                } catch (Exception e) {

                }
                  File file = new File("C:/spapplication/application/report/inVat.jrxml");               
                try {

                    Float cp = Float.parseFloat(cashPay);
                    Float crp = Float.parseFloat(crPay);
                    Float cup = Float.parseFloat(cupon);

                    Map parameters = new HashMap();

                    parameters.put("companyName", comName);
                    parameters.put("address", address);
                    parameters.put("companyTel", comTel);
                    parameters.put("companyFax", comFax);
                    parameters.put("no", no);
                    parameters.put("vatNo", tax);
                    parameters.put("branVat", branchName);

                    convertToChar convert = new convertToChar();
                    parameters.put("priceString", "(" + convert.convertNumberToChar(amount) + ")");

                    if (cp > 0 && crp > 0 && cup > 0) {
                        parameters.put("cash", "X");
                        parameters.put("credit", "X");
                        parameters.put("gift", "X");
                        parameters.put("creditNo", crNo);
                    } else if (cp > 0 && crp <= 0 && cup <= 0) {
                        parameters.put("cash", "X");
                        parameters.put("credit", "");
                        parameters.put("gift", "");
                        parameters.put("creditNo", "");
                    } else if (cp > 0 && crp > 0 && cup <= 0) {
                        parameters.put("cash", "X");
                        parameters.put("credit", "X");
                        parameters.put("gift", "");
                        parameters.put("creditNo", crNo);
                    } else if (cp > 0 && crp <= 0 && cup > 0) {
                        parameters.put("cash", "X");
                        parameters.put("credit", "");
                        parameters.put("gift", "X");
                        parameters.put("creditNo", "");
                    } else if (cp <= 0 && crp > 0 && cup > 0) {
                        parameters.put("cash", "");
                        parameters.put("credit", "X");
                        parameters.put("gift", "X");
                        parameters.put("creditNo", crNo);
                    } else if (cp <= 0 && crp <= 0 && cup > 0) {
                        parameters.put("cash", "");
                        parameters.put("credit", "");
                        parameters.put("gift", "X");
                        parameters.put("creditNo", "");
                    } else if (cp <= 0 && crp > 0 && cup <= 0) {
                        parameters.put("cash", "");
                        parameters.put("credit", "X");
                        parameters.put("gift", "");
                        parameters.put("creditNo", crNo);
                    }

                    parameters.put("docNo", vatNo);
                    parameters.put("branchName", branchName);
                    parameters.put("vatNo", vatNo);
                    parameters.put("amoutlist", amoutlist);
                    parameters.put("docDate", convertDate(onDate));
                    parameters.put("remark", hrecive[1]);
                    JasperReport jasperReport = null;
                    try {
                        jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/file/inVat.jasper"));
                    } catch (Exception e) {
                        MSG.ERR(null, e.toString());
                    }

                    /**
                     * * OPEN CONNECTION **
                     */
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                    JasperViewer v = new JasperViewer(jasperPrint, false);
                    JDialog j = new JDialog(new JFrame(), true);
                    j.setTitle("Print");
                    j.setSize(1024, 768);
                    j.getContentPane().add(v.getContentPane());
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    v.setTitle("Report...");
                    mysql.close();
                } catch (Exception e) {
                    MSG.ERR(e.getMessage());
                }
            } else {
                MSG.ERR(null, "ไมพบข้อมูลที่ต้องการพิมพ์");
            }
            mysql.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }

    }

    public void printReportIVat(String vatNo) {
        ResultSet rs1;
        String comName = null, address = null, comTel = null, comFax = null, no = null, tax = null;
        String sub = "", prov = "", city = "", post = "";
        String sqlCompany = "SELECT c.Name, c.Address, c.Subprovince,"
                + " c.Province, c.City, c.POST, c.Tel, c.Fax, c.Tax"
                + " FROM company c";
        try {

            rs1 = stmt.executeQuery(sqlCompany);
            if (rs1 != null) {
                if (rs1.next()) {
                    comName = rs1.getString("c.Name");
                    address = rs1.getString("c.Address");
                    sub = rs1.getString("c.Subprovince");
                    prov = rs1.getString("c.Province");
                    city = rs1.getString("c.City");
                    post = rs1.getString("c.POST");
                    comTel = rs1.getString("c.Tel");
                    comFax = rs1.getString("c.Fax");
                    tax = rs1.getString("c.Tax");
                    address += sub + prov + city + post;
                }
            }
            rs1.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }

        String sql = "SELECT *  FROM invcashdoc  WHERE invNo = '" + vatNo + "' ;";
        ResultSet rs;
        int check = 0;
        String onDate = "";
        String CustCode = "";
        String CustName = "";
        String CustAddr = "";
        String CustTel = "";
        String CustFax = "";
        String dueDate = "";
        String crTerm = "";
        String OnTime = "";
        String MacNo = "";
        String RegNo = "";
        String RefNo = "";
        String Cashier = "";
        String discount = "";
        String earnest = "";
        String service = "";
        String subtotal = "";
        String vat = "";
        String amount = "";
        String ramark = "";
        try {
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                if (rs.next()) {
                    onDate = rs.getString("InvDate");
                    CustCode = rs.getString("CustCode");
                    CustName = rs.getString("CustName");
                    CustAddr = rs.getString("CustAddr1");
                    CustTel = rs.getString("CustTel");
                    CustFax = rs.getString("CustFax");
                    dueDate = rs.getString("DueDate");
                    crTerm = rs.getString("CrTerm");
                    OnTime = rs.getString("OnTime");
                    MacNo = rs.getString("MacNo");
                    RegNo = rs.getString("RegNo");
                    RefNo = rs.getString("RefNo");
                    Cashier = rs.getString("Cashier");
                    discount = rs.getString("Discount");
                    earnest = rs.getString("Earnest");
                    service = rs.getString("Service");
                    subtotal = rs.getString("Subtotal");
                    vat = rs.getString("Vat");
                    amount = rs.getString("Amount");
                    ramark = rs.getString("Remark");
                    check = 1;
                }
            }
            rs.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }

        if (check > 0) {

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
            } catch (Exception e) {
                MSG.NOTICE(e.toString());
            }
            try {

                Map parameters = new HashMap();

                parameters.put("companyName", comName);
                parameters.put("address", address);
                parameters.put("companyTel", comTel);
                parameters.put("companyFax", comFax);
                parameters.put("branVat", branchName);
                convertToChar convert = new convertToChar();
                parameters.put("priceString", "(" + convert.convertNumberToChar(amount) + ")");

                parameters.put("docNo", vatNo);
                parameters.put("branchName", branchName);
                parameters.put("vatNo", vatNo);
                parameters.put("docDate", convertDate(onDate));
                parameters.put("remark", ramark);
                dueDate = convertDate(dueDate);

                parameters.put("onDate", onDate);
                parameters.put("CustCode", CustCode);
                parameters.put("CustName", CustName);
                parameters.put("CustAddr", CustAddr);
                parameters.put("CustTel", CustTel);
                parameters.put("CustFax", CustFax);
                parameters.put("dueDate", dueDate);
                parameters.put("crTerm", crTerm);
                parameters.put("OnTime", OnTime);
                parameters.put("MacNo", MacNo);
                parameters.put("RegNo", RegNo);
                parameters.put("RefNo", RefNo);
                parameters.put("Cashier", Cashier);

                Float amt = Float.parseFloat(amount);
                Float dis = Float.parseFloat(discount);
                Float ear = Float.parseFloat(earnest);
                Float ser = Float.parseFloat(service);
                Float subt = Float.parseFloat(subtotal);
                Float vt = Float.parseFloat(vat);

                parameters.put("discount", doubleFmt.format(dis));
                parameters.put("earnest", doubleFmt.format(ear));
                parameters.put("service", doubleFmt.format(ser));
                parameters.put("subtotal", doubleFmt.format(subt));
                parameters.put("vat", doubleFmt.format(vt));
                parameters.put("amount", doubleFmt.format(amt));
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/file/debtVat.jasper"));
                /**
                 * * OPEN CONNECTION **
                 */
                MySQLConnect mysql = new MySQLConnect();
                mysql.open();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");
                mysql.close();
            } catch (Exception e) {
                MSG.ERR(e.getMessage());
            }
        } else {
            MSG.ERR(null, "ไมพบข้อมูลที่ต้องการพิมพ์");
        }
    }

    public void printReportPVatDaily(String str, String end) {
        ResultSet rs;
        String date1 = "";
        String date2 = "";
        String header = "รายงานการพิมพ์ใบกำกับภาษี / ใบเสร็จรับเงิน ประจำวันที่ " + str + " - " + end;
        String sqlBranch = "SELECT * FROM branch ";
        String branchName = "";
        try {
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
            } catch (Exception e) {
            }
            Date dates = new Date();
            try {
                dates = outFmt.parse(str);
                date1 = inFmt.format(dates);

                dates = outFmt.parse(end);
                date2 = inFmt.format(dates);
            } catch (Exception e) {
            }
            try {
                Map parameters = new HashMap();
                parameters.put("header", header);
                parameters.put("branchName", branchName);
                parameters.put("date1", date1);
                parameters.put("date2", date2);

                JasperReport jasperReport = null;
                try {
                    jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/file/PVatDaily.jasper"));
                } catch (Exception e) {
                    MSG.ERR(null, e.toString());
                }

                /**
                 * * OPEN CONNECTION **
                 */
//                MySQLConnect mysql = new MySQLConnect();
//                mysql.open();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                int pageSize = jasperPrint.getPages().size();
                if (pageSize > 0) {
                    JasperViewer v = new JasperViewer(jasperPrint, false);
                    JDialog j = new JDialog(new JFrame(), true);
                    j.setTitle("Print");
                    j.setSize(1024, 768);
                    j.getContentPane().add(v.getContentPane());
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    v.setTitle("Report...");

                } else {
                    MSG.ERR(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
                }

                mysql.close();
            } catch (Exception e) {
                MSG.ERR(e.getMessage());
            }
            mysql.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }

    }

    public void printReportIVatDaily(String str, String end) {
        ResultSet rs;
        String date1 = "";
        String date2 = "";
        String header = "รายงานการพิมพ์ใบกำกับภาษี / ใบแจ้งหนี้ ประจำวันที่ " + str + " - " + end;
        String sqlBranch = "SELECT * FROM branch ";
        String branchName = "";
        try {
            rs = stmt.executeQuery(sqlBranch);
            if (rs != null) {
                if (rs.next()) {
                    branchName = rs.getString("Name");
                }
            }
        } catch (Exception e) {
        }
        Date dates = new Date();
        try {
            dates = outFmt.parse(str);
            date1 = inFmt.format(dates);

            dates = outFmt.parse(end);
            date2 = inFmt.format(dates);
        } catch (Exception e) {
        }
        try {
            Map parameters = new HashMap();
            parameters.put("header", header);
            parameters.put("branchName", branchName);
            parameters.put("date1", date1);
            parameters.put("date2", date2);

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/file/IVatDaily.jasper"));
            } catch (Exception e) {
                MSG.ERR(null, e.toString());
            }

            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
            int pageSize = jasperPrint.getPages().size();
            if (pageSize > 0) {
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");

            } else {
                MSG.ERR(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
            }

            mysql.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }

    }

    public String convertDate(String convert) {
        String output = "";
        try {
            Date date = new Date();
            date = inFmt.parse(convert);
            output = outFmt.format(date);

        } catch (Exception e) {
        }
        return output;
    }

    public void printCompile() {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Map parameters = new HashMap();
            parameters.put("branchName", "");
            parameters.put("month", "");
            parameters.put("sql", "");
            parameters.put("tax", "");
            parameters.put("companyName", "");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/file/stockInhand_balanceVender.jasper"));

            JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
        } catch (JRException ex) {
            Logger.getLogger(ViewReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        mysql.close();
    }

    public static void main(String args[]) {
        ViewReport view = new ViewReport();
        view.printReportIVatDaily("01/07/2005", "04/07/2009");
        //  view.p("1001");
        // view.printReportTranin("211046");
        // view.printReportTranout("22/11/46");
        //view.printReportProLost("14/5/50"); 
        // view.printReportProFree("16/9/47");
        // view.printReportIVat("P549000001");
        ////211046
    }
}
