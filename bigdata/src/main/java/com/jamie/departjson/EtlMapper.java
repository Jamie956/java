package com.jamie.departjson;

import com.alibaba.fastjson.JSONObject;
import com.jamie.utils.IDGenerator;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

import static com.jamie.Utils.convertKey;
import static com.jamie.Utils.replaceCrlf;

public class EtlMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private MultipleOutputs<Text, NullWritable> multipleOutputs;
    private Text k = new Text();
    private String fileName;

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
        multipleOutputs = new MultipleOutputs<>(context);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        JSONObject srcJson = JSONObject.parseObject(value.toString());
        JSONObject destJson = convertJson(srcJson);
        long id = IDGenerator.nextId();
        destJson.put("snow_id", id);
        k.set(destJson.toString());
        multipleOutputs.write(k, NullWritable.get(), fileName);
    }

    @Override
    protected void cleanup(Mapper.Context context) throws IOException, InterruptedException {
        //如果不关闭，输出文件没数据
        multipleOutputs.close();
    }

    public JSONObject convertJson(JSONObject srcJson) {
        JSONObject destJson = new JSONObject();
        for (String srcKey : srcJson.keySet()) {
            String newKey = convertKey(fileName, srcKey);
            Object newValue = replaceCrlf(srcJson.get(srcKey));
            destJson.put(newKey, newValue);
        }
        return destJson;
    }

}
