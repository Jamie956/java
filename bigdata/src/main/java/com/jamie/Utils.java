package com.jamie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Utils {
    public static Job initJob(Class<?> driverClass,
                              Class<? extends Mapper> mapperClass,
                              Class<? extends Reducer> reduceClass,
                              Class<?> mapperKey,
                              Class<?> mapperValue,
                              Class<?> reduceKey,
                              Class<?> reduceValue) throws IOException {

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

    public static Job initJob(Configuration conf,
                              Class<?> driverClass,
                              Class<? extends Mapper> mapperClass,
                              Class<? extends Reducer> reduceClass,
                              Class<?> mapperKey,
                              Class<?> mapperValue,
                              Class<?> reduceKey,
                              Class<?> reduceValue) throws IOException {

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

    public static Job getJob(Class<?> jarClaz, Class<? extends Mapper> mapClaz, Class<? extends Reducer> reduceClaz, Class<?> mapOutKey, Class<?> mapOutVal, Class<?> outKey, Class<?> outVal) throws IOException {
        Configuration conf = new Configuration();
        return getJob(conf, jarClaz, mapClaz, reduceClaz, mapOutKey, mapOutVal, mapOutKey, mapOutVal);
    }

    public static Job getJob(Class<?> jarClaz, Class<? extends Mapper> mapClaz, Class<? extends Reducer> reduceClaz, Class<?> mapOutKey, Class<?> mapOutVal) throws IOException {
        return getJob(jarClaz, mapClaz, reduceClaz, mapOutKey, mapOutVal, mapOutKey, mapOutVal);
    }

    public static Job getJob(Configuration conf, Class<?> jarClaz, Class<? extends Mapper> mapClaz, Class<? extends Reducer> reduceClaz, Class<?> mapOutKey, Class<?> mapOutVal, Class<?> outKey, Class<?> outVal) throws IOException {

        Job job = Job.getInstance(conf);
        job.setJarByClass(jarClaz);

        job.setMapperClass(mapClaz);
        job.setReducerClass(reduceClaz);

        job.setMapOutputKeyClass(mapOutKey);
        job.setMapOutputValueClass(mapOutVal);

        job.setOutputKeyClass(outKey);
        job.setOutputValueClass(outVal);

        return job;
    }

    public static Job getJob(Configuration conf, Class<?> jarClaz, Class<? extends Mapper> mapClaz, Class<? extends Reducer> reduceClaz, Class<?> mapOutKey, Class<?> mapOutVal) throws IOException {
        return getJob(conf, jarClaz, mapClaz, reduceClaz, mapOutKey, mapOutVal, mapOutKey, mapOutVal);
    }
}
