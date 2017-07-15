package cn.smart.test.consumerAndProducer.blockingQueue;

import cn.smart.test.consumerAndProducer.Consumer;
import cn.smart.test.consumerAndProducer.Producer;
import cn.smart.test.consumerAndProducer.StorageInter;

/**
 * Created by shu on 2017/7/15.
 * BlockingQueue队列实现消费者生产者
 */
public class Test {
    public static void main(String[] args) {
        StorageInter storage = new Storage();

        //使用线程来进行测试
        Thread producer1 = new Thread(new Producer(storage, 10));
        Thread producer2 = new Thread(new Producer(storage, 10));

        Thread constume1 = new Thread(new Consumer(storage, 8));
        Thread constume2 = new Thread(new Consumer(storage, 6));

        producer1.start();
        producer2.start();
        constume1.start();
        constume2.start();
    }
}
