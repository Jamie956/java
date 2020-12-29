package com.jamie;

import com.jamie.friends.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class Driver {
    /**
     * friends 1
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(Driver.class, OneShareFriendsMapper.class, OneShareFriendsReducer.class, Text.class, Text.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/friends"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * friends 2
     */
    @Test
    public void t2() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(Driver.class, TwoShareFriendsMapper.class, TwoShareFriendsReducer.class, Text.class, Text.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/friends2"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
