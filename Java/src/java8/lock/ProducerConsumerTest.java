package java8.lock;

import java.util.concurrent.*;

/**
 * @desc : 生产者消费者模式(阻塞队列实现)
 **/
public class ProducerConsumerTest {

    static class Consumer implements Runnable{
        // 阻塞队列用于存放产品
        // ArrayBlockingQueue 好像是生产、消费用的一个锁 不能同时进行
        private LinkedBlockingQueue<Integer> queue;
        public Consumer(LinkedBlockingQueue<Integer> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            while(true){
                try {
                    int sth = queue.take();
                    System.out.println("consumer : " + sth);
                    //睡1秒继续消费
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable{

        private LinkedBlockingQueue<Integer> queue;

        public Producer(LinkedBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int i = 0;
            while(true){
                try {
                    queue.put(i);
                    System.out.println("producer : " + i);
                    i++;
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService producePool = new ThreadPoolExecutor(
                1,1,0,TimeUnit.SECONDS,new LinkedBlockingDeque<>(100),
                new ThreadPoolExecutor.AbortPolicy());

        ExecutorService consumerPool = new ThreadPoolExecutor(
                5,5,0,TimeUnit.SECONDS,new LinkedBlockingDeque<>(100),
                new ThreadPoolExecutor.AbortPolicy());

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        producePool.execute(new Producer(queue));
        consumerPool.execute(new Consumer(queue));
        consumerPool.execute(new Consumer(queue));
        consumerPool.execute(new Consumer(queue));
        consumerPool.execute(new Consumer(queue));
        consumerPool.execute(new Consumer(queue));
        System.out.println("===================");

    }


}
