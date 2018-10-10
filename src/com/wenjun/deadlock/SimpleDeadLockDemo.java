package com.wenjun.deadlock;

/**
 * 简单的锁顺序死锁
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-08 12:23
 */

public class SimpleDeadLockDemo {
    private static final Object left = new Object();
    private static final Object right = new Object();

    private static class ThreadLeft extends Thread {
        @Override
        public void run() {
            synchronized (left){
                System.out.println("线程ThreadLeft获得left锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right){
                    System.out.println("线程ThreadLeft获得right锁");
                }
            }
        }
    }

    private static class ThreadRight extends Thread{
        @Override
        public void run(){
            synchronized (right){
                System.out.println("线程ThreadRight获得right锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (left){
                    System.out.println("线程ThreadRight获得left锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadLeft threadLeft = new ThreadLeft();
        ThreadRight threadRight = new ThreadRight();
        threadLeft.start();
        threadRight.start();
    }

}
