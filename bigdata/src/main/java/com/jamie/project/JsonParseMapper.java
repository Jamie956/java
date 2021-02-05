package com.jamie.project;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.utils.IDGenerator;
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
        JSONObject lineJson = JSONObject.parseObject(lines);

        //获取当前行的公司名
        String compName = lineJson.getString("company_name");

        //按json的外层key 遍历，相同的json key 作为mapper key 写出
        for (String outerKey : lineJson.keySet()) {
            Object outerValue = lineJson.get(outerKey);
            String outValue = "";

            //如果 lineJson value 是数组，每个数组元素加一个公司名
            if (outerValue instanceof JSONArray) {
                JSONArray list = (JSONArray) outerValue;
                for (Object item : list) {
                    long snowId = IDGenerator.nextId();
                    JSONObject subObject = (JSONObject) JSONObject.toJSON(item);

                    subObject.put("company_name", compName);
                    subObject.put("snow_id", snowId);

                    for (String subKey : subObject.keySet()) {
                        Object subValue = subObject.get(subKey);
                        if (subValue instanceof JSONArray) {
                            JSONArray subList = (JSONArray) subValue;
                            for (Object subSubItem : subList) {
                                JSONObject subSubObject = (JSONObject) JSONObject.toJSON(subSubItem);
                                subSubObject.put("snow_id", snowId);

                            }
                        }
                    }
                }
                outValue = outerValue.toString();
            } else if (outerValue instanceof JSONObject){
                JSONObject subJson = (JSONObject) JSONObject.toJSON(outerValue);

                //如果 lineJson value 是文档
                subJson.put("company_name", compName);
                subJson.put("id", IDGenerator.nextId());
                outValue = outerValue.toString();
            }

            k.set(outerKey);
            v.set(outValue);

            if (!"".equals(outValue)) {
                context.write(k, v);
            }
        }
    }

}
