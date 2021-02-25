package com.jamie;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: Zjm
 * @Date: 2021/2/24 14:18
 */
public class GsonGson {

    public static void main(String[] args) {
        String userJson = "{'isDeveloper':false,'name':'xiaoqiang','age':26,'email':'578570174@qq.com'}";
        Gson gson = new Gson();
//        String a = gson.toJson("{name:1}");

        JsonObject returnData = new JsonParser().parse(userJson).getAsJsonObject();
//        User user = gson.fromJson(userJson, User.class);
        JsonElement a = returnData.get("isDeveloper");

//        returnData.
        for (Map.Entry<String, JsonElement> entry : returnData.entrySet()) {
//            JsonElement
            entry.setValue(a);
//            entry.
        }
    }


    @Test
    public void asa(){
        JSONObject j1 = new JSONObject();
        j1.put("k1", "v1");
        j1.put("k2", "v2");

        JSONObject j2 = new JSONObject();
        j2.put("k1", "v11");
        j2.put("k2", "v22");
        j2.put("k3", "v33");

        j1.putAll(j2);

    }
}
