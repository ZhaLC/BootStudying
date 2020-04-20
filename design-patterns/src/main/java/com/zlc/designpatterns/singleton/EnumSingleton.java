package com.zlc.designpatterns.singleton;

/**
 * @author : ZLC
 * @create : 2020-04-20 17:39
 * @desc : 枚举实现 jdk1.5之后 (推荐 不会受到反射、序列化影响)
 **/
public enum EnumSingleton {
    INSTANCE;
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
