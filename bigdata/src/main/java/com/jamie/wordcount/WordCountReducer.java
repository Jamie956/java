package com.jamie.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 进行词频统计
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    int count;
    IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        count = 0;
        for (IntWritable value : values) {
            count += value.get();
        }
        v.set(count);
        context.write(key, v);
    }
}