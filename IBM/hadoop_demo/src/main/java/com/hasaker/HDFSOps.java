package com.hasaker;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSOps {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.18.0.2:9000");
        // Get the instance of HDFS FileSystem
        FileSystem fs = FileSystem.get(conf);
        // Upload
        // Configure path
        Path path = new Path("hdfs://172.18.0.2:9000/root/local_bashrc");
        // Get the output stream of fs
        FSDataOutputStream outputStream = fs.create(path);
        // Get the input stream of local file
        FileInputStream input = new FileInputStream("~/.bashrc");
        // Copy input stream to output stream
        IOUtils.copy(input, outputStream);

        System.out.println("Translate Successful!");
    }
}