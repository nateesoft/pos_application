package test;

import java.awt.print.PrinterException;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JEditorPane;
import sun.natee.project.util.ThaiUtil;

public class TextAreaPrint {

    public static void main(String[] args) {
        
//        //for jTextArea printer
//        String textPrint = "<html>1234567890\n<font size=1>1234567890</font>\n<font size=5>1234567890</font>\n<font size=3>01234567890</font></html>";
//        JTextArea text = new JTextArea(textPrint);        
//        text.setFont(new Font("Norasi", Font.PLAIN, 16));
//        try {
//            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();             
//            attr.add(new MediaPrintableArea(0f, 0f, 80f, 72f, MediaPrintableArea.INCH));
//            
//            text.print(null, null, false, null, attr, false);
//        } catch (PrinterException ex) {
//            System.out.println(ex.getMessage());
//        }
        
        String textPrint2 = "<html>"
                + "<body>"
                + "<font size=+1 face=Norasi>นที สังข์ทองงาม</font><p>"
                + "<font size=+2 face=AngsanaUPC color=red>โรงเรียนบ้านร่องตาทีวิทยา อุทัยธานี</font>"
                + "</body>"
                + "</html>";
        System.out.println(textPrint2);
        textPrint2 = ThaiUtil.ASCII2Unicode(textPrint2);
        JEditorPane pane = new JEditorPane("text/html", textPrint2);
        try {
            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();             
            attr.add(new MediaPrintableArea(0f, 0f, 80f, 72f, MediaPrintableArea.INCH));
            
            pane.print(null, null, false, null, attr, false);
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
