package com.wenjun.latch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FeatureTask例子
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-29 14:20
 */

public class FutureTaskDemo {

    private static FutureTask<Integer> featureTask = new FutureTask<>(() -> {
        Integer i = 2;
        for (int j = 1; j < 10; j++) {
            i = i * j;
        }
        return i;
    });

    public static void main(String[] args) {
        Thread thread = new Thread(featureTask);
        thread.start();
        try {
            // get方法会阻塞当前线程
            Integer i = featureTask.get();
            System.out.println(i);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
