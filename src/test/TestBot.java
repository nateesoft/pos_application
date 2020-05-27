package test;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class TestBot {

    public static void main(String[] args) {
        try {
            SimpleDateFormat simp = new SimpleDateFormat("ddMMyyyyHHmmss");
            String prefix = simp.format(new Date());
            Robot robot = new Robot();
            // Capture a particular area on the screen
            int x = 50;
            int y = 50;
            int width = 250;
            int height = 250;
            Rectangle area = new Rectangle(x, y, width, height);
            BufferedImage bufferedImage = robot.createScreenCapture(area);
            // Write generated image to a file
            try {
                // Save as PNG
                File file = new File(prefix+"_SMALL.png");
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                System.out.println("Could not save small screenshot " + e.getMessage());
            }

            // Capture the whole screen
            area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            bufferedImage = robot.createScreenCapture(area);

            // Write generated image to a file
            try {
                // Save as PNG
                File file = new File(prefix+"_FULL.png");
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                System.out.println("Could not save full screenshot " + e.getMessage());
            }
            
            //sample auto key
            
        } catch (AWTException e) {
            System.out.println("Could not capture screen " + e.getMessage());
        }
    }
}
