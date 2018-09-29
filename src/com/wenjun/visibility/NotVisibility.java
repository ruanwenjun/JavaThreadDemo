package com.wenjun.visibility;



/**
 * 可见性的例子
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-27 14:16
 */

public class NotVisibility {
    private static int number;
    private static boolean read;

    /**
     * 监控变量的值是否改变
     */
    private static class ReaderThread extends Thread{
        @Override
        public void run(){
            while (!read){
                System.out.println(Thread.currentThread().getName()+"----"+number);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i< 10; i ++){
            new ReaderThread().start();
        }
        number++;
        read = true;
    }

}
