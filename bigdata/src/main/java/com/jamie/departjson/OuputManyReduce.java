package com.jamie.departjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OuputManyReduce extends Reducer<Text, Text, NullWritable, Text> {
    MultipleOutputs<NullWritable, Text> multipleOutputs;
    Text v = new Text();

    @Override
    protected void setup(Context context) {
        multipleOutputs = new MultipleOutputs<>(context);
    }

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            Object object = JSONObject.parse(value.toString());
            //如果是数组，每个元素写出一次
            if (object instanceof JSONArray) {
                JSONArray array = (JSONArray) object;
                for (Object e : array) {
                    v.set(e.toString());
                    multipleOutputs.write(NullWritable.get(), v, key.toString());
                }
            } else {
                //重命名输出文件
                multipleOutputs.write(NullWritable.get(), value, key.toString());
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //如果不关闭，输出文件没数据
        multipleOutputs.close();
    }

}

