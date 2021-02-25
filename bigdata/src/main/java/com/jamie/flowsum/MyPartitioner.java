package com.jamie.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<Text, Counter> {

    @Override
    public int getPartition(Text key, Counter value, int numPartitions) {
        String phone = key.toString();

        if ("136".equals(phone)) {
            return 0;
        } else if ("137".equals(phone)) {
            return 1;
        } else if ("138".equals(phone)) {
            return 2;
        } else if ("139".equals(phone)) {
            return 3;
        } else {
            return 4;
        }
    }

}
