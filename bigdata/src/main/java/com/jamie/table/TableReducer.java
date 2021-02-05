package com.jamie.table;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
        ArrayList<TableBean> orders = new ArrayList<>();
        TableBean product = new TableBean();

        for (TableBean bean : values) {
            if ("order".equals(bean.getFlag())) {
                TableBean tmpBean = new TableBean();
                tmpBean.setId(bean.getId());
                tmpBean.setPid(bean.getPid());
                tmpBean.setAmount(bean.getAmount());

                orders.add(tmpBean);

            } else if ("pd".equals(bean.getFlag())) {
                product.setPid(bean.getPid());
                product.setId(bean.getId());
                product.setPname(bean.getPname());
            }
        }

        for (TableBean order : orders) {
            //合并pd表
            order.setPname(product.getPname());
            context.write(order, NullWritable.get());
        }
    }
}
