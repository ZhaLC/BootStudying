package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:49
 * @desc : 饿汉式 静态代码块(可用) 和静态常量一样 都是在类装载的时候就初始化
 **/
public class Singleton2 {

    private static Singleton2 singleton2;

    private Singleton2() {
    }

    static{
        singleton2 = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return singleton2;
    }

}
