package com.zlc.config;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author : ZLC
 * @create : 2020-07-02 23:17
 * @desc : 获取zk
 **/
public class ZKUtils {

    private static ZooKeeper zk;
    private static String address = "192.168.181.11:2181,192.168.181.12:2181,192.168.181.13:2181,192.168.181.14:2181/testConf";
    private static DefaultWatch watch = new DefaultWatch();
    private static CountDownLatch init  =  new CountDownLatch(1);

    public static ZooKeeper getZk(){
        try {
            zk = new ZooKeeper(address,1000,watch);
            watch.setCc(init);
            init.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }

}
