package com.softpos.database.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigFileServer {
    
    public static final String FILE_CONFIG = "C:/erp_confServer.txt";
    
    public static String getProperties(String keyword){
        String str="";
        try {
            FileInputStream fs = new FileInputStream(FILE_CONFIG);  
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));
            String tmp;  
            while ((tmp = br.readLine()) != null)   {
                if(tmp.indexOf(keyword)!=-1){
                    str = tmp.split(":", tmp.length())[1];
                    break;
                }
            }  
            br.close();
            ds.close();
            fs.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            str = "";
        }
        
        return str.trim();
    }
}
