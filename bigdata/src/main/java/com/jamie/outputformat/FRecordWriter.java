package com.jamie.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class FRecordWriter extends RecordWriter<Text, NullWritable> {
    public static final String RESOURCE = "src/main/resources/";
    public static final Path SRC_PATH = new Path(RESOURCE);

    FSDataOutputStream atOut;
    FSDataOutputStream otOut;

    public FRecordWriter(TaskAttemptContext job) {
        try {
            FileSystem fs = FileSystem.get(job.getConfiguration());
            //创建文件输出流
            atOut = fs.create(SRC_PATH.suffix("/out/atguigu.log"));
            otOut = fs.create(SRC_PATH.suffix("/out/other.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException {
        //根据条件，写出到不同的流
        if (key.toString().contains("atguigu")) {
            atOut.write(key.toString().getBytes());
        } else {
            otOut.write(key.toString().getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext context) {
        IOUtils.closeStream(atOut);
        IOUtils.closeStream(otOut);
    }
}
