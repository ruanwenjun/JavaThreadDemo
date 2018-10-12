package com.wenjun.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author RUANWENJUN
 * @Creat 2018-10-11 20:43
 */

public class RryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 在制定时间内如果能获得锁就范围true
                    if (reentrantLock.tryLock(100, TimeUnit.NANOSECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "加锁成功...");
                        } finally {
                            reentrantLock.unlock();
                            System.out.println(Thread.currentThread().getName() + "释放锁...");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
