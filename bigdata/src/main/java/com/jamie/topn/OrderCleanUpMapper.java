package com.jamie.topn;

import com.jamie.flowsum.Counter;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

public class OrderCleanUpMapper extends Mapper<LongWritable, Text, Counter, Text> {
    // 排序容器
    TreeMap<Counter, String> flowMap = new TreeMap<>();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) {
        String[] fields = value.toString().split(" ");

        String name = fields[0];
        int count = Integer.parseInt(fields[1]);
        Counter bean = new Counter(count);

        //由对象 compareTo 实现排序
        flowMap.put(bean, name);

        //控制 TreeMap 的大小，超过10条就删除掉最小的一条数据
        if (flowMap.size() > 3) {
            flowMap.remove(flowMap.lastKey());
        }
    }

    //cleanup 最后执行写出
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Counter bean : flowMap.keySet()) {
            String name = flowMap.get(bean);
            v.set(name);
            context.write(bean, v);
        }
    }
}
