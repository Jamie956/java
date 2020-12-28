package com.jamie.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class WordcountDriver {

    /**
     * 词频统计
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/words"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * ?
     */
    @Test
    public void te2() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class);

        // 如果不设置InputFormat，它默认用的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        // 虚拟存储切片最大值设置4m
        CombineTextInputFormat.setMaxInputSplitSize(job, 1194304);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/words"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * Combine输入数据多，输出时经过合并，输出数据降低。
     */
    @Test
    public void te3() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class);

        job.setCombinerClass(WordcountCombiner.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/words"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * 将WordcountReducer作为Combiner在WordcountDriver驱动类中指定
     */
    @Test
    public void te4() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class);

        job.setCombinerClass(WordcountReducer.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/words"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * map端压缩
     */
    @Test
    public void t5() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();

        // 开启map端输出压缩
        configuration.setBoolean("mapreduce.map.output.compress", true);
        // 设置map端输出压缩方式
        configuration.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);

        Job job = getJob(configuration, WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/words"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * reduce 端压缩
     */
    @Test
    public void te6() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class);

        // 设置reduce端输出压缩开启
        FileOutputFormat.setCompressOutput(job, true);

        // 设置压缩的方式
        FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
//	    FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
//	    FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/words"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
