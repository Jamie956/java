package com.jamie.phonedata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 输入数据格式：
 * 7 	13560436666	120.196.100.99		1116		 954			200
 * id	手机号码		网络ip			上行流量  下行流量     网络状态码
 *
 * 期望输出数据格式
 * 13560436666 		1116		      954 			2070
 * 手机号码		    上行流量        下行流量		总流量
 */
public class FlowsumDriver {

    public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, InterruptedException, IOException {
        args = new String[] { "bigdata/src/main/resources/phone_data.txt", "bigdata/src/main/resources/out" };

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(FlowsumDriver.class);
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
