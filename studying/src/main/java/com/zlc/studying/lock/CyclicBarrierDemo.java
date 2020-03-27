package com.zlc.studying.lock;

import java.util.concurrent.CyclicBarrier;

/**
 * @author : ZLC
 * @create : 2020-03-27 17:17
 * @desc : 循环栅栏demo
 **/
public class CyclicBarrierDemo {

    static class TaskThread extends Thread{
        CyclicBarrier cyclicBarrier;
        public TaskThread(CyclicBarrier barrier) {
            this.cyclicBarrier = barrier;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                cyclicBarrier.await();
                System.out.println(getName() + " 冲破栅栏 A");

                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                cyclicBarrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
}
