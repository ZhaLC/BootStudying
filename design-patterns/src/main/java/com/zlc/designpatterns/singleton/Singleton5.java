package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 18:10
 * @desc : 懒汉式 同步代码块 线程不安全
 **/
public class Singleton5 {

    private static Singleton5 singleton5;

    private Singleton5() {
    }

    public static Singleton5 getInstance(){
        if(singleton5 == null){
            synchronized (Singleton5.class){
                singleton5 = new Singleton5();
            }
        }
        return singleton5;
    }
}
