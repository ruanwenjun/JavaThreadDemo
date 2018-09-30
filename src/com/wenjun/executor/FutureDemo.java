package com.wenjun.executor;

import java.util.concurrent.*;

/**
 * Future例子
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-30 15:01
 */

public class FutureDemo {
    private final static int CORE_POLL_SIZE = 3;
    private final static int MAX_POOL_SIZE = 10;
    private final static int KEEP_TIME = 0;
    /**
     * 线程池
     */
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(CORE_POLL_SIZE,
            MAX_POOL_SIZE,
            KEEP_TIME,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10),
            MyThreadFactory.getFactory(),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Future<String> future = pool.submit(() -> Thread.currentThread().getName() + " number:" + finalI);
            try {
                System.out.println(future.get(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("执行中断......");
            } catch (ExecutionException e) {
                e.printStackTrace();
                System.out.println("执行失败.....");
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println("执行超时.....");
            }
        }
        pool.shutdown();
    }

}
