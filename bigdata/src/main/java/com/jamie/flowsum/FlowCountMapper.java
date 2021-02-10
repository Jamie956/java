package com.jamie.flowsum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean1> {
    Text k = new Text();
    FlowBean1 v = new FlowBean1();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");

        String num = fields[0];
        long up = Long.parseLong(fields[1]);
        long down = Long.parseLong(fields[2]);

        k.set(num);
        v.setUpFlow(up);
        v.setDownFlow(down);
        context.write(k, v);
    }
}
