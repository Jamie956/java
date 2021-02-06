package com.jamie.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBean implements WritableComparable<OrderBean> {
    private int order_id;
    private double price;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(order_id);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        order_id = in.readInt();
        price = in.readDouble();
    }

    @Override
    public int compareTo(OrderBean bean) {
        // 先按照定id升序排序，如果相同 按照价格降序排序
        int result;

        if (order_id > bean.getOrder_id()) {
            result = 1;
        } else if (order_id < bean.getOrder_id()) {
            result = -1;
        } else {

            if (price > bean.getPrice()) {
                result = -1;
            } else if (price < bean.getPrice()) {
                result = 1;
            } else {
                result = 0;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return order_id + "\t" + price;
    }
}
