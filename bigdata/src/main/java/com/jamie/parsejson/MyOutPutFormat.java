package com.jamie.parsejson;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class MyOutPutFormat extends TextOutputFormat{
    @Override
    public Path getDefaultWorkFile(TaskAttemptContext context, String extension) throws IOException{
        FileOutputCommitter committer = (FileOutputCommitter) getOutputCommitter(context);
        return new Path(committer.getWorkPath(), getOutputName(context));
    }

}