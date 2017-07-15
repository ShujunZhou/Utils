package cn.smart.test.consumerAndProducer.blockingQueue;

import cn.smart.test.consumerAndProducer.StorageInter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by shu on 2017/7/15.
 * 使用阻塞队列实现生产者和消费者
 */
public class Storage implements StorageInter {
    private static final int MAXSIZE = 200;

    private BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(200);

    public void product(int num) {
        if (queue.size() == MAXSIZE) {
            System.out.println("队列已满");
        }
        try {
            for (int i = 0; i < num; ++i) {
                queue.put(new Object());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "===生产者-目前的库存量为：" + queue.size());
    }

    public void consume(int num) {
        if (queue.size() == 0) {
            System.out.println("队列资源为0");
        }
        try {
            for (int i = 0; i < num; ++i) {
                queue.take();
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "===消费者-目前的库存容量为：" + queue.size());
    }
}
