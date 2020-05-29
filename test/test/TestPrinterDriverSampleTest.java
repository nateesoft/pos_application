package test;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.softpos.main.utils.MSG;

public class TestPrinterDriverSampleTest {

    static MessageFormat head = new MessageFormat("");
    static MessageFormat foot = new MessageFormat("");

    public static void main(String[] args){
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
        
        PrinterJob pj = PrinterJob.getPrinterJob();
        if(pj.printDialog()) {
            try {
                JEditorPane text = new JEditorPane("text/html", "text");
//            text.setText("Return this page to Shyarmal.");
                text.read(new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/a.html")))), "");
                text.repaint();
                pj.setPrintable(text.getPrintable(head, foot));
                try {
                    pj.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(TestPrinterDriverSampleTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("done .............. ");
            } catch (IOException ex) {
                Logger.getLogger(TestPrinterDriverSampleTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
