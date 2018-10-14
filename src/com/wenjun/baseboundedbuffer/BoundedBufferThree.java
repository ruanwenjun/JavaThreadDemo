package com.wenjun.baseboundedbuffer;

/**
 * 通过等待和唤醒实现队列
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-14 15:50
 */

public class BoundedBufferThree<T> extends AbstractBaseBoundedBuffer<T> {

    public BoundedBufferThree(int capacity) {
        super(capacity);
    }

    /**
     * 如果队列满了则等待，直到被唤醒
     * 这里必须用 synchronized 否则线程不是此对象的监视器的所有者
     *
     * @param t
     * @throws InterruptedException
     */
    public synchronized void put(T t) throws InterruptedException {
        if (isFull()) {
            System.out.println("队列满了，陷入等待");
            wait();
        }
        doPut(t);
        notifyAll();
    }

    /**
     * 如果队列为空则等待
     *
     * @return
     * @throws InterruptedException
     */
    public synchronized T get() throws InterruptedException {
        if (isEmpty()) {
            System.out.println("队列为空，陷入等待");
            wait();
        }
        T t = doTake();
        notifyAll();
        return t;
    }

    public static void main(String[] args) {
        BoundedBufferThree<String> buffer = new BoundedBufferThree(20);

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "尝试从队列中取出");
                String msg = buffer.get();
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "尝试放入队列");
                buffer.put("Hello world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
