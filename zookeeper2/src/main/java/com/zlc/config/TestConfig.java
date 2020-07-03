package com.zlc.config;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : ZLC
 * @create : 2020-07-02 23:16
 * @desc : 测试
 **/
public class TestConfig {

    ZooKeeper zk;

    @Before
    public void conn (){
        zk  = ZKUtils.getZk();
    }

    @After
    public void close (){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConf() {

        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setZk(zk);
        MyConf myConf = new MyConf();
        watchCallBack.setConf(myConf);

        //异步的 所以下面使用while
        //既然封装了就封装彻底  这一步判断也加到watchCallBack中
        //zk.exists("/AppConf", watchCallBack, watchCallBack, "ctx");
        watchCallBack.aWait();
        //1，节点不存在
        //2，节点存在

        while (true) {

            if (myConf.getConf().equals("")) {
                System.out.println("conf diu le ......");
                //被删了 就在这阻塞着
                watchCallBack.aWait();
            } else {
                System.out.println(myConf.getConf());

            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
