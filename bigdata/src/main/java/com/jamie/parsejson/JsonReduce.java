package com.jamie.parsejson;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class JsonReduce extends Reducer<Pojo, NullWritable, Pojo, NullWritable> {
    @Override
    protected void reduce(Pojo key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable value : values) {
            context.write(key,NullWritable.get());
        }
    }
}

