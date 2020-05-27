package util;

public class CutText {

    public static String cutText(String str, int n) {
        if (str != null && str.equals("")) {
            if (str.length() < n) {
                return str;
            } else {
                return str.substring(0, n);
            }
        } else {
            return str;
        }
    }
}
