package com.wenjun.baseboundedbuffer;

/**
 * 有界缓冲队列
 *
 * @Author RUANWENJUN
 * @Creat 2018-10-14 10:44
 */

public abstract class AbstractBaseBoundedBuffer<T> {

    /**
     * [head....tail]
     */
    private final Object[] buf;
    private int tail;
    private int head;
    private int count;

    protected AbstractBaseBoundedBuffer(int capacity) {
        this.buf = new Object[capacity];
    }

    protected synchronized final void doPut(T t) {
        buf[tail] = t;
        if (++tail == buf.length) {
            // 循环队列,这里还应该考虑跟头位置比较
            tail = 0;
        }
        count++;
    }

    protected synchronized final T doTake() {
        T t = (T) buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            // 这里还应该跟尾比较
            head = 0;
        }
        count--;
        return t;
    }

    protected synchronized final boolean isFull() {
        return count == buf.length;
    }

    protected synchronized final boolean isEmpty() {
        return count == 0;
    }

    protected synchronized final int size() {
        return count;
    }
}
