package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:46
 * @desc : 饿汉式 静态常量(可用) 线程安全, 类装载的时候就进行了初始化, 避免了线程同步 但是没有达到懒加载的效果
 **/
public class Singleton1 {

    private final static Singleton1 singleton1 = new Singleton1();

    public Singleton1() {
    }

    public static Singleton1 getInstance(){
        return singleton1;
    }
}
