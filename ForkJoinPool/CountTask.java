package cn.smart.test.ForkJoinPool;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * Created by shu on 2017/7/13.
 * 多线程数列求和
 */
public class CountTask extends RecursiveTask<Long> {
    //任务量限定
    private static final int LIMIT = 1000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0L;
        //任务量小于阈值时，不需要分割任务。
        boolean flag = (end - start) < LIMIT;
        if (flag) {
            for (long i = start; i <= end; ++i) {
                sum += i;
            }
        } else {
            //分成100个小任务
            long step = (start + end) / 100;
            long pos = start;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            //将100个任务进行
            for (int i = 0; i < 100; ++i) {
                //任务量
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                //构造任务
                CountTask countTask = new CountTask(pos, lastOne);
                pos += step + 1;
                //提交任务到队列
                subTasks.add(countTask);
                //任务分割
                countTask.fork();
            }

            for (CountTask countTask : subTasks) {
                sum += countTask.join();
            }
        }

        return sum;
    }
}
