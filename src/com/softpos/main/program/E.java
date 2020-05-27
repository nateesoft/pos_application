package com.softpos.main.program;

import javax.swing.JOptionPane;

public class E {
    
    public static void Log(Exception e, Class c){
        JOptionPane.showMessageDialog(null, e.getMessage()+c);
    }
}
