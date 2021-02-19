package com.zlc.designpatterns.factory.simple;

/**
 * @author : zlc
 * @create : 2021-01-25 11:00
 * @desc : 简单工厂
 **/
public class NoodlesFactory {

    public static final int TYPE_PAO = 1;
    public static final int TYPE_LA = 2;

    /**
     * 1、参数也可以用Class, 用反射创建对象 Class c,  ()Class.forName(c.getName()).newInstance())
     * 2、也可以不指定参数, 这里获取INoodles所有实现类, 然后随机产生对象(参考设计模式之禅)
     * @param type
     * @return
     */
    public static INoodles create(int type){
        switch (type){
            case TYPE_LA:
                return new LaNoodle();
            default:
                return new PaoNoodle();
        }
    }
}
