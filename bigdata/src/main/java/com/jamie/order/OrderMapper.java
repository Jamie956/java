package com.jamie.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN 读取输入行首字符的位置
 * VALUEIN 读取输入的一行的数据
 * KEYOUT 输出的key
 * VALUEOUT 输出的value
 */
public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {
    OrderBean k = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, OrderBean, NullWritable>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(" ");

        int orderId = Integer.parseInt(fields[0]);
        double price = Double.parseDouble(fields[1]);
        k.setOrderId(orderId);
        k.setPrice(price);
        context.write(k, NullWritable.get());
    }
}
