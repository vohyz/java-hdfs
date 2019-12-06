package hw2;

public class Producer {
    public static byte[] create(int start, int end) {
        byte[] number = new byte[end-start];
        for(int j = start/1024;j < end/1024;j++) {
            for(int i = 0;i < 1024;i += 4) {
                number[i+1024*j-start] = int2Byte(j)[0];
                number[i+1+1024*j-start] = int2Byte(j)[1];
                number[i+2+1024*j-start] = int2Byte(j)[2];
                number[i+3+1024*j-start] = int2Byte(j)[3];
            }
        }
        return number;
    }

    public static byte[] int2Byte(int i) {  
        byte[] result = new byte[4];  
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }
}