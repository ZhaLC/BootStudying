package com.zlc.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author : ZLC
 * @create : 2020-07-03 7:58
 * @desc : path的watch
 **/
public class WatchCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    ZooKeeper zk ;
    MyConf conf ;
    CountDownLatch cc = new CountDownLatch(1);

    public MyConf getConf() {
        return conf;
    }

    public void setConf(MyConf conf) {
        this.conf = conf;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void aWait(){
        zk.exists("/AppConf",this,this ,"ABC");
        try {
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //AsyncCallback.DataCallback
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        if(bytes != null){
            String ss = new String(bytes);
            conf.setConf(ss);
            cc.countDown();
        }
    }

    //AsyncCallback.StatCallback
    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        //节点存在
        if(stat != null){
           zk.getData("/AppConf", this, this, "ctx");
        }
    }

    //Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/AppConf",this,this,"sdfs");
                break;
            case NodeDeleted:
                //容忍性
                conf.setConf("");
                //删除了 清空配置 同时阻塞着
                cc = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.getData("/AppConf",this,this,"sdfs");
                break;
            case NodeChildrenChanged:
                break;
        }
    }
}
