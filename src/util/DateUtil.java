package util;

import util.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JTextField;

public class DateUtil {

    private DateUtil(){
        throw new AssertionError();
    }
    
    public static int getMaxDay(GregorianCalendar month){
        int nday;
        switch(month.get(Calendar.MONTH)){ 
            case 0: nday = 31; break;
            case 1:             
                nday = month.isLeapYear(month.get(Calendar.MONTH)) ? 29 : 28; 
                break;
            case 2: nday = 31; break;
            case 3: nday = 30; break;
            case 4: nday = 31; break;
            case 5: nday = 30; break;
            case 6: nday = 31; break;
            case 7: nday = 31; break;
            case 8: nday = 30; break;
            case 9: nday = 31; break;
            case 10: nday = 30; break;
            case 11: nday = 31; break;
            default: nday = 0; break;
        }
        return nday;
    }
    
    public static int getDistanceDays(Calendar gc1, Calendar gc2) {
        int elapsed = 0;
        GregorianCalendar g1, g2;
        if (gc2.after(gc1)) {
            g2 = (GregorianCalendar) gc2.clone();
            g1 = (GregorianCalendar) gc1.clone();
        } else {
            g2 = (GregorianCalendar) gc1.clone();
            g1 = (GregorianCalendar) gc2.clone();
        }
        g1.clear(Calendar.MILLISECOND);
        g1.clear(Calendar.SECOND);
        g1.clear(Calendar.MINUTE);
        g1.clear(Calendar.HOUR_OF_DAY);
        g2.clear(Calendar.MILLISECOND);
        g2.clear(Calendar.SECOND);
        g2.clear(Calendar.MINUTE);
        g2.clear(Calendar.HOUR_OF_DAY);
        while (g1.before(g2)) {
            g1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }
    
    public static int getDistanceMonths(Calendar gc1, Calendar gc2) {
        int elapsed = 0;
        GregorianCalendar g1, g2;
        if (gc2.after(gc1)) {
            g2 = (GregorianCalendar) gc2.clone();
            g1 = (GregorianCalendar) gc1.clone();
        } else {
            g2 = (GregorianCalendar) gc1.clone();
            g1 = (GregorianCalendar) gc2.clone();
        }
        g1.clear(Calendar.MILLISECOND);
        g1.clear(Calendar.SECOND);
        g1.clear(Calendar.MINUTE);
        g1.clear(Calendar.HOUR_OF_DAY);
        g1.clear(Calendar.DATE);
        g2.clear(Calendar.MILLISECOND);
        g2.clear(Calendar.SECOND);
        g2.clear(Calendar.MINUTE);
        g2.clear(Calendar.HOUR_OF_DAY);
        g2.clear(Calendar.DATE);
        while (g1.before(g2)) {
            g1.add(Calendar.MONTH, 1);
            elapsed++;
        }
        return elapsed;
    }
   
    public static long getDistanceMillis(Calendar gc1, Calendar gc2) {
        Date d1 = gc1.getTime();
        Date d2 = gc2.getTime();
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        long difference = l2 - l1;
        return difference;
    }
   
    public static long getDistanceSeconds(Calendar gc1, Calendar gc2) {
        Date d1 = gc1.getTime();
        Date d2 = gc2.getTime();
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        long difference = l2 - l1;
        return difference / 1000;
    }
   
    public static int getMaximumDay(Calendar gc1) {
        gc1.set(Calendar.DAY_OF_MONTH, 1);
        GregorianCalendar gc2 = (GregorianCalendar) gc1.clone();
        gc2.add(Calendar.MONTH, 1);
        int count = 0;
        while (gc1.compareTo(gc2) <= -1) {
            gc1.add(Calendar.DAY_OF_MONTH, 1);
            count++;
        }
        return count;
    }
   
    public static void showDateDetail(Calendar date) {
        System.out.println();
        System.out.println("date.getTime()   = " + date.getTime());
        System.out.println("  date.get(Calendar.DATE)          = " + date.get(Calendar.DATE));
        System.out.println("  date.get(Calendar.MONTH )        = " + (date.get(Calendar.MONTH)));
        System.out.println("  date.get(Calendar.YEAR)          = " + date.get(Calendar.YEAR));
        System.out.println("  date.get(Calendar.DAY_OF_MONTH)  = " + date.get(Calendar.DAY_OF_MONTH));
        System.out.println("  date.get(Calendar.DAY_OF_WEEK)   = " + date.get(Calendar.DAY_OF_WEEK));
        System.out.println("  date.get(Calendar.DAY_OF_WEEK_IN_MONTH) = " + date.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("  date.get(Calendar.DAY_OF_YEAR)   = " + date.get(Calendar.DAY_OF_YEAR));
        System.out.println("  date.get(Calendar.WEEK_OF_MONTH) = " + date.get(Calendar.WEEK_OF_MONTH));
        System.out.println("  date.get(Calendar.WEEK_OF_YEAR)  = " + date.get(Calendar.WEEK_OF_YEAR));
        System.out.println("  date.get(Calendar.HOUR)          = " + date.get(Calendar.HOUR));
        System.out.println("  date.get(Calendar.HOUR_OF_DAY)   = " + date.get(Calendar.HOUR_OF_DAY));
        System.out.println("  date.get(Calendar.MINUTE)        = " + date.get(Calendar.MINUTE));
        System.out.println("  date.get(Calendar.SECOND)        = " + date.get(Calendar.SECOND));
        System.out.println("  date.get(Calendar.MILLISECOND)   = " + date.get(Calendar.MILLISECOND));
        System.out.println();
        System.out.println("  date.getMaximum(Calendar.DAY_OF_MONTH) = " + date.getMaximum(Calendar.DAY_OF_MONTH) + " ผิด");
        System.out.println("  date.getMaximum(Calendar.MONTH)        = " + date.getMaximum(Calendar.MONTH) + " ผิด");
        System.out.println("  date.getMinimum(Calendar.DAY_OF_MONTH) = " + date.getMinimum(Calendar.DAY_OF_MONTH) + " ผิด");
        System.out.println("  date.getMinimum(Calendar.MONTH)        = " + date.getMinimum(Calendar.MONTH) + " ผิด");
        System.out.println("  date.getMinimalDaysInFirstWeek()       = " + date.getMinimalDaysInFirstWeek());
    }
  
    public static GregorianCalendar fusionDate(Date day, Date time) {
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        SimpleDateFormat month = new SimpleDateFormat("M", Locale.ENGLISH);
        SimpleDateFormat date = new SimpleDateFormat("d", Locale.ENGLISH);
        SimpleDateFormat hour = new SimpleDateFormat("H", Locale.ENGLISH);
        SimpleDateFormat minute = new SimpleDateFormat("m", Locale.ENGLISH);
        SimpleDateFormat second = new SimpleDateFormat("s", Locale.ENGLISH);
        GregorianCalendar cal = null;
        cal = new GregorianCalendar(
                Integer.parseInt(year.format(day)),
                Integer.parseInt(month.format(day)) - 1,
                Integer.parseInt(date.format(day)),
                Integer.parseInt(hour.format(time)),
                Integer.parseInt(minute.format(time)),
                Integer.parseInt(second.format(time)));

        return cal;
    }
  
    public static GregorianCalendar convertDateToCal(Date day) {
        
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        SimpleDateFormat month = new SimpleDateFormat("M", Locale.ENGLISH);
        SimpleDateFormat date = new SimpleDateFormat("d", Locale.ENGLISH);
        SimpleDateFormat hour = new SimpleDateFormat("H", Locale.ENGLISH);
        SimpleDateFormat minute = new SimpleDateFormat("m", Locale.ENGLISH);
        SimpleDateFormat second = new SimpleDateFormat("s", Locale.ENGLISH);
        GregorianCalendar cal = null;
        try {
            cal = new GregorianCalendar(
                    Integer.parseInt(year.format(day)),
                    Integer.parseInt(month.format(day)) - 1,
                    Integer.parseInt(date.format(day)),
                    Integer.parseInt(hour.format(day)),
                    Integer.parseInt(minute.format(day)),
                    Integer.parseInt(second.format(day)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cal;
    }

    public static GregorianCalendar showDateChooserDialog(JTextField jtext){
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            GregorianCalendar cal;
            DateChooserDialog dcd = new DateChooserDialog(null, true);
            dcd.setVisible(true);
            cal = (GregorianCalendar) dcd.getSelectedDate();
            if (cal != null) {
                jtext.setText(fmt.format(cal.getTime()));
            }
            return cal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static GregorianCalendar showDateChooserDialog(JTextField jtext,SimpleDateFormat format){
        try {
            SimpleDateFormat fmt = format;
            GregorianCalendar cal;
            DateChooserDialog dcd = new DateChooserDialog(null, true);
            dcd.setVisible(true);
            cal = (GregorianCalendar) dcd.getSelectedDate();
            if (cal != null) {
                jtext.setText(fmt.format(cal.getTime()));
            }
            return cal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        
    public static Time lunarStay(Calendar lunarLanding, Calendar lunarDeparture) {
        
        long timeInMillis = getDistanceMillis(lunarLanding, lunarDeparture);
        long timeInSecons = getDistanceSeconds(lunarLanding, lunarDeparture);
        
        long day, hours, minutes, seconds, millis;
        
        hours = timeInSecons / 3600;
        timeInSecons = timeInSecons - (hours * 3600);
        minutes = timeInSecons / 60;
        timeInSecons = timeInSecons - (minutes * 60);
        seconds = timeInSecons;
        
        millis = timeInMillis % 1000;
        
        day = hours/24;
        hours = hours%24;
        
        return new Time(day,(int)hours,(int)minutes,(int)seconds,(int)millis);
   
    }
        
    public static void main(String[] args) {
        GregorianCalendar start = new GregorianCalendar(2007,1,1,20,20,20);
        start.set(Calendar.MILLISECOND, 100);
        GregorianCalendar stop = new GregorianCalendar(2007,1,3,10,25,20);
        stop.set(Calendar.MILLISECOND, 900);
        System.out.println(lunarStay(start,stop).toString());
        
    }
}
