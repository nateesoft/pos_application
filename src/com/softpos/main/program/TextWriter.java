package com.softpos.main.program;

import com.softpos.main.utils.DirectoryUtility;
import java.io.FileWriter;

public class TextWriter {
    
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
