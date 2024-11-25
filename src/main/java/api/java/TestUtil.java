package api.java;

import java.util.stream.Stream;

public class TestUtil {
    public static Stream<String> flatMapBuild(String input) {
        return Stream.of(input).map(String::toUpperCase);
    }
}
