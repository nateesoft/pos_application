/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_utility.text_to_image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;

import java.awt.GraphicsEnvironment;
import java.util.Vector;
import java.util.Date;

import javax.imageio.ImageIO;
/*
 * Read in a text file and create PSP sized (480x272) JPEG images.
 * Rotate the text inthe images left by 90 degrees so that they can by read in portrait mode.
 * Long lines of text are wrapped to fit.
 * The images have a base name followed by 4 digits of increasing numbers.
 *
 * @author David Wheeler drwheeler@lycos.com
 *
 */

public class PSPeBookTest {

    static ArrayList images;
    static int lineHeight;
    static int width = 500;
    static int margin = 5;
    static String fontName = "Arial";
    static int fontSize = 12;
    static Date ts;

    public static void main(String[] argsT) {

        String[] args = { "C:\\1.txt","C:\\1En" ,"Arial" , "14" };

        if (args.length < 2) {
//            System.out.println("PSPeBook <Text File> <Image name base> [font name] [font size]");
            return;
        }

        if (args.length < 4) {
//            System.out.println("PSPeBook <Text File> <Image name base> [font name] [font size]");

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] fontNames = ge.getAvailableFontFamilyNames();
            Vector v = new Vector();
            for (int i = 0; i < fontNames.length; i++) {
//                System.out.println(fontNames[i]);
//           v.add(new Font(fontNames[i],Font.PLAIN,20) );
            }
            return;
        } else {
            fontName = args[2];
            fontSize = Integer.parseInt(args[3]);
        }

        ts = new Date((new Date()).getTime() - (24 * 60 * 60 * 1000));

        Graphics2D g, g1;
        FontRenderContext fc;

        String testText = "Testing";

        int fileCnt = 0;
        /*
         * Work out the height of a line of text
         */
        Font font = null;

        try {
            font = new Font(fontName, Font.PLAIN, (int) fontSize);
        } catch (Exception e) {
//            System.out.println("Error opening font file : " + e.getMessage());
        }

        BufferedImage tempBuffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        g = tempBuffer.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font);

        fc = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(testText, fc);

        float wrappingWidth = width - margin;
        lineHeight = (int) bounds.getHeight();
        /*
         * Open the input text file.
         */
        BufferedReader srcFile = null;

        try {
            srcFile = new BufferedReader(new FileReader(args[0]));
        } catch (Exception e) {
//            System.out.println("Error opening file " + args[0] + " : " + e.getMessage());
        }

        images = new ArrayList();
        int lineCnt = margin + margin;
        /*
         * Process for each line in the file.
         */
        while (true) {
            String line;

            try {
                line = srcFile.readLine();
            } catch (IOException ignore) {
                break;
            }

            if (line == null) { // End Of File
                break;
            }

            if ("".equals(line)) { // Still create an empty image line for a blank line.
                line = " ";
            }

            AttributedString attribString = new AttributedString(line);

            //attribString.addAttribute(TextAttribute.FOREGROUND, Color.BLACK, 0, line.length());
            attribString.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, line.length());
            attribString.addAttribute(TextAttribute.FONT, font, 0, line.length());

            AttributedCharacterIterator aci = attribString.getIterator();
            LineBreakMeasurer lbm = new LineBreakMeasurer(aci, fc);
            /*
             * Create an image for each line.
             */
            while (lbm.getPosition() < line.length()) {
                BufferedImage lineBuffer = new BufferedImage(width, lineHeight, BufferedImage.TYPE_INT_RGB);
                g1 = lineBuffer.createGraphics();

                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                TextLayout layout = lbm.nextLayout(wrappingWidth);
                int y = (int) layout.getAscent();

                layout.draw(g1, margin, y);

                images.add(lineBuffer);
                lineCnt += lineHeight;
                /*
                 * Check if a screen-full of lines has benn collect, and save a screen image if so.
                 */
                if (lineCnt + lineHeight > 300) {
                    try {
                        saveImage(args[1], fileCnt++);
                    } catch (IOException e) {
//                        System.out.println("Error writing image : " + e.getMessage());
                    }

                    images.clear();
                    lineCnt = margin + margin;
                }
            }
        }
        /*
         * Save the last image lines.
         */
        if (lineCnt != 0) {
            try {
                saveImage(args[1], fileCnt++);
            } catch (IOException e) {
//                System.out.println("Error writing image : " + e.getMessage());
            }
        }
    }
    /*
     * Collect all the line images into 1 big image.
     */

    static void saveImage(String fileName, int fileCnt) throws IOException {
        Graphics2D g, g1;

        BufferedImage buffer = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
        g = buffer.createGraphics();
        g.setBackground(Color.BLACK);
        //g.setBackground(Color.BLACK);
        g.clearRect(0, 0, 500, 300);

        for (int i = 0; i < images.size(); i++) {
            g.drawImage((BufferedImage) images.get(i), 0, margin + (i * lineHeight), null);
        }
        /*
         * Rotate the final image left by 90 degrees for a portrait view on the PSP.
         */
        BufferedImage rotBuffer = new BufferedImage(buffer.getHeight(), buffer.getWidth(), BufferedImage.TYPE_INT_RGB);
        g1 = rotBuffer.createGraphics();

        double theta = -1.5707963267948966192313216916398;

        g1.translate(0, buffer.getWidth());
        g1.rotate(theta);
        g1.drawImage(buffer, 0, 0, buffer.getWidth(), buffer.getHeight(), null);
        /*
         * Save the final image.
         */
//StringBuffer fullFileName = new StringBuffer(fileName).append(addLeadingZeros(fileCnt, 4)).append(".png");
        //StringBuffer fullFileName = new StringBuffer(fileName).append(addLeadingZeros(fileCnt, 4)).append(".jpg");
        StringBuffer fullFileName = new StringBuffer(fileName).append(addLeadingZeros(fileCnt, 4)).append(".gif");
        OutputStream out = new FileOutputStream(new File(fullFileName.toString()));

//ImageIO.write(rotBuffer, "png", out);
       // ImageIO.write(rotBuffer, "jpg", out);
ImageIO.write(rotBuffer, "gif", out);
        out.close();

        File imgFile = new File(fullFileName.toString());
        imgFile.setLastModified(ts.getTime());
        ts.setTime(ts.getTime() + (60 * 1000));
    }
    /*
     * Left zero pad an integer.
     */

    static String addLeadingZeros(int value, int len) {
        String strValue = Integer.toString(value);
        String retString = "";

        if (strValue.length() >= len) { // Already longer than the padding length
            retString = strValue;
        } else if (strValue.length() < len) { // need to add zeros
            retString = "0000000000000000000000000000000".substring(0, len - strValue.length()) + strValue;
        }

        return retString;
    }
}
