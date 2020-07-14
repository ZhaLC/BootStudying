package com.zlc.algorithm.leetcode;

import java.util.*;

/**
 * @author : 清零
 * @create : 2020-07-14 12:47
 * @desc : 三角形最小路径和
 **/
public class Q120_MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        /**
         * 4、对方法三的优化 优化空间复杂度
         * 实际递推中我们发现，计算 dp[i][j]时，只用到了下一行的 dp[i+1][j] 和 dp[i+1][j+1]。
         * 因此 dp 数组不需要定义 N 行，只要定义 1 行就阔以啦。
         * 所以我们稍微修改一下上述代码，将 i 所在的维度去掉（如下），就可以将 O(N^2)的空间复杂度优化成 O(N)啦～
         */
        int length = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和
        int dp[] = new int[length + 1];
        // 从三角形的最后一行开始递推。
        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];


        /**
         * 3、从上往下推
         * 时间复杂度：O(N2)，NN 为三角形的行数。
         * 空间复杂度：O(N2)，NN 为三角形的行数。
         */
//        int length = triangle.size();
//        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和
//        int dp[][] = new int[length+1][length+1];
//        // 从三角形的最后一行开始递推。
//        for (int i = length-1; i >= 0 ; i--) {
//            for (int j = 0; j <= i; j++) {
//                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
//            }
//        }
//        return dp[0][0];


        /**
         * 官方题解 把一些边界条件提取出来了
         * 时间复杂度：O(n2)，其中 n 是三角形的行数。
         * 空间复杂度：O(n2)。我们需要一个 n∗n 的二维数组存放所有的状态。
         */
//        int n = triangle.size();
//        int[][] f = new int[n][n];
//        f[0][0] = triangle.get(0).get(0);
//        for (int i = 1; i < n; ++i) {
//            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
//            for (int j = 1; j < i; ++j) {
//                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
//            }
//            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
//        }
//        int minTotal = f[n - 1][0];
//        for (int i = 1; i < n; ++i) {
//            minTotal = Math.min(minTotal, f[n - 1][i]);
//        }
//        return minTotal;

        /**
         * dp 没发现, 这是等腰直角三角形  其实: size1=size2
         */
//        int size1 = triangle.size();
//        int size2 = triangle.get(size1-1).size();
//        int[][] dp = new int[size1][size2];
//        for(int i = 0; i < size1; i++){
//            for(int j = 0; j < triangle.get(i).size(); j++){
//                if(i == 0){
//                    dp[i][j] = triangle.get(i).get(j);
//                }else if(j == 0){
//                    dp[i][j] = dp[i-1][j] +  triangle.get(i).get(j);
//                }else if(i == j){
//                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);;
//                }else{
//                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);;
//                }
//            }
//        }
//        int min = dp[size1-1][0];
//        for(int i = 1; i < size2; i++){
//            min = Math.min(min, dp[size1-1][i]);
//        }
//        return min;
    }
}
