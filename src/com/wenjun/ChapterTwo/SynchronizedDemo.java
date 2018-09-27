package com.wenjun.ChapterTwo;

/**
 * 内部锁Synchronized
 * @Author RUANWENJUN
 * @Creat 2018-06-06 9:47
 */

public class SynchronizedDemo {
    private static int num = 0;

    public static synchronized void count() {
        for (int i = 0; i < 100; i++) {
            num++;
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count();
                }
            }).start();
        }
    }

}
