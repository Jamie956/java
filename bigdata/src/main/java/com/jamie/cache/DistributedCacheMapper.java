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

    HashMap<String, String> pdMap = new HashMap<>();
    Text k = new Text();

    /**
     * 加载缓存文件
     * @param context
     * @throws IOException
     */
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException {
        // 加载cache
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        String line;
        while (StringUtils.isNotEmpty(line = reader.readLine())) {
            String[] fileds = line.split("\t");
            //id 品牌
            pdMap.put(fileds[0], fileds[1]);
        }

        // 关闭资源
        IOUtils.closeStream(reader);
    }


    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String pid = line.split("\t")[1];
        String productName = pdMap.get(pid);
        k.set(line + "\t" + productName);

        context.write(k, NullWritable.get());
    }
}
