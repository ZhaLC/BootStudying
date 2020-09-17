package com.zlc.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @desc :
 **/
public class Q226_InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * BFS  : com.zlc.algorithm.structure.tree
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode left = node.right;
            node.right = node.left;
            node.left = left;
            if(left != null){
                queue.offer(left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return root;
    }


    public TreeNode invertTree3(TreeNode root) {
        if(root == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode left = node.right;
            node.right = node.left;
            node.left = left;
            if(node.right != null){
                stack.push(node.right);
            }
            if(left != null){
                stack.push(left);
            }
        }
        return root;
    }
}
