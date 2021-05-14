package 二叉树;

public class _109_有序链表转换二叉搜索树 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        if(head.next == null)return new TreeNode(head.val);
        ListNode fast = head, slow = head, pre = null;
        while(fast != null && fast.next != null){
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode treeNode = new TreeNode(slow.val);
        pre.next = null;
        treeNode.left = sortedListToBST(head);
        treeNode.right = sortedListToBST(slow.next);
        return treeNode;
    }
}
