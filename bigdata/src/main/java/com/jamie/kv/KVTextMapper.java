package com.jamie.kv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KVTextMapper extends Mapper<Text, Text, Text, IntWritable> {
    IntWritable v = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        //key不再时首字符数，而是指定分割符的key
        context.write(key, v);
    }
}
