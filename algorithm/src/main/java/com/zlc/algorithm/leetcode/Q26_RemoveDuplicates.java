package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-29 17:37
 * @desc : 删除数组中的重复项
 **/
public class Q26_RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int i  = 0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] == nums[i]){
                continue;
            }
            i++;
            nums[i] = nums[j];
        }
        return i;
    }

}
