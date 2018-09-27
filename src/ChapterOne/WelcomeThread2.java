package ChapterOne;

/**
 * 使用创建Runnable接口实例的方式创建线程
 *
 * @Author RUANWENJUN
 * @Creat 2018-06-03 10:26
 */

public class WelcomeThread2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new WelcomeTask());
        thread.start();

        System.out.println("Welcome I'm " + Thread.currentThread().getName());
    }
    static class WelcomeTask implements Runnable{

        @Override
        public void run() {
            System.out.println("Welcome! I'm " + Thread.currentThread().getName());
        }
    }
}
