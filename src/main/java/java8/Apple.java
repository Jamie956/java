package java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Apple {
    public static List<Apple> list = new ArrayList<>();
    static {
        list.add(new Apple("apple", "green"));
        list.add(new Apple("the apple", "blue"));
        list.add(new Apple("the the apple", "yellow"));

    }

    private String name;
    private String color;

    public Apple(String color) {
        this.color = color;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }



}
