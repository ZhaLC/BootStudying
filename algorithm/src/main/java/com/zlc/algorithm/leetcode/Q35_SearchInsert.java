package com.zlc.algorithm.leetcode;

/**
 * @author : 清零
 * @create : 2020-07-17 23:24
 * @desc : 搜索插入位置 二分查找
 **/
public class Q35_SearchInsert {
    public int searchInsert(int[] nums, int target) {

        /**
         * 二分查找 模板
         */
        int left = 0;
        int right = nums.length - 1; //注意
        while(left <= right){ //上面减1了, 所以下面这里要加=
            int mid = left + (right - left) / 2; // 没有使用 (left+right)/2是怕溢出 但是评论区说这样也可能溢出
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                //nums[mid} > target
                right = mid -1;
            }
        }
        //注意 返回的是left
        return left;
    }
}
