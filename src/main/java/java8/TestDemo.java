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
        System.out.println(ret);
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
        System.out.println(data);
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
        processElements(list, (Integer i) -> i > 3, (Integer i) -> i + 1, System.out::println);
    }

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

    @Test
    public void queryTest() {
        Query query = new Query();
        //函数做参数，变量
        //覆盖默认的处理方法
        query.setMap(1, x -> System.out.println("1覆盖: " + x));
        query.getWrapper();
    }


    /**
     * stream
     */
    @Test
    public void test5() {
        Stream.of(1, 5, 6, 7).filter(i -> i > 5).forEach(System.out::println);

        Stream.of(1, 5, 6, 7).filter(i -> i > 5).map(i -> i + 1).forEach(System.out::println);

        Stream.of(1, 1, 6, 7).distinct().forEach(System.out::println);

        Stream.of(1, 1, 6, 7).limit(2).forEach(System.out::println);

        Stream.of(1, 1, 6, 7).map(i -> i + 1).forEach(System.out::println);

        Stream.of(6, 1, 7, 9, 3).sorted().forEach(System.out::println);

    }

    @Test
    public void asd(){
        Predicate<Integer> tester = (Integer i) -> i > 5;
        List<Integer> list = Arrays.asList(1,1,6,7);
        boolean a = list.stream().allMatch(tester);
        boolean b = list.stream().anyMatch(tester);
        boolean c = list.stream().noneMatch(tester);


//        List<Integer> list = Arrays.asList(1,1,6,7);
//        Optional<Integer> op1 = list.stream().findFirst();
//        Optional<Integer> op2 = list.stream().findAny();
//
//
//        System.out.println(op1.get());
//        System.out.println(op2.get());
//
//
//        long count = list.stream().count();
//        System.out.println(count);
//
//
//        Optional<Integer> op3 = list.stream().max((a, b) -> b-a);
//        Optional<Integer> op4 = list.stream().min((a, b) -> b-a);
//
//        System.out.println(op3.get());
//        System.out.println(op4.get());

    }

    /**
     * 表达式指定kv构建map
     */
    @Test
    public void streamTest() {
        List<Integer> list = Arrays.asList(1, 4, 6, 8);
        Map<Integer, Integer> ret = list.stream().collect(Collectors.toMap(i -> i, i -> i + 1));
        System.out.println(ret);
    }

    /**
     * 分组
     */
    @Test
    public void asdasd() {
        Function<Integer, String> mapper = i -> i > 5 ? "great then 5" : "less than 5";
        List<Integer> list = Arrays.asList(1, 4, 7, 9);
        Map<String, List<Integer>> ret = list.stream().collect(Collectors.groupingBy(mapper, Collectors.toList()));
        System.out.println(ret);
    }

    @Test
    public void asdfrg() {
//        Map<String, List<String>> groupRegion = regions.stream().collect(Collectors.groupingBy(region -> regionTypes.get(region.getType() - 1), Collectors.mapping(SysRegionInfoResponse::getCode, Collectors.toList())));

        List<Integer> ret = Stream.of(1, 4, 6, 8).collect(Collectors.mapping(i -> i+1, Collectors.toList()));
        System.out.println(ret);

    }


}
