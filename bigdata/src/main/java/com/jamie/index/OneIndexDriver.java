package com.jamie.index;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class OneIndexDriver {
	/**
	 * 建立搜索索引
	 */
	@Test
	public void t0() throws IOException, ClassNotFoundException, InterruptedException {
		Job job = getJob(OneIndexDriver.class, OneIndexMapper.class, OneIndexReducer.class, Text.class, IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path("src/main/resources/index"));
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

		job.waitForCompletion(true);
	}
}
