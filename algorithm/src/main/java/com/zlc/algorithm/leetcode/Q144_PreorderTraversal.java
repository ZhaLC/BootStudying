package com.zlc.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-06-30 14:38
 * @desc : 二叉树的前序遍历
 **/
public class Q144_PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {

        /**
         * 还有个更牛比的莫里斯算法 先不看了
         */

        /**
         * 2、迭代
         * 时间复杂度：访问每个节点恰好一次，时间复杂度为 O(N) ，其中 N是节点的个数，也就是树的大小。
         * 空间复杂度：取决于树的结构，最坏情况存储整棵树，因此空间复杂度是 O(N)。
         */
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;


        /**
         * 1、前序遍历 迭代实现 借助Stack
         */
//        List<Integer> list = new ArrayList<>();
//        TreeNode node = root;
//        Stack<TreeNode> stack = new Stack<>();
//        while(!stack.isEmpty() || node != null){
//            while(node != null){
//                stack.push(node);
//                list.add(node.val);
//                node = node.left;
//            }
//            if(!stack.isEmpty()){
//                node = stack.pop();
//                node = node.right;
//            }
//        }
//        return list;
    }

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}


