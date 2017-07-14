package cn.smart.test.consumerAndProducer;

/**
 * Created by shu on 2017/7/14.
 * 生产者线程
 */
public class Producer implements Runnable {
    //设置仓库
    private MyQueue myQueue;

    //每次消费的个数
    private int nums;

    public Producer(MyQueue myQueue, int nums) {
        this.myQueue = myQueue;
        this.nums = nums;
    }

    @Override
    public void run() {
        while (true) {
            myQueue.produce(nums);
        }

    }
}
