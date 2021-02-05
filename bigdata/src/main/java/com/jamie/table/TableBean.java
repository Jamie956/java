package com.jamie.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableBean implements Writable {
    private String id;
    private String pid;
    private int amount;
    private String pname;
    private String flag;

    /**
     * map 写出时调
     */
    @Override
    public void write(DataOutput out) throws IOException {
        // 序列化方法
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);
    }

    /**
     * reduce 读取时调
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        // 反序列化方法
        id = in.readUTF();
        pid = in.readUTF();
        amount = in.readInt();
        pname = in.readUTF();
        flag = in.readUTF();
    }

    @Override
    public String toString() {
        return id + "\t" + amount + "\t" + pname;
    }
}
