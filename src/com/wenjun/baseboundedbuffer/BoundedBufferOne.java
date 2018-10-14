package com.wenjun.baseboundedbuffer;


/**
 * one：通过抛异常让上层去处理
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-14 11:49
 */

public class BoundedBufferOne<T> extends AbstractBaseBoundedBuffer<T> {
    protected BoundedBufferOne(int capacity) {
        super(capacity);
    }

    /**
     * put
     *
     * @param t
     * @throws BufferFullException 当队列满的时候抛异常
     */
    public synchronized void put(T t) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException();
        }
        super.doPut(t);
    }

    /**
     * get
     *
     * @return
     * @throws BufferEmptyException 让队列空的时候抛异常
     */
    public synchronized T get() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return super.doTake();
    }


    public static void main(String[] args) {
        BoundedBufferOne<String> buffer = new BoundedBufferOne<>(20);
        // 写入队列
        new Thread(() -> {
            // 这里需要自己手动实现重试
            while (true) {
                try {
                    buffer.put(Thread.currentThread().getName() + System.currentTimeMillis());
                    System.out.println(Thread.currentThread().getName() + "添加成功,size=" + buffer.size());
                } catch (BufferFullException e) {
                    System.out.println(Thread.currentThread().getName() + "添加失败,size=" + buffer.size());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 从队列中拿出
        new Thread(() -> {
            while (true) {
                try {
                    String s = buffer.get();
                    System.out.println(Thread.currentThread().getName() + "取出成功,size=" + buffer.size() + s);
                } catch (BufferEmptyException e) {
                    System.out.println(Thread.currentThread().getName() + "取出失败,size=" + buffer.size());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
