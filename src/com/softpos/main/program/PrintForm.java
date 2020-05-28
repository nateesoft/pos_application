/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softpos.main.program;

import com.softpos.main.model.Value;
import database.MySQLConnect;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import printReport.PrintDriver;
import util.MSG;

/**
 *
 * @author Dell-Softpos
 */
public class PrintForm {

    public void printMainOrder(String printerName, String text) {
        String t = text;
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
            MSG.ERR(e.toString());
        }
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data1 : strs) {
            Value.printerDriverKitChenName = printerName;
            pd.addTextIFont(data1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                MSG.ERR(e.toString());
            }
        }

        pd.printHTMLOrder();
    }

}
