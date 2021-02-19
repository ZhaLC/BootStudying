package com.zlc.designpatterns.factory.simple;

/**
 * @author : zlc
 * @create : 2021-01-25 11:05
 * @desc : 使用
 **/
public class Main {

    public static void main(String[] args) {
        INoodles noodles = NoodlesFactory.create(NoodlesFactory.TYPE_LA);
        noodles.desc();
    }
}
