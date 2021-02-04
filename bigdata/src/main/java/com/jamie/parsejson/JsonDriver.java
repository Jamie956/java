package com.jamie.parsejson;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.FileUtil;
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

public class JsonDriver {

    /**
     * {"id":11,"name":"pk","address":"beijing"}
     * {"id":11,"name":"pxj","address":"gz"}
     * {"id":12,"name":"wfy","address":"gx"}
     * {"id":13,"name":"zcl","address":"st"}
     *
     * 预期
     * 13	zcl	st
     * 12	wfy	gx
     * 11	pxj	gz
     * 11	pk	beijing
     */
    @Test
    public void t0() throws IOException, ClassNotFoundException, InterruptedException {
        File srcPath = new File("src/main/resources/out");

        FileUtils.deleteDirectory(srcPath);

        Job job = getJob(JsonDriver.class, JsonMapper.class, JsonReduce.class, Pojo.class, NullWritable.class, Pojo.class, NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/publicly"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

/**
{
	"base": {
		"code": "xm",
		"name": "project"
	},
	"list": [{
		"ACode": "cp1",
		"AName": "Product1",
		"BList": [{
			"BCode": "gn1",
			"BName": "Feature1"
		}, {
			"BCode": "gn2",
			"BName": "Feature2"
		}]
	}, {
		"ACode": "cp2",
		"AName": "Product2",
		"BList": [{
			"BCode": "gn1",
			"BName": "Feature1"
		}]
	}]
}

 预期
 base
 {"code":"xm","name":"project"}
 list
 {"ACode":"cp1","AName":"Product1","BList":[{"BCode":"gn1","BName":"Feature1"},{"BCode":"gn2","BName":"Feature2"}]}
 {"ACode":"cp2","AName":"Product2","BList":[{"BCode":"gn1","BName":"Feature1"}]}
*/
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        File srcPath = new File("src/main/resources/out");

        FileUtils.deleteDirectory(srcPath);

        Job job = getJob(JsonDriver.class, JsonMapper2.class, JsonReduce2.class, Text.class, Text.class, NullWritable.class, Text.class);

		LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/json2"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
