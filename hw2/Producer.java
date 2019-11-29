package hw2;

public class Producer {
    public static String create() {
        String number = "";
        for(int j = 0;j < 2014*512;j++) {
            for(int i = 0;i < 256;i ++) {
                String str = String.valueOf(j);
                number += str;
            }
        }
        return number;
    }
}