package com.wenjun.ChapterTwo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 显式锁Lock
 *
 * @Author RUANWENJUN
 * @Creat 2018-06-06 9:53
 */

public class ReentrantLockDemo {
    private Lock lock = new ReentrantLock();

    private int num = 0;

    public void count() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                num++;
                System.out.println(num);
            }
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.count();
                }
            }).start();
        }
    }

}
