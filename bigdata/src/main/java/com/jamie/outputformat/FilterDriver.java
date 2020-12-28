package com.jamie.outputformat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class FilterDriver {
    /**
     * 过滤输入的log日志，包含atguigu的网站输出到 atguigu.log，不包含atguigu的网站输出到 other.log
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(FilterDriver.class, FilterMapper.class, FilterReducer.class, Text.class, NullWritable.class);

        job.setOutputFormatClass(FilterOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/outputformat"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
