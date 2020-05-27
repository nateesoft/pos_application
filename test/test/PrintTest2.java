package test;

import java.awt.*;
import java.awt.geom.*;
import java.awt.print.*;

public class PrintTest2 {

    public static void main(String args[]) {
        MyPagePainter painter = new MyPagePainter();
        PrinterJob pjob = PrinterJob.getPrinterJob();

        PageFormat fmt = pjob.pageDialog(pjob.defaultPage());
        pjob.setPrintable(painter, fmt);

        if (pjob.printDialog()) {
            try {
                pjob.print();
            } catch (PrinterException ex) {
                System.err.println(ex);
            }
        } else {
            System.err.println("PrintJob canceled");
        }
    }
}

class MyPagePainter implements Printable {

    @Override
    public int print(Graphics g, PageFormat fmt, int pageIndex) {
        if (pageIndex > 1) {
            return Printable.NO_SUCH_PAGE;
        } else {
            double pw = fmt.getWidth();
            double ph = fmt.getHeight();
            double ix = fmt.getImageableX();
            double iy = fmt.getImageableY();
            double iw = fmt.getImageableWidth();
            double ih = fmt.getImageableHeight();

            Graphics2D g2d = (Graphics2D) g;

            g2d.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f,
                    null, 0.0f));
            Rectangle2D rect1 = new Rectangle2D.Double(0, 0, pw, ph);
            Rectangle2D rect2 = new Rectangle2D.Double(ix, iy, iw, ih);
            Rectangle2D rect3 = new Rectangle2D.Double(ix + 100, iy + 100, iw - 200, ih - 200);
            g2d.setColor(Color.black);
            g2d.draw(rect1);
            g2d.setColor(Color.blue);
            g2d.draw(rect2);
            g2d.setColor(Color.green);
            g2d.draw(rect3);

            g2d.setColor(Color.black);
            g2d.setFont(new Font("MS Gothic", Font.PLAIN, 20));
            g2d.drawString("?$@$3$s$K$A$O?(B", (int) ix + 10, (int) iy + 30);
            g2d.setFont(new Font("MS Mincho", Font.PLAIN, 20));
            g2d.drawString("?$@$3$s$K$A$O?(B", (int) ix + 10, (int) iy + 60);

            return Printable.PAGE_EXISTS;
        }
    }
}
