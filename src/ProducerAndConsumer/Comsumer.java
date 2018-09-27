package ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 *
 * @Author RUANWENJUN
 * @Creat 2018-09-25 10:36
 */

public class Comsumer implements Runnable{
    private BlockingQueue<Food> blockingQueue;
    public Comsumer(BlockingQueue<Food> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            try {
                Food food = blockingQueue.take();
                System.out.println("消费一个:"+food.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("消费失败");
            }
        }
    }
}
