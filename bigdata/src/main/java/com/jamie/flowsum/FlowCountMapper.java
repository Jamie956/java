package com.jamie.flowsum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text, Text, Counter> {
    Text k = new Text();
    Counter v = new Counter();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");

        String phone = fields[0];
        int count = Integer.parseInt(fields[1]);

        k.set(phone);
        v.setCount(count);
        //写出 key 电话号码，value 对象，将电话号码一样的对象聚集在一起
        context.write(k, v);
    }
}
