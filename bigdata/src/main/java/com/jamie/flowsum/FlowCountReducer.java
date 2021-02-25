package com.jamie.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text, Counter, Text, Counter> {
    Counter v = new Counter();

    @Override
    protected void reduce(Text key, Iterable<Counter> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (Counter value : values) {
            sum += value.getCount();
        }
        v.setCount(sum);

        //写出 key 电话号码，value 对象
        context.write(key, v);
    }
}
