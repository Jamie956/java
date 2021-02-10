package com.jamie.departjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;

import static com.jamie.Utils.jsonLoop;

public class OuputManyMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        JSONObject json = JSONObject.parseObject(value.toString());
        jsonLoop(json, 0);

        for (String jsonKey : json.keySet()) {
            Object jsonValue = json.get(jsonKey);
            //只取文档和数组 类型
            if (jsonValue instanceof JSONArray || jsonValue instanceof JSONObject) {
                k.set(jsonKey);
                v.set(jsonValue.toString());
                context.write(k, v);
            }
        }
    }

}
