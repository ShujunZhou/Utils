package cn.smart.test.ForkJoinPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;


/**
 * Created by shu on 2017/7/12.
 *多线程加法
 */
public class TestClass {
    public static void main(String[] args) {

        ForkJoinPool joinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0L, 20000L);

        Long startTime = System.currentTimeMillis();
        ForkJoinTask<Long> result = joinPool.submit(countTask);

        long sum = 0;
        try {
            sum = result.get();
            Long middleTime = System.currentTimeMillis();
            System.out.println("多线程花费时间==>" +  (middleTime - startTime) +"    sum= " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        sum = 0;
        startTime = System.currentTimeMillis();
        for (int  i = 0; i <= 200000; ++ i) {
            sum += i;
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("一般方法花费时间==>" + (endTime - startTime) + "    sum= " + sum);
    }
}
