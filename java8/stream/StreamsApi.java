package test.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shu on 2017/7/22.
 * StreamsAPI
 */
public class StreamsApi {
    public static void main(String[] args) {
        Stream.iterate(1, item -> item + 1).limit(10).forEach(
                System.out::println
        );

        List<Integer> integers = Arrays.asList(1, 2, 3, 3, null, 4, 5, 5, null, null, 6);
        ArrayList<Integer> notEmptyList = integers
                .stream()
                .filter(num -> num != null)
                .collect(ArrayList::new,
                ArrayList::add,
                ArrayList::addAll);

        List<Integer> notDistinct = integers
                .stream()
                .distinct()
                .collect(Collectors.toList());

        notEmptyList.stream().forEach(
                System.out::print
        );

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        inputStream.flatMap(List::stream).forEach(
                System.out::println
        );
    }
}
