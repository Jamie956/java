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
        for (Text num : values) {
            FlowBean bean = new FlowBean(key.getCount());

            flowMap.put(bean, new Text(num));

            if (flowMap.size() > 10) {
                flowMap.remove(flowMap.lastKey());
            }
        }
    }

    //最后 写出排序后的map
    @Override
    protected void cleanup(Reducer<FlowBean, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        for (FlowBean bean : flowMap.keySet()) {
            context.write(new Text(flowMap.get(bean)), bean);
        }
    }
}
