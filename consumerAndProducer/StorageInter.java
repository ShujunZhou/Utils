package cn.smart.test.consumerAndProducer;

/**
 * Created by shu on 2017/7/15.
 * 仓库接口，不同的仓库可以用不同的实现
 */
public interface StorageInter {
    public final static int MAXSIZE = 200;

    void product(int num);
    void consume(int num);
}
