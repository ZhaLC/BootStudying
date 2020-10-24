package com.zlc.studying.retry.aop;

/**
 * @desc :
 **/
public class Test {

    @Retryable(maxAttempts = 5, value = RuntimeException.class)
    public void fiveTimes() {
        System.out.println("fiveTimes called!");
        throw new RuntimeException("测试");
    }

    public static void main(String[] args) {
        new Test().fiveTimes();
    }
}
