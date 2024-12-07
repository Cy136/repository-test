package api.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    /**
     * count 计数，是一个终止操作
     *
     */
    @Test
    public void countTest() {
        List<Integer> list = Arrays.asList(100, 200, 300, 400, 500);
        long count = list.stream().filter(l -> l > 300).count();
        System.out.println("count = " + count);
    }

    /**
     * map 映射操作、转换元素
     *
     */
    @Test
    public void mapTest(){
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * flatMap 可以将多个流摊平为一个流
     *
     */
    @Test
    public void flatMapTest(){
        // 多个流
        Stream<Stream<String>> map = Arrays.asList("a", "b", "c")
                .stream()
                .map(s -> TestUtil.flatMapBuild(s));

        // 多个流摊平为一个流
        Stream<String> flatMap = Arrays.asList("a", "b", "c")
                .stream()
                .flatMap(s -> TestUtil.flatMapBuild(s));
    }

    /**
     * limit 产生一个流，截取前 n 个元素
     * skip 产生一个流，剔除前 n 个元素
     */
    @Test
    public void limitTest(){
        Stream<String> limit = Stream.of("a", "b", "c", "d", "e").limit(3);
        limit.forEach(System.out::println);

        Stream<String> skip = Stream.of("a", "b", "c", "d", "e").skip(2);
        skip.forEach(System.out::println);
    }

    /**
     * distinct 去除重复元素并产生一个新的流
     *
     */
    @Test
    public void distinctTest(){
        Stream<String> distinct = Stream.of("a", "b", "c", "d", "e", "e").distinct();
    }

    /**
     * sorted 排序并产生一个新的流
     * sorted(Comparator<? super T> comparator)
     *
     */
    @Test
    public void sortedTest(){
        Stream<String> sorted = Stream.of("1", "2", "4", "3").sorted();
        sorted.forEach(System.out::println);

        Stream<Integer> sorted1 = Stream.of("1", "3", "4", "2").map(s -> Integer.valueOf(s))
                .sorted(Comparator.comparing(Integer::intValue).reversed());
        sorted1.forEach(System.out::println);
    }
}
