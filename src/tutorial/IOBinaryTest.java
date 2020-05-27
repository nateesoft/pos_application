package tutorial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOBinaryTest {
    
    public static void main(String[] args) {
//        long start=0,end=0;
//        try {
//            start = System.currentTimeMillis();
//            FileWriter writer = new FileWriter("C:\\test.txt");
//            for(int i=0;i<1000000;i++){
//                writer.write('A');
//            }
//            writer.close();
//            end = System.currentTimeMillis();
//        } catch (IOException ex) {
//            Logger.getLogger(IOBinaryTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(end-start);//235
        
        long start=0,end=0;
        try {
            start = System.currentTimeMillis();
            FileWriter w = new FileWriter("C:\\test.txt");
            BufferedWriter writer = new BufferedWriter(w);
            for(int i=0;i<1000000;i++){
                writer.write('A');
            }
            writer.close();
            end = System.currentTimeMillis();
        } catch (IOException ex) {
            Logger.getLogger(IOBinaryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(end-start);//90
    }
}
