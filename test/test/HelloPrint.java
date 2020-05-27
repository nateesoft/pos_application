package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HelloPrint extends JPanel implements Printable {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Hello Frame");

        final HelloPrint printPanel = new HelloPrint();
        printPanel.add(new JLabel("PATIENT: Doe, John"));
        printPanel.add(new JLabel("SSN: 123-45-6789 DOB: 01/01/08"));
        printPanel.add(new JLabel("ID #: T1234"));
        printPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        printPanel.setPreferredSize(new Dimension(144, 54));
        printPanel.setMinimumSize(new Dimension(144, 54));

        frame.getContentPane().add(printPanel);

        //frame.setSize(400,400);
        frame.pack();

   	   //invokeLater() is used as a workaround for a java
        //gui bug.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    frame.setVisible(true);

                    //get a PrintJob
                    PrinterJob pjob = PrinterJob.getPrinterJob();
                    //set a HelloPrint as the target to print
                    pjob.setPrintable(printPanel, pjob.defaultPage());
   				//get the print dialog, continue if canel
                    //is not clicked
                    if (pjob.printDialog()) {
                        //print the target (HelloPrint)
                        pjob.print();
                    }
                } catch (HeadlessException e) {
                    System.out.println(e.getMessage());
                } catch (PrinterException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    /**
     * We happen to implement a paint method, but this could be ignored for
     * components that already to something interesting on their own.
     */
	//public void paint(Graphics g) {
    //super.paint(g);
    //g.drawString("Hello world!", 35, 100);
    //}
    /**
     * Printable's implementation
     * @param g
     * @param pf
     */
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        // assume the page exists until proven otherwise
        int retval = Printable.PAGE_EXISTS;

		// We only want to deal with the first page.
        // The first page is numbered '0'
        if (pageIndex > 0) {
            retval = Printable.NO_SUCH_PAGE;
        } else {
            // setting up the Graphics object for printing
            g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
            // populate the Graphics object from HelloPrint's paint() method
            paint(g);
        }

        return retval;
    }
}
