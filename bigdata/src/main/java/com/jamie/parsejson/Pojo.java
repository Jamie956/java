package com.jamie.parsejson;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class Pojo implements WritableComparable<Pojo> {
    private Integer id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return id + "\t" + name + '\t' + address + '\t';
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.id);
        out.writeUTF(this.name);
        out.writeUTF(this.address);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readInt();
        name = in.readUTF();
        address = in.readUTF();
    }

    @Override
    public int compareTo(Pojo o) {
        return 0;
    }
}
