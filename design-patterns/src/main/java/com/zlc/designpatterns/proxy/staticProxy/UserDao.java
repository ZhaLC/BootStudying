package com.zlc.designpatterns.proxy.staticProxy;

/**
 * @desc : 目标类, 接口实现
 **/
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("========保存数据========");
    }
}
