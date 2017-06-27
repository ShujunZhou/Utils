package cn.smart.test;

/**
 * Created by shu on 2017/6/27.
 *
 */
public class SequenceA implements Sequence {
    private static int number = 0;

    @Override
    public int getNumber() {
        number++;
        return number;
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceA();
        ClientThread clientThread = new ClientThread(sequence);
        ClientThread clientThread1 = new ClientThread(sequence);
        ClientThread clientThread2 = new ClientThread(sequence);

        new Thread(clientThread).start();
        new Thread(clientThread1).start();
        new Thread(clientThread2).start();
    }
}
