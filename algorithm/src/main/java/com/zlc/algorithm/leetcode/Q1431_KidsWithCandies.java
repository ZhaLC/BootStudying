package com.zlc.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : ZLC
 * @create : 2020-06-01 9:58
 * @desc : 拥有最多糖果的孩子
 **/
public class Q1431_KidsWithCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new LinkedList<>();
        int maxValue = -1;
        for (int i = 0; i < candies.length; i++) {
            maxValue = Math.max(maxValue,candies[i]);
        }
        for (int i = 0; i < candies.length; i++) {
//            if (candies[i] + extraCandies >= maxValue){
//                list.add(true);
//            }else{
//                list.add(false);
//            }
            list.add(candies[i] + extraCandies >= maxValue);
        }
        return list;
    }
}
