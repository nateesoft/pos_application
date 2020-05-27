package util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FullScreenUtility {

    private FullScreenUtility(){
        throw new AssertionError();
    }

    //Have Error

    public static void setFullScreen(JFrame frame){
        GraphicsEnvironment graphicsEnvironment =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice =
            graphicsEnvironment.getDefaultScreenDevice();

        DisplayMode displayModes[] = graphicsDevice.getDisplayModes();
        DisplayMode originalDisplayMode = graphicsDevice.getDisplayMode();

        try {
            if (graphicsDevice.isFullScreenSupported()) {
                graphicsDevice.setFullScreenWindow(frame);
            }else{
                System.err.println("System not support full screen mode. ");
            }
        }catch(Exception e){
            System.err.println("System not support full screen mode. ");
        }

    }

    public static void setFullScreen(JDialog dialog){
        GraphicsEnvironment graphicsEnvironment =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice =
            graphicsEnvironment.getDefaultScreenDevice();

        DisplayMode displayModes[] = graphicsDevice.getDisplayModes();
        DisplayMode originalDisplayMode = graphicsDevice.getDisplayMode();

        try {
            if (graphicsDevice.isFullScreenSupported()) {
                graphicsDevice.setFullScreenWindow(dialog);
            }else{
                System.err.println("System not support full screen mode. ");
            }
        }catch(Exception e){
            System.err.println("System not support full screen mode. ");
        }

    }
}
