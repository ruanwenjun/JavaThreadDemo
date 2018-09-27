package com.wenjun.Synchronized;

/**
 * 可重入性测试
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-27 11:52
 */

public class ReentrantSon extends ReentrantFather{
    /**
     *
     * 该方法会调用父类的方法，没有发生死锁，
     * 结果表明synchronized是可重入锁
     */
    @Override
    public synchronized void print(){
        System.out.println("我是子类，该方法用synchronized修饰");
        super.print();
        System.out.println("我是子类，该方法执行完毕");
    }

    public static void main(String[] args) {
        ReentrantSon s = new ReentrantSon();
        s.print();
    }
}
