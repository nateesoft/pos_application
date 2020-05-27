/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package convert_utility.text_to_image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Test1 {
    public static void main(String[] argsT) throws IOException {
        String[] args = { "C:\\AAA.jpg" ,"C:\\Axx.gif" };
        String inFilename = args[0];
        String outFilename = args[1];
        BufferedImage bImage1;
        BufferedImage bImage2;

        File fileIn = new File(inFilename);
        File out = new File(outFilename);

        bImage1 = ImageIO.read(fileIn);

//Color color = bImage1.createGraphics().getColor();
        Color color = bImage1.getGraphics().getColor();

        IndexColorModel colorModel = new IndexColorModel(8, 2,
                new byte[]{(byte) (color.getRed() - 1), (byte) color.getRed()},
                new byte[]{0, (byte) color.getGreen()},
                new byte[]{0, (byte) color.getBlue()}, 0);

        ColorConvertOp ccOp = new ColorConvertOp(bImage1.getColorModel().getColorSpace(), colorModel.getColorSpace(), null);

        bImage2 = new BufferedImage(bImage1.getWidth(),
                bImage1.getHeight(),
                BufferedImage.TYPE_BYTE_INDEXED,
                colorModel);

        Graphics2D g2d = bImage2.createGraphics();
//g2d.drawImage(bImage1, 0, 0, bImage1.getWidth(), bImage1.getHeight(), null);
//g2d.drawRenderedImage(bImage1, null);
        g2d.drawImage(bImage1, ccOp, 0, 0);

        ImageIO.write(bImage2, "gif", out);


    }
}
