package com.zlc.designpatterns.proxy.CglibProxy;

/**
 * @desc : Cglib代理目标类 无需实现接口
 **/
public final class UserDao{
    public  void save() {
        System.out.println("========保存数据========");
    }
}
