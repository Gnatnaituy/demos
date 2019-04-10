package io;

import java.io.*;

/**
 * 比较 FileInputStream/FileOutputStream, BufferedInputStream/BufferedOutputStream 读写性能
 */
public class SpeedTest {

    public static void main(String[] aegs) {
        String src = "C:\\Users\\Hasaker\\Desktop\\Manjaro.iso";
        String dest1 = "C:\\Users\\Hasaker\\Desktop\\copy\\Manjaro1.iso";
        String dest2 = "C:\\Users\\Hasaker\\Desktop\\copy\\Manjaro2.iso";
        String dest3 = "C:\\Users\\Hasaker\\Desktop\\copy\\Manjaro3.iso";
//        testFileInputOutputStream(src, dest1);
        testFileInputOutputStreamWithBuffer(src, dest2);
        testBufferedInputOutputStream(src, dest3);
    }

    private static void testFileInputOutputStream(String src, String dest) {
        try (FileInputStream inputStream = new FileInputStream(src);
             FileOutputStream outputStream = new FileOutputStream(dest)) {
            int b;
            long begin = System.currentTimeMillis();

            while ((b = inputStream.read()) != -1)
                outputStream.write(b);

            System.out.println(System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFileInputOutputStreamWithBuffer(String src, String dest) {
        try (FileInputStream inputStream = new FileInputStream(src);
             FileOutputStream outputStream = new FileOutputStream(dest)) {
            int len;
            byte[] buffer = new byte[1024 * 1024];
            long begin = System.currentTimeMillis();

            while ((len = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, len);

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
            byte[] buffer = new byte[1024 * 1024];
            long begin = System.currentTimeMillis();

            while ((len = bufferedInputStream.read(buffer)) != -1)
                bufferedOutputStream.write(buffer, 0, len);

            System.out.println(System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
