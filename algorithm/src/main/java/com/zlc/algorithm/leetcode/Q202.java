package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-04-30 17:44
 * @desc : 快乐数  //TODO 链表 快慢指针
 **/
public class Q202 {

    public int squareSum(int n){
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n){
        int slow = n;
        int fast = squareSum(n);
        while(slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return slow == 1;
    }

    public static void main(String[] args) {
        System.out.println(new Q202().isHappy(19));
    }

}
