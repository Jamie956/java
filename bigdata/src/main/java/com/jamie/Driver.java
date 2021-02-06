package com.jamie;

import com.jamie.friends.*;
import com.jamie.departjson.JsonParseMapper;
import com.jamie.departjson.JsonParseOutPutFormat;
import com.jamie.departjson.JsonParseReduce;
import com.jamie.kv.KVTextMapper;
import com.jamie.kv.KVTextReducer;
import com.jamie.log.LogMapper;
import com.jamie.nline.NLineMapper;
import com.jamie.nline.NLineReducer;
import com.jamie.order.*;
import com.jamie.outputformat.FilterMapper;
import com.jamie.outputformat.FilterOutputFormat;
import com.jamie.outputformat.FilterReducer;
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
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
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


    /*

    {"base":{"code":"xm","name":"project"},"comp":"mt","list":[{"ACode":"aaaa","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]},{"ACode":"bbb","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}]}
    {"base":{"code":"xm2","name":"project2"},"comp":"mt","list":[{"ACode":"ccc","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]},{"ACode":"eee","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}]}
    {"base":{"code":"xm3","name":"project3"},"comp":"mt","list":[{"ACode":"ddd","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]},{"ACode":"fff","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}]}

    预期
    base
{"code":"xm3","tree_id":1357867087288074254,"parent_id":1357867087288074248,"name":"project3"}
{"code":"xm2","tree_id":1357867087288074247,"parent_id":1357867087288074241,"name":"project2"}
{"code":"xm","tree_id":1357867087225159687,"parent_id":1357867087225159681,"name":"project"}


    list
{"AName":"Product1","tree_id":1357867087288074249,"BList":[{"tree_id":1357867087288074250,"parent_id":1357867087288074249,"BName":"Feature1","BCode":"gn1"},{"tree_id":1357867087288074251,"parent_id":1357867087288074249,"BName":"Feature2","BCode":"gn2"}],"parent_id":1357867087288074248,"ACode":"ddd"}
{"AName":"Product2","tree_id":1357867087288074252,"BList":[{"tree_id":1357867087288074253,"parent_id":1357867087288074252,"BName":"Feature1","BCode":"gn1"}],"parent_id":1357867087288074248,"ACode":"fff"}
{"AName":"Product1","tree_id":1357867087288074242,"BList":[{"tree_id":1357867087288074243,"parent_id":1357867087288074242,"BName":"Feature1","BCode":"gn1"},{"tree_id":1357867087288074244,"parent_id":1357867087288074242,"BName":"Feature2","BCode":"gn2"}],"parent_id":1357867087288074241,"ACode":"ccc"}
{"AName":"Product2","tree_id":1357867087288074245,"BList":[{"tree_id":1357867087288074246,"parent_id":1357867087288074245,"BName":"Feature1","BCode":"gn1"}],"parent_id":1357867087288074241,"ACode":"eee"}
{"AName":"Product1","tree_id":1357867087225159682,"BList":[{"tree_id":1357867087225159683,"parent_id":1357867087225159682,"BName":"Feature1","BCode":"gn1"},{"tree_id":1357867087225159684,"parent_id":1357867087225159682,"BName":"Feature2","BCode":"gn2"}],"parent_id":1357867087225159681,"ACode":"aaaa"}
{"AName":"Product2","tree_id":1357867087225159685,"BList":[{"tree_id":1357867087225159686,"parent_id":1357867087225159685,"BName":"Feature1","BCode":"gn1"}],"parent_id":1357867087225159681,"ACode":"bbb"}

    */
    @Test
    public void t13() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, JsonParseMapper.class, JsonParseReduce.class, Text.class, Text.class, NullWritable.class, Text.class);

        LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
        //自定义输出文件名
        job.setOutputFormatClass(JsonParseOutPutFormat.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/json3"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));
        job.waitForCompletion(true);
    }


    /*
过滤输入的log日志，包含atguigu的网站输出到 atguigu.log，不包含atguigu的网站输出到 other.log
http://www.baidu.com
http://www.google.com
http://cn.bing.com
http://www.atguigu.com
http://www.sohu.com
http://www.sohu.com
http://www.sina.com
http://www.sin2a.com
http://www.sin2desa.com
http://www.sindsafa.com

预期
atguigu.log
http://www.atguigu.com

other.log
http://cn.bing.com
http://www.baidu.com
http://www.google.com
http://www.sin2a.com
http://www.sin2desa.com
http://www.sina.com
http://www.sindsafa.com
http://www.sohu.com

     */
    @Test
    public void logFilter() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, FilterMapper.class, FilterReducer.class, Text.class, NullWritable.class, Text.class, NullWritable.class);
        job.setOutputFormatClass(FilterOutputFormat.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/outputformat"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));
        job.waitForCompletion(true);
    }


    /**
     * WritableComparable 对象重写排序方法
     */
    @Test
    public void order0() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, OrderMapper.class, OrderReducer.class, OrderBean.class, NullWritable.class, OrderBean.class, NullWritable.class, "/orderinfo", "/out");
        job.waitForCompletion(true);
    }

    /**
     * 要求出每一个订单中最贵的商品
     * <p>
     * orderId productId price
     * 0000001	Pdt_01	222.8
     * 0000002	Pdt_05	722.4
     * 0000001	Pdt_02	33.8
     * 0000003	Pdt_06	232.8
     * 0000003	Pdt_02	33.8
     * 0000002	Pdt_03	522.8
     * 0000002	Pdt_04	122.4
     * <p>
     * 预期
     * 1	222.8
     * 2	722.4
     * 3	232.8
     */
    @Test
    public void order1() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, OrderMapper.class, OrderReducer.class, OrderBean.class, NullWritable.class, OrderBean.class, NullWritable.class, "/orderinfo", "/out");
        job.setGroupingComparatorClass(OrderGroupingComparator.class);
        job.waitForCompletion(true);
    }

    /**
     * nline
     * number of splits:4
     *
     * 输入文件一共11行
     * 每个切片分3行
     * 需要4个切片
     */
    @Test
    public void nline() throws Exception {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Job job = initJob(Driver.class, NLineMapper.class, NLineReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, "/words", "/out");
        // 设置每个切片InputSplit中划分三条记录
        NLineInputFormat.setNumLinesPerSplit(job, 3);
        // 使用NLineInputFormat处理记录数
        job.setInputFormatClass(NLineInputFormat.class);
        job.waitForCompletion(true);
    }

    /**
     * 去除日志中字段长度小于等于11的日志
     */
    @Test
    public void logEtl() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Driver.class);
        job.setMapperClass(LogMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //不进行reduce
        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/log"));
        FileOutputFormat.setOutputPath(job, SRC_PATH.suffix("/out"));

        job.waitForCompletion(true);
    }

    @Test
    public void kv() throws Exception {
        FileUtils.deleteDirectory(new File(RESOURCE + "/out"));
        Configuration conf = new Configuration();
        //在map 阶段，根据分割符把一行数据分成key 和 value
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, " ");

        Job job = initJob(conf, Driver.class, KVTextMapper.class, KVTextReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        FileInputFormat.setInputPaths(job, SRC_PATH.suffix("/kv"));
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
