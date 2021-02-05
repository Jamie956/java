package com.jamie.project;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

public class JsonParseReduce extends Reducer<Text, Text, NullWritable, Text> {

    private MultipleOutputs<NullWritable, Text> multipleOutputs;

    Text v = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
    }

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {

            Object object = JSONObject.parse(value.toString());
            //如果是数组，每个元素写出一次
            if (object instanceof JSONArray) {
                for (Object o : (JSONArray) object) {
                    v.set(o.toString());
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

