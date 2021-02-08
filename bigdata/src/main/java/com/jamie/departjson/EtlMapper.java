package com.jamie.departjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EtlMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Text k = new Text();
    private static Properties p;
    String fileName;

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
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) {
        // 获取文件的名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        JSONObject srcJson = JSONObject.parseObject(value.toString());
        JSONObject destJson = convertJson(srcJson);

        k.set(destJson.toString());
        context.write(k, NullWritable.get());
    }


    public JSONObject convertJson(JSONObject srcJson) {
        JSONObject destJson = new JSONObject();
        for (String srcKey : srcJson.keySet()) {
            String newKey = convertKey(srcKey);
            Object newValue = replaceCrlf(srcJson.get(srcKey));
            destJson.put(newKey, newValue);
        }
        return destJson;
    }


    /**
     * 替换换行符
     */
    public Object replaceCrlf(Object value) {
        if (value == null) {
            return null;
        }
        String newValue = "";
        if (StringUtils.isNoneBlank(value.toString())) {
            newValue = value.toString().replaceAll("(\\r\\n|\\n|\\n\\r|\\r)", "<br/>");
        }
        if (value instanceof JSONObject) {
            return JSONObject.parseObject(newValue);
        } else if (value instanceof JSONArray) {
            return JSONObject.parseArray(newValue);
        } else if (value instanceof String) {
            return newValue;
        }
        return "";
    }

    /**
     * 根据资源文件转换 json 的key
     */
    public String convertKey(String key) {
        String propertyValue = p.getProperty(fileName + "." + key);
        if (propertyValue != null) {
            return propertyValue;
        }
        return key;
    }

}
