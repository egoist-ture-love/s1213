package 链表;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */

public class _160_相交链表 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * 判断两个链表是否相交
         * 就是把两个链表交替循环一遍,看是否有相同的节点,如果有就会返回相同的节点,
         * 没有就会发现遍历判断之后的节点为null
         */
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
