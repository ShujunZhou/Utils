package cn.smart.test.consumerAndProducer.waitAndNofity;

import cn.smart.test.consumerAndProducer.StorageInter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shu on 2017/7/13.
 * 创建仓库
 */
public class Storage implements StorageInter {
    //仓库
    private List<Object> queue;

    public Storage() {
        queue = new LinkedList<>();
    }
    //生产num个物品
    public void product(int num) {
        //如果减小锁的粒度，会发生死锁现象
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
            System.out.println(Thread.currentThread().getName() + "生产者仓库目前的存货量为" + queue.size());
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

            System.out.println(Thread.currentThread().getName() + "消费者仓库目前的存货量为" + queue.size());
            queue.notifyAll();
        }
    }
}
