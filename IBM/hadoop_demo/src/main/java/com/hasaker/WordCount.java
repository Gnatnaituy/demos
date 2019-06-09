package com.hasaker;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;

public class WordCount {

    /**
     * Every line in the file calls map() once
     * Input:  <k1, v1>
     * Output: <k2, v2>
     */
    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
        Text k2 = new Text();
        LongWritable v2 = new LongWritable();

        @Override
        protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
                throws IOException, InterruptedException {
             String line = v1.toString();
             String[] splits = line.split("\t");
             for (String word : splits) {
                 k2.set(word);
                 v2.set(1);
                 context.write(k2, v2);
             }
        }
    }

    /**
     * For every <k2, v2>, call reduce() once.
     * Input:  <k2, v2>
     * Output: <k3, v3>
     */
    public static class MyReduce extends Reducer<Text, LongWritable, Text, LongWritable> {
        Text k3 = new Text();
        LongWritable v3 = new LongWritable();
        long sum = 0;

        @Override
        protected void reduce(Text k2, Iterable<LongWritable> v2s, Reducer<Text, LongWritable, Text, LongWritable>.Context context)
            throws IOException, InterruptedException {
            Iterator<LongWritable> iterator = v2s.iterator();
            sum = 0;
            while (iterator.hasNext()) {
                LongWritable next = iterator.next();
                sum += next.get();
            }
            k3.set(k2);
            v3.set(sum);
            context.write(k3, v3);
        }
    }

    /**
     * Construct a job by map and reduce
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Program needs input path and output path!");
            System.exit(10);
        }

        // Get input and output path
        String inputPath = args[0];
        // Output path should not be existed
        String outputPath = args[1];

        // Get Configuration class
        Configuration configuration = new Configuration();
        // Get job
        String jobName = WordCount.class.getSimpleName();
        Job job = Job.getInstance(configuration, jobName);

        // Assemble jar package
        job.setJarByClass(WordCount.class);

        // Configure input and output path
        FileInputFormat.setInputPaths(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // Configure map parameters
        job.setMapperClass(MyMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // Configure reduce parameters
        job.setReducerClass(MyReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // Assemble job, waiting for job execution successful
        job.waitForCompletion(true);
    }
}
