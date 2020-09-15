package com.zlc.algorithm.leetcode;

/**
 * @desc :
 **/
public class Q671_FindSecondMinimumValue {
    int res = -1;
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null){
            return res;
        }
        //如果存在子树且值不相等, 那么较大的值有可能是第二小的
        if(root.left != null && root.left.val != root.right.val){
            //获取左右子树中较大的值
            int bigger = Math.max(root.left.val,root.right.val);
            //如果返回值没有被改过, 则bigger有可能就是第二小的, 如果返回值被改过, 则比较当前的热死和bigger哪个更小
            res = res == -1 ? bigger : Math.min(res, bigger);
            //将左右子树中更小的树进行递归, 查找是否有更小的值(即为了上一步判断)
            findSecondMinimumValue(root.left.val > root.right.val ? root.right : root.left);
        }
        //如果左右子树相等或为空, 分别递归
        else{
            findSecondMinimumValue(root.left);
            findSecondMinimumValue(root.right);
        }
        return res;
    }
}
