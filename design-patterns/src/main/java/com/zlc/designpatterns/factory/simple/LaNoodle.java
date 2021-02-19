package com.zlc.designpatterns.factory.simple;

/**
 * @author : zlc
 * @create : 2021-01-25 10:58
 * @desc : 拉面
 **/
public class LaNoodle extends INoodles {

    @Override
    public void desc() {
        System.out.println("拉面口味不同");
    }
}
