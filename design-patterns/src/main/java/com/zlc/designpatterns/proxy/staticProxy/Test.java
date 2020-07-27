package com.zlc.designpatterns.proxy.staticProxy;

/**
 * @desc :
 **/
public class Test {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        proxy.save();
    }


}
