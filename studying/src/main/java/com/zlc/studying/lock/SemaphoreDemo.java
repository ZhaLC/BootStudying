package com.zlc.studying.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : ZLC
 * @create : 2020-03-30 11:02
 * @desc : Semaphore 信号量 和锁类似 控制某组资源的访问权限
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        int num = 8;
        Semaphore semaphore = new Semaphore(5);
        for(int i = 0; i < num; i++){
            new Worker(i,semaphore).start();
        }
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num, Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一台机器正在生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("工人" + this.num + "释放机器");
            semaphore.release();
        }
    }


}
