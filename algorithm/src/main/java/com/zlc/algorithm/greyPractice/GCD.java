package com.zlc.algorithm.greyPractice;

/**
 * greatest common divisor
 */
public class GCD {

    /**
     * 递归
     * 欧几里得算法(辗转相除法) 原理： gcd(a, b) = gcd(b, a mod b)
     */
    public static int getGCD(int m, int n){
        //TODO 要是参数可以是负数 需要取绝对值 否则会栈溢出
        int big = Math.max(m,n);
        int small = Math.min(m,n);
        if(big % small == 0){
            return small;
        }
        return getGCD(big % small, small);
    }

    /**
     * 非递归
     * 欧几里得算法
     */
    public static int getGCD2(int num1, int num2){
        // 先获得绝对值，保证负数也可以求
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        // 假定第一个数较大；如果第二个较大，在第二轮会颠倒过来
        // 如果第二个数为 0，则第一个数就是最大公约数
        while(num2 != 0){
            // 求余
            int remainder = num1 % num2;
            System.out.println(remainder);
            // 交换数，等同递归调用
            num1 = num2;
            num2 = remainder;
        }
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(getGCD(2,-6));
        System.out.println(2 % -6);
        System.out.println(-7 % 2);
    }

}
