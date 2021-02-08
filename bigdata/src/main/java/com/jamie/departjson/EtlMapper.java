package com.jamie.departjson;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class EtlMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        JSONObject o = JSONObject.parseObject(value.toString());
        for (String jsonKey : o.keySet()) {
            String jsonValue = o.getString(jsonKey);
            //替换换行符
            if (StringUtils.isNoneBlank(jsonValue)) {
                jsonValue = jsonValue.replaceAll("(\\r\\n|\\n|\\n\\r|\\r)","<br/>");
            }

            o.put(jsonKey, jsonValue);
        }
        k.set(o.toString());
        context.write(k, NullWritable.get());
    }

}
