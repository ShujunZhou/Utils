package cn.smart.test.java8.lambda;

import java.util.ArrayList;

/**
 * Created by shu on 2017/7/20.
 *
 */
public class Test {
    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaulableImpl :: new);

        ArrayList<Integer> list = new ArrayList<>();
    }
}
