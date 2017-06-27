package cn.smart.test;

/**
 * Created by shu on 2017/6/27.
 *
 */
public class SequenceB implements Sequence {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        threadLocal.set(threadLocal.get() + 1);
        return threadLocal.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceB();
        ClientThread clientThread = new ClientThread(sequence);
        ClientThread clientThread1 = new ClientThread(sequence);
        ClientThread clientThread2 = new ClientThread(sequence);

        new Thread(clientThread).start();
        new Thread(clientThread1).start();
        new Thread(clientThread2).start();
    }
}
