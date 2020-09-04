package java8;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestDemo {
    @Test
    public void biConsumerTest() {
        BiConsumer<String, String> biConsumer = (a, b) -> {
            System.out.println(a);
            System.out.println(b);
        };
        biConsumer.accept("aaa", "bbb");
    }

    @Test
    public void queryTest() {
        Query query = new Query();
        Map<String, Consumer> map = new HashMap<>();

        Consumer<QueryWrapper> eqConsumer = (queryWrapper) -> {
            queryWrapper.setEq("myeq");
        };
        map.put("eq", eqConsumer);
        QueryWrapper queryWrapper = query.getWrapper(map);

    }
}
