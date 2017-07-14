package cn.smart.test.consumerAndProducer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shu on 2017/7/13.
 * 创建仓库
 */
public class MyQueue {
    //仓库容量
    private final static int MAXSIZE = 200;
    //仓库
    private List<Object> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }
    //生产num个物品
    public void produce(int num) {
        synchronized (queue) {
            while (queue.size() + num > MAXSIZE) {
                System.out.println("仓库库存已达存储限制：" + (queue.size() + num));
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < num; ++i) {
                queue.add(new Object());
            }
            System.out.println("生产者仓库目前的存货量为" + queue.size());
            queue.notifyAll();
        }
    }

    //消费num个物品
    public void consume(int num) {
        synchronized (queue) {
            while (queue.size() < num) {
                System.out.println("仓库库存不足" + queue.size());

                try {
                    //wait方法会释放目标对象的锁（属于Object类中的方法）
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; ++i) {
                queue.remove(0);
            }

            System.out.println("消费者仓库目前的存货量为" + queue.size());
            queue.notifyAll();
        }
    }
}
