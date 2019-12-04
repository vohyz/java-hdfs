package hw2;

import hw2.Producer;
import java.nio.file.*;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.io.IOException;
public class MTWriter {
    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        Path path = Paths.get("output.txt");
        byte[] number = Producer.create();
        ByteBuffer byteBuffer = ByteBuffer.wrap(number);
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, 2014*512*256*4);
            if (mappedByteBuffer != null) {
                mappedByteBuffer.put(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}