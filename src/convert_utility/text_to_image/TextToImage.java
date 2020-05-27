package convert_utility.text_to_image;

import directory_utility.DirectoryUtility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class TextToImage {
    private int charecter = 50;
    public void setCharecter(int i){
        this.charecter = i;
    }
    public int getCharecter(){
        return charecter;
    }
    
    public static void main(String[] args){
        TextToImage t = new TextToImage();
        
        String[] fonts = t.getAvailableFontFamilyNames();
        for(int i=0; i<fonts.length; i++){
//            System.out.println(fonts[i]);
        }
    }

    private String formatImage;
    private String font;
    
    public TextToImage(){
        this.formatImage = "png";
        this.font = "Monospaced";
    }
    public TextToImage(String font, String formatImage){
        this.font = font;
        this.formatImage = formatImage;
    }

    public String[] getAvailableFontFamilyNames(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return ge.getAvailableFontFamilyNames();
    }
    
    public boolean textToImage(String txtPath, String savePath){
        InnerImageWriter writer = new InnerImageWriter(txtPath, savePath, formatImage, font);
        return writer.create();
    }

    public boolean textToImage(String txtPath, String savePath, String formatImage, String font){
        this.formatImage = formatImage;
        this.font = font;
        InnerImageWriter writer = new InnerImageWriter(txtPath, savePath, formatImage, font);
        return writer.create();
    }

    private class InnerImageWriter extends Panel {

        ImageWriter writer;
        Iterator<ImageWriter> iter;
        BufferedImage txtImg;
        
        String txtPath;
        String savePath;
        String formatImage;
        String font;

        final int fontSize = 14;



        InnerImageWriter(String txtPath, String savePath, String formatImage, String font) {
            this.txtPath = txtPath;
            this.savePath = savePath;
            this.formatImage = formatImage;
            this.font = font;
        }


        public Vector<String> readTextFile(File file) throws FileNotFoundException, IOException {
            Vector<String> text = new Vector<String>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String lineStr;
            while ((lineStr = br.readLine()) != null) {
                text.add(lineStr);
            }
            br.close();
            fr.close();
            return text;
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(txtImg, null, 0, 0);
        }

        public boolean create() {
            boolean success = false;

                //check path of file
            File txtFile = new File(txtPath);
            if(!txtFile.exists()){
               return false;
            }
            DirectoryUtility du = new DirectoryUtility();
            File saveFile;
            try{
                saveFile = du.getFileAndCreateDir(savePath);
                if(saveFile == null){
                    return false;
                }
            }catch(Exception e){
                return false;
            }

            ImageOutputStream imageOut = null;
            Vector<String> text = null;
            try {
                    //read text file
                text = readTextFile(txtFile);
                    //set width of 40 character
                int width = (int)(charecter*8.6);

                    //set height 20 pixel per line
                int height;
                if(text.size() <=40)
                    height = (text.size() * 18)+5;
                else if(text.size() <= 200)
                    height = (text.size() * 18);
                else
                    height = (text.size() * 17);
                
                //System.out.println(width+" X "+height);

                iter = ImageIO.getImageWritersByFormatName(formatImage);
                if (iter.hasNext()) {
                    writer = iter.next();
                }

                imageOut = ImageIO.createImageOutputStream(saveFile);
                
                txtImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D gImage = txtImg.createGraphics();
                gImage.setBackground(Color.WHITE);
                gImage.clearRect(0, 0, width, height);

                gImage.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
                gImage.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                gImage.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                gImage.setPaint(Color.BLACK);

                gImage.setFont(new Font(font, 0, fontSize));

                int x=13, y=16;
                for(int i=0; i<text.size(); i++){
                    gImage.drawString(text.get(i), x, y);
                    y += 18;
                }

                writer.setOutput(imageOut);
                writer.write(new IIOImage(txtImg, null, null));
                //System.out.println("Write complete!" + gImage.getBackground());
                success = true;
            } catch (Exception e) {
                success = false;
            }finally{
                if(imageOut != null)
                    try { imageOut.close(); } catch(Exception ex){}
                return success;
            }
        }
    }
}
