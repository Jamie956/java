package com.jamie.order;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class OrderDriver {

    /**
     * WritableComparable 对象重写排序方法
     */
    @Test
    public void t0() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(OrderDriver.class, OrderMapper.class, OrderReducer.class, OrderBean.class, NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/orderinfo"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * 要求出每一个订单中最贵的商品
     * <p>
     * orderId productId price
     * 0000001	Pdt_01	222.8
     * 0000002	Pdt_05	722.4
     * 0000001	Pdt_02	33.8
     * 0000003	Pdt_06	232.8
     * 0000003	Pdt_02	33.8
     * 0000002	Pdt_03	522.8
     * 0000002	Pdt_04	122.4
     * <p>
     * 预期
     * 1	222.8
     * 2	722.4
     * 3	232.8
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(OrderDriver.class, OrderMapper.class, OrderReducer.class, OrderBean.class, NullWritable.class);

        job.setGroupingComparatorClass(OrderGroupingComparator.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/orderinfo"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
