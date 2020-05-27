package com.softpos.main.program;

public class ThaiUtil {

    public static String Unicode2ASCII(String unicode) { // แปลง Unicode เป็น ASCII 
        StringBuilder ascii = new StringBuilder(unicode); // กำหนดพื้นที่ใช้งานชั่วคราว
        int code;
        for (int i = 0; i < unicode.length(); i++) { // ลูปเท่าจำนวนตัวอักษร
            code = (int) unicode.charAt(i); // อ่านค่ารหัสที่ละตัวอักษร
            if ((0xE01 <= code) && (code <= 0xE5B)) // ตรวจสอบว่าอยู่ในช่วงภาษาไทยของ Unicode หรือไม่
            {
                ascii.setCharAt(i, (char) (code - 0xD60)); // หากใช้แปลงเป็นภาษาไทยในช่วงของ ASCII
            }
        }
        return ascii.toString(); // แปลงข้อมูลกลับไปเป็นแบบ String เพื่อใช้งานต่อไป
    }

    public static String ASCII2Unicode(String ascii) {
        if(ascii==null){
            return "";
        }
        StringBuilder unicode = new StringBuilder(ascii);
        int code;
        for (int i = 0; i < ascii.length(); i++) {
            code = (int) ascii.charAt(i);
            if ((0xA1 <= code) && (code <= 0xFB)) // ตรวจสอบว่าอยู่ในช่วงภาษาไทยของ ASCII หรือไม่
            {
                unicode.setCharAt(i, (char) (code + 0xD60)); // หากใช้แปลงเป็นภาษาไทยในช่วงของ Unicode
            }
        }
        return unicode.toString(); // แปลงข้อมูลกลับไปเป็นแบบ String เพื่อใช้งานต่อไป
    }

    static Object ASCII2Unicode(Object[] MS) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
