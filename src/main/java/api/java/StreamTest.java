package api.java;

import org.junit.Test;
import org.springframework.util.NumberUtils;

import java.util.*;
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

    /**
     * peek 中间操作，不会改变流中元素，对流中的元素进行“查看”操作，一般用于调试
     * 只会在终端操作（如 collect）执行时真正触发，是惰性求值的
     *
     */
    @Test
    public void peekTest(){
        List<String> list = Arrays.asList("1", "2", "3");
        List<String> collect = list.stream()
                .peek(p -> System.out.println("peekTest:" + p))
                .collect(Collectors.toList());
        // 遍历collect：集合元素未改变
        collect.forEach(System.out::println);
    }

    /**
     * 约简操作：从流中获取元素，终止操作
     * 将流约简为可以在程序中使用的非流值
     */

    /**
     * max，min 取最大值和最小值
     *
     */
    @Test
    public void maxAndMinTest(){
        Optional<Integer> max = Stream.of(1, 2, 3).max(Comparator.comparing(Integer::intValue));
        System.out.println("max = " + max.get());
    }

    /**
     * findFirst:返回非空集合中第一个值
     *
     */
    @Test
    public void findFirstTest(){
        Optional<String> first = Stream.of("a", "b", "c").findFirst();
        System.out.println("first = " + first.get());
    }

    /**
     * findAny:不强调第一个，而是任意一个匹配的元素都可以
     * 在并行处理流中很有效
     *
     */
    @Test
    public void findAnyTest(){
        Optional<Integer> any = Stream.of(1, 2, 3, 4, 5).filter(i -> i > 1).findAny();
        System.out.println("any = " + any.get());
    }

    /**
     * anyMatch,allMatch,noneMatch
     *
     */
    @Test
    public void anyMatchTest(){
        // anyMatch:任意一元素匹配返回true
        boolean anyMatch = Stream.of(1, 2, 3, 4, 5).anyMatch(i -> i == 2);
        System.out.println("anyMatch = " + anyMatch);

        // allMatch：所有元素都匹配返回true
        boolean allMatch = Stream.of(1, 2, 3, 4, 5).allMatch(i -> i < 6);
        System.out.println("allMatch = " + allMatch);

        // noneMatch：所有元素都不匹配返回true
        boolean noneMatch = Stream.of(1, 2, 3, 4, 5).noneMatch(i -> i == 0);
        System.out.println("noneMatch = " + noneMatch);
    }
}
