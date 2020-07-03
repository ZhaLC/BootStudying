package com.zlc.lock;

import com.zlc.config.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : ZLC
 * @create : 2020-07-03 12:32
 * @desc :
 **/
public class TestLock {

    ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZKUtils.getZk();
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        //不同的watch 拿不同CountDownLatch
                        WatchCallBack watchCallBack = new WatchCallBack();
                        watchCallBack.setZk(zk);
                        String threadName = Thread.currentThread().getName();
                        watchCallBack.setThreadName(threadName);

                        //每一个线程
                        //抢锁
                        watchCallBack.tryLock();

                        //干活
                        System.out.println(threadName + " working.......");

                        //如果把这个干掉 会产生死锁现象 但是不是死锁 节点确实删除了 但是后面的不能继续获得所运行
                        //太快了 前面的节点删除完了  后面的节点也监控上了
                        //实际解决 在判断自己是最小 获得锁时 向根上设置数据 一方面增加运行时间解决上面说的问题
                        // 另一方面 解决可重入问题: //TODO
                        //Thread.sleep(1000);

                        //释放锁
                        watchCallBack.unLock();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        //阻塞住 别退出
        while(true){

        }

    }

}
