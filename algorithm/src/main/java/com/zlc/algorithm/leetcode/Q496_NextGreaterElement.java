package com.zlc.algorithm.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author : ZLC
 * @create : 2020-06-11 22:07
 * @desc : 下一个更大元素
 **/
public class Q496_NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /**
         * 单调栈 两个数组辅助使用了HashMap
         * 时间: O(m+n) m和n分别是数组num1和num2的长度
         * 空间: O(n) 遍历num2时使用栈
         */
        int len2 = nums2.length;
        int[] res = new int[nums1.length];
        // 下面的Deque也是stack 是非synchronized的  Stack里面的方法都是synchronized的
        Deque<Integer> stack = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len2; i++) {
            while(!stack.isEmpty() && nums2[i] > stack.peek()){
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
