package com.jamie.topn;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class TopNDriver {
	/**
	 * 输出流量使用量在前10的用户信息
	 */
	@Test
	public void t0() throws IOException, ClassNotFoundException, InterruptedException {
		Job job = getJob(TopNDriver.class, TopNMapper.class, TopNReducer.class, FlowBean.class, Text.class, Text.class, FlowBean.class);

		FileInputFormat.setInputPaths(job, new Path("src/main/resources/top10"));
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

		job.waitForCompletion(true);
	}
}
