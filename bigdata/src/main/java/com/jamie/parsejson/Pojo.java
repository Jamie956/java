package com.jamie.parsejson;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
public class Pojo implements WritableComparable {

    private Integer id;
    private String name;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Pojo() {
    }

    public Pojo(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return  id +
                "\t" + name + '\t'
                + address + '\t'
                ;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.id);
        out.writeUTF(this.name);
        out.writeUTF(this.address);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id=in.readInt();
        name=in.readUTF();
        address=in.readUTF();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }


}
