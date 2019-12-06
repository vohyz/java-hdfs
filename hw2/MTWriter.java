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
public class MTWriter{

    public static class Twriter implements Runnable{
        int run_start;
        int run_end;

        Twriter (int m, int n) {
            run_start = m;
            run_end = n;
        }

        public void run() {
            System.out.println(run_start);
            write2File(run_start, run_end);
            System.out.println(run_end);
        }

        public void write2File(int start, int end) {
            Path path = Paths.get("output.txt");
            byte[] number = Producer.create(start, end);
            ByteBuffer byteBuffer = ByteBuffer.wrap(number);
            try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING)) {
                MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, start, end-start);
                if (mappedByteBuffer != null) {
                    mappedByteBuffer.put(byteBuffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void countTime(int n) {
        Thread[] threads = new Thread[n];
        
        long startTime = System.currentTimeMillis();    //获取开始时间
        
        try {
            for(int i = 0;i < n;i++){
                threads[i] = new Thread(new Twriter(i*1024*256*4/n,(i+1)*1024*256*4/n));
            }
            for(int i = 0;i < n;i++){
                threads[i].start();
            }
            for(int i = 0;i < n;i++){
                threads[i].join(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

    public static void main(String [] args) {
        countTime(8);
    }
}