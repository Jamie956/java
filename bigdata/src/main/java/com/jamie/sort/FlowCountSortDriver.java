package com.jamie.sort;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class FlowCountSortDriver {

    /**
     * 全排序
     * <p>
     * 数据
     * 13956435636	132		1512	1644
     * 13509468723	7335	110349	117684
     * 13846544121	264		0		264
     * 13736230513	2481	24681	27162
     * <p>
     * 预期
     * 13509468723	7335	110349	117684
     * 13736230513	2481	24681	27162
     * 13956435636	132		1512	1644
     * 13846544121	264		0		264
     */
    @Test
    public void t0() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(FlowCountSortDriver.class, FlowCountSortMapper.class, FlowCountSortReducer.class, FlowBean.class, Text.class, Text.class, FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/sort"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * 分区内排序
     */
    @Test
    public void te2() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(FlowCountSortDriver.class, FlowCountSortMapper.class, FlowCountSortReducer.class, FlowBean.class, Text.class, Text.class, FlowBean.class);

        // 关联分区
        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/sort"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

}
