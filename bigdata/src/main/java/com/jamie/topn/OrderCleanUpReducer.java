package com.jamie.topn;

import com.jamie.flowsum.Counter;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

public class OrderCleanUpReducer extends Reducer<Counter, Text, Text, Counter> {
    // 排序容器
    TreeMap<Counter, Text> flowMap = new TreeMap<>();
    Text k = new Text();

    @Override
    protected void reduce(Counter key, Iterable<Text> values, Context context) {
        Text name = values.iterator().next();
        Counter bean = new Counter(key.getCount());
        flowMap.put(bean, new Text(name));

        //对象排序，取前3名
        if (flowMap.size() > 3) {
            flowMap.remove(flowMap.lastKey());
        }
    }

    //最后 写出排序后的map
    @Override
    protected void cleanup(Reducer<Counter, Text, Text, Counter>.Context context) throws IOException, InterruptedException {
        for (Counter bean : flowMap.keySet()) {
            k.set(flowMap.get(bean));
            context.write(k, bean);
        }
    }
}
