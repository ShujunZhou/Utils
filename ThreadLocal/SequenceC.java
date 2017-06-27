package cn.smart.test;

/**
 * Created by shu on 2017/6/27.
 *
 */
public class SequenceC implements Sequence {

    private static MyThreadLocal<Integer> myThreadLocal = new MyThreadLocal<Integer>(){
        @Override
        protected Integer initValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        myThreadLocal.set(myThreadLocal.get() + 1);
        return myThreadLocal.get();
    }

    public static void main(String[] args) {
        Sequence sequenceC = new SequenceC();

        new Thread(new ClientThread(sequenceC)).start();
        new Thread(new ClientThread(sequenceC)).start();
        new Thread(new ClientThread(sequenceC)).start();
    }
}
