package 链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    //快慢指针
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slowNode = head;
        ListNode fastNode = head.next;
        while(fastNode != null && fastNode.next != null){
            if (slowNode == fastNode){
                return true;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return false;


    }
}
