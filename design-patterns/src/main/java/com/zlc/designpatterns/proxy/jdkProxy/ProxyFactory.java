package com.zlc.designpatterns.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @desc : 动态代理代理工厂 不需要实现接口
 **/
public class ProxyFactory {
    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }
    //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), //指定当前目标对象使用类加载器,获取加载器的方法是固定的
                target.getClass().getInterfaces(), //目标对象实现的接口的类型,使用泛型方式确认类型
                new InvocationHandler() { //事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("=====动态代理开始事务=====");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("=====动态代理提交事务=====");
                        return returnValue;
                    }
                }
        );
    }
}
