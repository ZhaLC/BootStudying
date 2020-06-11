package com.zlc.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : ZLC
 * @create : 2020-06-10 18:33
 * @desc : 两两交换链表中的节点
 **/
public class Q24_SwapPairs {

    public ListNode swapPairs(ListNode head) {

        /**
         * 2、递归 下一次的参数是新头 每次交换两个节点
         * 时间: O(n) 链表的节点数量
         * 空间: O(n) 递归栈
         */
        // If the list has no node or has only one node left.
        if(head == null || head.next == null){
           return head;
        }
        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        // Swapping
        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;
        // Now the head is the second node
        return secondNode;


        /**
         * 1、递归解法
         * 时间: O(n) n 为链表节点数量
         * 空间: O(1)
         */
        // Dummy node acts as the prevNode for the head node
        // of the list and hence stores pointer to the head node.
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        ListNode preNode = dummy;
//        while(head != null && head.next != null){
//            // Nodes to be swapped
//            ListNode firstNode = head;
//            ListNode secondNode = head.next;
//
//            // Swapping
//            preNode.next = secondNode;
//            firstNode.next = secondNode.next;
//            secondNode.next = firstNode;
//
//            // Reinitializing the head and prevNode for next swap
//            preNode = firstNode;
//            head = firstNode.next;
//        }
//        // Return the new head node.
//        return dummy.next;



        /**
         * 做不出来...不对
         */
//        Queue<ListNode> queue = new LinkedList<>();
//        if(head == null){
//            return null;
//        }
//        //ListNode returnNode = new ListNode(0);
//        ListNode node = new ListNode(0);
//        queue.add(head);
//        ListNode tempNode;
//        ListNode tempNode2;
//        ListNode tempNode3;
//        while(!queue.isEmpty()){
//            tempNode = queue.poll();
//            tempNode2 = tempNode.next;
//            tempNode3 = tempNode;
//            if(tempNode2 == null){
//                node.next = tempNode;
//            }else{
//                tempNode.next = null;
//                tempNode2.next = null;
//                node.next = tempNode2;
//                node.next.next = tempNode;
//                node = node.next.next;
//
//                if(tempNode3.next.next != null){
//                    queue.add(tempNode3.next.next);
//                }
//            }
//        }
//        return node.next;
    }


    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
