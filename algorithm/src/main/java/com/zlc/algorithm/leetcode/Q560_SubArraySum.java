package com.zlc.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ZLC
 * @create : 2020-05-16 11:00
 * @desc : 和为k的子数组 个数 (1、暴力解法  2、前缀和)
 **/
public class Q560_SubArraySum {

    //暴力解法 O(n²) / O(1)
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

    //前缀和 O(n²) / O(n)
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        //构建前缀和数组 这里构建的前缀和是 preNums[i+1] = preNums[i] + nums[i]
        int[] preNums = new int[len+1];
        preNums[0] = 0;
        for(int i = 0; i < len; i++){
            preNums[i+1] = preNums[i] + nums[i];
        }
        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++){
                //TODO 注意:  区间是[left, right] 左右都包含
                if(preNums[right+1] - preNums[left] == k){
                    count++;
                }
            }
        }
        return count;
    }
    //前缀和 + 哈希表 O(n) / O(n)
    // https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/dai-ni-da-tong-qian-zhui-he-cong-zui-ben-fang-fa-y/
    // https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/bao-li-jie-fa-qian-zhui-he-qian-zhui-he-you-hua-ja/
    public int subarraySum3(int[] nums, int k) {
        Map<Integer, Integer> preNumFreq = new HashMap<>();
        preNumFreq.put(0,1);
        int count = 0;
        int preSum = 0;
        for(int num : nums){
            preSum += num;
            if(preNumFreq.containsKey(preSum - k)){
                count += preNumFreq.get(preSum - k);
            }
            preNumFreq.put(preSum, preNumFreq.getOrDefault(preSum,0)+1);
        }
        return count;
    }
}
