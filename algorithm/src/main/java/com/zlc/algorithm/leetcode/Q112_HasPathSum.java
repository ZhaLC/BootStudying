package com.zlc.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @desc : 路径总和
 **/
public class Q112_HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {

        /**
         * 广度优先搜索 队列
         * 时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。
         * 空间复杂度：O(N)，其中 N 是树的节点数。空间复杂度主要取决于队列的开销，队列中的元素个数不会超过树的节点数。
         */
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> help = new LinkedList<>();
        queue.offer(root);
        help.offer(root.val);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            int temp = help.poll();
            if(node.left == null && node.right == null){
                if(temp == sum){
                    return true;
                }
                continue;
            }
            if(node.left != null){
                queue.offer(node.left);
                help.offer(node.left.val + temp);
            }
            if(node.right != null){
                queue.offer(node.right);
                help.offer(node.right.val + temp);
            }
        }
        return false;





        /**
         * 递归实现
         * 时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。
         * 空间复杂度：O(H)，其中 H 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，
         * 空间复杂度为 O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(logN)。
         */
//        if(root == null){
//            return false;
//        }
//        if(root.left == null && root.right == null){
//            return root.val == sum;
//        }
//        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
