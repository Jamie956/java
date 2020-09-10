package java8;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class TestDemo {

    @Test
    public void streamTest() {
        //从数组对象，指定kv构建map
        Map<String, String> appleMap = Apple.list.stream().collect(Collectors.toMap(Apple::getName, Apple::getColor));
    }

    @Test
    public void biConsumerTest() {
        BiConsumer<String, String> biConsumer = (a, b)-> System.out.println(a + b);
        biConsumer.accept("aaa", "bbb");
    }

    @Test
    public void queryTest() {
        Query query = new Query();
        //函数做参数，变量
        //覆盖默认的处理方法
        query.setMap(1, x -> System.out.println("1覆盖: " + x));
        query.getWrapper();
    }

    private static String getType(String code) {
        if ("440000".equals(code)) {
            return "1";
        }
        if ("320000".equals(code)) {
            return "2";
        }
        return "0";
    }
    @Test
    public void asdasd() {
        //按类型分组，相同类型元素放在一组
        String[] permitRegionArray = StringUtils.split("440000|320000", "|");
        Map<String, List<String>> ret = Arrays.stream(permitRegionArray).collect(Collectors.groupingBy(TestDemo::getType, Collectors.toList()));
    }

    @Test
    public void asdasdas() {
        Runnable runnable = () -> System.out.println("hello lambda");
        runnable.run();
    }

}
