package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-04-14 16:13
 * @desc : 两数相加II
 **/

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Q445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while(l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        //进位标识
        int carry = 0;
        ListNode head = null;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry > 0){
//            int sum = 0;
            int sum = carry;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();
            ListNode node = new ListNode(sum % 10);
            //原有高位降一位 因为每次循环都是向高位进行
            node.next = head;
            //设置最新高位
            head = node;
            carry = sum / 10;
//            if(sum + carry > 9){
//                sum = sum + carry - 10;
//                carry = 1;
//            }else{
//                sum += carry;
//                carry = 0;
//            }
//            ListNode listNode = new ListNode(sum);
//            //原有高位降一位
//            listNode.next = head;
//            //设置最新高位
//            head = listNode;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
 }