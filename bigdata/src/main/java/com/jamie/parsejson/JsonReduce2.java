package com.jamie.parsejson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

public class JsonReduce2 extends Reducer<Text, Text, NullWritable, Text> {

    private MultipleOutputs<NullWritable, Text> multipleOutputs;

    Text v = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
    }

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        if ("list".equals(key.toString())) {
            //如果是数组，每个元素写出一次
            for (Text value : values) {
                JSONArray list = JSONObject.parseArray(value.toString());
                for (Object ele : list) {
                    v.set(ele.toString());
                    multipleOutputs.write(NullWritable.get(), v, key.toString());
                }
            }
        } else {
            for (Text value : values) {
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

