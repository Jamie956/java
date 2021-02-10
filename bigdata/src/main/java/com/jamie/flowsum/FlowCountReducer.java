package com.jamie.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text, FlowBean1, Text, FlowBean1> {
    FlowBean1 v = new FlowBean1();

    @Override
    protected void reduce(Text key, Iterable<FlowBean1> values, Context context) throws IOException, InterruptedException {
        long sumUp = 0;
        long sumDown = 0;

        for (FlowBean1 value : values) {
            sumUp += value.getUpFlow();
            sumDown += value.getDownFlow();
        }
        v.setUpFlow(sumUp);
        v.setDownFlow(sumDown);
        v.setSumFlow(sumUp + sumDown);
        context.write(key, v);
    }
}
