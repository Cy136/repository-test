package api.java;

import org.junit.Test;

import java.util.Arrays;
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
}
