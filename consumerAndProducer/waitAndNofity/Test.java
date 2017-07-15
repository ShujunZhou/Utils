package cn.smart.test.consumerAndProducer.waitAndNofity;

import cn.smart.test.consumerAndProducer.Consumer;
import cn.smart.test.consumerAndProducer.Producer;

import java.util.concurrent.*;

/**
 * Created by shu on 2017/7/14.
 * 测试线程
 */
public class Test {

    public static void main(String[] args) {
        //构造资源仓库
        Storage storage = new Storage();
        //参数含义：使用线程池,基本线程数4，最大线程数4，
        //当线程池内线程数量超过基本线程数量时，多余线程的存活时间，
        //任务队列选择有界的ArrayBlockingQueue(一共有四种队列，
        // 分别为直接提交的队列(synchronousQueue),有界任务队列，无界任务队列，优先任务队列))
        //拒绝策略，当任务量超过系统的负荷时，如何处理
        //jdk内置了四种方式，AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作
        //CallerRunsPolicy策略：在线程池未关闭的情况下，该策略直接在调用者线程中，运行当前被丢弃的任务
        //DiscardOledestPolicy策略：该策略丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务
        //DiscardPolicy策略：该策略丢弃无法处理的任务，不予任何处理
        //生产者线程池
        ExecutorService producerPool = new ThreadPoolExecutor(4, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardPolicy());

        //消费者线程池
        ExecutorService consumerPool = new ThreadPoolExecutor(8, 10, 2, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardPolicy());
        //生产者线程提交4个任务
        for (int i = 0; i < 4; ++i) {
            producerPool.submit(new Producer(storage, 10));
        }

        //消费者线程提交
        for (int i = 0; i < 3; ++i) {
            consumerPool.submit(new Consumer(storage, 5));
        }

    }
}
