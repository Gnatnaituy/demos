package com.hasaker.video;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class VideoInfoWritable implements Writable {
    private Long gold;
    private Long watchnumpv;
    private Long follower;
    private Long length;

    public Long getGold() {
        return gold;
    }

    public Long getWatchnumpv() {
        return watchnumpv;
    }

    public Long getFollower() {
        return follower;
    }

    public Long getLength() {
        return length;
    }

    public void set(Long gold, Long watchnumpv, Long follower, Long length) {
        this.gold = gold;
        this.watchnumpv = watchnumpv;
        this.follower = follower;
        this.length = length;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(gold);
        out.writeLong(watchnumpv);
        out.writeLong(follower);
        out.writeLong(length);
    }

    /**
     * read order should equal to writer order
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        this.gold = in.readLong();
        this.watchnumpv = in.readLong();
        this.follower = in.readLong();
        this.length = in.readLong();
    }

    @Override
    public String toString() {
        return gold + "\t" + watchnumpv + "\t" + follower + "\t" + length;
    }
}
