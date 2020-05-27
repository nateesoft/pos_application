/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printReport;

public class convertToChar {

    public convertToChar() {
    }

    public String convertNumberToChar(String a) {

        String outPrice = "";
        int len = a.indexOf(".");
        String b = a.substring(0, len);
        String[] vert = new String[len];
        String[] ch = {"สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน", "สิบล้าน", "ร้อยล้าน"};
        for (int i = 0; i < len - 1; i++) {
            if (a.substring(i, i + 1).equals("1")) {
                if (i == len - 2) {
                    vert[i] = "สิบ";
                } else {
                    vert[i] = "หนึ่ง";
                }
            } else if (a.substring(i, i + 1).equals("2")) {
                if (i == len - 2) {
                    vert[i] = "ยี่";
                } else {
                    vert[i] = "สอง";
                }
            } else if (a.substring(i, i + 1).equals("3")) {
                vert[i] = "สาม";
            } else if (a.substring(i, i + 1).equals("4")) {
                vert[i] = "สี่";
            } else if (a.substring(i, i + 1).equals("5")) {
                vert[i] = "ห้า";
            } else if (a.substring(i, i + 1).equals("6")) {
                vert[i] = "หก";
            } else if (a.substring(i, i + 1).equals("7")) {
                vert[i] = "เจ็ด";
            } else if (a.substring(i, i + 1).equals("8")) {
                vert[i] = "แปด";
            } else if (a.substring(i, i + 1).equals("9")) {
                vert[i] = "เก้า";
            } else {
                vert[i] = "";
            }
        }
        int size = vert.length - 2;       
        for (int i = 0; i < vert.length - 1; i++) {
            if (!vert[i].equals("")) {  
                String chs = b.substring(b.length()-2,b.length()-1);
                if(i == len-2 && chs.equals("1")){ 
                  //  JOptionPane.showMessageDialog(null, chs);
                }else{
                    vert[i] += ch[size]; 
                }
                               
            }
            size--;
        }
        for (int i = 0; i < vert.length - 1; i++) {
            if (!vert[i].equals("")) {
                outPrice += vert[i];
            }
        }
        if (b.substring(b.length() - 1, b.length()).equals("1")) {
            if (b.length() != 1) {
                outPrice += "เอ็ด";
            } else {
                outPrice += "หนึ่ง";
            }
        } else if (b.substring(b.length() - 1, b.length()).equals("2")) {
            outPrice += "สอง";

        } else if (b.substring(b.length() - 1, b.length()).equals("3")) {
            outPrice += "สาม";

        } else if (b.substring(b.length() - 1, b.length()).equals("4")) {
            outPrice += "สี่";

        } else if (b.substring(b.length() - 1, b.length()).equals("5")) {
            outPrice += "ห้า";

        } else if (b.substring(b.length() - 1, b.length()).equals("6")) {
            outPrice += "หก";

        } else if (b.substring(b.length() - 1, b.length()).equals("7")) {
            outPrice += "เจ็ด";

        } else if (b.substring(b.length() - 1, b.length()).equals("8")) {
            outPrice += "แปด";

        } else if (b.substring(b.length() - 1, b.length()).equals("9")) {
            outPrice += "เก้า";

        } else if (b.substring(b.length() - 1, b.length()).equals("0")) {
        }
        outPrice += "บาท";

        String sib = a.substring(a.length() - 2, a.length() - 1);
        String noui = a.substring(a.length() - 1, a.length());
        String satank = "";
        String check = a.substring(a.length() - 2, a.length() - 1);
        int num = Integer.parseInt(check);
        if (num > 0) {
            if (sib.equals("1")) {
                satank += "สิบ";
            } else if (sib.equals("2")) {
                satank += "ยี่สิบ";
            } else if (sib.equals("3")) {
                satank += "สามสิบ";
            } else if (sib.equals("4")) {
                satank += "สี่สิบ";
            } else if (sib.equals("5")) {
                satank += "ห้าสิบ";
            } else if (sib.equals("6")) {
                satank += "หกสิบ";
            } else if (sib.equals("7")) {
                satank += "เจ็ดสิบ";
            } else if (sib.equals("8")) {
                satank += "แปดสิบ";
            } else if (sib.equals("9")) {
                satank += "เก้าสิบ";
            } else {
            }

            if (noui.equals("1")) {
                satank += "เอ็ด";
            } else if (noui.equals("2")) {
                satank += "สอง";
            } else if (noui.equals("3")) {
                satank += "สาม";
            } else if (noui.equals("4")) {
                satank += "สี่";
            } else if (noui.equals("5")) {
                satank += "ห้า";
            } else if (noui.equals("6")) {
                satank += "หก";
            } else if (noui.equals("7")) {
                satank += "เจ็ด";
            } else if (noui.equals("8")) {
                satank += "แปด";
            } else if (noui.equals("9")) {
                satank += "เก้า";
            } else {
            }
            outPrice += satank + "สตางค์";

        } else {
            outPrice += "ถ้วน";
        }
//        System.out.println(a+" convert is :" + outPrice);
        return outPrice;
    }

    public static void main(String[] args) {
        convertToChar c = new convertToChar();
       
    }
}
