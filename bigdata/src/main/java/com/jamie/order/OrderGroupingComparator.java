package com.jamie.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupingComparator extends WritableComparator {
    protected OrderGroupingComparator() {
        super(OrderBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        // 要求只要id相同，就认为是相同的key
        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        if (aBean.getOrderId() > bBean.getOrderId()) {
            return 1;
        } else if (aBean.getOrderId() < bBean.getOrderId()) {
            return -1;
        }
        //返回0就会执行reduce
        return 0;
    }
}
