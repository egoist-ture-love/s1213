package 链表;

/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class _24_两两交换链表中的节点 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode swapPairs(ListNode head) {
        //1.非递归解法
        ListNode dummp = new ListNode();
        dummp.next = head;
        ListNode temp = dummp;
        while (dummp.next != null && dummp.next.next != null){
            ListNode first = dummp.next;
            ListNode second = dummp.next.next;
            first.next = second.next;
            second.next = first;
            dummp.next = second;
            dummp = dummp.next.next;
        }
        return temp.next;
    }
}
