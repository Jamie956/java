package com.jamie.flowsum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counter implements WritableComparable<Counter> {
    private int count;

    // 序列化方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(count);
    }

    // 反序列化方法
    @Override
    public void readFields(DataInput in) throws IOException {
        // 必须要求和序列化方法顺序一致
        count = in.readInt();
    }

    @Override
    public int compareTo(Counter bean) {
        if (this.count > bean.getCount()) {
            return -1;
        } else if (this.count < bean.getCount()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return count + "...";
    }
}
