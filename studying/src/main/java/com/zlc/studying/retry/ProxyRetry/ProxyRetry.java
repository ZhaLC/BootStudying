package com.zlc.studying.retry.ProxyRetry;

/**
 * @desc :
 **/
public class ProxyRetry {

    public void doSth(){
        System.out.println("ProxyRetry doSth..........");
        throw new RuntimeException("测试ProxyRetry");
    }

    public static void main(String[] args) {
        ProxyRetry retry = new ProxyRetry();
        ProxyRetry proxyFactory = (ProxyRetry) new RetryProxyFactory(retry).getProxyInstance();
        proxyFactory.doSth();
    }
}
