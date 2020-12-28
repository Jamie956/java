package com.jamie.index;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class TwoIndexDriver {
	@Test
	public void t0() throws IOException, ClassNotFoundException, InterruptedException {
		Job job = getJob(TwoIndexDriver.class, TwoIndexMapper.class, TwoIndexReducer.class, Text.class, Text.class);

		FileInputFormat.setInputPaths(job, new Path("src/main/resources/index2"));
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

		job.waitForCompletion(true);
	}
}
