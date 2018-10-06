package com.wenjun.cancelthread;

import com.wenjun.executor.MyThreadFactory;

import java.util.concurrent.*;

/**
 * 使用Future停止线程
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-06 15:34
 */

public class FutureCalcelDemo {
    private static ExecutorService pool = new ThreadPoolExecutor(
            3,
            3,
            60,
            TimeUnit.NANOSECONDS,
            new ArrayBlockingQueue<>(10),
            MyThreadFactory.getFactory());

    public static void main(String[] args) {
        Future<?> future = pool.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            future.get(100,TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // 在完成前是否停止
            System.out.println(future.isCancelled());
            future.cancel(true);
            System.out.println(future.isCancelled());
            // 这里其实不应该只是打印异常
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
