package com.jamie.parsejson;

import com.google.gson.Gson;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class JsonMapper extends Mapper<LongWritable, Text,Pojo, NullWritable> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lines = value.toString();
        context.write(new Gson().fromJson(lines,Pojo.class),NullWritable.get());
    }
}
