package com.wenjun.latch;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 并发缓存例子
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-29 17:09
 */

public class HighConcurrentCache {
    /**
     * 模拟缓存
     */
    private static ConcurrentHashMap<Integer, Future<String>> concurrentHashMap = new ConcurrentHashMap<>();
    private static Random random = new Random(47);

    /**
     * 一个很耗时的计算过程
     *
     * @return
     * @throws InterruptedException
     */
    private static String longCompute() throws InterruptedException {
        Thread.sleep(1000);
        return "计算很耗时：" + Math.random() * 100;
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 100个线程的并发
         */
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                int num = random.nextInt(10);
                Future<String> future = concurrentHashMap.get(num);
                if (future == null) {
                    System.err.println(num + "缓存没有命中:");
                    FutureTask<String> ft = new FutureTask<>(() -> longCompute());
                    // 如果返回null 代表之前没有计算过，那么就run进行计算
                    future = concurrentHashMap.putIfAbsent(num, ft);
                    if (future == null) {
                        future = ft;
                        ((FutureTask<String>) future).run();
                    }
                } else {
                    System.out.println(num + "缓存命中：");
                }
                try {
                    String result = future.get();
                    //System.out.println(num + "------" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
