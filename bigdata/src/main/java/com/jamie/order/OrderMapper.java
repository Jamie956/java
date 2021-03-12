package com.jamie.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, Order, NullWritable> {
    Order k = new Order();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Order, NullWritable>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(" ");
        int orderId = Integer.parseInt(fields[0]);
        int price = Integer.parseInt(fields[1]);
        k.setOrderId(orderId);
        k.setPrice(price);
        context.write(k, NullWritable.get());
    }
}
