package cn.smart.test.CAS;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shu on 2017/7/17.
 *
 */
public class AtomicIntegerDemo implements Runnable{
    //CAS:包含三个参数：V(要更新的变量)E(表示预期值，预期值即为旧值)N(表示新值)
    //策略：如果V==E为真，更新变量V的值为N。否则的话，不进行更新，即更新失败（说明已有线程修改过变量的值）。
    //线程的主要行为：线程的创建，线程的阻塞，线程的销毁，线程的运行，线程的切换

    private static AtomicInteger i = new AtomicInteger();
    private static CountDownLatch lock = new CountDownLatch(4);

    @Override
    public void run() {
        for (int j = 0; j < 10000; ++j) {
            i.incrementAndGet();
        }
        lock.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        //线程池的好处：1、可以达到线程的复用，从而减少因为线程频繁的创建以及销毁带来的开销
        ExecutorService service = new ThreadPoolExecutor(4, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        AtomicIntegerDemo task = new AtomicIntegerDemo();
        for (int i = 0; i < 4; ++i) {
            service.submit(task);
        }

        //等待各线程任务执行完后，主线程继续执行
        lock.await();
        System.out.println(i);
        service.shutdown();
    }
}
