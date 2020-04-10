package com.zlc.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ZLC
 * @create : 2020-04-10 10:31
 * @desc : 盛最多水的容器 /image/Q11.png
 **/
public class Q11 {

    public static int maxArea(int[] height) {
        int count = -1;
        int high = -1;
        int width = -1;
        int max = -1;
        for(int i = 0; i < height.length-1; i++){
            for(int j = i+1; j < height.length; j++){
                if(height[j] < height[i]){
                    high = height[j];
                    width = j - i;
                }else {
                    high = height[i];
                    width = j - i;
                }
                count = high * width;
                if(count > max){
                    max = count;
                }
            }
        }
        return max;
    }

    /**
     * 精简代码 Math.max min abs源码使用的是三元表达式
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int max = -1;
        for(int i = 0; i < height.length-1; i++){
            for(int j = i+1; j < height.length; j++){
                max = Math.max(max,Math.min(height[j],height[i]) * (j-i));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea2(arr));
    }

}
