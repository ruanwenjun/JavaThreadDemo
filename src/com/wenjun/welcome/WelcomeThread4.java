package com.wenjun.welcome;

/**
 * 模拟多线程下载多个文件
 *
 * @Author RUANWENJUN
 * @Creat 2018-06-03 10:36
 */

public class WelcomeThread4 {
    public static void main(String[] args) {
        Thread thread = null;
        for (String url : args){
            thread = new Thread(new FileDownloader(url));
            thread.start();
        }
    }

    static class FileDownloader implements Runnable{
        private String url;
        public FileDownloader(String url){
            this.url = url;
        }

        @Override
        public void run() {
            System.out.println("开始下载！！！" + url);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("下载完毕！！！" + url);
        }
    }

}
