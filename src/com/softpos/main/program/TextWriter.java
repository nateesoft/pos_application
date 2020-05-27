package com.softpos.main.program;

import directory_utility.DirectoryUtility;
import java.io.FileWriter;

/**
 *
 * @author root
 */
public class TextWriter {
    
    public static void main(String[] args){
        try {

            TextWriter tw = new TextWriter();
            for(int i=0; i<10; i++)
                tw.writeToText("/root/AAA/BBB/CCC/DDD/new.txt", ""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i);
            tw.writeToText("/root/กลอน Test.txt", "อยาก delete แฟนเธอไป , แล้วลองใส่ หัวใจเรา , Format เธอให้ลืมเขา , upgrate เราใส่ใจเธอ ");
        } catch (Exception e) {
        }
    }
    
    public void writeToText(String pathFile, String data){
        FileWriter writer = null;
        try{
            writer = new FileWriter(new DirectoryUtility().getFileAndCreateDir(pathFile),true);
            
            writer.write(data);
            writer.write(13);
            writer.write(10);
        } catch (Exception e) {
        }finally{
            try{ writer.close(); } catch(Exception e){}
        }
       
    }
    public void writeToText(String pathFile, String data,boolean Overite) throws Exception {
        FileWriter writer = null;
        try{
            writer = new FileWriter(new DirectoryUtility().getFileAndCreateDir(pathFile),Overite);
            writer.write(data);
            writer.write(13);
            writer.write(10);
        }finally{
            try{ writer.close(); } catch(Exception e){}
        }
       
    }
  
}
