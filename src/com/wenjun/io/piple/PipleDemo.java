package com.wenjun.io.piple;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用管道让线程通信
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-18 20:27
 */

public class PipleDemo {
    public static void main(String[] args) throws InterruptedException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();
        try {
            out.connect(in);
            // 写到管道
            new Thread(() -> {
                try {
                    out.write("piple Demo".getBytes());
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            // 从管道中读
            new Thread(() -> {
                try {
                    int read = in.read();
                    while (read != -1) {
                        System.out.println(Thread.currentThread().getName() + "从管道中读" + (char) read);
                        read = in.read();
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            // 防止管道提前关闭
            Thread.sleep(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
