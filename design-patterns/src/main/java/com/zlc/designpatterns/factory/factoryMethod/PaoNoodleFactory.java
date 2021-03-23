package com.zlc.designpatterns.factory.factoryMethod;

/**
 * @author : zlc
 * @create : 2021-03-23 11:28
 * @desc : 泡面具体工厂
 **/
public class PaoNoodleFactory extends NoodlesFactory{
    @Override
    INoodles create() {
        return new PaoNoodle();
    }
}
