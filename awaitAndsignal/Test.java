package cn.smart.test.consumerAndProducer.awaitAndsignal;

import java.util.concurrent.*;

/**
 * Created by shu on 2017/7/15.
 * 使用数据库连接池进行测试
 */
public class Test {
    public static void main(String[] args) {
        Storage storage = new Storage();

        //生产者线程池
        ExecutorService producerPool = new ThreadPoolExecutor(4, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        //消费者线程池
        ExecutorService consumerPool = new ThreadPoolExecutor(4, 5, 2, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 4; ++i) {
            producerPool.submit(new Producer(storage, 10));
        }
        for (int i = 0; i < 5; ++i) {
            consumerPool.submit(new Consumer(storage, 8));
        }
    }
}
