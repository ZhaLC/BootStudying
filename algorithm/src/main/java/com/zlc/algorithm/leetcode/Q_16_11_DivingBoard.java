package com.zlc.algorithm.leetcode;

import java.util.*;

/**
 * @desc : 跳水板
 **/
public class Q_16_11_DivingBoard {
    public int[] divingBoard(int shorter, int longer, int k) {
        /**
         * 纯数学解法 没啥说的 另外 返回值用的数组不算在空间复杂度里
         * 时间复杂度：O(k)，其中 kk 是木板数量。短木板和长木板一共使用 k 块，一共有 k+1 种组合，对于每种组合都要计算跳水板的长度。
         * 空间复杂度：O(1)。除了返回值以外，额外使用的空间复杂度为常数。
         */
       if(k == 0){
           return new int[0];
       }
       if(shorter == longer){
           return new int[]{shorter * k};
       }
       int[] arr = new int[k+1];
       for(int i = 0; i <= k; i++){
            arr[i] = longer * i + shorter * (k - i);
       }
       return arr;
    }
}
