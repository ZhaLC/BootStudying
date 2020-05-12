package com.zlc.algorithm.leetcode;

import java.util.Stack;

/**
 * @author : ZLC
 * @create : 2020-05-12 10:24
 * @desc : 1、使用辅助栈 辅助栈和数据栈同步 Method2为辅助栈和数据栈不同步 会节省一些空间, 但是要考虑临界值的问题
 *         2、不使用辅助栈 LastMin 数据冗余法 MInStack2
 **/
public class Q155_MinStack {

    private Stack<Integer> data;
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public Q155_MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if(helper.isEmpty() || x < helper.peek()){
            helper.push(x);
        }else{
            helper.push(helper.peek());
        }
    }
    public void push2(int x) {
        data.add(x);
        if(helper.isEmpty() || x <= helper.peek()){
            helper.add(x);
        }
    }

    //删除
    public void pop() {
        if(!data.isEmpty()){
            data.pop();
            helper.pop();
        }
    }
    public void pop2(){
//        if(!data.isEmpty()){
//            data.pop();
//        }
//        if(data.peek().equals(helper.peek())){  //这里必须用equals 否则可能会不等
//            helper.pop();
//        }
        //为啥data必须要pop?
        int top = data.pop(); //完成了自动拆箱 下面比较可以用 ==
        if(top == helper.peek()){
            helper.pop();
        }

    }

    //获得
    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空, 此操作非法!");
    }

    public int getMin() {
        if(helper.size() != 0){
            return helper.peek();
        }
        throw new RuntimeException("栈中元素为空, 此操作非法!");
    }

    class MinStack2{
        private Stack<Integer> data;
        private int min;

        public MinStack2() {
            data = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if(x <= min){
                data.push(min);
                min = x;
            }
            data.push(x);
        }

        public void pop() {
            if(data.pop() == min){
                min = data.pop();
            }
        }

        //获得
        public int top() {
            if(!data.isEmpty()){
                return data.peek();
            }
            throw new RuntimeException("栈中元素为空, 此操作非法!");
        }

        public int getMin() {
            return min;
        }

    }



}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
