package test.java8.parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;

/**
 * Created by shu on 2017/7/22.
 * 并行数组
 */
public class ParalleArrays {
    public static void main(String[] args) {
        long[] arrayOfLong = new long[2000];
        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));

        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " ")
        );

        System.out.println();
        Arrays.parallelSort(arrayOfLong);

        Arrays.stream(arrayOfLong).limit(10).forEach(
                System.out::println
        );

        System.out.println();

        IntConsumer outPrintln = System.out::println;
        IntConsumer errPrintln = System.err::println;
    }
}
