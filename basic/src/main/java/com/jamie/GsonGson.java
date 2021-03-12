package com.jamie;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jamie.entity.User;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: Zjm
 * @Date: 2021/2/24 14:18
 */
public class GsonGson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        User user = gson.fromJson("{'id':1,'name':'tim'}", User.class);

        JsonObject json = new JsonParser().parse("{'name':'tim','age':26}").getAsJsonObject();
    }
}
