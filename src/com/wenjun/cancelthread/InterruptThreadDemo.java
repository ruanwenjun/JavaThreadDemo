package com.wenjun.cancelthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用中断取消线程
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-30 16:32
 */

public class InterruptThreadDemo {
    private final static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(1000);

    public static void main(String[] args) {
        InterruptJob interruptJob = new InterruptJob();
        interruptJob.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我要去中断任务。。。。");
        interruptJob.interruptThread();
        System.out.println("主线程结束。。。。");
    }

    private static class InterruptJob extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    blockingQueue.put(i++);
                    System.out.println(i + "没有中断...");
                } catch (InterruptedException e) {//e.printStackTrace();
                    interrupt();//线程退出
                    System.err.println(Thread.currentThread().isInterrupted() + "InterruptJob任务中断");
                }
            }
            System.out.println("InterruptJob任务结束");
        }

        /**
         * 中断
         */
        public void interruptThread() {
            System.err.println("有人调用了中断");
            interrupt();
        }
    }
}
