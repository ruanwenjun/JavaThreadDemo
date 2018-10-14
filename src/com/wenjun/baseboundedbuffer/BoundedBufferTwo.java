package com.wenjun.baseboundedbuffer;

/**
 * two:通过轮询，能够处理则立即处理，否则休眠一段时间再尝试
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-14 15:20
 */

public class BoundedBufferTwo<T> extends AbstractBaseBoundedBuffer<T> {
    protected BoundedBufferTwo(int capacity) {
        super(capacity);
    }

    /**
     * 添加
     *
     * @param t
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException {
        while (true) {
            // 缓存锁
            synchronized (this) {
                if (!isFull()) {
                    doPut(t);
                    return;
                }
            }
            Thread.sleep(1000);
        }
    }

    /**
     * 取出
     *
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        while (true) {
            // 缓存锁  在这里体现出加缓存锁的作用
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(1000);
        }
    }


    public static void main(String[] args) {
        BoundedBufferTwo<String> buffer = new BoundedBufferTwo<>(20);
        // 加入队列
        new Thread(() -> {
            // 这里不需要自己手动实现重试，方法里面实现了重试
            while (true) {
                try {
                    buffer.put(Thread.currentThread().getName() + System.currentTimeMillis());
                    System.out.println(Thread.currentThread().getName() + "插入" + buffer.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //退出
                    break;
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 从队列中取出
        new Thread(() -> {
            while (true) {
                try {
                    String take = buffer.take();
                    System.out.println(Thread.currentThread().getName() + "取出" + buffer.size() + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //退出
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
