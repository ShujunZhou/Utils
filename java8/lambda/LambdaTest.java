package test.java8.lambda;

import java.util.Arrays;

/**
 * Created by shu on 2017/7/20.
 *
 */
public class LambdaTest {
    public static void main(String[] args) {

        Arrays.asList("a", "b", "c").forEach(e-> {
            System.out.println(e);
        });

        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        Thread t1 = new Thread(runnable);

        Thread t2 = new Thread(() -> {
            int i = 0;

            System.out.println(Thread.currentThread().getName());
            Thread t3 = new Thread(() -> {
                System.out.println(Thread.currentThread().getContextClassLoader());
            });
            t3.start();
        });

        t1.start();
        t2.start();
    }
}
