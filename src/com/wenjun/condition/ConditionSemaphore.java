package com.wenjun.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现Semaphore
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-14 21:03
 */

public class ConditionSemaphore {
    private int permits;
    private final int size;
    private final Lock lock = new ReentrantLock();
    private final Condition permitContidion = lock.newCondition();

    public ConditionSemaphore(int permits) {
        // 为什么要加锁？？？保证构造的可见性？？
        lock.lock();
        try {
            this.permits = permits;
            this.size = permits;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @throws InterruptedException
     */
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitContidion.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 释放
     */
    public void release() {
        lock.lock();
        try {
            if (permits < size) {
                permits++;
                permitContidion.signal();
            }
        } finally {
            lock.unlock();
        }

    }
}
