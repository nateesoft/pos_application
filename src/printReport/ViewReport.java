package printReport;

import database.MySQLConnect;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.MSG;

public final class ViewReport {

    private DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");
    private SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public void printReportReceive(String R_no) {

        String sql = "SELECT hrecive.R_Date,hrecive.R_Remark,hrecive.R_Bran,factory.FactoryName "
                + "FROM hrecive INNER JOIN factory ON hrecive.R_Bran = factory.FactoryCode "
                + "WHERE R_No = '" + R_no + "' ;";

        String[] hrecive = new String[4];
        ResultSet rs;
        int check = 0;
        String amoutlist = "";
        String amoutprice = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    hrecive[0] = rs.getString("hrecive.R_Date");
                    hrecive[1] = rs.getString("hrecive.R_Remark");
                    hrecive[2] = rs.getString("hrecive.R_Bran");
                    hrecive[3] = rs.getString("factory.FactoryName");
                }
                check = 1;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {
            String sqlCount = "SELECT count(R_No) as row,sum(R_Amount) as total "
                    + "FROM recive WHERE R_No = '" + R_no + "'";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlCount);
                if (rs != null) {
                    while (rs.next()) {
                        amoutlist = rs.getString("row");
                        amoutprice = rs.getString("total");
                    }
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            File file = new File("/root/report/receive.jrxml");
            try {

                String date = convertDate(hrecive[0]);
                Float p = Float.parseFloat(amoutprice);
                String price = doubleFmt.format(p);
                Map parameters = new HashMap();
                parameters.put("branchName", branchName);
                parameters.put("rno", R_no);
                parameters.put("amoutlist", amoutlist);
                parameters.put("totalprice", price);
                parameters.put("date", date);
                parameters.put("remark", hrecive[1]);
                parameters.put("fac", hrecive[2] + "   " + hrecive[3]);
                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }

        mysql.close();
    }

    public void printReportTranin(String R_no) {

        String sql = "SELECT distinct htranin.R_Date,htranin.R_Remark,htranin.R_Bran,branfile.Name "
                + "FROM htranin INNER JOIN branfile ON htranin.R_Bran = branfile.Code "
                + "WHERE R_No = '" + R_no + "' ;";
        String[] hrecive = new String[4];
        ResultSet rs;
        int check = 0;
        String amoutlist = "";
        String amoutprice = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    hrecive[0] = rs.getString("htranin.R_Date");
                    hrecive[1] = rs.getString("htranin.R_Remark");
                    hrecive[2] = rs.getString("htranin.R_Bran");
                    hrecive[3] = rs.getString("branfile.Name");
                }
                check = 1;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {

            String sqlCount = "SELECT count(R_No) as row,sum(R_Amount) as total "
                    + "FROM tranin WHERE R_No = '" + R_no + "'";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlCount);
                if (rs != null) {
                    while (rs.next()) {
                        amoutlist = rs.getString("row");
                        amoutprice = rs.getString("total");
                    }
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            File file = new File("/root/report/tranin.jrxml");
            try {
                String date = convertDate(hrecive[0]);
                Float p = Float.parseFloat(amoutprice);
                String price = doubleFmt.format(p);
                Map parameters = new HashMap();
                parameters.put("branchName", branchName);
                parameters.put("rno", R_no);
                parameters.put("amoutlist", amoutlist);
                parameters.put("amoutprice", price);
                parameters.put("date", date);
                parameters.put("remark", hrecive[1]);
                parameters.put("fac", hrecive[2] + "    " + hrecive[3]);
                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }

        mysql.close();
    }

    public void printReportTranout(String R_no) {

        String sql = "SELECT distinct htranout.R_Date,htranout.R_Remark,htranout.R_Bran,branfile.Name "
                + "FROM htranout INNER JOIN branfile ON htranout.R_Bran = branfile.Code "
                + "WHERE R_No = '" + R_no + "' ;";
        String[] hrecive = new String[4];
        ResultSet rs;
        int check = 0;
        String amoutlist = "";
        String amoutprice = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    hrecive[0] = rs.getString("htranout.R_Date");
                    hrecive[1] = rs.getString("htranout.R_Remark");
                    hrecive[2] = rs.getString("htranout.R_Bran");
                    hrecive[3] = rs.getString("branfile.Name");
                }
                check = 1;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {
            String sqlCount = "SELECT count(R_No) as row,sum(R_Amount) as total "
                    + "FROM tranout WHERE R_No = '" + R_no + "'";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlCount);
                if (rs != null) {
                    while (rs.next()) {
                        amoutlist = rs.getString("row");
                        amoutprice = rs.getString("total");
                    }
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            File file = new File("/root/report/tranout.jrxml");
            try {
                String date = convertDate(hrecive[0]);
                Float p = Float.parseFloat(amoutprice);
                String price = doubleFmt.format(p);
                Map parameters = new HashMap();
                parameters.put("branchName", branchName);
                parameters.put("rno", R_no);
                parameters.put("amoutlist", amoutlist);
                parameters.put("amoutprice", price);
                parameters.put("date", date);
                parameters.put("remark", hrecive[1]);
                parameters.put("fac", hrecive[2] + "    " + hrecive[3]);
                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }

        mysql.close();
    }

