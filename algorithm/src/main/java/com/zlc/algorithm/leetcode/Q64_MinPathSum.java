package com.zlc.algorithm.leetcode;

/**
 * @author : 清零
 * @create : 2020-07-12 23:11
 * @desc : 最小路径和 简单dp
 **/
public class Q64_MinPathSum {
    public int minPathSum(int[][] grid) {

        /**
         * 简单dp
         * 图解 https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-dong-tai-gui-hua-gui-fan-liu-c/
         * 时间复杂度 O(M×N) ： 遍历整个 grid 矩阵元素。
         * 空间复杂度 O(1)： 直接修改原矩阵，不使用额外空间。
         */
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(i ==0 && j == 0){
                    continue;
                }
                if(i == 0){
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                }else if(j == 0){
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                }else{
                    grid[i][j] = Math.min(grid[i][j-1], grid[i-1][j]) + grid[i][j];
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
