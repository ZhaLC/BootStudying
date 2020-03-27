package com.zlc.studying.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : ZLC
 * @create : 2020-03-27 15:40
 * @desc : CountDownLatchDemo CountDownLatch 提供了一个构造器, 三个方法, 见代码注释1234
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) {
        //1、构造方法 参数为计数值
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println("主线程开始执行");
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                //2、void countDown(){} 将计数值减1
                countDownLatch.countDown();
            }
        });
        es1.shutdown();

        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                //将count值减1
                countDownLatch.countDown();
            }
        });
        es2.shutdown();

        System.out.println("等待两个子线程执行完毕");
        try {
            //3、void await() throws InterruptedException{} 调用await()方法的线程将被挂起, 直到计数值减为0继续执行
            //4、boolean await(long timeout, TimeUnit unit) throws InterruptedException{} 和上面类似, 等待一段时间不变为0直接继续执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程都执行完毕, 继续执行主线程");
    }

}
