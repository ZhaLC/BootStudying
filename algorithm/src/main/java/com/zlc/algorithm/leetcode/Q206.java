package com.zlc.algorithm.leetcode;

/**
 * @author : ZLC
 * @create : 2020-04-18 16:46
 * @desc : 链表反转
 **/
public class Q206 {

    // 迭代法 https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            //用于保存下一个节点的临时节点
            ListNode tempNext = cur.next;
            cur.next = pre;
            //pre 和 cur 节点都前进一步
            pre = cur;
            cur = tempNext;
        }
        return pre;
    }

    //TODO 还有递归实现没看懂



    public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }

}


