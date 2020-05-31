package com.zlc.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : ZLC
 * @create : 2020-05-31 11:32
 * @desc : 对称二叉树
 **/
public class Q101_IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
        //递归实现(深度周游)
        //return check(root, root);
        //迭代实现(广度周游) 需要队列辅助
        return check2(root);
    }

    //递归实现(深度周游)  O(n)(遍历了这棵树) / O(n)(最大, 空间复杂度和递归栈有关,层数不超过n,所以最大为O(n) 平均)
    private boolean check(TreeNode p1, TreeNode p2){
        //两棵子树同时为空 则true
        if(p1 == null && p2 == null){
            return true;
        }
        //上面都相等返回了 所以如果if true 必然p1 != p2
        if(p1 == null || p2 == null){
            return false;
        }
        //两棵树都非空: 值相等  p1左子树等于p2右子树 p1右子树等于p2左子树
        return p1.val == p2.val && check(p1.left, p2.right) && check(p1.right, p2.left);
    }

    //迭代实现(广度周游) 需要队列辅助 O(n)(遍历了这棵树) / O(n)(最大, 每个节点最多进队一次, 出队一次, 队列中不会超过n个节点) 平均)
    private boolean check2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode p1 = queue.poll();
            TreeNode p2 = queue.poll();
            if(p1 == null && p2 == null){
                //::::::: 这里不能直接返回了 还需要判断其他节点 后面一下入了四个节点 不像递归
                //return true;
                continue;
            }
            if(p1 == null || p2 == null){
                return false;
            }
            if(p1.val != p2.val){
                return false;
            }
            queue.add(p1.left);
            queue.add(p2.right);
            queue.add(p1.right);
            queue.add(p2.left);
        }
        return true;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}





