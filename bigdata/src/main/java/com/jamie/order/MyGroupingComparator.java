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
        //第一次返回0 执行reduce
        return 0;
    }
}
