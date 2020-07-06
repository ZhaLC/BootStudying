package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:54
 * @desc : 懒汉式(不可用) 线程不安全
 **/
public class Singleton3 {

    private static Singleton3 singleton3;

    private Singleton3() {
    }

    public static Singleton3 getInstance(){
        if(singleton3 == null){
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}
