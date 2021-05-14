package 链表;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class _234_回文链表 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 解题思路 1.找到中心节点  2.找到前置节点  3.反转链表
     *
     * @param head
     * @return
     */

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        if (fast == null) {
            //链表是偶数节点
            slow = reverseList(slow);
        } else {
            //链表是基数节点
            ListNode node = slow.next;
            slow.next = null;
            slow = reverseList(node);
        }
        while (head != null){
            if(head.val != slow.val){
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curt = head;
        while (curt != null) {
            ListNode next = curt.next;
            curt.next = pre;
            pre = curt;
            curt = next;
        }
        return pre;
    }
}
