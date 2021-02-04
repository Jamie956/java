package com.jamie.parsejson;

import com.alibaba.fastjson.JSONArray;
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

        //按json的外层key 遍历，相同的json key 作为mapper key 写出
        for (String jsonKey : json.keySet()) {
            if ("list".equals(jsonKey)) {
                //如果遍历到key=list，给数组的每个元素加个comp
                JSONArray list = json.getJSONArray("list");
                for (Object o : list) {
                    JSONObject jsonObject = (JSONObject) JSONObject.toJSON(o);
                    jsonObject.put("comp", json.getString("comp"));
                }
                v.set(list.toString());
            } else {
                v.set(json.getString(jsonKey));
            }

            k.set(jsonKey);
            context.write(k, v);
        }
    }

}
