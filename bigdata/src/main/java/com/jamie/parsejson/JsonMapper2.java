package com.jamie.parsejson;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class JsonMapper2 extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lines = value.toString();
        JSONObject json = JSONObject.parseObject(lines);

        for (String jsonKey : json.keySet()) {
            k.set(jsonKey);
            v.set(json.getString(jsonKey));
            context.write(k, v);
        }
    }

}
