package test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PrintTest implements Printable{
    private PrintService[] printService;  
    private String text;
    
    public PrintTest(){
        this.printService = PrinterJob.lookupPrintServices();
    }
    
    public static void main(String[] args) {
        try {
            JTextArea text = new JTextArea("นที สังข์ทองงาม\nทดสอบการพิมพ์ผ่าน Driver นะครับ\nNatee Sungthongngam!");
            text.print();
        } catch (PrinterException ex) {
            Logger.getLogger(PrintTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printString(String input) {
  
        this.text = input;
          
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();  
        aset.add(new PageRanges(1, 1));
        aset.add(new Copies(1));  
          
        PrinterJob printJob = PrinterJob.getPrinterJob();  
        printJob.setPrintable(this);
        
        int index = 0;
        for(int i=0;i<printService.length;i++){
            if(printService[i].getName().equals("KIC1")){
                index = i;
            }
        }
  
        try {
            printJob.setPrintService(printService[index]);
            //index of installed printers on you system  
            //not sure if default-printer is always '0'  
            printJob.print(aset);
            String textInput = "ทดสอบนะครับทดสอบ";
            JTextArea textArea = new JTextArea(textInput);
            try {
                JTextField header = new JTextField("HEADER");
                JTextField footer = new JTextField("HEADER");
                MessageFormat h = createFormat(header);
                MessageFormat f = createFormat(footer);
                textArea.print(h, f, false, printService[index], aset, true);
                
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }

        } catch (PrinterException err) {
            System.err.println(err);
        }
    }
    
    private MessageFormat createFormat(JTextField source) {
        String textIn = source.getText();
        if (textIn != null && textIn.length() > 0) {
            try {
                return new MessageFormat(textIn);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                //error("Sorry, this format is invalid.");
            }
        }
        return null;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;  
        g2.translate(pf.getImageableX(), pf.getImageableY());  
        g.drawString(String.valueOf(this.text), 14, 14);  
        
        return PAGE_EXISTS;
        
    }
}
