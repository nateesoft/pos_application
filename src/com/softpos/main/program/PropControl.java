package com.softpos.main.program;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import util.MSG;

public class PropControl {
    
    public static String PATH_CONFIG = "softpos/";
    public static String FILE_CONFIG = "softpos/config.properties";
    
    public void sampleSaveProp(){
        Properties prop = new Properties();
 
    	try {
    		//set the properties value
    		prop.setProperty("DB_SERVER", "localhost");
    		prop.setProperty("DB_ROOT", "root");
    		prop.setProperty("DB_PASS", "");
                prop.setProperty("DB_PORT", "3306");
 
    		//save properties to project root folder
    		prop.store(new FileOutputStream("test.properties"), null);
 
    	} catch (IOException ex) {
            MSG.ERR(null, ex.getMessage());
        }
    }
    
    public void sampleLoadProp(){
        Properties prop = new Properties();
 
    	try {
               //load a properties file
    		prop.load(new FileInputStream("test.properties"));
 
               //get the property value and print it out
                System.out.println(prop.getProperty("DB_SERVER"));
    		System.out.println(prop.getProperty("DB_ROOT"));
    		System.out.println(prop.getProperty("DB_PASSWORD"));
 
    	} catch (IOException ex) {
            MSG.ERR(null, ex.getMessage());
        }
    }
    
    public String getMacNo(){
       
        return Value.MACNO;
    }
    
    public ConnectBean connectServer(){
        Properties prop = new Properties();
        ConnectBean bean = new ConnectBean();
        try {
            prop.load(new FileInputStream(PropControl.FILE_CONFIG));            
            bean.setHostName(prop.getProperty("DB_SERVER"));
            bean.setDatabaseName(prop.getProperty("DB_NAME"));
            bean.setUsername(prop.getProperty("DB_USER"));
            bean.setPassword(prop.getProperty("DB_PASS"));            
            bean.setPort(Integer.parseInt(prop.getProperty("DB_PORT")));
            bean.setCharset(prop.getProperty("DB_CHARSET"));
        } catch (IOException e) {
            MSG.ERR(null, e.getMessage());
        } catch (NumberFormatException e) {
            MSG.ERR(null, e.getMessage());
        }
        
        return bean;
    }
    public ConnectBean connectClient(){
        Properties prop = new Properties();
        ConnectBean bean = new ConnectBean();
        try {
            prop.load(new FileInputStream(PropControl.FILE_CONFIG));
            
            bean.setHostName(prop.getProperty("DB_SERVER_CLIENT"));
            bean.setDatabaseName(prop.getProperty("DB_NAME_CLIENT"));
            bean.setUsername(prop.getProperty("DB_USER_CLIENT"));
            bean.setPassword(prop.getProperty("DB_PASS_CLIENT"));            
            bean.setPort(Integer.parseInt(prop.getProperty("DB_PORT_CLIENT")));
            bean.setCharset(prop.getProperty("DB_CHARSET_CLIENT"));
        } catch (IOException e) {
            MSG.ERR(null, e.getMessage());
        } catch (NumberFormatException e) {
            MSG.ERR(null, e.getMessage());
        }
        
        return bean;
    }
    
    
}
