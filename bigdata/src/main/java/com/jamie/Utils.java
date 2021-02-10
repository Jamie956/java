package com.jamie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.utils.IDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    public static final String RESOURCE = "src/main/resources/";
    public static final Path SRC_PATH = new Path(RESOURCE);

    private static Properties p;

    static {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/test.properties"));
            p = new Properties();
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 替换换行符
     */
    public static Object replaceCrlf(Object value) {
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
        }
        return newValue;
    }

    /**
     * 根据资源文件转换 json 的key
     */
    public static String convertKey(String prefix, String key) {
        String propertyValue = p.getProperty(prefix + "." + key);
        if (propertyValue != null) {
            return propertyValue;
        }
        return key;
    }

    /**
     * json 递归生成树结构
     */
    public static void jsonLoop(Object object, long parentId) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            long id = IDGenerator.nextId();
            json.put("snow_id", id);
            json.put("parent_snow_id", parentId);

            for (String key : json.keySet()) {
                Object value = json.get(key);
                if (value instanceof JSONObject || value instanceof JSONArray) {
                    jsonLoop(value, id);
                } else if (value instanceof String) {
                    //最终的节点
                    json.put(key, value.toString()+"!!");
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray arr = (JSONArray) object;
            for (Object e : arr) {
                jsonLoop(e, parentId);
            }
        }
    }

    public static Job initJob(Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue) throws IOException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        return job;
    }

    public static Job initJob(Configuration conf, Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue) throws IOException {

        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        return job;
    }

    public static Job initJob(Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue, String inPath, String outPath) throws IOException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix(inPath));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix(outPath));

        return job;
    }
}
