package test;

import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintBook {

    public static void main(String[] args) {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        Book book = new Book();

        PageFormat landscape = pjob.defaultPage();
        landscape.setOrientation(PageFormat.LANDSCAPE);
        book.append(new Printable1(), landscape);

        PageFormat portrait = pjob.defaultPage();
        portrait.setOrientation(PageFormat.PORTRAIT);
        book.append(new Printable2(), portrait, 5);

        pjob.setPageable(book);
        try {
            pjob.print();
        } catch (PrinterException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Printable1 implements Printable {

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        drawGraphics(g, pf);
        return Printable.PAGE_EXISTS;
    }

    public void drawGraphics(Graphics g, PageFormat pf) {
            
    }
}

class Printable2 implements Printable {

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        drawGraphics(g, pf);
        return Printable.PAGE_EXISTS;
    }

    public void drawGraphics(Graphics g, PageFormat pf) {
           
    }

}
