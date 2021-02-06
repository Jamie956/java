package com.jamie.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    String fileName;
    IntWritable v = new IntWritable(1);

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context) {
        // 获取文件名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(" ");
        //每个词 连接 所在的文件名
        for (String word : fields) {
            context.write(new Text(word + "--" + fileName), v);
        }
    }
}
