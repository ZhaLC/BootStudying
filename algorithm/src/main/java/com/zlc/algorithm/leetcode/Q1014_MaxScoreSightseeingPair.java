package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-17 23:01
 * @desc : 最佳观光组合
 **/
public class Q1014_MaxScoreSightseeingPair {
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0;
        int mx = A[0] + 0;
        for(int j = 1; j < A.length; j++){
            ans = Math.max(ans, mx + A[j] - j);
            //边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }
}
