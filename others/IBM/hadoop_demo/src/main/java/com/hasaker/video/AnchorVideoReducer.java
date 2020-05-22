package com.hasaker.video;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AnchorVideoReducer extends Reducer<Text, VideoInfoWritable, Text, VideoInfoWritable> {
    private final VideoInfoWritable v3 = new VideoInfoWritable();

    @Override
    protected void reduce(Text k2, Iterable<VideoInfoWritable> v2s,
            Reducer<Text, VideoInfoWritable, Text, VideoInfoWritable>.Context context)
            throws IOException, InterruptedException {
        long gold = 0L;
        long watchnumpv = 0L;
        long follower = 0L;
        long length = 0L;

        for (VideoInfoWritable v2: v2s) {
            gold += v2.getGold();
            watchnumpv += v2.getWatchnumpv();
            follower += v2.getFollower();
            length += v2.getLength();
        }
        v3.set(gold, watchnumpv, follower, length);
        context.write(k2, v3);
    }
}
