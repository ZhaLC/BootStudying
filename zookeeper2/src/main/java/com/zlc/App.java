package com.zlc;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * zk连接基本api
 */
public class App {
    public static void main(String[] args) throws Exception{
        System.out.println("Hello World!");
        /**
         * zk有session概念 没有线程池的概念
         * watch 观察,回调  有两类
         *      watch的注册只发生在读类型调用 get exists ...
         *      1) new zk时候传的watch session级别的, 跟path, node没有关系 
         */
        final CountDownLatch cd = new CountDownLatch(1);
        //第二个参数 会决定session断开后临时节点存在的时间
        final ZooKeeper zk = new ZooKeeper("192.168.181.11:2181,192.168.181.12:2181,192.168.181.13:2181,192.168.181.14:2181",
                3000,
                new Watcher() {
                    //watch 回调方法
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        Event.KeeperState state = watchedEvent.getState();
                        Event.EventType type = watchedEvent.getType();
                        String path = watchedEvent.getPath();
                        System.out.println("new zk watch : " + watchedEvent.toString());

                        switch (state) {
                            case Unknown:
                                break;
                            case Disconnected:
                                break;
                            case NoSyncConnected:
                                break;
                            case SyncConnected:
                                //连接成功
                                System.out.println("connected");
                                cd.countDown();
                                break;
                            case AuthFailed:
                                break;
                            case ConnectedReadOnly:
                                break;
                            case SaslAuthenticated:
                                break;
                            case Expired:
                                break;
                        }

                        switch (type) {
                            case None:
                                break;
                            case NodeCreated:
                                break;
                            case NodeDeleted:
                                break;
                            case NodeDataChanged:
                                break;
                            case NodeChildrenChanged:
                                break;
                        }

                    }
                });

        //上面没连接完 就已经开始下面的了  所以需要一个异步同步 CountDownLatch
        cd.await();
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("ing............");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed............");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }

        //create根据参数 两种  一种阻塞的(带返回值) 另一种异步回调的(不带, 回调给)
        zk.create("/api", "oldData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        final Stat stat = new Stat();
        byte[] node = zk.getData("/api", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData watch: " + watchedEvent.toString());
                try {
                    //在回调这里继续调用这个 可以一直watch了
                    //为true时是default watch 被重新注册 new zk时那个watch
                    //zk.getData("/api", true, stat);
                    //为this时是还是这个watch
                    zk.getData("/api", this, stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);//stat参数会返回节点元数据 cZxid等
        System.out.println("node: " + new String(node));
        System.out.println("stat :" + stat.toString());

        //触发回调 回调只能一次
        Stat stat1 = zk.setData("/api", "newdata".getBytes(), 0);
        //还会触发吗？ 不会触发 想一直监控 就在上面get的回调方法里process在处理一下
        Stat stat2 = zk.setData("/api", "newdata01".getBytes(), stat1.getVersion());

        //getData不阻塞直接返回形式
        System.out.println("-------async start----------");
        zk.getData("/api", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("-------async call back----------");
                System.out.println(ctx.toString());
                System.out.println(new String(data));
            }
        },"abc");
        System.out.println("-------async over----------");


        Thread.sleep(222222);

    }
}
