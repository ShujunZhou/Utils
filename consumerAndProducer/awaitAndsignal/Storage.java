package cn.smart.test.consumerAndProducer.awaitAndsignal;

import cn.smart.test.consumerAndProducer.StorageInter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shu on 2017/7/15.
 * 仓库
 */
public class Storage implements StorageInter {
    //仓库
    private List<Object> storage;
    //锁
    private Lock lock = new ReentrantLock();
    //生产者线程condition条件
    private Condition notFull = lock.newCondition();
    //消费者线程condition条件
    private Condition notEmpty =lock.newCondition();


    public Storage() {
        storage = new ArrayList<>();
    }

    //生产
    public void product(int num) {
        try {
            lock.lock();
            //如果仓库容量不足，阻塞写线程
            while (storage.size() + num > MAXSIZE) {
                System.out.println("仓库容量不足: " + (storage.size() + num));
                notFull.await();
            }

            for (int i = 0; i < num; ++i) {
                storage.add(new Object());
            }
            System.out.println("生产者-当前仓库容量为: " + storage.size());
            //情况一：某个线程在执行完写入操作之前，其它的写线程出于阻塞状态，同样读线程出于阻塞状态
            //其实只要某个线程执行一次写入操作，便可以唤醒其它的读写线程进行操作
            notEmpty.signalAll();
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    //消费
    public void consume(int num) {
        try {
            lock.lock();
            while (storage.size() < num) {
                System.out.println("仓库资源不足：" + storage.size());
                notEmpty.await();
            }

            for (int i = 0; i < num; ++i) {
                storage.remove(0);
            }

            System.out.println("消费者-仓库容量为：" + storage.size());
            notFull.signalAll();
            notEmpty.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
