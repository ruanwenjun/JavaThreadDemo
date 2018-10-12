package com.wenjun.welcome;

/**
 * 简易倒计时
 *
 * @Author RUANWENJUN
 * @Creat 2018-06-03 10:31
 */

public class WelcomeThread3 {
    private static int count = 60;

    public static void main(String[] args) {
        int remaining = 0;
        while (true){
            remaining = countDown();
            if(remaining == 0){
                break;
            }else {
                System.out.println("Remaining " + count + "seconds(s)");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finish");
    }

    private static int countDown() {
        return count--;
    }
}
