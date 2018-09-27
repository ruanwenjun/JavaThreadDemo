package ChapterOne;

/**
 * 使用继承Thread类来创建线程
 *
 * @Author RUANWENJUN
 * @Creat 2018-06-03 10:21
 */

public class WelcomeThread1 {
    public static void main(String[] args) {
        WelcomeThread thread = new WelcomeThread();
        thread.start();

        System.out.println("Welcome! I'm " + Thread.currentThread().getName());

    }

    static class WelcomeThread extends Thread{
        @Override
        public void run() {
            System.out.println("Welcome! I'm " + Thread.currentThread().getName());
        }
    }
}
