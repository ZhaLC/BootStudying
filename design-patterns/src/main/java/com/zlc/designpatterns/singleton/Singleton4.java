package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:57
 * @desc : 懒汉式 同步方法 线程安全 不推荐使用 同步效率太低
 **/
public class Singleton4 {

    private static Singleton4 singleton4;

    public Singleton4() {
    }

    public static synchronized Singleton4 getInstance(){
        if(singleton4 == null){
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}
