package com.softpos.main.program;
public class FilterNameToMYSQL {
    public static String trueName(String text){
        String textTrue = "";
        for(int i=0; i<text.length(); i++){
            char ch = text.charAt(i);
            
            if(!isSymbol(""+ch)){
                textTrue += ch;
            }
        }
        return textTrue;
    }
    private static boolean isSymbol(String c){
        if( (c.equals("\"")) || (c.equals("'")) || (c.equals(";")) || (c.equals(",")) || (c.equals("-")) || (c.equals("/")) ) 
            return true;
        else
            return false;
    }
}
