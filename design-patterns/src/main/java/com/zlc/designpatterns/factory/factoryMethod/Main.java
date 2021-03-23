package com.zlc.designpatterns.factory.factoryMethod;

/**
 * @author : zlc
 * @create : 2021-01-25 11:05
 * @desc : 使用
 **/
public class Main {

    public static void main(String[] args) {
        LaNoodleFactory laNoodleFactory = new LaNoodleFactory();
        laNoodleFactory.create().desc();

        PaoNoodleFactory paoNoodleFactory = new PaoNoodleFactory();
        paoNoodleFactory.create().desc();
    }
}
