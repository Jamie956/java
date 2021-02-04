package com.jamie.parsejson;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class JsonDriver {

    @Test
    public void t0() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(JsonDriver.class, JsonMapper.class, JsonReduce.class, Pojo.class, NullWritable.class, Pojo.class, NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/publicly"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

}
