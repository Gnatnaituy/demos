package com.hasaker.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AnchorVideoMapper extends Mapper<LongWritable, Text, Text, VideoInfoWritable> {
    private Text k2 = new Text();
    private VideoInfoWritable v2 = new VideoInfoWritable();

    @Override
    protected void map(
            LongWritable k1,
            Text v1,
            Mapper<LongWritable, Text, Text, VideoInfoWritable>.Context context)
            throws IOException, InterruptedException {
        String line = v1.toString();
        JSONObject jsonObject = JSON.parseObject(line);
        k2.set(jsonObject.getString("uid"));
        v2.set(jsonObject.getLong("gold"), jsonObject.getLong("watchnumpv"),
                jsonObject.getLong("follower"), jsonObject.getLong("length"));
        context.write(k2, v2);
    }
}