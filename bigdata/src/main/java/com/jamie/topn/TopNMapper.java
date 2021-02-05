package com.jamie.topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

public class TopNMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    // 排序容器
    private TreeMap<FlowBean, Text> flowMap = new TreeMap<>();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        FlowBean kBean = new FlowBean(Long.parseLong(fields[1]), Long.parseLong(fields[2]), Long.parseLong(fields[3]));
        flowMap.put(kBean, new Text(fields[0]));

        //限制TreeMap的数据量，超过10条就删除掉流量最小的一条数据
        if (flowMap.size() > 10) {
            flowMap.remove(flowMap.lastKey());
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (FlowBean flowBean : flowMap.keySet()) {
            context.write(flowBean, flowMap.get(flowBean));
        }
    }
}
