package com.zlc.studying.retry.ProxyRetry;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @desc : cglib代理实现重试
 **/
public class RetryProxyFactory implements MethodInterceptor {

    private Object target;
    public RetryProxyFactory(Object target) {
        this.target = target;
    }
    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        int times = 0;
        Object returnValue = null;
        while(times < 5){
            try {
                //参数
                returnValue = method.invoke(target, objects);
            } catch (Exception e) {
                times++;
                if(times >= 5){
                    e.printStackTrace();
                }
            }
         }
        return returnValue;
    }
}
