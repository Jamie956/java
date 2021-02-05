package com.jamie.sort;

import com.jamie.topn.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountSortMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean, Text>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        FlowBean k = new FlowBean(Long.parseLong(fields[1]), Long.parseLong(fields[2]), Long.parseLong(fields[3]));
        context.write(k, new Text(fields[0]));
    }

}
