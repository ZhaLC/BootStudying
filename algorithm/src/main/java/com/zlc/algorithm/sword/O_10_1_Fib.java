package com.zlc.algorithm.sword;

/**
 * @desc :
 **/
public class O_10_1_Fib {
    //动态规划
    public static int fib(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        int fib0 = 0;
        int fib1 = 1;
        int temp;
        for(int i = 2; i <= n; i++){
            temp = fib0 + fib1;
            fib0 = fib1;
            fib1 = temp % 1000000007; //题目描述数据大 要取余 每次都除, 防止越界
        }
        return fib1;
    }

//    //递归 大量重复运算导致超时
//    public int fib(int n) {
//        if(n == 0){
//            return 0;
//        }
//        if(n == 1){
//            return 1;
//        }
//        return fib(n-1) + fib(n-2);
//    }
}
