package com.wenjun.latch;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁CountDownLatch例子
 * 只能使用一次
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-27 19:40
 */

public class CountDownLatchDemo {

    private static CountDownLatch ready = new CountDownLatch(10);
    private static CountDownLatch start = new CountDownLatch(1);
    private static CountDownLatch end = new CountDownLatch(10);

    /**
     * 模拟10个人同时准备跑步，同时开始，跑步结束
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "----准备好了");
                ready.countDown();
                try {
                    start.await();
                    System.out.println(Thread.currentThread().getName() + "----开始跑步");
                    end.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        ready.await();
        System.out.println("全部准备完毕");
        start.countDown();
        System.out.println("开始跑步");
        end.await();
        System.out.println("全部结束");
    }
}
