package util;

import java.util.Calendar;

public class DateAndTime {
    
    //23:59:59
    public static String getTimeStringFormat(Calendar time){
        return convertNumberToString((time.get(Calendar.HOUR_OF_DAY)))+":"+ convertNumberToString((time.get(Calendar.MINUTE)))+":"+ convertNumberToString((time.get(Calendar.SECOND)));
    }
    
    //01
    public static String convertNumberToString(int num){
        return (num<10) ? "0"+num : String.valueOf(num);
    }
    
    //1
    public static int convertStringToNumber(String num){
        return ( num.charAt(0) == '0' ) ? Integer.parseInt(num.substring(1)) :Integer.parseInt(num);
    }    
    
}
