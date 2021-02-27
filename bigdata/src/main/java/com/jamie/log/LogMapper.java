package com.jamie.log;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
        if (fields.length < 11) {
            context.getCounter("map", "false").increment(1);
            return;
        }
        context.getCounter("map", "true").increment(1);
        k.set(line);
        context.write(k, NullWritable.get());
    }

}
