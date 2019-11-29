package hw2;

import hw2.Producer;
import java.nio.file.*;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel.*;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.io.IOException;
public class MTWriter {
    public static void main(String [] args) {
        Path path = Paths.get("output.txt");
        String number = Producer.create();
        long startTime = System.currentTimeMillis();    //获取开始时间
        CharBuffer charBuffer = CharBuffer.wrap(number);
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, 2014*512*256);
            if (mappedByteBuffer != null) {
                mappedByteBuffer.put(Charset.forName("UTF-8").encode(charBuffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}