package com.zlc.algorithm.leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-06-30 15:32
 * @desc : N叉树的前序遍历
 *  树相关: TODO https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/solution/yi-tao-quan-fa-shua-diao-nge-bian-li-shu-de-wen--3/
 **/
public class Q589_Preorder {
    public List<Integer> preorder(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        Stack<Node> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        Node node = root;
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            list.add(node.val);
            //函数啊
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
            /*List<Node> childen = node.children;
            if(childen != null){
                for (int i = childen.size()-1; i >=0 ; i--) {
                    if(childen.get(i) != null){
                        stack.push(childen.get(i));
                    }
                }
            }*/
        }
        return list;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
