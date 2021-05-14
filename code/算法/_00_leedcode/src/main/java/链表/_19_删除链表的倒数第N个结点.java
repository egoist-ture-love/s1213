package 链表;

public class _19_删除链表的倒数第N个结点 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 重点是快慢指针思想
         */
        ListNode temp = new ListNode();
        temp.next = head;
        ListNode slow = temp;
        ListNode fast = head;
        while (n > 0){
            fast = fast.next;
            n--;
        }
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;
        return temp.next;
    }
}
