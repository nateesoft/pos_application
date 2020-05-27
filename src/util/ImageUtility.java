package util;

import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import javax.swing.Icon;

public class ImageUtility {

    private ImageUtility() {
        throw new AssertionError();
    }

    public static ImageIcon createImageIconFromLocation(String path,
            String description) {
        java.net.URL imgURL = null;
        File file = new File(path);
        if (file.exists()) {
            try {
                imgURL = file.toURL();
            } catch (MalformedURLException ex) {
                imgURL = null;
                //ex.printStackTrace();
            }
        }
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     *
     * @param srcImg - source image to scale
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    public static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Create an Icon
     *
     * @param file - file of image
     * @param description - description of the file
     * @param w - desired width
     * @param h - desired height
     * @return - the Icon for Button etc.
     */
    public static Icon createReadyIcon(File file, String description, int width, int height) {
        Icon rIcon;
        if (file != null) {
            if (file.exists()) {
                if (file.isFile()) {
                    String fname = file.getName();
                    if (fname.endsWith(".gif") || fname.endsWith(".jpg") || fname.endsWith("png")) {
                        try {
                            URL imgURL = file.toURL();
                            rIcon = createReadyIcon(imgURL, description, width, height);
                        } catch (MalformedURLException ex) {
                            rIcon = new MissingIcon(width, height);
                        }
                    } else {
                        rIcon = new MissingIcon(width, height);
                    }
                } else {
                    rIcon = new MissingIcon(width, height);
                }
            } else {
                rIcon = new MissingIcon(width, height);
            }
        } else {
            rIcon = new MissingIcon(width, height);
        }
        return rIcon;
    }

    /**
     * Create an Icon
     *
     * @param imgURL - source URL of image file
     * @param description - description of the file
     * @param w - desired width
     * @param h - desired height
     * @return - the Icon for Button etc.
     */
    public static Icon createReadyIcon(URL imgURL, String description, int width, int height) {
        Icon rIcon;
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL, description);
            rIcon = new ImageIcon(ImageUtility.getScaledImage(icon.getImage(), width, height));
        } else {
            rIcon = new MissingIcon(width, height);
        }
        return rIcon;
    }
}

/**
 * The "missing icon" is a white box with a black border and a red x. It's used
 * to display something when there are issues loading an icon from an external
 * location.
 *
 * @author Collin Fagan
 * @date 7/25/2007
 */
class MissingIcon implements Icon {

    private int width;
    private int height;
    private BasicStroke stroke = new BasicStroke(4);

    public MissingIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x + 1, y + 1, width - 2, height - 2);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 1, y + 1, width - 2, height - 2);

        g2d.setColor(Color.RED);

        g2d.setStroke(stroke);
        g2d.drawLine(x + 10, y + 10, x + width - 10, y + height - 10);
        g2d.drawLine(x + 10, y + height - 10, x + width - 10, y + 10);

        g2d.dispose();
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
}
