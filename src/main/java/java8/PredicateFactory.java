package java8;

import java.util.ArrayList;
import java.util.List;

public class PredicateFactory {

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
