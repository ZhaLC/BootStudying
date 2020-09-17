package com.zlc.algorithm.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @desc : BFS DFS https://leetcode-cn.com/problems/invert-binary-tree/solution/di-gui-bfshe-dfsduo-chong-fang-shi-jie-jue-quan-bu/
 **/
public class tree {
    /**
     * BFS 层序遍历
     * @param root
     */
    public void levelOrder(TreeNode root) {
        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    /**
     * DFS
     * @param root
     */
    public void treeDFS(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }
}