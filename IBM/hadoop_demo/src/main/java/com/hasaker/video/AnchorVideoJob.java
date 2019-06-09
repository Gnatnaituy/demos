package com.hasaker.video;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AnchorVideoJob {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("You should give input and output path parameters!");
            System.exit(0);
        }

        String inputPath = args[0];
        String outputPath = args[1];
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, VideoInfoWritable.class.getSimpleName());
        job.setJarByClass(VideoInfoWritable.class);

        FileInputFormat.setInputPaths(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setMapperClass(AnchorVideoMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(VideoInfoWritable.class);

        job.setReducerClass(AnchorVideoReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(VideoInfoWritable.class);

        job.waitForCompletion(true);
    }
}
