package test;

import java.text.BreakIterator;
import java.util.Locale;

public class ThaiCut {

    public static void main(String[] args) {
        Locale thaiLocale = new Locale("th");
        String input = "ชั่วโมงอินเทอร์เน็ตต้อนรับปีจอ แถมมโหฬารนานสูงสุด 2 เท่า";
        BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);
        boundary.setText(input);
        
        printEachForward(boundary, input);
    }

    public static void printEachForward(BreakIterator boundary, String source) {
        StringBuilder strout = new StringBuilder();
        int start = boundary.first();
        for (int end = boundary.next();
                end != BreakIterator.DONE;
                start = end, end = boundary.next()) {
            strout.append(source.substring(start, end));
            strout.append("-");
        }
        
        System.out.println(strout.toString());
    }
}
