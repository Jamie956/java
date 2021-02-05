package com.jamie.project;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class JsonParseMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lines = value.toString();
        JSONObject json = JSONObject.parseObject(lines);

        String outValue = "";
        //获取当前行的公司名
        String compName = json.getString("company_name");

        //按json的外层key 遍历，相同的json key 作为mapper key 写出
        for (String jsonKey : json.keySet()) {
            Object jsonValue = json.get(jsonKey);
            //如果 json value 是数组，每个数组元素加一个公司名
            if (jsonValue instanceof JSONArray) {
                for (Object o : (JSONArray) jsonValue) {
                    JSONObject jsonObject = (JSONObject)JSONObject.toJSON(o);
                    jsonObject.put("company_name", compName);
                }
                outValue = jsonValue.toString();
            } else if (jsonValue instanceof JSONObject){
                outValue = json.getString(jsonKey);
            }

            k.set(jsonKey);
            v.set(outValue);

            if (!"".equals(outValue)) {
                context.write(k, v);
            }
        }
    }

}
