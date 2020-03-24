package com.zlc.studying.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : ZLC
 * @create : 2020-03-24 17:13
 * @desc : 自旋锁demo
 **/
class MySpinLock {

    private AtomicReference<Thread> reference = new AtomicReference<>();

    public void lock(){
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + " want to get lock");
        while(!reference.compareAndSet(null, t)){
            System.out.println(t.getName() + " try to get lock");
        }
        System.out.println(t.getName() + " got the lock");
    }

    public void unlock(){
        System.out.println(Thread.currentThread().getName()+" want to Unlock");
        reference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName()+" Unlock ");
    }
}

public class SpinLockDemo{
    public static void main(String[] args) {
        MySpinLock mySpinLock = new MySpinLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mySpinLock.lock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySpinLock.unlock();
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mySpinLock.lock();
                mySpinLock.unlock();
            }
        },"t2").start();

    }
}
