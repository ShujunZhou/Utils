package test.java8.function;

import java.util.function.Supplier;

/**
 * Created by shu on 2017/7/21.
 *
 */
public class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("collide  " + car.toString());
    }
    public static void follow(Car car) {
        System.out.println("follow  " + car.toString());
    }

    public void repair(Car car) {
        System.out.println("repair  " + this.toString());
    }
}
