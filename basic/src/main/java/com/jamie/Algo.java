package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author: Zjm
 * @Date: 2021/2/5 16:00
 */
public class Algo {

    /**
     * 递归遍历json 全部节点，并在每个节点新增一个节点
     */
    public static void jsonLoop(Object object) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            //每个 json 节点增加新节点
            json.put("a", "a");
            for (String key : json.keySet()) {
                Object value = json.get(key);
                jsonLoop(value);
            }
        } else if (object instanceof JSONArray) {
            JSONArray array = (JSONArray) object;
            for (Object e : array) {
                jsonLoop(e);
            }
        }
    }

    @Test
    public void jsonRecursion() {
        String jsonString = "{\"TITLE\":\"Json Title\",\"FORM\":{\"USERNAME\":\"Rick and Morty\"},\"ARRAY\":[{\"FIRST\":\"Rick\"},{\"LAST\":\"Morty\"}]}";
        JSONObject json = JSON.parseObject(jsonString);
        jsonLoop(json);
    }
}
