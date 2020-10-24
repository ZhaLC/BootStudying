package com.zlc.studying.retry.simpleRetry;

/**
 * @desc : 简单重试
 **/
public class SimpleRetry {
    private final static int MAX_TIMES = 5;
    //调用处
    public void test(){
        int time = 0;
        while(time < MAX_TIMES){
            try {
                doSth();
            } catch (Exception e) {
                time++;
                if(time >= MAX_TIMES){
                    e.printStackTrace();
                }
            }
        }
    }

    // 实际工作的接口
    private void doSth(){
        System.out.println("doSth.................");
        throw new RuntimeException("测试重试");
    }

    public static void main(String[] args) {
        new SimpleRetry().test();
    }

}
