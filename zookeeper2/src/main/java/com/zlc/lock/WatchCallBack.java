package com.zlc.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import sun.awt.windows.WToolkit;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author : ZLC
 * @create : 2020-07-03 21:34
 * @desc :
 **/
public class WatchCallBack
        implements Watcher,
        AsyncCallback.StringCallback,
        AsyncCallback.Children2Callback,
        AsyncCallback.StatCallback {

    private String threadName; //线程名
    private ZooKeeper zk;
    CountDownLatch cc = new CountDownLatch(1);
    String pathName; //   /lock0000000021

    public void tryLock() throws InterruptedException {

        //TODO 集合判断是最小获得锁 设置根内容为线程名 可以实现重入 这里比较复杂不写了
        //if(zk.getData("/")){}

        System.out.println(threadName + " create.......");
        //this: StringCallback
        zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "ctx");
        //阻塞着
        cc.await();
    }

    public void unLock(){
        try {
            //-1 忽略版本判定
            zk.delete(pathName, -1);
            System.out.println(threadName + " over working......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    //StringCallBack create回调
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        //创建成功
        if(name != null){
            pathName = name;
            System.out.println(threadName + " create node : " + pathName);
            //判断自己是否是最小的(查询到父节点下所有) false不需要监控父节点 this: Children2Callback
            zk.getChildren("/", false, this, "ctx");
        }
    }

    //Children2Callback  zk.getChildren回调
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        //进到这个方法说明自己创建完了 且 看到了自己前面创建的所有节点
        //打印看到是乱序的 lock0000000021 且前面没有/
//        System.out.println(threadName + " look locks ....");
//        for (String child : children) {
//            System.out.println(child);
//        }
        Collections.sort(children);
        int index = children.indexOf(pathName.substring(1));//把前面的/去掉

        //是不是第一个
        if(index == 0){
            try {
                System.out.println(threadName + " i am first.....");
                zk.setData("/", threadName.getBytes(), -1);
                cc.countDown();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            //不是第一个 没拿到锁 需要监听前一个this 1 : Watcher, this2: 有可能监控一瞬间, 前一个掉线了 StatCallback
            zk.exists("/" + children.get(index-1), this, this, "ctx");
        }

    }

    //Watcher  zk.exists的watch
    @Override
    public void process(WatchedEvent watchedEvent) {
        //如果第一个哥们，那个锁释放了，其实只有第二个收到了回调事件！！
        //如果，不是第一个哥们，某一个，挂了，也能造成他后边的收到这个通知，从而让他后边那个跟去watch挂掉这个哥们前边的。。。
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                //this : Children2Callback 该方法同上面的getChildren
                zk.getChildren("/", false, this, "ctx");
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
        }
    }

    //StatCallback  zk.exists 回调
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        //TODO 下次再说
    }


    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }


}
