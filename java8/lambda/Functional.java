package cn.smart.test.java8.lambda;

/**
 * Created by shu on 2017/7/20.
 *
 */
@FunctionalInterface
public interface Functional {
    void method();
    default void defaultMethod(){
        System.out.println("默认方法可在接口内被实现");
    }
    static void getSome() {
        System.out.println("static 可以用来在接口内声明方法并实现");
    }
}
