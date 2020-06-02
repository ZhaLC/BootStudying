package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-02 17:24
 * @desc : 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 **/
public class Q64_SumNums {

    //    public int sumNums(int n) {
//        if(n == 1){
//            return 1;
//        }
//        return getSum(1,2, n);
//    }
//    public int getSum(int sum, int next, int n){
//        if(next == n){
//            return sum + next;
//        }
//        sum += next;
//        next++;
//        return getSum(sum, next, n);
//    }

    //不能使用三目运算符
//    public int sumNums(int n) {
//        return n == 0 ? 0 : n + sumNums(n - 1);
//    }

    //通过逻辑运算符的短路特性
    // A && B --> A是false直接返回false, 不再执行B
    // A || B --> A是true直接返回true, 不再执行B
    // ----> 判断是否为递归的出口看做A && B 的A部分, 递归主体函数看做是B部分, A返回true则继续执行递归函数
    // ----> 同理也可以根据A || B 实现
    public int sumNums(int n) {
        //注意: 返回的是n  n += sumNums(n-1)    n += ...
//        boolean flag = n > 0 && (n += sumNums(n-1)) > 0;
//        return n;

        boolean flag = n <= 0 || (n += sumNums(n-1)) < 0;
        return n;
    }

    public int sumNums2(int n) {
        return 0;
    }




    public static void main(String[] args) {
        Q64_SumNums q = new Q64_SumNums();
        int n = q.sumNums(3);
        System.out.println(n);
    }

}
