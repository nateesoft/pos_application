/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DecimalFormat;

/**
 *
 * @author Dell
 */
public class NumberToString {

    public String number(double num) {
        DecimalFormat df = new DecimalFormat("#,###,###,###.00");
        String textReturn = "";
//        double num = 1234567821.25;
//        double num = 1821.25;
//      1,234,567,890.25;
        String numText = df.format(num);
        String numTextDecimal = df.format(num);
        numText = numText.replace(",", "");
        numText = numText.substring(0, numText.lastIndexOf("."));
        char[] result = numText.replaceAll("\\W", numText).toCharArray();
        String a = "";
        String b = "";

        String aa = "";
        String bb = "";
        String decimalText = "";
        int round = result.length;

        numTextDecimal = numTextDecimal.replace(",", "");
//        numTextDecimal = numTextDecimal.substring(numText.length());
        try {
            decimalText = numTextDecimal.substring(numTextDecimal.lastIndexOf(".") + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!decimalText.equals("00")) {
//            int roundDecimal = numTextDecimal.length();
            int roundDecimal = 3;
//            char[] resultDecimal = numTextDecimal.replaceAll("\\W", decimalText).toCharArray();
            char[] resultDecimal = decimalText.toCharArray();
            //หาค่าจากทศนิยม

            for (char c : resultDecimal) {
                roundDecimal--;
                aa = changeNumToText("" + c, roundDecimal + "");
                if (aa.equals("หนึ่งสิบ")){
                    aa = "สิบ";
                }
                bb += aa;
            }
        } else {
            bb = "ศูนย์";
        }

        //หาค่าจากจำนวนเต็ม
        for (char c : result) {
            if (round > 0) {
                System.out.println("ตำแหน่ง " + round + " / " + a);
                a = changeNumToText("" + c, round + "");
                if (round != 0) {
                    if (round == 2 && a.equals("หนึ่งสิบ")) {
                        a = "สิบ";
                    }
                    b += a;
                }
                round--;
            }
        }
        if (decimalText.equals("00")) {
            textReturn = b + "บาทถ้วน";
        } else {
            textReturn = b + "บาท" + bb + "สตางค์";
        }

        System.out.println(textReturn);
        return textReturn;
    }

    private String changeNumToText(String numberText, String digit) {

        switch (digit) {
            case "1":
                digit = "";
                break;
            case "2":
                digit = "สิบ";
                break;
            case "3":
                digit = "ร้อย";
                break;
            case "4":
                digit = "พัน";
                break;
            case "5":
                digit = "หมื่น";
                break;
            case "6":
                digit = "แสน";
                break;
            case "7":
                digit = "ล้าน";
                break;
            case "8":
                digit = "สิบ";
                break;
            case "9":
                digit = "ร้อย";
                break;
            case "10":
                digit = "พัน";
                break;
        }
        switch (numberText) {
            case "0":
                numberText = "";
                break;
            case "1":
                if (digit.equals("")) {
                    numberText = "เอ็ด";
                } else {
                    numberText = "หนึ่ง";
                }

                break;
            case "2":
                if (digit.equals("สิบ")) {
                    numberText = "ยี่";
                } else {
                    numberText = "สอง";
                }

                break;
            case "3":
                numberText = "สาม";
                break;
            case "4":
                numberText = "สี่";
                break;
            case "5":
                numberText = "ห้า";
                break;
            case "6":
                numberText = "หก";
                break;
            case "7":
                numberText = "เจ็ด";
                break;
            case "8":
                numberText = "แปด";
                break;
            case "9":
                numberText = "เก้า";
                break;
            case ".":
                numberText = "จุด";
                break;
        }
        if (numberText.equals("จุด")) {
            digit = "";
        }
        return numberText + digit;
    }

//    public static void main(String[] args) {
//        NumberToString numberToText = new NumberToString();
//        numberToText.number(712.15);
//        numberToText.number(712);
//    }
}
