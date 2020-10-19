package com.zlc.algorithm.sword;

/**
 * @author : 清零
 * @create : 2020-10-19 22:27
 * @desc : 二叉搜索树的最近公共祖先
 **/
public class O_68_1_LowestCommonAncestor {
    /**
     * 迭代
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            if(root.val < p.val && root.val < q.val){
                root = root.right;
            }else if(root.val > p.val && root.val > q.val){
                root = root.left;
            }else{
                break;
            }
        }
        return root;
    }

    /**
     * 迭代
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val)
            return lowestCommonAncestor2(root.right, p, q);
        if(root.val > p.val && root.val > q.val)
            return lowestCommonAncestor2(root.left, p, q);
        return root;
    }
}
