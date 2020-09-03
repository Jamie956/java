package java8;

import cglib.App;

import java.util.ArrayList;
import java.util.List;

public class HG {

//    public static boolean isHeavyApple(Apple apple) {
//        return apple.getWeight() > 150;
//    }
    public interface Predicate<T>{
        boolean test(T t);
    }
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    // 使用
//    filterApples(inventory, Apple::isHeavyApple);
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Apple("green"));
        list.add(new Apple("red"));
        list.add(new Apple("yellow"));

        List<Apple> result = filterApples(list, Apple::isGreenApple);

//        test("a", Apple::opps);


    }



    public static void test(String str, Predicate p){
        switch (str) {
            case "a":
                System.out.println("a");
                break;
            case "b":
                System.out.println("b");
                break;
            default:
                break;
        }
    }

}
