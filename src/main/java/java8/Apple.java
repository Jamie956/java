package java8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Apple {
    private String color;
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static void opps () {
        System.out.println("test1");
    }

//    public static boolean opps(Object o) {
//        System.out.println();
//    }
}
