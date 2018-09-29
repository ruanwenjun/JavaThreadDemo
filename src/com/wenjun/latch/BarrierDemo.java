package com.wenjun.latch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏例子
 * 能使用多次
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-29 16:33
 */

public class BarrierDemo {

    private static CyclicBarrier barrier = new CyclicBarrier(10, () -> System.out.println("这一批10个开始执行"));

    /**
     * 模拟每次执行10个任务
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i ++){
            new Thread(()->{
                try {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()+"开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
