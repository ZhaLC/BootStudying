package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-05-16 11:00
 * @desc : 和为k的子数组 个数
 **/
public class Q560_SubArraySum {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i+1; j < nums.length; j++){
                sum += nums[j];
                if(sum == k){
                    count++;
                }
            }
        }
        return count;
    }
}
