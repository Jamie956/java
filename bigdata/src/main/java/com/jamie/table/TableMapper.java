package com.jamie.table;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {
    String fileName;
    TableBean tableBean = new TableBean();
    Text k = new Text();

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, TableBean>.Context context) {
        // 获取文件的名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");

        //用关联的id 作为key，使得相同key 的两条记录聚合在一起
        if (fileName.startsWith("order")) {
            tableBean.setId(fields[0]);
            tableBean.setPid(fields[1]);
            tableBean.setAmount(Integer.parseInt(fields[2]));
            tableBean.setPname("");
            tableBean.setFlag("order");

            //pid 作为key，用来关联2个表
            k.set(fields[1]);
        } else if (fileName.startsWith("pd")) {
            // 封装key和value
            tableBean.setId("");
            tableBean.setPid(fields[0]);
            tableBean.setAmount(0);
            tableBean.setPname(fields[1]);
            tableBean.setFlag("pd");

            //pid 作为key，用来关联2个表
            k.set(fields[0]);
        }

        // 写出
        context.write(k, tableBean);
    }
}
