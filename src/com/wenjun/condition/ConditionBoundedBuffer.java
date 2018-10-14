package com.wenjun.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现阻塞队列
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-14 20:12
 */

public class ConditionBoundedBuffer<T> {

    private final Lock lock = new ReentrantLock();
    private final Condition nonEmpty = lock.newCondition();
    private final Condition nonFull = lock.newCondition();
    private final Object[] buffer;
    private int head;
    private int tail;
    private int count;

    public ConditionBoundedBuffer(int capacity) {
        this.buffer = new Object[capacity];
    }

    /**
     * put
     *
     * @param t
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                // 这里await会释放当前拥有的锁，所以不用担心死锁的问题，当await被唤醒时，会尝试获取锁
                nonFull.await();
            }
            buffer[tail] = t;
            if (++tail == buffer.length) {
                tail = 0;
            }
            count++;
            nonEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * get
     *
     * @return
     * @throws InterruptedException
     */
    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                nonEmpty.await();
            }
            T t = (T) buffer[head];
            if (++head == buffer.length) {
                tail = 0;
            }
            count--;
            nonFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionBoundedBuffer<String> buffer = new ConditionBoundedBuffer<>(2);
        // 取出
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "尝试取出");
                String s = buffer.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //写入
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "尝试放入");
                buffer.put("Hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
