package com.jamie.inputformat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class SequenceFileDriver {
	/**
	 * 将多个小文件合并成一个SequenceFile文件
	 */
	@Test
	public void t0() throws IOException, ClassNotFoundException, InterruptedException {
		Job job = getJob(SequenceFileDriver.class, SequenceFileMapper.class, SequenceFileReducer.class, Text.class, BytesWritable.class);

		// 7设置输入的inputFormat
		job.setInputFormatClass(WholeFileInputformat.class);
		// 8设置输出的outputFormat
		job.setOutputFormatClass(SequenceFileOutputFormat.class);

		FileInputFormat.setInputPaths(job, new Path("src/main/resources/format"));
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

		job.waitForCompletion(true);
	}
}
