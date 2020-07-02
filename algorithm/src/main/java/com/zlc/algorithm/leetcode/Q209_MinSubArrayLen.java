package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-28 16:54
 * @desc : 长度最小的子数组
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/javade-jie-fa-ji-bai-liao-9985de-yong-hu-by-sdwwld/
 **/
public class Q209_MinSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {

        /**
         * 双指针? 滑动窗口? 队列?
         * 时间复杂度：O(n)，其中 n 是数组的长度。指针 start 和 end 最多各移动 n 次。
         * 空间复杂度：O(1)。
         */
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while(end < n){
            sum += nums[end];
            while(sum >= s){
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;


        /**
         * 1、暴力 O(n²)
         */
//        if(nums.length == 0){
////            return 0;
////        }
////        int ans = Integer.MAX_VALUE;
////        for (int i = 0; i < nums.length ; i++) {
////            int sum = 0;
////            for (int j = i; j < nums.length; j++) {
////                sum += nums[j];
////                if(sum >= s){
////                    ans = Math.min(ans, j-i+1);
////                    break;
////                }
////            }
////        }
////        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
