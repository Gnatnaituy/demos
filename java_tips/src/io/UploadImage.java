package io;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class UploadImage {

    public static void main(String[] args) {
        String srcUrl = "C:\\Users\\Hasaker\\Desktop\\originImage";
        String destUrl = "C:\\Users\\Hasaker\\Desktop\\lalala";
        long start = System.currentTimeMillis();
        copyImagesInFolder(new File(srcUrl), new File(destUrl));
        System.out.println("\nCost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void copyImagesInFolder(File src, File dest) {
        if (src.isDirectory()) {
            // Destination folder didn't exists. create it.
            if (!dest.exists()) dest.mkdir();
            HashSet<String> destFiles = new HashSet<>(Arrays.asList(Objects.requireNonNull(dest.list())));

            for (String file : Objects.requireNonNull(src.list())) {
                // Get the file's postfix
                String[] imagePostfixes = {"jpg", "png", "jpeg", "gif", "bmp"};
                String filePostfix = file.substring(file.lastIndexOf('.') + 1);

                // Check if the file is an image
                if (Arrays.asList(imagePostfixes).contains(filePostfix)) {
                    File srcFile = new File(src, file);
                    // Add "_copy" to the filename's tail if duplicate
                    if (destFiles.contains(file)) {
                        file = file.substring(0, file.lastIndexOf('.')) + "_copy." + filePostfix;
                    }
                    File destFile = new File(dest, file);
                    copyImagesInFolder(srcFile, destFile);
                }
            }
        } else {
            try (InputStream in = new FileInputStream(src);
                 OutputStream out = new FileOutputStream(dest)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) out.write(buffer, 0, length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 }
