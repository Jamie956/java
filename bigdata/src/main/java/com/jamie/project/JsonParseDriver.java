package com.jamie.project;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.jamie.Utils.getJob;

public class JsonParseDriver {

/**

 {"base":{"code":"xm","name":"project"},"comp":"mt","list":[{"ACode":"aaaa","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]},{"ACode":"bbb","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}]}
 {"base":{"code":"xm2","name":"project2"},"comp":"mt","list":[{"ACode":"ccc","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]},{"ACode":"eee","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}]}
 {"base":{"code":"xm3","name":"project3"},"comp":"mt","list":[{"ACode":"ddd","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]},{"ACode":"fff","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}]}

 预期
 base
 {"code":"xm3","name":"project3"}
 {"code":"xm2","name":"project2"}
 {"code":"xm","name":"project"}

 comp
 mt
 mt
 mt

 list
 {"comp":"mt","AName":"Product1","BList":[{"BName":"Feature1","BCode":"gn1"},{"BName":"Feature2","BCode":"gn2"}],"ACode":"ddd"}
 {"comp":"mt","AName":"Product2","BList":[{"BName":"Feature1","BCode":"gn1"}],"ACode":"fff"}
 {"comp":"mt","AName":"Product1","BList":[{"BName":"Feature1","BCode":"gn1"},{"BName":"Feature2","BCode":"gn2"}],"ACode":"ccc"}
 {"comp":"mt","AName":"Product2","BList":[{"BName":"Feature1","BCode":"gn1"}],"ACode":"eee"}
 {"comp":"mt","AName":"Product1","BList":[{"BName":"Feature1","BCode":"gn1"},{"BName":"Feature2","BCode":"gn2"}],"ACode":"aaaa"}
 {"comp":"mt","AName":"Product2","BList":[{"BName":"Feature1","BCode":"gn1"}],"ACode":"bbb"}

*/
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        File srcPath = new File("src/main/resources/out");
        Path inPath = new Path("src/main/resources/json3");
        Path outPath = new Path("src/main/resources/out");
        //删除输出文件夹
        FileUtils.deleteDirectory(srcPath);

        //构建job
        Job job = getJob(JsonParseDriver.class, JsonParseMapper.class, JsonParseReduce.class, Text.class, Text.class, NullWritable.class, Text.class);

		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
		//自定义输出文件名
		job.setOutputFormatClass(JsonParseOutPutFormat.class);

        FileInputFormat.setInputPaths(job, inPath);
        FileOutputFormat.setOutputPath(job, outPath);

        job.waitForCompletion(true);
    }
}
