package com.zlc.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc : 两个数组的交集II(不是连续的)
 **/
public class Q350_Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {

        /**
         * 2、排序 + 双指针
         * 时间复杂度：O(mlogm+nlogn)，其中 m 和 n 分别是两个数组的长度。对两个数组进行排序的时间复杂度是 O(mlogm+nlogn)，
         * 遍历两个数组的时间复杂度是 O(m+n)，因此总时间复杂度是 O(mlogm+nlogn)。
         *
         * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。为返回值创建一个数组 ans，其长度为较短的数组的长度。
         * 不过在 C++ 中，我们可以直接创建一个 vector，不需要把答案临时存放在一个额外的数组中，所以这种实现的空间复杂度为 O(1)O(1)。
         *
         * 总结: 如果 nums2的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中。
         * 那么就无法高效地对nums2进行排序，因此推荐使用方法一而不是方法二。
         * 在方法一中，nums2 只关系到查询操作，因此每次读取 nums2中的一部分数据，并进行处理即可。
         */
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0, index = 0;
        int[] ans = new int[Math.min(length1, length2)];
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else{
                ans[index++] = nums1[index1];
                index1++;
                index2++;
            }
        }
        //TODO 这里要把后面的截掉
        return Arrays.copyOfRange(ans, 0, index);


        /**
         * 1、哈希表
         * 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。需要遍历两个数组并对哈希表进行操作，
         *      哈希表操作的时间复杂度是 O(1)，因此总时间复杂度与两个数组的长度和呈线性关系。
         * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。对较短的数组进行哈希表的操作，
         *      哈希表的大小不会超过较短的数组的长度。为返回值创建一个数组 ans，其长度为较短的数组的长度。
         */
        /**
         * 为了低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集
         */
//        if(nums1.length > nums2.length){
//            return intersect(nums2, nums1);
//        }
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums1) {
//            int count = map.getOrDefault(num, 0) + 1;
//            map.put(num, count);
//        }
//        int[] ans = new int[nums1.length];
//        int index = 0;
//        for (int num : nums2) {
//            int count = map.getOrDefault(num, 0);
//            if(count > 0){
//                ans[index++] = num;
//                count--;
////                map.put(num, count);
//                if (count > 0) {
//                    map.put(num, count);
//                } else {
//                    map.remove(num);
//                }
//            }
//        }
//        //TODO 这里要把后面的截掉
//        return Arrays.copyOfRange(ans, 0, index);
    }
}
