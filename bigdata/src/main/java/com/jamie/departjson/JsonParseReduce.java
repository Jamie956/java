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

public class JsonParseReduce extends Reducer<Text, Text, NullWritable, Text> {
    private MultipleOutputs<NullWritable, Text> multipleOutputs;
    Text v = new Text();

    static Properties p;

    static {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/test.properties"));
            p = new Properties();
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                for (Object o : (JSONArray) object) {
                    v.set(o.toString());
                    multipleOutputs.write(NullWritable.get(), v, key.toString());
                }
            } else {
                convertJsonKey(key, value);
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

    //根据资源文件转换 json 的key
    public void convertJsonKey(Text key, Text value) {
        JSONObject destJson = new JSONObject();
        JSONObject srcJson = JSONObject.parseObject(value.toString());
        for (String k : srcJson.keySet()) {
            String propertyValue = p.getProperty(key.toString() + "." + k);
            if (propertyValue != null) {
                destJson.put(propertyValue, srcJson.get(k));
            } else {
                destJson.put(k, srcJson.get(k));
            }
        }
        value.set(destJson.toString());
    }

}

