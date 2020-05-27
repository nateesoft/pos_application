package test;

public class TestlOop {

    public static void main(String[] args) {
        int count = 1;
        for (int i = 1; i <= 10; i++) {
            count = i;
            for (int j = 0; j < 15; j++) {
                System.out.print("btn"+count + "|");
                count += 10;
            }
            System.out.println("");
        }
    }
}
