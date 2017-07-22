package test.java8.lambda;

import java.util.function.Supplier;

/**
 * Created by shu on 2017/7/20.
 *
 */
public interface DefaulableFactory {
    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }
}
