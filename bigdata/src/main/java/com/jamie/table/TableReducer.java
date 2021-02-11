package com.jamie.table;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class TableReducer extends Reducer<Text, Table, Table, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Table> values, Context context) throws IOException, InterruptedException {
        ArrayList<Table> orders = new ArrayList<>();
        Table product = new Table();

        for (Table value : values) {
            String flag = value.getFlag();
            String orderId = value.getOrderId();
            String productId = value.getProductId();
            String productName = value.getProductName();

            if ("order".equals(flag)) {
                Table order = new Table();
                order.setOrderId(orderId);
                order.setProductId(productId);

                orders.add(order);

            } else if ("product".equals(flag)) {
                product.setProductId(productId);
                product.setProductName(productName);
            }
        }

        for (Table order : orders) {
            order.setProductName(product.getProductName());
            context.write(order, NullWritable.get());
        }
    }
}
