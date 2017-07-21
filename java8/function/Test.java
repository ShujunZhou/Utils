package cn.smart.test.java8.function;

import sun.plugin2.util.ParameterNames;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shu on 2017/7/21.
 *
 */

/*
java8的新特性
1、Lambda表达式与Functional接口{
    Lambda表达式即函数式编程，即把函数作为参数传进去
    可以使用@FunctionalInterface注解声明函数式接口，
    函数式接口内只允许有一个普通方法，但可包含默认方法和静态方法
}
2、接口的默认方法和静态方法 {
    接口内允许使用default关键字声明默认方法并实现，实现该接口的类中既可以不实现默认方法，也可以重写默认方法。
    接口内可以使用static定义静态方法，并实现。此类方法默认使用接口.方法名的形式进行调用。默认不被子类实现。
}

3、方法引用 {
    方法引用，可以直接引用java类或对象的方法或构造器。一共有四种方法引用
    1、构造方法引用 (Class::new)
    2、静态方法引用(Class:static_method)
    3、一般方法引用(Class:method)
    4、特定对象的方法引用(instance:method)
}
4、重复注解 {
        重复注解机制必须用@Repeatable注解}，
        引入重复注解机制，这样一个注解可以在同一地方声明多次
 5、更好的类型推测机制
 6、扩展注解的支持（局部变量、泛型类、父类与接口的实现，方法的异常都可以添加注解）
 7、Java编译器的新特性,使得方法参数的名字能保留在Java字节码中，并且能够在运行时获取它们,可
 以使用反射API和Parameter.getName()获取
 8、引入Stream API(java.util.stream)极大简化了集合框架的处理
 9、Date/Time API进一步加强了对日期与时间的处理
 */
        public class Test {
            public static void main(String[] args) throws NoSuchMethodException {
                Car car = Car.create(Car::new);
                final List<Car> carList = Arrays.asList(car);
                carList.forEach(Car::collide);
//        carList.forEach(Car::repair);

                Method method = ParameterNames.class.getMethod("main", String[].class);
                for (final Parameter param : method.getParameters()) {
                    System.out.println(param.getName());
        }


    }
}
