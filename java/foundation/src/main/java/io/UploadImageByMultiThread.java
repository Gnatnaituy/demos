package io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


public class UploadImageByMultiThread {
    private static final List<String> imagePostfixes = Arrays.asList("jpg", "png", "jpeg", "gif", "bmp");

    public static void main(String[] args) {
        String src = "C:\\Users\\Hasaker\\Desktop\\originImage";
        String dest = "C:\\Users\\Hasaker\\Desktop\\lalala";
        long start = System.currentTimeMillis();
        copyImage(new File(src), new File(dest));
        System.out.println("Cost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void copyImage(File src, File dest) {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }

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
    private final File src;
    private final File dest;

    CopyThread(File src, File dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void run() {
        try (FileInputStream in = new FileInputStream(src); FileOutputStream out = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
