package com.zlc.algorithm.sword;

/**
 * @author : 清零
 * @create : 2020-10-19 23:20
 * @desc : 二叉树的公共最近祖先(和二叉搜索树相比, 没法直接确定个左右子树了)
 **/
public class O_68_2_LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;
    }
}
