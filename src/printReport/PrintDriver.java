package printReport;

import database.MySQLConnect;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.softpos.main.program.BalanceBean;
import com.softpos.main.program.BalanceControl;
import com.softpos.main.program.PublicVar;
import com.softpos.main.program.SendTerminalReportAuto;
import com.softpos.main.program.Value;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateConvert;
import util.MSG;

public class PrintDriver {

    private String textAll = "";
    private String textNormal = "";
//    private final String header = "<html><head></head><body><table border=0 cellpadding=0 cellspaceing=0 width=100 height=50>";
    private final String header = "<html><head></head><body><table border=0 cellpadding=0 cellspaceing=0 width=100 height=50>";
    private final String footer = "</table></body></html>";
    private final String fontName = "Angsana New";
    private float width = 80;
    private float height = 72;

    public PrintDriver() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
//            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
//            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
//            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
//            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setResolution(float w, float h) {
        this.width = w;
        this.height = h;
    }

    public String getPrinterName() {
        return Value.printerDriverName;
    }

    public void setPrinterName(String printerName) {
        Value.printerDriverName = printerName;
    }

    public void addImage(String path) {
        textAll += "<img src=\"" + path + "\" width=200 height=200></img>";
        textNormal += "...";
    }

    public void addText(String str, String size) {
        textAll += "<font face=" + fontName + " size=" + size + ">" + str + "</font>";
        textNormal += str;
    }

    public void addTextIFont(String str) {
        textAll += "<tr><td " + str + "</font></td></tr>";
        textNormal += str + "\n";
    }

    public void addTextLn(String str, String size) {
        textAll += "<font face=" + fontName + " size=" + size + ">" + str + "</font><br>";
        textNormal += str + "\n";
    }

    public void addText(String str) {
        String[] datas = str.split("=", str.length());
        for (int i = 0; i <= str.length(); i++) {

        }
        String[] temps = new String[]{"", "", ""};
        System.arraycopy(datas, 0, temps, 0, datas.length);
        textAll += "<tr><td><font face=" + fontName + " size=0>" + str + "</font></td></tr>";
//        textAll += str + "\n";
        textNormal += str;
    }

    public void addTextLn(String str) {
        textAll += "<tr><td><font face=" + fontName + " size=-1>" + str + "</font><br></td></tr>";
        textNormal += str + "\n";
    }

    public void printVoid(String R_Table) {
        BalanceControl bControl = new BalanceControl();
        ArrayList<BalanceBean> list = bControl.getBalanceIndexVoid(R_Table);
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BalanceBean bean = (BalanceBean) list.get(i);
            addTextLn("โต๊ะ " + R_Table + "");
            addTextLn("*** ยกเลิกรายการ ***");
            addTextLn("(" + bean.getR_Opt9() + ")");
            addTextLn("UserVoid : " + bean.getCashier());
            addTextLn("");
            String ETD = bean.getR_ETD();
            if (ETD.equals("E")) {
                addTextLn("*** EAT-IN ***");
            } else if (ETD.equals("T")) {
                addTextLn("*** Takeaway ***");
            } else if (ETD.equals("D")) {
                addTextLn("*** Delivery ***");
            } else if (ETD.equals("P")) {
                addTextLn("*** Pinto ***");
            } else if (ETD.equals("W")) {
                addTextLn("*** Wholesale ***");
            }
            addTextLn(bean.getR_PName());
            addTextLn("จำนวน  " + (bean.getR_Quan() * -1) + "  " + "ราคา  " + bean.getR_Price());
            addTextLn("----------------------");

            SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
            addTextLn(simp.format(new Date()) + " " + bean.getMacno() + "/");

            setPrinterName("KIC" + bean.getR_Kic());//set printer sample KIC1
            printKichen();

            //update r_kicprint
            /**
             * * OPEN CONNECTION **
             */
            MySQLConnect mysql = new MySQLConnect();
            mysql.open();
            try {
                String sql = "update balance "
                        + "set r_kicprint='P' "
                        + "where r_index='" + bean.getR_Index() + "' "
                        + "and r_table='" + bean.getR_Table() + "'";
                Statement stmt = mysql.getConnection().createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException e) {
                MSG.ERR(null, e.getMessage());
                e.printStackTrace();
            } finally {
                mysql.close();
            }
        }

        close();
    }

    public void printHTML() {
        //Print Cashier
        String text = header + textAll + footer;

        try {
            JEditorPane editor = new JEditorPane();
            editor.setContentType("text/html");
            editor.setText(text);

            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f, width, height, MediaPrintableArea.INCH));

            editor.print(null, null, false, getPrinter(), attr, false);
        } catch (PrinterException ex) {
            MSG.ERR(ex.getMessage());
        }
        close();
    }

    public void printHTMLIntoFile() throws FileNotFoundException, UnsupportedEncodingException {
        //Print Cashier
        String header1 = "<html><head><meta charset=\"UTF-8\"></head><body><table align='center' border=0 cellpadding=0 cellspaceing=0 width=180 height=50>";
        String footer1 = "</table></body></html>";
        String text = header1 + textAll + footer1;
        BufferedWriter output = null;
        DateConvert dc = new DateConvert();
        try {
            SendTerminalReportAuto tm = new SendTerminalReportAuto();
            String filename = dc.dateGetToShow(dc.GetCurrentDate()).replace("/", "").replace(" ", "");
            String path = "D:/DailySales/" + filename + "MTD.html";

//            tm.filePath = path;
//            tm.fileName = path;
            PublicVar.filePath = path;
            File file = new File(path);
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(PrintDriver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void printHTMLKitChen() {
        //Print Cashier
        String text = header + textAll + footer;
        
        try {
            JEditorPane editor = new JEditorPane();
            editor.setContentType("text/html");
            editor.setText(text);

            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f, width, height, MediaPrintableArea.INCH));

            editor.print(null, null, false, getPrinterKitchen(), attr, false);
        } catch (PrinterException ex) {
            ex.printStackTrace();
//            MSG.ERR(ex.getMessage());
        }
        close();
    }

    public void printHTMLOrder() {
        //Print Cashier
        String text = header + textAll + footer;

        try {
            JEditorPane editor = new JEditorPane();
            editor.setContentType("text/html");
            editor.setText(text);

            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f, width, height, MediaPrintableArea.INCH));

            editor.print(null, null, false, getPrinterKitchen(), attr, false);
        } catch (PrinterException ex) {
            MSG.ERR(ex.getMessage());
        }
        close();
    }

    public void printNormal() {
        try {
            JTextArea textArea = new JTextArea(textNormal);
            textArea.setFont(new Font("Tahoma", Font.PLAIN, 10));

            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f, width, height, MediaPrintableArea.INCH));

            textArea.print(null, null, false, getPrinter(), attr, false);
        } catch (PrinterException ex) {
            MSG.ERR(ex.getMessage());
        }

        close();
    }

    public void printKichen() {
        try {
            JTextArea textArea = new JTextArea(textNormal);
            textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));

            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(new MediaPrintableArea(0f, 0f, width, height, MediaPrintableArea.INCH));

            textArea.print(null, null, false, getPrinter(), attr, false);
        } catch (PrinterException ex) {
            MSG.ERR(ex.getMessage());
        }

        close();
    }

    private PrintService getPrinter() {
        PrintService[] printService = PrinterJob.lookupPrintServices();

        for (PrintService printService1 : printService) {
            if (printService1.getName().equals(Value.printerDriverName)) {
                return printService1;
            }
        }

        return printService[0];
    }

    private PrintService getPrinterKitchen() {
        PrintService[] printService = PrinterJob.lookupPrintServices();

        for (PrintService printService1 : printService) {
            if (printService1.getName().equals(Value.printerDriverKitChenName)) {
                return printService1;
            }
        }

        return printService[0];
    }

    public void close() {
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
    }

    public Image addTextToImage(BufferedImage bufferImage, String[] text) {
        final int VERTICLE_PADDING_PIXELS = 5;
        final int LEFT_MARGIN_PIXELS = 5;
        FontMetrics fm = bufferImage.createGraphics().getFontMetrics();
        int ww = bufferImage.getWidth();
        int hh = bufferImage.getHeight() + (text.length * (fm.getHeight() + VERTICLE_PADDING_PIXELS));

        for (String s : text) {
            ww = Math.max(ww, fm.stringWidth(s) + LEFT_MARGIN_PIXELS);
        }

        BufferedImage result = new BufferedImage(bufferImage.getHeight(), ww, hh);
        Graphics2D g = result.createGraphics();
        g.drawImage(bufferImage, 0, 0, null);

        for (int x = 0; x < text.length; x++) {
            g.drawString(text[x], LEFT_MARGIN_PIXELS, bufferImage.getHeight() + (x + 1) * VERTICLE_PADDING_PIXELS + x * fm.getHeight());
        }

        return result;
    }

    public static void main(String[] args) {
        Value.printerDriverName = "kic1";
        PrintDriver pd = new PrintDriver();
        //pd.addText("<font size=-6>ทดสอบภาษาไทย</font>abcdefghijklmnopqrstuvwxy<i><font color=red size=-6>z1234567890</font></i>");
        String fontName1 = "Tahoma";
        fontName1 = "TIS11_3_FontA11";
//        pd.addText("<font size=-6>N-ข้าวผัดกุ้ง      1   120.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดหมูแดง   1    95.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดกุ้ง      1   120.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดหมูแดง   1    95.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดกุ้ง      1   120.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดหมูแดง   1    95.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดกุ้ง      1   120.00E</font>");
//        pd.addText("<font size=-6>N-ข้าวผัดหมูแดง   1    95.00E</font>");
        pd.addText("<font face=" + fontName1 + " size=-1>" + " &nbsp;  &nbsp;   JF1001" + "</font>");
        pd.addText("<font face=" + fontName1 + " size=1>" + " &nbsp;  &nbsp;   ซีฟู้ดพริกไทยดำกระทะร้อน" + "</font>");
        pd.addText("<font face=" + fontName1 + " size=2>" + " &nbsp;  &nbsp;   ซีฟู้ดพริกไทยดำกระทะร้อน" + "</font>");
        pd.addText("<font face=" + fontName1 + " size=1>" + " &nbsp;  &nbsp;   รวมภาษีมูลค่าเพิ่มแล้ว" + "</font>");
        pd.addText("<font face=" + fontName1 + " size=2>" + " &nbsp;  &nbsp;   รวมภาษีมูลค่าเพิ่มแล้ว" + "</font>");
        pd.printHTML();

    }
}
