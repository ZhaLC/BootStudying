package com.zlc.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : ZLC
 * @create : 2020-06-28 9:40
 * @desc : Curator-framework
 * Curator主要解决了三类问题
 * 1.封装ZooKeeper client与ZooKeeper server之间的连接处理
 * 2.提供了一套Fluent风格的操作API
 * 3.提供ZooKeeper各种应用场景(recipe, 比如共享锁服务, 集群领导选举机制)的抽象封装
 **/
public class CuratorApi {

    /**
     * Curator客户端
     */
    public static CuratorFramework client = null;
    /**
     * 集群模式则是多个ip
     */
//    private static final String zkServerIps = "192.168.10.124:2182,192.168.10.124:2183,192.168.10.124:2184";
    private static final String zkServerIps = "10.130.25.133:2181";

    public static CuratorFramework getConnection() {
        if (client == null) {
            synchronized (CuratorApi.class) {
                if (client == null) {
                    //通过工程创建连接
                    client = CuratorFrameworkFactory.builder()
                            .connectString(zkServerIps)
                            .connectionTimeoutMs(5000) ///连接超时时间
                            .sessionTimeoutMs(5000)  // 设定会话时间
                            .retryPolicy(new ExponentialBackoffRetry(1000, 10))   // 重试策略：初试时间为1s 重试10次
//	           				.namespace("super")  // 设置命名空间以及开始建立连接
                            .build();

                    //开启连接
                    client.start();
                    System.out.println("开启连接: " + client.getState());
                }
            }
        }
        return client;
    }

    /**
     * 创建节点   不加withMode默认为持久类型节点
     *
     * @param path  节点路径
     * @param value 值
     */
    public static String create(String path, String value) {
        try {
            //若创建节点的父节点不存在会先创建父节点再创建子节点
            return getConnection().create().creatingParentsIfNeeded().forPath("/super" + path, value.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建节点
     *
     * @param path     节点路径
     * @param value    值
     * @param modeType 节点类型
     */
    public static String create(String path, String value, String modeType) {
        try {
            if (StringUtils.isEmpty(modeType)) {
                return null;
            }
            //持久型节点
            if (CreateMode.PERSISTENT.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super" + path, value.getBytes());
            }
            //临时节点
            if (CreateMode.EPHEMERAL.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super" + path, value.getBytes());
            }

            //持久类型顺序性节点
            if (CreateMode.PERSISTENT_SEQUENTIAL.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/super" + path, value.getBytes());
            }

            //临时类型顺序性节点
            if (CreateMode.EPHEMERAL_SEQUENTIAL.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/super" + path, value.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取单个节点
     *
     * @param path
     * @return
     */
    public static String getData(String path) {
        try {
            String str = new String(getConnection().getData().forPath("/super" + path));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字节点
     *
     * @param path
     * @return
     */
    public static List<String> getChildren(String path) {
        try {
            List<String> list = getConnection().getChildren().forPath("/super" + path);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 修改节点值
     *
     * @param path
     * @param valu
     * @return
     */
    public static String setData(String path, String valu) {
        try {
            getConnection().setData().forPath("/super" + path, valu.getBytes());
            String str = new String(getConnection().getData().forPath("/super" + path));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除节点
     *
     * @param path
     */
    public static void delete(String path) {
        try {
            getConnection().delete().guaranteed().deletingChildrenIfNeeded().forPath("/super" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检测节点是否存在
     *
     * @param path
     * @return
     */
    public static boolean checkExists(String path) {
        try {
            Stat s = getConnection().checkExists().forPath("/super" + path);
            return s == null ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) throws Exception {
        if (checkExists("/qxw")) {
            delete("/qxw");
        }
        System.out.println("创建节点：" + create("/qxw/q1", "苏打水法萨芬撒"));
        System.out.println("创建节点：" + create("/qxw/q2", "苏打水法萨芬撒"));
        System.out.println("创建节点：" + create("/qxw/q3", "苏打水法萨芬撒"));

        ExecutorService pool = Executors.newCachedThreadPool();
        getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework cf, CuratorEvent ce) throws Exception {
                        System.out.println("code:" + ce.getResultCode());
                        System.out.println("type:" + ce.getType());
                        System.out.println("线程为:" + Thread.currentThread().getName());
                    }
                }, pool)
                .forPath("/super/qxw/q4", "q4内容".getBytes());

        System.out.println("读取节点q3： " + getData("/qxw/q3"));
        System.out.println("读取节点q4： " + getData("/qxw/q4"));
        System.out.println("读取节点qxw： " + getData("/qxw"));
        System.out.println("读取字节点qxw：" + getChildren("/qxw").toString());

//        test();

    }

//    /**
//     * 分布式锁 对象
//     *
//     * @param path
//     * @return
//     */
//    public static InterProcessMutex getLock(String path) {
//        InterProcessMutex lock = null;
//        try {
//            lock = new InterProcessMutex(getConnection(), "/super" + path);
//            return lock;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /***
//     * 分布锁演示
//     */
//    private static int count = 0;
//    public static void test() throws InterruptedException {
//        final InterProcessMutex lock = getLock("/lock");
//        final CountDownLatch c = new CountDownLatch(10);
//        ExecutorService pool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            pool.execute(new Runnable() {
//                public void run() {
//                    try {
//                        c.countDown();
//                        Thread.sleep(1000);
//                        //加锁
//                        lock.acquire();
//                        System.out.println(System.currentTimeMillis() + "___" + (++count));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            lock.release();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//            });
//        }
//        pool.shutdown();
//        c.await();
//        System.out.println("CountDownLatch执行完");
//    }
//
//    static class InterProcessMutex {
//        private CuratorFramework connection;
//        private String path;
//
//        public InterProcessMutex(CuratorFramework connection, String path) {
//            this.connection = connection;
//            this.path = path;
//        }
//    }
}
