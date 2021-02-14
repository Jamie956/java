package com.jamie.enum1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Color {
    /**
     *
     */
    RED("red", "红色"), GREEN("green", "绿色"), BLANK("blank", "白色"), YELLO("yello", "黄色");

    // 成员变量
    private final String name;
    private final String value;

    // 将数据缓存到map中
    private static final Map<String, String> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.getName(), color.getValue());
        }
    }

    // 根据name查询value值
    public static String getValueByName(String name) {
        return CACHE.get(name);
    }

    @Test
    public void enumtest3() {
        String value = Color.getValueByName("red");
    }
}
