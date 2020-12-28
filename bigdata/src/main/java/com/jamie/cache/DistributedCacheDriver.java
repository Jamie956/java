package com.jamie.cache;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class DistributedCacheDriver {

	public static void main(String[] args) throws Exception {

		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		job.setJarByClass(DistributedCacheDriver.class);

		job.setMapperClass(DistributedCacheMapper.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path("src/main/resources/table/order"));
		FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

		// 6 加载缓存数据
		job.addCacheFile(new URI("file:///Volumes/WD_BLACK/学习资料/hadoop/3.代码/mr-0529/src/main/resources/cache/pd"));

		// 7 Map端Join的逻辑不需要Reduce阶段，设置reduceTask数量为0
		job.setNumReduceTasks(0);

		job.waitForCompletion(true);
	}
}
