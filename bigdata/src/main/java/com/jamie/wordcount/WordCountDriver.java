package com.jamie.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class WordCountDriver {
    private static final String HDFS_URL = "hdfs://hadoop102:9000";
    private static final String HADOOP_USER_NAME = "root";

    public static void main(String[] args) throws Exception {
        args = new String[]{"/wordcount/hello.txt", "/wordcount/out"};

        if (args.length < 2) {
            System.out.println("Input and output paths are necessary!");
            return;
        }

        System.setProperty("HADOOP_USER_NAME", HADOOP_USER_NAME);
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", HDFS_URL);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileSystem fileSystem = FileSystem.get(new URI(HDFS_URL), configuration, HADOOP_USER_NAME);
        Path outputPath = new Path(args[1]);
        if (fileSystem.exists(outputPath)) {
            fileSystem.delete(outputPath, true);
        }

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, outputPath);

        boolean result = job.waitForCompletion(true);

        fileSystem.close();

        System.exit(result ? 0 : -1);
    }
}