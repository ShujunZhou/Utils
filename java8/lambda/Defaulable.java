package cn.smart.test.java8.lambda;

import java.util.function.Supplier;

/**
 * Created by shu on 2017/7/20.
 *
 */
public interface Defaulable {
    default String notRequired() {
        return "Default implementation";
    }
    static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
