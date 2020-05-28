/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.softpos.main.model.Value;
import database.MySQLConnect;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

/**
 *
 * @author Dell-Softpos
 */
public class TestOpenDrawerDriver {

    String PrinterName = "";

    public void cashdrawerOpen() {
        try {
            MySQLConnect c = new MySQLConnect();
            c.open();
            PrinterName = Value.printerDriverName;
            c.close();
        } catch (Exception e) {
        }
        byte[] open = {27, 112, 48, 55, 121};
//        byte[] cutter = {29, 86,49};
//        String printer = PrinterName;
        String printer = PrinterName;
        PrintServiceAttributeSet printserviceattributeset = new HashPrintServiceAttributeSet();
        printserviceattributeset.add(new PrinterName(printer, null));
        PrintService[] printservice = PrintServiceLookup.lookupPrintServices(null, printserviceattributeset);
        if (printservice.length != 1) {
            System.out.println("Printer not found");
        }
        PrintService pservice = printservice[0];
        DocPrintJob job = pservice.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(open, flavor, null);
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        try {
            job.print(doc, aset);
        } catch (PrintException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        TestOpenDrawerDriver OpenDrawer = new TestOpenDrawerDriver();
        OpenDrawer.cashdrawerOpen();
    }

}
