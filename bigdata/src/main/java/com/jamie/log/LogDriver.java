package com.jamie.log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

public class LogDriver {

	/**
	 * 去除日志中字段长度小于等于11的日志
	 */
	@Test
	public void t0() throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		job.setJarByClass(LogDriver.class);
		job.setMapperClass(LogMapper.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		job.setNumReduceTasks(0);

		FileInputFormat.setInputPaths(job, new Path("src/main/resources/log"));
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

		job.waitForCompletion(true);
	}
}
