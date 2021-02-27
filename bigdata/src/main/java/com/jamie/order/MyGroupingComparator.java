package com.jamie.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupingComparator extends WritableComparator {
    protected MyGroupingComparator() {
        super(Order.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        if (((Order) a).getOrderId() != ((Order) b).getOrderId()) {
            return -1;
        }
        //返回0 分到 reduce 一组
        return 0;
    }
}
