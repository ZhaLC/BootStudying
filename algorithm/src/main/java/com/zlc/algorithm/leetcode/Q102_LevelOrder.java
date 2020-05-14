package com.zlc.algorithm.leetcode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
 */
public class Q102_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root != null){
            List<Integer> list;
            Queue<TreeNode> queue = new LinkedList();
            TreeNode node = root;
            queue.offer(node);
            while(!queue.isEmpty()){
                int count = queue.size();
                list = new LinkedList<>();
                for (int i = 0; i < count; i++) {
                    node = queue.poll();
                    list.add(node.val);
                    if(node.left != null){
                        queue.offer(node.left);
                    }
                    if(node.right != null){
                        queue.offer(node.right);
                    }
                }
                lists.add(list);

            }
        }
        return lists;
    }

     //Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }


}