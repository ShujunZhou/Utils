package cn.smart.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shu on 2017/6/27.
 *
 */
public class MyThreadLocal<T> {
    //封装一个线程安全的Map，以线程对象为key，线程变量T为value
    Map<Thread, T> threadTMap = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T t) {
        threadTMap.put(Thread.currentThread(), t);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = threadTMap.get(thread);
        if (value == null && !threadTMap.containsKey(thread)) {
            value = initValue();
            threadTMap.put(thread, value);
        }

        return value;
    }

    public void remove() {
        threadTMap.remove(Thread.currentThread());
    }


    protected T initValue() {
        return null;
    }
}
