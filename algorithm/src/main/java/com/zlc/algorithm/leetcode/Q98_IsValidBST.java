package com.zlc.algorithm.leetcode;

import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-07-01 17:56
 * @desc : 验证二叉搜索树
 **/
public class Q98_IsValidBST {

    /**
     * 中序遍历 迭代
     * 时间复杂度 : O(n)，其中 n 为二叉树的节点个数。二叉树的每个节点最多被访问一次，因此时间复杂度为 O(n)。
     * 空间复杂度 : O(n)，其中 n为二叉树的节点个数。栈最多存储 n个节点，因此需要额外的 O(n) 的空间。
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        //TODO 前中后遍历大致写法
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //TODO 为啥等于也不行?  如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if(root.val <= pre){
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }


    /**
     * 中序遍历  递归
     */
    //TODO
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        // 访问左子树
        if(!isValidBST(root.left)){
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if(root.val <= pre){
            return false;
        }
        //前一个的值
        pre = root.val;
        //访问右子树
        return isValidBST(root.right);
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
