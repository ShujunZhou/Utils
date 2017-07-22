package test.java8.lambda;

/**
 * Created by shu on 2017/7/20.
 * 
 */
public class DefaulableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }

    public static void main(String[] args) {
        System.out.println(Defaulable.getThreadName());
    }
}
