package com.zlc.designpatterns.factory.factoryMethod;

/**
 * @author : zlc
 * @create : 2021-03-23 11:27
 * @desc : 拉面具体工厂
 **/
public class LaNoodleFactory extends NoodlesFactory{

    @Override
    INoodles create() {
        return new LaNoodle();
    }
}
