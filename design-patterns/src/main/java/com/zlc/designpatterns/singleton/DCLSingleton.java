package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:19
 * @desc : DCL(double check lock) 单例模式 推荐使用 线程安全 延迟加载 效率较高
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
                    singleton = new DCLSingleton();
                }
            }
        }
        return singleton;
    }

    private DCLSingleton() {
    }
}
