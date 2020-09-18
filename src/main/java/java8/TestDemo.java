package java8;

import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDemo {

    /**
     * Predicate，返回boolean
     */
    @Test
    public void test1() {
        Predicate<Integer> tester = (Integer i) -> i > 10;
        boolean ret = tester.test(11);
    }

    /**
     * Consumer，存粹调用，无返回
     */
    @Test
    public void test2() {
        Consumer<String> block = System.out::println;
        block.accept("halo");
    }

    /**
     * Function，有返回
     */
    @Test
    public void test3() {
        Function<String, String> mapper = str -> str + "...";
        String data = mapper.apply("arg");
    }


    public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    /**
     * 泛型
     */
    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(1, 2, 5, 6);
        Predicate<Integer> tester = i -> i > 3;
        Function<Integer, Integer> mapper = i -> i + 1;
        Consumer<Integer> block = System.out::println;
        processElements(list, tester, mapper, block);
    }

    /**
     * 与consumer相似，不同的是传2个参数
     */
    @Test
    public void biConsumerTest() {
        BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);
        biConsumer.accept("1", "1");
    }

    @Test
    public void asdasdas() {
        Runnable runnable = () -> System.out.println("hello lambda");
        runnable.run();
    }


    /**
     * stream 元素控制处理
     */
    @Test
    public void test5() {
        List<Integer> a = Stream.of(1, 5, 6, 7).filter(i -> i > 5).collect(Collectors.toList());
        List<Integer> b = Stream.of(1, 1, 6, 7).distinct().collect(Collectors.toList());
        List<Integer> c = Stream.of(1, 1, 6, 7).limit(2).collect(Collectors.toList());
        List<Integer> d = Stream.of(1, 1, 6, 7).map(i -> i + 1).collect(Collectors.toList());
        List<Integer> e = Stream.of(6, 1, 7, 9, 3).sorted().collect(Collectors.toList());
        List<Integer> f = Stream.of(6, 1, 7, 9, 3).skip(2).collect(Collectors.toList());

        List<Integer> g = Stream.iterate(0, n -> n + 2).limit(10).collect(Collectors.toList());
        List<Double> h = Stream.generate(Math::random).limit(10).collect(Collectors.toList());

        Integer i = Stream.of(6, 1, 7, 9, 3).reduce(0, Integer::sum);
    }

    @Test
    public void asd() {
        Predicate<Integer> tester = i -> i > 5;
        List<Integer> list = Arrays.asList(1, 1, 6, 7);
        boolean a = list.stream().allMatch(tester);
        boolean b = list.stream().anyMatch(tester);
        boolean c = list.stream().noneMatch(tester);

        int e = list.stream().findFirst().get();
        int f = list.stream().findAny().get();

        long count = list.stream().count();

        int g = list.stream().max((x, y) -> y - x).get();
        int h = list.stream().min((x, y) -> y - x).get();
    }

    /**
     * 收集器
     */
    @Test
    public void streamTest() {
        //合成map
        Map<Integer, Integer> a = Stream.of(1, 4, 6, 8).collect(Collectors.toMap(i -> i, i -> i + 1));

        //分组
        Function<Integer, String> mapper = i -> i > 5 ? "great then 5" : "less than 5";
        Map<String, List<Integer>> b = Stream.of(1, 4, 7, 9).collect(Collectors.groupingBy(mapper, Collectors.toList()));

        //分组之后通过mapper修改参数，再组成map
        Function<Integer, String> mapper2 = i -> i + "..";
        Map<String, List<String>> c = Stream.of(1, 4, 7, 9).collect(Collectors.groupingBy(mapper, Collectors.mapping(mapper2, Collectors.toList())));
    }

    /**
     * map排序
     */
    @Test
    public void maporder() {
        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("USA", 100);
        wordCounts.put("jobs", 200);
        wordCounts.put("software", 50);
        wordCounts.put("technology", 70);
        wordCounts.put("opportunity", 200);

        //升序
        Map<String, Integer> a = wordCounts.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //降序
        Map<String, Integer> b = wordCounts.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //升序
        Map<String, Integer> c = wordCounts.entrySet().stream().sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //降序
        Map<String, Integer> d = wordCounts.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}