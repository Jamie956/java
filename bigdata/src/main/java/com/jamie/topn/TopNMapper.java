package com.jamie.topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

public class TopNMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    // 排序容器
    TreeMap<FlowBean, Text> flowMap = new TreeMap<>();

    @Override
    protected void map(LongWritable key, Text value, Context context) {
        String[] fields = value.toString().split(" ");

        String num = fields[0];
        int count = Integer.parseInt(fields[1]);

        FlowBean bean = new FlowBean();
        bean.setCount(count);

        flowMap.put(bean, new Text(num));

        //限制TreeMap的数据量，超过10条就删除掉流量最小的一条数据
        if (flowMap.size() > 10) {
            flowMap.remove(flowMap.lastKey());
        }
    }

    //完成排序后，cleanup 写出
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (FlowBean bean : flowMap.keySet()) {
            Text num = flowMap.get(bean);
            context.write(bean, num);
        }
    }
}
