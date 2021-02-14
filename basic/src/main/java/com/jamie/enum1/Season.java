package com.jamie.enum1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

@Getter
@AllArgsConstructor
public enum Season {
    /**
     * 季节枚举，code
     */
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
    private final int code;

    @Test
    public void enumTest() {
        // values()，获取全部枚举成员数组
        for (Season season : Season.values()) {
            switch (season) {
                case SPRING:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                case AUTUMN:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                case SUMMER:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                case WINTER:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void enumTest2() {
        //根据枚举名字，获取指定的枚举实例
        Season a = Season.valueOf("SPRING");
        Season c = Season.SPRING;
        //获取枚举的索引
        int b = Season.valueOf("SUMMER").ordinal();
    }
}

