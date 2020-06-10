package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-06-10 17:24
 * @desc : 两数相加
 **/
public class Q2_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        /**
         * 2、思路是一样的 代码看着优雅点
         */
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;

        /**
         * 1、
         */
//        ListNode returnNode = new ListNode(0);
//        ListNode node1 = l1;
//        ListNode node2 = l2;
//        ListNode node = returnNode;  //node是临时节点 用于递归下一个
//        int curr = 0;
//        while(node1 != null || node2 != null){
//            int var1 = 0;
//            int var2 = 0;
//            int sum = 0;
//            if(node1 != null){
//                var1 = node1.val;
//            }
//            if(node2 != null){
//                var2 = node2.val;
//            }
//            sum = var1 + var2 + curr;
//            curr = sum / 10;
//            node.next = new ListNode(sum % 10);
//            // !!!!!!!! 这行 同时上面应该新加一个用于遍历的node节点
//            node = node.next;
//
//            if(node1 != null){
//                node1 = node1.next;
//            }
//            if(node2 != null){
//                node2 = node2.next;
//            }
//        }
//        //这个别忘了
//        if(curr > 0){
//           node.next = new ListNode(curr);
//        }
//        return returnNode.next;
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
