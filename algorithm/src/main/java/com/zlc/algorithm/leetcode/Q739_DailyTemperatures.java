package com.zlc.algorithm.leetcode;

import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-06-11 21:36
 * @desc : 每日温度
 **/
public class Q739_DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {

        /**
         * 单调栈使用 496 901 42 84
         * 时间 : O(n) 每个元素最多入栈出栈一次
         * 空间: O(n) 单调栈的栈空间
         */
        Stack<Integer> stack = new Stack<>();
        int len = T.length;
        int[] arr = new int[len];
        int temp;
        for (int i = 0; i < len; i++) {
            //arr[i] = 0; //不用加 默认值就是0
            int temperature = T[i];
            while(!stack.isEmpty() && temperature > T[stack.peek()]){
                temp = stack.pop();
                arr[temp] = i - temp;
            }
            stack.push(i);
        }
        return arr;
    }

}
