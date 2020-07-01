package com.zlc.algorithm.leetcode;

import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-07-01 16:32
 * @desc : 二叉树的最大深度
 **/
public class Q104_MaxDepth {
    public int maxDepth(TreeNode root) {

        /**
         * 递归 不好理解
         * 时间复杂度：我们每个结点只访问一次，因此时间复杂度为 O(N)，其中 N 是结点的数量。
         * 空间复杂度：
         *      在最糟糕的情况下，树是完全不平衡的，例如每个结点只剩下左子结点，递归将会被调用 N次（树的高度），因此保持调用栈的存储将是 O(N)。
         *      但在最好的情况下（树是完全平衡的），树的高度将是 log(N)。因此，在这种情况下的空间复杂度将是 O(log(N))。
         */
        if(root == null){
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        return Math.max(leftMax, rightMax) + 1;



        /**
         * 迭代 DFS Q199二叉树的右视图类似
         * 时间O(n)
         * 空间O(n)
         *
         * TODO javafx.util.Pair类 配对(Pair) 配对提供了一种方便方式来处理简单的键值关联 想从结果返回两个值有用
         */
//        if(root == null){
//            return 0;
//        }
//        Stack<Pair<Integer, TreeNode>> stack = new Stack<>();
//        int maxDepth = -1;
//        Pair<Integer, TreeNode> pair = new Pair<>(1, root);
//        stack.push(pair);
//        while (!stack.isEmpty()) {
//            pair = stack.pop();
//            int depth = pair.getKey();
//            TreeNode node = pair.getValue();
//            if(node != null){
//                maxDepth = Math.max(maxDepth, depth);
//                stack.push(new Pair<>(depth+1, node.left));
//                stack.push(new Pair<>(depth+1, node.right));
//            }
//        }
//        return maxDepth;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
