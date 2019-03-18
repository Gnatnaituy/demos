package io;


import java.io.*;
import java.util.*;


public class UploadImageUsingMultiThread {
    private static List<String> imagePostfixes = Arrays.asList("jpg", "png", "jpeg", "gif", "bmp");

    public static void main(String[] args) {
        String src = "C:\\Users\\Hasaker\\Desktop\\originImage";
        String dest = "C:\\Users\\Hasaker\\Desktop\\lalala";
        long start = System.currentTimeMillis();
        copyImage(new File(src), new File(dest));
        System.out.println("\nCost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void copyImage(File src, File dest) {
        if (src.isDirectory()) {
            if (!dest.exists()) dest.mkdir();
            HashSet<String> destFiles = new HashSet<>(Arrays.asList(Objects.requireNonNull(dest.list())));

            for (String file : Objects.requireNonNull(src.list())) {
                String filePostfix = file.substring(file.lastIndexOf('.') + 1);

                if (imagePostfixes.contains(filePostfix)) {
                    File srcFile = new File(src, file);
                    while (destFiles.contains(file)) {
                        file = file.substring(0, file.lastIndexOf('.')) + "_copy." + filePostfix;
                    }
                    File destFile = new File(dest, file);
                    copyImage(srcFile, destFile);
                }
            }
        } else {
            new Thread(new CopyThread(src, dest)).start();
        }
    }
}


class CopyThread implements Runnable {
    private File src;
    private File dest;

    CopyThread(File src, File dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void run() {
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
//            System.out.println("Copy " + src.getName() + " to " + dest.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
