package com.zlc.algorithm.leetcode;

import java.util.HashMap;

/**
 * @author : ZLC
 * @create : 2020-06-12 10:52
 * @desc : 两数之和
 **/
public class Q1_TwoSum {
    public int[] twoSum(int[] nums, int target) {

        /**
         * 3、一遍哈希
         * 时间: O(n) 哈希表将时间查找缩减到O(1)(冲突哈希查找可能会退化到O(n))
         * 空间: O(n)
         * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i],i);
        }
        throw new RuntimeException("没有");


        /**
         * 2、两遍哈希
         * 时间: O(n) 哈希表将时间查找缩减到O(1)
         * 空间: O(n)
         * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
         */
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i],i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int complement = target - nums[i];
//            if(map.containsKey(complement) && map.get(complement) != i){
//                // 注意这里顺序 是i在前
//                return new int[]{i, map.get(complement)};
//                // return new int[]{map.get(complement), i};
//            }
//        }
//        throw new RuntimeException("没有");

        /**
         * 1、暴力 通过了
         * 时间: O(n²)
         */
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                if(nums[i] + nums[j] == target){
//                    return new int[]{i,j};
//                }
//            }
//        }
//        throw new RuntimeException("没有");
    }
}
