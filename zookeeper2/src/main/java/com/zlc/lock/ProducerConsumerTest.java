package com.zlc.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @desc :
 **/
public class ProducerConsumerTest {

    private static Logger logger = LoggerFactory.getLogger(ProducerConsumerTest.class);

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
                    logger.info("consumer : " + sth);
                    //睡1秒继续消费
                    Thread.sleep(2000);
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
                    logger.info("producer : " + i);
                    i++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {


        /**
         * 谷歌的自定义线程工程: new ThreadFactoryBuilder().setNameFormat("Produce_%s").build()
         *     <dependency>
         *       <groupId>com.google.guava</groupId>
         *       <artifactId>guava</artifactId>
         *       <version>19.0</version>
         *     </dependency>
         */
        ThreadFactory produceThreadFactory =
                new ThreadFactoryBuilder().setNameFormat("Produce_%s").build();
        ThreadFactory consumerThreadFactory =
                new ThreadFactoryBuilder().setNameFormat("Consumer_%s").build();

        ExecutorService producePool = new ThreadPoolExecutor(
                1,1,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                produceThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        ExecutorService consumerPool = new ThreadPoolExecutor(
                5,5,0,TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(100),
                consumerThreadFactory,
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
