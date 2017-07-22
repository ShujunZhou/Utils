package test.java8.function;

import sun.plugin2.util.ParameterNames;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shu on 2017/7/21.
 *
 */
        public class Test {
            public static void main(String[] args) throws NoSuchMethodException {
                Car car = Car.create(Car::new);
                final List<Car> carList = Arrays.asList(car);
                carList.forEach(Car::collide);
//                carList.forEach(Car::repair);

                Method method = ParameterNames.class.getMethod("main", String[].class);
                for (final Parameter param : method.getParameters()) {
                    System.out.println(param.getName());
        }
    }
}
