package io;

import java.io.*;

/**
 * 比较 FileInputStream/FileOutputStream, BufferedInputStream/BufferedOutputStream 读写性能
 */
public class SpeedTest {

    public static void main(String[] aegs) {
        String src = "C:\\Users\\Hasaker\\Desktop\\twrp-3.2.3-1-z2_row.img";
        String src2 = "C:\\Users\\Hasaker\\Desktop\\Magisk-v18.0.zip";
        String dest1 = "C:\\Users\\Hasaker\\Desktop\\copy\\magisk.zip";
        String dest2 = "C:\\Users\\Hasaker\\Desktop\\copy\\twrp2.img";
        String dest3 = "C:\\Users\\Hasaker\\Desktop\\copy\\twrp3.img";
        testFileInputOutputStream(src2, dest1);             // 53537ms   50438ms    无法复制完成
//        testFileInputOutputStreamWithBuffer(src, dest2);   // 55833ms   54935ms
//        testBufferedInputOutputStream(src, dest3);         // 3167ms    2993ms
    }

    private static void testFileInputOutputStream(String src, String dest) {
        try (FileInputStream inputStream = new FileInputStream(src);
             FileOutputStream outputStream = new FileOutputStream(dest)) {
            int len;
            long begin = System.currentTimeMillis();
            while ((len = inputStream.read()) != -1) outputStream.write(len);
            System.out.println(System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFileInputOutputStreamWithBuffer(String src, String dest) {
        try (FileInputStream inputStream = new FileInputStream(src);
             FileOutputStream outputStream = new FileOutputStream(dest)) {
            int len;
            byte[] buffer = new byte[1024];
            long begin = System.currentTimeMillis();
            while ((len = inputStream.read()) != -1) outputStream.write(buffer, 0, len);
            System.out.println(System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testBufferedInputOutputStream(String src, String dest) {
        try (FileInputStream inputStream = new FileInputStream(src);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             FileOutputStream outputStream = new FileOutputStream(dest);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            int len;
            byte[] buffer = new byte[1024];
            long begin = System.currentTimeMillis();
            while ((len = bufferedInputStream.read()) != -1) bufferedOutputStream.write(buffer, 0, len);
            System.out.println(System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
