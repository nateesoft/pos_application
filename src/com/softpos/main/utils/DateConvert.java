package com.softpos.main.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateConvert {

    public String dateDatabase(String date) {
        date = date.replace("/", "");
        String dd = date.substring(0, 2);
        String mm = date.substring(2, 4);
        String yyyy = date.substring(4, 8);
        date = yyyy + "-" + mm + "-" + dd;
        return date;
    }

    public String dateGetToShow(String date) {
        date = date.replace("-", "");
        String yyyy = date.substring(0, 4);
        String mm = date.substring(4, 6);
        String dd = date.substring(6, 8);
        date = dd + " / " + mm + " / " + yyyy;
        return date;
    }

    public String GetCurrentDate() {
        SimpleDateFormat GetLocalDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        int MM = c.get(Calendar.MONTH);
        int yyyy = c.get(Calendar.YEAR);
        int dd = c.get(Calendar.DATE);
        String dateString = "";
        String DateOrder = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +2);
        dateString += ShowDatefmt.format(current.getTime());
        Calendar current1 = Calendar.getInstance();
        current1.add(Calendar.DATE, +0);
        DateOrder += GetLocalDate.format(current1.getTime());
        System.out.println(dateString);
        //txtDate1.setText(dateString);
        return DateOrder;
    }

    public String GetCurrentDateFM(String FM) {
        SimpleDateFormat GetLocalDate = new SimpleDateFormat(FM, Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        int MM = c.get(Calendar.MONTH);
        int yyyy = c.get(Calendar.YEAR);
        int dd = c.get(Calendar.DATE);
        String dateString = "";
        String DateOrder = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +2);
        Calendar current1 = Calendar.getInstance();
        current1.add(Calendar.DATE, +0);
        DateOrder += GetLocalDate.format(current1.getTime());
        System.out.println(dateString);
        //txtDate1.setText(dateString);
        return DateOrder;
    }

    public String minusDate(String dateInput, int i) {
        String[] dateStr = dateInput.split("-");//2016-12-31
        int yyyy = Integer.parseInt(dateStr[0]);//2016
        int MM = Integer.parseInt(dateStr[1]);//12
        int dd = Integer.parseInt(dateStr[2]);//31
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
//        c.set(yyyy, MM - 1, dd - 1);//set back date
        c.set(yyyy, MM - 1, dd - i);//set back date
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateUse = s.format(c.getTime());//use date time (format: yyyy-MM-dd);
//        System.out.println(dateUse);
        return dateUse;
    }

    public String GetCurrentTime() {
        SimpleDateFormat ShowDatefmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Calendar c = new GregorianCalendar();
        String TimeString = "";
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 0);
        TimeString += ShowDatefmt.format(current.getTime());
        System.out.println(TimeString);
        //txtDate1.setText(dateString);
        return TimeString;
    }

    public int compareDateMonthYear(String date) {
        //คำนวนเวลาการทำงานของพนักงานทั้งปี
        date = date.replace("-", "");
        int dd = Integer.parseInt(date.substring(6, 8));
        int MM = Integer.parseInt(date.substring(4, 6));
        int yyyy = Integer.parseInt(date.substring(0, 4));
        LocalDate today = LocalDate.now();                          //Today's date
        LocalDate birthday = LocalDate.of(yyyy, MM, dd);  //Birth date

        Period p = Period.between(birthday, today);
        return p.getDays();
//        System.out.println("Day : " +p.getDays());
//        System.out.println("Month : " +p.getMonths());
//        System.out.println("Year : " +p.getYears());
    }

    public int getCheckExpireDate(String date) {
        date = date.replace("-", "");
        String cur_Date = GetCurrentDate().replace("-", "");
        int dd = Integer.parseInt(date.substring(6, 8));
        int MM = Integer.parseInt(date.substring(4, 6));
        int yyyy = Integer.parseInt(date.substring(0, 4));

        int cur_dd = Integer.parseInt(cur_Date.substring(6, 8));
        int cur_MM = Integer.parseInt(cur_Date.substring(4, 6));
        int cur_yyyy = Integer.parseInt(cur_Date.substring(0, 4));

        dd = dd - cur_dd;
        MM = MM - cur_MM;
        yyyy = yyyy - cur_yyyy;
        int sum = dd + MM + yyyy;
//        System.out.println(dd + "/" + MM + "/" + yyyy);
        System.out.println(sum);
        return sum;
    }

    public int getCheckExpireTime(String timeStart, String timeExpire) {
        timeStart = timeStart.replace(":", "");
        timeExpire = timeExpire.replace(":", "");
        String cur_Time = GetCurrentTime().replace(":", "");
        int valueReturn = 0;
        int start = Integer.parseInt(timeStart);
        int expire = Integer.parseInt(timeExpire);
        int now = Integer.parseInt(cur_Time);
        if (now > start && now < expire) {
            valueReturn = 1;
        } else {
            valueReturn = -1;
        } 
        System.out.println(valueReturn);
        return valueReturn;
       
    }

}
