package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class TestDemo {

    @Test
    public void streamTest() {
        List<Integer> list = new ArrayList();
        list.add(3);
        list.add(2);
        list.add(7);

//        int i = list.stream().max(Comparator.comparing(a -> a)).orElse(null);

        List<Apple> appleList = new ArrayList();
        appleList.add(new Apple("a", "green"));
        appleList.add(new Apple("b", "blue"));

        Map<String, String> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getName, Apple::getColor));

//        int j = list.stream().filter(o -> 0 == 3).findAny().orElse(null);
    }

    @Test
    public void biConsumerTest() {
        BiConsumer<String, String> biConsumer = (a, b) -> {
            System.out.println(a);
            System.out.println(b);
        };
        biConsumer.accept("aaa", "bbb");
    }

    //函数做参数，变量
    @Test
    public void queryTest() {
        Query query = new Query();
        //覆盖默认的处理方法
        query.getMap().put("eq", (queryWrapper) -> {
            queryWrapper.setEq("a new eq");
        });

        QueryWrapper queryWrapper = query.getWrapper();

        System.out.println(queryWrapper);
    }

    @Test
    public void predicateTest() {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple("green"));
        list.add(new Apple("red"));
        list.add(new Apple("yellow"));

        List<Apple> result = PredicateFactory.filterApples(list, Apple::isGreenApple);
    }

}
