package com.zlc.algorithm.leetcode;

import java.util.*;

/**
 * 二叉树的右视图
 */
public class Q199 {

    //深度DFS(Deep First Search)
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightMostValueAtDepth = new HashMap<>();
        int maxDepth = -1;
        Stack<TreeNode> treeNodes = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        treeNodes.push(root);
        depths.push(0);
        while(!treeNodes.isEmpty()){
            TreeNode node = treeNodes.pop();
            int depth = depths.pop();
            if(node != null){
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth, depth);
                // 如果不存在对应深度的节点我们才插入
                if(!rightMostValueAtDepth.containsKey(maxDepth)){
                    rightMostValueAtDepth.put(maxDepth,node.val);
                }
                treeNodes.push(node.left);
                treeNodes.push(node.right);
                depths.push(depth+1);
                depths.push(depth+1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= maxDepth; i++){
            list.add(rightMostValueAtDepth.get(i));
        }
        return list;
    }

    //广度BFS(Breath First Search)
    public List<Integer> rightSideView2(TreeNode root) {
        Map<Integer, Integer> rightMostValueAtDepth = new HashMap<>();
        int maxDepth = -1;
        Queue<TreeNode> treeQueus = new LinkedList<>();
        Queue<Integer> depths = new LinkedList<>();
        treeQueus.add(root);
        depths.add(0);
        while(!treeQueus.isEmpty()){
            TreeNode node = treeQueus.remove();
            int depth = depths.remove();
            if(node != null){
                // 维护二叉树的最大深度
                maxDepth = Math.max(maxDepth, depth);
                // 因为每一层最后的元素才是想要的 所以一直更新即可
                rightMostValueAtDepth.put(maxDepth,node.val);
                treeQueus.add(node.left);
                treeQueus.add(node.right);
                depths.add(depth+1);
                depths.add(depth+1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= maxDepth; i++){
            list.add(rightMostValueAtDepth.get(i));
        }
        return list;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}



