package com.jamie.parsejson;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class Pojo2 implements WritableComparable {
    private String base;
    private String list;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.base);
        out.writeUTF(this.list);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        base = in.readUTF();
        list = in.readUTF();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
