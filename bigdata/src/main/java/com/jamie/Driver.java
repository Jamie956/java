package com.jamie;

import com.jamie.friends.*;
import com.jamie.sort.FlowCountSortMapper;
import com.jamie.sort.FlowCountSortReducer;
import com.jamie.sort.ProvincePartitioner;
import com.jamie.table.TableBean;
import com.jamie.table.TableMapper;
import com.jamie.table.TableReducer;
import com.jamie.topn.FlowBean;
import com.jamie.topn.TopNMapper;
import com.jamie.topn.TopNReducer;
import com.jamie.wordcount.WordcountCombiner;
import com.jamie.wordcount.WordcountMapper;
import com.jamie.wordcount.WordcountReducer;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.jamie.Utils.getJob;
import static com.jamie.Utils.initJob;

public class Driver {
    public static final String RESOURCE = "src/main/resources/";
    public static final Path SRC_PATH = new Path(RESOURCE);

    /**
     * 词频统计
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));

        Job job = initJob(Driver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

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

        Job job = initJob(Driver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

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

        Job job = initJob(configuration, Driver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

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

        Job job = initJob(Driver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);

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

    /**
     * 输出流量使用量在前10的用户信息
     */
    @Test
    public void t0() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, TopNMapper.class, TopNReducer.class, FlowBean.class, Text.class, Text.class, FlowBean.class);
        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/top10"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));
        job.waitForCompletion(true);
    }

    /**
     * reduce 合并两个表
     */
    @Test
    public void t10() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, TableMapper.class, TableReducer.class, Text.class, TableBean.class, TableBean.class, NullWritable.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/table"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));
        job.waitForCompletion(true);
    }

    /**
     * 全排序
     * <p>
     * 数据
     * 13956435636	132		1512	1644
     * 13509468723	7335	110349	117684
     * 13846544121	264		0		264
     * 13736230513	2481	24681	27162
     * <p>
     * 预期
     * 13509468723	7335	110349	117684
     * 13736230513	2481	24681	27162
     * 13956435636	132		1512	1644
     * 13846544121	264		0		264
     */
    @Test
    public void t11() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, FlowCountSortMapper.class, FlowCountSortReducer.class, FlowBean.class, Text.class, Text.class, FlowBean.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/sort"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));
        job.waitForCompletion(true);
    }

    /**
     * 分区内排序
     */
    @Test
    public void te12() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, FlowCountSortMapper.class, FlowCountSortReducer.class, FlowBean.class, Text.class, Text.class, FlowBean.class);

        // 关联分区
        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/sort"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));
        job.waitForCompletion(true);
    }

    /**
     * friends 1
     * <p>
     * 人：好友
     * A:B,C,D,F,E,O
     * B:A,C,E,K
     * C:F,A,D,I
     * D:A,E,F,L
     * E:B,C,D,M,L
     * <p>
     * 人    关注者
     * A	I,K,C,B,G,F,H,O,D,
     * B	A,F,J,E,
     * C	A,E,B,H,F,G,K,
     * D	G,C,K,A,L,F,E,H,
     * E	G,M,L,H,A,F,B,D,
     */
    @Test
    public void t7() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(Driver.class, OneShareFriendsMapper.class, OneShareFriendsReducer.class, Text.class, Text.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/friends"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * friends 2
     * <p>
     * 人    关注者
     * A	I,K,C,B,G,F,H,O,D,
     * B	A,F,J,E,
     * C	A,E,B,H,F,G,K,
     * D	G,C,K,A,L,F,E,H,
     * E	G,M,L,H,A,F,B,D,
     * <p>
     * A与B 的共同好友 E, C
     * A-B	E C
     */
    @Test
    public void t8() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(Driver.class, TwoShareFriendsMapper.class, TwoShareFriendsReducer.class, Text.class, Text.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/friends2"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
