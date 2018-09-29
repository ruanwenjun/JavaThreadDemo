package com.wenjun.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 信号量例子
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-29 15:32
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    MyList.add(Thread.currentThread().getName());
                    Thread.sleep(3000);
                    MyList.remove(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("添加失败");
                }
            }).start();
        }
    }

    private static class MyList {
        /**
         * 信号量
         */
        private static Semaphore sem = new Semaphore(10);
        private static List<String> list = new ArrayList<>();

        public static void add(String name) throws InterruptedException {
            sem.acquire();
            list.add(name);
            System.out.println(Thread.currentThread().getName() + "添加了");
        }

        public static void remove(String name) {
            sem.release();
            list.remove(name);
            System.out.println(Thread.currentThread().getName() + "删除了");
        }
    }

}
