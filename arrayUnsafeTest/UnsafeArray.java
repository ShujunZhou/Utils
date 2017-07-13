package cn.smart.test.arrayUnsafeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shu on 2017/7/13.
 *多个线程操作同一个对象案例
 */
public class UnsafeArray implements Runnable {
    private List<Integer> arrayList = new ArrayList<>();
    private static CountDownLatch lock = new CountDownLatch(4);
    private static Lock lockA = new ReentrantLock();
    private int nums = 0;

    @Override
    public void run() {
        while (nums < 1000) {
            lockA.lock();
            if (nums < 1000) {
                System.out.println(Thread.currentThread().getName() + "======>正在执行  " + nums);
                arrayList.add(nums++);
            }
            lockA.unlock();
        }

        lock.countDown();
    }

    public List<Integer> getArrayList() {
        return arrayList;
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeArray runnable = new UnsafeArray();
        ExecutorService service = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),5, 10,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        //操作同一对象
        for (int i = 0; i < 4; ++i) {
            service.submit(runnable);
        }

        lock.await();
        System.out.println(runnable.getArrayList().size());
        service.shutdown();
    }
}
