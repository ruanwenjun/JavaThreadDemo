package com.wenjun.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-12 11:05
 */

public class ReadWriteLockDemo {

    private static List<String> nameList = new ArrayList<>();
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock read = readWriteLock.readLock();
    private static Lock write = readWriteLock.writeLock();
    private static char[] name = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};


    public static void main(String[] args) {
        // 写线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int first = (int) (Math.random() * name.length);
                int second = (int) (Math.random() * name.length);
                int third = (int) (Math.random() * name.length);
                StringBuilder sb = new StringBuilder();
                sb.append(name[first]);
                sb.append(name[second]);
                sb.append(name[third]);
                addName(sb.toString());
            }).start();
        }

        // 读线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                readName();
            }).start();
        }

    }

    /**
     * 往nameList写入名字
     *
     * @param name
     */
    public static void addName(String name) {
        write.lock();
        try {
            nameList.add(name);
            System.out.println(Thread.currentThread().getName() + "获取写锁添加" + name);
        } finally {
            try {
                // 在释放写锁之前，其他线程不能获取写锁
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            write.unlock();
        }
    }

    /**
     * 读取nameList中的名字，并且打印到控制台
     */
    public static void readName() {
        read.lock();
        try {
            int size = nameList.size();
            if (size == 0) {
                System.out.println(Thread.currentThread().getName() + "获取读锁没有名字 ");
                return;
            }
            int v = (int) (Math.random() * size);
            String name = nameList.get(v);
            System.out.println(Thread.currentThread().getName() + "获取读锁" + name);
        } finally {
            try {
                // 发现在释放读锁之前，其他线程也可以获取读锁
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            read.unlock();
        }
    }


}
