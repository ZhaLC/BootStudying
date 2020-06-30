package com.zlc.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : ZLC
 * @create : 2020-06-30 15:48
 * @desc : 用两个栈实现队列
 **/
public class Q_O9_CQueue {

    /**
     * 时间复杂度：对于插入和删除操作，时间复杂度均为 O(1)。插入不多说，对于删除操作，虽然看起来是 O(n) 的时间复杂度，
     *          但是仔细考虑下每个元素只会「至多被插入和弹出 stack2 一次」，因此均摊下来每个元素被删除的时间复杂度仍为 O(1)。
     * 空间复杂度：O(n)。需要使用两个栈存储已有的元素。
     */
    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public Q_O9_CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    /**
     * 添加正常添加
     * 删除的时候s2位空则将s1出栈加到s2中  s1负责添加 s2负责删除
     * @return
     */
    public int deleteHead() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else{
            return -1;
        }
    }
}
/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new Q_O9_CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
