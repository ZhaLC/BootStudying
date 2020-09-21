package com.zlc.algorithm.structure;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @desc : 把二叉搜索树转换为累加树(反序中序遍历)
 **/
public class Q538_ConvertBST {

    /**
     * 递归
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root != null){
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root){
        if(root == null){
            return null;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        //TODO 不能直接返回root 是null
        TreeNode res = root;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
//            root.val += sum;
//            sum = root.val;
//            root = root.left;
            sum += root.val;
            root.val = sum;
            root = root.left;
        }
        //TODO 返回res
        return res;
    }
}
