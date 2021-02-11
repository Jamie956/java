package com.jamie.topn;

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
public class FlowBean implements WritableComparable<FlowBean> {
    private int count;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        count = in.readInt();
    }

    @Override
    public int compareTo(FlowBean bean) {
        if (this.count > bean.getCount()) {
            return -1;
        } else if (this.count < bean.getCount()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
