package test.java8.lambda;

import java.util.ArrayList;

/**
 * Created by shu on 2017/7/20.
 *
 */
public class Test {
    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaulableImpl :: new);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(28);
        list.add(30);
        long nums = list.stream().filter(num -> num > 10).count();
        list.stream().filter(num -> num > 10).forEach(
                System.out::println
        );
        System.out.println(nums);
    }
}
