package com.softpos.main.program;

import java.text.DecimalFormat;
import util.MSG;

public class NumberControl {

    public static String format(double data) {
        DecimalFormat df1 = new DecimalFormat("#,##0.00");

        return df1.format(data);
    }

    public static int TO_INT(double data) {
        return (int) data;
    }

    public static double UP_DOWN_25(double d) {
        DecimalFormat fm = new DecimalFormat("#.00");
        String money = "" + fm.format(d);
        if (money.indexOf(".") != -1) {
            String m = money.replace('.', ',');
            int multi = 1;
            if (m.indexOf("-") != -1) {
                multi = -1;
            }
            String[] data = m.split(",");
            if (data.length > 1) {
                if (data[1].length() > 2) {
                    data[1] = data[1].substring(0, 2);
                }
                double num1 = Double.parseDouble(data[1]);
                if (num1 < 10) {
                    num1 *= 10;
                }
                double num2 = Double.parseDouble(data[0]);
                if (num1 > 100) {
                    num1 = num1 / 10;
                }
                if (num1 >= 88 && num1 < 100) {
                    return num2 + 1.00 * multi;
                } else if (num1 >= 63 && num1 <= 87) {
                    return num2 + 0.75 * multi;
                } else if (num1 >= 38 && num1 <= 62) {
                    return num2 + 0.50 * multi;
                } else if (num1 >= 13 && num1 <= 37) {
                    return num2 + 0.25 * multi;
                } else if (num1 >= 0 && num1 <= 12) {
                    return num2;
                } 
                else {
                    return num2;
                }
            
            } else {
                return 0;
            }
        } else {
            double total = 0.00;
            try {
                total = Double.parseDouble(money);
            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
            }

            return total;
        }
    }
//    public static double UP_DOWN_25(double d) {
//        String money = "" + d;
//        if (money.indexOf(".") != -1) {
//            String m = money.replace('.', ',');
//            int multi = 1;
//            if (m.indexOf("-") != -1) {
//                multi = -1;
//            }
//            String[] data = m.split(",");
//            if (data.length > 1) {
//                if (data[1].length() > 2) {
//                    data[1] = data[1].substring(0, 2);
//                }
//                double num1 = Double.parseDouble(data[1]);
//                if (num1 < 10) {
//                    num1 *= 10;
//                }
//                double num2 = Double.parseDouble(data[0]);
//                if (num1 > 100) {
//                    num1 = num1 / 10;
//                }
//                if (num1 >= 88 && num1 < 100) {
//                    return num2 + 1.00 * multi;
//                } else if (num1 >= 63 && num1 <= 87) {
//                    return num2 + 0.75 * multi;
//                } else if (num1 >= 38 && num1 <= 62) {
//                    return num2 + 0.50 * multi;
//                } else if (num1 >= 13 && num1 <= 37) {
//                    return num2 + 0.25 * multi;
//                } else if (num1 >= 0 && num1 <= 12) {
//                    return num2;
//                } else {
//                    return num2;
//                }
//            } else {
//                return 0;
//            }
//        } else {
//            double total = 0.00;
//            try {
//                total = Double.parseDouble(money);
//            } catch (NumberFormatException e) {
//                MSG.ERR(e.getMessage());
//            }
//
//            return total;
//        }
//    }

    public static double UP_DOWN_25NewTotal(double d) {
        String money = "" + d;
        if (money.indexOf(".") != -1) {
            String m = money.replace('.', ',');
            int multi = 1;
            if (m.indexOf("-") != -1) {
                multi = -1;
            }
            String[] data = m.split(",");
            if (data.length > 1) {
                if (data[1].length() > 2) {
                    data[1] = data[1].substring(0, 2);
                }
                double num1 = Double.parseDouble(data[1]);
                if (num1 < 10) {
                    num1 *= 10;
                }
                double num2 = Double.parseDouble(data[0]);
                if (num1 > 100) {
                    num1 = num1 / 10;
                }
                if (num1 >= 88 && num1 < 100) {
                    return num2 + 1.00 * multi;
                } else if (num1 >= 63 && num1 <= 87) {
                    return num2 + 0.75 * multi;
                } else if (num1 >= 38 && num1 <= 62) {
                    return num2 + 0.50 * multi;
                } else if (num1 >= 13 && num1 <= 37) {
                    return num2 + 0.25 * multi;
                } else if (num1 >= 0 && num1 <= 12) {
                    return num2;
                } else if (num1 >= 75) {
                    return num1 = 0;
                } else if (num1 >= 50) {
                    return num1 = 75;
                } else if (num1 >= 25) {
                    return num1 = 50;
                } else if (num1 >= 0 && num1 < 25) {
                    return num1 = 25;
                } else {
                    return num2;
                }
            } else {
                return 0;
            }
        } else {
            double total = 0.00;
            try {
                total = Double.parseDouble(money);
            } catch (NumberFormatException e) {
                MSG.ERR(e.getMessage());
            }

            return total;
        }
    }

    public static int UP_DOWN_NATURAL_BAHT(double d) {

        return (int) Math.round(d);
    }

    public static double DOWN_BAHT(double d) {

        return Math.floor(d);
    }

    public static double UP_BAHT(double d) {

        return Math.ceil(d);
    }

}
