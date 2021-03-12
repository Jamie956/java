package com.jamie.cache;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    HashMap<String, String> cacheMap = new HashMap<>();
    Text k = new Text();

    /**
     * 加载缓存文件
     */
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException {
        // 加载cache 文件product
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        String line;
        while (StringUtils.isNotEmpty(line = reader.readLine())) {
            String[] fields = line.split("\t");

            String productId = fields[0];
            String productName = fields[1];
            cacheMap.put(productId, productName);
        }

        // 关闭资源
        IOUtils.closeStream(reader);
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        //读取order
        String line = value.toString();
        String productId = line.split("\t")[1];
        String productName = cacheMap.get(productId);
        k.set(line + "\t" + productName);

        context.write(k, NullWritable.get());
    }
}
