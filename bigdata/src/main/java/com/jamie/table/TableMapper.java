package com.jamie.table;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable, Text, Text, Table> {
    String fileName;
    Table table = new Table();
    Text k = new Text();

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, Table>.Context context) {
        // 获取文件的名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");

        //用关联的id 作为key，使得相同key 聚合在一起
        if (fileName.startsWith("order")) {
            String orderId = fields[0];
            String productId = fields[1];

            table.setOrderId(orderId);
            table.setProductId(productId);
            table.setProductName("");
            table.setFlag("order");

            //productId 作为key，聚合
            k.set(productId);
        } else if (fileName.startsWith("product")) {
            String productId = fields[0];
            String productName = fields[1];

            table.setOrderId("");
            table.setProductId(productId);
            table.setProductName(productName);
            table.setFlag("product");

            //productId 作为key，聚合
            k.set(productId);
        }

        context.write(k, table);
    }
}
