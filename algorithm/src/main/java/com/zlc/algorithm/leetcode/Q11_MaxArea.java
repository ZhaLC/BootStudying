package com.zlc.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ZLC
 * @create : 2020-04-10 10:31
 * @desc : 盛最多水的容器 /image/Q11.png
 **/
public class Q11_MaxArea {

    public static int maxArea(int[] height) {

        /**
         * 双指针
         * 时间复杂度：O(N)，双指针总计最多遍历整个数组一次。
         * 空间复杂度：O(1)，只需要额外的常数级别的空间。
         *
         * 思路: 为什么这杨可以最大?
         * 用边界值分析, 最开始 l, r分别指向两边, 假设 arr[l] < arr[r], 指针之间距离t:
         * 面积: arr[l] * t = temp
         *      l不变, r无论怎么移动(最开始r在最右侧, 所以只能左移), 面积都不会超过 arr[l] * t;
         *      所以, l 对应的数不会作为容器的边界了, 故 l 右移
         *      就指向了一个新的、规模减少了的问题的数组的左右边界
         *      继续重复上面的思路就可以了
         */
        int max = 0;
        int low = 0;
        int high = height.length - 1;
        while (low < high) {
            int area = (high - low) * Math.min(height[low], height[high]);
            max = Math.max(max, area);
            //max = Math.max(max, (high - low) * Math.min(height[low], height[high]));
            if(height[low] <= height[high]){
                low++;
            }else{
                high--;
            }
        }
        return max;



//        int count = -1;
//        int high = -1;
//        int width = -1;
//        int max = -1;
//        for(int i = 0; i < height.length-1; i++){
//            for(int j = i+1; j < height.length; j++){
//                if(height[j] < height[i]){
//                    high = height[j];
//                    width = j - i;
//                }else {
//                    high = height[i];
//                    width = j - i;
//                }
//                count = high * width;
//                if(count > max){
//                    max = count;
//                }
//            }
//        }
//        return max;
    }

    /**
     * 精简代码 Math.max min abs源码使用的是三元表达式
     *
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int max = -1;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, Math.min(height[j], height[i]) * (j - i));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea2(arr));
    }

}
