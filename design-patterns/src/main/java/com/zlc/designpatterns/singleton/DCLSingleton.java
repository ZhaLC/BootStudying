package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:19
 * @desc : DCL(double check lock/loading?) 单例模式 推荐使用 线程安全 延迟加载 效率较高
 **/
public class DCLSingleton {

    //此处必须是volatile 否则可能获取到半初始化对象
    private static volatile DCLSingleton singleton;

    public static DCLSingleton getSingleton(){
        //也就是这里判断的时候不是null了 但是其实对象还没有赋值
        if(singleton == null){
            synchronized (DCLSingleton.class){
                if(singleton == null){
                    //这里不是原子的 如果上面不加volatile 可能会获取到半初始化对象(没有赋值)
                    //对应三条CPU指令
                    //1、为Singleton分配一块内存M
                    //2、在内存M上对singleton进行初始化
                    //3、将内存M地址赋值给singleton变量
                    singleton = new DCLSingleton();
                }
            }
        }
        return singleton;
    }

    private DCLSingleton() {
    }
}
