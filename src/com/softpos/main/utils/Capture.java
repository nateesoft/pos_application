package com.softpos.main.utils;

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

public class Capture {
    
    public static void save(){
        try {
            SimpleDateFormat simp = new SimpleDateFormat("ddMMyyyyHHmmss");
            String prefix = simp.format(new Date());
            Robot robot = new Robot();
            Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(area);
            
            // Write generated image to a file
            try {
                // Save as PNG
                if(!new File("tmp").exists()){
                    new File("tmp").mkdir();
                }
                File file = new File("tmp/"+prefix+"_FULL.png");
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
