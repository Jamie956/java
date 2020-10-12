package pattern.strategy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * java8 consumer lambda 实现策略模式
 */
public class Java8StrategyPattern {
    private Map<Integer, Consumer<Integer>> map = new HashMap<>();

    public void setMap(int type, Consumer<Integer> consumer) {
        map.put(type, consumer);
    }

    {
        System.out.println("初始map");
        map.put(1, x -> System.out.println("1执行默认方法: " + x));
        map.put(2, x -> System.out.println("2执行默认方法: " + x));
    }

    public void getWrapper() {
        for (int type : Arrays.asList(1, 2)) {
            map.get(type).accept(type);
        }
    }

    @Test
    public void queryTest() {
        Java8StrategyPattern query = new Java8StrategyPattern();
        //函数做参数，变量
        //覆盖默认的处理方法
        query.setMap(1, x -> System.out.println("1覆盖: " + x));
        query.getWrapper();
    }

}
