package com.zlc.designpatterns.proxy.jdkProxy;

/**
 * @desc : 动态代理目标类, 接口实现
 **/
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("========保存数据========");
    }
}
