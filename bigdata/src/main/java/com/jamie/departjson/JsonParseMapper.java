package com.jamie.departjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.utils.IDGenerator;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;

public class JsonParseMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        JSONObject lineJson = JSONObject.parseObject(value.toString());
        //获取当前行的公司名
        String compName = lineJson.getString("company_name");

        long id = IDGenerator.nextId();
        lineJson.put("tree_id", id);
        jsonLoop(lineJson, id, compName);

        //按json的外层key 遍历，相同的json key 作为mapper key 写出
        for (String outerKey : lineJson.keySet()) {
            Object outerValue = lineJson.get(outerKey);
            //只取文档和数组 类型
            if (outerValue instanceof JSONArray || outerValue instanceof JSONObject) {
                k.set(outerKey);
                v.set(outerValue.toString());
                context.write(k, v);
            }
        }
    }

    /**
     * json 递归生成树结构
     */
    public static void jsonLoop(Object object, long parentId, String compName) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            //遍历json 多个子节点
            for (String key : json.keySet()) {
                Object o = json.get(key);
                //当前子节点为 json 对象
                if (o instanceof JSONObject) {
                    //子节点的下级节点增加节点
                    long id = IDGenerator.nextId();
                    ((JSONObject) o).put("tree_id", id);
                    ((JSONObject) o).put("parent_id", parentId);
                    ((JSONObject) o).put("comp_name", compName);
                    //递归寻找下级节点
                    jsonLoop(o, id, compName);
                } else if (o instanceof JSONArray) {
                    //当前子节点为 数组 对象，遍历数组
                    for (Object ele : ((JSONArray) o)) {
                        if (ele instanceof JSONObject) {
                            //子节点的下级节点增加节点
                            long id = IDGenerator.nextId();
                            ((JSONObject) ele).put("tree_id", id);
                            ((JSONObject) ele).put("parent_id", parentId);
                            ((JSONObject) ele).put("comp_name", compName);
                            jsonLoop(ele, id, compName);
                        } else {
                            jsonLoop(ele, parentId, compName);
                        }
                    }
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray arr = (JSONArray) object;
            for (Object e : arr) {
                jsonLoop(e, parentId, compName);
            }
        }
    }
}
