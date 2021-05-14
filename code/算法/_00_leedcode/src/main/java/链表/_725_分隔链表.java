package 链表;

/**
 * https://leetcode-cn.com/problems/split-linked-list-in-parts/
 */
public class _725_分隔链表 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode[] splitListToParts(ListNode root, int k) {
        //1.遍历求长度
        int length = 0;
        for(ListNode head = root; head != null; head= head.next) length++;
        int elementNum = length / k;
        int numAdd = length % k;
        ListNode pre = null, head = root;
        ListNode[] arr = new ListNode[k];
        for(int i = 0; i < k; i++,numAdd--){
            arr[i] = head;
            int trueLength = elementNum + (numAdd > 0 ? 1 : 0);
            for(int j = 0; j < trueLength; j++){
                pre = head;
                head = head.next;
            }
            if(pre !=null) pre.next = null;
        }
        return arr;
    }
}
