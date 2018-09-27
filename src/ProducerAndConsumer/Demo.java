package ProducerAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author RUANWENJUN
 * @Creat 2018-09-25 10:39
 */

public class Demo {
    public static void main(String[] args) {
        BlockingQueue<Food> blockingQueue = new ArrayBlockingQueue<Food>(10);
        Producer producer = new Producer(blockingQueue);
        Comsumer comsumer = new Comsumer(blockingQueue);
        new Thread(producer).start();
        new Thread(comsumer).start();

    }
}
