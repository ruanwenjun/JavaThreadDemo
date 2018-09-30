package com.wenjun.synchronizeddemo;

/**
 *
 * 可重入性测试
 * @Author RUANWENJUN
 * @Creat 2018-09-27 11:50
 */

public class ReentrantFather {
    public synchronized void print(){
        System.out.println("我是父类，这个方法使用synchronized");
    }
}
