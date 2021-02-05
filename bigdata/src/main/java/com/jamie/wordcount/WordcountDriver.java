package com.jamie.wordcount;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.jamie.Utils.initJob;

public class WordcountDriver {

    public static final String RESOURCE = "src/main/resources/";
    public static final Path SRC_PATH = new Path(RESOURCE);

    /**
     * 词频统计
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));

        Job job = initJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/words"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));

        job.waitForCompletion(true);
    }

    /**
     * map -> combine -> reduce
     * 减少reduce 阶段的处理数据量，减少网络IO
     * <p>
     * Combine input records=36
     * Combine output records=5
     */
    @Test
    public void te3() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));

        Job job = initJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

        //设置 combine
        job.setCombinerClass(WordcountCombiner.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/words"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));

        job.waitForCompletion(true);
    }

    /**
     * map端压缩
     */
    @Test
    public void t5() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));

        Configuration configuration = new Configuration();
        // 开启map端输出压缩
        configuration.setBoolean("mapreduce.map.output.compress", true);
        // 设置map端输出压缩方式
        configuration.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);

        Job job = initJob(configuration, WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/words"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));

        job.waitForCompletion(true);
    }

    /**
     * reduce 端压缩
     */
    @Test
    public void te6() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));

        Job job = initJob(WordcountDriver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

        // 设置reduce端输出压缩开启
        FileOutputFormat.setCompressOutput(job, true);

        // 设置压缩的方式
        FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
//	    FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
//	    FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/words"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));

        job.waitForCompletion(true);
    }
}
