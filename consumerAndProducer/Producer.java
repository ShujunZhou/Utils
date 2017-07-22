package cn.smart.test.consumerAndProducer;

import cn.smart.test.consumerAndProducer.awaitAndsignal.Storage;

/**
 * Created by shu on 2017/7/15.
 * 生产者
 */
public class Producer implements Runnable{
    //仓库
    private StorageInter storage;
    //消费数量
    private int num;

    public Producer(StorageInter storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            storage.product(num);
        }
    }
}
