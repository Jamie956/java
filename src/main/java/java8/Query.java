package java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Query {
    private static Map<Integer, Consumer<Integer>> map = new HashMap<>();

    public void setMap(int type, Consumer<Integer> consumer) {
        map.put(type, consumer);
    }

    static {
        System.out.println("初始map");
        map.put(1, x -> System.out.println("1执行默认方法: " + x));
        map.put(2, x -> System.out.println("2执行默认方法: " + x));

    }

    public void getWrapper() {
        for (int type : Arrays.asList(1, 2)) {
            map.get(type).accept(type);
        }
    }
}
