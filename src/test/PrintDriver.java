package test;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JEditorPane;

public class PrintDriver {

    float width = 80f;
    float height = 72f;

    private PrintService getPrinter() {
        PrintService[] printService = PrinterJob.lookupPrintServices();
        for (PrintService printService1 : printService) {
            if (printService1.getName().equals("EPSON TM-U220 Receipt")) {
                return printService1;
            }
        }
        return printService[0];
    }

    public static void main(String[] args) {
        PrintDriver pd = new PrintDriver();
        pd.printTest();
    }

    public void printTest() {
        try {
            String table = ""
                    + "<table>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td>     ข้าวผัดกุ้ง</td>"
                    + "         <td>1</td>"
                    + "         <td align=right>120.00E</td>"
                    + "     </tr>"
                    + "       <font face=Angsana New size=-2><tr><td>&emsp;N-</td>เนื้อบด </td> <td>  1  </td><td align=right> 139.00E</font></td></tr>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td>ข้าวผัดกระเพา</td>"
                    + "         <td>2</td>"
                    + "         <td align=right>250.00E</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td>มาม่าผัดขี้เมากระเพาแตะ</td>"
                    + "         <td>5</td>"
                    + "         <td align=right>50.00E</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td><font size=2 face='Tahoma'>คะน้าหมูกรอบ</font></td>"
                    + "         <td>5</td>"
                    + "         <td align=right>85.00E</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td><font size=1 face='Angsana New'>เป็ดปักกิ่ง</font></td>"
                    + "         <td>5</td>"
                    + "         <td align=right>85.00E</td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td><font size=1 face='Angsana New'>เป็ดปักกิ่ง</font></td>"
                    + "         <td>5</td>"
                    + "         <td align=right><font size=1 face='Angsana New'>85.00E</font></td>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>&emsp;N-</td>"
                    + "         <td><font size=4 face='Angsana New'>เป็ดปักกิ่ง</font></td>"
                    + "         <td>5</td>"
                    + "         <td align=right>199.00E</td>"
                    + "     </tr>"
                    + "</table>";
            JEditorPane editor = new JEditorPane();
            editor.setContentType("text/html");
            editor.setText(table);

            HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            //width = ความกว้างกระดาษที่ปริ้น
            //height = ความสูงกระดาษที่ให้ปริ้น
            attr.add(new MediaPrintableArea(0f, 0f, width, height, MediaPrintableArea.INCH));

            editor.print(null, null, false, getPrinter(), attr, false);
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
