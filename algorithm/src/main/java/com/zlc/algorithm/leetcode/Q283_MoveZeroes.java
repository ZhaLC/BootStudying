package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-30 9:58
 * @desc : 移动零
 **/
public class Q283_MoveZeroes {
    /**
     *  双指针
     *  时间 O(n)
     *  空间 O(1)
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int j  = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[j] = nums[i];
                if(i != j){
                   nums[i] = 0;
                }
                j++;
            }
        }
    }
}
