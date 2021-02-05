package com.jamie.topn;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

public class TopNReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
    // 排序容器
    TreeMap<FlowBean, Text> flowMap = new TreeMap<>();

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) {
        for (Text value : values) {
            FlowBean bean = new FlowBean();
            bean.setDownFlow(key.getDownFlow());
            bean.setUpFlow(key.getUpFlow());
            bean.setSumFlow(key.getDownFlow() + key.getUpFlow());

            flowMap.put(bean, new Text(value));

            if (flowMap.size() > 10) {
                flowMap.remove(flowMap.lastKey());
            }
        }
    }

    @Override
    protected void cleanup(Reducer<FlowBean, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        for (FlowBean flowBean : flowMap.keySet()) {
            context.write(new Text(flowMap.get(flowBean)), flowBean);
        }
    }
}
