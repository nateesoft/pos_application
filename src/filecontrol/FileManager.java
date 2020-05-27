package filecontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {

    public static void copyFileUsingStream(File source, File dest){
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        FileManager.copyFileUsingStream(new File("C:/softpos/connect.ini"), new File("C:/softpos/connect.ini.bak"));
    }
}
