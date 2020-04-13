package com.zlc.algorithm.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author : ZLC
 * @create : 2020-04-13 15:55
 * @desc : 数组中的第k个最大元素 方法一、方法二都是使用的函数库(TODO 需要根据实际情况来 比如实际情况数一个一个进来,使用大顶堆, 显然不会看评论说的...)
 **/
public class Q215 {

    public static int findKthLargest1(int[] nums, int k) {
        //排完是升序的
        Arrays.sort(nums);
        //排完是降序的 但是要求nums是引用类型 需要使用Integer[] nums  另一种方法是使用Comparator实现 见findKthLargest2
        //Arrays.sort(nums, Collections.reverseOrder());
        return nums[nums.length-k];
    }
    public static int findKthLargest2(Integer[] nums, int k) {
        MyComparator comparator = new MyComparator();
        Arrays.sort(nums,comparator);
        return nums[k-1];
    }

    static class MyComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            /*如果o1小于o2，我们就返回正值，如果o1大于o2我们就返回负值，
    		这样颠倒一下，就可以实现降序排序了,反之即可自定义升序排序了*/
            return (o2-o1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,7,7,6,8,9,5,3,2};
        Integer[] arr2 = {1,4,7,7,6,8,9,5,3,2};
        System.out.println(findKthLargest1(arr,2));
        System.out.println(findKthLargest2(arr2,2));
    }
}
