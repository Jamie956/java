package com.jamie.flowsum;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class FlowsumDriver {
    /**
     * 输入：
     * id	手机号码		网络ip			上行流量  下行流量     网络状态码
     * 7	13560436666	120.196.100.99	1116	954	200
     * <p>
     * 输出：
     * 13560436666 		1116		      954 			2070
     * 手机号码		    上行流量        下行流量		总流量
     */
    @Test
    public void te1() throws InterruptedException, IOException, ClassNotFoundException {
        Job job = getJob(FlowsumDriver.class, FlowCountMapper.class, FlowCountReducer.class, Text.class, FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/phone"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * 手机号136、137、138、139开头都分别放到一个独立的4个文件中，其他开头的放到一个文件中。
     */
    @Test
    public void te2() throws InterruptedException, IOException, ClassNotFoundException {
        Job job = getJob(FlowsumDriver.class, FlowCountMapper.class, FlowCountReducer.class, Text.class, FlowBean.class);

        // 8 指定自定义数据分区
        job.setPartitionerClass(ProvincePartitioner.class);
        // 9 同时指定相应数量的reduce task
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/phone"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
