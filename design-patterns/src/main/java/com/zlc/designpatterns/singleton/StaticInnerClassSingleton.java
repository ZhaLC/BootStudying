package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:25
 * @desc : 静态内部类实现(推荐使用) 避免了线程不安全 延迟加载 效率高
 **/
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton(){}

    //静态内部类在StaticInnerClassSingleton被装载时并不会被立即实例化
    //类的静态属性只有在第一次装载时才会初始化
    private static class Singleton{
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance(){
        //调用时才会装载内部类Singleton 从而完成StaticInnerClassSingleton的实例化
        return Singleton.INSTANCE;
    }

}
