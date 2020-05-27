package util;

public class StringCount {
    public static int countThai(String txt){
        int count = 0;
        char[] zero = {'ิ','ี','ุ','ู','ึ','ื','่','้','๊','๋','็','์','ั','ํ'};
        char[] ctxt = txt.toCharArray();
        boolean chk = false;
        L1:
        for(char c : ctxt){
            if(c == 'แ'){
                count += 2;
            }else{
                L2:
                for(char z : zero){
                    if( c == z){
                        chk = true;
                        break L2;
                    }else{
                        chk = false;
                    }
                }
                if(!chk){
                    count++;
                }
            }
        }
        return count;
    }
    public static int indexOfLastThai(String txt, int length){
        int index = -1;
        int count = 0;
        char[] zero = {'ิ','ี','ุ','ู','ึ','ื','่','้','๊','๋','็','์','ั','ํ'};
        char[] ctxt = txt.toCharArray();
        boolean chk = false;
        L1:
        for(int i=0;i<ctxt.length;i++){
            char c = ctxt [i];
            if(c == 'แ'){
                count += 2;
            }else{
                L2:
                for(char z : zero){
                    if( c == z){
                        chk = true;
                        break L2;
                    }else{
                        chk = false;
                    }
                }
                if(!chk){
                    count++;
                }
            }
            if(count == length+1){
                index = i-1;
                break L1;
            }
        }
        return index;
    }
    public static void main(String[] args) {
        System.out.println(countThai("สวัสดีครับ"));
    }
}
