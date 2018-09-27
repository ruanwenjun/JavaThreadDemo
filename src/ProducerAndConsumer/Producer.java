package ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-25 10:24
 */

public class Producer implements Runnable {
    /**
     * 队列，用于存放生产者
     */
    private BlockingQueue<Food> blockingQueue;

    public Producer(BlockingQueue<Food> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Food food = new Food("饺子"+i);
                blockingQueue.put(food);
                System.out.println("生产一个:" + food.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("添加失败");
            }
        }
    }
}
