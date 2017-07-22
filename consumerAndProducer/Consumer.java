package cn.smart.test.consumerAndProducer;

import cn.smart.test.consumerAndProducer.awaitAndsignal.Storage;

/**
 * Created by shu on 2017/7/15.
 * 消费者
 */
public class Consumer implements Runnable{
    //仓库
    private StorageInter storage;
    //每次消费个数
    private int num;

    public Consumer(StorageInter storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            storage.consume(num);
        }
    }
}
