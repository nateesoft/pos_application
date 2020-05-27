package test;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JComponent;

public class BasicPrint extends JComponent implements Printable {

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {

        g.setFont(new Font("Tahoma", Font.PLAIN, 11));
        g.drawString("นที สังข์ทองงาม", 0, 10);
        
        return Printable.PAGE_EXISTS;
    }

    public static void main(String[] args) {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat pf = pjob.defaultPage();
        pjob.setPrintable(new BasicPrint(), pf);
        try {
            pjob.print();
        } catch (PrinterException e) {
            System.out.println(e.getMessage());
        }
    }
}