    public void printReportProLost(String R_no) {

        String sql = "SELECT distinct(h.R_No), h.R_Date, h.R_Remark, h.R_Total FROM hprolost h "
                + "WHERE R_No = '" + R_no + "' ;";
        String[] hrecive = new String[4];
        ResultSet rs;
        int check = 0;
        String amoutlist = "";
        String amoutprice = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    hrecive[0] = rs.getString("h.R_Date");
                    hrecive[1] = rs.getString("h.R_Remark");
                    hrecive[2] = rs.getString("h.R_Total");
                    check = 1;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {

            String sqlCount = "SELECT count(R_No) as row,sum(R_Amount) as total "
                    + "FROM prolost WHERE R_No = '" + R_no + "'";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlCount);
                if (rs != null) {
                    while (rs.next()) {
                        amoutlist = rs.getString("row");
                        amoutprice = rs.getString("total");
                    }
                }

                rs.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            File file = new File("/root/report/lose.jrxml");
            try {

                String date = convertDate(hrecive[0]);
                Float p = Float.parseFloat(amoutprice);
                String price = doubleFmt.format(p);
                Map parameters = new HashMap();
                parameters.put("branchName", branchName);
                parameters.put("rno", R_no);
                parameters.put("amoutlist", amoutlist);
                parameters.put("amoutprice", price);
                parameters.put("date", date);
                parameters.put("remark", hrecive[1]);

                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }

        mysql.close();
    }

    public void printReportProFree(String R_no) {

        String sql = "SELECT distinct(h.R_No), h.R_Date, h.R_Remark FROM hcharge  h  "
                + "WHERE R_No = '" + R_no + "' ;";
        String[] hrecive = new String[4];
        ResultSet rs;
        int check = 0;
        String amoutlist = "";
        String amoutprice = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    hrecive[0] = rs.getString("h.R_Date");
                    hrecive[1] = rs.getString("h.R_Remark");
                    check = 1;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {

            String sqlCount = "SELECT count(R_No) as row,sum(R_Amount) as total "
                    + "FROM charge  WHERE R_No = '" + R_no + "'";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlCount);
                if (rs != null) {
                    while (rs.next()) {
                        amoutlist = rs.getString("row");
                        amoutprice = rs.getString("total");
                    }
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }

            File file = new File("/root/report/free.jrxml");
            try {

                String date = convertDate(hrecive[0]);
                Float p = Float.parseFloat(amoutprice);
                String price = doubleFmt.format(p);
                Map parameters = new HashMap();
                parameters.put("branchName", branchName);
                parameters.put("rno", R_no);
                parameters.put("amoutlist", amoutlist);
                parameters.put("amoutprice", price);
                parameters.put("date", date);
                parameters.put("remark", hrecive[1]);
                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }

        mysql.close();
    }

    public void printReportPVat(String vatNo) {
        ResultSet rs1;
        String comName = null, address = null, comTel = null, comFax = null, no = null, tax = null;
        String sub = "", prov = "", city = "", post = "";
        String sqlCompany = "SELECT c.Name, c.Address, c.Subprovince,"
                + " c.Province, c.City, c.POST, c.Tel, c.Fax, c.Tax"
                + " FROM mycpssysutf.company c";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
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
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
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
            Statement stmt = mysql.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
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
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            File file = new File("/root/report/inVat.jrxml");
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

                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }

        mysql.close();
    }

    public void printReportNVat(String vatNo) {
        ResultSet rs1;

        String comName = null, address = null, comTel = null, comFax = null, no = null, tax = null;
        String sub = "", prov = "", city = "", post = "";
        String sqlCompany = "SELECT c.Name, c.Address, c.Subprovince,"
                + " c.Province, c.City, c.POST, c.Tel, c.Fax, c.Tax"
                + " FROM mycpssysutf.company c";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
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
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
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
            Statement stmt = mysql.getConnection().createStatement();
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
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }

        if (check > 0) {

            String sqlBranch = "SELECT * FROM branch ";
            String branchName = "";
            try {
                Statement stmt = mysql.getConnection().createStatement();
                rs = stmt.executeQuery(sqlBranch);
                if (rs != null) {
                    if (rs.next()) {
                        branchName = rs.getString("Name");
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
            }
            File file = new File("/root/report/debtVat.jrxml");
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

                JasperReport jasperReport = JasperCompileManager.compileReport(file.toString());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mysql.getConnection());
                JasperViewer.viewReport(jasperPrint, true);

            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
                
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
                
            }
        } else {
            MSG.WAR("ไมพบข้อมูลที่ต้องการพิมพ์");
        }
        
        mysql.close();
    }

    public String convertDate(String convert) {
        String output = "";
        try {
            Date date = inFmt.parse(convert);
            output = outFmt.format(date);

        } catch (ParseException e) {
            MSG.ERR(e.getMessage());
        }
        return output;
    }
}
