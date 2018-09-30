package com.wenjun.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例：线程工厂
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-30 15:13
 */

public class MyThreadFactory implements ThreadFactory {
    /**
     * 工厂名字
     */
    private String name;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static volatile MyThreadFactory factory;

    private MyThreadFactory(String name) {
        this.name = name;
    }

    /**
     *  获得线程工厂
     * @return
     */
    public static MyThreadFactory getFactory() {
        if (factory == null) {
            synchronized (MyThreadFactory.class) {
                if (factory == null) {
                    factory = new MyThreadFactory("wenJunFactory");
                }
            }
        }
        return factory;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-Thread-" + atomicInteger.getAndIncrement());
        return thread;
    }
}
