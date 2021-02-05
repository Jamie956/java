package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: Zjm
 * @Date: 2021/2/5 16:00
 */
/**
递归遍历json，并在每个节点新增一个子节点
 */
public class JsonLoopLoop {
    /**
    {
        "TITLE": "Json Title",
        "FORM": {
            "USERNAME": "Rick and Morty"
        },
        "MAIN": {
            "USERNAME": {"BIT":"99"}
        },
        "ARRAY": [
            {
                "FIRST": "Rick"
            },
            {
                "LAST": "Morty"
            }
        ]
    }
     */
    public static String json = "{\"TITLE\":\"Json Title\",\"FORM\":{\"USERNAME\":\"Rick and Morty\"},\"ARRAY\":[{\"FIRST\":\"Rick\"},{\"LAST\":\"Morty\"}]}";

    public static void jsonLoop(Object object) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            for (String key : json.keySet()) {
                Object o = json.get(key);
                if (o instanceof JSONObject) {
                    ((JSONObject) o).put("a", "a");
                    jsonLoop(o);
                } else if (o instanceof JSONArray) {
                    for (Object o1 : ((JSONArray) o)) {
                        ((JSONObject) o1).put("a", "a");
                        jsonLoop(o);
                    }
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray arr = (JSONArray) object;
            for (Object e : arr) {
                jsonLoop(e);
            }
        }
    }

    public static void main(String[] args) {

        JSONObject jsonObject = JSON.parseObject(json);
        jsonLoop(jsonObject);
    }
}
