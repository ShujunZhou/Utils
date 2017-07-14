package cn.smart.test.consumerAndProducer;

/**
 * Created by shu on 2017/7/14.
 * 消费者线程
 */
public class Consumer implements Runnable {
    //设置仓库
    private MyQueue myQueue;

    //设置每次消费的个数
    private int nums;

    public Consumer(MyQueue myQueue, int nums) {
        this.myQueue = myQueue;
        this.nums = nums;
    }

    @Override
    public void run() {
        while (true) {
            myQueue.consume(nums);
        }
    }
}
