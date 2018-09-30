package com.wenjun.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 1.FixedThreadPool
 * 2.CachedThreadPool
 * 3.SingleThreadPool
 * 4.ScheduledThreadPool
 *
 * 固定大小的线程池
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-30 10:49
 */

public class FixedThreadPoolDemo {
    /**
     * 100个大小的线程池
     * 其本质也是调用new ThreadPoolExecutor
     */
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        for (int i = 0; i < 21; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                try {
                    int compute = compute(finalI);
                    System.out.println(Thread.currentThread().getName() + ":" + compute);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            pool.execute(runnable);
        }
        // 关闭线程池
        pool.shutdown();
        System.out.println("执行");
    }

    public static int compute(int i) throws InterruptedException {
        Thread.sleep(1000);
        return (int) Math.pow(i, 2);
    }

}
